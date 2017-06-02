package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class DataMappingComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType) pObject;
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType _2 = _1.getIn();
    if (_2 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _3 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType.class).getDriver();
      pController.marshal(_3, "http://www.wimo.inf.ed.ac.uk/stix", "in", _1.getIn());
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType _4 = _1.getOut();
    if (_4 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _5 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType.class).getDriver();
      pController.marshal(_5, "http://www.wimo.inf.ed.ac.uk/stix", "out", _1.getOut());
    }
  }

}
