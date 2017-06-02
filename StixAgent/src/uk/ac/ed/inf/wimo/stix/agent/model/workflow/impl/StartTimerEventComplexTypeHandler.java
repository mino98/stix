package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class StartTimerEventComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType) result;
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
      } else if ("timer".equals(pLocalName)) {
        try {
          java.lang.String _4 = (java.lang.String) pValue;
          java.text.ParsePosition _5 = new java.text.ParsePosition(0);
          java.lang.Object _6 = ((org.apache.ws.jaxme.impl.JMUnmarshallerImpl) getHandler().getJMUnmarshaller()).getDateTimeFormat().parseObject(_4, _5);
          if (_6 == null) {
            throw new java.lang.IllegalArgumentException("Failed to parse dateTime " + _4 + " at: " + _4.substring(_5.getErrorIndex()));
          }
          java.util.Calendar _7;
          if (_6 instanceof java.util.Calendar) {
            _7 = (java.util.Calendar) _6;
          } else {
            _7 = java.util.Calendar.getInstance();
            _7.setTime((java.util.Date) _6);
          }
          _1.setTimer(_7);
        } catch (java.lang.Exception _8) {
          getHandler().parseConversionEvent("Failed to convert value of @timer: " + pValue, _8);
        }
        return;
      } else if ("period".equals(pLocalName)) {
        try {
          _1.setPeriod(getHandler().getDatatypeConverter().parseLong((java.lang.String) pValue));
        } catch (java.lang.Exception _9) {
          getHandler().parseConversionEvent("Failed to convert value of @period: " + pValue, _9);
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType) result;
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
