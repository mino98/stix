package uk.ac.ed.inf.wimo.stix.agent.model.workflow;

public interface ExclusiveGatewayEventComplexType extends uk.ac.ed.inf.wimo.stix.agent.model.workflow.EventComplexType {
  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingConditionComplexType getFlowMapping();

  public void setFlowMapping(uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingConditionComplexType pFlowMapping);

}
