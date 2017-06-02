/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.ed.wimo.stix.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uk.ac.ed.wimo.stix.log.exception.MalformedQueryException;

/**
 * A fake class from which we can magically procure log entries until the actual log overlay is designed.
 * @author alex
 */
public class FakeLogOverlayAccess implements LogOverlayAccess {

    private static final String TABLE_NAME = "log";
    private Connection conn = null;
    private static FakeLogOverlayAccess _instance = new FakeLogOverlayAccess();

    public static FakeLogOverlayAccess getInstance() {
        return _instance;
    }

    private FakeLogOverlayAccess() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.sqlite.db");
        } catch (Exception ex) {
            Logger.getLogger(FakeLogOverlayAccess.class.getName()).log(Level.SEVERE, "Cannot initialise fake log overlay database.", ex);
            System.exit(1);
        }
    }

    public List<Entry> getLogEntries(Agent agent, String innerQuery) {
        List<Entry> entries = new ArrayList<Entry>();
        String sqlquery;
        try {
            sqlquery = mangleQuery(agent.getName(), innerQuery);
        } catch (MalformedQueryException ex) {
            Logger.getLogger(FakeLogOverlayAccess.class.getName()).log(Level.SEVERE, null, ex);
            // We cannot return any entries if the query was malformed.
            return entries;
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlquery);
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next()) {
                Entry e = new Entry();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    e.setField(md.getColumnName(i), rs.getString(i));
                }
                entries.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FakeLogOverlayAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        // A small delay here simulates the latency of accessing a distributed network.
        try {
            Thread.sleep((long) (500 + Math.random() * 500));
        } catch (InterruptedException ex) { }

        return entries;
    }

    /**
     * Takes an inner log overlay query and fudges it into a valid SQL query that can be run on the fake log table.
     * <br/><br/>
     * This function is only public to allow testing.
     * @param query The valid log overlay query.
     * @return The fudged SQL query.
     */
    public static String mangleQuery(String agentName, String query) throws MalformedQueryException {
        /*
        We want to break down the query into up to four clauses:
        SELECT something
        WHERE something
        ORDER BY something
        LIMIT something
        
        Before making the regex more amenable to whitespacing, and avoiding
        capturing keywords, it looks like this:
        SELECT (.*?) (WHERE .*?|) (ORDER BY .*?|) (LIMIT .*?|);?
        
        Note the query does not validate the syntax of individual clauses (ie.
        ensuring that the LIMIT is an integer) -- that is neglected for the
        SQL engine to deal with.
         */

        String sqlParseRegex = "\\A\\s*SELECT (.*?)\\s*(?:WHERE (.*?)|)\\s*(?:ORDER BY (.*?)|)\\s*(?:LIMIT (.*?)|)\\s*;?\\s*\\Z";

        // Parse.

        Pattern p = Pattern.compile(sqlParseRegex, Pattern.DOTALL);
        Matcher m = p.matcher(query);
        if (!m.find()) {
            throw new MalformedQueryException("Could not parse inner query: " + query);
        }

        // Log the parsed elements.

        Logger.getLogger(FakeLogOverlayAccess.class.getName()).log(Level.FINER, "Matched: " + m.group(0));
        Logger.getLogger(FakeLogOverlayAccess.class.getName()).log(Level.FINE, "SELECT: " + m.group(1));
        Logger.getLogger(FakeLogOverlayAccess.class.getName()).log(Level.FINE, "WHERE: " + m.group(2));
        Logger.getLogger(FakeLogOverlayAccess.class.getName()).log(Level.FINE, "ORDER BY: " + m.group(3));
        Logger.getLogger(FakeLogOverlayAccess.class.getName()).log(Level.FINE, "LIMIT: " + m.group(4));

        // Build the new SQL string.

        StringBuilder sb = new StringBuilder();

        // SELECT
        sb.append("SELECT ");
        sb.append(m.group(1));

        // FROM
        sb.append(" FROM ");
        sb.append(TABLE_NAME);

        // WHERE
        // Even if the inner query doesn't have a WHERE clause, we need one in
        // order to match only records for this agent.
        String thisAgentOnly = "agent_id = '" + agentName + "'";

        if (m.group(2) != null) {
            sb.append(" WHERE (");
            sb.append(m.group(2));
            // Also match only this agent.
            sb.append(") AND " + thisAgentOnly);
        } else {
            // At least match only this agent.
            sb.append(" WHERE " + thisAgentOnly);
        }

        // ORDER BY
        if (m.group(3) != null) {
            sb.append(" ORDER BY ");
            sb.append(m.group(3));
        }

        // LIMIT
        if (m.group(4) != null) {
            sb.append(" LIMIT ");
            sb.append(m.group(4));
        }

        Logger.getLogger(FakeLogOverlayAccess.class.getName()).log(Level.INFO, "SQL query: " + sb.toString());
        return sb.toString();

    }
}
