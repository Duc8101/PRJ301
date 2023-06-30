package Entity;

public class Stock {

    private int StoreID;
    private int ProductID;
    private int quantity;

    public Stock() {
    }

    public Stock(int StoreID, int ProductID, int quantity) {
        this.StoreID = StoreID;
        this.ProductID = ProductID;
        this.quantity = quantity;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
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
