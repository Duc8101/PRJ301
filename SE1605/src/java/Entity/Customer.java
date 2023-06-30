package Entity;

public class Customer extends Profile {

    private int ID;
    private String street;
    private String city;
    private String state;
    private String ZipCode;

    public Customer() {
    }

    public Customer(int ID, String FirstName, String LastName, String phone, String email, String street, String city, String state, String ZipCode, String username, String password) {
        super(FirstName, LastName, phone, email, username, password);
        this.ID = ID;
        this.street = street;
        this.city = city;
        this.state = state;
        this.ZipCode = ZipCode;
    }

    public Customer(String FirstName, String LastName, String phone, String email, String street, String city, String state, String ZipCode, String username, String password) {
        super(FirstName, LastName, phone, email, username, password);
        this.street = street;
        this.city = city;
        this.state = state;
        this.ZipCode = ZipCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

}
