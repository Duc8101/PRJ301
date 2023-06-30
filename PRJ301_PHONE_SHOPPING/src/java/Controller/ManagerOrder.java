package Controller;

import Entity.*;
import Model.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerOrder extends HttpServlet {

    private final DAOOrder daoOrder = new DAOOrder();
    private final DAOOrderDetail daoDetail = new DAOOrderDetail();
    private final DAOProduct daoProduct = new DAOProduct();
    private final DAOUser daoUser = new DAOUser();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service") == null ? "DisplayOrder" : request.getParameter("service");
        if (service.equals("DisplayOrder")) {
            String status = request.getParameter("status");
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            List<Order> list = daoOrder.getListOrder(status, page);
            int number = daoOrder.getNumberOfPage(status);
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL;
            String nextURL;
            String firstURL;
            String lastURL;
            if (status == null) {
                preURL = "ManagerOrder?page=" + prePage;
                nextURL = "ManagerOrder?page=" + nextPage;
                firstURL = "ManagerOrder";
                lastURL = "ManagerOrder?page=" + number;
            } else {
                preURL = "ManagerOrder?status=" + status + "&page=" + prePage;
                nextURL = "ManagerOrder?status=" + status + "&page=" + nextPage;
                firstURL = "ManagerOrder?status=" + status;
                lastURL = "ManagerOrder?status=" + status + "&page=" + number;
            }
            request.setAttribute("list", list);
            request.setAttribute("status", status);
            request.setAttribute("number", number);
            request.setAttribute("pageSelected", page);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            request.setAttribute("first", firstURL);
            request.setAttribute("last", lastURL);
            Dispatcher.forward(request, response, "DisplayOrder.jsp");
        }

        if (service.equals("EditOrder")) {
            String OrderID = request.getParameter("OrderID");
            int orderID = Integer.parseInt(OrderID);
            Order order = daoOrder.getOrder(orderID);
            List<String> list = getListStatus();
            request.setAttribute("order", order);
            request.setAttribute("list", list);
            Dispatcher.forward(request, response, "EditOrder.jsp");
        }
    }

    private List<String> getListStatus() {
        List<String> list = new ArrayList<>();
        list.add(Order.STATUS_NEW);
        list.add(Order.STATUS_SHIPPING);
        list.add(Order.STATUS_COMPLETED);
        list.add(Order.STATUS_REJECTED);
        return list;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private boolean isValidShipDate(String OrderDate, String ShipDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateShip = sdf.parse(ShipDate);
            Date dateOrder = sdf.parse(OrderDate);
            if (dateShip.before(dateOrder)) {
                return false;
            }
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String OrderID = request.getParameter("OrderID");
        String OrderDate = request.getParameter("OrderDate");
        String ShipDate = request.getParameter("ShipDate");
        String address = request.getParameter("address");
        String UserID = request.getParameter("UserID");
        String status = request.getParameter("status");
        int orderID = Integer.parseInt(OrderID);
        int userID = Integer.parseInt(UserID);
        Order order = new Order(orderID, userID, status, OrderDate, ShipDate == null || ShipDate.isEmpty() ? null : ShipDate, address);
        List<String> listStatus = getListStatus();
        request.setAttribute("order", order);
        request.setAttribute("list", listStatus);
        if (order.getShipDate() != null && !isValidShipDate(OrderDate, order.getShipDate())) {
            request.setAttribute("message", "Ship Date not before Order Date");
        } else if (status.equals(Order.STATUS_COMPLETED) && order.getShipDate() == null) {
            request.setAttribute("message", "You have to update ship date when status is " + Order.STATUS_COMPLETED.toUpperCase());
        } else {
            if (order.getStatus().equals(Order.STATUS_REJECTED)) {
                List<OrderDetail> listDetail = daoDetail.getListOrderDetail(orderID);
                double sum = 0;
                for (OrderDetail detail : listDetail) {
                    Product product = daoProduct.getProduct(detail.getProductID() + "");
                    double total = detail.getQuantity() * product.getPrice();
                    sum = sum + total;
                }
                User user = daoUser.getUser(userID);
                double money = (double) Math.round((sum * 100)) / 100 + user.getMoney();
                daoUser.UpdateMoney(user.getUsername(), money);
                User seller = (User) session.getAttribute("user");
                seller.setMoney(seller.getMoney() - sum);
                daoUser.UpdateMoney(seller.getUsername(), seller.getMoney());
                session.setAttribute("user", seller);
            }
            int number = daoOrder.UpdateOrder(order);
            // if update successful
            if (number > 0) {
                request.setAttribute("mess", "Edit successful");
            }
        }
        Dispatcher.forward(request, response, "EditOrder.jsp");
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
