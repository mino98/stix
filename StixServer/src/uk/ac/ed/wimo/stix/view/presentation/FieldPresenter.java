/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.view.presentation;

/**
 * The field presenter just returns the first field it encounters in the
 * entryset. (The query should be designed such that it only returns one
 * row with one column)
 * @author alex
 */
public class FieldPresenter extends Presenter {
    @Override
    public String present() {
        return entries.get(0).getField(0);
    }
}
