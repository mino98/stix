package uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl;

public class MetadataComplexTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType {
  public static class ValidityTypeImpl implements uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType {
    private java.util.Calendar _notBefore;
  
    private java.util.Calendar _notAfter;
  
  
    public java.util.Calendar getNotBefore() {
      return _notBefore;
    }
  
    public void setNotBefore(java.util.Calendar pNotBefore) {
      _notBefore = pNotBefore;
    }
  
    public java.util.Calendar getNotAfter() {
      return _notAfter;
    }
  
    public void setNotAfter(java.util.Calendar pNotAfter) {
      _notAfter = pNotAfter;
    }
  
  }

  private java.lang.String _name;

  private java.lang.String _author;

  private java.lang.String _uUID;

  private int _rev;

  private java.lang.String _notes;

  private boolean _enabled = true;

  private uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType _validity;


  public java.lang.String getName() {
    return _name;
  }

  public void setName(java.lang.String pName) {
    if (pName != null) {
      if (pName.length() > 256) {
        throw new java.lang.IllegalArgumentException("Length of 256 characters exceeded: " + pName);
      }
    }
    _name = pName;
  }

  public java.lang.String getAuthor() {
    return _author;
  }

  public void setAuthor(java.lang.String pAuthor) {
    if (pAuthor != null) {
      if (pAuthor.length() > 256) {
        throw new java.lang.IllegalArgumentException("Length of 256 characters exceeded: " + pAuthor);
      }
    }
    _author = pAuthor;
  }

  public java.lang.String getUUID() {
    return _uUID;
  }

  public void setUUID(java.lang.String pUUID) {
    _uUID = pUUID;
  }

  public int getRev() {
    return _rev;
  }

  public void setRev(int pRev) {
    _rev = pRev;
  }

  public java.lang.String getNotes() {
    return _notes;
  }

  public void setNotes(java.lang.String pNotes) {
    _notes = pNotes;
  }

  public boolean isEnabled() {
    return _enabled;
  }

  public void setEnabled(boolean pEnabled) {
    _enabled = pEnabled;
  }

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType getValidity() {
    return _validity;
  }

  public void setValidity(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType pValidity) {
    _validity = pValidity;
  }

}
