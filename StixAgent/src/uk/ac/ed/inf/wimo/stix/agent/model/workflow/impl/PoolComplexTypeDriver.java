package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class PoolComplexTypeDriver implements org.apache.ws.jaxme.impl.JMSAXDriver {
  public org.xml.sax.helpers.AttributesImpl getAttributes(org.apache.ws.jaxme.impl.JMSAXDriverController pController, java.lang.Object pObject) throws org.xml.sax.SAXException {
    org.xml.sax.helpers.AttributesImpl _1 = new org.xml.sax.helpers.AttributesImpl();
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType _2 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType) pObject;
    java.lang.String _3 = _2.getQuery();
    if (_3 != null) {
      _1.addAttribute("", "Query", pController.getAttrQName(this, "", "Query"), "CDATA", _2.getQuery());
    }
    _1.addAttribute("", "x", pController.getAttrQName(this, "", "x"), "CDATA", pController.getDatatypeConverter().printInt(_2.getX()));
    _1.addAttribute("", "y", pController.getAttrQName(this, "", "y"), "CDATA", pController.getDatatypeConverter().printInt(_2.getY()));
    return _1;
  }

  public java.lang.String getPreferredPrefix(java.lang.String pURI) {
    if (pURI == null) {
      pURI = "";
    }
    if (pURI.equals("http://www.wimo.inf.ed.ac.uk/stix")) {
      return "stix";
    }
    return null;
  }

  public void marshalChilds(org.apache.ws.jaxme.impl.JMSAXDriverController pController, org.xml.sax.ContentHandler pHandler, java.lang.Object pObject) throws org.xml.sax.SAXException {
    uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType _1 = (uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType) pObject;
    java.util.List _2 = _1.getMessageCatch();
    for (int _3 = 0;  _3 < (_2).size();  _3++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _4 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType.class).getDriver();
      pController.marshal(_4, "http://www.wimo.inf.ed.ac.uk/stix", "MessageCatch", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType)_2.get(_3));
    }
    java.util.List _5 = _1.getMessageThrow();
    for (int _6 = 0;  _6 < (_5).size();  _6++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _7 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType.class).getDriver();
      pController.marshal(_7, "http://www.wimo.inf.ed.ac.uk/stix", "MessageThrow", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType)_5.get(_6));
    }
    java.util.List _8 = _1.getStartMessageEvent();
    for (int _9 = 0;  _9 < (_8).size();  _9++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _10 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType.class).getDriver();
      pController.marshal(_10, "http://www.wimo.inf.ed.ac.uk/stix", "StartMessageEvent", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType)_8.get(_9));
    }
    java.util.List _11 = _1.getStartConditionEvent();
    for (int _12 = 0;  _12 < (_11).size();  _12++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _13 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType.class).getDriver();
      pController.marshal(_13, "http://www.wimo.inf.ed.ac.uk/stix", "StartConditionEvent", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType)_11.get(_12));
    }
    java.util.List _14 = _1.getStartTimerEvent();
    for (int _15 = 0;  _15 < (_14).size();  _15++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _16 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType.class).getDriver();
      pController.marshal(_16, "http://www.wimo.inf.ed.ac.uk/stix", "StartTimerEvent", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType)_14.get(_15));
    }
    java.util.List _17 = _1.getTask();
    for (int _18 = 0;  _18 < (_17).size();  _18++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _19 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class).getDriver();
      pController.marshal(_19, "http://www.wimo.inf.ed.ac.uk/stix", "Task", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType)_17.get(_18));
    }
    java.util.List _20 = _1.getTimer();
    for (int _21 = 0;  _21 < (_20).size();  _21++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _22 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class).getDriver();
      pController.marshal(_22, "http://www.wimo.inf.ed.ac.uk/stix", "Timer", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType)_20.get(_21));
    }
    java.util.List _23 = _1.getLog();
    for (int _24 = 0;  _24 < (_23).size();  _24++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _25 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class).getDriver();
      pController.marshal(_25, "http://www.wimo.inf.ed.ac.uk/stix", "Log", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType)_23.get(_24));
    }
    java.util.List _26 = _1.getEndPlainEvent();
    for (int _27 = 0;  _27 < (_26).size();  _27++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _28 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class).getDriver();
      pController.marshal(_28, "http://www.wimo.inf.ed.ac.uk/stix", "EndPlainEvent", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType)_26.get(_27));
    }
    java.util.List _29 = _1.getTerminateEvent();
    for (int _30 = 0;  _30 < (_29).size();  _30++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _31 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class).getDriver();
      pController.marshal(_31, "http://www.wimo.inf.ed.ac.uk/stix", "TerminateEvent", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType)_29.get(_30));
    }
    java.util.List _32 = _1.getExclusiveGateway();
    for (int _33 = 0;  _33 < (_32).size();  _33++) {
      org.apache.ws.jaxme.impl.JMSAXDriver _34 = pController.getJMMarshaller().getJAXBContextImpl().getManagerS(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class).getDriver();
      pController.marshal(_34, "http://www.wimo.inf.ed.ac.uk/stix", "ExclusiveGateway", (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType)_32.get(_33));
    }
  }

}
