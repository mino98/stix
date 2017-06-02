<%--
    Document   : save
    Created on : Jan 6, 2010, 11:07:10 AM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.ed.wimo.stix.view.*" %>
<%@page import="uk.ac.ed.wimo.stix.view.helpers.*" %>

<%
    // Read from GET and POST
    Integer id = new Integer(request.getParameter("id"));
    String content = request.getParameter("content");

    // Update the perspective.
    DocumentStoreHelper.getDocumentStore().updateDocument(id, content);

    // Redirect to the perspective view.
    response.sendRedirect("perspective.jsp?id="+id);
%>

Done.