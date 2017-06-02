package uk.ac.ed.inf.wimo.stix.agent.communication;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author matt
 *
 */
public class WorkflowMessage extends Message {

	protected static final String xmlBlock1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.wimo.inf.ed.ac.uk/code/stix\" xsi:schemaLocation=\"http://www.wimo.inf.ed.ac.uk/code/stix Message.xsd\"><Workflow>";
	protected static final String xmlBlock2 = "</Workflow></Message>";
	
	protected String workflowSource;  //
	
	public WorkflowMessage() {
		super(WORKFLOW_MESSAGE);
		
		String address = CommunicationManager.getCurrentEnvironmentNetworkIp();
		sender = address;
		originator = address;
	}
	
	public String getWorkflowSource() {
		return workflowSource;
	}

	public void setWorkflowSource(String workflowSource) {
		this.workflowSource = workflowSource;
	}

	@Override
	public void buildMessage(Node thisNode) {
		
		NodeList nodeList = thisNode.getChildNodes();
		for(int i=0; i<nodeList.getLength(); i++){
			
			Node node = nodeList.item(i);
			String nodeName = node.getNodeName();
			
			if(nodeName.equals("WorkflowSource")){
				String textContent = node.getTextContent();
				String noCDATA = textContent.replace("<![CDATA[", "").replace("]]>", "");
				setWorkflowSource(noCDATA);
			} else if(nodeName.equals("Originator")){
				setOriginator(node.getTextContent());
			} else if(nodeName.equals("Sender")){
				setSender(node.getTextContent());
			}
		}
		
	}

	@Override
	public String toXml() {
		return xmlBlock1 + getWorkflowSourceXML() + getOriginatorXML() + getSenderXML() + xmlBlock2;
	}
	
	private String getWorkflowSourceXML(){
		return "<WorkflowSource><![CDATA["+workflowSource+"]]></WorkflowSource>";
	}
	
	private String getOriginatorXML(){
		return "<Originator>"+originator+"</Originator>";
	}
	
	private String getSenderXML(){
		return "<Sender>"+sender+"</Sender>";
	}
}
