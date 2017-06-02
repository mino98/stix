package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class MetadataComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public static class ValidityTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
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
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType) pObject;
      java.util.Calendar _2 = _1.getNotBefore();
      if (_2 != null) {
        pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "NotBefore", pController.getJMMarshaller().getDateTimeFormat().format(_1.getNotBefore()));
      }
      java.util.Calendar _3 = _1.getNotAfter();
      if (_3 != null) {
        pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "NotAfter", pController.getJMMarshaller().getDateTimeFormat().format(_1.getNotAfter()));
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType) pObject;
    java.lang.String _2 = _1.getName();
    if (_2 != null) {
      pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "Name", _1.getName());
    }
    java.lang.String _3 = _1.getAuthor();
    if (_3 != null) {
      pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "Author", _1.getAuthor());
    }
    java.lang.String _4 = _1.getUUID();
    if (_4 != null) {
      pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "UUID", _1.getUUID());
    }
    pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "Rev", pController.getDatatypeConverter().printInt(_1.getRev()));
    java.lang.String _5 = _1.getNotes();
    if (_5 != null) {
      pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "Notes", _1.getNotes());
    }
    pController.marshalSimpleChild(this, "http://www.wimo.inf.ed.ac.uk/stix", "Enabled", pController.getDatatypeConverter().printBoolean(_1.isEnabled()));
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType _6 = _1.getValidity();
    if (_6 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _7 = new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.MetadataComplexTypeDriver.ValidityTypeDriver();
      pController.marshal(_7, "http://www.wimo.inf.ed.ac.uk/stix", "Validity", _1.getValidity());
    }
  }

}
