package uk.ac.ed.inf.wimo.stix.agent.communication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uk.ac.ed.inf.wimo.stix.agent.Agent;


/**
 * 
 * @author matt
 *
 */
public class LogMessage extends Message {
	
	/* uhg */
	private static final String xmlBlock1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.wimo.inf.ed.ac.uk/code/stix\" xsi:schemaLocation=\"http://www.wimo.inf.ed.ac.uk/code/stix Message.xsd\"><Log>";
	private static final String xmlBlock2 = "</Log></Message>";
	
	private static Logger log = Logger.getLogger(LogMessage.class);
	
	//2012-05-30T09:00:00
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	protected String agentId;
	protected String workflowId;
	protected String name;
	protected String value;
	protected Date timestamp;
	
	protected String messageId = UUID.randomUUID().toString();
	protected int j = Agent.getJ();
	protected int k = Agent.getK();
	
	public LogMessage(String agentId, String workflowId, String name, String value, Date timestamp){
		this();
		
		this.agentId = agentId;
		this.workflowId = workflowId;
		this.name = name;
		this.value = value;
		this.timestamp = timestamp;
	}
	
	public LogMessage() {
		super(LOG_MESSAGE);
		
		String address = CommunicationManager.getCurrentEnvironmentNetworkIp();
		sender = address;
		originator = address;
	}
	
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	
	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	@Override
	public void buildMessage(Node thisNode) {
		
		NodeList nodeList = thisNode.getChildNodes();
		
		for(int i=0; i<nodeList.getLength(); i++){
			Node node = nodeList.item(i);
			String nodeName = node.getNodeName();
			
			if(nodeName.equals("WorkflowID")){
				setWorkflowId(node.getTextContent());
			} else if(nodeName.equals("Name")){
				setName(node.getTextContent());
			} else if(nodeName.equals("Value")){
				setValue(node.getTextContent());
			} else if(nodeName.equals("MessageID")){
				setMessageId(node.getTextContent());
			} else if(nodeName.equals("J")){
				setJ(Integer.parseInt(node.getTextContent()));
			} else if(nodeName.equals("K")){
				setK(Integer.parseInt(node.getTextContent()));
			} else if(nodeName.equals("AgentID")){
				setAgentId(node.getTextContent());
			} else if(nodeName.equals("Originator")){
				setOriginator(node.getTextContent());
			} else if(nodeName.equals("Sender")){
				setSender(node.getTextContent());
			}
			else { 	//Timestamp
				String dateString = node.getTextContent();
				
				try {
					Date date = dateFormat.parse(dateString);
					setTimestamp(date);
				} catch (ParseException e) {
					log.error(e);
				}
				
			}
		}
		
	}
	
	@Override
	public String toXml() {
		StringBuilder str = new StringBuilder(xmlBlock1);
		
		str.append(getAgentIdXml());
		str.append(getNameXml());
		str.append(getTimestampXml());
		str.append(getWorkflowIdXml());
		str.append(getValueXml());
		str.append(getMessageIdXml());
		str.append(getJXml());
		str.append(getKXml());
		str.append(getOriginatorXML());
		str.append(getSenderXML());
		str.append(xmlBlock2);
		
		return str.toString();
	}
	
	private String getAgentIdXml(){
		return "<AgentID>"+agentId+"</AgentID>";
	}
	
	private String getNameXml(){
		return "<Name>"+name+"</Name>";
	}

	private String getTimestampXml(){
		return "<Timestamp>"+dateFormat.format(timestamp)+"</Timestamp>";
	}
	
	private String getWorkflowIdXml(){
		return "<WorkflowID>"+workflowId+"</WorkflowID>";
	}
	
	private String getValueXml(){
		return "<Value>"+value+"</Value>";
	}
	
	private String getMessageIdXml(){
		return "<MessageID>"+messageId+"</MessageID>";
	}
	
	private String getJXml(){
		return "<J>"+j+"</J>";
	}
	
	private String getKXml(){
		return "<K>"+k+"</K>";
	}
	
	private String getOriginatorXML(){
		return "<Originator>"+originator+"</Originator>";
	}
	
	private String getSenderXML(){
		return "<Sender>"+sender+"</Sender>";
	}
	
}
