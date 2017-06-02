package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class DataMappingComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}in
   *  2 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}out
   * 
   */
  private int __state;


  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
    switch (__state) {
      case 0:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "in".equals(pLocalName)) {
          __state = 1;
          org.apache.ws.jaxme.JMManager _2 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType.class);
          java.lang.Object _3 = _2.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _4 = _2.getHandler();
          _4.init(_1, _3, "http://www.wimo.inf.ed.ac.uk/stix", "in", _1.getLevel());
          _4.setAttributes(pAttr);
          _1.addElementParser(_4);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "out".equals(pLocalName)) {
          __state = 2;
          org.apache.ws.jaxme.JMManager _5 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType.class);
          java.lang.Object _6 = _5.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _7 = _5.getHandler();
          _7.init(_1, _6, "http://www.wimo.inf.ed.ac.uk/stix", "out", _1.getLevel());
          _7.setAttributes(pAttr);
          _1.addElementParser(_7);
          return true;
        }
        break;
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "out".equals(pLocalName)) {
          __state = 2;
          org.apache.ws.jaxme.JMManager _8 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType.class);
          java.lang.Object _9 = _8.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _10 = _8.getHandler();
          _10.init(_1, _9, "http://www.wimo.inf.ed.ac.uk/stix", "out", _1.getLevel());
          _10.setAttributes(pAttr);
          _1.addElementParser(_10);
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType) result;
    switch (__state) {
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "in".equals(pLocalName)) {
          _1.setIn(((uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType) pResult));
          return;
        }
        break;
      case 2:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "out".equals(pLocalName)) {
          _1.setOut(((uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType) pResult));
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
