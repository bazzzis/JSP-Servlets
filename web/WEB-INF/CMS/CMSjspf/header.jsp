





<%@page import="Domain.User"%>
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
                                <li><a href="CMSHomeCarousel">Edit Carousel</a></li>
                                <li><a href="CMSHomeContent">Edit Content</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">CMSProducts <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="CMSProducts">Edit existind products</a></li>
                                <li><a href="CMSProduct?productId=0">Add a new product</a></li>
                            </ul>
                        </li>
                        <li><a href="CMSContact">CMSContact</a></li>
                            <% User user = (User) session.getAttribute("MAIN_USER");%>
                        <li><a href="Logoff">Logoff <%=user.getUsername()%> </a></li>
                    </ul>
                   
                </div><!--/.nav-collapse -->
            </div><!-- /.navbar-inner -->
        </div><!-- /.navbar -->

    </div> <!-- /.container -->
</div><!-- /.navbar-wrapper -->

