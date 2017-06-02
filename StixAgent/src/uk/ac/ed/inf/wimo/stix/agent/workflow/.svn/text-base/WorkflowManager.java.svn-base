package uk.ac.ed.inf.wimo.stix.agent.workflow;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uk.ac.ed.inf.wimo.stix.agent.communication.CommunicationManager;
import uk.ac.ed.inf.wimo.stix.agent.communication.WorkflowMessage;
import uk.ac.ed.inf.wimo.stix.agent.device.DeviceManager;
import uk.ac.ed.inf.wimo.stix.agent.engine.Engine;
import uk.ac.ed.inf.wimo.stix.agent.engine.WorkflowException;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;
import uk.ac.ed.inf.wimo.stix.agent.storage.StorageManager;
import uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil;

/**
 * The WorkflowManager receives new workflows from the CommunicationManager
 * and determines if they are relevant for the local connected devices. If so,
 * it stores them to disk and passes them to the Engine for execution.
 * 
 * @author matt
 *
 */
public class WorkflowManager {

	public static final String WORKFLOW_SCHEMA = "xml/workflow/npmn.xsd";
	
	private static Logger log = Logger.getLogger(WorkflowManager.class);
	private static WorkflowManager instance;
	private WorkflowManager() {
		
	    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

	    Source schemaFile = new StreamSource(new File(WORKFLOW_SCHEMA));
	    
	    try {
			factory.newSchema(schemaFile);
		} catch (SAXException e1) {
			log.error(e1);
		}
	}
	
	public synchronized static WorkflowManager getInstance(){
		if(instance == null){
			instance = new WorkflowManager();
		}
		return instance;
	}
	
	/**
	 * Should only be called by the framework on startup to initialize workflows in the database
	 */
	public void bootstrap() throws IOException, WorkflowException {
		
		log.debug("loading existing workflows from database");
		
		StorageManager sm = StorageManager.getInstance();
		
		Engine engine = Engine.getInstance();		
		
		Workflow[] workflows = sm.readWorkflowXml();
		log.debug("loading "+workflows.length+" workflows.");
		
		for(Workflow workflow : workflows){
			engine.registerNewWorkflow(workflow);
		}
	}
	
