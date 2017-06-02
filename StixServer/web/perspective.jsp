<%--
    Document   : perspective
    Created on : Jan 6, 2010, 11:07:10 AM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.ed.wimo.stix.view.*" %>
<%@page import="uk.ac.ed.wimo.stix.view.helpers.*" %>
<%@page import="uk.ac.ed.wimo.stix.view.wiki.*" %>

<%
    Integer id = new Integer(request.getParameter("id"));

    String documentName = DocumentStoreHelper.getDocumentStore().getDocumentName(id);
%>

<jsp:include page="theme.jsp">
    <jsp:param name="title" value="Edit Perspective"/>
    <jsp:param name="zone" value="view"/>
</jsp:include>

        <link rel="stylesheet" type="text/css" href="css/master.css"/>
        <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript">
            // This is the list that queries are inserted into by
            // deferred-query scriptlets.
            var queries = [];

            $(document).ready(function() {

                // Perform an AJAX request for each query.
                for (var i in queries) {
                    var placeholderid = queries[i][0];
                    var query = queries[i][1];
                    $(placeholderid).load("query.jsp", {'query': query}, function(text, status) {
                        // Test for 500.
                        if (status == "error") {
                            this.innerHTML = "There was an error resolving this query. The syntax of the query may be incorrect or there may be a problem with the server.";
                        }
                    });
                }

            });
        </script>

        <h1>Perspective: <%= documentName %></h1>

        <div id="perspective-output">
            <% try { %>
                <%= new Renderer().render(
                        DocumentStoreHelper.getDocumentStore().retrieveDocument(id)
                        ) %>
            <% } catch (TokenMgrError te) { %>
            <h3>Error</h3>
            <p>
                There was an error parsing the wiki source.<br/>
                <%= te.getMessage() %>
            </p>
            <% } %>

        </div>
        <a href="perspectives.jsp">&lt;&lt; Back</a>

<jsp:include page="theme.jsp"><jsp:param name="part" value="bottom"/></jsp:include>


