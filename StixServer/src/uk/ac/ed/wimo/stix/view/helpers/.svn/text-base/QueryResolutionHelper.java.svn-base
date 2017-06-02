/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.ed.wimo.stix.view.helpers;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uk.ac.ed.wimo.stix.log.*;
import uk.ac.ed.wimo.stix.log.exception.*;
import uk.ac.ed.wimo.stix.view.presentation.BarGraphPresenter;
import uk.ac.ed.wimo.stix.view.presentation.FieldPresenter;
import uk.ac.ed.wimo.stix.view.presentation.LineGraphPresenter;
import uk.ac.ed.wimo.stix.view.presentation.PieGraphPresenter;
import uk.ac.ed.wimo.stix.view.presentation.Presenter;
import uk.ac.ed.wimo.stix.view.presentation.TablePresenter;

/**
 * JSP helper for resolving queries.
 * @author alex
 */
public class QueryResolutionHelper {

    private static QueryResolver qr = null;

    public static List<Entry> resolveQuery(String query) throws MalformedQueryException, UnresolvableQueryException {
        if (qr == null) {
            try {

                // Create agents
                Agent a = new Agent("sitea", InetAddress.getByName("192.168.2.101"), 9999);
                Agent b = new Agent("siteb", InetAddress.getByName("192.168.2.102"), 9999);
                Agent c = new Agent("sitec", InetAddress.getByName("192.168.2.103"), 9999);
                Agent d = new Agent("sited", InetAddress.getByName("192.168.2.104"), 9999);
                Agent e = new Agent("sitee", InetAddress.getByName("192.168.2.105"), 9999);

                // Build relationships
                a.addNeighbour(b);
                b.addNeighbour(a); b.addNeighbour(e); b.addNeighbour(c);
                c.addNeighbour(b); c.addNeighbour(d);
                d.addNeighbour(c); d.addNeighbour(e);
                e.addNeighbour(b); e.addNeighbour(d);

                // Register
                AgentRegistry.registerAgent(a);
                AgentRegistry.registerAgent(b);
                AgentRegistry.registerAgent(c);
                AgentRegistry.registerAgent(d);
                AgentRegistry.registerAgent(e);

            } catch (AgentNameConflictException ex) {
                Logger.getLogger(QueryResolutionHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(QueryResolutionHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            qr = new QueryResolver();

        }

        return qr.resolveQuery(query);
    }

    /**
     * Takes a query that may have a PRESENT AS clause and either returns the
     * presenter specified in the PRESENT AS clause or the default TABLE presenter.
     * @param query
     * @return
     */
    public static Presenter getPresenter(String query) throws MalformedQueryException {
        String present_as_clause = extractPresentation(query);
        if (present_as_clause.equalsIgnoreCase("FIELD"))
            return new FieldPresenter();
        else if (present_as_clause.equalsIgnoreCase("LINEGRAPH"))
            return new LineGraphPresenter();
        else if (present_as_clause.equalsIgnoreCase("BARGRAPH"))
            return new BarGraphPresenter();
        else if (present_as_clause.equalsIgnoreCase("PIEGRAPH"))
            return new PieGraphPresenter();
        else if (present_as_clause.equalsIgnoreCase("TABLE"))
            return new TablePresenter();
        else
            throw new MalformedQueryException("Unrecognised presentation style.");
    }

    /** Takes a query that may have a PRESENT AS clause and returns the contents of
     * the PRESENT AS clause, or "TABLE" if none is present.
     * @param query
     * @return
     */
    public static String extractPresentation(String query) {
        String matchPresentClauseRegex = "PRESENT AS\\s+(.+?)\\s*\\Z";
        Pattern p = Pattern.compile(matchPresentClauseRegex, Pattern.DOTALL);
        Matcher m = p.matcher(query);
        if (!m.find())
            return "TABLE";
        String match = m.group(1);
        Logger.getLogger(QueryResolver.class.getName()).log(Level.FINER, "extractPresentation matched: " + match);
        return match;
    }

    /**
     * Takes a query that may have a PRESENT AS clause and returns the query
     * without the PRESENT AS clause.
     * @param query
     * @return
     */
    public static String extractQuery(String query) {
        String matchQueryRegex = "\\A\\s*(.+?)\\s+PRESENT AS";
        Pattern p = Pattern.compile(matchQueryRegex, Pattern.DOTALL);
        Matcher m = p.matcher(query);
        if (!m.find())
            return query;
        String match = m.group(1);
        Logger.getLogger(QueryResolver.class.getName()).log(Level.FINER, "extractQuery matched: " + match);
        return match;
    }
}
