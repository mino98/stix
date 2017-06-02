<%--
    Document   : npmn
    Created on : Feb 1, 2010, 17:11:10 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.ed.inf.wimo.stix.gui.backend.workflow.*" %>
<%@page import="uk.ac.ed.inf.wimo.stix.gui.backend.helpers.*" %>

<% WorkflowStore ws = SQLiteWorkflowStore.getInstance(); %>
<% Integer id = new Integer(request.getParameter("id")); %>
<% Workflow w = SQLiteWorkflowStore.getInstance().getWorkflow(id); %>
<% HTMLHelper h = new HTMLHelper(); %>

<jsp:include page="theme.jsp">
    <jsp:param name="title" value="Workflow Properties"/>
</jsp:include>

        <h1>Workflow: <%= w.getName() %></h1>

        <form method="post" action="?id=<%= id %>&save">

            <%
                if (request.getParameter("save") != null) {
                    w.setName(request.getParameter("name"));
                    w.setAuthor(request.getParameter("author"));
                    w.setNotes(request.getParameter("notes"));
                    w.setValidNotBefore(h.stringToCalendar(request.getParameter("validnotbefore")));
                    w.setValidNotAfter(h.stringToCalendar(request.getParameter("validnotafter")));

                    ws.updateWorkflow(id, w);
                    %>


                    <a href="index.jsp" id="note">Your changes were saved. Click here to return to the workflow list.</a>

                    <%
                }
            %>

            <% if (w.getSvg() != null) { %>
                <object data="workflow/model/<%= id %>//svg" type="image/svg+xml"
                    width="898" height="310">
                        <embed src="workflow/model/<%= id %>//svg" type="image/svg+xml"
                        width="898" height="310" />
                </object>
            <% } %>

            <a class="button-color" href="delete.jsp?id=<%=id%>" onclick="return confirm('Are you sure?')"><span>Delete</span></a>
            <a class="button-color" href="/StixGuiEditor/editor#/model/<%=id%>/"><span>Edit the workflow in the Workflow Editor</span></a>

            <br style="clear: both;"/>&nbsp;<br/>

            <table>
                <tr>
                    <td>Name</td>
                    <td><input name="name" value="<%= h.safe(w.getName()) %>"/></td>
                </tr>
                <tr>
                    <td>Author</td>
                    <td><input name="author" value="<%= h.safe(w.getAuthor()) %>"/></td>
                </tr>
                <tr>
                    <td>Notes</td>
                    <td><textarea name="notes"><%= h.safe(w.getNotes()) %></textarea></td>
                </tr>
                <tr>
                    <td>Valid Not Before (Starts)</td>
                    <td><input name="validnotbefore" value="<%= h.calendarToString(w.getValidNotBefore()) %>"/> Format: yyyy-mm-ddThh:mm:ss</td>
                </tr>
                <tr>
                    <td>Valid Not After (Expires)</td>
                    <td><input name="validnotafter" value="<%= h.calendarToString(w.getValidNotAfter()) %>"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Click to save changes"/></td>
                </tr>
            </table>
        </form>

        <a href="index.jsp">&lt;&lt; Back to the Workflow List</a>

<jsp:include page="theme.jsp"><jsp:param name="part" value="bottom"/></jsp:include>
