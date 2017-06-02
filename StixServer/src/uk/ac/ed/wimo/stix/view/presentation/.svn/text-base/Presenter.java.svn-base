/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.view.presentation;

import java.util.ArrayList;
import java.util.List;
import uk.ac.ed.wimo.stix.log.Entry;

/**
 *
 * @author alex
 */
public abstract class Presenter {
    protected List<Entry> entries;

    public Presenter() {
        entries = new ArrayList<Entry>();
    }

    public String present(List<Entry> entries) {
        this.entries = entries;
        return present();
    }

    public String present() {
        throw new AbstractMethodError();
    }
}
