package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class ParameterComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  public class ValueChoiceAttributeHandler extends org.apache.ws.jaxme.impl.JMSAXGroupParser {
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
      return uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.ParameterComplexTypeHandler.this.getHandler();
    }
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Value".equals(pLocalName)) {
        if (__state) {
          if (__childNum != 0) {
            getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "Multiple different particles present in a choive group.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
          } else {
            getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}Value has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
          }
        }
        __state = true;
        __childNum = 0;
        _1.addSimpleAtomicState();
        return true;
      } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Attribute".equals(pLocalName)) {
        if (__state) {
          if (__childNum != 1) {
            getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "Multiple different particles present in a choive group.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
          } else {
            getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}Attribute has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_CHOICE_GROUP_REUSE, null);
          }
        }
        __state = true;
        __childNum = 1;
        _1.addSimpleAtomicState();
        return true;
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType) result;
      switch (__childNum) {
        case 0:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Value".equals(pLocalName)) {
            _1.setValue((java.lang.String) pResult);
            return;
          }
          break;
        case 1:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Attribute".equals(pLocalName)) {
            _1.setAttribute((java.lang.String) pResult);
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

  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Name
   *  2 = While parsing the nested group ValueChoiceAttribute
   * 
   */
  private int __state;


  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
    switch (__state) {
      case 0:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Name".equals(pLocalName)) {
          __state = 1;
          _1.addSimpleAtomicState();
          return true;
        }
        break;
      case 1:
        if (_1.testGroupParser(new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.ParameterComplexTypeHandler.ValueChoiceAttributeHandler(), pNamespaceURI, pLocalName, pQName, pAttr)) {
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType) result;
    switch (__state) {
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Name".equals(pLocalName)) {
          _1.setName((java.lang.String) pResult);
          return;
        }
        break;
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
