package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {

    private final static String DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "123456";
    Connection connect;

    public ConnectDatabase() {
        try {
            Class.forName(DRIVER_CLASS_NAME);
            String database = "PRJ301_PHONE_SHOPPING";
            String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + database;
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    ResultSet getData(String sql) {
        try {
            Statement statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

}
