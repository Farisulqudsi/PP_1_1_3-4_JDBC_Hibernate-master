package jm.task.core.jdbc.util;

import java.sql.*;
public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/user";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection.setAutoCommit(false);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
