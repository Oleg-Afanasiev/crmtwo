package com.becomejavasenior.jdbc;

import com.becomejavasenior.User;
import com.becomejavasenior.UserImpl;

import java.sql.*;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class UserDAOImpl extends GenericDAO<User> implements UserDAO {

    final String saveNewUser =  "INSERT INTO crm.\"user\" (role_id, username, last_name, first_name, email, created, updated, is_deleted)\n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING user_id;";

    final String updateUser =   "UPDATE crm.\"user\"\n" +
                                "SET (role_id, username, last_name, first_name, email, created, updated, is_deleted) =\n" +
                                "(?, ?, ?, ?, ?, ?, ?, ?)\n" +
                                "WHERE user_id = ? ;";

    final String getUserById =  "SELECT\n" +
                                "  u.user_id,\n" +
                                "  u.role_id,\n" +
                                "  u.username,\n" +
                                "  u.last_name,\n" +
                                "  u.first_name,\n" +
                                "  u.email,\n" +
                                "  u.created,\n" +
                                "  u.updated,\n" +
                                "  u.is_deleted\n" +
                                "FROM crm.\"user\" u\n" +
                                "WHERE u.user_id = ? " +
                                "and u.is_deleted = false";

    final String deleteUser =   "DELETE FROM crm.\"user\"\n" +
                                "WHERE user_id = ? ;";


    public UserDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    protected void saveRelations(User entity) throws SQLException {
        /*NOP*/
    }

    @Override
    protected Map<String, String> getConfig() {
        return null;
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id){
       return id == null ? this.saveNewUser : this.updateUser;
    }
    @Override
    protected String getQueryForGetById(){
        return getUserById;
    }
    @Override
    protected String getQueryForDelete(){
        return this.deleteUser;
    }


    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, User entity) throws SQLException {
        Long id = entity.getId();
        statement.setLong(1, entity.getRole_id());
        statement.setString(2, entity.getUserName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getFirstName());
        statement.setString(5, entity.getEmail());
        statement.setTimestamp(6, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(7, new Timestamp(entity.getUpdated().getTime()));
        statement.setBoolean(8, entity.isDeleted());
        if(id != null){
            statement.setLong(9, entity.getId());
        }
    }

    @Override
    protected User mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        User user = new UserImpl();

        super.setPrivateField(user, "id", resultSet.getLong("user_id"));
        user.setRole_id(resultSet.getInt("role_id"));
        user.setUserName(resultSet.getString("username"));
        user.setLastName(resultSet.getString("last_name"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setEmail(resultSet.getString("email"));
        user.setCreated(resultSet.getDate("created"));
        user.setUpdated(resultSet.getDate("updated"));
        user.setIsDeleted(resultSet.getBoolean("is_deleted"));

        return user;
    }
}
