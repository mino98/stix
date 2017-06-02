<%--
    Document   : edit
    Created on : Jan 6, 2010, 11:07:10 AM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.ed.wimo.stix.view.*" %>
<%@page import="uk.ac.ed.wimo.stix.view.helpers.*" %>
<%@page import="uk.ac.ed.inf.wimo.stix.gui.backend.helpers.*" %>


<% HTMLHelper h = new HTMLHelper(); %>
<% Integer id = new Integer(request.getParameter("id")); %>

<jsp:include page="theme.jsp">
    <jsp:param name="title" value="Edit Perspective"/>
    <jsp:param name="zone" value="view"/>
</jsp:include>

        <h1>Edit Perspective</h1>

        <form id="editor" method="post" action="edit.jsp?save&id=<%= id %>">

            <%
                if (request.getParameter("save") != null) {

                    // Read from GET and POST
                    String content = request.getParameter("content");
                    String name = request.getParameter("name");

                    // Update the perspective.
                    DocumentStoreHelper.getDocumentStore().updateDocument(id, content);
                    DocumentStoreHelper.getDocumentStore().renameDocument(id, name);

            %>


                    <a href="perspective.jsp?id=<%= id %>" id="note">Your changes were saved. Click here to view the updated perspective.</a>

                    <%
                }
            %>

            <table>
                <tr>
                    <td>Name</td>
                    <td><input name="name" value="<%= h.safe(DocumentStoreHelper.getDocumentStore().getDocumentName(id)) %>"/></td>
                </tr>
                <tr>
                    <td>Source</td>
                    <td><textarea name="content" rows="24" cols="80"><%= h.safe(DocumentStoreHelper.getDocumentStore().retrieveDocument(id)) %></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Click to save changes"/></td>
                </tr>
            </table>
        </form>
            <a class="button-color" href="deleteperspective.jsp?id=<%=id%>" onclick="return confirm('Are you sure?')"><span>Delete</span></a>
            <a class="button-color" href="perspective.jsp?id=<%=id%>"><span>View</span></a>

               <br/><br/>

                <a href="perspectives.jsp">&lt;&lt; Back</a>

<jsp:include page="theme.jsp"><jsp:param name="part" value="bottom"/></jsp:include>
