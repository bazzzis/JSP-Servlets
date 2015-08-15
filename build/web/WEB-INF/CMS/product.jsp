<%-- 
    Document   : products
    Created on : Mar 5, 2015, 1:35:05 PM
    Author     : Bazis
--%>


<%@page import="Domain.ProductImages"%>
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
    <body>
        <jsp:include page="CMSjspf/header.jsp" />







        <div class="container marketing">
            <div class="featurette">
                ${info}
                <div class="error alert-error">${error} </div>
                <table style="width:100%;">
                    <td style="width:40%;">
                        <div class="container-fluid">

                            <h2 >Add new or edit product</h2> 
                            <div class="control-label">


                            </div>

                        </div>
                        <% Produs product = (Produs) request.getAttribute("product");
                            if (product.getId() != 0) {
                        %>

                        <form class="form-horizontal" action="saveupdate" method="POST" enctype="multipart/form-data">

                            <div class="form-group">
                                <label for="productId" class="col-sm-2 control-label">Id</label>
                                <div class="col-sm-10">
                                    <input type="text" size="11" name="productId" class="form-control"  readonly="readonly" value="<%=product.getId()%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productName" class="col-sm-2 control-label">Product Name</label>
                                <div class="col-sm-10">
                                    <input type="text" size="45"  name="ProductName" class="form-control"   value="<%=product.getName()%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="ProductDecription" class="col-sm-2 control-label">Product Decription</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" type="text "name="ProductDecription" class="form-control" size="256" rows="8" cols="32"><%=product.getDescription()%></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productName" class="col-sm-2 control-label">Product Price</label>
                                <div class="col-sm-10">
                                    <input type="text" size="11" name="ProductPrice" class="form-control"   value="<%=product.getPrice()%>">
                                </div>
                            </div>

                            <div class="form-group" >
                                <label class="col-sm-2 control-label">Image input </label>
                                <div class="col-sm-10">
                                    <input type="file" name="file" id="file" accept="image" class="form-control">
                                </div>
                            </div>

                            <div class="form-group" style="position:relative;top:20px;">
                                <label class="col-sm-2 control-label"> </label>
                                <div class="col-sm-offset-2 ">
                                    <button type="submit" class="btn btn-large btn-primary">Update </button>
                                </div>
                            </div>

                        </form>
                        <% } else {%>                           
                        <form class="form-horizontal" action="saveupdate" method="POST" enctype="multipart/form-data">

                            <div class="form-group">

                                <div class="col-sm-10">
                                    <input type="hidden"  name="productId" class="form-control" value="<%=product.getId()%>" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productName" class="col-sm-2 control-label">Product Name</label>
                                <div class="col-sm-10">
                                    <input type="text" size="45"  name="ProductName" class="form-control"   placeholder="fill the product name" >
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="ProductDecription" class="col-sm-2 control-label">Product Decription</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" type="text "name="ProductDecription" class="form-control" size="256" rows="8" cols="32" placeholder="please enter the product description here"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productName" class="col-sm-2 control-label">Product Price</label>
                                <div class="col-sm-10">
                                    <input type="text" size="11" name="ProductPrice" class="form-control"   placeholder="price">
                                </div>
                            </div>

                          

                            <div class="form-group" style="position:relative;top:20px;">
                                <label class="col-sm-2 control-label"> </label>
                                <div class="col-sm-offset-2 ">
                                    <button type="submit" class="btn btn-large btn-primary">Create </button>
                                </div>
                            </div>

                        </form>
                        <% }%>
                    </td>
                    <td style="width:60%;">
                        <%
                            List<ProductImages> productImageList = (List<ProductImages>) request.getAttribute("imageList");
                            if (productImageList!=null && !productImageList.isEmpty()) {
                        %>
                        <form action="imageMainDelete" method="POST">
                            <input type="hidden" name="productId" value="<%=product.getId()%>" />
                            <%
                                for (ProductImages image : productImageList) {
                                    String checked = "";
                                    if (image.isMainImage()) {
                                        checked = "checked";
                                    }
                                    int counter = 0;
                                    if (counter == 0) {
                            %>
                            <div class="row-fluid">
                                <div class="span4">
                                    <img class="img-circle" data-src="holder.js/140x140" alt="<%=product.getName()%>" src="../productsImages/<%=image.getFileName()%>" style="width: 140px; height: 140px;"> 
                                    <p> <input type="radio" name="mainImage" value="<%=image.getId()%>" <%=checked%>>set main ||
                                        <input type="checkbox" name="deleteImage" value="<%=image.getId()%>" >delete </p>
                                </div>

                                <% counter++;
                                } else if (counter == 1) {
                                %>

                                <div class="span4">
                                    <img class="img-circle" data-src="holder.js/140x140" alt="<%=product.getName()%>" src="../productsImages/<%=image.getFileName()%>" style="width: 140px; height: 140px;"> 
                                    <p> <input type="radio" name="mainImage" value="<%=image.getId()%>" <%=checked%>>set main ||
                                        <input type="checkbox" name="deleteImage" value="<%=image.getId()%>" >delete </p>
                                </div>

                                <% counter++;
                                } else if (counter == 2) {
                                %>
                                <div class="row-fluid">
                                    <div class="span4">
                                        <img class="img-circle" data-src="holder.js/140x140" alt="<%=product.getName()%>" src="../productsImages/<%=image.getFileName()%>" style="width: 140px; height: 140px;"> 
                                        <p> <input type="radio" name="mainImage" value="<%=image.getId()%>" <%=checked%>>set main ||
                                            <input type="checkbox" name="deleteImage" value="<%=image.getId()%>" >delete </p>
                                    </div>
                                </div>

                                <% counter = 0;
                                        }
                                    }
                                %>
                                <br> 

                                <button type="submit" class="btn btn-large btn-primary" style="float: right">Submit </button>
                        </form> 
                        <% }%>
                    </td>
                </table>
            </div>
        </div>





        <jsp:include page="CMSjspf/footer.jspf" />

    </body>
</html>
