package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class AttributeSetComplexTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType {
  public static class AttributeTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType {
    private boolean _persistent = false;
  
    private java.lang.String _type;
  
    private java.lang.String _name;
  
    private java.lang.String _value;
  
  
    public boolean isPersistent() {
      return _persistent;
    }
  
    public void setPersistent(boolean pPersistent) {
      _persistent = pPersistent;
    }
  
    public java.lang.String getType() {
      return _type;
    }
  
    public void setType(java.lang.String pType) {
      _type = pType;
    }
  
    public java.lang.String getName() {
      return _name;
    }
  
    public void setName(java.lang.String pName) {
      _name = pName;
    }
  
    public java.lang.String getValue() {
      return _value;
    }
  
    public void setValue(java.lang.String pValue) {
      _value = pValue;
    }
  
  }

  private java.util.List _attribute = new java.util.ArrayList();


  public java.util.List getAttribute() {
    return _attribute;
  }

}
