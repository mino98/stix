package uk.ac.ed.inf.wimo.stix.agent.engine;

import uk.ac.ed.inf.wimo.stix.agent.model.workflow.EventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.WorkflowComplexType;

@SuppressWarnings("serial")
public class WorkflowException extends Exception {

	String workflowId;

	public String getWorkflowId() {
		return workflowId;
	}

	public WorkflowException(WorkflowComplexType workflow, EventComplexType event, String msg) {
		super("Workflow " + (workflow!=null?workflow.getMetadata().getUUID():null) +
				", " + (event!=null?event.getClass().getName():null) +
				(event!=null?event.getId():null) +
				": " + msg);
		workflowId = workflow!=null?workflow.getMetadata().getUUID():null;
	}
	
}
