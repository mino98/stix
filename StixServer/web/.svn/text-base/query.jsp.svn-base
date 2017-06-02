<%--
    Document   : query
    Created on : Jan 6, 2010, 1:50:45 PM
    Author     : alex

    Log overlay query resolution entrypoint.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="uk.ac.ed.wimo.stix.log.*" %>
<%@page import="uk.ac.ed.wimo.stix.view.helpers.*" %>
<%@page import="uk.ac.ed.wimo.stix.view.presentation.*" %>

<%
    // Get query from client
    String fullquery = request.getParameter("query");

    // Get entries from the QueryResolver
    Presenter presenter = QueryResolutionHelper.getPresenter(fullquery);
    String query = QueryResolutionHelper.extractQuery(fullquery);
    List<Entry> entries = QueryResolutionHelper.resolveQuery(query);


%>

<% if (request.getParameter("debug") != null) { %>
<pre>
Query to resolve:
<%= query %>

Entries:
<% for (Entry e : entries) { %>
    <%= e.toString() %>
<% } %>
</pre>
<% } %>

<%= presenter.present(entries) %>