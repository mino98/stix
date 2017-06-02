/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.wimo.stix.view.presentation;

import java.util.List;
import java.util.Map;

/**
 *
 * @author alex
 */
public abstract class GoogleChartPresenter extends Presenter {

    protected static String CHART_SIZE = "chs";
    protected static String CHART_LABELS = "chl";
    protected static String CHART_DATA = "chd";
    protected static String CHART_DATA_SCALING = "chds";
    
    protected static String CHART_AXIS_LABELS = "chxl";

    protected static String CHART_BAR_THICKNESS = "chbh";
    protected static String CHART_BAR_THICKNESS_AUTO = "a";

    protected static String CHART_AXES = "chxt";
    protected static String CHART_AXES_X_Y = "x,y";

    protected static String CHART_TYPE = "cht";
    protected static String CHART_TYPE_LINE = "lc";
    protected static String CHART_TYPE_BAR_VERTICAL_GROUPED = "bvg";
    protected static String CHART_TYPE_PIE = "p";

    private static final String GOOGLE_CHART_API_STUB =
            "http://chart.apis.google.com/chart?";

    protected String generateChart(Map<String, String> params) {
        return "<img src='"+generateUrl(params)+"'/>";
    }

    private String generateUrl(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry e : params.entrySet()) {
            sb.append(e.getKey());
            sb.append("=");
            sb.append(e.getValue());
            sb.append("&");
        }

        // Truncate the trailing ampersand.
        sb.setLength(sb.length()-1);

        return GOOGLE_CHART_API_STUB + sb.toString();
    }

    /**
     * Takes a list of doubles and returns a serialised chart data string.
     * @param data
     * @return
     */
    protected String serialiseChartDataWithScaledTextEncoding (List<Double> data) {
        StringBuilder sb = new StringBuilder("t:");
        
        for (Double datum : data) {
            sb.append(datum);
            sb.append(",");
        }

        // Truncate the trailing comma.
        sb.setLength(sb.length()-1);

        return sb.toString();
    }

    /**
     * Takes a list of doubles and serialises a scaling string to scale
     * the data to fill the Y-axis.
     * @param data
     * @return
     */
    protected String serialiseBestFitForScaledTextEncoding (List<Double> data) {
        return min(data) + "," + max(data);
    }

    protected String serialiseZeroToMaxForScaledTextEncoding (List<Double> data) {
        Double min = min(data);
        if (min(data) > 0) min = 0.;

        return min + "," + max(data);
    }

    protected String serialiseChartLabels (List<String> labels) {
        StringBuilder sb = new StringBuilder();

        for (String label : labels) {
            sb.append(label);
            sb.append("|");
        }

        // Truncate the trailing pipe.
        sb.setLength(sb.length()-1);

        return sb.toString();
    }

    protected Double min(List<Double> data) {
        Double min = Double.POSITIVE_INFINITY;

        for (Double datum : data) {
            min = (datum < min ? datum : min);
        }

        return min;
    }

    protected Double max(List<Double> data) {
        Double max = Double.NEGATIVE_INFINITY;

        for (Double datum : data) {
            max = (datum > max ? datum : max);
        }

        return max;
    }
}
