/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.ed.inf.wimo.stix.gui.backend.workflow.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.ed.inf.wimo.stix.gui.backend.workflow.Workflow;
import uk.ac.ed.inf.wimo.stix.gui.backend.workflow.Workflow2NPMN;
import uk.ac.ed.inf.wimo.stix.gui.backend.workflow.SQLiteWorkflowStore;

/**
 *
 * @author alex
 */
public class Model extends HttpServlet {

    private static int READ = 100;
    private static int WRITE = 200;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, int method)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String path = request.getPathInfo();

            String matchQueryRegex = "\\A\\/([0-9]+)\\/";
            Pattern p = Pattern.compile(matchQueryRegex, Pattern.DOTALL);
            Matcher m = p.matcher(path);
            if (!m.find()) {
                throw new Exception("Couldn't find a workflow ID in URL");
            }
            int id = Integer.parseInt(m.group(1));


            SQLiteWorkflowStore ws = SQLiteWorkflowStore.getInstance();

            Workflow w = ws.getWorkflow(id);

            if (method == READ) {
                String outputtype = getOutputType(path);
                if (outputtype == "json") {
                    response.setContentType("application/json;charset=UTF-8");
                    out.println(w.getJson());
                } else if (outputtype == "npmn") {
                    response.setContentType("text/xml;charset=UTF-8");
                    out.println(new Workflow2NPMN().convert(w));
                } else if (outputtype == "svg") {
                    response.setContentType("image/svg+xml");
                    out.println(w.getSvg());
                }

            } else if (method == WRITE) {
                String doc = request.getParameter("data");
                String svg = request.getParameter("svg");
                w.setJson(doc);
                w.setSvg(svg);
                ws.updateWorkflow(id, w);
            }


        } catch (Exception e) {
            response.setStatus(500);
            out.print(e.toString());
            out.print("\n\n");
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, READ);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, WRITE);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getOutputType(String path) {
        String matchQueryRegex = "\\A\\/[0-9]+\\/([a-z]+)";
        Pattern p = Pattern.compile(matchQueryRegex, Pattern.DOTALL);
        Matcher m = p.matcher(path);

        if (!m.find()) {
            throw new RuntimeException("No output type specified.");
        }

        String formatstring = m.group(1);

        if (formatstring.equals("npmn")) {
            return "npmn";
        } else if (formatstring.equals("json")) {
            return "json";
        } else if (formatstring.equals("svg")) {
            return "svg";
        } else {
            throw new RuntimeException("Invalid output type in url.");
        }


    }
}
