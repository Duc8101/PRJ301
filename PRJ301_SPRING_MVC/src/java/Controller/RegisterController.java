package Controller;

import Const.ConstValue;
import Entity.User;
import Model.DAOUser;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Register")
public class RegisterController {

    private final DAOUser dao = new DAOUser();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String Index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "Register/Index";
        }
        if (user.getRoleName().equals(ConstValue.ROLE_CUSTOMER)) {
            return ConstValue.REDIRECT + "/Home";
        }
        return ConstValue.REDIRECT + "/ManagerProduct";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView Index(User register) {
        Map<String, String> map = new HashMap<>();
        User check = dao.getUser(register.getUsername());
        if (register.getFullName().trim().length() == 0) {
            map.put("message", "You have to input your name");
        } else if (!register.getPhone().matches(ConstValue.FORMAT_PHONE)) {
            map.put("message", "Phone only number and length is " + ConstValue.LENGTH_PHONE);
        } else if (!register.getUsername().matches(ConstValue.FORMAT_USERNAME) || register.getUsername().length() > ConstValue.MAX_LENGTH_USERNAME) {
            map.put("message", "Username max " + ConstValue.MAX_LENGTH_USERNAME + " characters , starts with letters and contain only letter and digit");
        } else if (register.getPassword().length() > ConstValue.MAX_LENGTH_PASSWORD) {
            map.put("message", "Password max " + ConstValue.MAX_LENGTH_PASSWORD + " characters");
        } else if (check != null) {
            map.put("message", "Username existed");
        } else {
            register.setFullName(register.getFullName().trim());
            register.setEmail(register.getEmail().isEmpty() ? null : register.getEmail().trim());
            register.setAddress(register.getAddress().isEmpty() ? null : register.getAddress().trim());
            register.setMoney(0);
            register.setRoleName(ConstValue.ROLE_CUSTOMER);
            int number = dao.AddUser(register);
            // if register successful
            if (number > 0) {
                map.put("mess", "Register successful");
            }
        }
        return new ModelAndView("Register/Index", map);
    }
}
