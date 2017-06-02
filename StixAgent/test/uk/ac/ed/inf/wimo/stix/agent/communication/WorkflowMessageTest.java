package uk.ac.ed.inf.wimo.stix.agent.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class WorkflowMessageTest {

	private String workflowMessageFile = "xml/WorkflowMessageExample.xml";
	
	@Test
	public void testBuildMessage() throws Exception {
		
		FileInputStream fis = new FileInputStream(workflowMessageFile);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.parse(fis);
		
		Node workflowNode = doc.getElementsByTagName("Workflow").item(0);
		
		assertNotNull(workflowNode);
		
		assertEquals("Workflow", workflowNode.getNodeName());
		
		WorkflowMessage message = new WorkflowMessage();
		message.buildMessage(workflowNode);
		
		String workflowSource = message.getWorkflowSource();
		
		System.out.println(workflowSource);
		//assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Workflow>stuff and other stuff</Workflow>", workflowSource);
		
	}

	@Test
	public void testToXml(){
		
		WorkflowMessage message = new WorkflowMessage();
		message.setWorkflowSource("<workflow>work work work work</workflow>");
		
		String xml = message.toXml();
		System.out.println(xml);
	}
	
}
