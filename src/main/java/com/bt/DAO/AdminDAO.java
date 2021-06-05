package com.bt.DAO;

import com.bt.bean.Admin;
import com.bt.db.JDBCConfig;

import java.sql.*;

public class AdminDAO {
    JDBCConfig jdbcConfig = new JDBCConfig();

    public Admin retrieveDBModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int role = Integer.parseInt(resultSet.getString("role"));
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");

        return new Admin(id, role, username, password);
    }

    public Admin getAdminByUsername(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Admin admin;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from `admin` where username=?";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, username);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                admin = retrieveDBModel(resultSet);
            } else {
                throw new Exception("Could not find admin with username: " + username);
            }

            return admin;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

}
