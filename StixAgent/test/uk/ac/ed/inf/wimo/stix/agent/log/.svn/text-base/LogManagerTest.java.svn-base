package uk.ac.ed.inf.wimo.stix.agent.log;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import uk.ac.ed.inf.wimo.stix.agent.Agent;
import uk.ac.ed.inf.wimo.stix.agent.communication.LogMessage;
import uk.ac.ed.inf.wimo.stix.agent.storage.StorageManager;

public class LogManagerTest {
	
	private int j;
	private int k;
	private String agentId;
	
	public LogManagerTest() {
		j = Agent.getJ();
		k = Agent.getK();
		Agent.getJNum();
		Agent.getKNum();
		agentId = Agent.getAgentId();
	}
	
	@Test
	public void testGetMessageCount() throws Exception {
		
		StorageManager sm = StorageManager.getInstance();
		sm.query("DELETE FROM log");
		
		LogManager logManager = LogManager.getInstance();
		
		LogMessage logMessage1 = new LogMessage();
		logMessage1.setAgentId(agentId);
		logMessage1.setJ(j);
		logMessage1.setK(k);
		logMessage1.setName("name");
		logMessage1.setValue("value");
		logMessage1.setTimestamp(new Date());
		logMessage1.setWorkflowId("8115e220-0c56-11df-8a39-0800200c9a66");
		
		logManager.writeLog(logMessage1);
		
		int count = logManager.getMessageCount("agent1");
		assertEquals(1, count);
		
		LogMessage logMessage2 = new LogMessage();
		logMessage2.setAgentId(agentId);
		logMessage2.setJ(j);
		logMessage2.setK(k);
		logMessage2.setName("name1");
		logMessage2.setValue("value2");
		logMessage2.setTimestamp(new Date());
		logMessage2.setWorkflowId("b6299230-0c57-11df-8a39-0800200c9a66");
		
		logManager.writeLog(logMessage2);
		
		count = logManager.getMessageCount("agent1");
		assertEquals(2, count);
		
		sm.query("DELETE FROM log");
	}
	
	@Test
	public void removeLastMessage() throws Exception {
		
		StorageManager sm = StorageManager.getInstance();
		sm.query("DELETE FROM log");
		
		LogManager logManager = LogManager.getInstance();
		
		//insert 5
		for(int i=0; i<5; i++){
			LogMessage logMessage = new LogMessage(agentId, UUID.randomUUID().toString(), "name"+i, "value"+i, new Date());
			logManager.writeLog(logMessage);
		}
		
		//remove 1
		logManager.removeLastMessage(agentId);
	
		//assert there are 4
		int messageCount = logManager.getMessageCount(agentId);
		assertEquals(4, messageCount);
		
		//assert that it is the correct one
		
		sm.query("DELETE FROM log");
	}
	
	@Test
	public void messageExists() throws Exception {
		fail("not implemented");
	}
	

}
