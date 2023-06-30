package Controller;

import Const.ConstValue;
import Entity.*;
import Model.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Cart extends HttpServlet {

    private final DAOProduct daoProduct = new DAOProduct();
    private final DAOCustomer daoCustomer = new DAOCustomer();
    private final DAOStaff daoStaff = new DAOStaff();
    private final DAOOrder daoOrder = new DAOOrder();
    private final DAOOrderItem daoItem = new DAOOrderItem();
    private final DAOStock daoStock = new DAOStock();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String service = request.getParameter("service") == null ? "ShowCart" : request.getParameter("service");
        if (username == null) {
            response.sendRedirect("Login");
        } else {
            if (service.equals("AddCart")) {
                String ID = request.getParameter("ID");
                Integer quantity = (Integer) session.getAttribute(ID);
                // if product not exist in cart
                if (quantity == null) {
                    session.setAttribute(ID, 1);
                } else {
                    session.setAttribute(ID, quantity + 1);
                }
                response.sendRedirect("Cart");
            }

            if (service.equals("ShowCart")) {
                Map<Product, Integer> map = getListCart(request);
                request.setAttribute("map", map);
                Dispatcher.forward(request, response, "ShowCart.jsp");
            }

            if (service.equals("RemoveCart")) {
                String ID = request.getParameter("ID");
                session.removeAttribute(ID);
                response.sendRedirect("Cart");
            }

            if (service.equals("CheckOut")) {
                Map<Product, Integer> map = getListCart(request);
                request.setAttribute("map", map);
                Dispatcher.forward(request, response, "CheckOut.jsp");
            }
        }

    }

    private Map<Product, Integer> getListCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Product, Integer> map = new HashMap<>();
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String ProductID = enumeration.nextElement();
            if (!ProductID.equals("username") && !ProductID.equals("store") && !ProductID.equals("StoreID")) {
                int quantity = (int) session.getAttribute(ProductID);
                Product product = daoProduct.getProduct(ProductID);
                map.put(product, quantity);
            }
        }
        return map;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private String CheckDate(String dateStr) {
        //use class SimpleDateFormat to initialize format date is yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String current = LocalDate.now().toString();
        if (dateStr.isEmpty()) {
            return "Required date not empty";
        }
        try {
            Date date = sdf.parse(dateStr);
            Date DateCurrent = sdf.parse(current);
            // if date in the past
            if (date.before(DateCurrent)) {
                return "Required date not in the past";
            }
        } catch (ParseException e) {
            return "Wrong format date";
        }
        return "";
    }

    private Product CheckQuantityProduct(Map<Product, Integer> map, int StoreID) {
        Set<Product> set = map.keySet();
        for (Product product : set) {
            int quantity = map.get(product);
            Stock stock = daoStock.getStock(StoreID, product.getID());
            // if quantity product in cart greater than quantity product in stock
            if (quantity - stock.getQuantity() > 0) {
                return product;
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requiredDate = request.getParameter("required").trim();
        HttpSession session = request.getSession();
        Map<Product, Integer> map = getListCart(request);
        request.setAttribute("map", map);
        String message = CheckDate(requiredDate);
        String username = (String) session.getAttribute("username");
        int StoreID = (int) session.getAttribute("StoreID");
        Customer customer = daoCustomer.getCustomer(username);
        Random random = new Random();
        List<Staff> list = daoStaff.getListStaff(StoreID);
        Staff staff = list.get(random.nextInt(list.size()));
        if (message.isEmpty()) {
            Product product = CheckQuantityProduct(map, StoreID);
            // if quantity of all product in cart valid
            if (product == null) {
                Order order = new Order(customer.getID(), ConstValue.INT_STATUS_WAIT, null, requiredDate, null, StoreID, staff.getStaffID());
                daoOrder.AddOrder(order);
                Order orderAdd = daoOrder.getOrderAdd();
                int ItemID = 1;
                int number = 0;
                Set<Product> set = map.keySet();
                for (Product pro : set) {
                    int quantity = map.get(pro);
                    OrderItem item = new OrderItem(orderAdd.getOrderID(), ItemID, pro.getID(), quantity);
                    number = daoItem.AddOrderItem(item);
                    ItemID++;
                }
                // if add successful
                if (number > 0) {
                    for (Product pro : set) {
                        int quantity = map.get(pro);
                        Stock stock = daoStock.getStock(StoreID, pro.getID());
                        stock.setQuantity(stock.getQuantity() - quantity);
                        daoStock.UpdateStock(stock);
                        session.removeAttribute(pro.getID() + "");
                    }
                    request.setAttribute("mess", "Check out successful");
                }
            } else {
                Stock stock = daoStock.getStock(StoreID, product.getID());
                request.setAttribute("message", "Store only has " + stock.getQuantity() + " product named \"" + product.getProductName() + "\" left");
            }
        } else {
            request.setAttribute("message", message);
        }
        Dispatcher.forward(request, response, "CheckOut.jsp");
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
