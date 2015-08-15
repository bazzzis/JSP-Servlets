<%-- 
    Document   : products
    Created on : Mar 5, 2015, 1:35:05 PM
    Author     : Bazis
--%>

<%@page import="Domain.ProductImages"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="Domain.Produs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="CMSjspf/css.jspf" />
        <title>CMS Products</title>
    </head>

    <jsp:include page="CMSjspf/header.jsp" />

    <body>


        <div class="container marketing"  style="position:relative;top:100px;">
${info}
<div class="error alert-error">${error} </div>


            <!-- Three columns of text below the carousel -->
            <table style="width:100%" border="1" class="table-striped table-bordered">
                <tr>
                    <th> Product Image</th>
                    <th> Product Name</th>
                   <!-- <th style="width:50%"> Product Description</th> -->
                    <th> Product Price</th>
                    <th> Update Product</th>
                    <th> Delete product</th>
                </tr>
                <%  
                    List<Produs> productsList = (List<Produs>) request.getAttribute("productsList");
                    HashMap <Integer, ProductImages> mainImagesMap= (HashMap<Integer, ProductImages>) request.getAttribute("imagesMap");
                    ProductImages productImage;
                    for (Produs produs : productsList) {
                        
                       productImage = mainImagesMap.get(produs.getId());
                       String imageLink; 
                               if(productImage!=null)
                               {imageLink = productImage.getFileName();}
                               else{imageLink = ""+produs.getId()+".jpg" ;}

                %>


                <tr>
                    <td> <img class="img-rounded" data-src="holder.js/140x140" alt="<%=produs.getName()%>" src="../productsImages/<%=imageLink%>" style="width: 75px; height: 75px;"></td>
                    <td> <p><%=produs.getName()%></p></td>
                  <!--  <td> <p><%=produs.getDescription()%></p></td> -->
                    <td> <p>Price: <%=produs.getPrice()%> lei</p></td>
                    <td> <p><a class="btn" href="CMSProduct?productId=<%=produs.getId()%> ">Update</a> </td>
                    <td> <a class="btn" href="CMSDeleteProduct?productId=<%=produs.getId()%> ">Delete</a> </td>
                </tr>

                <%
                    }
                %> 
            </table>

                <jsp:include page="CMSjspf/footer.jspf" />

                </body>
                </html>
