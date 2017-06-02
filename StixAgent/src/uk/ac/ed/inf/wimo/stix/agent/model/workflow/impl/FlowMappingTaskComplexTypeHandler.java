package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class FlowMappingTaskComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  public class OutHandler extends org.apache.ws.jaxme.impl.JMSAXGroupParser {
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}out
     * 
     */
    private int __state;
  
  
    public org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl getHandler() {
      return uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowMappingTaskComplexTypeHandler.this.getHandler();
    }
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "out".equals(pLocalName)) {
            __state = 1;
            _1.addSimpleAtomicState();
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
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType) result;
      switch (__state) {
        case 1:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "out".equals(pLocalName)) {
            _1.setOut((java.lang.String) pResult);
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

  public class ErrorChoiceTimeoutHandler extends org.apache.ws.jaxme.impl.JMSAXGroupParser {
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
  
  
    public org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl getHandler() {
      return uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowMappingTaskComplexTypeHandler.this.getHandler();
    }
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "error".equals(pLocalName)) {
        if (__state) {
          if (__childNum != 0) {
            getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "Multiple different particles present in a choive group.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
          } else {
            getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}error has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
          }
        }
        __state = true;
        __childNum = 0;
        _1.addSimpleAtomicState();
        return true;
      } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "timeout".equals(pLocalName)) {
        if (__state) {
          if (__childNum != 1) {
            getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "Multiple different particles present in a choive group.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
          } else {
            getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}timeout has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
          }
        }
        __state = true;
        __childNum = 1;
        java.lang.Object _2 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowMappingTaskComplexTypeImpl.TimeoutTypeImpl();
        org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowMappingTaskComplexTypeHandler.TimeoutTypeHandler();
        _3.init(_1, _2, "http://www.wimo.inf.ed.ac.uk/stix", "timeout", _1.getLevel());
        _3.setAttributes(pAttr);
        _1.addElementParser(_3);
        return true;
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType) result;
      switch (__childNum) {
        case 0:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "error".equals(pLocalName)) {
            _1.setError((java.lang.String) pResult);
            return;
          }
          break;
        case 1:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "timeout".equals(pLocalName)) {
            _1.setTimeout(((uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType) pResult));
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

  public static class TimeoutTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
      if (pURI == null) {
        pURI = "";
      }
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType) result;
      if ("".equals(pURI)) {
        if ("duration".equals(pLocalName)) {
          try {
            _1.setDuration(getHandler().getDatatypeConverter().parseInt((java.lang.String) pValue));
          } catch (java.lang.Exception _2) {
            getHandler().parseConversionEvent("Failed to convert value of @duration: " + pValue, _2);
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
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType) result;
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

  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While parsing the nested group Out
   *  2 = While parsing the nested group ErrorChoiceTimeout
   * 
   */
  private int __state;


  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
    switch (__state) {
      case 0:
        if (_1.testGroupParser(new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowMappingTaskComplexTypeHandler.OutHandler(), pNamespaceURI, pLocalName, pQName, pAttr)) {
          __state = 1;
          return true;
        }
        break;
      case 1:
        if (_1.testGroupParser(new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowMappingTaskComplexTypeHandler.ErrorChoiceTimeoutHandler(), pNamespaceURI, pLocalName, pQName, pAttr)) {
          __state = 2;
          return true;
        }
        break;
      case 2:
        break;
      default:
        throw new java.lang.IllegalStateException("Invalid state: " + __state);
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType) result;
    switch (__state) {
      case 1:
        throw new java.lang.IllegalStateException("This case should be handled by a nested group parser.");
      case 2:
        throw new java.lang.IllegalStateException("This case should be handled by a nested group parser.");
      default:
        throw new java.lang.IllegalStateException("Illegal state: " + __state);
    }
  }

  public boolean isFinished() {
    switch (__state) {
      case 2:
        return true;
      default:
        return false;
    }
  }

}
