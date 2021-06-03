package com.bt.db;

import java.sql.*;


public class JDBCConfig {
    private String URL = "jdbc:mysql://127.0.0.1:3306/birthday_celebration_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
    private String username = "alen";
    private String password = "alen12345678";

    public Connection establishDBConnection() throws SQLException {
        // load driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("JAVA: Class.forName() error");
            e.printStackTrace();
        }

        // return established connection
        return DriverManager.getConnection(URL, username, password);
    }

    public void closeDBConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
