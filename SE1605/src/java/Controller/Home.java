package Controller;

import Const.ConstValue;
import Entity.*;
import Model.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOProduct daoProduct = new DAOProduct();
        DAOStaff daoStaff = new DAOStaff();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        // if not login or login as customer
        if (username == null || daoStaff.getStaff(username) == null) {
            String category = request.getParameter("category");
            String brand = request.getParameter("brand");
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            int StoreID = username == null ? 0 : (int) session.getAttribute("StoreID");
            int number = daoProduct.getNumberPage(brand, category);
            List<Product> listProduct = daoProduct.getListProduct(ConstValue.ROLE_CUSTOMER, page, StoreID, brand, category);
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL;
            String nextURL;
            String firstURL;
            String lastURL;
            // if all product
            if (category == null && brand == null) {
                preURL = "Home" + "?page=" + prePage;
                nextURL = "Home" + "?page=" + nextPage;
                firstURL = "Home";
                lastURL = "Home" + "?page=" + number;
            } else {
                preURL = "Home" + "?category=" + category + "&brand=" + brand + "&page=" + prePage;
                nextURL = "Home" + "?category=" + category + "&brand=" + brand + "&page=" + nextPage;
                firstURL = "Home" + "?category=" + category + "&brand=" + brand;
                lastURL = "Home" + "?category=" + category + "&brand=" + brand + "&page=" + number;
            }
            request.setAttribute("category", category);
            request.setAttribute("brand", brand);
            request.setAttribute("number", number);
            request.setAttribute("pageSelected", page);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("listCategory", ConstValue.LIST_CATEGORY);
            request.setAttribute("listBrand", ConstValue.LIST_BRAND);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            request.setAttribute("first", firstURL);
            request.setAttribute("last", lastURL);
            Dispatcher.forward(request, response, "index.jsp");
        } else {
            response.sendRedirect("ManagerProduct");
        }
    }

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
