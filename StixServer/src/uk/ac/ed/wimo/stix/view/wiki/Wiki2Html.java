package uk.ac.ed.wimo.stix.view.wiki;

/* Modified by Alex to work on strings instead of writing to stdout */

import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;

public class Wiki2Html implements wikiVisitor
{
   private static wiki w = null;

   boolean bold = true;
   boolean italics = true;
   private static String MONO_BEGIN = "{{";
   private static String MONO_END = "}}";
   private boolean mono = true;

   private MyStringBuilder sb;

   public String wikiUrl = "/Wiki.jsp?page=";

   private Wiki2Html() {
       sb = new MyStringBuilder();
   }

   private String replaceSpecial(String output)
   {
      output = output.replaceAll("&", "&amp;");
      output = output.replaceAll("<", "&lt;");
      output = output.replaceAll(">", "&gt;");
      return output;
   }
   private String replaceBold(String output)
   {
      while (output.indexOf("__") > -1)
      {
         String boldString = bold ? "<b>" : "</b>";
         bold = !bold;
         output = output.replaceFirst("__", boldString);
      }
      return output;
   }

   private String replaceItalics(String output)
   {
      while (output.indexOf("''") > -1)
      {
         String iString = italics ? "<i>" : "</i>";
         italics = !italics;
         output = output.replaceFirst("''", iString);
      }
      return output;
   }

   private String replaceMono(String output)
   {
      String findString = mono ? "{{" : "}}";

      while (output.indexOf(findString) > -1)
      {
         String pre = mono ? "<tt>" : "</tt>";
         String replaceString = mono ? "\\{\\{" : "\\}\\}";
         mono = !mono;
         output = output.replaceFirst(replaceString, pre);
         findString = mono ? "{{" : "}}";
      }
      return output;
   }

   public Node getPrevious(SimpleNode node, Object data)
   {
      int index = ((Integer) data).intValue();
      if (index == 0) return null;
      ASTStart start = (ASTStart) node.jjtGetParent().jjtGetParent();
      ASTLine line = (ASTLine) start.jjtGetChild(index - 1);
      if (line.jjtGetNumChildren() == 0) return null;
      return line.jjtGetChild(0);
   }

   public Node getNext(SimpleNode node, Object data)
   {
      int index = ((Integer) data).intValue();
      ASTStart start = (ASTStart) node.jjtGetParent().jjtGetParent();
      if (index + 1 >= start.jjtGetNumChildren()) return null;
      ASTLine line = (ASTLine) start.jjtGetChild(index + 1);
      if (line.jjtGetNumChildren() == 0) return null;
      return line.jjtGetChild(0);
   }

   public Object visit(SimpleNode node, Object data)
   {
      throw new RuntimeException("UNREACHABLE");
   }

   public Object visit(ASTStart node, Object data)
   {
      sb.appendln("<html>");
      sb.appendln("<body>");
      sb.appendln("<p>");

      for (int i = 0; i < node.jjtGetNumChildren(); i++)
      {
         SimpleNode obj = (SimpleNode) node.jjtGetChild(i);
         obj.jjtAccept(this, new Integer(i)); // pass along index
      }
      sb.appendln("</p>");
      sb.appendln("</body>");
      sb.appendln("</html>");
      return data;
   }

   public Object visit(ASTLine node, Object data)
   {
      if (node.jjtGetNumChildren() == 0)
      {
         sb.appendln("</p><p>");
      }
      else
      {
         node.childrenAccept(this, data);
         sb.appendln("");
      }
      return data;
   }

   public Object visit(ASTHeader node, Object data)
   {
      int size = node.size;
      if (size > 3) size = 3;
      size = 5 - size;
      sb.append("<h" + size + ">");
      node.childrenAccept(this, null);
      sb.appendln("</h" + size + ">");
      return data;
   }

   public Object visit(ASTHorizontalRuler node, Object data)
   {
      sb.append("<hr>");
      node.childrenAccept(this, null);
      return data;
   }

   public Object visit(ASTNumbers node, Object data)
   {
      Node previous = getPrevious(node, data);
      if (previous != null && previous instanceof ASTNumbers)
      {
         ASTNumbers pBullet = (ASTNumbers) previous;
         if (node.size > pBullet.size)
         {
            for (int i = pBullet.size; i < node.size; i++)
            {
               sb.appendln("<ol>");
            }
         }
      }
      else
      {
         for (int i = 0; i < node.size; i++) sb.appendln("<ol>");
      }
      sb.append("<li>");
      node.childrenAccept(this, null);
      sb.appendln("</li>");
      Node next = getNext(node, data);
      if (next != null && next instanceof ASTNumbers)
      {
         ASTNumbers pBullet = (ASTNumbers) next;
         if (node.size > pBullet.size)
         {
            for (int i = pBullet.size; i < node.size; i++)
            {
               sb.appendln("</ol>");
            }
         }
      }
      else
      {
         for (int i = 0; i < node.size; i++) sb.appendln("</ol>");
      }
      return data;
   }

