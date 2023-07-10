package Controller;

import Const.ConstValue;
import Entity.User;
import Model.DAOUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    private final DAOUser daoUser = new DAOUser();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = getUsername(request);
        if (username == null) {
            Dispatcher.forward(request, response, "Login/Index.jsp");
        } else {
            User user = daoUser.getUser(username);
            // if user not exist
            if (user == null) {
                Dispatcher.forward(request, response, "Login/Index.jsp");
            } else {
                session.setAttribute("user", user);
                if (user.getRoleName().equals(ConstValue.ROLE_CUSTOMER)) {
                    response.sendRedirect("Home");
                } else {
                    response.sendRedirect("ManagerProduct");
                }
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        User user = daoUser.getUser(username);
        if (user == null || !password.equals(user.getPassword())) {
            request.setAttribute("message", "Username or password incorrect");
            Dispatcher.forward(request, response, "Login.jsp");
        } else {
            if (remember != null) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(24 * 3600);
                response.addCookie(cookie);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (user.getRoleName().equals(ConstValue.ROLE_CUSTOMER)) {
                response.sendRedirect("Home");
            } else {
                response.sendRedirect("ManagerProduct");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
