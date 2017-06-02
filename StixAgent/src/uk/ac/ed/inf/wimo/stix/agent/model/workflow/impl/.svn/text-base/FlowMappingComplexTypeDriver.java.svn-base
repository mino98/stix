package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class FlowMappingComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType) pObject;
    java.lang.String _2 = _1.getOut();
    if (_2 != null) {
      pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "out", _1.getOut());
    }
  }

}
