package uk.ac.ed.inf.wimo.stix.agent.log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.communication.LogQueryMessage;

/**
 * 
 * @author matt
 *
 */
public class LogQueryRunnable implements Runnable {

	private Logger log = Logger.getLogger(LogQueryRunnable.class);
	
	private String query;
	private Socket socket;
	
	public LogQueryRunnable(String query, Socket socket){
		this.query = query;
		this.socket = socket;
	}
	
	public void run() {
		LogQueryMessage message = getResults();
		sendResults(message);
	}
	
	protected LogQueryMessage getResults(){
		
		LogManager logManager = LogManager.getInstance();
		
		ResultSet results = logManager.queryLog(query);
		
		StringBuffer buffer = new StringBuffer();
		
		//buffer.append("<results>");
		
		try {
			ResultSetMetaData metadata = results.getMetaData();
			
			int numColumns = metadata.getColumnCount();
			
			while (results.next()) {
				buffer.append("<logentry>");
				for(int i=1; i<=numColumns; i++){
					String columnName = metadata.getColumnName(i);
					String column = "<"+columnName+">"+results.getString(i)+"</"+columnName+">";
					buffer.append(column);
				}
				buffer.append("</logentry>");
			}
		}
		catch(SQLException e){
			log.error(e);
		}
		
		//buffer.append("</results>");
		
		String resultXml = buffer.toString();
		
		log.debug(resultXml);
		
		LogQueryMessage message = new LogQueryMessage();
		message.setResults(resultXml);	
		
		return message;
	}
	
	protected void sendResults(LogQueryMessage message){
		
		/* write the result back to the requester */
		try {
			BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
			
			String m = message.toXml() + "\n\n";
			
			os.write(m.getBytes());
			
			os.close();
		} catch (IOException e) {
			log.error(e);
			return;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {log.error(e); }
		}
	}
	
	
	public static void main(String args[]) throws IOException {
		
		LogQueryMessage m = new LogQueryMessage("SELECT * FROM log");
		
		String messageXml = m.toXml();
		messageXml+="\n\nEOF\n\n";
		
		Socket socket = new Socket("testbedgw.wimo.inf.ed.ac.uk", 9991);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		DataOutputStream bos = new DataOutputStream(socket.getOutputStream());
		bos.write(messageXml.getBytes());
		
		String line = null;
		while((line = reader.readLine()) != null){
			System.out.println(line);
		}
	}

}
