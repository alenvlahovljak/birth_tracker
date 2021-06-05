package com.bt.DAO;

import com.bt.bean.User;
import com.bt.db.JDBCConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    JDBCConfig jdbcConfig = new JDBCConfig();

    public User retrieveDBModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String role = resultSet.getString("role");
        String username = resultSet.getString("username");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String avatar = resultSet.getString("avatar_url");

        return new User(id, role, username, firstName, lastName, avatar);
    }

    public List<User> getUsers() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<User> users = new ArrayList<>();

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from user";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = retrieveDBModel(resultSet);
                users.add(user);
            }

            return users;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }


    public User getUser(String userId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        User user;
        int id;

        try {
            id = Integer.parseInt(userId);

            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from user where id=?";
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = retrieveDBModel(resultSet);
            } else {
                throw new Exception("Could not find user with id: " + id);
            }

            return user;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public User getUserByUsername(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        User user;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from `user` where username=?";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, username);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = retrieveDBModel(resultSet);
            } else {
                throw new Exception("Could not find user with username: " + username);
            }

            return user;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public void createUser(User user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "insert into user " + "(username, first_name, last_name, avatar_url) " + "value (?, ?, ?, ?)";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getAvatar());

            statement.executeUpdate();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void updateUser(User user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "update user " + "set username=?, first_name=?, last_name=?, avatar_url=? " + "where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getAvatar());
            statement.setInt(5, user.getId());

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void deleteUser(String userId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        int id;

        try {
            id = Integer.parseInt(userId);

            connection = jdbcConfig.establishDBConnection();

            String SQL = "delete from user where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }
}
