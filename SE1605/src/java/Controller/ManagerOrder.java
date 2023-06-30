package Controller;

import Const.ConstValue;
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
    private final DAOStaff daoStaff = new DAOStaff();
    private final DAOCustomer daoCustomer = new DAOCustomer();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String service, int status)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Staff staff = daoStaff.getStaff(username);
        if (service.equals("DisplayOrder")) {
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            int number = daoOrder.getNumberPage(status, staff.getStaffID());
            List<Order> list = daoOrder.getListOrder(status, staff.getStaffID(), page);
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL;
            String nextURL;
            String firstURL;
            String lastURL;
            if (status == 0) {
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

        if (service.equals("ViewCustomer")) {
            String user = request.getParameter("username");
            Customer customer = daoCustomer.getCustomer(user);
            request.setAttribute("customer", customer);
            Dispatcher.forward(request, response, "ViewCustomer.jsp");
        }

        if (service.equals("EditOrder")) {
            String OrderID = request.getParameter("OrderID");
            int orderID = Integer.parseInt(OrderID);
            Order order = daoOrder.getOrder(orderID);
            Map<Integer, String> map = getListStatus();
            request.setAttribute("order", order);
            request.setAttribute("map", map);
            Dispatcher.forward(request, response, "EditOrder.jsp");
        }
    }

    private Map<Integer, String> getListStatus() {
        Map<Integer, String> map = new HashMap<>();
        map.put(ConstValue.INT_STATUS_WAIT, ConstValue.STRING_STATUS_WAIT);
        map.put(ConstValue.INT_STATUS_PROCESS, ConstValue.STRING_STATUS_PROCESS);
        map.put(ConstValue.INT_STATUS_DONE, ConstValue.STRING_STATUS_DONE);
        map.put(ConstValue.INT_STATUS_CANCEL, ConstValue.STRING_STATUS_CANCEL);
        return map;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service") == null ? "DisplayOrder" : request.getParameter("service");
        String Status = request.getParameter("status") == null ? "0" : request.getParameter("status");
        int status = Integer.parseInt(Status);
        processRequest(request, response, service, status);
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
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Staff staff = daoStaff.getStaff(username);
        String OrderID = request.getParameter("OrderID");
        String OrderDate = request.getParameter("OrderDate");
        String RequiredDate = request.getParameter("RequiredDate");
        String ShipDate = request.getParameter("ShipDate");
        String CustomerID = request.getParameter("CustomerID");
        String Status = request.getParameter("status");
        int status = Integer.parseInt(Status);
        int orderID = Integer.parseInt(OrderID);
        int customerID = Integer.parseInt(CustomerID);
        int StoreID = (int) session.getAttribute("StoreID");
        Order order = new Order(orderID, customerID, status, OrderDate, RequiredDate, ShipDate == null || ShipDate.isEmpty() ? null : ShipDate, StoreID, staff.getStaffID());
        Map<Integer, String> map = getListStatus();
        request.setAttribute("order", order);
        request.setAttribute("map", map);
        if (order.getShippedDate() != null && !isValidShipDate(OrderDate, order.getShippedDate())) {
            request.setAttribute("message", "Ship Date not before Order Date");
        } else if (status == ConstValue.INT_STATUS_DONE && order.getShippedDate() == null) {
            request.setAttribute("message", "You have to update ship date when status is DONE");
        } else {
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
