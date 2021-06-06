package com.bt.DAO;

import com.bt.bean.Order;
import com.bt.db.JDBCConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    JDBCConfig jdbcConfig = new JDBCConfig();

    public Order retrieveDBModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int partyId = resultSet.getInt("party_id");
        int rating = resultSet.getInt("rating");
        boolean hasDiscount = resultSet.getBoolean("has_discount");

        return new Order(id, userId, partyId, rating, hasDiscount);
    }

    public List<Order> getOrders() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Order> orders = new ArrayList<>();

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from `order`";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Order order = retrieveDBModel(resultSet);
                orders.add(order);
            }

            return orders;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public float getUserDiscount(String userId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        float discount;
        int id;

        try {
            id = Integer.parseInt(userId);

            connection = jdbcConfig.establishDBConnection();

            String SQL = "SELECT COUNT(user_id) * 2.8 as discount " + "FROM `order` " + "WHERE user_id=?";
            statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                discount = resultSet.getFloat("discount");
            } else {
                throw new Exception("Could not find order with user_id: " + id);
            }

            return discount;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public Order getOrder(String orderId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Order order;
        int id;

        try {
            id = Integer.parseInt(orderId);

            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from `order` where id=?";
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                order = retrieveDBModel(resultSet);
            } else {
                throw new Exception("Could not find order with id: " + id);
            }

            return order;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public void createOrder(Order order) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "insert into `order` " + "(user_id, party_id) " + "value (?, ?)";
            statement = connection.prepareStatement(SQL);

            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getPartyId());

            statement.executeUpdate();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void updateOrderRating(Order order) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "update `order` " + "set rating=? " + "where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setFloat(1, order.getRating());
            statement.setInt(2, order.getId());

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void updateOrderDiscount(Order order) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "update `order` " + "set has_discount=? " + "where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setBoolean(1, order.isHasDiscount());
            statement.setInt(2, order.getId());

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void deleteOrder(int id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "delete from `order` where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }
}
