package Entity;

public class Staff extends Profile {

    private int StaffID;
    private boolean active;
    private int StoreID;
    private String ManagerID;

    public Staff() {
    }

    public Staff(int StaffID, String FirstName, String LastName, String email, String phone, boolean active, int StoreID, String ManagerID, String username, String password) {
        super(FirstName, LastName, phone, email, username, password);
        this.StaffID = StaffID;
        this.active = active;
        this.StoreID = StoreID;
        this.ManagerID = ManagerID;
    }

    public Staff(String FirstName, String LastName, String email, String phone, boolean active, int StoreID, String ManagerID, String username, String password) {
        super(FirstName, LastName, phone, email, username, password);
        this.active = active;
        this.StoreID = StoreID;
        this.ManagerID = ManagerID;
    }

    public int getStaffID() {
        return StaffID;
    }

    public void setStaffID(int StaffID) {
        this.StaffID = StaffID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }

    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String ManagerID) {
        this.ManagerID = ManagerID;
    }

}
