/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.*;

/**
 * Represents a single log entry in the log overlay. A LinkedHashMap is used in
 * order to preserve the order of the columns as returned by the log overlay when
 * rendering the entry in a table.
 * @author alex
 */
public class Entry {

    private LinkedHashMap<String, String> data;

    public Entry() {
        data = new LinkedHashMap<String, String>();
    }

    public static Entry fromXml(Element element) {
        Entry entry = new Entry();

        /*
         *      + "<AGENT_ID>agent2</AGENT_ID>"
                + "<WORKFLOW_ID>9871239871</WORKFLOW_ID>"
                + "<NAME>name</NAME>"
                + "<VALUE>value1</VALUE>"
                + "<TIMESTAMP>1264523449986</TIMESTAMP>"
                + "<MESSAGE_ID>-1</MESSAGE_ID>"
                + "<J>-1</J>"
                + "<K>-1</K>"
         */

        NodeList fields = element.getChildNodes();
        for (int i = 0; i < fields.getLength(); i++) {
            Node field = fields.item(i);
            entry.setField(field.getNodeName(), field.getFirstChild().getNodeValue());
        }

        return entry;
    }

    public void setField(String name, String value) {
        data.put(name, value);
    }

    public String getField(String name) {
        return data.get(name);
    }

    public String getField(Integer index) {
        if (data.size() == 0)
            throw new IndexOutOfBoundsException();

        int i = 0;
        for (String value : data.values())
            if (i++ == index)
                return value;

        throw new IndexOutOfBoundsException();
    }

    public String[] getFieldNames() {
        return data.keySet().toArray(new String[data.size()]);
    }

    public Map<String, String> getAll() {
        return new HashMap<String, String>(data);
    }

    @Override
    public String toString() {
        return String.format("Entry: %s -> %s",
                joinStringCollection(data.keySet(), ","),
                joinStringCollection(data.values(), ","));
    }

    private static String joinStringCollection(Collection s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }



}
