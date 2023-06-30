package Model;

import Entity.Staff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOStaff extends ConnectDatabase {

    private static final int MAX_STAFF_IN_PAGE = 20;

    public Staff getStaff(String username) {
        String sql = "SELECT * FROM [dbo].[staffs]\n"
                + "where [username] = '" + username + "'";
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int StaffID = result.getInt(1);
                String FirstName = result.getString(2);
                String LastName = result.getString(3);
                String email = result.getString(4);
                String phone = result.getString(5);
                boolean active = result.getBoolean(6);
                int StoreID = result.getInt(7);
                String ManagerID = result.getString(8);
                String password = result.getString(10);
                Staff staff = new Staff(StaffID, FirstName, LastName, email, phone, active, StoreID, ManagerID, username, password);
                return staff;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public List<Staff> getListStaff(int StoreID) {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[staffs]\n"
                + "where [store_id] = " + StoreID + " and [active] = 1";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int StaffID = result.getInt(1);
                String FirstName = result.getString(2);
                String LastName = result.getString(3);
                String email = result.getString(4);
                String phone = result.getString(5);
                String ManagerID = result.getString(8);
                String username = result.getString(9);
                String password = result.getString(10);
                Staff staff = new Staff(StaffID, FirstName, LastName, email, phone, true, StoreID, ManagerID, username, password);
                list.add(staff);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public int UpdateStaff(Staff staff) {
        String sql = "UPDATE [dbo].[staffs]\n"
                + "   SET [first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phone] = ?\n"
                + " WHERE [username] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, staff.getFirstName());
            prepare.setString(2, staff.getLastName());
            prepare.setString(3, staff.getEmail());
            prepare.setString(4, staff.getPhone());
            prepare.setString(5, staff.getUsername());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int getNumberPage(String ManagerID) {
        String sql = "SELECT * FROM [dbo].[staffs] where [manager_id] = " + ManagerID;
        double number = 0;
        ResultSet result = getData(sql);
        try {
            if (result.next()) {
                number = result.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (number <= MAX_STAFF_IN_PAGE) {
            number = 1;
        } else if ((number / MAX_STAFF_IN_PAGE) > (Math.round(number / MAX_STAFF_IN_PAGE))) {
            number = Math.floor(number / MAX_STAFF_IN_PAGE) + 1;
        } else {
            number = Math.round(number / MAX_STAFF_IN_PAGE);
        }
        return (int) number;
    }

    public List<Staff> getListStaff(String ManagerID, int page) {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[staffs]\n";
        if (ManagerID == null) {
            sql = sql + "";
        } else {
            sql = sql + "where [manager_id] = " + ManagerID + "\n"
                    + "order by staff_id\n"
                    + "offset (" + MAX_STAFF_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_STAFF_IN_PAGE + " row only";
        }
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int StaffID = result.getInt(1);
                String FirstName = result.getString(2);
                String LastName = result.getString(3);
                String email = result.getString(4);
                String phone = result.getString(5);
                boolean active = result.getBoolean(6);
                int StoreID = result.getInt(7);
                String managerID = result.getString(8);
                String username = result.getString(9);
                String password = result.getString(10);
                Staff staff = new Staff(StaffID, FirstName, LastName, email, phone, active, StoreID, managerID, username, password);
                list.add(staff);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public int UpdateStaff(boolean active, String StaffID) {
        String sql = "UPDATE [dbo].[staffs]\n"
                + "   SET [active] = ?\n"
                + " WHERE [staff_id] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setBoolean(1, active);
            prepare.setString(2, StaffID);
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int AddStaff(Staff staff) {
        String sql = "INSERT INTO [dbo].[staffs]\n"
                + "           ([first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[active]\n"
                + "           ,[store_id]\n"
                + "           ,[manager_id]\n"
                + "           ,[username]\n"
                + "           ,[password])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, staff.getFirstName());
            prepare.setString(2, staff.getLastName());
            prepare.setString(3, staff.getEmail());
            prepare.setString(4, staff.getPhone());
            prepare.setBoolean(5, staff.isActive());
            prepare.setInt(6, staff.getStoreID());
            prepare.setString(7, staff.getManagerID());
            prepare.setString(8, staff.getUsername());
            prepare.setString(9, staff.getPassword());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
