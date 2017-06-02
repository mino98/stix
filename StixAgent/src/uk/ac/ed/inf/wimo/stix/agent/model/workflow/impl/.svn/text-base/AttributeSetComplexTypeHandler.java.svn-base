package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class AttributeSetComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  public static class AttributeTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Type
     *  2 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Name
     *  3 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Value
     * 
     */
    private int __state;
  
  
    public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
      if (pURI == null) {
        pURI = "";
      }
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType) result;
      if ("".equals(pURI)) {
        if ("Persistent".equals(pLocalName)) {
          _1.setPersistent(getHandler().getDatatypeConverter().parseBoolean((java.lang.String) pValue));
          return;
        }
      }
      super.addAttribute(pURI, pLocalName, pValue);
    }
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Type".equals(pLocalName)) {
            __state = 1;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Name".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 1:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Name".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 2:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Value".equals(pLocalName)) {
            __state = 3;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 3:
          break;
        default:
          throw new java.lang.IllegalStateException("Invalid state: " + __state);
      }
      return false;
    }
  
    public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType) result;
      switch (__state) {
        case 1:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Type".equals(pLocalName)) {
            _1.setType((java.lang.String) pResult);
            return;
          }
          break;
        case 2:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Name".equals(pLocalName)) {
            _1.setName((java.lang.String) pResult);
            return;
          }
          break;
        case 3:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Value".equals(pLocalName)) {
            _1.setValue((java.lang.String) pResult);
            return;
          }
          break;
        default:
          throw new java.lang.IllegalStateException("Illegal state: " + __state);
      }
    }
  
    public boolean isFinished() {
      switch (__state) {
        case 3:
        case 2:
          return true;
        default:
          return false;
      }
    }
  
  }

  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Attribute
   * 
   */
  private int __state;


  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
    switch (__state) {
      case 0:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Attribute".equals(pLocalName)) {
          __state = 1;
          java.lang.Object _2 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.AttributeSetComplexTypeImpl.AttributeTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.AttributeSetComplexTypeHandler.AttributeTypeHandler();
          _3.init(_1, _2, "http://www.wimo.inf.ed.ac.uk/stix", "Attribute", _1.getLevel());
          _3.setAttributes(pAttr);
          _1.addElementParser(_3);
          return true;
        }
        break;
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Attribute".equals(pLocalName)) {
          __state = 1;
          java.lang.Object _4 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.AttributeSetComplexTypeImpl.AttributeTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.AttributeSetComplexTypeHandler.AttributeTypeHandler();
          _5.init(_1, _4, "http://www.wimo.inf.ed.ac.uk/stix", "Attribute", _1.getLevel());
          _5.setAttributes(pAttr);
          _1.addElementParser(_5);
          return true;
        }
        break;
      default:
        throw new java.lang.IllegalStateException("Invalid state: " + __state);
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType) result;
    switch (__state) {
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Attribute".equals(pLocalName)) {
          _1.getAttribute().add(pResult);
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
      case 0:
        return true;
      default:
        return false;
    }
  }

}
