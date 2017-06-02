package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class MessageCatchingEventComplexTypeImpl extends uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowEventComplexTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType {
  private long _timeout = -1l;

  private uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType _dataMapping;


  public long getTimeout() {
    return _timeout;
  }

  public void setTimeout(long pTimeout) {
    _timeout = pTimeout;
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType getDataMapping() {
    return _dataMapping;
  }

  public void setDataMapping(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType pDataMapping) {
    _dataMapping = pDataMapping;
  }

}
