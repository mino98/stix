package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class EndPlainEventComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
    if (pURI == null) {
      pURI = "";
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType) result;
    if ("".equals(pURI)) {
      if ("id".equals(pLocalName)) {
        _1.setId((java.lang.String) pValue);
        return;
      } else if ("x".equals(pLocalName)) {
        try {
          _1.setX(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
        } catch (java.lang.Exception _2) {
          getHandler().parseConversionEvent("Failed to convert value of @x: " + pValue, _2);
        }
        return;
      } else if ("y".equals(pLocalName)) {
        try {
          _1.setY(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
        } catch (java.lang.Exception _3) {
          getHandler().parseConversionEvent("Failed to convert value of @y: " + pValue, _3);
        }
        return;
      }
    }
    super.addAttribute(pURI, pLocalName, pValue);
  }

  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
  }

  public boolean isFinished() {
    return true;
  }

  public boolean isEmpty() {
    return true;
  }

  public boolean isAtomic() {
    return false;
  }

}
