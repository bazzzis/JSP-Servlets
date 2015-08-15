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
        <jsp:include page="CMSjspf/css.jspf" />
        <title>CMS Home</title>
        
    </head>

   

<body>
     <jsp:include page="CMSjspf/header.jsp" />
    <% User user = (User) session.getAttribute("MAIN_USER");%>

    <div class="featurette">

        <h1 >Wellcome to CMS <%=user.getUsername()%>!</h1> 

    </div>
    <jsp:include page="CMSjspf/footer.jspf" />
</body>
</html>
