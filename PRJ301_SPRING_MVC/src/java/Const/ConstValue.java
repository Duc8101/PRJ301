package Const;

import java.util.*;

public class ConstValue {

    public static final String ROLE_CUSTOMER = "Customer";
    public static final String ROLE_SELLER = "Seller";
    public static final String GENDER_MALE = "Male";
    public static final String GENDER_FEMALE = "Female";
    public static final int MAX_LENGTH_USERNAME = 50;
    public static final int MAX_LENGTH_PASSWORD = 50;
    public static final String FORMAT_PHONE = "^[0-9]{10}+$";
    public static final String FORMAT_USERNAME = "^[a-zA-Z][a-zA-Z0-9]+$";
    public static final int LENGTH_PHONE = 10;
    public static final String STATUS_NEW = "New";
    public static final String STATUS_SHIPPING = "Shipping";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_REJECTED = "Rejected";
    public static final String STATUS_ALL = "All";

    public static final List<String> getAllStatus() {
        List<String> list = new ArrayList<>();
        list.add(STATUS_ALL);
        list.add(STATUS_NEW);
        list.add(STATUS_SHIPPING);
        list.add(STATUS_COMPLETED);
        list.add(STATUS_REJECTED);
        return list;
    }
    
    public static final String CONTEXT_PATH = "/PRJ301_SPRING_MVC";
    public static final String REDIRECT = "redirect:";
    public static final int MAX_PRODUCT_IN_PAGE = 6;
    public static final int MAX_ORDER_IN_PAGE = 20;
}
