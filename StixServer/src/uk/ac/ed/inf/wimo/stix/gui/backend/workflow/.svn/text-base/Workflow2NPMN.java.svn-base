/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.ed.inf.wimo.stix.gui.backend.workflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.*;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.impl.*;
import uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil;

/**
 *
 * @author alex
 */
public class Workflow2NPMN {

    /**
     * A map for translating JSON pointers to SequenceFlow shapes into NPMN
     * pointers to the next ComplexType in the flow.
     */
    private Map<String, String> _flowMappings;

    public Workflow2NPMN() {
        _flowMappings = new HashMap<String, String>();
    }


    public String convert(Workflow workflow) {

        // Convert the JSON string into an object representation.
        JSONObject doc = null;

        try {
            doc = new JSONObject(workflow.getJson());

            uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow w = new WorkflowImpl();

            JSONArray childShapes = doc.getJSONArray("childShapes");

            // First build the flow mapping dictionary.
            for (int i = 0; i < childShapes.length(); i++) {
                JSONObject childShape = childShapes.getJSONObject(i);

                if (childShape.getJSONObject("stencil").getString("id").equalsIgnoreCase("SequenceFlow")) {
                    FlowMappingComplexType fm = (FlowMappingComplexType)convertToComplexType(childShape);

                    _flowMappings.put(childShape.getString("resourceId"), fm.getOut());
                }

            }

            // Now go through the rest of the document.
            for (int i = 0; i < childShapes.length(); i++) {
                JSONObject childShape = childShapes.getJSONObject(i);
                Object o = convertToComplexType(childShape);

                if (o instanceof PoolComplexType) {
                    // We've found the pool. WOOOOOOOOOOOOO!
                    w.setPool((PoolComplexType)o);
                }
            }

            MetadataComplexType m = new MetadataComplexTypeImpl();
            m.setAuthor(workflow.getAuthor());
            m.setEnabled(workflow.isEnabled());
            m.setName(workflow.getName());
            m.setNotes(workflow.getNotes());
            m.setUUID(workflow.getUUID());
            m.setRev(workflow.getRevision());
            MetadataComplexType.ValidityType vt = new MetadataComplexTypeImpl.ValidityTypeImpl();
            vt.setNotAfter(workflow.getValidNotAfter());
            vt.setNotBefore(workflow.getValidNotBefore());
            m.setValidity(vt);
            
            w.setMetadata(m);
            
            if (doc.has("workflowAttributes")) {
                AttributeSetComplexType as = getAttributeSetFromJSON(doc.getJSONArray("workflowAttributes"));
                w.setAttributeSet(as);
            }


            // Output XML
            return XmlUtil.workflowToString(w);

        } catch (Exception x) {
            throw new RuntimeException(x);
        }

    }

