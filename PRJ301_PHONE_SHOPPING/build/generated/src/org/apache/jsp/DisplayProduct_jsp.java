package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Entity.Category;
import Entity.Product;
import java.util.List;
import Model.DAOCategory;
import Entity.User;

public final class DisplayProduct_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/component/header.jsp");
    _jspx_dependants.add("/component/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>PHONESHOP | E-commerce template</title>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <!-- Mobile Specific Metas\n");
      out.write("        ================================================== -->\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"description\" content=\"Construction Html5 Template\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=5.0\">\n");
      out.write("        <meta name=\"author\" content=\"Themefisher\">\n");
      out.write("        <meta name=\"generator\" content=\"Themefisher Constra HTML Template v1.0\">\n");
      out.write("\n");
      out.write("        <!-- Favicon -->\n");
      out.write("        <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"images/favicon.png\" />\n");
      out.write("\n");
      out.write("        <!-- Themefisher Icon font -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"plugins/themefisher-font/style.css\">\n");
      out.write("        <!-- bootstrap.min css -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"plugins/bootstrap/css/bootstrap.min.css\">\n");
      out.write("\n");
      out.write("        <!-- Animate css -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"plugins/animate/animate.css\">\n");
      out.write("        <!-- Slick Carousel -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"plugins/slick/slick.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"plugins/slick/slick-theme.css\">\n");
      out.write("\n");
      out.write("        <!-- Main Stylesheet -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("    </head>\n");
      out.write("    <body id=\"body\">\n");
      out.write("        <!-- Start Top Header Bar -->\n");
      out.write("        <section class=\"top-header\" >\n");
      out.write("            <div class=\"container-fluid row\">\n");
      out.write("                <div class=\"col-lg-5 col-md-5\">\n");
      out.write("                    <div class=\"col-md-4 col-xs-12 col-sm-4\">\n");
      out.write("                        <!-- Site Logo -->\n");
      out.write("                        <div class=\"logo \" style=\"width: 100%;height: 100%;\" >\n");
      out.write("                            <a href=\"Home\">\n");
      out.write("                                <!-- replace logo here -->\n");
      out.write("                                <span style=\"font-size: 30px; font-family: 'Times New Roman', Times, serif, Helvetica, sans-serif;\" >PHONESHOP</span>\n");
      out.write("                            </a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    ");

                        User user = (User) session.getAttribute("user");
                    
      out.write("\n");
      out.write("                    <div id=\"navbar\" class=\"col-md-1 navbar-collapse collapse text-center\">\n");
      out.write("                        <ul class=\"nav navbar-nav\">\n");
      out.write("                            ");

                                if (user == null || user.getRoleName().equalsIgnoreCase(User.ROLE_CUSTOMER)) {

                            
      out.write("\n");
      out.write("                            <!-- Home -->\n");
      out.write("                            <li class=\"dropdown \">\n");
      out.write("                                <a href=\"Home\">HOME</a>\n");
      out.write("                            </li><!-- / Home -->\n");
      out.write("                            ");
} else {
      out.write("\n");
      out.write("                            <li><a href=\"ManagerProduct\">Product</a></li>\n");
      out.write("                            <li><a href=\"ManagerOrder\">Order</a></li>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                        </ul><!-- / .nav .navbar-nav -->\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"col-lg-3 col-md-5\">\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"col-lg-2 col-md-2 col-xs-12 col-sm-4\" style=\"margin-top: 12px;\">\n");
      out.write("                    ");

                        // if login as customer
                        if (user != null && user.getRoleName().equalsIgnoreCase(User.ROLE_CUSTOMER)) {
                    
      out.write("\n");
      out.write("\n");
      out.write("                    <!-- Cart -->\n");
      out.write("                    <ul class=\"top-menu text-right list-inline\">\n");
      out.write("                        <li class=\"dropdown cart-nav dropdown-slide\">\n");
      out.write("                            <a href=\"#!\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\" style=\"font-size: 20px;top:auto\">\n");
      out.write("                                <i class=\"tf-ion-android-cart mx-auto\" style=\"font-size: 25px;top:auto\"></i>Cart \n");
      out.write("                                <span class=\"badge bg-dark text-white ms-1 rounded-pill\"></span>\n");
      out.write("                            </a>\n");
      out.write("                            <div class=\"dropdown-menu cart-dropdown\">\n");
      out.write("                                <ul class=\"text-center cart-buttons\">\n");
      out.write("                                    <li><a href=\"Cart\" class=\"btn btn-small\">View</a></li>\n");
      out.write("                                </ul>\n");
      out.write("                            </div>\n");
      out.write("                        </li><!-- / Cart -->\n");
      out.write("                    </ul><!-- / .nav .navbar-nav .navbar-right -->    \n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                </div>\n");
      out.write("                ");
   // if not login
                    if (user == null) {
                
      out.write("\n");
      out.write("                <div class=\"col-lg-2 col-md-2\" style=\"margin-top: 12px;\">\n");
      out.write("                    <button class=\"btn\"> <a href=\"Login\">Login</a></button>\n");
      out.write("                </div>\n");
      out.write("                ");
} else if (user.getRoleName().equalsIgnoreCase(User.ROLE_CUSTOMER)) {
      out.write("       \n");
      out.write("                <div class=\"col-lg-2 col-md-2\" >\n");
      out.write("                    <ul class=\"nav navbar-nav\">\n");
      out.write("                        <li class=\"dropdown dropdown-slide\">\n");
      out.write("                            <a href=\"#!\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\"\n");
      out.write("                               data-delay=\"350\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"font-size: 18px\">Welcome ");
      out.print(user.getUsername());
      out.write("\n");
      out.write("                                <span class=\"tf-ion-ios-arrow-down\"></span></a>\n");
      out.write("                            <div class=\"dropdown-menu\">\n");
      out.write("                                <div class=\"row\">\n");
      out.write("                                    <div class=\"col-lg-6 col-md-6 mb-sm-3\">\n");
      out.write("                                        <ul>\n");
      out.write("                                            <li><a href=\"Profile\">Profile</a></li>\n");
      out.write("                                            <li><a href=\"MyOrder\">MyOrder</a></li>\n");
      out.write("                                            <li><a href=\"Logout\">Logout</a></li>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>    \n");
      out.write("                </div>\n");
      out.write("                ");
} else {
      out.write("\n");
      out.write("                <div class=\"col-lg-2 col-md-2\" style=\"margin-top: 12px;\">\n");
      out.write("                    <button class=\"btn\"> <a href=\"Logout\">Logout</a></button>\n");
      out.write("                </div>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </div>\n");
      out.write("    </section><!-- End Top Header Bar -->\n");
      out.write('\n');

    DAOCategory daoCategory = new DAOCategory();
    List<Category> listCategory = daoCategory.GetAllCategory();
    // get attribute
    String CategoryID = (String) request.getAttribute("CategoryID");
    List<Product> listProduct = (List<Product>) request.getAttribute("list");
    int pageSelected = (Integer) request.getAttribute("pageSelected");
    int numberPage = (Integer) request.getAttribute("number");
    String preURL = (String) request.getAttribute("previous");
    String nextURL = (String) request.getAttribute("next");
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");

      out.write("\n");
      out.write("<section class=\"products section bg-gray\">\n");
      out.write("    <div class=\"container-fluid\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-lg-12 title\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <button class=\"btn btn-primary\" style=\"margin-left: 50%\"><a href=\"ManagerProduct?service=AddProduct\">Add Product</a></button>\n");
      out.write("                    <p style=\"margin-left: 10%;color:red\">");
      out.print(message == null ? "" : message);
      out.write("</p>\n");
      out.write("                    <p style=\"margin-left: 10%;color:green\">");
      out.print(mess == null ? "" : mess);
      out.write("</p>\n");
      out.write("                    <form action=\"ManagerProduct\" style=\"margin-left: 10%\">\n");
      out.write("                        <select name=\"CategoryID\">\n");
      out.write("                            ");

                                for (Category category : listCategory) {
                            
      out.write("\n");
      out.write("                            <option value=\"");
      out.print(category.getID());
      out.write('"');
      out.write(' ');
      out.print(String.valueOf(category.getID()).equals(CategoryID) ? "selected" : "");
      out.write('>');
      out.print(category.getName());
      out.write("</option>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                        </select>\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-outline-primary ms-lg-2\">Search</button>\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                    ");

                        for (Product product : listProduct) {
                    
      out.write("\n");
      out.write("                    <div class=\"col-lg-4 text-center\">\n");
      out.write("                        <h2>");
      out.print(product.getProductName());
      out.write("</h2>\n");
      out.write("                        <img src=\"");
      out.print(product.getImage());
      out.write("\" alt=\"alt\" width=\"100\" height=\"100\"><br>\n");
      out.write("                        <p>Price: $");
      out.print(product.getPrice());
      out.write("</p>\n");
      out.write("                        <button class=\"btn btn-success\"><a href=\"ManagerProduct?service=EditProduct&ProductID=");
      out.print(product.getProductID());
      out.write("\">Edit</a></button>\n");
      out.write("                        <button class=\"btn btn-danger\"><a href=\"ManagerProduct?service=DeleteProduct&ProductID=");
      out.print(product.getProductID());
      out.write("\" onclick=\"return confirm('Are you sure you want to remove?')\">Remove</a></button>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        ");
 // if exist product sold
            if (listProduct.size() != 0) {
        
      out.write("\n");
      out.write("        <nav aria-label=\"Page navigation example\">\n");
      out.write("            <ul class=\"pagination justify-content-center\" style=\"margin-left: 60%\">\n");
      out.write("                ");
 // if not first page
                    if (pageSelected != 1) {
                
      out.write("\n");
      out.write("                <li class=\"page-item\"><a class=\"page-link\" href=\"");
      out.print(preURL);
      out.write("\">Previous</a></li>  \n");
      out.write("                    ");
}
      out.write("                                                         \n");
      out.write("                    ");
 // if not final page
                        if (pageSelected != numberPage) {
                    
      out.write("\n");
      out.write("                <li class=\"page-item\"><a class=\"page-link\" href=\"");
      out.print(nextURL);
      out.write("\">Next</a></li>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("            </ul>\n");
      out.write("        </nav>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("<footer class=\"footer section text-center\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-12\">\n");
      out.write("                <ul class=\"social-media\">\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">\n");
      out.write("                            <i class=\"tf-ion-social-facebook\"></i>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">\n");
      out.write("                            <i class=\"tf-ion-social-instagram\"></i>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">\n");
      out.write("                            <i class=\"tf-ion-social-twitter\"></i>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">\n");
      out.write("                            <i class=\"tf-ion-social-pinterest\"></i>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("                <ul class=\"footer-menu text-uppercase\">\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">CONTACT</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">SHOP</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">Pricing</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"#\">PRIVACY POLICY</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("                <p class=\"copyright-text\">Copyright &copy;2021, Designed &amp; Developed by <a\n");
      out.write("                        href=\"#\">Duc</a></p>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</footer>\n");
      out.write("    <!-- Main jQuery -->\n");
      out.write("    <script src=\"plugins/jquery/dist/jquery.min.js\"></script>\n");
      out.write("    <!-- Bootstrap 3.1 -->\n");
      out.write("    <script src=\"plugins/bootstrap/js/bootstrap.min.js\"></script>\n");
      out.write("    <!-- Bootstrap Touchpin -->\n");
      out.write("    <script src=\"plugins/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js\"></script>\n");
      out.write("    <!-- Instagram Feed Js -->\n");
      out.write("    <script src=\"plugins/instafeed/instafeed.min.js\"></script>\n");
      out.write("    <!-- Video Lightbox Plugin -->\n");
      out.write("    <script src=\"plugins/ekko-lightbox/dist/ekko-lightbox.min.js\"></script>\n");
      out.write("    <!-- Count Down Js -->\n");
      out.write("    <script src=\"plugins/syo-timer/build/jquery.syotimer.min.js\"></script>\n");
      out.write("\n");
      out.write("    <!-- slick Carousel -->\n");
      out.write("    <script src=\"plugins/slick/slick.min.js\"></script>\n");
      out.write("    <script src=\"plugins/slick/slick-animation.min.js\"></script>\n");
      out.write("\n");
      out.write("    <!-- Google Mapl -->\n");
      out.write("    <script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyCC72vZw-6tGqFyRhhg5CkF2fqfILn2Tsw\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"plugins/google-map/gmap.js\"></script>\n");
      out.write("\n");
      out.write("    <!-- Main Js File -->\n");
      out.write("    <script src=\"js/script.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
