package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class WorkflowComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.WorkflowComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.WorkflowComplexType) pObject;
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType _2 = _1.getMetadata();
    if (_2 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _3 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.class).getDriver();
      pController.marshal(_3, "http://www.wimo.inf.ed.ac.uk/stix", "Metadata", _1.getMetadata());
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType _4 = _1.getAttributeSet();
    if (_4 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _5 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.class).getDriver();
      pController.marshal(_5, "http://www.wimo.inf.ed.ac.uk/stix", "AttributeSet", _1.getAttributeSet());
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType _6 = _1.getPool();
    if (_6 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _7 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType.class).getDriver();
      pController.marshal(_7, "http://www.wimo.inf.ed.ac.uk/stix", "Pool", _1.getPool());
    }
  }

}
