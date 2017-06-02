package uk.ac.ed.inf.wimo.stix.agent.model.workflow;

public interface MetadataComplexType {
  public interface ValidityType {
    public java.util.Calendar getNotBefore();
  
    public void setNotBefore(java.util.Calendar pNotBefore);
  
    public java.util.Calendar getNotAfter();
  
    public void setNotAfter(java.util.Calendar pNotAfter);
  
  }

  public java.lang.String getName();

  public void setName(java.lang.String pName);

  public java.lang.String getAuthor();

  public void setAuthor(java.lang.String pAuthor);

  public java.lang.String getUUID();

  public void setUUID(java.lang.String pUUID);

  public int getRev();

  public void setRev(int pRev);

  public java.lang.String getNotes();

  public void setNotes(java.lang.String pNotes);

  public boolean isEnabled();

  public void setEnabled(boolean pEnabled);

  public uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType getValidity();

  public void setValidity(uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType pValidity);

}
