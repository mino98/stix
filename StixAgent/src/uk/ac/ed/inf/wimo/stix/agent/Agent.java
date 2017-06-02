package uk.ac.ed.inf.wimo.stix.agent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.communication.CommunicationManager;
import uk.ac.ed.inf.wimo.stix.agent.device.DeviceManager;
import uk.ac.ed.inf.wimo.stix.agent.engine.Engine;
import uk.ac.ed.inf.wimo.stix.agent.engine.WorkflowException;
import uk.ac.ed.inf.wimo.stix.agent.workflow.WorkflowManager;

/**
 * The main Agent class that manages all the other components
 * @author damon
 */

public class Agent {
	
	private static Logger log = Logger.getLogger(Agent.class);
	
	private final static String AGENT_PROP = "agent.properties";
	private final static String AGENT_ID_PROP = "agentid";
	private final static String J_ID_PROP = "j";
	private final static String K_ID_PROP = "k";
	private final static String JNUM_ID_PROP = "jnum";
	private final static String KNUM_ID_PROP = "knum";
	private final static String POLLING_PROP = "pollingperiod";
	private final static String GC_PROP = "gc";
	private final static int DEFAULT_PORT = 9999; 
	private final static String DEFAULT_PORT_PROP = "port";
	
	private static Properties properties;
	
	/**
	 * The main method that starts the agent
	 */
	public static void main(String[] args) {
		
		log.info("Agent Starting up...");
		
		// Starts garbage collector if property 'gc=1'
		if ("1".equals(getProperty(GC_PROP)))
			startGarbageCollector();
		
		// Instanciate devices
		DeviceManager.getInstance().registerDevices();
		// Start device polling
		DeviceManager.getInstance().startConditionPolling(
				Long.parseLong(getProperty(POLLING_PROP)));

		WorkflowManager workflowManager = WorkflowManager.getInstance();
		
		try {
			workflowManager.bootstrap();
		} catch (IOException e) {
			log.error(e);
			log.error("There was an error instantiating the database");
			log.error("Exiting Agent startup");
			return;
		} catch (WorkflowException e) {
			log.error(e);
			log.error("There was an error registering existing Workflows.");
			log.error("Exiting Agent startup");
			return;
		}
		
		CommunicationManager comm = CommunicationManager.Factory.getInstance();
		
		Thread commThread = new Thread(comm);
		commThread.start();
		
	}

	/**
	 * Gets the value of an Agent property.
	 * @param propertyName The name of the property
	 * @return The value of the property
	 */
	public static String getProperty(String propertyName) {
		if (properties == null) {
			InputStream is = Agent.class.getResourceAsStream("/" + AGENT_PROP);
			try {
				if (is == null)
					is = new FileInputStream(AGENT_PROP);
				properties = new Properties();
				properties.load(is);
			} catch (IOException e) {
				log.error(e);
			}
		}
		return properties.getProperty(propertyName);
	}
	
	/**
	 * Gets the Agent ID of this agent.
	 * @return The Agent ID of this agent
	 */
	public static String getAgentId() {
		return getProperty(AGENT_ID_PROP);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static int getJ(){
		return Integer.parseInt(getProperty(J_ID_PROP));
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getK(){
		return Integer.parseInt(getProperty(K_ID_PROP));
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getJNum(){
		return Integer.parseInt(getProperty(JNUM_ID_PROP));
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getKNum(){
		return Integer.parseInt(getProperty(KNUM_ID_PROP));
	}
	
	/**
	 * Starts thread that runs the garbage collector continuously.
	 */
	private static void startGarbageCollector() {
		Thread thread = new Thread(new Date().toString() +
		" gc run ") {
			public void run() {
				while (true) {
					System.out.print("XXXXTime: " + System.currentTimeMillis() +
							", NrOfWorkflows: " + Engine.getInstance().getWorkflowNumber() + ", ");
					System.gc();
					Thread.yield();
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
	}

	public static int getDefaultPort() {
		String port = getProperty(DEFAULT_PORT_PROP);
		if ((port == null) || "".equals(port))
			return DEFAULT_PORT;
		return Integer.parseInt(port);
	}
	
}
