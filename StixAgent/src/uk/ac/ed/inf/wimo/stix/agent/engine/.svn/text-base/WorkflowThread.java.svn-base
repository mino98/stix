package uk.ac.ed.inf.wimo.stix.agent.engine;

import java.util.Date;
import java.util.Hashtable;

import uk.ac.ed.inf.wimo.stix.agent.device.Device;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;

public class WorkflowThread extends Thread {
	
	private Hashtable<String, String> localAttributes = new Hashtable<String, String>();
	private Workflow workflow;
	private Device device;


	public WorkflowThread(Workflow workflow, Device device, String info) {
		super(new Date().toString() + ", " + workflow.getMetadata().getUUID() +
				": " + info);
		this.workflow = workflow;
		this.device = device;
	}

	
	public Hashtable<String, String> getLocalAttributes() {
		return localAttributes;
	}


	public Device getDevice() {
		return device;
	}


	/**
	 * Sets a workflow-run attribute.
	 * @param name The name of the attribute
	 * @param value The value of the attribute
	 */
	public void setLocalAttribute(String name, String value) {
		localAttributes.put(name, value);
	}
	
	
	/**
	 * Gets a workflow-run attribute.
	 * @param name The name of the attribute
	 * @return The value of the attribute
	 */
	public String getLocalAttribute(String name) {
		return localAttributes.get(name);
	}
	
	
	/**
	 * Gets the associated workflow.
	 * @return The workflow associated with this workflow thread
	 */
	public Workflow getWorkflow() {
		return workflow;
	}
	
}
