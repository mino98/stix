package uk.ac.ed.inf.wimo.stix.agent.model.workflow;

public class ObjectFactory {
  private org.apache.ws.jaxme.impl.JAXBContextImpl jaxbContext;

  private java.util.Map properties;


  public java.lang.Object newInstance(java.lang.Class pElementInterface) throws javax.xml.bind.JAXBException {
    return jaxbContext.getManager(pElementInterface).getElementJ();
  }

  public java.lang.Object getProperty(java.lang.String pName) {
    if (properties == null) {
      return null;
    }
    return properties.get(pName);
  }

  public void setProperty(java.lang.String pName, java.lang.Object pValue) {
    if (properties == null) {
      properties = new java.util.HashMap();
    }
    properties.put(pName, pValue);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow createWorkflow() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.WorkflowComplexType createWorkflowComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.WorkflowComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.WorkflowComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType createMetadataComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType createMetadataComplexTypeValidityType() throws javax.xml.bind.JAXBException {
    return new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.MetadataComplexTypeImpl.ValidityTypeImpl();
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType createAttributeSetComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType createAttributeSetComplexTypeAttributeType() throws javax.xml.bind.JAXBException {
    return new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.AttributeSetComplexTypeImpl.AttributeTypeImpl();
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType createPoolComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType createTaskComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType createParameterComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType createExclusiveGatewayEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartEventComplexType createStartEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.EventComplexType createEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.EventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType createEndPlainEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType createTerminateEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TerminateEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType createTimerEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType createStartTimerEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType createStartConditionEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType createStartMessageEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType createMessageThrowingEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType createDataMappingComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType createDataInComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType createDataOutComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType createFlowMappingComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingConditionComplexType createFlowMappingConditionComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingConditionComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingConditionComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType createMessageCatchingEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType createLogEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType createFlowMappingTaskComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType.TimeoutType createFlowMappingTaskComplexTypeTimeoutType() throws javax.xml.bind.JAXBException {
    return new uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.FlowMappingTaskComplexTypeImpl.TimeoutTypeImpl();
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndEventComplexType createEndEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowEventComplexType createFlowEventComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowEventComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowEventComplexType.class);
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType createFlowOutConditionComplexType() throws javax.xml.bind.JAXBException {
    return (uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType) newInstance(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType.class);
  }

  public ObjectFactory() throws javax.xml.bind.JAXBException {
    jaxbContext = (org.apache.ws.jaxme.impl.JAXBContextImpl) javax.xml.bind.JAXBContext.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
  }

}
