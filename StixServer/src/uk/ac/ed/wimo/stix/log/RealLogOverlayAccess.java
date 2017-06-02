/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.ed.wimo.stix.log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import uk.ac.ed.wimo.stix.log.exception.MalformedQueryException;
import org.w3c.dom.*;

/**
 *
 * @author alex
 */
public class RealLogOverlayAccess implements LogOverlayAccess {

    private static RealLogOverlayAccess _instance = new RealLogOverlayAccess();

    public static RealLogOverlayAccess getInstance() {
        return _instance;
    }

    private static void log(String msg) {
        Logger.getLogger(RealLogOverlayAccess.class.getName()).log(Level.INFO, msg);
    }

    public List<Entry> getLogEntries(Agent agent, String innerQuery) {

        String sqlQuery;

        List<Entry> entries = null;

        try {
            sqlQuery = FakeLogOverlayAccess.mangleQuery(agent.getName(), innerQuery);
        } catch (MalformedQueryException ex) {
            Logger.getLogger(RealLogOverlayAccess.class.getName()).log(Level.SEVERE, null, ex);
            // We cannot return any entries if the query was malformed.
            return new ArrayList<Entry>();
        }

        String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<Message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.wimo.inf.ed.ac.uk/code/stix\" xsi:schemaLocation=\"http://www.wimo.inf.ed.ac.uk/code/stix Message.xsd\">"
                + "<LogQuery><query>" + sqlQuery + "</query></LogQuery>"
                + "</Message>\n\nEOF\n\n";

        log("Sending message: " + message);

        String response = null;

        // How deep we should try to look for an active neighbor.
        int k = 2;
        
        // Tracks agents which have been tried.
        Set<Agent> agentsTried = new HashSet<Agent>();
        // Tracks agents which are pending to try.
        List<Agent> agentsToTry = new ArrayList<Agent>();
        // Tracks what k is for each agent, so as to stop recursing when we are too far from the source.
        Map<Agent, Integer> agentDistances = new HashMap<Agent, Integer>();
        // The only agent to try initially is the agent we wish to query about.
        agentsToTry.add(agent);
        agentDistances.put(agent, 0);

        while (agentsToTry.size() > 0) {
            Agent currentAgent = agentsToTry.remove(0);
            Integer currentDistance = agentDistances.get(agent);

            log("Trying agent " + currentAgent.getName() + "regarding " + agent.getName());
            try {

                response = queryAgent(currentAgent, message);
                log("Received response: " + response);
                entries = parseEntriesFromXml(response);
                log("Communication with " + currentAgent.getName() + "regarding " + agent.getName() + " succeeded");
                break;

            } catch (Exception ex) {
                Logger.getLogger(RealLogOverlayAccess.class.getName()).log(Level.SEVERE, null, ex);
                log("Couldn't access agent " + currentAgent.getName() + " regarding " + agent.getName());

                // If we're not too far away...
                if (currentDistance < k) {
                    // For all neighbours of the current agent...
                    for (Agent neighbour : currentAgent.getNeighbours()) {
                        // If we haven't seen the neighbour already...
                        if (!agentDistances.containsKey(neighbour)) {
                            agentsToTry.add(neighbour);
                            agentDistances.put(neighbour, currentDistance+1);
                        }
                    }
                }
            }
        }

        if (entries == null) {
            // We must have exhausted available neighbours.
            log("Failed to communicate with any nodes regarding " + agent.getName());
            return new ArrayList<Entry>();
        }

        return entries;

    }

    private static String queryAgent(Agent agent, String message) throws SocketTimeoutException, UnknownHostException, IOException {

        Socket socket = null;

        StringBuilder response = new StringBuilder();

        try {
            
            socket = new Socket(agent.getAddress(), agent.getPort());
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            socket.setSoTimeout(5000);

            // Write message
            BufferedOutputStream bos = new BufferedOutputStream(os);
            bos.write(message.getBytes());
            bos.flush();

            // Read until remote closes connection
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String responseLine;
            log("Starting read");
            while ((responseLine = br.readLine()) != null) {
                log("br.readLine() got: " + responseLine);
                response.append(responseLine);
            }

            bos.close();

        } finally {

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    Logger.getLogger(RealLogOverlayAccess.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return response.toString();

    }

    public static void removeWhitespaceNodes(Element e) {
        NodeList children = e.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
                e.removeChild(child);
            } else if (child instanceof Element) {
                removeWhitespaceNodes((Element) child);
            }
        }
    }

    private List<Entry> parseEntriesFromXml(String response) throws Exception {

        List<Entry> entries = new ArrayList<Entry>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            byte[] responseBytes = response.getBytes("UTF-8");
            Document doc = docBuilder.parse(new ByteArrayInputStream(responseBytes));
            removeWhitespaceNodes(doc.getDocumentElement());

            Node resultsNode = doc.getDocumentElement().getFirstChild().getFirstChild();
            Logger.getLogger(RealLogOverlayAccess.class.getName()).log(Level.INFO, "Descended to " + resultsNode.getNodeName());
            if (!resultsNode.getNodeName().equalsIgnoreCase("results")) {
                Logger.getLogger(RealLogOverlayAccess.class.getName()).log(Level.SEVERE, "Couldn't find results node in XML.");
                return entries;
            }

            NodeList logEntries = resultsNode.getChildNodes();
            log(logEntries.getLength() + " results.");

            for (int i = 0; i < logEntries.getLength(); i++) {
                Element e = (Element) logEntries.item(i);
                entries.add(Entry.fromXml(e));
            }

        } catch (Exception e) {
            // lolbored
            Logger.getLogger(RealLogOverlayAccess.class.getName()).log(Level.SEVERE, "Parsing XML failed.", e);
            throw e;
        }

        return entries;
    }
}
