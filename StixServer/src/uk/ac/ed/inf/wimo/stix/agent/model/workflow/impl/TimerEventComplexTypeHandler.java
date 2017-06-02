package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class TimerEventComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}FlowMapping
   * 
   */
  private int __state;


  public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
    if (pURI == null) {
      pURI = "";
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType) result;
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
      } else if ("duration".equals(pLocalName)) {
        try {
          _1.setDuration(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
        } catch (java.lang.Exception _4) {
          getHandler().parseConversionEvent("Failed to convert value of @duration: " + pValue, _4);
        }
        return;
      }
    }
    super.addAttribute(pURI, pLocalName, pValue);
  }

  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
    switch (__state) {
      case 0:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "FlowMapping".equals(pLocalName)) {
          __state = 1;
          org.apache.ws.jaxme.JMManager _2 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType.class);
          java.lang.Object _3 = _2.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _4 = _2.getHandler();
          _4.init(_1, _3, "http://www.wimo.inf.ed.ac.uk/stix", "FlowMapping", _1.getLevel());
          _4.setAttributes(pAttr);
          _1.addElementParser(_4);
          return true;
        }
        break;
      case 1:
        break;
      default:
        throw new java.lang.IllegalStateException("Invalid state: " + __state);
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType) result;
    switch (__state) {
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "FlowMapping".equals(pLocalName)) {
          _1.setFlowMapping(((uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType) pResult));
          return;
        }
        break;
      default:
        throw new java.lang.IllegalStateException("Illegal state: " + __state);
    }
  }

  public boolean isFinished() {
    switch (__state) {
      case 1:
        return true;
      default:
        return false;
    }
  }

}
