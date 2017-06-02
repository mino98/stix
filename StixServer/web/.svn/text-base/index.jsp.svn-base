<%--
    Document   : index
    Created on : Jan 4, 2010, 3:31:42 PM
    Author     : alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="uk.ac.ed.inf.wimo.stix.gui.backend.workflow.*" %>

<%
    WorkflowStore ws = SQLiteWorkflowStore.getInstance();

    // Check if we are creating a workflow.
    String nwn = request.getParameter("newworkflow");
    if (nwn != null) {
        Workflow w = Workflow.createBlankWorkflow(nwn);
        ws.createWorkflow(w);
    }


    // Get a list of perspectives here.
    Map<Integer, String> workflows = ws.getWorkflows();
    Set<Integer> workflow_ids = workflows.keySet();
    System.out.println("Number of workflow ids:" + workflow_ids.size());
%>

<jsp:include page="theme.jsp">
    <jsp:param name="title" value="Workflows"/>
</jsp:include>

        <h2>Workflows</h2>
        <style>
            ul.itemlist li {
                clear: both;
                padding-bottom: 15px;
            }
        </style>
        <ul class="itemlist">
            <% for (Integer id : workflow_ids) { %>
                <li>
                    <!--<a class="button" href="json.jsp?id=<%=id%>"><span>JSON</span></a>
                    <a class="button" href="/StixGuiEditor/editor#/model/<%=id%>/"><span>Edit</span></a>
                    <a class="button" href="npmn.jsp?id=<%=id%>"><span>NPMN</span></a>-->
                    <a class="button" href="properties.jsp?id=<%=id%>"><span>Edit</span></a>
                    <a class="button-color" href="deploy.jsp?id=<%=id%>"><span>Deploy</span></a>
                    <%=workflows.get(id)%>
                </li>
            <% } %>
            </c:forEach>
        </ul>

        <h2>New Workflow</h2>
        <form method="get">
            Enter a name for the new workflow: <input name="newworkflow"/>&nbsp;<input type="submit" value="Create Blank Workflow"/>
        </form>


<jsp:include page="theme.jsp"><jsp:param name="part" value="bottom"/></jsp:include>

