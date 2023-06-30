package Controller;

import Const.ConstValue;
import Entity.*;
import Model.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Home")
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        HttpSession session = request.getSession();
        DAOProduct daoProduct = new DAOProduct();
        DAOCategory daoCategory = new DAOCategory();
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        if (user == null || user.getRoleName().equalsIgnoreCase(ConstValue.ROLE_CUSTOMER)) {
            List<Category> listCategory = daoCategory.getAllCategory();
            String CategoryID = request.getParameter("CategoryID") == null ? "0" : request.getParameter("CategoryID");
            String properties = request.getParameter("properties");
            String flow = request.getParameter("flow");
            int catID = Integer.parseInt(CategoryID);
            int number = daoProduct.getNumberOfPage(catID);
            String pageSelected = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int page = Integer.parseInt(pageSelected);
            List<Product> listProduct = daoProduct.getListProduct(ConstValue.ROLE_CUSTOMER, catID, page, properties, flow);
            int prePage = page - 1;
            int nextPage = page + 1;
            String preURL = ConstValue.CONTEXT_PATH + "/";
            String nextURL = ConstValue.CONTEXT_PATH + "/";
            String firstURL = ConstValue.CONTEXT_PATH + "/";
            String lastURL = ConstValue.CONTEXT_PATH + "/";
            if (properties == null && flow == null) {
                if (catID == 0) {
                    preURL = preURL + "Home" + "?page=" + prePage;
                    nextURL = nextURL + "Home" + "?page=" + nextPage;
                    firstURL = firstURL + "Home";
                    lastURL = lastURL + "Home" + "?page=" + number;
                } else {
                    preURL = preURL + "Home" + "?CategoryID=" + catID + "&page=" + prePage;
                    nextURL = nextURL + "Home" + "?CategoryID=" + catID + "&page=" + nextPage;
                    firstURL = firstURL + "Home" + "?CategoryID=" + catID;
                    lastURL = lastURL + "Home" + "?CategoryID=" + catID + "&page=" + number;
                }
            } else {
                if (catID == 0) {
                    preURL = preURL + "Home" + "?properties=" + properties + "&flow=" + flow + "&page=" + prePage;
                    nextURL = nextURL + "Home" + "?properties=" + properties + "&flow=" + flow + "&page=" + nextPage;
                    firstURL = firstURL + "Home" + "?properties=" + properties + "&flow=" + flow;
                    lastURL = lastURL + "Home" + "?properties=" + properties + "&flow=" + flow + "&page=" + number;
                } else {
                    preURL = preURL + "Home" + "?CategoryID=" + catID + "&properties=" + properties + "&flow=" + flow + "&page=" + prePage;
                    nextURL = nextURL + "Home" + "?CategoryID=" + catID + "&properties=" + properties + "&flow=" + flow + "&page=" + nextPage;
                    firstURL = firstURL + "Home" + "?CategoryID=" + catID + "&properties=" + properties + "&flow=" + flow;
                    lastURL = lastURL + "Home" + "?CategoryID=" + catID + "&properties=" + properties + "&flow=" + flow + "&page=" + number;
                }
            }
            map.put("CategoryID", catID);
            map.put("flow", flow);
            map.put("properties", properties);
            map.put("listProduct", listProduct);
            map.put("listCategory", listCategory);
            map.put("number", number);
            map.put("pageSelected", page);
            map.put("previous", preURL);
            map.put("next", nextURL);
            map.put("first", firstURL);
            map.put("last", lastURL);
            return new ModelAndView("Home/Index", map);
        }
        return new ModelAndView(ConstValue.REDIRECT + "/ManagerProduct");
    }
}
