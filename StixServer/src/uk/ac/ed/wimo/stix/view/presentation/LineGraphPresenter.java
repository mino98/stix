/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.view.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uk.ac.ed.wimo.stix.log.Entry;

/**
 *
 * @author alex
 */
public class LineGraphPresenter extends GoogleChartPresenter {


    @Override
    public String present() {
        if (entries.size() == 0)
            return "No data to render.";
        
        Map<String, String> params = new HashMap<String, String>();

        params.put(CHART_TYPE, CHART_TYPE_LINE);
        params.put(CHART_SIZE, "500x200");

        // Create lists of labels and data
        List<String> labels = new ArrayList<String>();
        List<Double> data = new ArrayList<Double>();
        for(Entry e : entries) {
            labels.add(e.getField(0));
            data.add(new Double(e.getField(1)));
        }

        params.put(CHART_DATA, serialiseChartDataWithScaledTextEncoding(data));
        params.put(CHART_DATA_SCALING, serialiseBestFitForScaledTextEncoding(data));

        params.put(CHART_AXES, CHART_AXES_X_Y);

        List<String> minmax = new ArrayList<String>();
        minmax.add(min(data).toString());
        minmax.add(max(data).toString());

        List<String> firstlast = new ArrayList<String>();
        firstlast.add(labels.get(0));
        firstlast.add(labels.get(labels.size()-1));

        params.put(CHART_AXIS_LABELS, "0:|" + serialiseChartLabels(firstlast) + "|1:|" + serialiseChartLabels(minmax));

        return generateChart(params);
    }

}
