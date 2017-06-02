/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.inf.wimo.stix.gui.backend.workflow;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author alex
 */
public class Workflow implements Serializable {
    private String name;
    private String author;
    private String uuid;
    private int revision;
    private String notes;
    private boolean enabled;
    private Calendar validNotBefore;
    private Calendar validNotAfter;
    private String svg;

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    private String json;

    public static Workflow createBlankWorkflow(String name) {
        Workflow w = new Workflow();
        w.setName(name);
        w.setAuthor("No-one");
        w.setUUID(generateUUID());
        w.setRevision(0);
        w.setNotes("");
        w.setEnabled(true);

        w.setJson("{\"resourceId\":\"npmn-canvas123\",\"properties\":{\"id\":\"\",\"name\":\"\",\"pools\":\"\"},\"stencil\":{\"id\":\"NPMNDiagram\"},\"childShapes\":[],\"bounds\":{\"lowerRight\":{\"x\":1485,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"/StixGuiEditor/stencilsets/npmn/npmn.json\",\"namespace\":\"http://www.wimo.inf.ed.ac.uk/stix#\"},\"ssextensions\":[]}");

        return w;
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public Calendar getValidNotAfter() {
        return validNotAfter;
    }

    public void setValidNotAfter(Calendar validNotAfter) {
        this.validNotAfter = validNotAfter;
    }

    public Calendar getValidNotBefore() {
        return validNotBefore;
    }

    public void setValidNotBefore(Calendar validNotBefore) {
        this.validNotBefore = validNotBefore;
    }

    public String serialize() {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream out = null;
        try {
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Base64 b64 = new Base64();
        return b64.encodeBase64String(bos.toByteArray());
    }

    public static Workflow unserialize(String sobj) throws WorkflowStoreException {
        Workflow w = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream in = null;

        try {
            Base64 b64 = new Base64();
            bis = new ByteArrayInputStream(b64.decode(sobj));
            in = new ObjectInputStream(bis);
            w = (Workflow)in.readObject();
            in.close();
        } catch (IOException ex) {
            throw new WorkflowStoreException("Couldn't unserialise the workflow", ex);
        } catch (ClassNotFoundException ex) {
            throw new WorkflowStoreException("Couldn't unserialise the workflow", ex);
        }

        return w;
    }

    void incrementRevision() {
        revision += 1;
    }
    
}
