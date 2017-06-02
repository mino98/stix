/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.view.helpers;

import java.sql.SQLException;
import uk.ac.ed.wimo.stix.view.storage.*;

/**
 * JSP helper for getting access to the datastore.
 * @author alex
 */
public class DocumentStoreHelper {

    private static DocumentStore ds;

    public static DocumentStore getDocumentStore() throws SQLException {
        if (ds == null)
            ds = new SQLiteDocumentStore();

        return ds;
    }
}
