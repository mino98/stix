/* Generated By:JJTree: Do not edit this line. ASTStart.java */

package uk.ac.ed.wimo.stix.view.wiki;

public class ASTStart extends SimpleNode {
  public ASTStart(int id) {
    super(id);
  }

  public ASTStart(wiki p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(wikiVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
