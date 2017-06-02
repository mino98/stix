package uk.ac.ed.inf.wimo.stix.agent.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;

/**
 * 
 * @author matt
 *
 */
public class XmlUtil {
	
	private static Logger log = Logger.getLogger(XmlUtil.class);
	
	/**
	 * Code taken from http://forums.java.net/jive/thread.jspa?messageID=345459
	 * to fix horrible Java bug http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6564400
	 * 
	 * @param e
	 */
	public static void removeWhitespaceNodes(Element e) {
		NodeList children = e.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--) {
			Node child = children.item(i);
			if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
				e.removeChild(child);
			} else if (child instanceof Element) {
				removeWhitespaceNodes((Element) child);
			}
		}
	}
	
	/**
	 * 
	 * @param doc
	 * @return
	 */
	public static boolean validate(Document doc, String schemaPath){
		
		// create a SchemaFactory capable of understanding WXS schemas
	    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

	    //TODO this should be done once at initialization
	    //TODO this will break outside of eclipse
	    Source schemaFile = new StreamSource(new File(schemaPath));
	    
	    Schema schema = null;
		try {
			schema = factory.newSchema(schemaFile);
		} catch (SAXException e1) {
			log.error(e1);
			return false;
		}

	    // create a Validator instance, which can be used to validate an instance document
	    Validator validator = schema.newValidator();

	    try {
			validator.validate(new DOMSource(doc));
		} catch (SAXException e) {
			log.error(e);
			return false;
		} catch (IOException e) {
			log.error(e);
			return false;
		}
		
		return true;

	}
	
	
	/**
	 * 
	 * @param is
	 */
	public static Document parse(InputStream is) throws SAXException, IOException {
			
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			
			DocumentBuilder docBuilder = factory.newDocumentBuilder();		
			
			Document doc = docBuilder.parse(is);
			
			removeWhitespaceNodes(doc.getDocumentElement());
			
			return doc;
		} catch (ParserConfigurationException e) {
			log.error(e);
		} 
		
		return null;
	}	
	
	/**
	 * 
	 * @param doc
	 * @return
	 */
	public static String docToString(Document doc){
		
		/* wow. look at all this crap just to get a string */
		
		//TODO this can probably live at the class level
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(doc);
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		String xmlString = result.getWriter().toString();
		
		return xmlString;
	}
	
	public static Workflow workflowFromXML(String xml) {
		
		InputSource iSource = new InputSource(new ByteArrayInputStream(xml.getBytes()));
		Workflow workflow = null;
		
		try {
			JAXBContext context = JAXBContext.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
			Unmarshaller unmarshaller = context.createUnmarshaller();		
			
			workflow = (Workflow) unmarshaller.unmarshal(iSource);
		} catch(JAXBException ex){
			ex.printStackTrace();
		} 
		
		return workflow;		
	}
	
	/**
	 * http://blogs.sun.com/teera/entry/jaxb_for_simple_java_xml
	 * @param workflow
	 * @return
	 */
	public static String workflowToString(Workflow workflow){
		try {
			
			JAXBContext context = JAXBContext.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
			Marshaller marshaller = context.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(workflow, sw); 
			
			return sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
