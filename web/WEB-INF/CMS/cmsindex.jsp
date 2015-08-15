<%-- 
    Document   : cmshome
    Created on : Mar 8, 2015, 7:38:48 PM
    Author     : bazziss
--%>
<HTML>
    <head>
        <%@page import="Domain.User"%>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <jsp:include page="../jspf/css.jspf" />
<title>CMS entrance</title>
        <!DOCTYPE html>
</HEAD>



<body>
    <div class="navbar-wrapper">
        <!-- Wrap the .navbar in .container to center it within the absolutely positioned parent. -->
        <div class="container">

            <div class="navbar navbar-inverse">
                <div class="navbar-inner">
                    <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
                    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <div class="nav-collapse collapse">
                        <ul class="nav">

                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">CMSHome <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="CMS/CMSHomeCarousel">Edit Carousel</a></li>
                                    <li><a href="CMS/CMSHomeContent">Edit Content</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">CMSProducts <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="CMS/CMSProducts">Edit existind products</a></li>
                                    <li><a href="CMS/CMSProduct?productId=0">Add a new product</a></li>
                                </ul>
                            </li>
                            <li><a href="CMS/CMSContact">CMSContact</a></li>
                                <% User user = (User) session.getAttribute("MAIN_USER");%>
                            <li><a href="CMS/Logoff">Logoff <%=user.getUsername()%> </a></li>
                        </ul>

                    </div><!--/.nav-collapse -->
                </div><!-- /.navbar-inner -->
            </div><!-- /.navbar -->

        </div> <!-- /.container -->
    </div><!-- /.navbar-wrapper -->


    <div class="container marketing">
        <div class="featurette">
            <div class="container-fluid">
                This is a CMS start page, please choose the link to follow
            </div>
        </div>
    </div>
    <div class="container  marketing">
    <div class="featurette">
        <div class="container-fluid">
            <hr size="5" style="color: gray; background-color: gray;">

            <a href="http://www.iucosoft.com" style="text-align:center;">iucosoft.com</a> 2015
        </div>
    </div>
</div>
      <script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap-transition.js"></script>
<script src="bootstrap/js/bootstrap-alert.js"></script>
<script src="bootstrap/js/bootstrap-modal.js"></script>
<script src="bootstrap/js/bootstrap-dropdown.js"></script>
<script src="bootstrap/js/bootstrap-scrollspy.js"></script>
<script src="bootstrap/js/bootstrap-tab.js"></script>
<script src="bootstrap/js/bootstrap-tooltip.js"></script>
<script src="bootstrap/js/bootstrap-popover.js"></script>
<script src="bootstrap/js/bootstrap-button.js"></script>
<script src="bootstrap/js/bootstrap-collapse.js"></script>

<script src="carousel/bootstrap-carousel.js"></script>
<script src="bootstrap/js/bootstrap-typeahead.js"></script>
</body>
</html>
