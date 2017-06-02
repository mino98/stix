package uk.ac.ed.inf.wimo.stix.agent.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;

import org.junit.Test;
import org.w3c.dom.Document;

import uk.ac.ed.inf.wimo.stix.agent.communication.CommunicationManager;


public class XmlUtilTest {
	
	@Test
	public void parse() throws Exception {
		
		FileInputStream fis = new FileInputStream("xml/LogMessageExample.xml");
		
		@SuppressWarnings("unused")
		CommunicationManager endPoint1 = CommunicationManager.Factory.getInstance();
		Document doc = XmlUtil.parse(fis);
		
		fis.close();
		
		assertNotNull(doc);
	}
	
	@Test
	public void validate() throws Exception {
		fail("Not yet implemented");
		
		FileInputStream fis = new FileInputStream("xml/LogMessageExample.xml");
		
		@SuppressWarnings("unused")
		CommunicationManager endPoint1 = CommunicationManager.Factory.getInstance();
		Document doc = XmlUtil.parse(fis);
		
		fis.close();
		
		assertTrue(XmlUtil.validate(doc, "xml/Message.xsd"));
	}
	
	@Test
	public void docToString() throws Exception {
		
		@SuppressWarnings("unused")
		CommunicationManager cm = CommunicationManager.Factory.getInstance();
		
		FileInputStream fis = new FileInputStream("xml/LogMessageExample.xml");
		Document doc = XmlUtil.parse(fis);
		
		String xmlString = XmlUtil.docToString(doc);
		
		assertNotNull(xmlString);
	}
	
}
