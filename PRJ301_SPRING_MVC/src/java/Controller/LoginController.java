package Controller;

import Const.ConstValue;
import Entity.User;
import Model.DAOUser;
import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Login")
public class LoginController {

    private final DAOUser daoUser = new DAOUser();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String Index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = getUsername(request);
        if (username == null) {
            return "Login/Index";
        }
        User user = daoUser.getUser(username);
        if (user == null) {
            return "Login/Index";
        }
        session.setAttribute("user", user);
        if (user.getRoleName().equals(ConstValue.ROLE_CUSTOMER)) {
            return ConstValue.REDIRECT + "/Home";
        }
        return ConstValue.REDIRECT + "/ManagerProduct";
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView Index(HttpServletRequest request, HttpServletResponse response, String username, String password, String remember) {
        User user = daoUser.getUser(username);
        Map<String, String> map = new HashMap<>();
        if (user == null || !password.equals(user.getPassword())) {
            map.put("message", "Username or password incorrect");
            return new ModelAndView("Login/Index", map);
        }
        if (remember != null) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(24 * 3600);
            response.addCookie(cookie);
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (user.getRoleName().equals(ConstValue.ROLE_CUSTOMER)) {
            return new ModelAndView(ConstValue.REDIRECT + "/Home");
        }
        return new ModelAndView(ConstValue.REDIRECT + "/ManagerProduct");
    }
}
