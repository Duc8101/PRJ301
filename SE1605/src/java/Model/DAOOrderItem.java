package Model;

import Entity.OrderItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOOrderItem extends ConnectDatabase {

    public int AddOrderItem(OrderItem item) {
        String sql = "INSERT INTO [dbo].[order_items]([order_id],[item_id],[product_id],[quantity])\n"
                + "     VALUES (?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, item.getOrderID());
            prepare.setInt(2, item.getItemID());
            prepare.setInt(3, item.getProductID());
            prepare.setInt(4, item.getQuantity());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public List<OrderItem> getListOrderItem(int OrderID) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[order_items]\n"
                + "WHERE [order_id] = " + OrderID;
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int ItemID = result.getInt(2);
                int ProductID = result.getInt(3);
                int quantity = result.getInt(4);
                OrderItem item = new OrderItem(OrderID, ItemID, ProductID, quantity);
                list.add(item);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public boolean isExist(int ProductID) {
        String sql = "SELECT * FROM [dbo].[order_items]\n"
                + "WHERE [product_id] = " + ProductID;
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
