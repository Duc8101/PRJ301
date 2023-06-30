package Controller;

import Const.ConstValue;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/Logout")
public class LogoutController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        // get all cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cook : cookies) {
            // if cookie name equal to username
            if (cook.getName().equals("username")) {
                cook.setMaxAge(0);
                response.addCookie(cook);
                break;
            }
        }
        return ConstValue.REDIRECT +"/Home";
    }
}
