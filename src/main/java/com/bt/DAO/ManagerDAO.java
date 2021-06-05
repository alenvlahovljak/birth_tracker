package com.bt.DAO;

import com.bt.bean.Manager;
import com.bt.db.JDBCConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {
    JDBCConfig jdbcConfig = new JDBCConfig();

    public Manager retrieveDBModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String role = resultSet.getString("role");
        String username = resultSet.getString("username");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String avatar = resultSet.getString("avatar_url");

        return new Manager(id, role, username, firstName, lastName, avatar);
    }

    public Manager retrieveAuthDBModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String role = resultSet.getString("role");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String avatar = resultSet.getString("avatar_url");

        return new Manager(id, role, username, password, firstName, lastName, avatar);
    }

    public List<Manager> getManagers() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Manager> managers = new ArrayList<>();

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from manager";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Manager manager = retrieveDBModel(resultSet);
                managers.add(manager);
            }

            return managers;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }


    public Manager getManager(String managerId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Manager manager;
        int id;

        try {
            id = Integer.parseInt(managerId);

            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from manager where id=?";
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                manager = retrieveDBModel(resultSet);
            } else {
                throw new Exception("Could not find manager with id: " + id);
            }

            return manager;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public Manager getManagerByUsername(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Manager manager;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from manager where username=?";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, username);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                manager = retrieveAuthDBModel(resultSet);
            } else {
                throw new Exception("Could not find manager with username: " + username);
            }

            return manager;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public void createManager(Manager manager) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "insert into manager " + "(username, first_name, last_name, avatar_url) " + "value (?, ?, ?, ?)";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, manager.getUsername());
            statement.setString(2, manager.getFirstName());
            statement.setString(3, manager.getLastName());
            statement.setString(4, manager.getAvatar());

            statement.executeUpdate();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void updateManager(Manager manager) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "update manager " + "set username=?, first_name=?, last_name=?, avatar_url=? " + "where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, manager.getUsername());
            statement.setString(2, manager.getFirstName());
            statement.setString(3, manager.getLastName());
            statement.setString(4, manager.getAvatar());
            statement.setInt(5, manager.getId());

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void deleteManager(String managerId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        int id;

        try {
            id = Integer.parseInt(managerId);

            connection = jdbcConfig.establishDBConnection();

            String SQL = "delete from manager where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }
}
