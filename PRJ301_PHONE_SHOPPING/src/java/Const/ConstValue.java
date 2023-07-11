package Const;

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
    public static final String FORMAT_EMAIL = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})+$";
    public static final int MAX_PRODUCT_IN_PAGE = 6;
    public static final String[] LIST_STATUS = {STATUS_ALL, STATUS_NEW, STATUS_SHIPPING, STATUS_COMPLETED, STATUS_REJECTED};
    public static final int MAX_ORDER_IN_PAGE = 20;
    public static final String USERNAME_SELLER = ROLE_SELLER;
}
