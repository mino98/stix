<%--
    Document   : perspectives
    Created on : Jan 4, 2010, 3:31:42 PM
    Author     : alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="uk.ac.ed.wimo.stix.view.storage.*" %>
<%@page import="uk.ac.ed.wimo.stix.view.helpers.*" %>

<%
    DocumentStore ds = DocumentStoreHelper.getDocumentStore();

    // Check if we are creating a perspective.
    String npn = request.getParameter("newperspective");
    if (npn != null) {
        ds.createDocument(npn, "");
    }
    
    // Get a list of perspectives here.
    Map<Integer, String> perspectives = ds.getDocuments();

    Set<Integer> perspective_ids = perspectives.keySet();
    System.out.println("Number of perspective ids:" + perspective_ids.size());


%>

<jsp:include page="theme.jsp">
    <jsp:param name="title" value="Perspectives"/>
    <jsp:param name="zone" value="view"/>
</jsp:include>

        <h2>Perspectives</h2>
        <style>
            ul.itemlist li {
                clear: both;
                padding-bottom: 15px;
            }
        </style>
        <ul class="itemlist">
            <% for (Integer id : perspective_ids) { %>
                <li>
                    <a class="button" href="edit.jsp?id=<%=id%>"><span>Edit</span></a>
                    <a class="button-color" href="perspective.jsp?id=<%=id%>"><span>View</span></a>
                    <a href="perspective.jsp?id=<%=id%>"><%=perspectives.get(id)%></a>
                </li>
            <% } %>
            </c:forEach>
        </ul>

        <h2>New Perspective</h2>
        <form method="get">
            Enter a name for the new perspective: <input name="newperspective"/>&nbsp;<input type="submit" value="Create Perspective"/>
        </form>
<jsp:include page="theme.jsp"><jsp:param name="part" value="bottom"/></jsp:include>
