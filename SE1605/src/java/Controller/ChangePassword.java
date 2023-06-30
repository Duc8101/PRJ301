package Controller;

import Const.ConstValue;
import Entity.Customer;
import Entity.Staff;
import Model.DAOCustomer;
import Model.DAOStaff;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Dispatcher.forward(request, response, "ChangePassword.jsp");
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
        String username = (String) session.getAttribute("username");
        String OldPassword = request.getParameter("old");
        String NewPassword = request.getParameter("new");
        String ComfirmPassword = request.getParameter("confirm");
        DAOStaff daoStaff = new DAOStaff();
        DAOCustomer daoCustomer = new DAOCustomer();
        Staff staff = daoStaff.getStaff(username);
        // if customer
        if (staff == null) {
            Customer customer = daoCustomer.getCustomer(username);
            if (!customer.getPassword().equals(OldPassword)) {
                request.setAttribute("message", "Your old password not correct");
            } else if (!NewPassword.equals(ComfirmPassword)) {
                request.setAttribute("message", "Your confirm password not the same new password");
            } else if (NewPassword.length() > ConstValue.MAX_LENGTH_PASSWORD) {
                request.setAttribute("message", "Password max 25 characters");
            } else {
                int number = daoCustomer.UpdatePassword(username, NewPassword);
                // if update successful
                if (number > 0) {
                    request.setAttribute("mess", "Change successful");
                }
            }
        }
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
