package uk.ac.ed.inf.wimo.stix.agent.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;

import uk.ac.ed.inf.wimo.stix.agent.storage.StorageManager;
import uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil;

/**
 * Tests two nodes passing messages using the CommunicationManager
 * 
 * @author matt
 *
 */
public class CommunicationManagerTest {
	
	
	@Test
	public void stop() throws Exception {
		
		CommunicationManager endPoint1 = CommunicationManager.Factory.getInstance();
		
		Thread thread = new Thread(endPoint1);
		thread.start();
		
		Thread.sleep(200);   //wait for the timeout
		
		endPoint1.stop();    //technically a race condition here
		
		Thread.sleep(200);   //wait for the timeout
		
		assertFalse(endPoint1.isRunning());  //assert that we have stopped the server
		
		assertFalse(thread.isAlive());       //assert that the server thread is no longer running
	}	
	
	@Test
	public void delegateMessage() throws Exception {
		FileInputStream fis = new FileInputStream("xml/LogMessageExample.xml");
		
		CommunicationManager cm = CommunicationManager.Factory.getInstance();
		Document doc = XmlUtil.parse(fis);   
		
		Message message = Message.toObject(doc);
		
		assertNotNull(message);
		assertEquals(Message.LOG_MESSAGE, message.getMessageType());
		
		cm.delegateMessage(message, null);
	}
	
	@Test
	public void testGetAgentId() throws Exception {
		
		CommunicationManager cm = CommunicationManager.Factory.getInstance();
		
		String agentId = cm.getAgentId();
		assertEquals("agent1", agentId);
		
		cm.setAgentId("agent2");
		agentId = cm.getAgentId();
		assertEquals("agent2", agentId);
		
		cm.setAgentId("agent1");
	}
	
	@Test
	public void testGetNeighbors() throws Exception {
		
		CommunicationManager cm = CommunicationManager.Factory.getInstance();
		
		assertEquals("agent1", cm.getAgentId());
		
		String[] neighbors = cm.getNeighbors();
		assertNotNull(neighbors);
		
//		String n1 = neighbors[0];
//		String n2 = neighbors[1];
//		
//		assertEquals("agent2", n1);
//		assertEquals("agent3", n2);
	}

	@Test
	public void testGetNeighborAddress() throws Exception {
	
		CommunicationManager cm = CommunicationManager.Factory.getInstance();
		
		assertEquals("agent1", cm.getAgentId());
		
//		String[] neighbors = cm.getNeighbors();
		
//		String n1 = neighbors[0];
//		String n2 = neighbors[1];
//		
//		assertEquals("agent2", n1);
//		assertEquals("agent3", n2);
//		
//		String address1 = cm.getNeighborAddress(n1);
//		String address2 = cm.getNeighborAddress(n2);
//		
//		assertEquals("127.0.0.1", address1);
//		assertEquals("", address2);	
	}
	
	@Test
	public void sendMessage() throws Exception {
	
		CommunicationManager sender = CommunicationManager.Factory.getInstance(9998);
		sender.setRemotePort(9999);
		
		CommunicationManager receiver = CommunicationManager.Factory.getInstance(9999);
		receiver.setRemotePort(9998);
		
		Thread t1 = new Thread(sender);
		Thread t2 = new Thread(receiver);
		
		t1.start();
		t2.start();
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		
		{
			// LogMessage
			FileInputStream fis = new FileInputStream("xml/LogMessageExample.xml");
			Document doc = documentBuilder.parse(fis);
			
			LogMessage message = (LogMessage)Message.toObject(doc);
			
			sender.sendMessage("localhost", message);
			//TODO check that receiver got it
		}

		{
			// EventMessage
			FileInputStream fis = new FileInputStream("xml/EventMessageExample.xml");
			Document doc = documentBuilder.parse(fis);

			EventMessage message = (EventMessage)Message.toObject(doc);
			
			sender.sendMessage("localhost", message);
		}

		
		{
			// LogQueryMessage
			FileInputStream fis = new FileInputStream("xml/LogQueryMessageExample.xml");
			Document doc = documentBuilder.parse(fis);
			
			LogQueryMessage message = (LogQueryMessage)Message.toObject(doc);
			
			sender.sendMessage("localhost", message);
		}
		
		{
			//WorkflowMessage
			FileInputStream fis = new FileInputStream("xml/WorkflowMessageExample.xml");
			Document doc = documentBuilder.parse(fis);
			//fis.close();
			
			WorkflowMessage message = (WorkflowMessage)Message.toObject(doc);
			
			//String xmlString = XmlUtil.docToString(doc);
			sender.sendMessage("localhost", message);
		}
		
		//clean up the database
		StorageManager storageManager = StorageManager.getInstance();
		
		storageManager.query("DELETE FROM device_var");
		storageManager.query("DELETE FROM workflow_var");
		storageManager.query("DELETE FROM workflow");
		storageManager.query("DELETE FROM log");
	}
	
	@Test
	public void testGetLocalIp(){
		String ip = CommunicationManager.getCurrentEnvironmentNetworkIp();
		System.out.println(ip);
	}
	
}
