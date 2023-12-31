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
            //call driver
            Class.forName(DRIVER_CLASS_NAME);
            // connect to database
            String database = "SE1605";
            String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + database;
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    ResultSet getData(String sql) {
        try {
            Statement statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = statement.executeQuery(sql);
            return result;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

}
