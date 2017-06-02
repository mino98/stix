package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class StartTimerEventComplexTypeImpl extends uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.StartEventComplexTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType {
  private java.util.Calendar _timer;

  private long _period = -1l;


  public java.util.Calendar getTimer() {
    return _timer;
  }

  public void setTimer(java.util.Calendar pTimer) {
    _timer = pTimer;
  }

  public long getPeriod() {
    return _period;
  }

  public void setPeriod(long pPeriod) {
    _period = pPeriod;
  }

}
