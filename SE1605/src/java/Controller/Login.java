package Controller;

import Entity.*;
import Model.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    private final DAOCustomer daoCustomer = new DAOCustomer();
    private final DAOStaff daoStaff = new DAOStaff();
    private final DAOStore daoStore = new DAOStore();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = getUsername(request);
        // if logout or not login
        if (username == null) {
            Dispatcher.forward(request, response, "Login.jsp");
        } else {
            Store store;
            Staff staff = daoStaff.getStaff(username);
            Customer customer = daoCustomer.getCustomer(username);
            if (staff == null && customer == null) {
                Dispatcher.forward(request, response, "Login.jsp");
            } else if (staff == null) {
                session.setAttribute("username", username);
                customer = daoCustomer.getCustomer(username);
                store = daoStore.getStore(customer.getState());
                session.setAttribute("StoreID", store.getID());
                session.setAttribute("store", store);
                response.sendRedirect("Home");
            } else {
                session.setAttribute("username", username);
                session.setAttribute("StoreID", staff.getStoreID());
                store = daoStore.getStore(staff.getStoreID());
                session.setAttribute("store", store);
                response.sendRedirect("ManagerProduct");
            }
        }
    }

    private String getUsername(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        // if not exist username in cookie
        if (cookie == null) {
            return null;
        }
        for (Cookie cook : cookie) {
            // if exist username
            if (cook.getName().equals("username")) {
                return cook.getValue();
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        Customer customer = daoCustomer.getCustomer(username);
        Staff staff = daoStaff.getStaff(username);
        if ((customer == null || !password.equals(customer.getPassword())) && (staff == null || !password.equals(staff.getPassword()))) {
            request.setAttribute("message", "Username or password incorrect");
            Dispatcher.forward(request, response, "Login.jsp");
        } else {
            if (remember != null) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(24 * 3600);
                response.addCookie(cookie);
            }
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            Store store;
            if (staff == null && customer != null) {
                store = daoStore.getStore(customer.getState());
                session.setAttribute("StoreID", store.getID());
                session.setAttribute("store", store);
                response.sendRedirect("Home");
            } else if (staff != null) {
                session.setAttribute("StoreID", staff.getStoreID());
                store = daoStore.getStore(staff.getStoreID());
                session.setAttribute("store", store);
                response.sendRedirect("ManagerProduct");
            }
        }
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
