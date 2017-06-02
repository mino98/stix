package uk.ac.ed.inf.wimo.stix.agent.model.xml;

/**
 * XML representation of the workflow
 * 
 * @author damon
 *
 */
public class WorkflowXML {
	
	private String id;
	private String xml;
	
	public WorkflowXML(String id, String xml){
		this.id = id;
		this.xml = xml;
	}
	
	public WorkflowXML() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	
}
