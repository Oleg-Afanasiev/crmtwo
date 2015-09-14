package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Arrays.asList;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
abstract public class GenericDAO<T extends Identity> implements AbstractDAO<T> {
    protected Connection connection;

//    private static final Map<String, Class> methodToEntity = new HashMap<>();
//    static {
//        methodToEntity.put("getPhones", Phone.class);
//        methodToEntity.put("getDeals", Deal.class);
//        methodToEntity.put("getTags", Tag.class);
//        methodToEntity.put("getFiles", File.class);
//        methodToEntity.put("getComments", Comment.class);
//        methodToEntity.put("getResponsibleUser", User.class);
//        methodToEntity.put("getDealStatus", DealStatus.class);
//        methodToEntity.put("getCompanies", Company.class);
//        methodToEntity.put("getContacts", Contact.class);
//        methodToEntity.put("getCompany", Company.class);
//        methodToEntity.put("getRole", Role.class);
//        methodToEntity.put("getDeal", Deal.class);
//        methodToEntity.put("getContact", Contact.class);
//        methodToEntity.put("getTaskType", TaskType.class);
//        methodToEntity.put("getTaskPeriod", TaskPeriod.class);
//    }

    Boolean hasResultSet = false; //todo how can I fix that? suppose that we use one instance of DAO per thread

    public void insertOrUpdate(T entity) {

        Long id = entity.getId();

        String query = getQueryForInsertOrUpdate(id);

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = executePreparedStatementForUpdate(statement, entity)) {

            if (this.hasResultSet && rs != null && rs.next()) {
                id = rs.getLong(1);
                setPrivateField(entity, "id", id);
            } else {
                int updatedRowsCount = statement.getUpdateCount();
                if (updatedRowsCount < 1) {
                    throw new DAOException("Entity wasn't updated");
                }
            }
            saveRelations(entity);
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new DAOException("Can't save or update entity", e);
        }
    }


    protected abstract void saveRelations(T entity) throws SQLException;

    public T getById(long id) {
        T entity = null;

        try (PreparedStatement statement = connection.prepareStatement(getQueryForGetById());
             ResultSet rs = executePreparedStatementForGetById(statement, id)) {

            if (!rs.next()) {
                throw new DAOException("Entity with specified ID wasn't found. Id = " + id);
            }
            entity = mapFieldsFromResultSet(rs);

        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new DAOException("Can't find entity", e);
        }
        return entity;
    }

    public void delete(T entity) { //todo what I have to do with object after deleting? clear ID?

        if (entity.getId() != null) {
            try (PreparedStatement statement = connection.prepareStatement(getQueryForDelete())) {
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

    @Override
    public Collection<T> getRange(long from, long size) {
        if(from < 0 || size < 1){
           throw new IllegalArgumentException("Please put positive values of arguments");
        }

        List<T> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(getQueryForGetRange());
             ResultSet rs = executePreparedStatementForGetRange(statement, from, size)) {


            while (rs.next()) {
                result.add(mapFieldsFromResultSet(rs));
            }

        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new DAOException("Can't find entity", e);
        }
        return result;
    }

    private ResultSet executePreparedStatementForGetRange(PreparedStatement statement, long from, long size) throws SQLException {
        statement.setLong(1, size);
        statement.setLong(2, from);
        statement.execute();
        return statement.getResultSet();
    }

    /**
     *
     * @param entity any object
     * @param fieldName String with name of private field
     * @param value value with will be set to field
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * This method collects all private fields from entity  class, his ancestors till Object class,
     * takes field with defined name and sets defined value.
     */
    protected void setPrivateField(T entity, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
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
        String query = getMethodToQueryMap().get(methodName);

        if (query == null) throw new DAOException("Can't find query to find related entities");

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, instance.getId());

        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            Long id = rs.getLong(1);
            if (!rs.wasNull()) {
                result.add(id);
            }
        }
        return result;
    }

//    protected void methodEngine(String methodName, T instance) throws SQLException {
//        final Map<String, String> methodToQueryMap = getMethodToQueryMap();
//        Collection<Long> IDs = getRelatedIds(methodName, instance);
//
//
//
//
//
//    }

    protected abstract String getQueryForGetRange();

    abstract protected Map<String, String> getMethodToQueryMap();

    abstract protected String getQueryForInsertOrUpdate(Long id);

    abstract protected String getQueryForGetById();

    abstract protected String getQueryForDelete();

    abstract protected void setParamsForSaveOrUpdate(PreparedStatement statement, T entity) throws SQLException;

    abstract protected T mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException;

}
