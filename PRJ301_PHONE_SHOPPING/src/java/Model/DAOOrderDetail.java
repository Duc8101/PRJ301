package Model;

import Entity.OrderDetail;
import java.sql.*;
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

    private List<OrderDetail> getList(String sql) {
        List<OrderDetail> list = new ArrayList<>();
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int DetailID = result.getInt(1);
                int OrderID = result.getInt(2);
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

    public List<OrderDetail> getListOrderDetail(int OrderID) {
        String sql = "SELECT * FROM [dbo].[OrderDetail]\n"
                + "WHERE [OrderID] = " + OrderID;
        List<OrderDetail> list = this.getList(sql);
        return list;
    }
    
    public boolean isExist(int ProductID){
        String sql = "SELECT * FROM [dbo].[OrderDetail]\n"
                + "WHERE [ProductID] = " + ProductID;
        List<OrderDetail> list = this.getList(sql);
        return !list.isEmpty();
    }
}
