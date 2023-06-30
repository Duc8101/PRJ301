package Const;

public class ConstValue {

    public static final int INT_STATUS_WAIT = 1;
    public static final int INT_STATUS_PROCESS = 2;
    public static final int INT_STATUS_DONE = 3;
    public static final int INT_STATUS_CANCEL = 4;
    public static final String STRING_STATUS_WAIT = "Wait";
    public static final String STRING_STATUS_PROCESS = "Process";
    public static final String STRING_STATUS_DONE = "Done";
    public static final String STRING_STATUS_CANCEL = "Cancel";
    public static final String FORMAT_PHONE = "^[(]+[0-9]{3}+[)]+[ ]+[0-9]{3}+[-]+[0-9]{4}+$";
    public static final int MAX_LENGTH_USERNAME = 25;
    public static final int MAX_LENGTH_PASSWORD = 25;
    public static final String ROLE_CUSTOMER = "Customer";
    public static final String ROLE_STAFF = "Staff";
    public static final String[] LIST_CATEGORY = {
        "Children Bicycles", "Comfort Bicycles", "Cruisers Bicycles", "Cyclocross Bicycles",
        "Electric Bikes", "Mountain Bikes", "Road Bikes"
    };
    public static final String[] LIST_BRAND = {
        "Electra", "Haro", "Heller", "Pure Cycles",
        "Ritchey", "Sun Bicycles", "Surly", "Trek"
    };
    public static int LENGTH_ZIP_CODE = 5;
    public static final String FORMAT_USERNAME = "^[a-zA-Z][a-zA-Z0-9]+$";

}
