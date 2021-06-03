package com.bt.DAO;

import com.bt.bean.Party;
import com.bt.db.JDBCConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartyDAO {
    JDBCConfig jdbcConfig = new JDBCConfig();

    public Party retrieveDBModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String thumbnail = resultSet.getString("thumbnail_url");
        int participants = resultSet.getInt("num_of_participants");
        int maxParticipants = resultSet.getInt("max_participants");
        boolean hasFreeSpots = resultSet.getBoolean("has_free_spots");
        int organizationId = resultSet.getInt("organization_id");

        return new Party(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots, organizationId);
    }

    public List<Party> getParties() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Party> parties = new ArrayList<>();

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from party";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Party party = this.retrieveDBModel(resultSet);
                parties.add(party);
            }

            return parties;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public Party getParty(String partyId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Party party;
        int id;

        try {
            id = Integer.parseInt(partyId);

            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from party where id=?";
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                party = retrieveDBModel(resultSet);
            } else {
                throw new Exception("Could not find party with id: " + id);
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

            String SQL = "insert into party " + "(name, description, thumbnail_url, max_participants, organization_id) " + "value (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, party.getName());
            statement.setString(2, party.getDescription());
            statement.setString(3, party.getThumbnail());
            statement.setInt(4, party.getMaxParticipants());
            statement.setInt(5, party.getOrganizationId());

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

            String SQL = "update party " + "set name=?, description=?, thumbnail_url=?, max_participants=?, organization_id=? " + "where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, party.getName());
            statement.setString(2, party.getDescription());
            statement.setString(3, party.getThumbnail());
            statement.setInt(4, party.getMaxParticipants());
            statement.setInt(5, party.getOrganizationId());
            statement.setInt(6, party.getId());

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void deleteParty(String partyId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        int id;

        try {
            id = Integer.parseInt(partyId);

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
