<%-- 
    Document   : cmshome
    Created on : Mar 8, 2015, 7:38:48 PM
    Author     : bazziss
--%>

<%@page import="Domain.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS home</title>
    </head>
    <body>
        <% User user = (User) session.getAttribute("MAIN_USER"); %>
        <h1>Wellcome to CMS <%=user.getUsername() %>!</h1>
    </body>
</html>
