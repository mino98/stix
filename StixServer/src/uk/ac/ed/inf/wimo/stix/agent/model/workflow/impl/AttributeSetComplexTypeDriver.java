package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class AttributeSetComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public static class AttributeTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
    public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
      org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType _2 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType) pObject;
      _1.addAttribute("", "Persistent", pController.getAttrQName(this, "", "Persistent"), "CDATA", pController.getDatatypeConverter().printBoolean(_2.isPersistent()));
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
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType) pObject;
      java.lang.String _2 = _1.getType();
      if (_2 != null) {
        pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "Type", _1.getType());
      }
      java.lang.String _3 = _1.getName();
      if (_3 != null) {
        pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "Name", _1.getName());
      }
      java.lang.String _4 = _1.getValue();
      if (_4 != null) {
        pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "Value", _1.getValue());
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType) pObject;
    java.util.List _2 = _1.getAttribute();
    for (int _3 = 0;  _3 < (_2).size();  _3++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _4 = new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.AttributeSetComplexTypeDriver.AttributeTypeDriver();
      pController.marshal(_4, "http://www.wimo.inf.ed.ac.uk/stix", "Attribute", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType)_2.get(_3));
    }
  }

}
