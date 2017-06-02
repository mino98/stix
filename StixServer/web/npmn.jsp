<%--
    Document   : npmn
    Created on : Feb 1, 2010, 17:11:10 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.ed.inf.wimo.stix.gui.backend.workflow.*" %>

<% Integer id = new Integer(request.getParameter("id")); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Workflow NPMN</title>
    </head>
    <body>
        <h1><%= SQLiteWorkflowStore.getInstance().getWorkflowName(id) %> NPMN</h1>

        <pre><%= new Workflow2NPMN().convert(SQLiteWorkflowStore.getInstance().getWorkflow(id)).replace("<", "&lt;").replace(">", "&gt;") %></pre>

        <a href="index.jsp">&lt;&lt; Back</a>
    </body>
</html>
