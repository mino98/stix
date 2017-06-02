package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class FlowMappingComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}out
   * 
   */
  private int __state;


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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType) result;
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
