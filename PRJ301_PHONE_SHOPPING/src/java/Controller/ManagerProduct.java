package Controller;

import Entity.*;
import Model.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerProduct extends HttpServlet {

    private final DAOProduct daoProduct = new DAOProduct();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String service, String CategoryID)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (service.equals("DisplayProduct")) {
            int CatID = CategoryID == null ? 0 : Integer.parseInt(CategoryID);
            int number = daoProduct.getNumberOfPage(CatID);
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            List<Product> list = daoProduct.getListProduct(User.ROLE_SELLER, CatID, page, null, null);
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL;
            String nextURL;
            String firstURL;
            String lastURL;
            if (CategoryID == null) {
                preURL = "ManagerProduct" + "?page=" + prePage;
                nextURL = "ManagerProduct" + "?page=" + nextPage;
                firstURL = "ManagerProduct";
                lastURL = "ManagerProduct" + "?page=" + number;
            } else {
                preURL = "ManagerProduct" + "?CategoryID=" + CategoryID + "&page=" + prePage;
                nextURL = "ManagerProduct" + "?CategoryID=" + CategoryID + "&page=" + nextPage;
                firstURL = "ManagerProduct" + "?CategoryID=" + CategoryID;
                lastURL = "ManagerProduct" + "?CategoryID=" + CategoryID + "&page=" + number;
            }
            request.setAttribute("CategoryID", CatID);
            request.setAttribute("list", list);
            request.setAttribute("number", number);
            request.setAttribute("pageSelected", page);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            request.setAttribute("first", firstURL);
            request.setAttribute("last", lastURL);
            Dispatcher.forward(request, response, "DisplayProduct.jsp");
        }

        if (service.equals("AddProduct")) {
            Product product = new Product();
            request.setAttribute("product", product);
            Dispatcher.forward(request, response, "AddProduct.jsp");
        }

        if (service.equals("EditProduct")) {
            String ProductID = request.getParameter("ProductID");
            Product product = daoProduct.getProduct(ProductID);
            request.setAttribute("product", product);
            Dispatcher.forward(request, response, "EditProduct.jsp");
        }

        if (service.equals("DeleteProduct")) {
            String ProductID = request.getParameter("ProductID");
            int number = daoProduct.DeleteProduct(ProductID);
            // if delete successful
            if (number > 0) {
                request.setAttribute("mess", "Delete successful");
                processRequest(request, response, "DisplayProduct", null);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service") == null ? "DisplayProduct" : request.getParameter("service");
        String CategoryID = request.getParameter("CategoryID");
        processRequest(request, response, service, CategoryID);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        String NewProductName = request.getParameter("name").trim();
        String image = request.getParameter("image").trim();
        String Price = request.getParameter("price").trim();
        double price = Double.parseDouble(Price);
        String CategoryID = request.getParameter("CategoryID");
        int CatID = Integer.parseInt(CategoryID);
        Product product = new Product(NewProductName, image, price, CatID, false);
        if (service.equals("AddProduct")) {
            request.setAttribute("product", product);
            if (NewProductName.isEmpty()) {
                request.setAttribute("message", "You have to input product name");
                Dispatcher.forward(request, response, "AddProduct.jsp");
            } else if (daoProduct.isExist(product.getProductName())) {
                request.setAttribute("message", "Product name existed");
                Dispatcher.forward(request, response, "AddProduct.jsp");
            } else if (price <= 0) {
                request.setAttribute("message", "Price must be greater than 0");
                Dispatcher.forward(request, response, "AddProduct.jsp");
            } else {
                int number = daoProduct.AddProduct(product);
                // if add successful
                if (number > 0) {
                    request.setAttribute("mess", "Add successful");
                    processRequest(request, response, "DisplayProduct", null);
                }
            }
        } else {
            String oldName = request.getParameter("old");
            String ProductID = request.getParameter("ProductID");
            String IsSold = request.getParameter("isSold");
            boolean isSold = IsSold != null;
            int productID = Integer.parseInt(ProductID);
            product = new Product(productID, NewProductName, image, price, CatID, isSold);
            request.setAttribute("product", product);
            if (NewProductName.isEmpty()) {
                request.setAttribute("message", "You have to input product name");
                Dispatcher.forward(request, response, "EditProduct.jsp");
            } else if (daoProduct.isExist(product.getProductName()) && !NewProductName.equalsIgnoreCase(oldName)) {
                request.setAttribute("message", "Product name existed");
                Dispatcher.forward(request, response, "EditProduct.jsp");
            } else if (price <= 0) {
                request.setAttribute("message", "Price must be greater than 0");
                Dispatcher.forward(request, response, "EditProduct.jsp");
            } else {
                int number = daoProduct.UpdateProduct(product);
                // if edit successful
                if (number > 0) {
                    request.setAttribute("mess", "Edit successful");
                    processRequest(request, response, "DisplayProduct", null);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
