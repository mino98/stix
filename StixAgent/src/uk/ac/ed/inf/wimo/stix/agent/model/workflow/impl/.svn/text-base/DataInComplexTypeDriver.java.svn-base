package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class DataInComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType) pObject;
    java.util.List _2 = _1.getParameter();
    for (int _3 = 0;  _3 < (_2).size();  _3++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _4 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType.class).getDriver();
      pController.marshal(_4, "http://www.wimo.inf.ed.ac.uk/stix", "parameter", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType)_2.get(_3));
    }
  }

}
