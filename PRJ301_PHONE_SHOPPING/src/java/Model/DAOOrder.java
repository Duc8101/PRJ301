package Model;

import Entity.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOOrder extends ConnectDatabase {

    public static final int MAX_ORDER_IN_PAGE = 20;

    public int AddOrder(Order order) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([UserID]\n"
                + "           ,[status]\n"
                + "           ,[OrderDate]\n"
                + "           ,[ShipDate]\n"
                + "           ,[address])\n"
                + "     VALUES (?,?,GETDATE(),?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, order.getUserID());
            prepare.setString(2, order.getStatus());
            prepare.setString(3, order.getShipDate());
            prepare.setString(4, order.getAddress());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int getNumberOfPage(String status) {
        String sql = "select count(OrderID) from [dbo].[Order]";
        if (status == null) {
            sql = sql + "";
        } else {
            sql = sql + " where [status] = '" + status + "'";
        }
        ResultSet result = getData(sql);
        double number = 0;
        try {
            // if get data successful
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

    public List<Order> getListOrder(String status, int page) {
        List<Order> list = new ArrayList<>();
        String sql;
        if (status == null) {
            sql = "SELECT * FROM [dbo].[Order]\n";
            if (page == 0) {
                sql = sql + "";
            } else {
                sql = sql + "	order by OrderID\n"
                        + "	offset (" + MAX_ORDER_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_ORDER_IN_PAGE + " row only";
            }
        } else {
            sql = "SELECT * FROM [dbo].[Order]\n"
                    + "WHERE [status] = '" + status + "'" + "\n"
                    + "	order by OrderID\n"
                    + "	offset (" + MAX_ORDER_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_ORDER_IN_PAGE + " row only";
        }
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int OrderID = result.getInt(1);
                int UserID = result.getInt(2);
                String Status = result.getString(3);
                String OrderDate = result.getString(4);
                String ShippedDate = result.getString(5);
                String address = result.getString(6);
                Order order = new Order(OrderID, UserID, Status, OrderDate, ShippedDate, address);
                list.add(order);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public int getNumberOfPage(int UserID) {
        String sql = "select count(OrderID) from [dbo].[Order] where [UserID] = " + UserID;
        ResultSet result = getData(sql);
        double number = 0;
        try {
            // if get data successful
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

    public List<Order> getListOrder(int page, int UserID) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Order]\n"
                + "WHERE [UserID] = " + UserID + "\n"
                + "	order by OrderID\n"
                + "	offset (" + MAX_ORDER_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_ORDER_IN_PAGE + " row only";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int OrderID = result.getInt(1);
                String status = result.getString(3);
                String OrderDate = result.getString(4);
                String ShippedDate = result.getString(5);
                String address = result.getString(6);
                Order order = new Order(OrderID, UserID, status, OrderDate, ShippedDate, address);
                list.add(order);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public Order getOrder(int OrderID) {
        String sql = "SELECT * FROM [dbo].[Order]\n"
                + "WHERE [OrderID] = " + OrderID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int UserID = result.getInt(2);
                String status = result.getString(3);
                String OrderDate = result.getString(4);
                String ShipDate = result.getString(5);
                String address = result.getString(6);
                Order order = new Order(OrderID, UserID, status, OrderDate, ShipDate, address);
                return order;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int UpdateOrder(Order order) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "SET [ShipDate] = ?\n"
                + ",    [status] = ?\n"
                + "WHERE [OrderID] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, order.getShipDate());
            prepare.setString(2, order.getStatus());
            prepare.setInt(3, order.getOrderID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
