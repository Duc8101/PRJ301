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
@RequestMapping(value = "/Cart")
public class CartController {

    private final DAOProduct daoProduct = new DAOProduct();
    private final DAOOrder daoOrder = new DAOOrder();
    private final DAOUser daoUser = new DAOUser();
    private final DAOOrderDetail daoDetail = new DAOOrderDetail();

    @RequestMapping(value = "/AddCart", method = RequestMethod.GET)
    public String add(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ConstValue.REDIRECT + "/Login";
        }
        String ProductID = request.getParameter("ProductID");
        Integer quantity = (Integer) session.getAttribute(ProductID);
        // if product not exist in cart
        if (quantity == null) {
            session.setAttribute(ProductID, 1);
        } else {
            session.setAttribute(ProductID, quantity + 1);
        }
        return ConstValue.REDIRECT + "/Cart";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView Index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView(ConstValue.REDIRECT + "/Login");
        }
        Map<Product, Integer> map = getListCart(request);
        Map<String, Map<Product, Integer>> result = new HashMap<>();
        result.put("map", map);
        return new ModelAndView("Cart/Index", result);
    }

    private Map<Product, Integer> getListCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Product, Integer> map = new HashMap<>();
        Enumeration<String> enumeration = session.getAttributeNames();
        double sum = 0;
        while (enumeration.hasMoreElements()) {
            String ProductID = enumeration.nextElement();
            if (!ProductID.equals("user")) {
                int quantity = (int) session.getAttribute(ProductID);
                Product product = daoProduct.getProduct(ProductID);
                sum = sum + product.getPrice() * quantity;
                map.put(product, quantity);
            }
        }
        return map;
    }

    @RequestMapping(value = "/RemoveCart", method = RequestMethod.GET)
    public String remove(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ConstValue.REDIRECT + "/Login";
        }
        String ProductID = request.getParameter("ProductID");
        session.removeAttribute(ProductID);
        return ConstValue.REDIRECT + "/Cart";
    }

    @RequestMapping(value = "/Checkout", method = RequestMethod.GET)
    public ModelAndView Checkout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView(ConstValue.REDIRECT + "/Login");
        }
        Map<Product, Integer> map = getListCart(request);
        Map<String, Map<Product, Integer>> result = new HashMap<>();
        result.put("map", map);
        return new ModelAndView("Cart/Checkout", result);
    }

    @RequestMapping(value = "/Checkout", method = RequestMethod.POST)
    public ModelAndView doCheckout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> result = new HashMap<>();
        String address = request.getParameter("address").trim();
        String Sum = request.getParameter("sum");
        double sum = Double.parseDouble(Sum);
        User user = (User) session.getAttribute("user");
        Map<Product, Integer> map = getListCart(request);
        result.put("map", map);
        if (address.isEmpty()) {
            result.put("message", "You have to input address");
        } else if (user.getMoney() - sum < 0) {
            result.put("message", "You don't have enough money to check out");
        } else {
            Order order = new Order(user.getID(), ConstValue.STATUS_NEW, null, address.trim());
            daoOrder.AddOrder(order);
            List<Order> list = daoOrder.getListOrder(null, 0);
            int OrderID = list.get(list.size() - 1).getOrderID();
            int number = 0;
            Set<Product> set = map.keySet();
            for (Product key : set) {
                int quantity = map.get(key);
                OrderDetail detail = new OrderDetail(OrderID, key.getProductID(), quantity);
                number = daoDetail.AddOrderDetail(detail);
            }
            // if add successful
            if (number > 0) {
                User seller = daoUser.getUser("seller");
                double money = user.getMoney() - sum;
                user.setMoney(money);
                seller.setMoney(seller.getMoney() + sum);
                daoUser.UpdateMoney(user.getUsername(), money);
                daoUser.UpdateMoney(seller.getUsername(), seller.getMoney());
                session.setAttribute("user", user);
                result.put("address", address);
                result.put("mess", "Check out successful");
                request.setAttribute("mess", "Check out successful");
                for (Product key : set) {
                    session.removeAttribute(key.getProductID() + "");
                }
            }
        }
        return new ModelAndView("Cart/Checkout", result);
    }

}
