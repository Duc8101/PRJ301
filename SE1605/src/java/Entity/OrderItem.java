package Entity;

public class OrderItem {

    private int OrderID;
    private int ItemID;
    private int ProductID;
    private int quantity;

    public OrderItem(int OrderID, int ItemID, int ProductID, int quantity) {
        this.OrderID = OrderID;
        this.ItemID = ItemID;
        this.ProductID = ProductID;
        this.quantity = quantity;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int ItemID) {
        this.ItemID = ItemID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
