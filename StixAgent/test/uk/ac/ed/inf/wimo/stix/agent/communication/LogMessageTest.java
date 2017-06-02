package uk.ac.ed.inf.wimo.stix.agent.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil;

public class LogMessageTest {

	private String logMessageFile = "xml/LogMessageExample.xml";
	
	@Test
	public void testBuildMessage() throws Exception {
		
		FileInputStream fis = new FileInputStream(logMessageFile);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.parse(fis);
		
		XmlUtil.removeWhitespaceNodes(doc.getDocumentElement());  //lolh4x
		
		Node logNode = doc.getElementsByTagName("Log").item(0);
		
		assertNotNull(logNode);
		
		assertEquals("Log", logNode.getNodeName());
		
		LogMessage message = new LogMessage();
		message.buildMessage(logNode);
		
		String messageType = message.getMessageType();
		String name = message.getName();
		Date timestamp = message.getTimestamp();
		String workflowId = message.getWorkflowId();
		String value = message.getValue();
		String agentId = message.getAgentId();
		int j = message.getJ();
		int k = message.getK();
		String messageId = message.getMessageId();
		
		assertEquals(Message.LOG_MESSAGE, messageType);
		assertEquals("TestWorkflow", name);
		assertEquals("2012-05-30T09:00:00", message.getDateFormat().format(timestamp));
		assertEquals("workflowId-1234", workflowId);
		assertEquals("This is a test log message", value);
		assertEquals(1, j);
		assertEquals(2, k);
		assertEquals("agent1", agentId);
		assertEquals("1a3b0c90-0c62-11df-8a39-0800200c9a66", messageId);
	}
	
	@Test
	public void testToXml(){
		
		LogMessage message = new LogMessage();
		
		message.setName("Name = name");
		message.setTimestamp(new Date());
		message.setValue("Value = value");
		message.setWorkflowId("147816238716231");
		message.setAgentId("agent1");
		
		String xml = message.toXml();
		System.out.println(xml);
	}

}
