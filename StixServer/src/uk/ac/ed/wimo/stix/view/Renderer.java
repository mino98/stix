/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.view;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uk.ac.ed.wimo.stix.view.wiki.ParseException;
import uk.ac.ed.wimo.stix.view.wiki.Wiki2Html;

/**
 * The Renderer's render() function transforms a WikiText input document into
 * a HTML document containing placeholders for run-time queries. The document
 * will then be sent to the client, which will make AJAX calls to populate the
 * placeholders.
 * @author alex
 */
public class Renderer {
    private int deferredScriptCounter;

    public Renderer() {
        deferredScriptCounter = 0;
    }

    public String render(String input) {
        String buf = input;
        
        // Convert the document to HTML.
        try {
            // The parser requires that the input terminates with a newline.
            buf = Wiki2Html.convertToHtml(buf + "\n");
        } catch (ParseException ex) {
            Logger.getLogger(Renderer.class.getName()).log(Level.SEVERE, null, ex);
            return "Error rendering: " + ex.toString();
        }

        // Convert StixView queries to HTML/JS placeholders.
        buf = convertQueriesToDeferredQueries(buf);

        return buf;

    }

    /**
     * Locates all StixView queries in the input string and transforms them to HTML
     * placeholders and javascriptlets for the browser to request the query at
     * display-time.
     * @param input The input document containing zero or more StixView queries
     * @return The document with HTML/JS query placeholders
     */
    private String convertQueriesToDeferredQueries(String input) {
        // Find instances of {: ... :} tags and replace them with skeletons and javascripts.
        String r = "\\{:\\s*([\\w\\s\\*\\-,'\"=;]+?)\\s*:\\}";
        System.out.println("Pattern: " + r);
        Pattern p = Pattern.compile(r);

        StringBuffer sb = new StringBuffer();
        Matcher m = p.matcher(input);
        while (m.find()) {
            String query = m.group(1);
            String deferScript = generateDeferScript(query);
            m.appendReplacement(sb, deferScript);
        }
        m.appendTail(sb);

        return sb.toString();
    }

    /**
     * Generates a HTML placeholder and deferred query scriptlet based on the
     * query's presentation mode as specified by the PRESENT AS clause.
     * @param query
     * @return
     */
    private String generateDeferScript(String query) {
        deferredScriptCounter++;

        // If the query is a FIELD it needs to be placeholded in a span.
        // Otherwise the query should be placeholded with a div.

        String placeholderTag = "div";
        if (query.matches("[\\s\\S]*PRESENT\\s+AS\\s+FIELD\\s*\\Z"))
            placeholderTag = "span";

        String js_safe_query = query.replace("'", "\\\\\\\'").replace("\n", "\\\\\\n");

        return "<script>" + "\n" +
                // "   queries = queries || {};" + "\n" +
                "   queries.push([" + "\n" +
                "       '#placeholder"+deferredScriptCounter+"'," + "\n" +
                "       '"+js_safe_query+"'" + "\n" +
                "   ]);" + "\n" +
                "</script>" + "\n" +
                "<"+placeholderTag+" id='placeholder"+deferredScriptCounter+"' class='placeholder'>" + "\n" +
                "   Resolving query..." + "\n" +
                "</"+placeholderTag+">";
    }
}
