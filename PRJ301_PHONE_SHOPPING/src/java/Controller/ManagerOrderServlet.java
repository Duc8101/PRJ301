package Controller;

import Const.ConstValue;
import Entity.*;
import Model.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerOrderServlet extends HttpServlet {

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
            int number = daoOrder.getNumberPage(status);
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
            Dispatcher.forward(request, response, "/View/ManagerOrder/Index.jsp");
        }

        if (service.equals("ViewCustomer")) {
            String username = request.getParameter("username");
            User user = daoUser.getUser(username);
            if (user == null) {
                response.sendRedirect("ManagerOrder");
            } else {
                request.setAttribute("user", user);
                Dispatcher.forward(request, response, "/View/ManagerOrder/ViewCustomer.jsp");
            }
        }

        if (service.equals("Detail")) {
            String OrderID = request.getParameter("OrderID");
            int orderID = Integer.parseInt(OrderID);
            Order order = this.daoOrder.getOrder(orderID);
            // if not find order
            if (order == null) {
                response.sendRedirect("ManagerOrder");
            } else {
                List<OrderDetail> list = this.daoDetail.getListOrderDetail(orderID);
                request.setAttribute("list", list);
                Dispatcher.forward(request, response, "/View/ManagerOrder/Detail.jsp");
            }
        }

        if (service.equals("EditOrder")) {
            String OrderID = request.getParameter("OrderID");
            int orderID = Integer.parseInt(OrderID);
            Order order = daoOrder.getOrder(orderID);
            if (order == null) {
                response.sendRedirect("ManagerOrder");
            } else {
                request.setAttribute("order", order);
                Dispatcher.forward(request, response, "/View/ManagerOrder/Edit.jsp");
            }
        }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String OrderID = request.getParameter("OrderID");
        String OrderDate = request.getParameter("OrderDate");
        String ShipDate = request.getParameter("ShipDate");
        String address = request.getParameter("address");
        String UserID = request.getParameter("UserID");
        String status = request.getParameter("status");
        int orderID = Integer.parseInt(OrderID);
        int userID = Integer.parseInt(UserID);
        Order order = new Order(orderID, userID, status, OrderDate, ShipDate == null || ShipDate.isEmpty() ? null : ShipDate, address);
        request.setAttribute("order", order);
        if (order.getShipDate() != null && !isValidShipDate(OrderDate, order.getShipDate())) {
            request.setAttribute("message", "Ship Date not before Order Date");
        } else if (status.equals(ConstValue.STATUS_COMPLETED) && order.getShipDate() == null) {
            request.setAttribute("message", "You have to update ship date when status is " + ConstValue.STATUS_COMPLETED.toUpperCase());
        } else {
            int number = daoOrder.UpdateOrder(order);
            // if update successful
            if (number > 0) {
                request.setAttribute("mess", "Edit successful");
            }
        }
        Dispatcher.forward(request, response, "/View/ManagerOrder/Edit.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
