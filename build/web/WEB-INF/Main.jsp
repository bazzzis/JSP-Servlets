<%-- 
    Document   : Main
    Created on : Mar 5, 2015, 10:35:46 AM
    Author     : Bazis
--%>

<%@page import="Domain.Article"%>
<%@page import="Domain.Carousel"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="jspf/css.jspf" />
        <title>Home page</title>
    </head>


    <jsp:include page="jspf/header.jspf" />

    <body>





        <!-- Carousel
      ================================================== -->
        <div id="myCarousel" class="carousel slide">
            <div class="carousel-inner">
                <% List<Carousel> cList = (List<Carousel>) request.getAttribute("carousel");
                    int counter = 0;
                    String active = "";
                    for (Carousel carousel : cList) {
                        if (counter == 0) {
                            active = "active";
                        } else {
                            active = "";
                        }


                %>


                <div class="item <%=active%>">
                    <img src="carousel/<%=carousel.getFileName()%>" alt="">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1><%=carousel.getHeader()%></h1>
                            <p class="lead"><%=carousel.getText()%></p>
                            <a class="btn btn-large btn-primary" href="<%=carousel.getButtonLink()%>"><%=carousel.getButtonName()%></a>
                        </div>
                    </div>
                </div>
                <% counter++;
                    }%>
            </div>
            <a class="left carousel-control" href="HomePage#myCarousel" data-slide="prev">‹</a>
            <a class="right carousel-control" href="HomePage#myCarousel" data-slide="next">›</a>
        </div><!-- /.carousel -->



        <!-- Wrap the rest of the page in another container to center all the content. -->

        <div class="container marketing">
            <%   List<Article> articleList = (List<Article>) request.getAttribute("articleList");
    if (articleList != null) {
        for (Article article : articleList) {%>
            <div class="featurette">
                <%=article.getArticle()%>
            </div>
            <hr class="featurette-divider">
            <% }
    }%>

        </div>


        <jsp:include page="jspf/footer.jspf" />
    </body>
</html>
