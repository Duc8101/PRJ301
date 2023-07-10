package Controller;

import Const.ConstValue;
import Entity.Product;
import Entity.User;
import Model.DAOProduct;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        DAOProduct daoProduct = new DAOProduct();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRoleName().equalsIgnoreCase(ConstValue.ROLE_CUSTOMER)) {
            String CategoryID = request.getParameter("CategoryID") == null ? "0" : request.getParameter("CategoryID");
            String properties = request.getParameter("properties");
            String flow = request.getParameter("flow");
            int catID = Integer.parseInt(CategoryID);
            int number = daoProduct.getNumberPage(catID);
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            List<Product> list = daoProduct.getListProduct(ConstValue.ROLE_CUSTOMER, catID, page, properties, flow);
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL;
            String nextURL;
            String firstURL;
            String lastURL;
            // if not sort
            if (properties == null && flow == null) {
                // if not choose category
                if (CategoryID == null) {
                    preURL = "Home" + "?page=" + prePage;
                    nextURL = "Home" + "?page=" + nextPage;
                    firstURL = "Home";
                    lastURL = "Home" + "?page=" + number;
                } else {
                    preURL = "Home" + "?CategoryID=" + catID + "&page=" + prePage;
                    nextURL = "Home" + "?CategoryID=" + catID + "&page=" + nextPage;
                    firstURL = "Home" + "?CategoryID=" + catID;
                    lastURL = "Home" + "?CategoryID=" + catID + "&page=" + number;
                }
            } else {
                // if not choose category
                if (CategoryID == null) {
                    preURL = "Home" + "?properties=" + properties + "&flow=" + flow + "&page=" + prePage;
                    nextURL = "Home" + "?properties=" + properties + "&flow=" + flow + "&page=" + nextPage;
                    firstURL = "Home" + "?properties=" + properties + "&flow=" + flow;
                    lastURL = "Home" + "?properties=" + properties + "&flow=" + flow + "&page=" + number;
                } else {
                    preURL = "Home" + "?CategoryID=" + catID + "&properties=" + properties + "&flow=" + flow + "&page=" + prePage;
                    nextURL = "Home" + "?CategoryID=" + catID + "&properties=" + properties + "&flow=" + flow + "&page=" + nextPage;
                    firstURL = "Home" + "?CategoryID=" + catID + "&properties=" + properties + "&flow=" + flow;
                    lastURL = "Home" + "?CategoryID=" + catID + "&properties=" + properties + "&flow=" + flow + "&page=" + number;
                }
            }
            request.setAttribute("CategoryID", catID);
            request.setAttribute("flow", flow);
            request.setAttribute("properties", properties);
            request.setAttribute("list", list);
            request.setAttribute("number", number);
            request.setAttribute("pageSelected", page);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            request.setAttribute("first", firstURL);
            request.setAttribute("last", lastURL);
            Dispatcher.forward(request, response, "/WEB-INF/Home/Index.jsp");
        } else {
            response.sendRedirect("ManagerProduct");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
