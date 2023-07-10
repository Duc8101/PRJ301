package Model;

import Entity.OrderDetail;
import java.sql.*;

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
}
