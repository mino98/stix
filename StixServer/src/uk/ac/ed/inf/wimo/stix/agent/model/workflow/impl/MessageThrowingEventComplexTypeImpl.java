package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class MessageThrowingEventComplexTypeImpl extends uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowEventComplexTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType {
  private uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType _dataMapping;


  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType getDataMapping() {
    return _dataMapping;
  }

  public void setDataMapping(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType pDataMapping) {
    _dataMapping = pDataMapping;
  }

}
