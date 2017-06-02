package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class ExclusiveGatewayEventComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  /** Will be set to true, if the first child is parsed.
   * It is an error, if another child is parsed, and the
   * fields value is true.
   * 
   */
  private boolean __state;

  /** Index of the particle being currently parsed
   * 
   */
  private int __childNum;


  public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
    if (pURI == null) {
      pURI = "";
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType) result;
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
    org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
    if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "FlowMapping".equals(pLocalName)) {
      if (__state) {
        if (__childNum != 0) {
          getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "Multiple different particles present in a choive group.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
        } else {
          getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}FlowMapping has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
        }
      }
      __state = true;
      __childNum = 0;
      org.apache.ws.jaxme.JMManager _2 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingConditionComplexType.class);
      java.lang.Object _3 = _2.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _4 = _2.getHandler();
      _4.init(_1, _3, "http://www.wimo.inf.ed.ac.uk/stix", "FlowMapping", _1.getLevel());
      _4.setAttributes(pAttr);
      _1.addElementParser(_4);
      return true;
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType) result;
    switch (__childNum) {
      case 0:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "FlowMapping".equals(pLocalName)) {
          _1.setFlowMapping(((uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingConditionComplexType) pResult));
          return;
        }
        break;
      default:
        throw new java.lang.IllegalStateException("Illegal state: " + __childNum);
    }
  }

  public boolean isFinished() {
    return __state;
  }

}
