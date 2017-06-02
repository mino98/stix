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
        <title>Workflow JSON</title>
        <style>
            .json div {
                padding-left: 20px;
                font-family: monospace;
            }

            .json div:hover {
                padding-left: 10px;
                border-left: 10px solid #DDD;
            }
        </style>
    </head>
    <body>
        <h1><%= SQLiteWorkflowStore.getInstance().getWorkflowName(id) %> JSON</h1>

        <div class="json"><%= SQLiteWorkflowStore.getInstance().getWorkflow(id).getJson()
                .replace(",", ",<br/>")
                .replace("{","<div>{<br/>")
                .replace("}", "<br/>}</div>") %></div>

        <a href="index.jsp">&lt;&lt; Back</a>
    </body>
</html>
