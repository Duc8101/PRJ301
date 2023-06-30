package Controller;

import Const.ConstValue;
import Entity.Staff;
import Model.DAOStaff;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileStaff extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Dispatcher.forward(request, response, "ProfileStaff.jsp");
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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        DAOStaff dao = new DAOStaff();
        Staff staff = dao.getStaff(username);
        if (FirstName.isEmpty() || LastName.isEmpty()) {
            request.setAttribute("message", "You have to input First Name and Last Name");
        } else if (!phone.isEmpty() && !phone.matches(ConstValue.FORMAT_PHONE)) {
            request.setAttribute("message", "Wrong format phone");
        } else {
            staff.setFirstName(FirstName);
            staff.setLastName(LastName);
            staff.setPhone(phone.isEmpty() ? null : phone);
            staff.setEmail(email);
            int number = dao.UpdateStaff(staff);
            // if update successful
            if (number > 0) {
                request.setAttribute("mess", "Update successful");
            }
        }
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
