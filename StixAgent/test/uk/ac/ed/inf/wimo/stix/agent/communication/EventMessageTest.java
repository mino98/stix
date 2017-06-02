package uk.ac.ed.inf.wimo.stix.agent.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class EventMessageTest {

	private String eventMessageFile = "xml/EventMessageExample.xml";
	
	@Test
	public void testBuildMessage() throws Exception {
		
		FileInputStream fis = new FileInputStream(eventMessageFile);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.parse(fis);
		
		Node eventNode = doc.getElementsByTagName("Event").item(0);
		
		assertNotNull(eventNode);
		
		assertEquals("Event", eventNode.getNodeName());
		
		
		EventMessage message = new EventMessage();
		message.buildMessage(eventNode);
		
		String content = message.getContent();
		String name = message.getName();
		String sourceId = message.getSourceId();
		
		assertEquals("here is some content", content);
		assertEquals("New Node ID", name);
		assertEquals("blah blah", sourceId);
		
	}
	
	@Test
	public void testToXml(){
		
		EventMessage message = new EventMessage();
		
		message.setContent("This is Content");
		message.setName("Name is Matt");
		message.setSourceId("127.0.0.1");
		
		String xml = message.toXml();
		
		System.out.println(xml);
	}

}
