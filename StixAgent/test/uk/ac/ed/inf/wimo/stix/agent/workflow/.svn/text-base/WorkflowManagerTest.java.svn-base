package uk.ac.ed.inf.wimo.stix.agent.workflow;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.w3c.dom.Document;

import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;
import uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil;

public class WorkflowManagerTest {

	private String simpleWorkflowExample = "xml/test/simpleworkflowexample.xml";
	@SuppressWarnings("unused")
	private String startEventExample = "xml/test/starteventsexample.xml";
	@SuppressWarnings("unused")
	private String messageEventExample = "xml/test/msgcatchingeventexample.xml";
	
	@Test
	public void testWorkflowFromXmlString() throws Exception {
		
		WorkflowManager workflowManager = WorkflowManager.getInstance();
		
		FileInputStream fis = new FileInputStream(simpleWorkflowExample);
		byte[] xmlBytes = new byte[fis.available()];
		fis.read(xmlBytes);
		
		String xml = new String(xmlBytes);
		
		Workflow workflow = workflowManager.workflowFromXML(xml);
		assertNotNull(workflow);
	}
	
	@Test
	public void testWorkflowFromDoc() throws Exception {
		fail("broken");
		
		WorkflowManager workflowManager = WorkflowManager.getInstance();
		
		InputStream is = new FileInputStream(simpleWorkflowExample);
		
		Document doc = XmlUtil.parse(is);
		
		Workflow workflow = workflowManager.workflowFromXML(doc.getFirstChild());
		assertNotNull(workflow);
	}
	
	@Test
	public void validate() throws Exception {
		
		WorkflowManager workflowManager = WorkflowManager.getInstance();
		
		InputStream is = new FileInputStream(simpleWorkflowExample);
		
		Document doc = XmlUtil.parse(is);
		
		boolean isValid = workflowManager.validate(doc);
	
		assertTrue(isValid);
	}

	
}
