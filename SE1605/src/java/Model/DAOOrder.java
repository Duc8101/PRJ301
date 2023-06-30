package Model;

import Entity.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOOrder extends ConnectDatabase {

    public final static int MAX_ORDER_IN_PAGE = 20;

    public int AddOrder(Order order) {
        String sql = "INSERT INTO [dbo].[orders] ([customer_id],[order_status],[order_date],[required_date],[shipped_date],[store_id],[staff_id])\n"
                + "     VALUES (?,?,GETDATE(),?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, order.getCustomerID());
            prepare.setInt(2, order.getStatus());
            prepare.setString(3, order.getRequiredDate());
            prepare.setString(4, order.getShippedDate());
            prepare.setInt(5, order.getStoreID());
            prepare.setInt(6, order.getStaffID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public Order getOrderAdd() {
        String sql = "SELECT TOP 1 * FROM [dbo].[orders]\n"
                + "ORDER BY order_id DESC";
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int OrderID = result.getInt(1);
                int CustomerID = result.getInt(2);
                int status = result.getInt(3);
                String OrderDate = result.getString(4);
                String RequiredDate = result.getString(5);
                String ShippedDate = result.getString(6);
                int StoreID = result.getInt(7);
                int StaffID = result.getInt(8);
                Order order = new Order(OrderID, CustomerID, status, OrderDate, RequiredDate, ShippedDate, StoreID, StaffID);
                return order;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int getNumberPage(int CustomerID) {
        String sql = "select count(order_id) from [dbo].[orders]";
        if (CustomerID == 0) {
            sql = sql + "";
        } else {
            sql = sql + " WHERE [customer_id] = " + CustomerID;
        }
        double number = 0;
        ResultSet result = getData(sql);
        try {
            if (result.next()) {
                number = result.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (number <= MAX_ORDER_IN_PAGE) {
            number = 1;
        } else if ((number / MAX_ORDER_IN_PAGE) > (Math.round(number / MAX_ORDER_IN_PAGE))) {
            number = Math.floor(number / MAX_ORDER_IN_PAGE) + 1;
        } else {
            number = Math.round(number / MAX_ORDER_IN_PAGE);

        }
        return (int) number;
    }

    public int getNumberPage(int status, int StaffID) {
        String sql = "select count(order_id) from [dbo].[orders] WHERE [order_status] = " + status;
        if (status == 0) {
            sql = sql + "";
        } else {
            sql = sql + " AND [staff_id] = " + StaffID;
        }
        double number = 0;
        ResultSet result = getData(sql);
        try {
            if (result.next()) {
                number = result.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (number <= MAX_ORDER_IN_PAGE) {
            number = 1;
        } else if ((number / MAX_ORDER_IN_PAGE) > (Math.round(number / MAX_ORDER_IN_PAGE))) {
            number = Math.floor(number / MAX_ORDER_IN_PAGE) + 1;
        } else {
            number = Math.round(number / MAX_ORDER_IN_PAGE);

        }
        return (int) number;
    }

    public List<Order> getListOrder(int CustomerID, int page) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[orders]\n"
                + "WHERE [customer_id] = " + CustomerID + "\n"
                + "order by order_id\n"
                + "offset (" + MAX_ORDER_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_ORDER_IN_PAGE + " row only ";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int OrderID = result.getInt(1);
                int status = result.getInt(3);
                String OrderDate = result.getString(4);
                String RequiredDate = result.getString(5);
                String ShippedDate = result.getString(6);
                int StoreID = result.getInt(7);
                int StaffID = result.getInt(8);
                Order order = new Order(OrderID, CustomerID, status, OrderDate, RequiredDate, ShippedDate, StoreID, StaffID);
                list.add(order);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<Order> getListOrder(int status, int StaffID, int page) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[orders]\n"
                + "WHERE [staff_id] = " + StaffID;
        // if all status
        if (status == 0) {
            sql = sql + "\n";
        } else {
            sql = sql + " and [order_status] = " + status + "\n";
        }
        sql = sql + "order by order_id\n"
                + "offset (" + MAX_ORDER_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_ORDER_IN_PAGE + " row only ";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int OrderID = result.getInt(1);
                int CustomerID = result.getInt(2);
                int Status = result.getInt(3);
                String OrderDate = result.getString(4);
                String RequiredDate = result.getString(5);
                String ShippedDate = result.getString(6);
                int StoreID = result.getInt(7);
                Order order = new Order(OrderID, CustomerID, Status, OrderDate, RequiredDate, ShippedDate, StoreID, StaffID);
                list.add(order);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public Order getOrder(int OrderID) {
        String sql = "SELECT * FROM [dbo].[orders]\n"
                + "WHERE [order_id] = " + OrderID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int CustomerID = result.getInt(2);
                int status = result.getInt(3);
                String OrderDate = result.getString(4);
                String RequiredDate = result.getString(5);
                String ShippedDate = result.getString(6);
                int StoreID = result.getInt(7);
                int StaffID = result.getInt(8);
                Order order = new Order(OrderID, CustomerID, status, OrderDate, RequiredDate, ShippedDate, StoreID, StaffID);
                return order;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int UpdateOrder(Order order) {
        String sql = "UPDATE [dbo].[orders]\n"
                + "SET [shipped_date] = ?\n"
                + ",    [order_status] = ?\n"
                + "WHERE [order_id] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, order.getShippedDate());
            prepare.setInt(2, order.getStatus());
            prepare.setInt(3, order.getOrderID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
