/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.log;

import uk.ac.ed.wimo.stix.log.exception.AgentNotFoundException;
import uk.ac.ed.wimo.stix.log.exception.MalformedQueryException;
import uk.ac.ed.wimo.stix.log.exception.UnresolvableQueryException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * QueryResolver.resolveQuery(query) takes an SQL-like log overlay query and performs the task of communicating with the agents to procure matching log entries. See {@link QueryResolver#resolveQuery }.
 * @author alex
 */
public class QueryResolver {

    /**
     * Resolves an SQL-like log overlay query by extracting the list of agents and forwarding the inner query to each matched agent (or all agents if the list of agents is '*').
     * <br/><br/>
     * <b>NOTE</b>: A query passed to the QueryResolver should never carry a PRESENT AS clause. The PRESENT AS clause is only relevant to the {@link uk.ac.ed.wimo.stix.view.Renderer}, not the QueryResolver.
     * @param query The log overlay query.
     * @return A list of Entry objects from the log overlay that match the query.
     * @throws MalformedQueryException
     * @throws UnresolvableQueryException
     */
    public List<Entry> resolveQuery(String query) throws MalformedQueryException, UnresolvableQueryException {
        List<Agent> agents = getAgents(extractListOfAgents(query));
        List<Entry> entries = new ArrayList<Entry>();

        String innerQuery = extractInnerQuery(query);

        for (Agent agent : agents) {
            entries.addAll(agent.resolveQuery(innerQuery));
        }

        return entries;
    }

    /**
     * Transforms a string representing a list of agents into a list of {@link Agent} objects.
     * @param listOfAgents the list-of-agents term from a log overlay query.
     * @return A list of {@link Agent} objects.
     * @throws UnresolvableQueryException
     */
    private List<Agent> getAgents(String listOfAgents) throws UnresolvableQueryException {
        // If the list-of-agents term is the wildcard, return all agents.
        if (listOfAgents.equals("*"))
            return AgentRegistry.allAgents();

        List<Agent> agents = new ArrayList<Agent>();
        String[] agentnames = listOfAgents.split("[\\s,]+");

        for (String agentName : agentnames) {
            try {
                agents.add(AgentRegistry.findAgent(agentName));
            } catch (AgentNotFoundException ex) {
                throw new UnresolvableQueryException("Agent not known to registry: " + agentName);
            }
        }

        return agents;
    }

    /**
     * Extracts the list-of-agents term from a log overlay query.
     * @param query The log overlay query
     * @return A substring of the query representing the list of agents that the query is directed to.
     * @throws MalformedQueryException if a list-of-agents term cannot be found in the query string.
     */
    private String extractListOfAgents(String query) throws MalformedQueryException {
        String matchListOfAgentsRegex = "\\A\\s*ON\\s+(\\*|[\\w\\s,]+?)\\s+SELECT";
        Pattern p = Pattern.compile(matchListOfAgentsRegex);
        Matcher m = p.matcher(query);
        if (!m.find())
            throw new MalformedQueryException("Could not find list-of-agents in query: "  + query);
        String match = m.group(1);
        Logger.getLogger(QueryResolver.class.getName()).log(Level.FINER, "extractListOfAgents matched: " + match);
        return match;
    }

    /**
     * Extracts the part of the query that is sent to each agent (ie, everything starting from SELECT)
     * @param query The log overlay query
     * @return A substring of the query representing the query that is processed by each individual agent.
     * @throws MalformedQueryException if a valid inner query cannot be found in the query string.
     */
    private String extractInnerQuery(String query) throws MalformedQueryException {
        String matchInnerQueryRegex = "(SELECT .+?)\\s*\\Z";
        Pattern p = Pattern.compile(matchInnerQueryRegex, Pattern.DOTALL);
        Matcher m = p.matcher(query);
        if (!m.find())
            throw new MalformedQueryException("Could not find inner query in query: " + query);
        String match = m.group(1);
        Logger.getLogger(QueryResolver.class.getName()).log(Level.FINER, "extractInnerQuery matched: " + match);
        return match;
    }
}
