package uk.ac.ed.inf.wimo.stix.agent.util;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uk.ac.ed.inf.wimo.stix.agent.communication.Message;
import uk.ac.ed.inf.wimo.stix.agent.communication.WorkflowMessage;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType;

public class TestRunMultipleWorkflowMessage {
	
	private static int MAX_WORKFLOW = 10;

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, InterruptedException, JAXBException {

		Random rand = new Random();
		long startMillisec = System.currentTimeMillis() + 600000;
		int i;
		for (i = 0; i < 300; i++) {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			String filename = "xml/MultipleMessageExample" + 
							 rand.nextInt(MAX_WORKFLOW) + 
							 ".xml";
			
			FileInputStream fis = new FileInputStream(filename);
			System.out.println(filename);
			
			Document doc = documentBuilder.parse(fis);

			XmlUtil.removeWhitespaceNodes(doc.getDocumentElement());

			WorkflowMessage message = (WorkflowMessage)Message.toObject(doc);
			message.setOriginator("192.168.10.101");
			message.setSender("192.168.10.102");
			

			InputSource iSource = new InputSource(new ByteArrayInputStream(message.getWorkflowSource().getBytes()));
			JAXBContext context = JAXBContext.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Workflow workflow = (Workflow) unmarshaller.unmarshal(iSource);
			MetadataComplexType meta = workflow.getMetadata();
			meta.setUUID(UUID.randomUUID().toString());
			if ((workflow.getAttributeSet() != null) && (workflow.getAttributeSet().getAttribute() != null)) {
				for (AttributeType attr: (List<AttributeType>) workflow.getAttributeSet().getAttribute()) {
					attr.setPersistent(false);
				}
			}
			PoolComplexType pool = workflow.getPool();
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(startMillisec);
			((StartTimerEventComplexType) pool.getStartTimerEvent().get(0))
				.setTimer(cal);
					
			
			Marshaller marshaller = context.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(workflow, sw);
			message.setWorkflowSource(sw.toString());
			
			String messageXml = message.toXml();

			Socket socket = new Socket("testbedgw.wimo.inf.ed.ac.uk", 8883);
			//Socket socket = new Socket("localhost", 9999);


			DataOutputStream bos = new DataOutputStream(socket.getOutputStream());
			bos.write(messageXml.getBytes());
			bos.close();
			
			//Thread.sleep(500);
		}

	}

}
