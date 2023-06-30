package Model;

import Const.ConstValue;
import Entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOProduct extends ConnectDatabase {

    public int getNumberOfPage(int CategoryID) {
        String sql = "select count(ProductID) from Product";
        if (CategoryID == 0) {
            sql = sql + "";
        } else {
            sql = sql + " where CategoryID = " + CategoryID;
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
        if (number <= ConstValue.MAX_PRODUCT_IN_PAGE) {
            number = 1;
        } else if ((number / ConstValue.MAX_PRODUCT_IN_PAGE) > (Math.round(number / ConstValue.MAX_PRODUCT_IN_PAGE))) {
            number = Math.floor(number / ConstValue.MAX_PRODUCT_IN_PAGE) + 1;
        } else {
            number = Math.round(number / ConstValue.MAX_PRODUCT_IN_PAGE);

        }
        return (int) number;
    }

    public List<Product> getListProduct(String role, int CategoryID, int page, String properties, String flow) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product\n";
        String line;
        String order;
        // if not sort
        if (properties == null && flow == null) {
            order = "	order by ProductID\n";
            // if not choose category
            if (CategoryID == 0) {
                line = role.equals(ConstValue.ROLE_CUSTOMER) ? "     where isSold = 1\n" : "";
                sql = sql + line + order;
            } else {
                line = role.equals(ConstValue.ROLE_CUSTOMER) ? " and isSold = 1\n" : "\n";
                sql = sql
                        + "     where CategoryID = " + CategoryID + line
                        + order;
            }
        } else {
            order = "	order by " + properties + " " + flow + "\n";
            // if not choose category
            if (CategoryID == 0) {
                line = role.equals(ConstValue.ROLE_CUSTOMER) ? "     where isSold = 1\n" : "\n";
                sql = sql + line + order;
            } else {
                line = role.equals(ConstValue.ROLE_CUSTOMER) ? " and isSold = 1\n" : "\n";
                sql = sql + "     where CategoryID = " + CategoryID + line
                        + order;
            }
        }
        sql = sql + "	offset (" + ConstValue.MAX_PRODUCT_IN_PAGE + "*" + (page - 1) + ") row fetch next " + ConstValue.MAX_PRODUCT_IN_PAGE + " row only";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int ProductID = result.getInt(1);
                String ProductName = result.getString(2);
                String image = result.getString(3);
                double price = result.getDouble(4);
                int CatID = result.getInt(5);
                boolean isSold = result.getBoolean(6);
                Product product = new Product(ProductID, ProductName, image, price, CatID, isSold);
                list.add(product);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public Product getProduct(String ProductID) {
        String sql = "SELECT * FROM [dbo].[Product]\n"
                + "WHERE [ProductID] = " + ProductID;
        ResultSet result = getData(sql);
        try {
            // if get result successful
            if (result.next()) {
                String ProductName = result.getString(2);
                String image = result.getString(3);
                double price = result.getDouble(4);
                int CatID = result.getInt(5);
                boolean isSold = result.getBoolean(6);
                Product product = new Product(Integer.parseInt(ProductID), ProductName, image, price, CatID, isSold);
                return product;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public boolean isExist(String ProductName) {
        String sql = "SELECT * FROM [dbo].[Product]";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                if (ProductName.equalsIgnoreCase(result.getString(2))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public int AddProduct(Product product) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([ProductName]\n"
                + "           ,[image]\n"
                + "           ,[price]\n"
                + "           ,[CategoryID]\n"
                + "           ,[isSold])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, product.getProductName());
            prepare.setString(2, product.getImage());
            prepare.setDouble(3, product.getPrice());
            prepare.setInt(4, product.getCategoryID());
            prepare.setBoolean(5, product.isSold());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public int UpdateProduct(Product product) {
        int number = 0;// number of row affected
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[isSold] = ?\n"
                + " WHERE [ProductID]=?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, product.getProductName());
            prepare.setString(2, product.getImage());
            prepare.setDouble(3, product.getPrice());
            prepare.setInt(4, product.getCategoryID());
            prepare.setBoolean(5, product.isSold());
            prepare.setInt(6, product.getProductID());
            number = prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }

    public int DeleteProduct(String ProductID) {
        int number = 0;// number of row affected
        String sql = "DELETE FROM [dbo].[Product]\n"
                + " WHERE [ProductID] = " + ProductID;
        try {
            Statement statement = connect.createStatement();
            number = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return number;
    }
}
