package Model;

import Const.ConstValue;
import Entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOProduct extends ConnectDatabase {

    private final static int MAX_PRODUCT_IN_PAGE = 20;

    public int getNumberPage(String brand, String category) {
        String sql = "select count(product_id) from products";
        if (brand == null && category == null) {
            sql = sql + "";
        } else {
            sql = sql + " where category_name = '" + category + "'" + " and brand_name = '" + brand + "'";
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
        if (number <= MAX_PRODUCT_IN_PAGE) {
            number = 1;
        } else if ((number / MAX_PRODUCT_IN_PAGE) > (Math.round(number / MAX_PRODUCT_IN_PAGE))) {
            number = Math.floor(number / MAX_PRODUCT_IN_PAGE) + 1;
        } else {
            number = Math.round(number / MAX_PRODUCT_IN_PAGE);

        }
        return (int) number;
    }

    public List<Product> getListProduct(String role, int page, int StoreID, String brand, String category) {
        List<Product> list = new ArrayList<>();
        String sql;
        if (StoreID == 0) {
            sql = "select * from products\n";
            if (brand == null && category == null) {
                sql = sql + "";
            } else {
                sql = sql + "where category_name = '" + category + "'" + " and brand_name = '" + brand + "'" + "\n";
            }
        } else {
            sql = "select distinct p.* from (products p join stocks sk\n"
                    + "on sk.product_id = p.product_id) join stores s\n"
                    + "on sk.store_id = s.store_id \n";
            String whereClause = role.equals(ConstValue.ROLE_CUSTOMER) ? "where sk.quantity > 0 and s.store_id = " + StoreID + "\n" : "where s.store_id = " + StoreID + "\n";
            sql = sql + whereClause;
            if (brand == null && category == null) {
                sql = sql + "";
            } else {
                sql = sql + " and p.category_name ='" + category + "'" + " and p.brand_name ='" + brand + "'" + "\n";
            }
        }
        sql = sql + "order by product_id\n"
                + "offset (" + MAX_PRODUCT_IN_PAGE + "*" + (page - 1) + ") row fetch next " + MAX_PRODUCT_IN_PAGE + " row only ";
        ResultSet result = getData(sql);
        try {
            while (result.next()) {
                int ID = result.getInt(1);
                String ProductName = result.getString(2);
                int year = result.getInt(3);
                double price = result.getDouble(4);
                String BrandName = result.getString(5);
                String CategoryName = result.getString(6);
                Product product = new Product(ID, ProductName, year, price, BrandName, CategoryName);
                list.add(product);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public Product getProduct(String ID) {
        String sql = "SELECT * FROM products\n"
                + "WHERE [product_id] = " + ID;
        ResultSet result = getData(sql);
        try {
            // if get result successful
            if (result.next()) {
                String ProductName = result.getString(2);
                int year = result.getInt(3);
                double price = result.getDouble(4);
                String brand = result.getString(5);
                String category = result.getString(6);
                Product product = new Product(Integer.parseInt(ID), ProductName, year, price, brand, category);
                return product;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int AddProduct(Product product) {
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_name]\n"
                + "           ,[model_year]\n"
                + "           ,[list_price]\n"
                + "           ,[brand_name]\n"
                + "           ,[category_name])\n"
                + "     VALUES (?,?,?,?,?)";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, product.getProductName());
            prepare.setInt(2, product.getYear());
            prepare.setDouble(3, product.getPrice());
            prepare.setString(4, product.getBrandName());
            prepare.setString(5, product.getCategoryName());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public Product getProductAdd() {
        String sql = "SELECT TOP 1 * FROM [dbo].[products]\n"
                + "ORDER BY product_id DESC";
        ResultSet result = getData(sql);
        try {
            // if get data successful
            if (result.next()) {
                int ID = result.getInt(1);
                String ProductName = result.getString(2);
                int year = result.getInt(3);
                double price = result.getDouble(4);
                String BrandName = result.getString(5);
                String CategoryName = result.getString(6);
                Product product = new Product(ID, ProductName, year, price, BrandName, CategoryName);
                return product;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int UpdateProduct(Product product) {
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [product_name] = ?\n"
                + "      ,[model_year] = ?\n"
                + "      ,[list_price] = ?\n"
                + "      ,[brand_name] = ?\n"
                + "      ,[category_name] = ?\n"
                + " WHERE [product_id] = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, product.getProductName());
            prepare.setInt(2, product.getYear());
            prepare.setDouble(3, product.getPrice());
            prepare.setString(4, product.getBrandName());
            prepare.setString(5, product.getCategoryName());
            prepare.setInt(6, product.getID());
            return prepare.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

}
