package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "230101";

    //jdbc:mysql://localhost:3306/user?serverTimezone=UTC

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed...");
        }
        return connection;
    }


}
