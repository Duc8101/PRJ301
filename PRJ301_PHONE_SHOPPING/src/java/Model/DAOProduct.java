package Model;

import Const.ConstValue;
import Entity.Product;
import Entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DAOProduct extends ConnectDatabase {

    private List<Product> getList(String sql) {
        List<Product> list = new ArrayList<>();
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

    public int getNumberPage(int CategoryID) {
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
        List<Product> list = this.getList(sql);
        return list;
    }
}
