package Controller;

import Const.ConstValue;
import Entity.Customer;
import Entity.Staff;
import Model.DAOCustomer;
import Model.DAOStaff;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerStaff extends HttpServlet {

    private final DAOStaff daoStaff = new DAOStaff();
    private final DAOCustomer daoCustomer = new DAOCustomer();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String service)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (service.equals("DisplayStaff")) {
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            Staff staff = daoStaff.getStaff(username);
            List<Staff> list = daoStaff.getListStaff(staff.getStaffID() + "", page);
            int number = daoStaff.getNumberPage(staff.getStaffID() + "");
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL = "ManagerStaff" + "?page=" + prePage;
            String nextURL = "ManagerStaff" + "?page=" + nextPage;
            String firstURL = "ManagerStaff";
            String lastURL = "ManagerStaff" + "?page=" + number;
            request.setAttribute("number", number);
            request.setAttribute("pageSelected", page);
            request.setAttribute("list", list);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            request.setAttribute("first", firstURL);
            request.setAttribute("last", lastURL);
            Dispatcher.forward(request, response, "DisplayStaff.jsp");
        }

        if (service.equals("AddStaff")) {
            Dispatcher.forward(request, response, "AddStaff.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service") == null ? "DisplayStaff" : request.getParameter("service");
        processRequest(request, response, service);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String service = request.getParameter("service");
        if (service.equals("EditStaff")) {
            String StaffID = request.getParameter("StaffID");
            String Active = request.getParameter("active");
            boolean active = Active != null;
            int number = daoStaff.UpdateStaff(active, StaffID);
            // if update successful
            if (number > 0) {
                response.sendRedirect("ManagerStaff");
            }
        } else {
            int StoreID = (int) session.getAttribute("StoreID");
            String FirstName = request.getParameter("FirstName").trim();
            String LastName = request.getParameter("LastName").trim();
            String phone = request.getParameter("phone");
            String email = request.getParameter("email").trim();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Customer customer = daoCustomer.getCustomer(username);
            Staff staff = daoStaff.getStaff(username);
            if (FirstName.isEmpty() || LastName.isEmpty()) {
                request.setAttribute("message", "You have to input First Name and Last Name");
                Dispatcher.forward(request, response, "AddStaff.jsp");
            } else if (!phone.isEmpty() && !phone.matches(ConstValue.FORMAT_PHONE)) {
                request.setAttribute("message", "Wrong format phone");
                Dispatcher.forward(request, response, "AddStaff.jsp");
            } else if (!username.matches(ConstValue.FORMAT_USERNAME) || username.length() > ConstValue.MAX_LENGTH_USERNAME) {
                request.setAttribute("message", "Username max " + ConstValue.MAX_LENGTH_USERNAME + " characters , starts with letters and contain only letter and digit");
            } else if (password.length() > ConstValue.MAX_LENGTH_PASSWORD) {
                request.setAttribute("message", "Password max " + ConstValue.MAX_LENGTH_PASSWORD + " characters");
            } else if (customer != null || staff != null) {
                request.setAttribute("message", "User existed");
                Dispatcher.forward(request, response, "AddStaff.jsp");
            } else {
                List<Staff> list = this.daoStaff.getListStaff(null, 0);
                Random random = new Random();
                String ManagerID = list.get(random.nextInt(list.size())).getStaffID() + "";
                staff = new Staff(FirstName, LastName, email, phone.isEmpty() ? null : phone, false, StoreID, ManagerID, username, password);
                int number = daoStaff.AddStaff(staff);
                // if add successful
                if (number > 0) {
                    request.setAttribute("mess", "Add successful");
                    processRequest(request, response, "DisplayStaff");
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
