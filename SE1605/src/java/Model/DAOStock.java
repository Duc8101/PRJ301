package Model;

import Entity.Stock;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOStock extends ConnectDatabase {

    public Stock getStock(int StoreID, int ProductID) {
        String sql = "SELECT * FROM [dbo].[stocks]\n"
                + "WHERE [store_id] = " + StoreID + " and [product_id] = " + ProductID;
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int quantity = result.getInt(3);
                Stock stock = new Stock(StoreID, ProductID, quantity);
                return stock;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int UpdateStock(Stock stock) {
        String sql = "UPDATE [dbo].[stocks]\n"
                + "SET [quantity] = ?\n"
                + " WHERE [store_id] = ?\n"
                + " AND [product_id] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, stock.getQuantity());
            prepare.setInt(2, stock.getStoreID());
            prepare.setInt(3, stock.getProductID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int AddStock(Stock stock) {
        String sql = "INSERT INTO [dbo].[stocks]\n"
                + "           ([store_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES (?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, stock.getStoreID());
            prepare.setInt(2, stock.getProductID());
            prepare.setInt(3, stock.getQuantity());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int DeleteStock(int StoreID, String ProductID) {
        String sql = "DELETE FROM [dbo].[stocks]\n"
                + " WHERE [store_id] = " + StoreID + "\n"
                + " AND [product_id] = " + ProductID;
        try {
            Statement statement = connect.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
