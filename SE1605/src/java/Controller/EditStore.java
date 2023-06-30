package Controller;

import Const.ConstValue;
import Entity.Store;
import Model.DAOStore;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditStore extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Dispatcher.forward(request, response, "EditStore.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOStore dao = new DAOStore();
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        String name = request.getParameter("StoreName").trim();
        String phone = request.getParameter("phone").trim();
        String email = request.getParameter("email").trim();
        String street = request.getParameter("street").trim();
        String city = request.getParameter("city").trim();
        String zip = request.getParameter("zip");
        if (name.isEmpty()) {
            request.setAttribute("message", "You have to input store name");
        } else if (phone.isEmpty()) {
            request.setAttribute("message", "You have to input phone");
        } else if (!phone.matches(ConstValue.FORMAT_PHONE)) {
            request.setAttribute("message", "Wrong format phone");
        } else if (email.isEmpty()) {
            request.setAttribute("message", "You have to input email");
        } else if (street.isEmpty()) {
            request.setAttribute("message", "You have to input street");
        } else if (city.isEmpty()) {
            request.setAttribute("message", "You have to input city");
        } else {
            store = new Store(store.getID(), name, phone, email, street, city, store.getState(), zip);
            int number = dao.UpdateStore(store);
            // if update successful
            if (number > 0) {
                session.setAttribute("store", store);
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
