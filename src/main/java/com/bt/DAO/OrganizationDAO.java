package com.bt.DAO;

import com.bt.bean.Organization;
import com.bt.db.JDBCConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDAO {
    JDBCConfig jdbcConfig = new JDBCConfig();

    public Organization retrieveDBModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String abbreviation = resultSet.getString("abbreviation");
        String description = resultSet.getString("description");
        String thumbnail = resultSet.getString("thumbnail_url");
        float rating = resultSet.getInt("rating");
        int managerId = resultSet.getInt("manager_id");

        return new Organization(id, name, abbreviation, description, thumbnail, rating, managerId);
    }

    public List<Organization> getOrganizations() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Organization> organizations = new ArrayList<>();

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from organization";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Organization organization = this.retrieveDBModel(resultSet);
                organizations.add(organization);
            }

            return organizations;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public Organization getOrganization(String organizationId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Organization organization;
        int id;

        try {
            id = Integer.parseInt(organizationId);

            connection = jdbcConfig.establishDBConnection();

            String SQL = "select * from organization where id=?";
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                organization = retrieveDBModel(resultSet);
            } else {
                throw new Exception("Could not find organization with id: " + id);
            }

            return organization;
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, resultSet);
        }
    }

    public void createOrganization(Organization organization) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "insert into organization " + "(name, abbreviation, description, thumbnail_url, rating) " + "value (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, organization.getName());
            statement.setString(2, organization.getAbbreviation());
            statement.setString(3, organization.getDescription());
            statement.setString(4, organization.getThumbnail());
            statement.setFloat(5, organization.getRating());

            statement.executeUpdate();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void updateOrganization(Organization organization) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConfig.establishDBConnection();

            String SQL = "update organization " + "set name=?, abbreviation=?, description=?, thumbnail_url=?, rating=? " + "where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setString(1, organization.getName());
            statement.setString(2, organization.getAbbreviation());
            statement.setString(3, organization.getDescription());
            statement.setString(4, organization.getThumbnail());
            statement.setFloat(5, organization.getRating());
            statement.setInt(6, organization.getId());

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }

    public void deleteOrganization(String organizationId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        int id;

        try {
            id = Integer.parseInt(organizationId);

            connection = jdbcConfig.establishDBConnection();

            String SQL = "delete from organization where id=?";
            statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            statement.execute();
        } finally {
            jdbcConfig.closeDBConnection(connection, statement, null);
        }
    }
}
