<%-- 
    Document   : delete
    Created on : Feb 5, 2010, 3:38:45 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.ed.wimo.stix.view.helpers.*" %>


<% Integer id = new Integer(request.getParameter("id")); %>
<% DocumentStoreHelper.getDocumentStore().deleteDocument(id); %>

<jsp:include page="theme.jsp">
    <jsp:param name="title" value="Workflows"/>
</jsp:include>

        <h1>Perspective deleted.</h1>

        <a href="perspectives.jsp" id="note">The perspective was deleted. Click here to return to the perspective list.</a>


<jsp:include page="theme.jsp"><jsp:param name="part" value="bottom"/></jsp:include>


