/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.log;

import java.util.ArrayList;
import java.util.List;
import uk.ac.ed.wimo.stix.log.exception.AgentNotFoundException;
import uk.ac.ed.wimo.stix.log.exception.AgentNameConflictException;
import java.util.HashMap;
import java.util.Map;

/**
 * A thread-safe wrapper around a map allowing agents to be registered and looked up by name.
 * @author alex
 */
public class AgentRegistry {
    
    private static Map<String, Agent> agents = new HashMap<String, Agent>();

    public static synchronized Agent findAgent(String agentName) throws AgentNotFoundException {
        if (agents.containsKey(agentName))
            return agents.get(agentName);
        else
            throw new AgentNotFoundException();
    }

    public static synchronized void registerAgent(Agent agent) throws AgentNameConflictException {
        if (agents.containsKey(agent.getName()))
            throw new AgentNameConflictException("An agent with this name is already registered.");
        else
            agents.put(agent.getName(), agent);
    }

    static List<Agent> allAgents() {
        return new ArrayList<Agent>(agents.values());
    }

}
