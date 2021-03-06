package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.UserImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.*;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class UserDAOImpl extends GenericDAO<User> implements UserDAO {

    private static final Map<String, String> methodToQueryMap;

    static {
        Map<String, String> tempMethodToQueryMap = new HashMap<>();
        tempMethodToQueryMap.put("getRole", "SELECT role_id FROM crm.user WHERE user_id  = ? ;");
        methodToQueryMap = Collections.unmodifiableMap(tempMethodToQueryMap);
    }

    private static final String saveNewUser =   "INSERT INTO crm.user (role_id, username, last_name, first_name, email, created, updated, password) " +
                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING user_id;";

    private static final String updateUser =    "UPDATE crm.user " +
                                                "SET (role_id, username, last_name, first_name, email, created, updated, password) = " +
                                                "(?, ?, ?, ?, ?, ?, ?, ?) " +
                                                "WHERE user_id = ? ;";

    private static final String getUserById =   "SELECT user_id, role_id, username, last_name, first_name, email, created, updated, password " +
                                                "FROM crm.user " +
                                                "WHERE user_id = ? " +
                                                "and is_deleted = FALSE";

    private static final String deleteUser =   "UPDATE crm.user SET (is_deleted) = (TRUE) WHERE user_id = ? ;";

    private static final String queryForGetRange = "SELECT * FROM crm.user WHERE is_deleted = FALSE ORDER BY user_id LIMIT ? offset ? ;";


    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(User entity) throws SQLException {
        /*NOP*/
    }

    @Override
    protected String getQueryForGetRange() {
        return queryForGetRange;
    }

    @Override
    protected Map<String, String> getMethodToQueryMap() {
        return methodToQueryMap;
    }

    @Override
    protected String getQueryForInsertOrUpdate(Long id) {
        return id == null ? this.saveNewUser : this.updateUser;
    }

    @Override
    protected String getQueryForGetById() {
        return getUserById;
    }

    @Override
    protected String getQueryForDelete() {
        return this.deleteUser;
    }


    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, User entity) throws SQLException {
        Long id = entity.getId();
        statement.setLong(1, entity.getRole().getId());
        statement.setString(2, entity.getUserName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getFirstName());
        statement.setString(5, entity.getEmail());
        statement.setTimestamp(6, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(7, new Timestamp(entity.getUpdated().getTime()));
        statement.setString(8, entity.getPassword());
        if (id != null) {
            statement.setLong(9, entity.getId());
        }
    }

    @Override
    protected User mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final User user = new UserImpl();

        super.setPrivateField(user, "id", resultSet.getLong("user_id"));
        user.setUserName(resultSet.getString("username"));
        user.setLastName(resultSet.getString("last_name"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setEmail(resultSet.getString("email"));
        user.setCreated(resultSet.getDate("created"));
        user.setUpdated(resultSet.getDate("updated"));
        user.setPassword(resultSet.getString("password"));

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return improveMethods(method, user, args);
            }
        };
        User proxy =
                (User) Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{User.class}, handler);
        return proxy;
    }

    private <T extends Identity> Object improveMethods(Method method, T instance, Object[] args)
            throws InvocationTargetException, IllegalAccessException, SQLException, NoSuchFieldException {
        Object result = method.invoke(instance, args);
        if (result != null) {
            return result;
        }
        String methodName = method.getName();

        result =
                CommandMethod.valueOf(methodName)
                        .init()
                        .setRelatedIDs(getRelatedIds(methodName, instance))
                        .execute(method, instance, args);

        return result;
    }


}
