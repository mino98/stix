/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.view.wiki;

/**
 *
 * @author alex
 */
class MyStringBuilder {

    private StringBuilder sb;

    protected MyStringBuilder () {
        sb = new StringBuilder();
    }

    protected void appendln(String str) {
        sb.append(str + "\n");
    }

    protected void append(String str) {
        sb.append(str);
    }

    @Override
    public String toString() {
        return sb.toString();
    }

}
