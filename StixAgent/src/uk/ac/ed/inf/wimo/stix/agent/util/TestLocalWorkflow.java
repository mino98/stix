package uk.ac.ed.inf.wimo.stix.agent.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.ws.jaxme.impl.JAXBContextImpl;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uk.ac.ed.inf.wimo.stix.agent.communication.WorkflowMessage;
import uk.ac.ed.inf.wimo.stix.agent.engine.WorkflowException;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;

public class TestLocalWorkflow {

	private static final Logger log = Logger.getLogger(TestLocalWorkflow.class);
	
	/**
	 * @param args
	 * @throws WorkflowException 
	 * @throws InterruptedException 
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, WorkflowException, InterruptedException, JAXBException {
		WorkflowMessage workflowMessage = new WorkflowMessage();
		workflowMessage.setWorkflowSource(readXmlFile(args[0]));
		
		/* Unmarshall workflow */
		InputSource iSource = new InputSource(new StringReader(workflowMessage.getWorkflowSource()));
		JAXBContext context = JAXBContextImpl.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		
		/*return workflow;
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		log.debug(args[0]);
		FileInputStream fis = new FileInputStream(args[0]);
		Document doc = documentBuilder.parse(fis);
		XmlUtil.removeWhitespaceNodes(doc.getDocumentElement());
		String xmlString = XmlUtil.docToString(doc);
		InputSource iSource = new InputSource(xmlString);
		log.debug(xmlString);
		
		Workflow workflow = null;
		
		JAXBContext context = JAXBContext.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
		Unmarshaller unmarshaller = context.createUnmarshaller();
			
		workflow = (Workflow) unmarshaller.unmarshal(iSource);
		
		// XmlUtil.workflowFromXML(xmlString);*/
		
		
	}


	private static String readXmlFile(String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		StringBuffer stringBuffer = new StringBuffer();
		String str;
		while ((str = bufferedReader.readLine()) != null) {
			stringBuffer.append(str);
		}
		bufferedReader.close();
		
		return stringBuffer.toString();
	}
	
	private static  Workflow unMarshallWorkflow(String xmlFileName) throws IOException, JAXBException {
		WorkflowMessage workflowMessage = new WorkflowMessage();
		workflowMessage.setWorkflowSource(readXmlFile(xmlFileName));
		
		/* Unmarshall workflow */
		InputSource iSource = new InputSource(new ByteArrayInputStream(workflowMessage.getWorkflowSource().getBytes()));
		JAXBContext context = JAXBContext.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Workflow workflow = (Workflow) unmarshaller.unmarshal(iSource);
		return workflow;
	}
	
}
