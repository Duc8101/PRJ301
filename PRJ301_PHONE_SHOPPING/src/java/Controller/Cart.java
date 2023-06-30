package Controller;

import Entity.*;
import Model.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Cart extends HttpServlet {

    private final DAOProduct daoProduct = new DAOProduct();
    private final DAOOrder daoOrder = new DAOOrder();
    private final DAOUser daoUser = new DAOUser();
    private final DAOOrderDetail daoDetail = new DAOOrderDetail();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String service = request.getParameter("service") == null ? "ShowCart" : request.getParameter("service");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("Login");
        } else {
            if (service.equals("AddCart")) {
                String ProductID = request.getParameter("ProductID");
                Integer quantity = (Integer) session.getAttribute(ProductID);
                // if product not exist in cart
                if (quantity == null) {
                    session.setAttribute(ProductID, 1);
                } else {
                    session.setAttribute(ProductID, quantity + 1);
                }
                response.sendRedirect("Cart");
            }

            if (service.equals("ShowCart")) {
                Map<Product, Integer> map = getListCart(request);
                request.setAttribute("map", map);
                Dispatcher.forward(request, response, "ShowCart.jsp");
            }

            if (service.equals("RemoveCart")) {
                String ProductID = request.getParameter("ProductID");
                session.removeAttribute(ProductID);
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
        double sum = 0;
        while (enumeration.hasMoreElements()) {
            String ProductID = enumeration.nextElement();
            if (!ProductID.equals("user")) {
                int quantity = (int) session.getAttribute(ProductID);
                Product product = daoProduct.getProduct(ProductID);
                sum = sum + product.getPrice() * quantity;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String address = request.getParameter("address").trim();
        String Sum = request.getParameter("sum");
        double sum = Double.parseDouble(Sum);
        User user = (User) session.getAttribute("user");
        Map<Product, Integer> map = getListCart(request);
        request.setAttribute("map", map);
        if (address.isEmpty()) {
            request.setAttribute("message", "You have to input address");
        } else if (user.getMoney() - sum < 0) {
            request.setAttribute("message", "You don't have enough money to check out");
        } else {
            Order order = new Order(user.getID(), Order.STATUS_NEW, null, address);
            daoOrder.AddOrder(order);
            List<Order> list = daoOrder.getListOrder(null, 0);
            int OrderID = list.get(list.size() - 1).getOrderID();
            int number = 0;
            Set<Product> set = map.keySet();
            for (Product key : set) {
                int quantity = map.get(key);
                OrderDetail detail = new OrderDetail(OrderID, key.getProductID(), quantity);
                number = daoDetail.AddOrderDetail(detail);
            }
            // if add successful
            if (number > 0) {
                User seller = daoUser.getUser("seller");
                double money = user.getMoney() - sum;
                user.setMoney(money);
                seller.setMoney(seller.getMoney() + sum);
                daoUser.UpdateMoney(user.getUsername(), money);
                daoUser.UpdateMoney(seller.getUsername(), seller.getMoney());
                session.setAttribute("user", user);
                request.setAttribute("address", address);
                request.setAttribute("mess", "Check out successful");
            }
        }
        Dispatcher.forward(request, response, "CheckOut.jsp");
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
