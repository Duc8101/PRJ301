package Controller;

import Const.ConstValue;
import Entity.User;
import Model.DAOUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends BaseServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            this.forward(request, response, "/View/Register/Index.jsp");
        } else if (user.getRoleName().equals(ConstValue.ROLE_CUSTOMER)) {
            response.sendRedirect("Home");
        } else {
            response.sendRedirect("ManagerProduct");
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
        DAOUser daoUser = new DAOUser();
        String FullName = request.getParameter("FullName").trim();
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone").trim();
        String email = request.getParameter("email").trim();
        String address = request.getParameter("address").trim();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = daoUser.getUser(username);
        if (FullName.isEmpty()) {
            request.setAttribute("message", "You have to input your name");
        } else if (!phone.matches(ConstValue.FORMAT_PHONE)) {
            request.setAttribute("message", "Phone only number and length is " + ConstValue.LENGTH_PHONE);
        } else if (!email.isEmpty() && !email.matches(ConstValue.FORMAT_EMAIL)) {
            request.setAttribute("message", "Invalid email");
        } else if (!username.matches(ConstValue.FORMAT_USERNAME) || username.length() > ConstValue.MAX_LENGTH_USERNAME) {
            request.setAttribute("message", "Username max " + ConstValue.MAX_LENGTH_USERNAME + " characters , starts with letters and contain only letter and digit");
        } else if (password.length() > ConstValue.MAX_LENGTH_PASSWORD) {
            request.setAttribute("message", "Password max " + ConstValue.MAX_LENGTH_PASSWORD + " characters");
        } else if (address.isEmpty()) {
            request.setAttribute("message", "You have to input address");
        } else if (user != null) {
            request.setAttribute("message", "Username existed");
        } else {
            user = new User(FullName, phone, email.isEmpty() ? null : email, gender, address, username, password, ConstValue.ROLE_CUSTOMER);
            int number = daoUser.AddUser(user);
            // if register successful
            if (number > 0) {
                request.setAttribute("mess", "Register successful");
            }
        }
        this.forward(request, response, "/View/Register/Index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
