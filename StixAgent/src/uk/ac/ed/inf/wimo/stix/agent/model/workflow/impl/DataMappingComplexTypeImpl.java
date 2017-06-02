package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class DataMappingComplexTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType {
  private uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType _in;

  private uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType _out;


  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType getIn() {
    return _in;
  }

  public void setIn(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType pIn) {
    _in = pIn;
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType getOut() {
    return _out;
  }

  public void setOut(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType pOut) {
    _out = pOut;
  }

}
