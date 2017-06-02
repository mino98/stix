package uk.ac.ed.inf.wimo.stix.agent.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil;

public class LogQueryMessageTest {
	
	private String logQueryMessageFile = "xml/LogQueryMessageExample.xml";
	
	@Test
	public void testBuildMessage() throws Exception {
		
		FileInputStream fis = new FileInputStream(logQueryMessageFile);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.parse(fis);
		
		XmlUtil.removeWhitespaceNodes(doc.getDocumentElement());  //lolh4x
		
		Node logQueryNode = doc.getElementsByTagName("LogQuery").item(0);
		
		assertNotNull(logQueryNode);
		
		assertEquals("LogQuery", logQueryNode.getNodeName());
		
		LogQueryMessage logQuery = new LogQueryMessage();
		logQuery.buildMessage(logQueryNode);
		
		assertEquals(Message.LOG_QUERY_MESSAGE, logQuery.getMessageType());
		
		assertEquals("SELECT * FROM log", logQuery.getQuery());
	}
	
	@Test
	public void testToXml(){
		
		LogQueryMessage logQuery = new LogQueryMessage("SELECT * FROM log");
		String xml = logQuery.toXml();
		System.out.println(xml);
	}
	
}
