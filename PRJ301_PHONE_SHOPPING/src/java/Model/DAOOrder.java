package Model;

import Const.ConstValue;
import Entity.Order;
import java.sql.*;
import java.util.*;

public class DAOOrder extends ConnectDatabase {

    public int AddOrder(Order order) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([UserID]\n"
                + "           ,[status]\n"
                + "           ,[OrderDate]\n"
                + "           ,[ShipDate]\n"
                + "           ,[address])\n"
                + "     VALUES (?,'" + ConstValue.STATUS_NEW + "',GETDATE(),NULL,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, order.getUserID());
            prepare.setString(2, order.getAddress());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    private List<Order> getList(String sql) {
        List<Order> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int OrderID = result.getInt(1);
                int UserID = result.getInt(2);
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

    public List<Order> getListOrder(String status, int page) {
        String sql = "SELECT * FROM [dbo].[Order]\n";
        if (status != null && !status.equals(ConstValue.STATUS_ALL)) {
            sql = sql + "WHERE [status] = '" + status + "'" + "\n";
        }
        if (page != 0) {
            sql = sql + "	order by OrderID\n"
                    + "	offset (" + ConstValue.MAX_ORDER_IN_PAGE + "*" + (page - 1) + ") row fetch next " + ConstValue.MAX_ORDER_IN_PAGE + " row only";
        }
        List<Order> list = this.getList(sql);
        return list;
    }

    public List<Order> getListOrder(int page, int UserID) {
        String sql = "SELECT * FROM [dbo].[Order]\n"
                + "WHERE [UserID] = " + UserID + "\n"
                + "	order by OrderID\n"
                + "	offset (" + ConstValue.MAX_ORDER_IN_PAGE + "*" + (page - 1) + ") row fetch next " + ConstValue.MAX_ORDER_IN_PAGE + " row only";
        List<Order> list = this.getList(sql);
        return list;
    }

    public int getNumberPage(int UserID) {
        String sql = "select * from [dbo].[Order] where [UserID] = " + UserID;
        List<Order> list = this.getList(sql);
        double number = list.size();
        if (number <= ConstValue.MAX_ORDER_IN_PAGE) {
            number = 1;
        } else if ((number / ConstValue.MAX_ORDER_IN_PAGE) > (Math.round(number / ConstValue.MAX_ORDER_IN_PAGE))) {
            number = Math.floor(number / ConstValue.MAX_ORDER_IN_PAGE) + 1;
        } else {
            number = Math.round(number / ConstValue.MAX_ORDER_IN_PAGE);
        }
        return (int) number;
    }

    public Order getOrder(int OrderID) {
        String sql = "select * from [dbo].[Order] where [OrderID] = " + OrderID;
        List<Order> list = this.getList(sql);
        return list.isEmpty() ? null : list.get(0);
    }

    public int getNumberPage(String status) {
        String sql = "select * from [dbo].[Order]";
        if (status != null && !status.equals(ConstValue.STATUS_ALL)) {
            sql = sql + "WHERE [status] = '" + status + "'" + "\n";
        }
        List<Order> list = this.getList(sql);
        double number = list.size();
        if (number <= ConstValue.MAX_ORDER_IN_PAGE) {
            number = 1;
        } else if ((number / ConstValue.MAX_ORDER_IN_PAGE) > (Math.round(number / ConstValue.MAX_ORDER_IN_PAGE))) {
            number = Math.floor(number / ConstValue.MAX_ORDER_IN_PAGE) + 1;
        } else {
            number = Math.round(number / ConstValue.MAX_ORDER_IN_PAGE);
        }
        return (int) number;
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
