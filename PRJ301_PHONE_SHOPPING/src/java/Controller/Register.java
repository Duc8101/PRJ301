package Controller;

import Entity.User;
import Model.DAOUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            Dispatcher.forward(request, response, "Register.jsp");
        } else if (user.getRoleName().equals(User.ROLE_CUSTOMER)) {
            response.sendRedirect("Home");
        } else {
            response.sendRedirect("ManagerProduct");
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
        DAOUser daoUser = new DAOUser();
        String FullName = request.getParameter("FullName").trim();
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email").trim();
        String address = request.getParameter("address").trim();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = daoUser.getUser(username);
        if (!phone.matches(User.FORMAT_PHONE)) {
            request.setAttribute("message", "Phone only number and length is " + User.LENGTH_PHONE);
        } else if (!username.matches(User.FORMAT_USERNAME) || username.length() > User.MAX_LENGTH_USERNAME) {
            request.setAttribute("message", "Username max " + User.MAX_LENGTH_USERNAME + " characters , starts with letters and contain only letter and digit");
        } else if (password.length() > User.MAX_LENGTH_PASSWORD) {
            request.setAttribute("message", "Password max " + User.MAX_LENGTH_PASSWORD + " characters");
        } else if (address.isEmpty()) {
            request.setAttribute("message", "You have to input address");
        } else if (user != null) {
            request.setAttribute("message", "Username existed");
        } else {
            user = new User(FullName, phone, email.isEmpty() ? null : email, gender, address, 0, username, password, User.ROLE_CUSTOMER);
            int number = daoUser.AddUser(user);
            // if register successful
            if (number > 0) {
                request.setAttribute("mess", "Register successful");
            }
        }
        Dispatcher.forward(request, response, "Register.jsp");
        // processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
