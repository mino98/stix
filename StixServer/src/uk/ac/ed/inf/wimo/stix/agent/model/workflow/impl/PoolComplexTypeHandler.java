package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class PoolComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  /** This array indicates the state of the group. For any
   * possible child, the corresponding boolean value is set,
   * if the child is parsed.
   * If the same child occurs again, and the childs boolean
   * value is true, then an exception is thrown.
   * These are the indices, to which the child elements are
   * mapped:
   *  0 = The child element {http://www.wimo.inf.ed.ac.uk/stix}MessageCatch
   *  1 = The child element {http://www.wimo.inf.ed.ac.uk/stix}MessageThrow
   *  2 = The child element {http://www.wimo.inf.ed.ac.uk/stix}StartMessageEvent
   *  3 = The child element {http://www.wimo.inf.ed.ac.uk/stix}StartConditionEvent
   *  4 = The child element {http://www.wimo.inf.ed.ac.uk/stix}StartTimerEvent
   *  5 = The child element {http://www.wimo.inf.ed.ac.uk/stix}Task
   *  6 = The child element {http://www.wimo.inf.ed.ac.uk/stix}Timer
   *  7 = The child element {http://www.wimo.inf.ed.ac.uk/stix}Log
   *  8 = The child element {http://www.wimo.inf.ed.ac.uk/stix}EndPlainEvent
   *  9 = The child element {http://www.wimo.inf.ed.ac.uk/stix}TerminateEvent
   * 
   */
  private boolean[] __state = new boolean[10];

  /** Index of the particle being currently parsed
   * 
   */
  private int __childNum;


  public void addAttribute(java.lang.String pURI, java.lang.String pLocalName, java.lang.String pValue) throws org.xml.sax.SAXException {
    if (pURI == null) {
      pURI = "";
    }
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType) result;
    if ("".equals(pURI)) {
      if ("Query".equals(pLocalName)) {
        _1.setQuery((java.lang.String) pValue);
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
    if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageCatch".equals(pLocalName)) {
      if (__state[0]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}MessageCatch has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 0;
      __state[0] = true;
      org.apache.ws.jaxme.JMManager _2 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType.class);
      java.lang.Object _3 = _2.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _4 = _2.getHandler();
      _4.init(_1, _3, "http://www.wimo.inf.ed.ac.uk/stix", "MessageCatch", _1.getLevel());
      _4.setAttributes(pAttr);
      _1.addElementParser(_4);
      return true;
    } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageThrow".equals(pLocalName)) {
      if (__state[1]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}MessageThrow has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 1;
      __state[1] = true;
      org.apache.ws.jaxme.JMManager _5 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType.class);
      java.lang.Object _6 = _5.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _7 = _5.getHandler();
      _7.init(_1, _6, "http://www.wimo.inf.ed.ac.uk/stix", "MessageThrow", _1.getLevel());
      _7.setAttributes(pAttr);
      _1.addElementParser(_7);
      return true;
    } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartMessageEvent".equals(pLocalName)) {
      if (__state[2]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}StartMessageEvent has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 2;
      __state[2] = true;
      org.apache.ws.jaxme.JMManager _8 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType.class);
      java.lang.Object _9 = _8.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _10 = _8.getHandler();
      _10.init(_1, _9, "http://www.wimo.inf.ed.ac.uk/stix", "StartMessageEvent", _1.getLevel());
      _10.setAttributes(pAttr);
      _1.addElementParser(_10);
      return true;
    } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartConditionEvent".equals(pLocalName)) {
      if (__state[3]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}StartConditionEvent has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 3;
      __state[3] = true;
      org.apache.ws.jaxme.JMManager _11 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType.class);
      java.lang.Object _12 = _11.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _13 = _11.getHandler();
      _13.init(_1, _12, "http://www.wimo.inf.ed.ac.uk/stix", "StartConditionEvent", _1.getLevel());
      _13.setAttributes(pAttr);
      _1.addElementParser(_13);
      return true;
    } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartTimerEvent".equals(pLocalName)) {
      if (__state[4]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}StartTimerEvent has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 4;
      __state[4] = true;
      org.apache.ws.jaxme.JMManager _14 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType.class);
      java.lang.Object _15 = _14.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _16 = _14.getHandler();
      _16.init(_1, _15, "http://www.wimo.inf.ed.ac.uk/stix", "StartTimerEvent", _1.getLevel());
      _16.setAttributes(pAttr);
      _1.addElementParser(_16);
      return true;
    } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
      if (__state[5]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}Task has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 5;
      __state[5] = true;
      org.apache.ws.jaxme.JMManager _17 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class);
      java.lang.Object _18 = _17.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _19 = _17.getHandler();
      _19.init(_1, _18, "http://www.wimo.inf.ed.ac.uk/stix", "Task", _1.getLevel());
      _19.setAttributes(pAttr);
      _1.addElementParser(_19);
      return true;
    } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
      if (__state[6]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}Timer has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 6;
      __state[6] = true;
      org.apache.ws.jaxme.JMManager _20 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
      java.lang.Object _21 = _20.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _22 = _20.getHandler();
      _22.init(_1, _21, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", _1.getLevel());
      _22.setAttributes(pAttr);
      _1.addElementParser(_22);
      return true;
    } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
      if (__state[7]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}Log has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 7;
      __state[7] = true;
      org.apache.ws.jaxme.JMManager _23 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
      java.lang.Object _24 = _23.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _25 = _23.getHandler();
      _25.init(_1, _24, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
      _25.setAttributes(pAttr);
      _1.addElementParser(_25);
      return true;
    } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
      if (__state[8]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}EndPlainEvent has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 8;
      __state[8] = true;
      org.apache.ws.jaxme.JMManager _26 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
      java.lang.Object _27 = _26.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _28 = _26.getHandler();
      _28.init(_1, _27, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
      _28.setAttributes(pAttr);
      _1.addElementParser(_28);
      return true;
    } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
      if (__state[9]) {
        getHandler().validationEvent(javax.xml.bind.ValidationEvent.WARNING, "The element {http://www.wimo.inf.ed.ac.uk/stix}TerminateEvent has already been defined.", org.apache.ws.jaxme.ValidationEvents.EVENT_ALL_GROUP_REUSE, null);
      }
      __childNum = 9;
      __state[9] = true;
      org.apache.ws.jaxme.JMManager _29 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
      java.lang.Object _30 = _29.getElementS();
      org.apache.ws.jaxme.impl.JMSAXElementParser _31 = _29.getHandler();
      _31.init(_1, _30, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
      _31.setAttributes(pAttr);
      _1.addElementParser(_31);
      return true;
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType) result;
    switch (__childNum) {
      case 0:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageCatch".equals(pLocalName)) {
          _1.getMessageCatch().add(pResult);
          return;
        }
        break;
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageThrow".equals(pLocalName)) {
          _1.getMessageThrow().add(pResult);
          return;
        }
        break;
      case 2:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartMessageEvent".equals(pLocalName)) {
          _1.getStartMessageEvent().add(pResult);
          return;
        }
        break;
      case 3:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartConditionEvent".equals(pLocalName)) {
          _1.getStartConditionEvent().add(pResult);
          return;
        }
        break;
      case 4:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartTimerEvent".equals(pLocalName)) {
          _1.getStartTimerEvent().add(pResult);
          return;
        }
        break;
      case 5:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
          _1.getTask().add(pResult);
          return;
        }
        break;
      case 6:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          _1.getTimer().add(pResult);
          return;
        }
        break;
      case 7:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          _1.getLog().add(pResult);
          return;
        }
        break;
      case 8:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          _1.getEndPlainEvent().add(pResult);
          return;
        }
        break;
      case 9:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          _1.getTerminateEvent().add(pResult);
          return;
        }
        break;
      default:
        throw new java.lang.IllegalStateException("Illegal state: " + __childNum);
    }
  }

  public boolean isFinished() {
    return true;
  }

}
