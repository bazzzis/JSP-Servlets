<%-- 
    Document   : login
    Created on : Mar 8, 2015, 12:45:38 PM
    Author     : bazziss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../jspf/css.jspf"/>
        
        
        
        <title>Login Page</title>
        
         <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
        p{color:red;}
</style>
   
        
    </head>
    <jsp:include page="../jspf/header.jspf" />
    
    <body>
         <div class="container">
             

      <form class="form-signin" action="loginDispatcher" method="POST" style="position:relative;top:100px;" >
         
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" name="login" class="input-block-level" placeholder="Login">
        <input type="password" name="password" class="input-block-level" placeholder="Password">
        
        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
        <div class="error alert-error"> ${error} </div>
      </form>

    </div> 
        
        <jsp:include page="../jspf/footer.jspf"/>
    </body>
</html>
