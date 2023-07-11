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

public class MyOrderServlet extends HttpServlet {

    private final DAOOrder daoOrder = new DAOOrder();
    private final DAOOrderDetail daoDetail = new DAOOrderDetail();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String service = request.getParameter("service") == null ? "DisplayOrder" : request.getParameter("service");
        User user = (User) session.getAttribute("user");
        if (service.equals("DisplayOrder")) {
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            List<Order> list = this.daoOrder.getListOrder(page, user.getID());
            int No = ConstValue.MAX_ORDER_IN_PAGE * (page - 1) + 1;
            int number = this.daoOrder.getNumberPage(user.getID());
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL = "MyOrder?page=" + prePage;
            String nextURL = "MyOrder?page=" + nextPage;
            String firstURL = "MyOrder";
            String lastURL = "MyOrder?page=" + number;
            request.setAttribute("list", list);
            request.setAttribute("No", No);
            request.setAttribute("number", number);
            request.setAttribute("pageSelected", page);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            request.setAttribute("first", firstURL);
            request.setAttribute("last", lastURL);
            Dispatcher.forward(request, response, "/View/MyOrder/Index.jsp");
        }

        if (service.equals("Detail")) {
            String OrderID = request.getParameter("OrderID");
            int orderID = Integer.parseInt(OrderID);
            Order order = this.daoOrder.getOrder(orderID);
            // if not find order
            if (order == null) {
                response.sendRedirect("MyOrder");
            } else {
                List<OrderDetail> list = this.daoDetail.getListOrderDetail(orderID);
                request.setAttribute("list", list);
                Dispatcher.forward(request, response, "/View/MyOrder/Detail.jsp");
            }
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
