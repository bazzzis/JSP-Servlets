<%-- 
    Document   : cmshome
    Created on : Mar 8, 2015, 7:38:48 PM
    Author     : bazziss
--%>

<%@page import="Domain.Article"%>
<%@page import="java.util.List"%>
<%@page import="Domain.Carousel"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="CMSjspf/css.jspf" />
        <title>CMS Home Content</title>

    </head>
    <body>
        <jsp:include page="CMSjspf/header.jsp" />

        <%   List<Article> articleList = (List<Article>) request.getAttribute("articleList");  %>
        <div class="container marketing"  style="position:relative;top:100px;">
            ${info}
            <div class="error alert-error">${error} </div>


            <%

                if (articleList != null) {
                    for (Article article : articleList) {
            %>

            <form action="CMSHomeContent" method="POST">

                <h1> <label for="editor1">Article <%=article.getArticleId()%> edit ||<a href="CMSHomeContent?delete=<%=article.getArticleId()%>"> delete </a> </label></h1>
                <input type="hidden" name="articleId" class="form-control" value="<%=article.getArticleId()%>">
                <textarea cols="160"  rows="50" class="ckeditor" id="editor1" name="article<%=article.getArticleId()%>"> <%=article.getArticle()%> </textarea>

                <p>
                    <button type="submit" class="btn btn-large btn-primary">Update </button>
                </p>
                <ckeditor:replace replace="article<%=article.getArticleId()%>" basePath="../ckeditor/"/>
            </form>
            <% }
                }%>
            <form action="CMSHomeContent" method="POST" >

                <h1> <label for="articlenew">New article</label></h1>
                <input type="hidden" name="articleId" class="form-control" value="new">
                <textarea cols="160"  rows="50" class="ckeditor" id="articlenew" name="articlenew">  </textarea>

                <p>
                    <button type="submit" class="btn btn-large btn-primary">Create </button>
                </p>
                <ckeditor:replace replace="articlenew" basePath="../ckeditor/"/>
            </form> 

        </div>

        <jsp:include page="CMSjspf/footer.jspf" />

    </body>
</html>
