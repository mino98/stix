package uk.ac.ed.inf.wimo.stix.agent.communication;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

public class LogQueryMessage extends Message {

	private Logger log = Logger.getLogger(LogQueryMessage.class);
	
	private final static String block1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.wimo.inf.ed.ac.uk/code/stix\" xsi:schemaLocation=\"http://www.wimo.inf.ed.ac.uk/code/stix Message.xsd\"><LogQuery>";
	private final static String block2 = "</LogQuery></Message>";
	
	private String query;
	private String results;
	private boolean isQuery;
	
	public LogQueryMessage() {
		super(LOG_QUERY_MESSAGE);
	}
	
	public LogQueryMessage(String query){
		this();
		this.query = query;
		isQuery = true;
	}
	
	public boolean isQuery() {
		return isQuery;
	}
	
	public void setQuery(String query) {
		isQuery = true;
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
	
	public String getResults() {
		return results;
	}
	
	public void setResults(String results) {
		isQuery = false;
		this.results = results;
	}
	
	@Override
	public void buildMessage(Node node) {
		Node queryNode = node.getFirstChild();
		
		if(queryNode.getNodeName().equals("query")){
			String query = queryNode.getTextContent();
			setQuery(query);
		}
		else if(queryNode.getNodeName().equals("results")){
			String results = queryNode.getTextContent();
			setResults(results);
		}
		else {
			log.error("Child node was not query or results node. Could not properly parse LogQueryMessage");
		}
	}

	@Override
	public String toXml() {
		if(isQuery){
			return block1 + "<query>"+query+"</query>"+block2;
		}
		else {
			return block1 + "<results>"+results+"</results>"+block2;
		}
	}

}
