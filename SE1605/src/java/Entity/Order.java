package Entity;

public class Order {

    private int OrderID;
    private int CustomerID;
    private int status;
    private String OrderDate;
    private String RequiredDate;
    private String ShippedDate;
    private int StoreID;
    private int StaffID;

    public Order() {
    }

    public Order(int OrderID, int CustomerID, int status, String OrderDate, String RequiredDate, String ShippedDate, int StoreID, int StaffID) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.status = status;
        this.OrderDate = OrderDate;
        this.RequiredDate = RequiredDate;
        this.ShippedDate = ShippedDate;
        this.StoreID = StoreID;
        this.StaffID = StaffID;
    }

    public Order(int CustomerID, int status, String OrderDate, String RequiredDate, String ShippedDate, int StoreID, int StaffID) {
        this.CustomerID = CustomerID;
        this.status = status;
        this.OrderDate = OrderDate;
        this.RequiredDate = RequiredDate;
        this.ShippedDate = ShippedDate;
        this.StoreID = StoreID;
        this.StaffID = StaffID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getRequiredDate() {
        return RequiredDate;
    }

    public void setRequiredDate(String RequiredDate) {
        this.RequiredDate = RequiredDate;
    }

    public String getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(String ShippedDate) {
        this.ShippedDate = ShippedDate;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }

    public int getStaffID() {
        return StaffID;
    }

    public void setStaffID(int StaffID) {
        this.StaffID = StaffID;
    }

}
