package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class WorkflowComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Metadata
   *  2 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}AttributeSet
   *  3 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Pool
   * 
   */
  private int __state;


  public boolean startElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, org.xml.sax.Attributes pAttr) throws org.xml.sax.SAXException {
    org.apache.ws.jaxme.impl.JMUnmarshallerHandlerImpl _1 = getHandler();
    switch (__state) {
      case 0:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Metadata".equals(pLocalName)) {
          __state = 1;
          org.apache.ws.jaxme.JMManager _2 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.class);
          java.lang.Object _3 = _2.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _4 = _2.getHandler();
          _4.init(_1, _3, "http://www.wimo.inf.ed.ac.uk/stix", "Metadata", _1.getLevel());
          _4.setAttributes(pAttr);
          _1.addElementParser(_4);
          return true;
        }
        break;
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "AttributeSet".equals(pLocalName)) {
          __state = 2;
          org.apache.ws.jaxme.JMManager _5 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.class);
          java.lang.Object _6 = _5.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _7 = _5.getHandler();
          _7.init(_1, _6, "http://www.wimo.inf.ed.ac.uk/stix", "AttributeSet", _1.getLevel());
          _7.setAttributes(pAttr);
          _1.addElementParser(_7);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Pool".equals(pLocalName)) {
          __state = 3;
          org.apache.ws.jaxme.JMManager _8 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType.class);
          java.lang.Object _9 = _8.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _10 = _8.getHandler();
          _10.init(_1, _9, "http://www.wimo.inf.ed.ac.uk/stix", "Pool", _1.getLevel());
          _10.setAttributes(pAttr);
          _1.addElementParser(_10);
          return true;
        }
        break;
      case 2:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Pool".equals(pLocalName)) {
          __state = 3;
          org.apache.ws.jaxme.JMManager _11 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType.class);
          java.lang.Object _12 = _11.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _13 = _11.getHandler();
          _13.init(_1, _12, "http://www.wimo.inf.ed.ac.uk/stix", "Pool", _1.getLevel());
          _13.setAttributes(pAttr);
          _1.addElementParser(_13);
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
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.WorkflowComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.WorkflowComplexType) result;
    switch (__state) {
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Metadata".equals(pLocalName)) {
          _1.setMetadata(((uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType) pResult));
          return;
        }
        break;
      case 2:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "AttributeSet".equals(pLocalName)) {
          _1.setAttributeSet(((uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType) pResult));
          return;
        }
        break;
      case 3:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Pool".equals(pLocalName)) {
          _1.setPool(((uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType) pResult));
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
        return true;
      default:
        return false;
    }
  }

}
