package uk.ac.ed.inf.wimo.stix.agent.model.workflow;

public interface WorkflowComplexType {
  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType getMetadata();

  public void setMetadata(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType pMetadata);

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType getAttributeSet();

  public void setAttributeSet(uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType pAttributeSet);

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType getPool();

  public void setPool(uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType pPool);

}
