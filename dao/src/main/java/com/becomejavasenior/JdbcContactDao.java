package com.becomejavasenior;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Contact DAO implementation
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class JdbcContactDao implements ContactDao {
    private JdbcDBConnUtils jdbcDBConnUtils;

    private String generalGetSql = "SELECT " +
            "  contact.contact_id, "+
            "  contact.name, " +
            "  contact.job_position, " +
            "  contact.email, " +
            "  contact.skype, " +
            "  contact.created, " +
            "  contact.updated, " +
            "  contact.is_deleted " +
            "FROM crm.contact " +
            "WHERE contact.is_deleted = FALSE ";

    public JdbcContactDao() {
        this.jdbcDBConnUtils = new JdbcDBConnUtils();
    }

    public Contact getById(int id) throws DBException {
        String sqlGetById = generalGetSql + "AND contact_id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Contact contact = new Contact();

        try {
            connection = jdbcDBConnUtils.getConnection();
            preparedStatement = jdbcDBConnUtils.getPreparedStatement(connection, sqlGetById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contact.setId(resultSet.getInt("contact_id"));
                contact.setName(resultSet.getString("name"));
                contact.setJobPosition(resultSet.getString("job_position"));
                contact.setEmail(resultSet.getString("email"));
                contact.setSkype(resultSet.getString("skype"));
                contact.setCreated(new Date((resultSet.getTimestamp("created")).getTime()));
                contact.setUpdated(new Date((resultSet.getTimestamp("updated")).getTime()));
                contact.setIsDeleted(resultSet.getBoolean("is_deleted"));
            }
        } catch (SQLException e) {
            throw new DBSystemException("Can't execute SQL = '" + sqlGetById + "'", e);
        } finally {
            jdbcDBConnUtils.closeQuietly(resultSet);
            jdbcDBConnUtils.closeQuietly(preparedStatement);
            jdbcDBConnUtils.closeQuietly(connection);
        }
        jdbcDBConnUtils.finalizeConn();

        return contact.getId() == 0 ? null : contact;
    }

    public List<Contact> getAll() throws DBException {
        String sqlGetAll = generalGetSql + " ORDER BY contact.contact_id";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Contact> contactList = new ArrayList<Contact>();

        try {
            connection = jdbcDBConnUtils.getConnection();
            preparedStatement = jdbcDBConnUtils.getPreparedStatement(connection, sqlGetAll);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Contact contact = new Contact();

                contact.setId(resultSet.getInt("contact_id"));
                contact.setName(resultSet.getString("name"));
                contact.setJobPosition(resultSet.getString("job_position"));
                contact.setEmail(resultSet.getString("email"));
                contact.setSkype(resultSet.getString("skype"));
                contact.setCreated(resultSet.getTimestamp("created"));
                contact.setUpdated(resultSet.getTimestamp("updated"));
                contact.setIsDeleted(resultSet.getBoolean("is_deleted"));

                contactList.add(contact);
            }
        } catch (SQLException e) {
            throw new DBSystemException("Can't execute SQL = '" + sqlGetAll + "'", e);
        } finally {
            jdbcDBConnUtils.closeQuietly(resultSet);
            jdbcDBConnUtils.closeQuietly(preparedStatement);
            jdbcDBConnUtils.closeQuietly(connection);
        }
        jdbcDBConnUtils.finalizeConn();

        return contactList;
    }

    public boolean delete(int id) throws DBException {
        String deleteSql = "Update crm.contact SET is_deleted = true WHERE contact_id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 2;

        try {
            connection = jdbcDBConnUtils.getConnection();
            preparedStatement = jdbcDBConnUtils.getPreparedStatement(connection, deleteSql);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBSystemException("Can't execute SQL = '" + deleteSql + "'", e);
        } finally {
            jdbcDBConnUtils.closeQuietly(preparedStatement);
            jdbcDBConnUtils.closeQuietly(connection);
        }
        jdbcDBConnUtils.finalizeConn();

        return result == 1;
    }

    public Contact create(Contact contact) throws DBException {
        if(contact.getId()!=0) throw new DaoException("Contact already exist");

        String insertSQL = "INSERT INTO crm.contact "
                + "(company_id, responsible_user_id, \"name\", job_position, email, skype, created, updated, is_deleted) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int newId = 0;

        try {
            connection = jdbcDBConnUtils.getConnection();
            preparedStatement = jdbcDBConnUtils.getPreparedStatement(connection, insertSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, contact.getCompany().getId());
            preparedStatement.setInt(2, contact.getResponsibleUser().getId());
            preparedStatement.setString(3, contact.getName());
            preparedStatement.setString(4, contact.getJobPosition());
            preparedStatement.setString(5, contact.getEmail());
            preparedStatement.setString(6, contact.getSkype());
            preparedStatement.setTimestamp(7, new Timestamp(contact.getCreated().getTime()));
            preparedStatement.setTimestamp(8, new Timestamp(contact.getUpdated().getTime()));
            preparedStatement.setBoolean(9, contact.isDeleted());

            newId = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBSystemException("Can't execute SQL = '" + insertSQL + "'", e);
        } finally {
            jdbcDBConnUtils.closeQuietly(preparedStatement);
            jdbcDBConnUtils.closeQuietly(connection);
        }
        jdbcDBConnUtils.finalizeConn();

        return newId == 0 ? null : getById(newId);
    }

    public void update(Contact contact) throws DBException {
        String updateSql = "UPDATE crm.contact SET " +
                "company_id = ?, " +
                "responsible_user_id = ?, " +
                "\"name\" = ?, " +
                "job_position = ?, " +
                "email = ?, " +
                "skype = ?, " +
                "updated = ?, " +
                "is_deleted = ? " +
                "WHERE contact_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = jdbcDBConnUtils.getConnection();
            preparedStatement = jdbcDBConnUtils.getPreparedStatement(connection, updateSql);

            preparedStatement.setInt(1, contact.getCompany().getId());
            preparedStatement.setInt(2, contact.getResponsibleUser().getId());
            preparedStatement.setString(3, contact.getName());
            preparedStatement.setString(4, contact.getJobPosition());
            preparedStatement.setString(5, contact.getEmail());
            preparedStatement.setString(6, contact.getSkype());
            preparedStatement.setTimestamp(7, new Timestamp(contact.getUpdated().getTime()));
            preparedStatement.setBoolean(8, contact.isDeleted());
            preparedStatement.setInt(9, contact.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBSystemException("Can't execute SQL = '" + updateSql + "'", e);
        } finally {
            jdbcDBConnUtils.closeQuietly(preparedStatement);
            jdbcDBConnUtils.closeQuietly(connection);
        }
        jdbcDBConnUtils.finalizeConn();
    }
}
