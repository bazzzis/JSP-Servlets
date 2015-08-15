<%-- 
    Document   : products
    Created on : Mar 5, 2015, 1:35:05 PM
    Author     : Bazis
--%>

<%@page import="java.util.HashMap"%>
<%@page import="Domain.ProductImages"%>
<%@page import="java.util.List"%>
<%@page import="Domain.Produs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="jspf/css.jspf" />
        <title>Products</title>
    </head>

    <jsp:include page="jspf/header.jspf" />

    <body>


        <div class="container marketing"  style="position:relative;top:100px;">

            <!-- Three columns of text below the carousel -->

            <%  int counter = 0;
                List<Produs> productsList = (List<Produs>) request.getAttribute("productsList");
                HashMap <Integer, ProductImages> mainImagesMap= (HashMap<Integer, ProductImages>) request.getAttribute("imagesMap");
                    ProductImages productImage;
                for (Produs produs : productsList) {
                     productImage = mainImagesMap.get(produs.getId());
                       String imageLink; 
                               if(productImage!=null)
                               {imageLink = productImage.getFileName();}
                               else{imageLink = ""+produs.getId()+".jpg" ;}

                    if (counter == 0) {

            %>

            <div class="row-fluid">
                <div class="span4">
                    <img class="img-circle" data-src="holder.js/140x140" alt="140x140" src="productsImages/<%=imageLink%>" style="width: 140px; height: 140px;">
                    <h2><%=produs.getName()%></h2>
                    <p><%=produs.getDescription()%></p>
                    <p>Price: <%=produs.getPrice()%> lei</p>
                    <p><a class="btn" href="http://getbootstrap.com/2.3.2/examples/carousel.html#">View details »</a></p>
                </div><!-- /.span4 -->

                <%  counter++;
                    }
                    else if (counter == 1) {
                %> 
                <div class="span4">
                    <img class="img-circle" data-src="holder.js/140x140" alt="140x140" src="productsImages/<%=imageLink%>" style="width: 140px; height: 140px;">
                    <h2><%=produs.getName()%></h2>
                    <p><%=produs.getDescription()%></p>
                    <p>Price: <%=produs.getPrice()%> lei</p>
                    <p><a class="btn" href="http://getbootstrap.com/2.3.2/examples/carousel.html#">View details »</a></p>
                </div><!-- /.span4 -->

                <% counter++;
                    }
                   else if (counter == 2) {
                %> 
                <div class="span4">
                    <img class="img-circle" data-src="holder.js/140x140" alt="140x140" src="productsImages/<%=imageLink%>" style="width: 140px; height: 140px;">
                    <h2><%=produs.getName()%></h2>
                    <p><%=produs.getDescription()%></p>
                    <p>Price: <%=produs.getPrice()%> lei</p>
                    <p><a class="btn" href="http://getbootstrap.com/2.3.2/examples/carousel.html#">View details »</a></p>
                </div><!-- /.span4 -->
   </div><!-- /.row -->
                <% counter=0;
                        } 
                    }%>


               
         
        </div><!-- /.marketing container -->
        
         <jsp:include page="jspf/footer.jspf" />
         
    </body>
</html>
