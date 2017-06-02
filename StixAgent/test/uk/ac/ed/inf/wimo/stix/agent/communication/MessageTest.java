package uk.ac.ed.inf.wimo.stix.agent.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;

/**
 * 
 * @author matt
 *
 */
public class MessageTest {
	
	private String logMessageFile = "xml/LogMessageExample.xml";
//	private String eventMessageFile = "xml/EventMessageExample.xml";
//	private String workflowMessageFile = "xml/WorkflowMessageExample.xml";
	
	//@Test
	public void toXml(){
		
	}
	
	@Test
	public void toObject() throws Exception {
		FileInputStream fis = new FileInputStream(logMessageFile);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.parse(fis);
		
		Message message = Message.toObject(doc);
		
		assertNotNull(message);
		
		assertEquals("log", message.getMessageType());		
	}
	
}
