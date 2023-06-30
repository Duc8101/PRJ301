package Model;

import Entity.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOOrderDetail extends ConnectDatabase {

    public int AddOrderDetail(OrderDetail detail) {
        String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[quantity])\n"
                + "     VALUES(?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, detail.getOrderID());
            prepare.setInt(2, detail.getProductID());
            prepare.setInt(3, detail.getQuantity());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public List<OrderDetail> getListOrderDetail(int OrderID) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[OrderDetail]\n"
                + "WHERE [OrderID] = " + OrderID;
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int DetailID = result.getInt(1);
                int ProductID = result.getInt(3);
                int quantity = result.getInt(4);
                OrderDetail detail = new OrderDetail(DetailID, OrderID, ProductID, quantity);
                list.add(detail);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public boolean isExist(int ProductID) {
        String sql = "SELECT * FROM [dbo].[OrderDetail]\n"
                + "WHERE [ProductID] = " + ProductID;
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

}
