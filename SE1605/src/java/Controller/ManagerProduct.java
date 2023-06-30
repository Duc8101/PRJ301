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

public class ManagerProduct extends HttpServlet {

    private final DAOProduct daoProduct = new DAOProduct();
    private final DAOStock daoStock = new DAOStock();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String service, String brand, String category)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (service.equals("DisplayProduct")) {
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            int StoreID = (int) session.getAttribute("StoreID");
            int number = daoProduct.getNumberPage(brand, category);
            List<Product> listProduct = daoProduct.getListProduct(ConstValue.ROLE_STAFF, page, StoreID, brand, category);
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL;
            String nextURL;
            String firstURL;
            String lastURL;
            // if all product
            if (category == null && brand == null) {
                preURL = "ManagerProduct" + "?page=" + prePage;
                nextURL = "ManagerProduct" + "?page=" + nextPage;
                firstURL = "ManagerProduct";
                lastURL = "ManagerProduct" + "?page=" + number;
            } else {
                preURL = "ManagerProduct" + "?category=" + category + "&brand=" + brand + "&page=" + prePage;
                nextURL = "ManagerProduct" + "?category=" + category + "&brand=" + brand + "&page=" + nextPage;
                firstURL = "ManagerProduct" + "?category=" + category + "&brand=" + brand;
                lastURL = "ManagerProduct" + "?category=" + category + "&brand=" + brand + "&page=" + number;
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
            Dispatcher.forward(request, response, "DisplayProduct.jsp");
        }

        if (service.equals("AddProduct")) {
            Product product = new Product();
            request.setAttribute("product", product);
            Dispatcher.forward(request, response, "AddProduct.jsp");
        }

        if (service.equals("EditProduct")) {
            String ID = request.getParameter("ID");
            Product product = daoProduct.getProduct(ID);
            request.setAttribute("product", product);
            Dispatcher.forward(request, response, "EditProduct.jsp");
        }

        if (service.equals("DeleteProduct")) {
            String ProductID = request.getParameter("ID");
            int StoreID = (int) session.getAttribute("StoreID");
            int number = daoStock.DeleteStock(StoreID, ProductID);
            // if delete successful
            if (number > 0) {
                request.setAttribute("mess", "Delete successful");
                processRequest(request, response, "DisplayProduct", null, null);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service") == null ? "DisplayProduct" : request.getParameter("service");
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        processRequest(request, response, service, brand, category);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        HttpSession session = request.getSession();
        int StoreID = (int) session.getAttribute("StoreID");
        String ProductName = request.getParameter("name").trim();
        String Price = request.getParameter("price").trim();
        double price = Double.parseDouble(Price);
        String Year = request.getParameter("year");
        int year = Integer.parseInt(Year);
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        if (service.equals("AddProduct")) {
            Product product = new Product(ProductName, year, price, brand, category);
            request.setAttribute("product", product);
            if (ProductName.isEmpty()) {
                request.setAttribute("message", "You have to input product name");
                Dispatcher.forward(request, response, "AddProduct.jsp");
            } else if (price <= 0) {
                request.setAttribute("message", "Price must be greater than 0");
                Dispatcher.forward(request, response, "AddProduct.jsp");
            } else {
                daoProduct.AddProduct(product);
                Product productAdd = daoProduct.getProductAdd();
                Stock stock = new Stock(StoreID, productAdd.getID(), 0);
                int number = daoStock.AddStock(stock);
                // if add successful
                if (number > 0) {
                    request.setAttribute("mess", "Add successful");
                    processRequest(request, response, "DisplayProduct", null, null);
                }
            }
        } else {
            String ID = request.getParameter("ID");
            String Quantity = request.getParameter("quantity");
            int Id = Integer.parseInt(ID);
            int quantity = Integer.parseInt(Quantity);
            Product product = new Product(Id, ProductName, year, price, brand, category);
            request.setAttribute("product", product);
            if (ProductName.isEmpty()) {
                request.setAttribute("message", "You have to input product name");
                Dispatcher.forward(request, response, "EditProduct.jsp");
            } else if (price <= 0) {
                request.setAttribute("message", "Price must be greater than 0");
                Dispatcher.forward(request, response, "EditProduct.jsp");
            } else {
                Stock stock = new Stock(StoreID, product.getID(), quantity);
                int numberProduct = daoProduct.UpdateProduct(product);
                int numberStock = daoStock.UpdateStock(stock);
                // if edit successful
                if (numberProduct > 0 && numberStock > 0) {
                    request.setAttribute("mess", "Edit successful");
                    processRequest(request, response, "DisplayProduct", null, null);
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
