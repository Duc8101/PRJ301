package Controller;

import Const.ConstValue;
import Entity.*;
import Model.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends HttpServlet {

    private final DAOCustomer daoCustomer = new DAOCustomer();
    private final DAOStaff daoStaff = new DAOStaff();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            Dispatcher.forward(request, response, "Register.jsp");
        } else {
            Staff staff = daoStaff.getStaff(username);
            if (staff == null) {
                response.sendRedirect("Home");
            } else {
                response.sendRedirect("ManagerProduct");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String FirstName = request.getParameter("FirstName").trim();
        String LastName = request.getParameter("LastName").trim();
        String phone = request.getParameter("phone");
        String email = request.getParameter("email").trim();
        String street = request.getParameter("street").trim();
        String city = request.getParameter("city").trim();
        String state = request.getParameter("state");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer customer = daoCustomer.getCustomer(username);
        Staff staff = daoStaff.getStaff(username);
        if (FirstName.isEmpty() || LastName.isEmpty()) {
            request.setAttribute("message", "You have to input First Name and Last Name");
        } else if (!phone.isEmpty() && !phone.matches(ConstValue.FORMAT_PHONE)) {
            request.setAttribute("message", "Wrong format phone");
        } else if (street.isEmpty()) {
            request.setAttribute("message", "You have to input street");
        } else if (city.isEmpty()) {
            request.setAttribute("message", "You have to input city");
        } else if (!username.matches(ConstValue.FORMAT_USERNAME) || username.length() > ConstValue.MAX_LENGTH_USERNAME) {
            request.setAttribute("message", "Username max " + ConstValue.MAX_LENGTH_USERNAME + " characters , starts with letters and contain only letter and digit");
        } else if (password.length() > ConstValue.MAX_LENGTH_PASSWORD) {
            request.setAttribute("message", "Password max " + ConstValue.MAX_LENGTH_PASSWORD + " characters");
        } else if (customer != null || staff != null) {
            request.setAttribute("message", "User existed");
        } else {
            customer = new Customer(FirstName, LastName, phone.isEmpty() ? null : phone, email, street, city, state, null, username, password);
            int number = daoCustomer.AddCustomer(customer);
            // if add successful
            if (number > 0) {
                request.setAttribute("mess", "Register successful");
            }
        }
        Dispatcher.forward(request, response, "Register.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
