package uk.ac.ed.inf.wimo.stix.agent.log;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.communication.LogMessage;
import uk.ac.ed.inf.wimo.stix.agent.storage.StorageManager;

/**
 * The LogManager maintains tracks of the most recent messages originated locally
 * or at the neighboring sites.
 * 
 * @author matt
 *
 */
public class LogManager {
	
	private Logger log = Logger.getLogger(LogManager.class);
	
	private static LogManager instance;
	
	public synchronized static LogManager getInstance() {
		if (instance == null)
			instance = new LogManager();
		return instance;
	}
	
	private LogManager() {}
	
	/**
	 * Executes a query on the Log repository and returns the corresponding record set
	 * @param query The query to be executed
	 * @return The RecordSet containing all the elements that the query returns
	 */
	public ResultSet queryLog(String query) {
		
		try {
			StorageManager storageManager = StorageManager.getInstance();
			return storageManager.query(query);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	/**
	 * 
	 * @param agentId
	 */
	public void removeLastMessage(String agentId){
		String query = "DELETE FROM log WHERE agent_id='"+agentId+"' AND timestamp=(SELECT min(timestamp) FROM log)";
		
		queryLog(query);
	}
	
	/**
	 * 
	 * @param messageId
	 * @return
	 */
	public boolean messageExists(String messageId){
		String query = "SELECT * FROM log WHERE message_id='"+messageId+"'";
		
		ResultSet results = queryLog(query);
		
		try {
			if(results.next()){
				return true;
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		
	}
	
	public int getMessageCount(String agentId){
		
		String query = "SELECT COUNT(*) FROM log WHERE agent_id='"+agentId+"'";
		
		try {
			StorageManager storageManager = StorageManager.getInstance();
			ResultSet results = storageManager.query(query);
			
			if(results.next()){
				return results.getInt(1);
			}
			else {
				return 0;
			} 
		} catch (SQLException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		
		return 0;
	}
	
	/**
	 * Writes a new message in the log
	 * @param agent The ID of the agent
	 * @param workflowId The ID of the workflow
	 * @param name The name of the variable
	 * @param value The value of the variable
	 * @param timeStamp The time when the event happened
	 */
	public void writeLog(String agent, String workflowId, String name, String value, Date timeStamp) {
		LogMessage logMessage = new LogMessage(agent, workflowId, name, value, timeStamp);
		writeLog(logMessage);
	}
	
	/**
	 * 
	 * @param logMessage
	 */
	public void writeLog(LogMessage logMessage){
		LogWorker worker = new LogWorker(logMessage);
		
		new Thread(worker).start();
	}
}
