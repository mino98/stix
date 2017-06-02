/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.log;

import java.util.List;

/**
 *
 * @author alex
 */
public interface LogOverlayAccess {

    List<Entry> getLogEntries(Agent agent, String innerQuery);

}
