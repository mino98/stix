/* Generated By:JJTree: Do not edit this line. ASTPlainText.java */
package uk.ac.ed.wimo.stix.view.wiki;


public class ASTPlainText extends SimpleNode {
  public ASTPlainText(int id) {
    super(id);
  }

  public ASTPlainText(wiki p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(wikiVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
    String text;
    public void setText(String t) { text = t; }

    public String toString() { return text; }
}
