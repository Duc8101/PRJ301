package Controller;

import Const.ConstValue;
import Entity.User;
import Model.DAOUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileServlet extends BaseServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        this.forward(request, response, "/View/Profile/Index.jsp");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        DAOUser dao = new DAOUser();
        String FullName = request.getParameter("FullName").trim();
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email").trim();
        String address = request.getParameter("address");
        if (!phone.matches(ConstValue.FORMAT_PHONE)) {
            request.setAttribute("message", "Phone only number and length is " + ConstValue.LENGTH_PHONE);
        } else if (address != null && address.trim().isEmpty()) {
            request.setAttribute("message", "You have to input your address");
        } else {
            user = new User(FullName, phone, email.isEmpty() ? null : email, gender, address == null ? null : address.trim(), user.getUsername(), user.getPassword(), user.getRoleName());
            int number = dao.UpdateProfile(user);
            // if update successful
            if (number > 0) {
                session.setAttribute("user", user);
                request.setAttribute("mess", "Update successful");
            }
        }
        this.forward(request, response, "/View/Profile/Index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
