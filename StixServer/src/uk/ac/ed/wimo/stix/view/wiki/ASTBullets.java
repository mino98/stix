/* Generated By:JJTree: Do not edit this line. ASTBullets.java */
package uk.ac.ed.wimo.stix.view.wiki;


public class ASTBullets extends SimpleNode {
  public ASTBullets(int id) {
    super(id);
  }

  public ASTBullets(wiki p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(wikiVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
   public int size;

   public void setSize(String s)
   {
      size = s.length();
   }
}