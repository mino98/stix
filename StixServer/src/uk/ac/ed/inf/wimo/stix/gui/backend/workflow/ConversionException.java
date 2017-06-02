/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.inf.wimo.stix.gui.backend.workflow;

import uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.PoolComplexTypeImpl;

/**
 *
 * @author alex
 */
public class ConversionException extends Exception {

    static ConversionException UnrecognisedChildOf(Object parent, Object child) {
        return new ConversionException("Cannot convert JSON to Workflow: " + parent.getClass().getSimpleName() + " does not know how to handle a " + child.getClass().getSimpleName());
    }

    public ConversionException(String message) {
        super(message);
    }

}
