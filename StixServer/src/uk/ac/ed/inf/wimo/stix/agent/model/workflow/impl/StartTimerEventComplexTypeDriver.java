package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class StartTimerEventComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
    org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType _2 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType) pObject;
    java.lang.String _3 = _2.getId();
    if (_3 != null) {
      _1.addAttribute("", "id", pController.getAttrQName(this, "", "id"), "CDATA", _2.getId());
    }
    _1.addAttribute("", "x", pController.getAttrQName(this, "", "x"), "CDATA", pController.getDatatypeConverter().printInt(_2.getX()));
    _1.addAttribute("", "y", pController.getAttrQName(this, "", "y"), "CDATA", pController.getDatatypeConverter().printInt(_2.getY()));
    java.util.Calendar _4 = _2.getTimer();
    if (_4 != null) {
      _1.addAttribute("", "timer", pController.getAttrQName(this, "", "timer"), "CDATA", pController.getJMMarshaller().getDateTimeFormat().format(_2.getTimer()));
    }
    _1.addAttribute("", "period", pController.getAttrQName(this, "", "period"), "CDATA", pController.getDatatypeConverter().printLong(_2.getPeriod()));
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType) pObject;
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType _2 = _1.getFlowMapping();
    if (_2 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _3 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType.class).getDriver();
      pController.marshal(_3, "http://www.wimo.inf.ed.ac.uk/stix", "FlowMapping", _1.getFlowMapping());
    }
  }

}
