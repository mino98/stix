package uk.ac.ed.inf.wimo.stix.agent.communication;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 
 * @author matt
 *
 */
public abstract class Message {
	
	private final static Logger log = Logger.getLogger(Message.class);
	
	public final static String LOG_MESSAGE="Log";
	public final static String EVENT_MESSAGE="Event";
	public final static String WORKFLOW_MESSAGE="Workflow";
	public final static String LOG_QUERY_MESSAGE = "LogQuery";
	
	protected String sender;
	protected String originator;
	
	protected String messageType;
	
	public Message(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}
	
	public String getOriginator() {
		return originator;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getSender() {
		return sender;
	}
	
	public abstract void buildMessage(Node node);
	
	/**
	 * 
	 * @param doc
	 * @return
	 */
	public static Message toObject(Document doc){
		
		Message message = null;
		
		Node messageNode = doc.getFirstChild();
		
		Node messageTypeNode = messageNode.getFirstChild();
		String messageType = messageTypeNode.getNodeName();
		
		log.info("parsing message type: "+messageType);
		
		if(messageType.equals(LOG_MESSAGE)){
			message = new LogMessage();
		} else if(messageType.equals(LOG_QUERY_MESSAGE)){
			message = new LogQueryMessage();
		} else if(messageType.equals(EVENT_MESSAGE)){
			message = new EventMessage();
		} else if(messageType.equals(WORKFLOW_MESSAGE)) {
			message = new WorkflowMessage();
		} else {
			return null;
		}
		
		message.buildMessage(messageTypeNode);  
		
		return message;
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	public abstract String toXml();
	
}
