package uk.ac.ed.inf.wimo.stix.agent.model.workflow;

public interface StartTimerEventComplexType extends uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartEventComplexType {
  public java.util.Calendar getTimer();

  public void setTimer(java.util.Calendar pTimer);

  public long getPeriod();

  public void setPeriod(long pPeriod);

}
