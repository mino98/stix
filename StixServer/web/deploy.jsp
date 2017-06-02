<%-- 
    Document   : deploy
    Created on : Feb 5, 2010, 6:45:26 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.ed.inf.wimo.stix.gui.backend.workflow.*" %>
<%@page import="java.io.*" %>
<%@page import="java.net.*" %>



<% Integer id = new Integer(request.getParameter("id")); %>
<% Workflow w = SQLiteWorkflowStore.getInstance().getWorkflow(id); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deploying workflow</title>
    </head>
    <body>
        <h1>Deploying workflow: <%= w.getName() %></h1>

        <pre><%

        Socket socket = null;

        String workflowMessagePreamble = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
            "<Message xmlns=\"http://www.wimo.inf.ed.ac.uk/code/stix\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.wimo.inf.ed.ac.uk/code/stix Message.xsd\">" +
                "<Workflow>" +
                    "<WorkflowSource><![CDATA[";

        String workflowMessagePostamble = "" +
                    "]]></WorkflowSource>" +
                    "<Originator>" + request.getLocalAddr() + "</Originator>" +
                    "<Sender>" + request.getLocalAddr() + "</Sender>" +
                "</Workflow>" +
            "</Message>";

        String message = workflowMessagePreamble + new Workflow2NPMN().convert(w) + workflowMessagePostamble;
        message = message.replace("<stix:", "<");
        message = message.replace("</stix:", "</");
        message = message.replace("<Workflow xmlns:stix=\"http://www.wimo.inf.ed.ac.uk/stix\"", "<Workflow xmlns=\"http://www.wimo.inf.ed.ac.uk/stix\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.wimo.inf.ed.ac.uk/stix ../workflow/npmn.xsd \" ");


        %>

        <%= message.replace("<", "&lt;").replace(">", "&gt;") %>

        <%

        try {

            socket = new Socket("192.168.2.102", 9999);
            OutputStream os = socket.getOutputStream();
            //InputStream is = socket.getInputStream();

            // Write message
            BufferedOutputStream bos = new BufferedOutputStream(os);
            bos.write(message.getBytes());
            bos.flush();
            bos.close();

        } finally {

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace(response.getWriter());
                }
            }
        }

        %></pre>

        <p>Done.</p>

        <a href="index.jsp">&lt;&lt; Back</a>
    </body>
</html>
