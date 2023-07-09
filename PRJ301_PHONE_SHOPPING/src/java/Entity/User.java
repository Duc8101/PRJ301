package Entity;

public class User {

    private int ID;
    private String FullName;
    private String phone;
    private String email;
    private String gender;
    private String address;
    private String username;
    private String password;
    private String RoleName;

    public User() {
    }

    public User(int ID, String FullName, String phone, String email, String gender, String address, String username, String password, String RoleName) {
        this.ID = ID;
        this.FullName = FullName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.password = password;
        this.RoleName = RoleName;
    }

    public User(String FullName, String phone, String email, String gender, String address, String username, String password, String RoleName) {
        this.FullName = FullName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.password = password;
        this.RoleName = RoleName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

}
