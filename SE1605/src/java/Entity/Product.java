package Entity;

public class Product {

    private int ID;
    private String ProductName;
    private int year;
    private double price;
    private String BrandName;
    private String CategoryName;

    public Product() {
    }

    public Product(int ID, String ProductName, int year, double price, String BrandName, String CategoryName) {
        this.ID = ID;
        this.ProductName = ProductName;
        this.year = year;
        this.price = price;
        this.BrandName = BrandName;
        this.CategoryName = CategoryName;
    }

    public Product(String ProductName, int year, double price, String BrandName, String CategoryName) {
        this.ProductName = ProductName;
        this.year = year;
        this.price = price;
        this.BrandName = BrandName;
        this.CategoryName = CategoryName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

}
