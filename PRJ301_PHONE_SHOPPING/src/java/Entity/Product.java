package Entity;

public class Product {

    private int ProductID;
    private String ProductName;
    private String image;
    private double price;
    private int CategoryID;
    private boolean isSold;

    public Product() {
    }

    public Product(int ProductID, String ProductName, String image, double price, int CategoryID, boolean isSold) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.image = image;
        this.price = price;
        this.CategoryID = CategoryID;
        this.isSold = isSold;
    }

    public Product(String ProductName, String image, double price, int CategoryID, boolean isSold) {
        this.ProductName = ProductName;
        this.image = image;
        this.price = price;
        this.CategoryID = CategoryID;
        this.isSold = isSold;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setIsSold(boolean isSold) {
        this.isSold = isSold;
    }

}
