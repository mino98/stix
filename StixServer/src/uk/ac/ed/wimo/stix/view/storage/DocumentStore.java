/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.view.storage;

import java.util.Map;

/**
 *
 * @author alex
 */
public interface DocumentStore {

    void createDocument(String name, String contents) throws DocumentStoreException;

    void deleteDocument(Integer id) throws DocumentStoreException;

    String retrieveDocument(String name) throws DocumentStoreException;

    String retrieveDocument(Integer id) throws DocumentStoreException;

    String getDocumentName(Integer id) throws DocumentStoreException;

    void updateDocument(Integer id, String contents) throws DocumentStoreException;

    void renameDocument(Integer id, String name) throws DocumentStoreException;

    Map<Integer, String> getDocuments() throws DocumentStoreException;

    void createAndPopulate();

}
