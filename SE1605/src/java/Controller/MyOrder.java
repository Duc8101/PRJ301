package Controller;

import Const.ConstValue;
import Entity.*;
import Model.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        DAOCustomer daoCustomer = new DAOCustomer();
        DAOOrder daoOrder = new DAOOrder();
        String username = (String) session.getAttribute("username");
        String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
        int page = Integer.parseInt(pageSelected);
        Customer customer = daoCustomer.getCustomer(username);
        List<Order> list = daoOrder.getListOrder(customer.getID(), page);
        int number = daoOrder.getNumberPage(customer.getID());
        Map<Integer, String> map = new HashMap<>();
        map.put(ConstValue.INT_STATUS_WAIT, ConstValue.STRING_STATUS_WAIT);
        map.put(ConstValue.INT_STATUS_PROCESS, ConstValue.STRING_STATUS_PROCESS);
        map.put(ConstValue.INT_STATUS_DONE, ConstValue.STRING_STATUS_DONE);
        map.put(ConstValue.INT_STATUS_CANCEL, ConstValue.STRING_STATUS_CANCEL);
        int prePage = page - 1;
        int nextPage = page + 1;
        int No = DAOOrder.MAX_ORDER_IN_PAGE * (page - 1) + 1;
        String preURL = "MyOrder?page=" + prePage;
        String nextURL = "MyOrder?page=" + nextPage;
        String firstURL = "MyOrder";
        String lastURL = "MyOrder?page=" + number;
        request.setAttribute("list", list);
        request.setAttribute("map", map);
        request.setAttribute("number", number);
        request.setAttribute("pageSelected", page);
        request.setAttribute("previous", preURL);
        request.setAttribute("next", nextURL);
        request.setAttribute("first", firstURL);
        request.setAttribute("last", lastURL);
        request.setAttribute("No", No);
        Dispatcher.forward(request, response, "MyOrder.jsp");
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
