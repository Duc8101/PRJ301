package Controller;

import Entity.Order;
import Entity.User;
import Model.DAOOrder;
import java.io.IOException;
import java.util.List;
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
        User user = (User) session.getAttribute("user");
        DAOOrder dao = new DAOOrder();
        String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
        int page = Integer.parseInt(pageSelected);
        List<Order> list = dao.getListOrder(page, user.getID());
        int No = DAOOrder.MAX_ORDER_IN_PAGE * (page - 1) + 1;
        int number = dao.getNumberOfPage(user.getID());
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
