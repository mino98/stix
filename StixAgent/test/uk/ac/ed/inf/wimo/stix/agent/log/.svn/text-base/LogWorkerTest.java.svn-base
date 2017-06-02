package uk.ac.ed.inf.wimo.stix.agent.log;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ed.inf.wimo.stix.agent.communication.CommunicationManager;
import uk.ac.ed.inf.wimo.stix.agent.communication.LogMessage;

public class LogWorkerTest {

	private static CommunicationManager agent1;
	private static CommunicationManager agent2;
	
	@BeforeClass
	public static void setUp() {
		agent1 = CommunicationManager.Factory.getInstance(9999);
		agent1.setRemotePort(9998);
		new Thread(agent1).start();
		
		agent2 = CommunicationManager.Factory.getInstance(9998);
		agent2.setRemotePort(9999);
		agent2.setAgentId("agent2");
		
		new Thread(agent2).start();
	}
	
	@AfterClass
	public static void shutdown(){
		agent1.stop();
		agent2.stop();
	}
	
	@Test
	public void testRun() {	
		fail("Not yet implemented");
	}

	@Test
	public void testLogForward() {
		
		LogMessage localMessage = new LogMessage("agent1", UUID.randomUUID().toString(), "name1", "value1", new Date());
		
		LogWorker localWorker = new LogWorker(localMessage);
		localWorker.logForward(localMessage);
		
		LogMessage remoteMessage = new LogMessage("agent2", UUID.randomUUID().toString(), "name2", "value2", new Date());
		remoteMessage.setJ(1);
		remoteMessage.setK(1);
		
		LogWorker remoteWorker = new LogWorker(remoteMessage);
		remoteWorker.logForward(remoteMessage);
	}

	@Test
	public void testHandleRemoteMessage() {
		fail("Not yet implemented");
	}

}
