/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.log.exception;

/**
 *
 * @author alex
 */
public class UnresolvableQueryException extends Exception {

    public UnresolvableQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnresolvableQueryException(String message) {
        super(message);
    }

    
}
