package uk.ac.ed.inf.wimo.stix.agent.communication;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author matt
 *
 */
public class EventMessage extends Message {

	/* don't look at me. i am hideous. */
	private static final String xmlBlock1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.wimo.inf.ed.ac.uk/code/stix\" xsi:schemaLocation=\"http://www.wimo.inf.ed.ac.uk/code/stix Message.xsd\"><Event>";
	private static final String xmlBlock2 = "</Event></Message>";
	/*                             */
	
	protected String sourceId;
	protected String name;
	protected String content;
	
	public EventMessage() {
		super(EVENT_MESSAGE);
	}
	
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void buildMessage(Node thisNode) {
		NodeList nodeList = thisNode.getChildNodes();
		
		for(int i=0; i<nodeList.getLength(); i++){
			Node node = nodeList.item(i);
			String nodeName = node.getNodeName();
			
			if(nodeName.equals("SourceID")){
				setSourceId(node.getTextContent());
			} else if(nodeName.equals("Name")){
				setName(node.getTextContent());
			} else if(nodeName.equals("Content")){
				setContent(node.getTextContent());
			} 
		}

	}
	
	@Override
	public String toXml() {
		StringBuilder str = new StringBuilder(xmlBlock1);
		
		str.append(getNameXml());
		str.append(getSourceIdXml());
		str.append(getContentXml());
		str.append(xmlBlock2);
		
		return str.toString();
	}
	
	private String getNameXml(){
		return "<Name>"+name+"</Name>";
	}
	
	private String getSourceIdXml(){
		return "<SourceID>"+sourceId+"</SourceID>";
	}
	
	private String getContentXml(){
		return "<Content>"+content+"</Content>";
	}
}
