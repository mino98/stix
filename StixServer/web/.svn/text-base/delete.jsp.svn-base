<%-- 
    Document   : delete
    Created on : Feb 5, 2010, 3:38:45 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.ed.inf.wimo.stix.gui.backend.workflow.*" %>

<% Integer id = new Integer(request.getParameter("id")); %>
<% SQLiteWorkflowStore.getInstance().deleteWorkflow(id); %>

<jsp:include page="theme.jsp">
    <jsp:param name="title" value="Workflows"/>
</jsp:include>

        <h1>Workflow deleted.</h1>

        <a href="index.jsp" id="note">The workflow was deleted. Click here to return to the workflow list.</a>


<jsp:include page="theme.jsp"><jsp:param name="part" value="bottom"/></jsp:include>