   public Object visit(ASTBullets node, Object data)
   {
      Node previous = getPrevious(node, data);
      if (previous != null && previous instanceof ASTBullets)
      {
         ASTBullets pBullet = (ASTBullets) previous;
         if (node.size > pBullet.size)
         {
            for (int i = pBullet.size; i < node.size; i++)
            {
               sb.appendln("<ul>");
            }
         }
      }
      else
      {
         for (int i = 0; i < node.size; i++) sb.appendln("<ul>");
      }
      sb.append("<li>");
      node.childrenAccept(this, null);
      sb.append("</li>");
      Node next = getNext(node, data);
      if (next != null && next instanceof ASTBullets)
      {
         ASTBullets pBullet = (ASTBullets) next;
         if (node.size > pBullet.size)
         {
            for (int i = pBullet.size; i < node.size; i++)
            {
               sb.appendln("");
               sb.append("</ul>");
            }
         }
      }
      else
      {
         for (int i = 0; i < node.size; i++)
         {
            sb.appendln("");
            sb.append("</ul>");
         }
      }
      return data;
   }

   public Object visit(ASTTable node, Object data)
   {
      Node previous = getPrevious(node, data);
      if (previous == null || !(previous instanceof ASTTable))
      {
         sb.appendln("<table border=\"1\">");
      }
      sb.append("<tr>");
      node.childrenAccept(this, null);
      sb.append("</tr>");
      Node next = getNext(node, data);
      if (next == null || !(next instanceof ASTTable))
      {
         sb.appendln("");
         sb.append("</table>");
      }
      return data;
   }

   public Object visit(ASTColumn node, Object data)
   {
      if (node.isHeader)
         sb.append("<th>");
      else
         sb.append("<td>");
      node.childrenAccept(this, null);
      if (node.isHeader)
         sb.append("</th>");
      else
         sb.append("</td>");
      return data;
   }

   public Object visit(ASTTerm node, Object data)
   {
      sb.appendln("<dl>");
      sb.append("<dt><b>");
      node.term.jjtAccept(this, null);
      sb.append("</b></dt><dd>");
      node.def.jjtAccept(this, null);
      sb.appendln("</dd>");
      sb.appendln("</dl>");
      return data;
   }

   public Object visit(ASTPlainText node, Object data)
   {
      String output = node.toString();
      output = replaceSpecial(output);
      output = replaceBold(output);
      output = replaceItalics(output);
      output = replaceMono(output);
      sb.append(output);
      return data;
   }

   public Object visit(ASTLink node, Object data)
   {
      String text = node.getText();
      text = replaceSpecial(text);
      text = replaceBold(text);
      text = replaceItalics(text);
      text = replaceMono(text);

      String link = node.getLinkText();
      if (link == null)
      {
         link = wikiUrl + java.net.URLEncoder.encode(text);
      }

      sb.append("<a href=\"" + link + "\">" + text + "</a>");
      return data;
   }

   public Object visit(ASTFootnoteRef node, Object data)
   {
      return data;
   }

   public Object visit(ASTFootnote node, Object data)
   {
      return data;
   }

   public Object visit(ASTAnything node, Object data)
   {
      if (node.text == null) return data;
      sb.append("<pre>");
      String output = replaceSpecial(node.text);
      sb.append(output);
      sb.append("</pre>");
      return data;
   }

   public Object visit(ASTText node, Object data)
   {
      data = node.childrenAccept(this, data);
      return data;
   }

   /**
    * Outputs the result of parsing the wiki text.
    */
   public String eject() {
       return sb.toString();
   }

   public static String convertToHtml(String input) throws ParseException {

       if (w == null)
           w = new wiki(new StringReader(input));
       else
           w.ReInit(new StringReader(input));

       ASTStart n = w.Start();
       Wiki2Html v = new Wiki2Html();
       n.jjtAccept(v, null);
       return v.eject();
   }

   public static void main(String args[])
   {
      //System.err.println("Reading from file: " + args[0]);
      try
      {
         File io = new File(args[0]);
         FileInputStream is = new FileInputStream(io);
         wiki t = new wiki(is);
         ASTStart n = t.Start();
         Wiki2Html v = new Wiki2Html();
         n.jjtAccept(v, null);
         is.close();
         System.out.println(v.eject());
      }
      catch (Exception e)
      {
         System.err.println("Oops.");
         e.printStackTrace();
         usage();
      }
   }

   public static void usage()
   {
      System.err.println();
      System.err.println("Usage: Wiki2Html <wiki file>");
   }

}

/*end*/
