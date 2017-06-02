package uk.ac.ed.inf.wimo.stix.gui.backend.workflow;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Map;

/**
 *
 * @author alex
 */
public interface WorkflowStore {

    int createWorkflow(Workflow workflow) throws WorkflowStoreException;

    void deleteWorkflow(Integer id) throws WorkflowStoreException;

    Workflow getWorkflow(String name) throws WorkflowStoreException;

    Workflow getWorkflow(Integer id) throws WorkflowStoreException;

    String getWorkflowName(Integer id) throws WorkflowStoreException;

    void updateWorkflow(Integer id, Workflow workflow) throws WorkflowStoreException;

    Map<Integer, String> getWorkflows() throws WorkflowStoreException;

}
