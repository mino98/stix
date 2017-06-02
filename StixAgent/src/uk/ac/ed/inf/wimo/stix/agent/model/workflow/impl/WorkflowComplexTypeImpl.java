package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class WorkflowComplexTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.WorkflowComplexType {
  private uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType _metadata;

  private uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType _attributeSet;

  private uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType _pool;


  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType getMetadata() {
    return _metadata;
  }

  public void setMetadata(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType pMetadata) {
    _metadata = pMetadata;
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType getAttributeSet() {
    return _attributeSet;
  }

  public void setAttributeSet(uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType pAttributeSet) {
    _attributeSet = pAttributeSet;
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType getPool() {
    return _pool;
  }

  public void setPool(uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType pPool) {
    _pool = pPool;
  }

}
