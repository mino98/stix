package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class MetadataComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  public static class ValidityTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
    /** The current state. The following values are valid states:
     *  0 = Before parsing the element
     *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}NotBefore
     *  2 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}NotAfter
     * 
     */
    private int __state;
  
  
    public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
      org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
      switch (__state) {
        case 0:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "NotBefore".equals(pLocalName)) {
            __state = 1;
            _1.addSimpleAtomicState();
            return true;
          } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "NotAfter".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
            return true;
          }
          break;
        case 1:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "NotAfter".equals(pLocalName)) {
            __state = 2;
            _1.addSimpleAtomicState();
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
      uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType) result;
      switch (__state) {
        case 1:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "NotBefore".equals(pLocalName)) {
            try {
              java.lang.String _2 = (java.lang.String) pResult;
              java.text.ParsePosition _3 = new java.text.ParsePosition(0);
              java.lang.Object _4 = ((org.apache.ws.jaxme.impl.JMUnmarshallerImpl) getHandler().getJMUnmarshaller()).getDateTimeFormat().parseObject(_2, _3);
              if (_4 == null) {
                throw new java.lang.IllegalArgumentException("Failed to parse dateTime " + _2 + " at: " + _2.substring(_3.getErrorIndex()));
              }
              java.util.Calendar _5;
              if (_4 instanceof java.util.Calendar) {
                _5 = (java.util.Calendar) _4;
              } else {
                _5 = java.util.Calendar.getInstance();
                _5.setTime((java.util.Date) _4);
              }
              _1.setNotBefore(_5);
            } catch (java.lang.Exception _6) {
              getHandler().parseConversionEvent("Failed to convert value of {http://www.wimo.inf.ed.ac.uk/stix}NotBefore: " + pResult, _6);
            }
            return;
          }
          break;
        case 2:
          if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "NotAfter".equals(pLocalName)) {
            try {
              java.lang.String _7 = (java.lang.String) pResult;
              java.text.ParsePosition _8 = new java.text.ParsePosition(0);
              java.lang.Object _9 = ((org.apache.ws.jaxme.impl.JMUnmarshallerImpl) getHandler().getJMUnmarshaller()).getDateTimeFormat().parseObject(_7, _8);
              if (_9 == null) {
                throw new java.lang.IllegalArgumentException("Failed to parse dateTime " + _7 + " at: " + _7.substring(_8.getErrorIndex()));
              }
              java.util.Calendar _10;
              if (_9 instanceof java.util.Calendar) {
                _10 = (java.util.Calendar) _9;
              } else {
                _10 = java.util.Calendar.getInstance();
                _10.setTime((java.util.Date) _9);
              }
              _1.setNotAfter(_10);
            } catch (java.lang.Exception _11) {
              getHandler().parseConversionEvent("Failed to convert value of {http://www.wimo.inf.ed.ac.uk/stix}NotAfter: " + pResult, _11);
            }
            return;
          }
          break;
        default:
          throw new java.lang.IllegalStateException("Illegal state: " + __state);
      }
    }
  
    public boolean isFinished() {
      switch (__state) {
        case 2:
        case 1:
        case 0:
          return true;
        default:
          return false;
      }
    }
  
  }

  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Name
   *  2 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Author
   *  3 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}UUID
   *  4 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Rev
   *  5 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Notes
   *  6 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Enabled
   *  7 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Validity
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
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Author".equals(pLocalName)) {
          __state = 2;
          _1.addSimpleAtomicState();
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "UUID".equals(pLocalName)) {
          __state = 3;
          _1.addSimpleAtomicState();
          return true;
        }
        break;
      case 2:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "UUID".equals(pLocalName)) {
          __state = 3;
          _1.addSimpleAtomicState();
          return true;
        }
        break;
      case 3:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Rev".equals(pLocalName)) {
          __state = 4;
          _1.addSimpleAtomicState();
          return true;
        }
        break;
      case 4:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Notes".equals(pLocalName)) {
          __state = 5;
          _1.addSimpleAtomicState();
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Enabled".equals(pLocalName)) {
          __state = 6;
          _1.addSimpleAtomicState();
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Validity".equals(pLocalName)) {
          __state = 7;
          java.lang.Object _2 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.MetadataComplexTypeImpl.ValidityTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _3 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.MetadataComplexTypeHandler.ValidityTypeHandler();
          _3.init(_1, _2, "http://www.wimo.inf.ed.ac.uk/stix", "Validity", _1.getLevel());
          _3.setAttributes(pAttr);
          _1.addElementParser(_3);
          return true;
        }
        break;
      case 5:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Enabled".equals(pLocalName)) {
          __state = 6;
          _1.addSimpleAtomicState();
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Validity".equals(pLocalName)) {
          __state = 7;
          java.lang.Object _4 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.MetadataComplexTypeImpl.ValidityTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _5 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.MetadataComplexTypeHandler.ValidityTypeHandler();
          _5.init(_1, _4, "http://www.wimo.inf.ed.ac.uk/stix", "Validity", _1.getLevel());
          _5.setAttributes(pAttr);
          _1.addElementParser(_5);
          return true;
        }
        break;
      case 6:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Validity".equals(pLocalName)) {
          __state = 7;
          java.lang.Object _6 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.MetadataComplexTypeImpl.ValidityTypeImpl();
          org.apache.ws.jaxme.impl.JMSAXElementParser _7 =  new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.MetadataComplexTypeHandler.ValidityTypeHandler();
          _7.init(_1, _6, "http://www.wimo.inf.ed.ac.uk/stix", "Validity", _1.getLevel());
          _7.setAttributes(pAttr);
          _1.addElementParser(_7);
          return true;
        }
        break;
      case 7:
        break;
      default:
        throw new java.lang.IllegalStateException("Invalid state: " + __state);
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType) result;
    switch (__state) {
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Name".equals(pLocalName)) {
          try {
            _1.setName((java.lang.String) pResult);
          } catch (java.lang.Exception _2) {
            getHandler().parseConversionEvent("Failed to convert value of {http://www.wimo.inf.ed.ac.uk/stix}Name: " + pResult, _2);
          }
          return;
        }
        break;
      case 2:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Author".equals(pLocalName)) {
          try {
            _1.setAuthor((java.lang.String) pResult);
          } catch (java.lang.Exception _3) {
            getHandler().parseConversionEvent("Failed to convert value of {http://www.wimo.inf.ed.ac.uk/stix}Author: " + pResult, _3);
          }
          return;
        }
        break;
      case 3:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "UUID".equals(pLocalName)) {
          _1.setUUID((java.lang.String) pResult);
          return;
        }
        break;
      case 4:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Rev".equals(pLocalName)) {
          try {
            _1.setRev(getHandler().getDatatypeConverter().parseInt((java.lang.String) pResult));
          } catch (java.lang.Exception _4) {
            getHandler().parseConversionEvent("Failed to convert value of {http://www.wimo.inf.ed.ac.uk/stix}Rev: " + pResult, _4);
          }
          return;
        }
        break;
      case 5:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Notes".equals(pLocalName)) {
          _1.setNotes((java.lang.String) pResult);
          return;
        }
        break;
      case 6:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Enabled".equals(pLocalName)) {
          _1.setEnabled(getHandler().getDatatypeConverter().parseBoolean((java.lang.String) pResult));
          return;
        }
        break;
      case 7:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Validity".equals(pLocalName)) {
          _1.setValidity(((uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType) pResult));
          return;
        }
        break;
      default:
        throw new java.lang.IllegalStateException("Illegal state: " + __state);
    }
  }

  public boolean isFinished() {
    switch (__state) {
      case 7:
      case 6:
      case 5:
      case 4:
        return true;
      default:
        return false;
    }
  }

}
