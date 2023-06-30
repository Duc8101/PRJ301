package Controller;

import Const.ConstValue;
import Entity.*;
import Model.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerCustomer extends HttpServlet {

    private final DAOCustomer daoCustomer = new DAOCustomer();
    private final DAOStaff daoStaff = new DAOStaff();
    private final DAOStore daoStore = new DAOStore();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String service)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Staff staff = daoStaff.getStaff(username);
        Store store = this.daoStore.getStore(staff.getStoreID());
        if (service.equals("DisplayCustomer")) {
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            List<Customer> list = daoCustomer.getListCustomer(store.getState(), page);
            int number = daoCustomer.getNumberPage(store.getState());
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL = "ManagerCustomer" + "?page=" + prePage;
            String nextURL = "ManagerCustomer" + "?page=" + nextPage;
            String firstURL = "ManagerCustomer";
            String lastURL = "ManagerCustomer" + "?page=" + number;
            request.setAttribute("number", number);
            request.setAttribute("pageSelected", page);
            request.setAttribute("list", list);
            request.setAttribute("previous", preURL);
            request.setAttribute("next", nextURL);
            request.setAttribute("first", firstURL);
            request.setAttribute("last", lastURL);
            Dispatcher.forward(request, response, "DisplayCustomer.jsp");
        }

        if (service.equals("EditCustomer")) {
            String user = request.getParameter("username");
            Customer customer = daoCustomer.getCustomer(user);
            request.setAttribute("customer", customer);
            Dispatcher.forward(request, response, "EditCustomer.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service") == null ? "DisplayCustomer" : request.getParameter("service");
        processRequest(request, response, service);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String zip = request.getParameter("zip");
        Customer customer = daoCustomer.getCustomer(username);
        request.setAttribute("customer", customer);
        if (zip.length() != ConstValue.LENGTH_ZIP_CODE) {
            request.setAttribute("message", "Zip code must be " + ConstValue.LENGTH_ZIP_CODE + " digits");
        } else if (daoCustomer.isExist("customers", zip) || daoCustomer.isExist("stores", zip)) {
            request.setAttribute("message", "Zip code existed");
        } else {
            customer.setZipCode(zip);
            int number = daoCustomer.UpdateZipCode(zip, username);
            // if update successful
            if (number > 0) {
                request.setAttribute("customer", customer);
                request.setAttribute("mess", "Edit successful");
            }
        }
        Dispatcher.forward(request, response, "EditCustomer.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
