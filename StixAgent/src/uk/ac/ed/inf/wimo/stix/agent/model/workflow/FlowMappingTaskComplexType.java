package uk.ac.ed.inf.wimo.stix.agent.model.workflow;

public interface FlowMappingTaskComplexType extends uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType {
  public interface TimeoutType {
    public int getDuration();
  
    public void setDuration(int pDuration);
  
    public java.lang.String getValue();
  
    public void setValue(java.lang.String pValue);
  
  }

  public java.lang.String getError();

  public void setError(java.lang.String pError);

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType getTimeout();

  public void setTimeout(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType pTimeout);

}
