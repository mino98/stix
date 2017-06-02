/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.view.storage;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.HashMap;

/**
 * NOTE: Thread-safety is only guaranteed for CRUD operations, not for bootstrap functions ({@link #createAndPopulate} and {@link #insertExampleData}).
 * @author alex
 */
public class SQLiteDocumentStore implements DocumentStore {
    private Connection conn;
    
    public SQLiteDocumentStore() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLiteDocumentStore.class.getName()).log(Level.SEVERE, "Sqlite JDBC class not available.", ex);
        }
        conn = DriverManager.getConnection("jdbc:sqlite:/var/stix/perspectives.db");
    }

    public synchronized void createDocument(String name, String contents) throws DocumentStoreException {
        // Ensure that the document doesn't already exist.
        // TODO: A literal SQL query (ie. checkDocumentExists(name)) would be cleaner.
        try {
            retrieveDocument(name);
            throw new DocumentAlreadyExistsException("Document already exists.");
        } catch (DocumentDoesNotExistException ex) {
            // Good.
        }

        try {
            PreparedStatement prep = conn.prepareStatement(
                    "INSERT INTO viewdocuments (name, content) VALUES (?, ?);");
            prep.setString(1, name);
            prep.setString(2, contents);
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw loggedDocumentStoreException(ex, "Couldn't create document in the document store.");
        }
    }

    public String retrieveDocument(String name) throws DocumentStoreException {
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM viewdocuments WHERE name = ?;");
            prep.setString(1, name);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                return rs.getString("content");
            } else {
                throw new DocumentDoesNotExistException("Document does not exist.");
            }
        } catch (SQLException ex) {
            throw loggedDocumentStoreException(ex, "Couldn't retrieve document from the document store.");
        }

    }

    public synchronized void updateDocument(Integer id, String contents) throws DocumentStoreException {
        // Ensure document exists already.
        retrieveDocument(id);

        try {
            PreparedStatement prep = conn.prepareStatement("UPDATE viewdocuments SET content = ? WHERE id = ?");
            prep.setString(1, contents);
            prep.setInt(2, id);
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw loggedDocumentStoreException(ex, "Couldn't update document in the document store.");
        }
    }

    public synchronized void renameDocument(Integer id, String name) throws DocumentStoreException {
        // Ensure document exists already.
        retrieveDocument(id);

        try {
            PreparedStatement prep = conn.prepareStatement("UPDATE viewdocuments SET name = ? WHERE id = ?");
            prep.setString(1, name);
            prep.setInt(2, id);
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw loggedDocumentStoreException(ex, "Couldn't rename document in the document store.");
        }
    }

    
    public synchronized void deleteDocument(Integer id) throws DocumentStoreException {
        // Ensure document exists already.
        retrieveDocument(id);
        
        try {
            PreparedStatement prep = conn.prepareStatement("DELETE FROM viewdocuments WHERE id = ?;");
            prep.setInt(1, id);
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw loggedDocumentStoreException(ex, "Couldn't delete document from the document store.");
        }
    }

    /**
     * Creates the document store table.
     */
    public void createDatabase() {
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate("DROP TABLE IF EXISTS viewdocuments;");
            stat.executeUpdate("CREATE TABLE viewdocuments (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "content TEXT" +
                    ");");
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteDocumentStore.class.getName()).log(Level.SEVERE, "Exception while creating viewdocuments table.", ex);
        }

    }

    /**
     * Puts some pretend documents in the document store table.
     */
    public void insertExampleData() {
        try {
            PreparedStatement prep = conn.prepareStatement(
                    "INSERT INTO viewdocuments (name, content) VALUES (?, ?)");
            prep.setString(1, "example1");
            prep.setString(2, "! Example Document 1\n\nHere is an example **document**.");
            prep.addBatch();
            prep.setString(1, "example2");
            prep.setString(2, "! Another Example Document\n\nThis is __example__ document 2.");
            prep.addBatch();
            prep.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteDocumentStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void createAndPopulate() {
        createDatabase();
        insertExampleData();
    }

    private DocumentStoreException loggedDocumentStoreException(SQLException ex, String message) {
        Logger.getLogger(SQLiteDocumentStore.class.getName()).log(Level.SEVERE, message, ex);
        return new DocumentStoreException(message, ex);            

    }

    public Map<Integer, String> getDocuments() throws DocumentStoreException {
        Map<Integer, String> documents = new HashMap<Integer, String>();

        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM viewdocuments;");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                documents.put(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException ex) {
            throw loggedDocumentStoreException(ex, "Couldn't retrieve documents from the document store.");
        }
        return documents;
    }

    public String retrieveDocument(Integer id) throws DocumentStoreException {
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM viewdocuments WHERE id = ?;");
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                return rs.getString("content");
            } else {
                throw new DocumentDoesNotExistException("Document does not exist.");
            }
        } catch (SQLException ex) {
            throw loggedDocumentStoreException(ex, "Couldn't retrieve document from the document store.");
        }
    }

    public String getDocumentName(Integer id) throws DocumentStoreException {
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT name FROM viewdocuments WHERE id = ?;");
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            } else {
                throw new DocumentDoesNotExistException("Document does not exist.");
            }
        } catch (SQLException ex) {
            throw loggedDocumentStoreException(ex, "Couldn't get document name from the document store.");
        }

    }
}
