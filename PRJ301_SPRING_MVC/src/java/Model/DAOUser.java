package Model;

import Entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser extends ConnectDatabase {

    public User getUser(String username) {
        String sql = "SELECT *\n"
                + "FROM [dbo].[User]\n"
                + "WHERE [username] = '" + username + "'";
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int ID = result.getInt(1);
                String FullName = result.getString(2);
                String phone = result.getString(3);
                String email = result.getString(4);
                String gender = result.getString(5);
                String address = result.getString(6);
                double money = result.getDouble(7);
                String password = result.getString(9);
                String RoleName = result.getString(10);
                User user = new User(ID, FullName, phone, email, gender, address, money, username, password, RoleName);
                return user;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int AddUser(User user) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([FullName]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[gender]\n"
                + "           ,[address]\n"
                + "           ,[money]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[RoleName])\n"
                + "           VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, user.getFullName());
            prepare.setString(2, user.getPhone());
            prepare.setString(3, user.getEmail());
            prepare.setString(4, user.getGender());
            prepare.setString(5, user.getAddress());
            prepare.setDouble(6, user.getMoney());
            prepare.setString(7, user.getUsername());
            prepare.setString(8, user.getPassword());
            prepare.setString(9, user.getRoleName());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdateProfile(User user) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [FullName] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[gender] =   ?\n"
                + " WHERE [username] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, user.getFullName());
            prepare.setString(2, user.getPhone());
            prepare.setString(3, user.getEmail());
            prepare.setString(4, user.getGender());
            prepare.setString(5, user.getUsername());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdatePassword(String username, String password) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [password] = ?\n"
                + " WHERE [username] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, password);
            prepare.setString(2, username);
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public User getUser(int UserID) {
        String sql = "SELECT *\n"
                + "FROM [dbo].[User]\n"
                + "WHERE [ID] = " + UserID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String FullName = result.getString(2);
                String phone = result.getString(3);
                String email = result.getString(4);
                String gender = result.getString(5);
                String address = result.getString(6);
                double money = result.getDouble(7);
                String username = result.getString(8);
                String password = result.getString(9);
                String RoleName = result.getString(10);
                User user = new User(UserID, FullName, phone, email, gender, address, money, username, password, RoleName);
                return user;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int UpdateMoney(String username, double money) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [money] = ?\n"
                + " WHERE [username] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setDouble(1, money);
            prepare.setString(2, username);
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
