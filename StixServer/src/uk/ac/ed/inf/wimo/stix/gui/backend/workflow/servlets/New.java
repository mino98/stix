/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.ed.inf.wimo.stix.gui.backend.workflow.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.ed.inf.wimo.stix.gui.backend.workflow.SQLiteWorkflowStore;
import uk.ac.ed.inf.wimo.stix.gui.backend.workflow.Workflow;

/**
 *
 * @author alex
 */
public class New extends HttpServlet {
   
	/** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // Save document
            SQLiteWorkflowStore ws = SQLiteWorkflowStore.getInstance();

            Workflow w = Workflow.createBlankWorkflow(request.getParameter("title"));
            w.setJson(request.getParameter("data"));

            int id = ws.createWorkflow(w);
            String path = "http://" + request.getServerName() + ":" + request.getServerPort() + "/StixServer/workflow/model/" + id + "/self";

            response.setStatus(201); // Created
            response.setHeader("location", path);
        } catch (Exception e) {
            response.setStatus(500);
            out.write(e.toString());
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
