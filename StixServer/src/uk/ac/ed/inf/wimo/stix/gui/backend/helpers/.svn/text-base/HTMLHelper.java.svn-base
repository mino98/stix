/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.inf.wimo.stix.gui.backend.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author alex
 */
public class HTMLHelper {

    // 2009-01-01T09:00:00
    private static SimpleDateFormat datefmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public String safe (String inp) {
        return inp
                .replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("\'", "&#39;")
                .replace(">", "&gt;")
                .replace("<", "&lt;")
                ;
    }

    public String calendarToString (Calendar c) {
        if (c == null) {
            return "";
        }
        return datefmt.format(c.getTime());
    }

    public Calendar stringToCalendar (String s) {
        if (s.trim().length() == 0)
            return null;
        Calendar c = Calendar.getInstance();
        Date d = null;
        try {
            d = datefmt.parse(s);
        } catch (ParseException ex) {
            return null;
        }
        c.setTime(d);
        return c;
    }
}
