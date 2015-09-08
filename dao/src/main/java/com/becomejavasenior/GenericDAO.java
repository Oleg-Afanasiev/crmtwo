package com.becomejavasenior;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
abstract public class GenericDAO<T extends Identity> implements AbstractDAO<T> {
    protected Connection connection;
    Boolean hasResultSet = false; //todo how can I fix that? suppose that we use one instance of DAO per thread


    public void saveOrUpdate(T entity) {
        Long id = entity.getId();

        String query = getQueryForSaveOrUpdate(id);

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = executePreparedStatementForUpdate(statement, entity)) {

            if (this.hasResultSet && rs != null && rs.next()) {
                id = rs.getLong(1);
                setPrivateField(entity, "id", id);
            } else {
                int updatedRowsCount = statement.getUpdateCount();
                if(updatedRowsCount < 1){
                    throw new DAOException("Entity wasn't updated");
                }
            }

        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new DAOException("Can't save or update entity", e);
        }
    }

    public T getById(long id) {
        T entity = null;

        try (PreparedStatement statement = connection.prepareStatement(getQueryForGetById());
             ResultSet rs = executePreparedStatementForGetById(statement, id)) {

            if(!rs.next()){
                throw new DAOException("Entity with specified ID wasn't found. Id = " + id);
            }
            entity = mapFieldsForGetById(rs);

        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new DAOException("Can't find entity", e);
        }
        return entity;
    }

    public void delete(T entity) { //todo what I have to do with object after deleting? clear ID?

        if (entity.getId() != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(getQueryForDelete());
                statement.setLong(1, entity.getId());
                statement.execute();
            } catch (SQLException e) {
                throw new DAOException("Can't delete user", e);
            }
        } else {
            throw new DAOException("Can't delete unsaved user (id is NULL)");
        }
    }

    public void deleteAll(Collection<? extends T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    public void setPrivateField(T entity, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        List<Field> fields = new ArrayList<Field>();
        Class clazz = entity.getClass();
        while (!clazz.equals(Object.class)) {
            fields = asList(clazz.getDeclaredFields());
            for (Field f : fields) {
                if (f.getName().equals(fieldName)) {
                    f.setAccessible(true);
                    f.set(entity, value);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private ResultSet executePreparedStatementForUpdate(PreparedStatement statement, T entity) throws SQLException {
        setParamsForSaveOrUpdate(statement, entity);

        this.hasResultSet = statement.execute();

        return statement.getResultSet();
    }

    private ResultSet executePreparedStatementForGetById(PreparedStatement statement, Long id) throws SQLException {
        statement.setLong(1, id);
        statement.execute();
        return statement.getResultSet();
    }

    protected Collection<Long> getRelatedIds(String methodName, Identity instance) throws SQLException {
        ArrayList<Long> result = new ArrayList<>();
        String query = getConfig().get(methodName);
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, instance.getId());

        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()){
            result.add(rs.getLong(1));
        }
        return result;
    }

    abstract protected Map<String, String> getConfig();

    abstract protected String getQueryForSaveOrUpdate(Long id);

    abstract protected String getQueryForGetById();

    abstract protected String getQueryForDelete();

    abstract protected void setParamsForSaveOrUpdate(PreparedStatement statement, T entity) throws SQLException;

    abstract protected T mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException;

}
