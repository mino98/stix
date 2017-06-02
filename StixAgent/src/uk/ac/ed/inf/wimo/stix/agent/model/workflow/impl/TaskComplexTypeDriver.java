package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class TaskComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
    org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType _2 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType) pObject;
    java.lang.String _3 = _2.getId();
    if (_3 != null) {
      _1.addAttribute("", "id", pController.getAttrQName(this, "", "id"), "CDATA", _2.getId());
    }
    _1.addAttribute("", "x", pController.getAttrQName(this, "", "x"), "CDATA", pController.getDatatypeConverter().printInt(_2.getX()));
    _1.addAttribute("", "y", pController.getAttrQName(this, "", "y"), "CDATA", pController.getDatatypeConverter().printInt(_2.getY()));
    java.lang.String _4 = _2.getClazz();
    if (_4 != null) {
      _1.addAttribute("", "Clazz", pController.getAttrQName(this, "", "Clazz"), "CDATA", _2.getClazz());
    }
    java.lang.String _5 = _2.getURL();
    if (_5 != null) {
      _1.addAttribute("", "URL", pController.getAttrQName(this, "", "URL"), "CDATA", _2.getURL());
    }
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType) pObject;
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType _2 = _1.getFlowMapping();
    if (_2 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _3 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.class).getDriver();
      pController.marshal(_3, "http://www.wimo.inf.ed.ac.uk/stix", "FlowMapping", _1.getFlowMapping());
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType _4 = _1.getDataMapping();
    if (_4 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _5 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType.class).getDriver();
      pController.marshal(_5, "http://www.wimo.inf.ed.ac.uk/stix", "DataMapping", _1.getDataMapping());
    }
  }

}
