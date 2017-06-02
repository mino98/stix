package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class FlowMappingTaskComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public static class TimeoutTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
    public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
      org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType _2 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType) pObject;
      _1.addAttribute("", "duration", pController.getAttrQName(this, "", "duration"), "CDATA", pController.getDatatypeConverter().printInt(_2.getDuration()));
      return _1;
    }
  
    public java.lang.String getPreferredPrefix(java.lang.String pURI) {
      return null;
    }
  
    public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType) pObject;
      java.lang.String _2 = _1.getValue();
      if (_2 != null  &&  _2.length() > 0) {
        char[] _3 = _2.toCharArray();
        pHandler.characters(_3, 0, _3.length);
      }
    }
  
  }

  public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
    org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
    return _1;
  }

  public java.lang.String getPreferredPrefix(java.lang.String pURI) {
    if (pURI == null) {
      pURI = "";
    }
    if (pURI.equals("http://www.wimo.inf.ed.ac.uk/stix")) {
      return "stix";
    }
    return null;
  }

  public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType) pObject;
    java.lang.String _2 = _1.getOut();
    if (_2 != null) {
      pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "out", _1.getOut());
    }
    java.lang.String _3 = _1.getError();
    if (_3 != null) {
      pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "error", _1.getError());
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType _4 = _1.getTimeout();
    if (_4 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _5 = new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowMappingTaskComplexTypeDriver.TimeoutTypeDriver();
      pController.marshal(_5, "http://www.wimo.inf.ed.ac.uk/stix", "timeout", _1.getTimeout());
    }
  }

}
