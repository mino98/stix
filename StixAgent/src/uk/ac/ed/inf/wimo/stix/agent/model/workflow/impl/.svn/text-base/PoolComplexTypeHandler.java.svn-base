package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class PoolComplexTypeHandler extends org.apache.ws.jaxme.impl.JMSAXElementParser {
  /** The current state. The following values are valid states:
   *  0 = Before parsing the element
   *  1 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}MessageCatch
   *  2 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}MessageThrow
   *  3 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}StartMessageEvent
   *  4 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}StartConditionEvent
   *  5 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}StartTimerEvent
   *  6 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Task
   *  7 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Timer
   *  8 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}Log
   *  9 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}EndPlainEvent
   *  10 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}TerminateEvent
   *  11 = While or after parsing the child element {http://www.wimo.inf.ed.ac.uk/stix}ExclusiveGateway
   * 
   */
  private int __state;


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
    switch (__state) {
      case 0:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageCatch".equals(pLocalName)) {
          __state = 1;
          org.apache.ws.jaxme.JMManager _2 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType.class);
          java.lang.Object _3 = _2.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _4 = _2.getHandler();
          _4.init(_1, _3, "http://www.wimo.inf.ed.ac.uk/stix", "MessageCatch", _1.getLevel());
          _4.setAttributes(pAttr);
          _1.addElementParser(_4);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageThrow".equals(pLocalName)) {
          __state = 2;
          org.apache.ws.jaxme.JMManager _5 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType.class);
          java.lang.Object _6 = _5.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _7 = _5.getHandler();
          _7.init(_1, _6, "http://www.wimo.inf.ed.ac.uk/stix", "MessageThrow", _1.getLevel());
          _7.setAttributes(pAttr);
          _1.addElementParser(_7);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartMessageEvent".equals(pLocalName)) {
          __state = 3;
          org.apache.ws.jaxme.JMManager _8 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType.class);
          java.lang.Object _9 = _8.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _10 = _8.getHandler();
          _10.init(_1, _9, "http://www.wimo.inf.ed.ac.uk/stix", "StartMessageEvent", _1.getLevel());
          _10.setAttributes(pAttr);
          _1.addElementParser(_10);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartConditionEvent".equals(pLocalName)) {
          __state = 4;
          org.apache.ws.jaxme.JMManager _11 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType.class);
          java.lang.Object _12 = _11.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _13 = _11.getHandler();
          _13.init(_1, _12, "http://www.wimo.inf.ed.ac.uk/stix", "StartConditionEvent", _1.getLevel());
          _13.setAttributes(pAttr);
          _1.addElementParser(_13);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartTimerEvent".equals(pLocalName)) {
          __state = 5;
          org.apache.ws.jaxme.JMManager _14 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType.class);
          java.lang.Object _15 = _14.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _16 = _14.getHandler();
          _16.init(_1, _15, "http://www.wimo.inf.ed.ac.uk/stix", "StartTimerEvent", _1.getLevel());
          _16.setAttributes(pAttr);
          _1.addElementParser(_16);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
          __state = 6;
          org.apache.ws.jaxme.JMManager _17 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class);
          java.lang.Object _18 = _17.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _19 = _17.getHandler();
          _19.init(_1, _18, "http://www.wimo.inf.ed.ac.uk/stix", "Task", _1.getLevel());
          _19.setAttributes(pAttr);
          _1.addElementParser(_19);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          __state = 7;
          org.apache.ws.jaxme.JMManager _20 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
          java.lang.Object _21 = _20.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _22 = _20.getHandler();
          _22.init(_1, _21, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", _1.getLevel());
          _22.setAttributes(pAttr);
          _1.addElementParser(_22);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          __state = 8;
          org.apache.ws.jaxme.JMManager _23 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
          java.lang.Object _24 = _23.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _25 = _23.getHandler();
          _25.init(_1, _24, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
          _25.setAttributes(pAttr);
          _1.addElementParser(_25);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _26 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _27 = _26.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _28 = _26.getHandler();
          _28.init(_1, _27, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _28.setAttributes(pAttr);
          _1.addElementParser(_28);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _29 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _30 = _29.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _31 = _29.getHandler();
          _31.init(_1, _30, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _31.setAttributes(pAttr);
          _1.addElementParser(_31);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _32 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _33 = _32.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _34 = _32.getHandler();
          _34.init(_1, _33, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _34.setAttributes(pAttr);
          _1.addElementParser(_34);
          return true;
        }
        break;
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageCatch".equals(pLocalName)) {
          __state = 1;
          org.apache.ws.jaxme.JMManager _35 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType.class);
          java.lang.Object _36 = _35.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _37 = _35.getHandler();
          _37.init(_1, _36, "http://www.wimo.inf.ed.ac.uk/stix", "MessageCatch", _1.getLevel());
          _37.setAttributes(pAttr);
          _1.addElementParser(_37);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageThrow".equals(pLocalName)) {
          __state = 2;
          org.apache.ws.jaxme.JMManager _38 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType.class);
          java.lang.Object _39 = _38.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _40 = _38.getHandler();
          _40.init(_1, _39, "http://www.wimo.inf.ed.ac.uk/stix", "MessageThrow", _1.getLevel());
          _40.setAttributes(pAttr);
          _1.addElementParser(_40);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartMessageEvent".equals(pLocalName)) {
          __state = 3;
          org.apache.ws.jaxme.JMManager _41 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType.class);
          java.lang.Object _42 = _41.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _43 = _41.getHandler();
          _43.init(_1, _42, "http://www.wimo.inf.ed.ac.uk/stix", "StartMessageEvent", _1.getLevel());
          _43.setAttributes(pAttr);
          _1.addElementParser(_43);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartConditionEvent".equals(pLocalName)) {
          __state = 4;
          org.apache.ws.jaxme.JMManager _44 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType.class);
          java.lang.Object _45 = _44.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _46 = _44.getHandler();
          _46.init(_1, _45, "http://www.wimo.inf.ed.ac.uk/stix", "StartConditionEvent", _1.getLevel());
          _46.setAttributes(pAttr);
          _1.addElementParser(_46);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartTimerEvent".equals(pLocalName)) {
          __state = 5;
          org.apache.ws.jaxme.JMManager _47 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType.class);
          java.lang.Object _48 = _47.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _49 = _47.getHandler();
          _49.init(_1, _48, "http://www.wimo.inf.ed.ac.uk/stix", "StartTimerEvent", _1.getLevel());
          _49.setAttributes(pAttr);
          _1.addElementParser(_49);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
          __state = 6;
          org.apache.ws.jaxme.JMManager _50 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class);
          java.lang.Object _51 = _50.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _52 = _50.getHandler();
          _52.init(_1, _51, "http://www.wimo.inf.ed.ac.uk/stix", "Task", _1.getLevel());
          _52.setAttributes(pAttr);
          _1.addElementParser(_52);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          __state = 7;
          org.apache.ws.jaxme.JMManager _53 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
          java.lang.Object _54 = _53.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _55 = _53.getHandler();
          _55.init(_1, _54, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", _1.getLevel());
          _55.setAttributes(pAttr);
          _1.addElementParser(_55);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          __state = 8;
          org.apache.ws.jaxme.JMManager _56 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
          java.lang.Object _57 = _56.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _58 = _56.getHandler();
          _58.init(_1, _57, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
          _58.setAttributes(pAttr);
          _1.addElementParser(_58);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _59 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _60 = _59.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _61 = _59.getHandler();
          _61.init(_1, _60, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _61.setAttributes(pAttr);
          _1.addElementParser(_61);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _62 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _63 = _62.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _64 = _62.getHandler();
          _64.init(_1, _63, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _64.setAttributes(pAttr);
          _1.addElementParser(_64);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _65 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _66 = _65.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _67 = _65.getHandler();
          _67.init(_1, _66, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _67.setAttributes(pAttr);
          _1.addElementParser(_67);
          return true;
        }
        break;
      case 2:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageThrow".equals(pLocalName)) {
          __state = 2;
          org.apache.ws.jaxme.JMManager _68 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType.class);
          java.lang.Object _69 = _68.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _70 = _68.getHandler();
          _70.init(_1, _69, "http://www.wimo.inf.ed.ac.uk/stix", "MessageThrow", _1.getLevel());
          _70.setAttributes(pAttr);
          _1.addElementParser(_70);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartMessageEvent".equals(pLocalName)) {
          __state = 3;
          org.apache.ws.jaxme.JMManager _71 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType.class);
          java.lang.Object _72 = _71.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _73 = _71.getHandler();
          _73.init(_1, _72, "http://www.wimo.inf.ed.ac.uk/stix", "StartMessageEvent", _1.getLevel());
          _73.setAttributes(pAttr);
          _1.addElementParser(_73);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartConditionEvent".equals(pLocalName)) {
          __state = 4;
          org.apache.ws.jaxme.JMManager _74 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType.class);
          java.lang.Object _75 = _74.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _76 = _74.getHandler();
          _76.init(_1, _75, "http://www.wimo.inf.ed.ac.uk/stix", "StartConditionEvent", _1.getLevel());
          _76.setAttributes(pAttr);
          _1.addElementParser(_76);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartTimerEvent".equals(pLocalName)) {
          __state = 5;
          org.apache.ws.jaxme.JMManager _77 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType.class);
          java.lang.Object _78 = _77.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _79 = _77.getHandler();
          _79.init(_1, _78, "http://www.wimo.inf.ed.ac.uk/stix", "StartTimerEvent", _1.getLevel());
          _79.setAttributes(pAttr);
          _1.addElementParser(_79);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
          __state = 6;
          org.apache.ws.jaxme.JMManager _80 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class);
          java.lang.Object _81 = _80.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _82 = _80.getHandler();
          _82.init(_1, _81, "http://www.wimo.inf.ed.ac.uk/stix", "Task", _1.getLevel());
          _82.setAttributes(pAttr);
          _1.addElementParser(_82);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          __state = 7;
          org.apache.ws.jaxme.JMManager _83 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
          java.lang.Object _84 = _83.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _85 = _83.getHandler();
          _85.init(_1, _84, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", _1.getLevel());
          _85.setAttributes(pAttr);
          _1.addElementParser(_85);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          __state = 8;
          org.apache.ws.jaxme.JMManager _86 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
          java.lang.Object _87 = _86.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _88 = _86.getHandler();
          _88.init(_1, _87, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
          _88.setAttributes(pAttr);
          _1.addElementParser(_88);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _89 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _90 = _89.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _91 = _89.getHandler();
          _91.init(_1, _90, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _91.setAttributes(pAttr);
          _1.addElementParser(_91);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _92 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _93 = _92.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _94 = _92.getHandler();
          _94.init(_1, _93, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _94.setAttributes(pAttr);
          _1.addElementParser(_94);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _95 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _96 = _95.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _97 = _95.getHandler();
          _97.init(_1, _96, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _97.setAttributes(pAttr);
          _1.addElementParser(_97);
          return true;
        }
        break;
      case 3:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartMessageEvent".equals(pLocalName)) {
          __state = 3;
          org.apache.ws.jaxme.JMManager _98 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType.class);
          java.lang.Object _99 = _98.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _100 = _98.getHandler();
          _100.init(_1, _99, "http://www.wimo.inf.ed.ac.uk/stix", "StartMessageEvent", _1.getLevel());
          _100.setAttributes(pAttr);
          _1.addElementParser(_100);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartConditionEvent".equals(pLocalName)) {
          __state = 4;
          org.apache.ws.jaxme.JMManager _101 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType.class);
          java.lang.Object _102 = _101.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _103 = _101.getHandler();
          _103.init(_1, _102, "http://www.wimo.inf.ed.ac.uk/stix", "StartConditionEvent", _1.getLevel());
          _103.setAttributes(pAttr);
          _1.addElementParser(_103);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartTimerEvent".equals(pLocalName)) {
          __state = 5;
          org.apache.ws.jaxme.JMManager _104 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType.class);
          java.lang.Object _105 = _104.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _106 = _104.getHandler();
          _106.init(_1, _105, "http://www.wimo.inf.ed.ac.uk/stix", "StartTimerEvent", _1.getLevel());
          _106.setAttributes(pAttr);
          _1.addElementParser(_106);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
          __state = 6;
          org.apache.ws.jaxme.JMManager _107 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class);
          java.lang.Object _108 = _107.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _109 = _107.getHandler();
          _109.init(_1, _108, "http://www.wimo.inf.ed.ac.uk/stix", "Task", _1.getLevel());
          _109.setAttributes(pAttr);
          _1.addElementParser(_109);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          __state = 7;
          org.apache.ws.jaxme.JMManager _110 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
          java.lang.Object _111 = _110.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _112 = _110.getHandler();
          _112.init(_1, _111, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", _1.getLevel());
          _112.setAttributes(pAttr);
          _1.addElementParser(_112);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          __state = 8;
          org.apache.ws.jaxme.JMManager _113 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
          java.lang.Object _114 = _113.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _115 = _113.getHandler();
          _115.init(_1, _114, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
          _115.setAttributes(pAttr);
          _1.addElementParser(_115);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _116 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _117 = _116.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _118 = _116.getHandler();
          _118.init(_1, _117, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _118.setAttributes(pAttr);
          _1.addElementParser(_118);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _119 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _120 = _119.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _121 = _119.getHandler();
          _121.init(_1, _120, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _121.setAttributes(pAttr);
          _1.addElementParser(_121);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _122 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _123 = _122.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _124 = _122.getHandler();
          _124.init(_1, _123, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _124.setAttributes(pAttr);
          _1.addElementParser(_124);
          return true;
        }
        break;
      case 4:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartConditionEvent".equals(pLocalName)) {
          __state = 4;
          org.apache.ws.jaxme.JMManager _125 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType.class);
          java.lang.Object _126 = _125.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _127 = _125.getHandler();
          _127.init(_1, _126, "http://www.wimo.inf.ed.ac.uk/stix", "StartConditionEvent", _1.getLevel());
          _127.setAttributes(pAttr);
          _1.addElementParser(_127);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartTimerEvent".equals(pLocalName)) {
          __state = 5;
          org.apache.ws.jaxme.JMManager _128 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType.class);
          java.lang.Object _129 = _128.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _130 = _128.getHandler();
          _130.init(_1, _129, "http://www.wimo.inf.ed.ac.uk/stix", "StartTimerEvent", _1.getLevel());
          _130.setAttributes(pAttr);
          _1.addElementParser(_130);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
          __state = 6;
          org.apache.ws.jaxme.JMManager _131 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class);
          java.lang.Object _132 = _131.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _133 = _131.getHandler();
          _133.init(_1, _132, "http://www.wimo.inf.ed.ac.uk/stix", "Task", _1.getLevel());
          _133.setAttributes(pAttr);
          _1.addElementParser(_133);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          __state = 7;
          org.apache.ws.jaxme.JMManager _134 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
          java.lang.Object _135 = _134.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _136 = _134.getHandler();
          _136.init(_1, _135, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", _1.getLevel());
          _136.setAttributes(pAttr);
          _1.addElementParser(_136);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          __state = 8;
          org.apache.ws.jaxme.JMManager _137 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
          java.lang.Object _138 = _137.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _139 = _137.getHandler();
          _139.init(_1, _138, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
          _139.setAttributes(pAttr);
          _1.addElementParser(_139);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _140 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _141 = _140.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _142 = _140.getHandler();
          _142.init(_1, _141, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _142.setAttributes(pAttr);
          _1.addElementParser(_142);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _143 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _144 = _143.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _145 = _143.getHandler();
          _145.init(_1, _144, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _145.setAttributes(pAttr);
          _1.addElementParser(_145);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _146 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _147 = _146.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _148 = _146.getHandler();
          _148.init(_1, _147, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _148.setAttributes(pAttr);
          _1.addElementParser(_148);
          return true;
        }
        break;
      case 5:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartTimerEvent".equals(pLocalName)) {
          __state = 5;
          org.apache.ws.jaxme.JMManager _149 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType.class);
          java.lang.Object _150 = _149.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _151 = _149.getHandler();
          _151.init(_1, _150, "http://www.wimo.inf.ed.ac.uk/stix", "StartTimerEvent", _1.getLevel());
          _151.setAttributes(pAttr);
          _1.addElementParser(_151);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
          __state = 6;
          org.apache.ws.jaxme.JMManager _152 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class);
          java.lang.Object _153 = _152.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _154 = _152.getHandler();
          _154.init(_1, _153, "http://www.wimo.inf.ed.ac.uk/stix", "Task", _1.getLevel());
          _154.setAttributes(pAttr);
          _1.addElementParser(_154);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          __state = 7;
          org.apache.ws.jaxme.JMManager _155 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
          java.lang.Object _156 = _155.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _157 = _155.getHandler();
          _157.init(_1, _156, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", _1.getLevel());
          _157.setAttributes(pAttr);
          _1.addElementParser(_157);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          __state = 8;
          org.apache.ws.jaxme.JMManager _158 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
          java.lang.Object _159 = _158.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _160 = _158.getHandler();
          _160.init(_1, _159, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
          _160.setAttributes(pAttr);
          _1.addElementParser(_160);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _161 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _162 = _161.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _163 = _161.getHandler();
          _163.init(_1, _162, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _163.setAttributes(pAttr);
          _1.addElementParser(_163);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _164 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _165 = _164.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _166 = _164.getHandler();
          _166.init(_1, _165, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _166.setAttributes(pAttr);
          _1.addElementParser(_166);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _167 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _168 = _167.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _169 = _167.getHandler();
          _169.init(_1, _168, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _169.setAttributes(pAttr);
          _1.addElementParser(_169);
          return true;
        }
        break;
      case 6:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
          __state = 6;
          org.apache.ws.jaxme.JMManager _170 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class);
          java.lang.Object _171 = _170.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _172 = _170.getHandler();
          _172.init(_1, _171, "http://www.wimo.inf.ed.ac.uk/stix", "Task", _1.getLevel());
          _172.setAttributes(pAttr);
          _1.addElementParser(_172);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          __state = 7;
          org.apache.ws.jaxme.JMManager _173 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
          java.lang.Object _174 = _173.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _175 = _173.getHandler();
          _175.init(_1, _174, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", _1.getLevel());
          _175.setAttributes(pAttr);
          _1.addElementParser(_175);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          __state = 8;
          org.apache.ws.jaxme.JMManager _176 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
          java.lang.Object _177 = _176.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _178 = _176.getHandler();
          _178.init(_1, _177, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
          _178.setAttributes(pAttr);
          _1.addElementParser(_178);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _179 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _180 = _179.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _181 = _179.getHandler();
          _181.init(_1, _180, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _181.setAttributes(pAttr);
          _1.addElementParser(_181);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _182 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _183 = _182.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _184 = _182.getHandler();
          _184.init(_1, _183, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _184.setAttributes(pAttr);
          _1.addElementParser(_184);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _185 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _186 = _185.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _187 = _185.getHandler();
          _187.init(_1, _186, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _187.setAttributes(pAttr);
          _1.addElementParser(_187);
          return true;
        }
        break;
      case 7:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          __state = 7;
          org.apache.ws.jaxme.JMManager _188 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
          java.lang.Object _189 = _188.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _190 = _188.getHandler();
          _190.init(_1, _189, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", _1.getLevel());
          _190.setAttributes(pAttr);
          _1.addElementParser(_190);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          __state = 8;
          org.apache.ws.jaxme.JMManager _191 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
          java.lang.Object _192 = _191.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _193 = _191.getHandler();
          _193.init(_1, _192, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
          _193.setAttributes(pAttr);
          _1.addElementParser(_193);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _194 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _195 = _194.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _196 = _194.getHandler();
          _196.init(_1, _195, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _196.setAttributes(pAttr);
          _1.addElementParser(_196);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _197 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _198 = _197.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _199 = _197.getHandler();
          _199.init(_1, _198, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _199.setAttributes(pAttr);
          _1.addElementParser(_199);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _200 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _201 = _200.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _202 = _200.getHandler();
          _202.init(_1, _201, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _202.setAttributes(pAttr);
          _1.addElementParser(_202);
          return true;
        }
        break;
      case 8:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          __state = 8;
          org.apache.ws.jaxme.JMManager _203 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
          java.lang.Object _204 = _203.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _205 = _203.getHandler();
          _205.init(_1, _204, "http://www.wimo.inf.ed.ac.uk/stix", "Log", _1.getLevel());
          _205.setAttributes(pAttr);
          _1.addElementParser(_205);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _206 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _207 = _206.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _208 = _206.getHandler();
          _208.init(_1, _207, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _208.setAttributes(pAttr);
          _1.addElementParser(_208);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _209 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _210 = _209.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _211 = _209.getHandler();
          _211.init(_1, _210, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _211.setAttributes(pAttr);
          _1.addElementParser(_211);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _212 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _213 = _212.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _214 = _212.getHandler();
          _214.init(_1, _213, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _214.setAttributes(pAttr);
          _1.addElementParser(_214);
          return true;
        }
        break;
      case 9:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          __state = 9;
          org.apache.ws.jaxme.JMManager _215 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
          java.lang.Object _216 = _215.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _217 = _215.getHandler();
          _217.init(_1, _216, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", _1.getLevel());
          _217.setAttributes(pAttr);
          _1.addElementParser(_217);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _218 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _219 = _218.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _220 = _218.getHandler();
          _220.init(_1, _219, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _220.setAttributes(pAttr);
          _1.addElementParser(_220);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _221 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _222 = _221.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _223 = _221.getHandler();
          _223.init(_1, _222, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _223.setAttributes(pAttr);
          _1.addElementParser(_223);
          return true;
        }
        break;
      case 10:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          __state = 10;
          org.apache.ws.jaxme.JMManager _224 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
          java.lang.Object _225 = _224.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _226 = _224.getHandler();
          _226.init(_1, _225, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", _1.getLevel());
          _226.setAttributes(pAttr);
          _1.addElementParser(_226);
          return true;
        } else if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _227 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _228 = _227.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _229 = _227.getHandler();
          _229.init(_1, _228, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _229.setAttributes(pAttr);
          _1.addElementParser(_229);
          return true;
        }
        break;
      case 11:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          __state = 11;
          org.apache.ws.jaxme.JMManager _230 = getHandler().getJMUnmarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
          java.lang.Object _231 = _230.getElementS();
          org.apache.ws.jaxme.impl.JMSAXElementParser _232 = _230.getHandler();
          _232.init(_1, _231, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", _1.getLevel());
          _232.setAttributes(pAttr);
          _1.addElementParser(_232);
          return true;
        }
        break;
      default:
        throw new java.lang.IllegalStateException("Invalid state: " + __state);
    }
    return false;
  }

  public void endElement(java.lang.String pNamespaceURI, java.lang.String pLocalName, java.lang.String pQName, java.lang.Object pResult) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType) result;
    switch (__state) {
      case 1:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageCatch".equals(pLocalName)) {
          _1.getMessageCatch().add(pResult);
          return;
        }
        break;
      case 2:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "MessageThrow".equals(pLocalName)) {
          _1.getMessageThrow().add(pResult);
          return;
        }
        break;
      case 3:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartMessageEvent".equals(pLocalName)) {
          _1.getStartMessageEvent().add(pResult);
          return;
        }
        break;
      case 4:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartConditionEvent".equals(pLocalName)) {
          _1.getStartConditionEvent().add(pResult);
          return;
        }
        break;
      case 5:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "StartTimerEvent".equals(pLocalName)) {
          _1.getStartTimerEvent().add(pResult);
          return;
        }
        break;
      case 6:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Task".equals(pLocalName)) {
          _1.getTask().add(pResult);
          return;
        }
        break;
      case 7:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Timer".equals(pLocalName)) {
          _1.getTimer().add(pResult);
          return;
        }
        break;
      case 8:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "Log".equals(pLocalName)) {
          _1.getLog().add(pResult);
          return;
        }
        break;
      case 9:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "EndPlainEvent".equals(pLocalName)) {
          _1.getEndPlainEvent().add(pResult);
          return;
        }
        break;
      case 10:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "TerminateEvent".equals(pLocalName)) {
          _1.getTerminateEvent().add(pResult);
          return;
        }
        break;
      case 11:
        if ("http://www.wimo.inf.ed.ac.uk/stix".equals(pNamespaceURI)  &&  "ExclusiveGateway".equals(pLocalName)) {
          _1.getExclusiveGateway().add(pResult);
          return;
        }
        break;
      default:
        throw new java.lang.IllegalStateException("Illegal state: " + __state);
    }
  }

  public boolean isFinished() {
    switch (__state) {
      case 11:
      case 10:
      case 9:
      case 8:
      case 7:
      case 6:
      case 5:
      case 4:
      case 3:
      case 2:
      case 1:
      case 0:
        return true;
      default:
        return false;
    }
  }

}
