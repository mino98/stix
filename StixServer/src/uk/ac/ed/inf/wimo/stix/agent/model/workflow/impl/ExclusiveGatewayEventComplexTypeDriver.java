package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class ExclusiveGatewayEventComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
    org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType _2 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType) pObject;
    java.lang.String _3 = _2.getId();
    if (_3 != null) {
      _1.addAttribute("", "id", pController.getAttrQName(this, "", "id"), "CDATA", _2.getId());
    }
    _1.addAttribute("", "x", pController.getAttrQName(this, "", "x"), "CDATA", pController.getDatatypeConverter().printInt(_2.getX()));
    _1.addAttribute("", "y", pController.getAttrQName(this, "", "y"), "CDATA", pController.getDatatypeConverter().printInt(_2.getY()));
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType) pObject;
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingConditionComplexType _2 = _1.getFlowMapping();
    if (_2 != null) {
      org.apache.ws.jaxme.impl.JMSAXDriver _3 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingConditionComplexType.class).getDriver();
      pController.marshal(_3, "http://www.wimo.inf.ed.ac.uk/stix", "FlowMapping", _1.getFlowMapping());
    }
  }

}
