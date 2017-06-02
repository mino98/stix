package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class FlowOutConditionComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
    org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType _2 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType) pObject;
    java.lang.String _3 = _2.getCondition();
    if (_3 != null) {
      _1.addAttribute("", "condition", pController.getAttrQName(this, "", "condition"), "CDATA", _2.getCondition());
    }
    return _1;
  }

  public java.lang.String getPreferredPrefix(java.lang.String pURI) {
    return null;
  }

  public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType) pObject;
    java.lang.String _2 = _1.getValue();
    if (_2 != null  &&  _2.length() > 0) {
      char[] _3 = _2.toCharArray();
      pHandler.characters(_3, 0, _3.length);
    }
  }

}
