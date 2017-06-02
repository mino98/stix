package uk.ac.ed.inf.wimo.stix.agent.storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.communication.LogMessage;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType;
import uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil;

/**
 * The StorageManager provides a persistent storage with an appropriate
 * database interface.
 * 
 * @author damon
 *
 */
public class StorageManager {

	private static final Logger log = Logger.getLogger(StorageManager.class);
	
	private final String DB_PROP = "db.properties";
	
	private static StorageManager instance;
	
	private String dbUrl;
	private String dbUser;
	private String dbPass;
	private String dbDriver;
	private Connection conn;
	
	public synchronized static StorageManager getInstance() throws IOException {
		if(instance == null){
			instance = new StorageManager();
		}
		return instance;
	}
	
	private StorageManager() throws IOException {
		
		String dbPath = System.getProperty(DB_PROP);
		InputStream is = null;
		
		/* look for the system property first */
		if(dbPath != null && dbPath.equals("")){
			is = new FileInputStream(dbPath);
		} else {
			is = StorageManager.class.getResourceAsStream("/"+DB_PROP);
		}
		
		Properties dbProp = new Properties();
		dbProp.load(is);
		
		dbUrl = dbProp.getProperty("db.url");
		dbUser = dbProp.getProperty("db.user");
		dbPass = dbProp.getProperty("db.pass");
		dbDriver = dbProp.getProperty("db.driver");
		
		try {
			Class.forName(dbDriver).newInstance();
		} catch (ClassNotFoundException e1) {
			log.error(e1);
		} catch (IllegalAccessException e){
			log.error(e);
		} catch (InstantiationException e){
			log.error(e);
		}
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			log.error(e);
		}
		
	}
	
	
	/**
	 * Executes a query on the local storage and returns the resulting record set.
	 * The query can also be an UPDATE or ADD.
	 * @param query The query to be executed
	 * @return The corresponding RecordSet
	 */
	public ResultSet query(String query) {
		
		try {			
			Statement statement = conn.createStatement();
			boolean isResultSet = statement.execute(query);
			
			if(isResultSet){
				return statement.getResultSet();
			}
			
			return null;
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param workflow
	 */
	public void writeWorkflowXml(Workflow workflow) {
		
		try {
			
			MetadataComplexType metadata = workflow.getMetadata();
			
			String uuid = metadata.getUUID();
			String name = metadata.getName();
			String author = metadata.getAuthor();
			int rev = metadata.getRev();
			String notes = metadata.getNotes();
			boolean enabled = metadata.isEnabled();
			long startLong = 0;
			long endingLong = 0;
			
			ValidityType validity = metadata.getValidity();
			if(validity != null){
				Calendar startingFrom = metadata.getValidity().getNotBefore();
				Calendar endingOn = metadata.getValidity().getNotAfter();
				
				startLong = startingFrom.getTimeInMillis();
				endingLong = endingOn.getTimeInMillis();
			}
			
			
			log.debug("writing workflow with uuid: "+uuid);
			
			String xml = XmlUtil.workflowToString(workflow);
			
			//Statement statement = conn.createStatement();			
			
			String sql = "INSERT INTO workflow VALUES ('"+uuid+"', '"+name+"', '"+author+"', '"+rev+"', '"+
			notes+"', '"+enabled+"', '"+startLong+"', '"+endingLong+"', '"+xml+"')";
			
			String prepared = "INSERT INTO workflow VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			log.debug(sql);
			
			PreparedStatement statement = conn.prepareStatement(prepared);
			
			statement.setString(1, uuid);
			statement.setString(2, name);
			statement.setString(3, author);
			statement.setInt(4, rev);
			statement.setString(5, notes);
			statement.setBoolean(6, enabled);
			statement.setLong(7, startLong);
			statement.setLong(8, endingLong);
			statement.setString(9, xml);
			
			statement.executeUpdate();
			//statement.executeUpdate(sql);
		} catch (SQLException e) {
			log.error(e);
		}
	}
	
	/**
	 * Retrieves all stored workflows
	 * @return Array of XML representation of all workflows
	 */
	public Workflow[] readWorkflowXml() {
		
		List<Workflow> workflowList = new ArrayList<Workflow>();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM workflow");
		
			while (results.next()) {	
				String xml = results.getString("xml");
				Workflow workflow = XmlUtil.workflowFromXML(xml);
				workflowList.add(workflow);
			}
			
		} catch (SQLException e){
			log.error(e);
		}
		
		return workflowList.toArray(new Workflow[0]);
	}
	
	/**
	 * Returns the value of a variable with a specific name of a specific workflow
	 * @param workflowId The ID of the workflow
	 * @param name The name of the variable
	 * @return The value of the variable
	 */
	public String readWorkflowVariable(String workflowId, String name) {
		try {
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery("SELECT value FROM workflow_var WHERE uuid='"+workflowId+"' AND name='"+name+"'");
		
			results.next();
			String value = results.getString("value");
		
			return value;
		} catch (SQLException e){
			log.error(e);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param workflow
	 * @return
	 */
	public boolean tryRevisionUpdate(Workflow workflow){
		
		MetadataComplexType metadata = workflow.getMetadata();
		
		int rev = metadata.getRev();
		String uuid = metadata.getUUID();
		
		/* this block handles the logic of checking for a workflow with the same uuid */
		try {
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT uuid, rev FROM workflow WHERE uuid='"+uuid+"'");
			
			if(result.next()){  	// if we got a hit
				int existingRev = result.getInt("rev");
				
				if(rev <= existingRev){ //drop if new workflow revision is the same or less than the existing one
					log.info("workflow already exists or has a smaller revision");
					return false;
				}
				else if(rev > existingRev){
					//remove the existing row from the database
					statement.executeUpdate("DELETE FROM workflow WHERE uuid='"+uuid+"'");
				}				
			}
			//uniqueness constraint on uuid enforces the > 1 case.
			//else there is 0 which is fine. do nothing
			
			//now insert the new one
			writeWorkflowXml(workflow);
			return true;
		}
		catch(SQLException ex){
			log.error(ex);
			log.error("caught a SQL exception checking uuid and rev in workflow db. exiting.");
			return false;
		}		
	}
	
	/**
	 * Writes the value of a variable with specific name and workflow ID into storage.
	 * @param workflowId The ID of the workflow
	 * @param name The name of the variable
	 * @param value The value of the variable
	 */
	public void writeWorkflowVariable(String workflowId, String name, String value) {
		
		log.debug("writing workflow variable ("+workflowId+","+name+","+value+")");
		
		try {			
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO workflow_var VALUES ('"+workflowId+"', '"+name+"', '"+value+"')");
		} catch (SQLException e) {
			log.error(e);
		}
	}
	
	/**
	 * Returns the value of a device variable with a specific name
	 * @param name The name of the variable
	 * @return The value of the variable
	 */
	public String readDeviceVariable(String name) {
		try {
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery("SELECT value FROM device_var WHERE name='"+name+"'");
		
			results.next();
			String value = results.getString("value");
		
			return value;
		} catch (SQLException e){
			log.error(e);
		}
		
		return null;
	}
	
	/**
	 * Writes the device value of a variable with specific name into storage.
	 * @param name The name of the variable
	 * @param value The value of the variable
	 */
	public void writeDeviceVariable(String name, String value) {
		
		log.debug("writing device variable ("+name+", "+value+")");
		
		try {			
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO device_var VALUES ('"+name+"', '"+value+"')");
		} catch (SQLException e) {
			log.error(e);
		}
		
	}
	
	/**
	 * 
	 * @param logMessage
	 */
	public void writeLog(LogMessage logMessage){
		
		String agentId = logMessage.getAgentId();
		String workflowId = logMessage.getWorkflowId();
		String name = logMessage.getName();
		String value = logMessage.getValue();
		Date timestamp = logMessage.getTimestamp();
		String messageId = logMessage.getMessageId();
		int j = logMessage.getJ();
		int k = logMessage.getK();
		
		log.debug("writing log message id "+messageId);
		
		String sql = "INSERT INTO log VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, agentId);
			statement.setString(2, workflowId);
			statement.setString(3, name);
			statement.setString(4, value);
			statement.setLong(5, timestamp.getTime());
			statement.setString(6, messageId);
			statement.setInt(7, j);
			statement.setInt(8, k);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	
}
