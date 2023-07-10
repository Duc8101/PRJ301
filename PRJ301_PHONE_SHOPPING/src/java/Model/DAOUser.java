package Model;

import Entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser extends ConnectDatabase {

    private User getObject(String sql) {
        ResultSet result = getData(sql);
        try {
            if (result.next()) {
                int ID = result.getInt(1);
                String FullName = result.getString(2);
                String phone = result.getString(3);
                String email = result.getString(4);
                String gender = result.getString(5);
                String address = result.getString(6);
                String username = result.getString(7);
                String password = result.getString(8);
                String RoleName = result.getString(9);
                User user = new User(ID, FullName, phone, email, gender, address, username, password, RoleName);
                return user;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public User getUser(String username) {
        String sql = "SELECT *\n"
                + "FROM [dbo].[User]\n"
                + "WHERE [username] = '" + username + "'";
        User user = this.getObject(sql);
        return user;
    }

    public int AddUser(User user) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([FullName]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[gender]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[RoleName])\n"
                + "           VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, user.getFullName());
            prepare.setString(2, user.getPhone());
            prepare.setString(3, user.getEmail());
            prepare.setString(4, user.getGender());
            prepare.setString(5, user.getUsername());
            prepare.setString(6, user.getPassword());
            prepare.setString(7, user.getRoleName());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
