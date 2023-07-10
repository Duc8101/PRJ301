<%@page import="Const.ConstValue"%>
<%@page import="Entity.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PHONESHOP | E-commerce template</title>
        <meta charset="utf-8">
        <!-- Mobile Specific Metas
        ================================================== -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Construction Html5 Template">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
        <meta name="author" content="Themefisher">
        <meta name="generator" content="Themefisher Constra HTML Template v1.0">

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="resources/images/favicon.png" />

        <!-- Themefisher Icon font -->
        <link rel="stylesheet" href="resources/plugins/themefisher-font/style.css">
        <!-- bootstrap.min css -->
        <link rel="stylesheet" href="resources/plugins/bootstrap/css/bootstrap.min.css">

        <!-- Animate css -->
        <link rel="stylesheet" href="resources/plugins/animate/animate.css">
        <!-- Slick Carousel -->
        <link rel="stylesheet" href="resources/plugins/slick/slick.css">
        <link rel="stylesheet" href="resources/plugins/slick/slick-theme.css">

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="resources/css/style.css">
    </head>
    <body id="body">
        <!-- Start Top Header Bar -->
        <section class="top-header" >
            <div class="container-fluid row">
                <div class="col-lg-5 col-md-5">
                    <div class="col-md-4 col-xs-12 col-sm-4">
                        <!-- Site Logo -->
                        <div class="logo " style="width: 100%;height: 100%;" >
                            <a href="">
                                <!-- replace logo here -->
                                <span style="font-size: 30px; font-family: 'Times New Roman', Times, serif, Helvetica, sans-serif;" >PHONESHOP</span>
                            </a>
                        </div>
                    </div>
                    <%
                        User user = (User) session.getAttribute("user");
                    %>
                    <div id="navbar" class="col-md-1 navbar-collapse collapse text-center">
                        <ul class="nav navbar-nav">
                            <%
                                if (user == null || user.getRoleName().equalsIgnoreCase(ConstValue.ROLE_CUSTOMER)) {
                            %>
                            <!-- Home -->
                            <li class="dropdown ">
                                <a href="Home">HOME</a>
                            </li><!-- / Home -->
                            <%
                                if (user != null) {
                            %>
                            <a href="ViewSeller">Seller</a>
                            <%}%>
                            <%} else {%>
                            <li><a href="ManagerProduct">Product</a></li>
                            <li><a href="ManagerOrder">Order</a></li>
                                <%}%>
                        </ul><!-- / .nav .navbar-nav -->

                    </div>
                </div>

                <div class="col-lg-3 col-md-5">

                </div>

                <div class="col-lg-2 col-md-2 col-xs-12 col-sm-4" style="margin-top: 12px;">
                    <%
                        if (user != null && user.getRoleName().equalsIgnoreCase(ConstValue.ROLE_CUSTOMER)) {
                    %>
                    <!-- Cart -->
                    <ul class="top-menu text-right list-inline">
                        <li class="dropdown cart-nav dropdown-slide">
                            <a href="#!" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" style="font-size: 20px;top:auto">
                                <i class="tf-ion-android-cart mx-auto" style="font-size: 25px;top:auto"></i>Cart 
                                <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
                            </a>
                            <div class="dropdown-menu cart-dropdown">
                                <ul class="text-center cart-buttons">
                                    <li><a href="Cart" class="btn btn-small">View</a></li>
                                </ul>
                            </div>
                        </li><!-- / Cart -->
                    </ul><!-- / .nav .navbar-nav .navbar-right -->    
                    <%}%>
                </div>
                <%   // if not login
                    if (user == null) {
                %>
                <div class="col-lg-2 col-md-2" style="margin-top: 12px;">
                    <button class="btn"> <a href="Login">Login</a></button>
                </div>
                <%} else {%>       
                <div class="col-lg-2 col-md-2" >
                    <ul class="nav navbar-nav">
                        <li class="dropdown dropdown-slide">
                            <a href="#!" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                               data-delay="350" role="button" aria-haspopup="true" aria-expanded="false" style="font-size: 18px">Welcome <%=user.getUsername()%>
                                <span class="tf-ion-ios-arrow-down"></span></a>
                            <div class="dropdown-menu">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 mb-sm-3">
                                        <ul>
                                            <li><a href="Profile">Profile</a></li>
                                                <%
                                                    if (user.getRoleName().equals(ConstValue.ROLE_CUSTOMER)) {
                                                %>
                                            <li><a href="MyOrder">MyOrder</a></li>
                                                <%}%>
                                            <li><a href="Logout">Logout</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>    
                </div>
                <%}%>
            </div>
        </section><!-- End Top Header Bar -->
