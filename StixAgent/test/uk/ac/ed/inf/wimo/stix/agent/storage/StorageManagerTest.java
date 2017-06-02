package uk.ac.ed.inf.wimo.stix.agent.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import uk.ac.ed.inf.wimo.stix.agent.communication.LogMessage;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;
import uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil;

public class StorageManagerTest {
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		emptyDatabase();
		
		/* insert some dummy data for read tests.
		   there is a much more professional way of doing this with
		   DbUnit but it would take to long */
		
		storageManager.writeDeviceVariable("device_var1", "value1");
		storageManager.writeDeviceVariable("device_var2", "value2");
		
		storageManager.writeWorkflowVariable("1", "workflow_var1", "value1");
		
		FileInputStream fis = new FileInputStream("xml/test/starteventsexample.xml");
		byte[] xmlBytes = new byte[fis.available()];
		fis.read(xmlBytes);
		fis.close();
		
		String xml = new String(xmlBytes);
		
		Workflow workflow = XmlUtil.workflowFromXML(xml);	
		storageManager.writeWorkflowXml(workflow);
	}
	
	public static void emptyDatabase(){
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		storageManager.query("DELETE FROM device_var");
		storageManager.query("DELETE FROM workflow_var");
		storageManager.query("DELETE FROM workflow");
		storageManager.query("DELETE FROM log");
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		emptyDatabase();
	}
	
	@Test
	public void testGetInstance() throws Exception {
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		assertNotNull(storageManager);
	}

	@Test
	public void testQuery() throws Exception {
		
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		String query = "select * from device_var";
		
		ResultSet results = storageManager.query(query);
		System.out.println("result size: "+results.getFetchSize());
	}
	
	@Test
	public void testWriteLog() throws Exception {
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		FileInputStream fis = new FileInputStream("xml/LogMessageExample.xml");
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.parse(fis);
		
		XmlUtil.removeWhitespaceNodes(doc.getDocumentElement());  //lolh4x
		
		Node logNode = doc.getElementsByTagName("Log").item(0);
		
		assertNotNull(logNode);
		
		assertEquals("Log", logNode.getNodeName());
	
		LogMessage message = new LogMessage();
		message.buildMessage(logNode);
		
		storageManager.writeLog(message);
	}
	
	@Test
	public void testWriteWorkflowXml() throws Exception {
		
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		FileInputStream fis = new FileInputStream("xml/test/simpleworkflowexample.xml");
		byte[] xmlBytes = new byte[fis.available()];
		fis.read(xmlBytes);
		fis.close();
		
		String xml = new String(xmlBytes);
		
		Workflow workflow = XmlUtil.workflowFromXML(xml);	
		storageManager.writeWorkflowXml(workflow);
	}

	@Test
	public void testReadWorkflowXml() {
		
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		Workflow[] workflowz = storageManager.readWorkflowXml();
		
		Workflow workflow = workflowz[0];
		assertNotNull(workflow);
	}

	@Test
	public void testReadWorkflowVariable() {
		
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		String var = storageManager.readWorkflowVariable("1", "workflow_var1");
		assertEquals("value1", var);
	}

	@Test
	public void testWriteWorkflowVariable() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadDeviceVariable() {
		
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		String name = "device_var1";
		
		String value = storageManager.readDeviceVariable(name);
		
		assertEquals("value1", value);
	}

	@Test
	public void testTryRevisionUpdate() throws Exception {
		
		fail("not implemented yet.");
		
		@SuppressWarnings("unused")
		StorageManager storageManager = null;
		
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	
		byte[] workflowBytes = null;
		
		try {
			FileInputStream fis = new FileInputStream("xml/test/starteventsexample.xml");
		
			workflowBytes = new byte[fis.available()];
			fis.read(workflowBytes);
			
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
	}
	
	@Test
	public void testWriteDeviceVariable() {
		fail("Not yet implemented");
	}

}
