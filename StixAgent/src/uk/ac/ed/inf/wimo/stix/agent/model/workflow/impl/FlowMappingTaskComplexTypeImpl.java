package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class FlowMappingTaskComplexTypeImpl extends uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowMappingComplexTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType {
  public static class TimeoutTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType {
    private int _duration;
  
    private java.lang.String _value;
  
  
    public int getDuration() {
      return _duration;
    }
  
    public void setDuration(int pDuration) {
      _duration = pDuration;
    }
  
    public java.lang.String getValue() {
      return _value;
    }
  
    public void setValue(java.lang.String pValue) {
      _value = pValue;
    }
  
  }

  private java.lang.String _error;

  private uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType _timeout;


  public java.lang.String getError() {
    return _error;
  }

  public void setError(java.lang.String pError) {
    _error = pError;
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType getTimeout() {
    return _timeout;
  }

  public void setTimeout(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType pTimeout) {
    _timeout = pTimeout;
  }

}
