package uk.ac.ed.inf.wimo.stix.agent.log;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.Agent;
import uk.ac.ed.inf.wimo.stix.agent.communication.CommunicationManager;
import uk.ac.ed.inf.wimo.stix.agent.communication.LogMessage;
import uk.ac.ed.inf.wimo.stix.agent.storage.StorageManager;

/**
 * 
 * @author matt
 *
 */
public class LogWorker implements Runnable {
	
	private Logger log = Logger.getLogger(LogWorker.class);
	
	private final int jNum = Agent.getJNum();
	private final int kNum = Agent.getKNum();
	private final String thisAgent = Agent.getAgentId();
	
	private final LogMessage logMessage;
	private final LogManager logManager;
	private StorageManager storageManager;
	
	/**
	 * 
	 * @param message
	 */
	public LogWorker(LogMessage message) {
		this.logMessage = message;
		
		logManager = LogManager.getInstance();
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			log.error(e);
			return;
		}
	}
	
	public void run() {
		
		//first check to see that we don't already have this same message
		String messageId = logMessage.getMessageId();
		boolean exists = logManager.messageExists(messageId);
		if(exists){
			return;
		}
		
		String agentId = logMessage.getAgentId();
		
		if(agentId.equals(thisAgent)){   // this is a message from the local agent
			storageManager.writeLog(logMessage);
		}
		else {  //message from a remote agent
			handleRemoteMessage(logMessage);
		}
		
		//forward logs to neighbors		
		logForward(logMessage);
	}
	
	/**
	 * 
	 * @param logMessage
	 */
	protected void logForward(LogMessage logMessage){
		
		int j = logMessage.getJ();
		int k = logMessage.getK();
		
		//only forward to neighbors if j or k is greater than 0
		if(j > 0 || k > 0){
			log.info("sending to "+j+" j neighbors and "+k+" k neighbors");
			
			CommunicationManager cm = CommunicationManager.Factory.getInstance();
			cm.sendMessageToNeighbors(logMessage);
//			String[] neighbors = cm.getNeighbors();
//			
//			for(String neighbor : neighbors){
//				if(neighbor.equals(originalSender)){
//					continue;
//				}
//				
//				String address = cm.getNeighborAddress(neighbor);
//				cm.sendMessage(address, logMessage.toXml());
//			}
		}
	}
	
	/**
	 * 
	 * @param logMessage
	 */
	protected void handleRemoteMessage(LogMessage logMessage){
		
		if(jNum <= 0 && kNum <= 0){   //if this local node does not hold j or k number of messages
			log.debug("this node is not storing remote log messages.");
			return;
		}
		
		//decrement j's and k's
		if(logMessage.getJ() > 0){
			logMessage.setJ(logMessage.getJ()-1);
		}
		if(logMessage.getK() > 0){
			logMessage.setK(logMessage.getK()-1);
		}
		
		
		//count the number of messages we are holding for this agent
		String agentId = logMessage.getAgentId();
		int messageCount = logManager.getMessageCount(agentId);  
	
		
		int j = logMessage.getJ();
		int k = logMessage.getK();
		
		
		/**
		 * this block could be cleaned up since the two cases are basically the same
		 * but it is clear for now what is going on.
		 */
		if (jNum > 0 && j >= 0) { 			// this is a j-node for this message and therefore also a k-node
			if (messageCount == jNum) { 	// this should never be >
				logManager.removeLastMessage(agentId);
			}
			storageManager.writeLog(logMessage);
		} else if (kNum > 0 && k >= 0) { 	// if this node is just a k-node
			if (messageCount == kNum) {    
				logManager.removeLastMessage(agentId);
			}
			storageManager.writeLog(logMessage);
		}
		
	}
	
	public static void main(String args[]){

		CommunicationManager agent1 = CommunicationManager.Factory.getInstance(9999);
		agent1.setRemotePort(9998);
		new Thread(agent1).start();

		CommunicationManager agent2 = CommunicationManager.Factory.getInstance(9998);
		agent2.setRemotePort(9999);
		agent2.setAgentId("agent2");
		new Thread(agent2).start();
		
		/****************************************************************/
		LogMessage localMessage = new LogMessage("agent1", UUID.randomUUID().toString(), "name1", "value1", new Date());
		
		LogWorker localWorker = new LogWorker(localMessage);
		localWorker.logForward(localMessage);
		
//		LogMessage remoteMessage = new LogMessage("agent2", UUID.randomUUID().toString(), "name2", "value2", new Date());
//		remoteMessage.setJ(1);
//		remoteMessage.setK(1);
//		
//		LogWorker remoteWorker = new LogWorker(remoteMessage);
//		remoteWorker.logForward(remoteMessage);
		/****************************************************************/
		
		agent1.stop();
		agent2.stop();
	}

}