    private Object convertToComplexType(JSONObject childShape) throws JSONException, ConversionException {
        String resourceId = childShape.getString("resourceId");
        String stencil = childShape.getJSONObject("stencil").getString("id");
        JSONObject properties = childShape.getJSONObject("properties");

        if (stencil.equalsIgnoreCase("Pool")) {

            PoolComplexType pool = new PoolComplexTypeImpl();
            pool.setQuery(properties.getString("name"));
            
            JSONArray children = childShape.getJSONArray("childShapes");
            for (int i = 0; i < children.length(); i++) {
                Object child = convertToComplexType(children.getJSONObject(i));
                if (child instanceof StartTimerEventComplexType) {
                    pool.getStartTimerEvent().add(child);
                } else if (child instanceof TaskComplexType) {
                    pool.getTask().add(child);
                } else if (child instanceof EndPlainEventComplexType) {
                    pool.getEndPlainEvent().add(child);
                } else {
                    throw ConversionException.UnrecognisedChildOf(pool, child);
                }
            }
            
            return pool;

        } else if (stencil.equalsIgnoreCase("StartTimerEvent")) {

            StartTimerEventComplexType ste = new StartTimerEventComplexTypeImpl();
            ste.setId(resourceId);
            
            FlowMappingComplexType fm = new FlowMappingComplexTypeImpl();
            fm.setOut(findFlowMapping(childShape));
            ste.setFlowMapping(fm);
            ste.setTimer(getCalendarObjectForString(properties.getString("name")));
            long period = getPeriodLongForString(properties.getString("name"));
            if (period > -1)
                ste.setPeriod(period);
 
            return ste;

        } else if (stencil.equalsIgnoreCase("Task")) {

            TaskComplexType task = new TaskComplexTypeImpl();
            task.setId(resourceId);
            task.setClazz(properties.getString("name"));
            task.setURL("NOTIMPLEMENTED");

            FlowMappingTaskComplexType fmt = new FlowMappingTaskComplexTypeImpl();
            fmt.setOut(findFlowMapping(childShape));
            FlowMappingTaskComplexTypeImpl.TimeoutTypeImpl tt = new FlowMappingTaskComplexTypeImpl.TimeoutTypeImpl();
            tt.setValue(findFlowMapping(childShape));
            fmt.setTimeout(tt);
            task.setFlowMapping(fmt);

            return task;

        } else if (stencil.equalsIgnoreCase("EndNoneEvent")) {

            EndPlainEventComplexType ee = new EndPlainEventComplexTypeImpl();
            ee.setId(resourceId);

            return ee;

        } else if (stencil.equalsIgnoreCase("SequenceFlow")) {

            FlowMappingComplexType fm = new FlowMappingComplexTypeImpl();
            fm.setOut(childShape.getJSONObject("target").getString("resourceId"));

            return fm;

        } else {
            throw new ConversionException("Unrecognised stencil: " + stencil);
        }

    }

    private Calendar getCalendarObjectForString(String timeString) throws ConversionException {

        String matchPeriodRegex = "at\\s*(.*?)\\s*\\Z";
        Matcher m = Pattern.compile(matchPeriodRegex, Pattern.DOTALL).matcher(timeString);
        if (!m.find())
            throw new ConversionException("Couldn't parse start time from StartEventTimer name: couldn't parse '" + timeString + "'");
        String match = m.group(1);
        Logger.getLogger(Workflow2NPMN.class.getName()).log(Level.FINER, "getCalendarObjectForString matched: " + match);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = null;
        try {
            d = sdf.parse(match);
        } catch (ParseException ex) {
            Logger.getLogger(Workflow2NPMN.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConversionException("Couldn't parse date string inside StartEventTimer name");
        }

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        
        return c;

    }

    private long getPeriodLongForString(String timeString) throws ConversionException {

        String matchPeriodRegex = "\\A\\s*every\\s*([0-9]+?)\\s*at.*";
        Matcher m = Pattern.compile(matchPeriodRegex, Pattern.DOTALL).matcher(timeString);
        if (!m.find())
            return -1;
        String match = m.group(1);
        Logger.getLogger(Workflow2NPMN.class.getName()).log(Level.FINER, "getPeriodLongForString matched: " + match);
        return Long.parseLong(match);

    }

    /**
     * Given a JSON object representing a shape, determine the name of the
     * object that flow should continue to from this shape.
     * @param shape
     * @return
     */
    private String findFlowMapping(JSONObject shape) throws ConversionException {
       String sequenceFlowId;
        try {
            sequenceFlowId = shape.getJSONArray("outgoing").getJSONObject(0).getString("resourceId");
        } catch (JSONException ex) {
            throw new ConversionException("Incorrect input document - shape has no outgoing flow.");
        }
       return _flowMappings.get(sequenceFlowId);
    }

    private AttributeSetComplexType getAttributeSetFromJSON(JSONArray attrs) {
        AttributeSetComplexType as = new AttributeSetComplexTypeImpl();
        List asl = as.getAttribute();

        for (int i = 0; i < attrs.length(); i++) {
            try {
                JSONObject attr = attrs.getJSONObject(i);
                AttributeSetComplexType.AttributeType at = new AttributeSetComplexTypeImpl.AttributeTypeImpl();
                at.setName(attr.getString("name"));
                at.setValue(attr.getString("value"));
                at.setPersistent(attr.optBoolean("persistent"));
                asl.add(at);
            } catch (JSONException ex) {
                Logger.getLogger(Workflow2NPMN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return as;

    }


}
