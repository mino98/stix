/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.log;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author alex
 */
public class Agent {
    private String name;
    private InetAddress address;
    private int port;
    private Set<Agent> neighbours;

    public Agent(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
        this.neighbours = new HashSet<Agent>();
    }

    public Agent(String name, InetAddress address) {
        this(name, address, 9999);
    }

    public String getName() {
        return name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public Set<Agent> getNeighbours() {
        return new HashSet<Agent>(neighbours);
    }

    public void addNeighbour(Agent agent) {
        neighbours.add(agent);
    }

    List<Entry> resolveQuery(String query) {
        // TODO: Here we will eventually communicate with the network,
        // however for now we just perform the operation by fudging the
        // query into an actual SQL query and running it against a fake
        // table held locally.
        LogOverlayAccess overlay = RealLogOverlayAccess.getInstance();
        return overlay.getLogEntries(this, query);
    }
}
