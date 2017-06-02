/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.x13n.stixtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.ed.wimo.stix.log.Agent;
import uk.ac.ed.wimo.stix.log.AgentRegistry;
import uk.ac.ed.wimo.stix.log.Entry;
import uk.ac.ed.wimo.stix.log.QueryResolver;
import uk.ac.ed.wimo.stix.log.exception.AgentNameConflictException;
import uk.ac.ed.wimo.stix.log.exception.MalformedQueryException;
import uk.ac.ed.wimo.stix.log.exception.UnresolvableQueryException;
import uk.ac.ed.wimo.stix.log.FakeLogOverlayAccess;
import uk.ac.ed.wimo.stix.view.Renderer;
import uk.ac.ed.wimo.stix.view.storage.DocumentStore;
import uk.ac.ed.wimo.stix.view.storage.DocumentStoreException;
import uk.ac.ed.wimo.stix.view.storage.SQLiteDocumentStore;

/**
 *
 * @author alex
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        //DocumentStore ds = new SQLiteDocumentStore();
        //initialiseAndPopulateDocumenetStore(ds);

        registerSomeAgents();
        testQueryResolution();

    }

    private static void testQueryResolution() {
        // Get all entries.
        resolveQuery("ON * SELECT *");

        // Get all SNRs for siteOne
        resolveQuery("ON siteOne SELECT value, timestamp WHERE name='SNR' AND workflowid = '1000' ORDER BY timestamp ASC");

        // Get the most recent SNR from all sites.
        resolveQuery("ON * SELECT agentId, value WHERE name='SNR' ORDER BY timestamp DESC LIMIT 1;");

        // Get all upgrade messages
        resolveQuery("ON * SELECT agentId, value, timestamp WHERE name = 'UpgradeMessage';");
    }

    private static void resolveQuery(String query) {
        System.out.println("Query: " + query);
        QueryResolver qr = new QueryResolver();
        try {
            List<Entry> result = qr.resolveQuery(query);
            System.out.println(result.size() + " entries.");
            for (Entry entry : result) {
                System.out.println("Matched " + entry.toString());
            }
        } catch (MalformedQueryException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnresolvableQueryException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void testQueryMangler() {
        try {
            FakeLogOverlayAccess.mangleQuery("agentOne", "SELECT value WHERE name='SNR' AND workflowid = 'test123' ORDER BY timestamp DESC");
            FakeLogOverlayAccess.mangleQuery("agentTwo", "SELECT value;");
            FakeLogOverlayAccess.mangleQuery("agentThree", "SELECT value WHERE name='SNR' ORDER BY timestamp DESC LIMIT 10");
        } catch (MalformedQueryException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void testWiki() {
        DocumentStore ds = null;
        try {
            ds = new SQLiteDocumentStore();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Couldn't construct document store.", ex);
            System.exit(1);
        }
        initialiseAndPopulateDocumenetStore(ds);
        addDocumentToStore(ds);
        String input = "";
        try {
            input = ds.retrieveDocument("mydocument");
        } catch (DocumentStoreException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Couldn't fetch document from store.", ex);
            System.exit(1);
        }
        System.out.println("--- Input to renderer:");
        System.out.println(input);
        Renderer r = new Renderer();
        String output = r.render(input);
        System.out.println("--- Output of renderer:");
        System.out.println(output);
    }

    private static void addDocumentToStore(DocumentStore ds) {
        try {
            ds.createDocument("mydocument", "!New Document\n\nI just created this one on the fly.");
        } catch (DocumentStoreException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String readFileToString(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
        } finally {
            stream.close();
        }
    }

    private static String loadDocumentFromExampleFile() {
        String input = "";
        try {
            input = readFileToString("/Users/alex/Dropbox/PhD/Work/Stix/StixView/example.txt");
        } catch (IOException e) {
            System.err.println("Could not open example.txt");
            System.exit(0);
        }
        return input;
    }

    private static void initialiseAndPopulateDocumenetStore(DocumentStore ds) {
        ds.createAndPopulate();
    }

    private static void registerSomeAgents() {
        try {
            try {
                AgentRegistry.registerAgent(new Agent("agent1", InetAddress.getByName("testbedgw.wimo.inf.ed.ac.uk"), 9991));
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (AgentNameConflictException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
