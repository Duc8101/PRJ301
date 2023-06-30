package Entity;

public class Store {

    private int ID;
    private String name;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String state;
    private String ZipCode;

    public Store() {
    }

    public Store(int ID, String name, String phone, String email, String street, String city, String state, String ZipCode) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.ZipCode = ZipCode;
    }

    public Store(String name, String phone, String email, String street, String city, String state, String ZipCode) {
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
