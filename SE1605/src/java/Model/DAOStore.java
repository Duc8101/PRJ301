package Model;

import Entity.Store;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOStore extends ConnectDatabase {

    public Store getStore(String state) {
        String sql = "SELECT * FROM [dbo].[stores]\n"
                + "where [state] = '" + state + "'";
        ResultSet result = this.getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int ID = result.getInt(1);
                String name = result.getString(2);
                String phone = result.getString(3);
                String email = result.getString(4);
                String street = result.getString(5);
                String city = result.getString(6);
                String ZipCode = result.getString(8);
                Store store = new Store(ID, name, phone, email, street, city, state, ZipCode);
                return store;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public Store getStore(int ID) {
        String sql = "SELECT * FROM [dbo].[stores]\n"
                + "where [store_id] = " + ID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String name = result.getString(2);
                String phone = result.getString(3);
                String email = result.getString(4);
                String street = result.getString(5);
                String city = result.getString(6);
                String state = result.getString(7);
                String ZipCode = result.getString(8);
                Store store = new Store(ID, name, phone, email, street, city, state, ZipCode);
                return store;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public List<String> getAllState() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[stores]\n";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                String state = result.getString(7);
                list.add(state);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public int UpdateStore(Store store) {
        String sql = "UPDATE [dbo].[stores]\n"
                + "   SET [store_name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[street] = ?\n"
                + "      ,[city] = ?\n"
                + "      ,[zip_code] = ?\n"
                + " WHERE [store_id] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, store.getName());
            prepare.setString(2, store.getPhone());
            prepare.setString(3, store.getEmail());
            prepare.setString(4, store.getStreet());
            prepare.setString(5, store.getCity());
            prepare.setString(6, store.getZipCode());
            prepare.setInt(7, store.getID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
