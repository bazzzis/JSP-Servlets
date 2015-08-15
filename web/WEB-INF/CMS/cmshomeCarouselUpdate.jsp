<%-- 
    Document   : products
    Created on : Mar 5, 2015, 1:35:05 PM
    Author     : Bazis
--%>



<%@page import="Domain.Carousel"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="CMSjspf/css.jspf" />
        <title>CMS Carousel</title>
    </head>
    <body>
        <jsp:include page="CMSjspf/header.jsp" />







        <div class="container marketing">
            <div class="featurette">
                ${info}
                <div class="error alert-error">${error} </div>

                <div class="container-fluid">

                    <h2 >Add new or edit Carousel</h2> 
                    

                </div>
                <%
                    if (request.getAttribute("carousel") != null) {
                        Carousel carousel = (Carousel) request.getAttribute("carousel");
                %>

                <form class="form-horizontal" action="CarouselSaveUpdate" method="POST" enctype="multipart/form-data">

                    <div class="form-group">
                        <label for="carouselId" class="col-sm-2 control-label">Id</label>
                        <div class="col-sm-10">
                            <input type="text" size="11" name="carouselId" class="form-control"  readonly="readonly" value="<%=carousel.getId()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="header" class="col-sm-2 control-label">Header</label>
                        <div class="col-sm-10">
                            <input type="text" size="45"  name="header" class="form-control"   value="<%=carousel.getHeader()%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="Text" class="col-sm-2 control-label">Text</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" type="text "name="Text" class="form-control" size="256" rows="8" cols="32"><%=carousel.getText()%></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="buttonLink" class="col-sm-2 control-label">Button link</label>
                        <div class="col-sm-10">
                            <input type="text" size="22" name="buttonLink" class="form-control"   value="<%=carousel.getButtonLink()%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="buttonName" class="col-sm-2 control-label">Button Name</label>
                        <div class="col-sm-10">
                            <input type="text" size="15" name="buttonName" class="form-control"   value="<%=carousel.getButtonName()%>">
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
                <form class="form-horizontal" action="CarouselSaveUpdate" method="POST" enctype="multipart/form-data">

                    <div class="form-group">

                        <div class="col-sm-10">
                            <input type="hidden" size="11" name="carouselId" class="form-control" value="0" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="header" class="col-sm-2 control-label">Header</label>
                        <div class="col-sm-10">
                            <input type="text" size="45"  name="header" class="form-control"   placeholder="Text header">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="Text" class="col-sm-2 control-label">Text</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" type="text "name="Text" class="form-control" size="256" rows="8" cols="32" placeholder="text to show on the carousel"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="buttonLink" class="col-sm-2 control-label">Button link</label>
                        <div class="col-sm-10">
                            <input type="text" size="30" name="buttonLink" class="form-control"   placeholder="for example: www.example.com">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="buttonName" class="col-sm-2 control-label">Button Name</label>
                        <div class="col-sm-10">
                            <input type="text" size="15" name="buttonName" class="form-control"   placeholder=" button name for link ">
                        </div>
                    </div>

                    <div class="form-group" >
                        <label class="col-sm-2 control-label">Image input (1550x550)</label>
                        <div class="col-sm-10">
                            <input type="file" name="file" id="file" accept="image" class="form-control">
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

            </div>
        </div>





        <jsp:include page="CMSjspf/footer.jspf" />

    </body>
</html>
