package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class FlowOutConditionComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
    if (pURI == null) {
      pURI = "";
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType) result;
    if ("".equals(pURI)) {
      if ("condition".equals(pLocalName)) {
        _1.setCondition((java.lang.String) pValue);
        return;
      }
    }
    super.addAttribute(pURI, pLocalName, pValue);
  }

  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType) result;
    _1.setValue((java.lang.String) pResult);
  }

  public boolean isFinished() {
    return true;
  }

  public boolean isEmpty() {
    return false;
  }

  public boolean isAtomic() {
    return true;
  }

}
