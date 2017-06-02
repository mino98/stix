package uk.ac.ed.inf.wimo.stix.agent.util;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import uk.ac.ed.inf.wimo.stix.agent.communication.Message;
import uk.ac.ed.inf.wimo.stix.agent.communication.WorkflowMessage;

public class TestSendMessageWorkflowMessage {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, InterruptedException {
		
		// Send receive messagre workflow
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		FileInputStream fis = new FileInputStream("xml/ReceiveMessageWorkflowMessageExample.xml");
		Document doc = documentBuilder.parse(fis);
		XmlUtil.removeWhitespaceNodes(doc.getDocumentElement());
		WorkflowMessage message = (WorkflowMessage)Message.toObject(doc);
		message.setOriginator("192.168.10.101");
		message.setSender("192.168.10.102");
		String messageXml = message.toXml();
		Socket socket = new Socket("testbedgw.wimo.inf.ed.ac.uk", 9995);
		DataOutputStream bos = new DataOutputStream(socket.getOutputStream());
		bos.write(messageXml.getBytes());
		bos.close();
		
		Thread.sleep(2000);
		
		// Send send message workflow
		builderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = builderFactory.newDocumentBuilder();
		fis = new FileInputStream("xml/SendMessageWorkflowMessageExample.xml");
		doc = documentBuilder.parse(fis);
		XmlUtil.removeWhitespaceNodes(doc.getDocumentElement());
		message = (WorkflowMessage)Message.toObject(doc);
		message.setOriginator("192.168.10.101");
		message.setSender("192.168.10.102");
		messageXml = message.toXml();
		socket = new Socket("testbedgw.wimo.inf.ed.ac.uk", 9995);
		bos = new DataOutputStream(socket.getOutputStream());
		bos.write(messageXml.getBytes());
		bos.close();

	}

}