	/**
	 * Processes a received workflow. Steps:
	 * Perform some simple validation:
	 * 1. check the XML against the XML Schema
	 * 2. if the number of start_event is != 1, throw error ad abort.
	 * 3. if the number of end_event is == 0, throw error ad abort.
	 * 4. check that the workflows have no null outgoing links.
	 * 5. (... other checks to be done TBD ...)
	 * 7. Forwarding to the neighbours:
	 * Check if the workflow is relevant:
	 * 8. analyses the query syntax to check whether it’s relevant for the current agent:
	 * 	- determine local devices pg 20
	 * 9. forward to the Workflow to interpret. if not: discard the workflow and terminate
	 * 
	 * @param workflowXML The XML representation of the workflow to be processed
	 */
	public void receiveWorkflow(WorkflowMessage workflowMessage) {
		
		String xml = workflowMessage.getWorkflowSource();
		
		/*****************************************************/
		
		/* validation here */
//		try {
//			Document doc = XmlUtil.parse(is);
//			
//			boolean isValid = validate(doc);
//		
//			if(!isValid){
//				//should we throw an exception here?
//				log.error("workflow schema was not valid");
//				return;
//			}
//			
//		} catch (SAXException e) {
//			log.error(e);
//		    return;
//		} catch (IOException e) {
//			log.error(e);
//			return;
//		}	
		
		/******************************************************/
		
		Workflow workflow = workflowFromXML(xml);
		
		MetadataComplexType metadata = workflow.getMetadata();
		
		
		/*******************************************************/
		

		StorageManager sm = null;
		
		try {
			sm = StorageManager.getInstance();
			boolean isNewWorkflow = sm.tryRevisionUpdate(workflow);  //adds or updates workflow depending on revision number
		
			if(!isNewWorkflow){
				log.info("received workflow is either a duplicate or has lower revision than an existing workflow");
				return;
			}
		} catch (IOException ex){
			log.error(ex);
			log.error("Problem instantiating StorageManager, exiting..");
			return;
		}

		
//		/* this block handles the logic of checking for a workflow with the same uuid */
//		try {
//			ResultSet result = sm.query("SELECT uuid, rev FROM workflow WHERE uuid='"+uuid+"");
//			
//			if(result.getFetchSize() == 1){  	// if we got a hit
//				int existingRev = result.getInt("rev");
//				
//				if(rev <= existingRev){ //drop if new workflow revision is the same or less than the existing one
//					return;
//				}
//				else if(rev > existingRev){
//					//remove the existing row from the database
//					sm.query("DELETE FROM workflow WHERE uuid='"+uuid+"'");
//				}				
//			}
//			//uniqueness constraint on uuid enforces the > 1 case.
//			//else there is 0 which is fine. do nothing
//			
//			//now insert the new one
//			sm.writeWorkflowXml(workflowXML);
//		}
//		catch(SQLException ex){
//			log.error(ex);
//			log.error("caught a SQL exception checking uuid and rev in workflow db. exiting.");
//			return;
//		}
		
		/***********************************************************/
		
		PoolComplexType pool = workflow.getPool();
		
		//check start events
		List<?> startConditionEvent = pool.getStartConditionEvent();
		List<?> startMessageEvent = pool.getStartMessageEvent();
		List<?> startTimerEvent = pool.getStartTimerEvent();
		
		int numStartEvents = startConditionEvent.size() + startMessageEvent.size() + startTimerEvent.size();
		
		//there needs to be exactly one
		//if((startConditionEvent.size() == 0) && (startMessageEvent.size() == 0) && (startTimerEvent.size() == 0)){
		if(numStartEvents != 1){
			//throw error??
			log.error("No start event was found while processing workflow uuid:"+metadata.getUUID());
			return;
		}
		
		
		//check end events
		List<?> endPlainEvents = pool.getEndPlainEvent();
		List<?> terminateEvents = pool.getTerminateEvent();
		
		int numEndEvents = endPlainEvents.size() + terminateEvents.size();
		
		//if there are no end events
		if(numEndEvents == 0){
			log.error("No end event was found while processing workflow uuid:"+metadata.getUUID());
			return;
		}
		
		//check for no null outgoing links


		log.debug("forwarding workflow to neighbors");
		
		//forward to neighbors
		CommunicationManager cm = CommunicationManager.Factory.getInstance();
		
		//WorkflowMessage message = new WorkflowMessage();
		//message.setWorkflowSource(xml);
		
		cm.sendMessageToNeighbors(workflowMessage);
		
//		CommunicationManager cm = CommunicationManager.Factory.getInstance();
//		String[] neighbors = cm.getNeighbors();
//		
//		for(String neighbor : neighbors){
//			String address = cm.getNeighborAddress(neighbor);
//			
//			
//			
//			WorkflowMessage message = new WorkflowMessage();
//			message.setWorkflowSource(xml);
//			
//			cm.sendMessage(address, message.toXml());
//		}
		
		
		Engine engine = Engine.getInstance();
		try {
			log.debug("registering new workflow with engine");
			
			engine.registerNewWorkflow(workflow);
		} catch (WorkflowException e) {
			// TODO we need to decide what to do here
			log.error(e);
		}
	}
	
	/**
	 * 
	 * @param xml
	 * @return
	 */
	protected Workflow workflowFromXML(String xml){
		
		InputSource iSource = new InputSource(new ByteArrayInputStream(xml.getBytes()));
		Workflow workflow = null;
		
		try {
			JAXBContext context = JAXBContext.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
			Unmarshaller unmarshaller = context.createUnmarshaller();		

			workflow = (Workflow) unmarshaller.unmarshal(iSource);
			//StreamSource source = new StreamSource(new StringReader(xml));
			//JAXBElement<Workflow> root = unmarshaller.unmarshal(source, Workflow.class);
			//workflow = root.getValue();
		} catch(JAXBException ex){
			log.error(ex);
		} 
		
		return workflow;		
	}
	
	/**
	 * 
	 * @param doc
	 * @return
	 */
	protected boolean validate(Document doc){
		return XmlUtil.validate(doc, WORKFLOW_SCHEMA);
	}
	
	//doesn't work
	protected Workflow workflowFromXML(Node docNode){
		
		Workflow workflow = null;
		
		try {
			JAXBContext context = JAXBContext.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
			Unmarshaller unmarshaller = context.createUnmarshaller();		
			
			workflow = (Workflow) unmarshaller.unmarshal(docNode);
		} catch(JAXBException ex){
			log.error(ex);
		} 
		
		return workflow;
	}
}
