/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.ed.wimo.stix.view.presentation;

import uk.ac.ed.wimo.stix.log.Entry;

/**
 *
 * @author alex
 */
public class TablePresenter extends Presenter {

    @Override
    public String present() {
        StringBuilder sb = new StringBuilder();

        // Get a list of columns returned by the query
        String[] columns = new String[]{};
        if (entries.size() > 0) {
            columns = entries.get(0).getFieldNames();
        }

        sb.append("<table><tr>");

        for (String col : columns) {
            sb.append("<th>");
            sb.append(col);
            sb.append("</th>");
        }

        sb.append("</tr>");

        for (Entry e : entries) {
            sb.append("<tr>");
            for (String col : columns) {
                sb.append("<td>");
                sb.append(e.getField(col));
                sb.append("</td>");
            }
            sb.append("</tr>");
        }

        sb.append("</table>");

        return sb.toString();
    }
}
