package Controller;

import Const.ConstValue;
import Entity.Customer;
import Model.DAOCustomer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileCustomer extends HttpServlet {

    private final DAOCustomer dao = new DAOCustomer();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Customer customer = dao.getCustomer(username);
        request.setAttribute("customer", customer);
        Dispatcher.forward(request, response, "ProfileCustomer.jsp");
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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Customer customer = dao.getCustomer(username);
        request.setAttribute("customer", customer);
        if (FirstName.isEmpty() || LastName.isEmpty()) {
            request.setAttribute("message", "You have to input First Name and Last Name");
        } else if (!phone.isEmpty() && !phone.matches(ConstValue.FORMAT_PHONE)) {
            request.setAttribute("message", "Wrong format phone");
        } else if (street.isEmpty()) {
            request.setAttribute("message", "You have to input street");
        } else if (city.isEmpty()) {
            request.setAttribute("message", "You have to input city");
        } else {
            customer = new Customer(FirstName, LastName, phone.isEmpty() ? null : phone, email, street, city, customer.getState(), null, username, customer.getPassword());
            int number = dao.UpdateProfile(customer);
            // if update successful
            if (number > 0) {
                request.setAttribute("customer", customer);
                request.setAttribute("mess", "Update successful");
            }
        }
        Dispatcher.forward(request, response, "ProfileCustomer.jsp");
        // processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
