<%-- 
    Document   : index
    Created on : Mar 5, 2015, 11:39:46 AM
    Author     : Bazis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           String redirectURL = "HomePage";
           response.sendRedirect(redirectURL);
       %>
        <h1>Redirecting...</h1>
    </body>
</html>
