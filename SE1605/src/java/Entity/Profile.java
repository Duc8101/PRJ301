package Entity;

public abstract class Profile {

    private String FirstName;
    private String LastName;
    private String phone;
    private String email;
    private String username;
    private String password;

    public Profile() {
    }

    public Profile(String FirstName, String LastName, String phone, String email, String username, String password) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
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

}
