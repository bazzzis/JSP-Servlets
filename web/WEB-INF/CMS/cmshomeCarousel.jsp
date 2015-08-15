<%-- 
    Document   : cmshome
    Created on : Mar 8, 2015, 7:38:48 PM
    Author     : bazziss
--%>

<%@page import="java.util.List"%>
<%@page import="Domain.Carousel"%>
<%@page import="Domain.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="CMSjspf/css.jspf" />
        <title>CMS Home carousel</title>
    </head>



    <body>
        <jsp:include page="CMSjspf/header.jsp" />


        <div class="container marketing"  style="position:relative;top:100px;">
            ${info}
            <div class="error alert-error">${error} </div>
             <a class="btn btn-large btn-primary" href="CMSHomeCarousel?updateId=0">New Image</a> 
<p></p>

            <table style="width:100%" border="1" class="table-striped table-bordered">
                <tr>
                    <th>Image</th>
                    <th> Header</th>
                    <!-- <th style="width:50%"> Product Description</th> -->
                    <th> Text</th>
                    <th> Button link</th>
                    <th> Button Name</th>
                    <th> Update </th>
                    <th> Delete</th>
                        <% List<Carousel> carouselList = (List<Carousel>) request.getAttribute("carouselList");
                           if(carouselList!=null){ for (Carousel carousel : carouselList) {%>
                </tr>
                <td> <img   alt="<%=carousel.getFileName()%>" src="../carousel/<%=carousel.getFileName()%>" style="width: 150px; height: 55px;"></td>
                <td><%=carousel.getHeader()%> </td>
                <td><%=carousel.getText()%> </td>
                <td><%=carousel.getButtonLink()%> </td>
                <td><%=carousel.getButtonName()%> </td>
                <td> <p><a class="btn" href="CMSHomeCarousel?deleteId=<%=carousel.getId()%> ">Delete</a> </td>
                    <td> <a class="btn" href="CMSHomeCarousel?updateId=<%=carousel.getId()%> ">Update</a> </td>
                <tr>
                    <% }%>

                </tr>
<% } %>
            </table>
        </div>
        <jsp:include page="CMSjspf/footer.jspf" />
    </body>
</html>
