package Model;

import Entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOCustomer extends ConnectDatabase {

    private static final int MAX_CUSTOMER_IN_PAGE = 20;

    public Customer getCustomer(String username) {
        String sql = "SELECT * FROM [dbo].[customers]\n"
                + "where [username] = '" + username + "'";
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int ID = result.getInt(1);
                String FirstName = result.getString(2);
                String LastName = result.getString(3);
                String phone = result.getString(4);
                String email = result.getString(5);
                String street = result.getString(6);
                String city = result.getString(7);
                String state = result.getString(8);
                String ZipCode = result.getString(9);
                String password = result.getString(11);
                Customer customer = new Customer(ID, FirstName, LastName, phone, email, street, city, state, ZipCode, username, password);
                return customer;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int AddCustomer(Customer customer) {
        String sql = "INSERT INTO [dbo].[customers]([first_name],[last_name],[phone],[email],[street],[city],[state],[zip_code],[username],[password])\n"
                + " VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, customer.getFirstName());
            prepare.setString(2, customer.getLastName());
            prepare.setString(3, customer.getPhone());
            prepare.setString(4, customer.getEmail());
            prepare.setString(5, customer.getStreet());
            prepare.setString(6, customer.getCity());
            prepare.setString(7, customer.getState());
            prepare.setString(8, customer.getZipCode());
            prepare.setString(9, customer.getUsername());
            prepare.setString(10, customer.getPassword());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdateProfile(Customer customer) {
        String sql = "UPDATE [dbo].[customers]\n"
                + "   SET [first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[street] = ?\n"
                + "      ,[city] = ?\n"
                + " WHERE [username] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, customer.getFirstName());
            prepare.setString(2, customer.getLastName());
            prepare.setString(3, customer.getPhone());
            prepare.setString(4, customer.getEmail());
            prepare.setString(5, customer.getStreet());
            prepare.setString(6, customer.getCity());
            prepare.setString(7, customer.getUsername());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdatePassword(String username, String password) {
        String sql = "UPDATE [dbo].[customers]\n"
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

    public Customer getCustomer(int ID) {
        String sql = "SELECT * FROM [dbo].[customers]\n"
                + "where [id] = " + ID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                String FirstName = result.getString(2);
                String LastName = result.getString(3);
                String phone = result.getString(4);
                String email = result.getString(5);
                String street = result.getString(6);
                String city = result.getString(7);
                String state = result.getString(8);
                String ZipCode = result.getString(9);
                String username = result.getString(10);
                String password = result.getString(11);
                Customer customer = new Customer(ID, FirstName, LastName, phone, email, street, city, state, ZipCode, username, password);
                return customer;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int getNumberPage(String state) {
        String sql = "select count(id) from [dbo].[customers] where [state] = '" + state + "'";
        double number = 0;
        ResultSet result = getData(sql);
        try {
            if (result.next()) {
                number = result.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (number <= MAX_CUSTOMER_IN_PAGE) {
            number = 1;
        } else if ((number / MAX_CUSTOMER_IN_PAGE) > (Math.round(number / MAX_CUSTOMER_IN_PAGE))) {
            number = Math.floor(number / MAX_CUSTOMER_IN_PAGE) + 1;
        } else {
            number = Math.round(number / MAX_CUSTOMER_IN_PAGE);

        }
        return (int) number;
    }

    public List<Customer> getListCustomer(String state, int page) {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[customers]\n"
                + "where [state] = '" + state + "'" + "\n"
                + "ORDER BY [username]\n"
                + "offset (" + MAX_CUSTOMER_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_CUSTOMER_IN_PAGE + " row only ";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int ID = result.getInt(1);
                String FirstName = result.getString(2);
                String LastName = result.getString(3);
                String phone = result.getString(4);
                String email = result.getString(5);
                String street = result.getString(6);
                String city = result.getString(7);
                String ZipCode = result.getString(9);
                String username = result.getString(10);
                String password = result.getString(11);
                Customer customer = new Customer(ID, FirstName, LastName, phone, email, street, city, state, ZipCode, username, password);
                list.add(customer);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public boolean isExist(String table, String zip) {
        String sql = "SELECT * FROM [dbo].[" + table + "] WHERE [zip_code] = '" + zip + "'";
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public int UpdateZipCode(String zip, String username) {
        String sql = "UPDATE [dbo].[customers]\n"
                + "   SET [zip_code] = ?\n"
                + " WHERE [username] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, zip);
            prepare.setString(2, username);
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

}
