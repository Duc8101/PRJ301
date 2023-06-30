package Controller;

import Entity.User;
import Model.DAOUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddMoney extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Dispatcher.forward(request, response, "AddMoney.jsp");///
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOUser dao = new DAOUser();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String Money = request.getParameter("money");
        String password = request.getParameter("password");
        double money = Double.parseDouble(Money);
        if (money <= 0) {
            request.setAttribute("message", "Money greater than 0");
        } else if (password.isEmpty()) {
            request.setAttribute("message", "You have to input password");
        } else if (!password.equals(user.getPassword())) {
            request.setAttribute("message", "Wrong password");
        } else {
            money = money + user.getMoney();
            int number = dao.UpdateMoney(user.getUsername(), money);
            // if update successful
            if (number > 0) {
                user.setMoney(money);
                session.setAttribute("user", user);
                request.setAttribute("mess", "Add money successful");
            }
        }
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
