package com.bt.DAO;

import com.bt.bean.Party;
import com.bt.db.JDBCConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartyDAO {
    JDBCConfig jdbcConfig = new JDBCConfig();

    private Party retrievePartiesDBModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String thumbnail = resultSet.getString("thumbnail_url");
        int participants = resultSet.getInt("num_of_participants");
        int maxParticipants = resultSet.getInt("max_participants");
        boolean hasFreeSpots = resultSet.getBoolean("has_free_spots");
        float price = resultSet.getFloat("price");
        int organizationId = resultSet.getInt("organization_id");
        String organizationAbbreviation = resultSet.getString("organization_abbreviation");

        return new Party(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots, price, organizationId, organizationAbbreviation);
    }

    private Party retrievePartyDBModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String thumbnail = resultSet.getString("thumbnail_url");
        int participants = resultSet.getInt("num_of_participants");
        int maxParticipants = resultSet.getInt("max_participants");
        boolean hasFreeSpots = resultSet.getBoolean("has_free_spots");
        float price = resultSet.getFloat("price");
        int organizationId = resultSet.getInt("organization_id");
        String organizationAbbreviation = resultSet.getString("organization_abbreviation");
        int managerId = resultSet.getInt("manager_id");
        int userId = resultSet.getInt("user_id");
        int orderId = resultSet.getInt("order_id");
        int rating = resultSet.getInt("rating");
        boolean hasDiscount = resultSet.getBoolean("has_discount");

        return new Party(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots, price, organizationId, organizationAbbreviation, managerId, userId, orderId, rating, hasDiscount);
    }

    public List<Party> getParties() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Party> parties = new ArrayList<>();

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "SELECT p.*, o.abbreviation as organization_abbreviation FROM party AS p LEFT JOIN organization as o ON (p.organization_id=o.id)";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Party party = this.retrievePartiesDBModel(resultSet);
                parties.add(party);
            }

            return parties;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public Party getParty(int id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Party party;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "SELECT p.*, org.abbreviation as organization_abbreviation, org.manager_id, o.user_id, o.id as order_id, o.rating, o.has_discount " + "FROM party as p " + "LEFT JOIN organization as org ON (p.organization_id = org.id) " + "LEFT JOIN `order` as o ON (p.id = o.party_id) " + "WHERE p.id =?";
            statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                party = retrievePartyDBModel(resultSet);
            } else {
                return null;
            }

            return party;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public void createParty(Party party) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "insert into party " + "(name, description, thumbnail_url, max_participants, organization_id, price) " + "value (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, party.getName());
            statement.setString(2, party.getDescription());
            statement.setString(3, party.getThumbnail());
            statement.setInt(4, party.getMaxParticipants());
            statement.setInt(5, party.getOrganizationId());
            statement.setFloat(6, party.getPrice());

            statement.executeUpdate();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void updateParty(Party party) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "update party " + "set name=?, description=?, thumbnail_url=?, max_participants=?, price=?, organization_id=? " + "where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, party.getName());
            statement.setString(2, party.getDescription());
            statement.setString(3, party.getThumbnail());
            statement.setInt(4, party.getMaxParticipants());
            statement.setFloat(5, party.getPrice());
            statement.setInt(6, party.getOrganizationId());
            statement.setInt(7, party.getId());

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void updatePartyParticipants(int partyId, String sign) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL;

            if (sign.equals("+")) {
                SQL = "UPDATE party " + "SET num_of_participants=num_of_participants+1 " + "WHERE id=?";
            } else {
                SQL = "UPDATE party " + "SET num_of_participants=num_of_participants-1 " + "WHERE id=?";
            }

            statement = connection.prepareStatement(SQL);

            statement.setInt(1, partyId);

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void deleteParty(int id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = jdbcConfig.establishDBConnection();

            String SQL = "delete from party where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }
}
