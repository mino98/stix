package uk.ac.ed.inf.wimo.stix.agent.model.workflow;

public interface MessageCatchingEventComplexType extends uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowEventComplexType {
  public long getTimeout();

  public void setTimeout(long pTimeout);

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType getDataMapping();

  public void setDataMapping(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType pDataMapping);

}
