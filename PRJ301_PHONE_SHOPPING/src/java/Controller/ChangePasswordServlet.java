package Controller;

import Const.ConstValue;
import Entity.User;
import Model.DAOUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Dispatcher.forward(request, response, "/View/ChangePassword/Index.jsp");
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
        String OldPassword = request.getParameter("old");
        String NewPassword = request.getParameter("new");
        String ComfirmPassword = request.getParameter("confirm");
        if (!user.getPassword().equals(OldPassword)) {
            request.setAttribute("message", "Your old password not correct");
        } else if (!NewPassword.equals(ComfirmPassword)) {
            request.setAttribute("message", "Your confirm password not the same new password");
        } else if (NewPassword.length() > ConstValue.MAX_LENGTH_PASSWORD) {
            request.setAttribute("message", "Password max " + ConstValue.MAX_LENGTH_PASSWORD + " characters");
        } else {
            int number = dao.UpdatePassword(user.getUsername(), NewPassword);
            //if update successful
            if (number > 0) {
                user.setPassword(NewPassword);
                session.setAttribute("user", user);
                request.setAttribute("mess", "Change successful");
            }
        }
        Dispatcher.forward(request, response, "/View/ChangePassword/Index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
