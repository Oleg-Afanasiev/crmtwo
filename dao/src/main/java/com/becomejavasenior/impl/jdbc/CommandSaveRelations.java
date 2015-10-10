package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Dmytro Tsapko on 9/17/2015.
 */
public enum CommandSaveRelations {

    Phone(Phone.class, Phone.class.getSimpleName()),
    Deal(Deal.class, Deal.class.getSimpleName()),
    Tag(Tag.class, Tag.class.getSimpleName()),
    File(File.class, File.class.getSimpleName()),
    Comment(Comment.class, Comment.class.getSimpleName()),
    User(User.class, User.class.getSimpleName()),
    DealStatus(DealStatus.class, DealStatus.class.getSimpleName()),
    Company(Company.class, Company.class.getSimpleName()),
    Contact(Contact.class, Contact.class.getSimpleName()),
    Role(Role.class, Role.class.getSimpleName()),
    TaskType(TaskType.class, TaskType.class.getSimpleName()),
    TaskPeriod(TaskPeriod.class, TaskPeriod.class.getSimpleName());

    private static final Map<String, String> columnToMethod;
    private static final Map<Class, Map<String, String>> entityRelations;

    static {
        Map<Class, Map<String, String>> tempClassToTables = new HashMap<>();
        Map<String, String> tempEntityRelations = new HashMap<>();
        tempClassToTables.put(Phone.class, Collections.EMPTY_MAP);
        tempClassToTables.put(Tag.class, Collections.EMPTY_MAP);
        tempClassToTables.put(File.class, Collections.EMPTY_MAP);
        tempClassToTables.put(Comment.class, Collections.EMPTY_MAP);
        tempClassToTables.put(User.class, Collections.EMPTY_MAP);
        tempClassToTables.put(DealStatus.class, Collections.EMPTY_MAP);
        tempClassToTables.put(Role.class, Collections.EMPTY_MAP);
        tempClassToTables.put(TaskType.class, Collections.EMPTY_MAP);
        tempClassToTables.put(TaskPeriod.class, Collections.EMPTY_MAP);

        tempEntityRelations.put("company_comment", "comment_id");
        tempEntityRelations.put("company_tag", "tag_id");
        tempEntityRelations.put("company_file", "file_id");
        tempEntityRelations.put("company_phone", "phone_number_id");
        tempClassToTables.put(Company.class, tempEntityRelations);
        tempEntityRelations = new HashMap<>();

        tempEntityRelations.put("contact_comment", "comment_id");
        tempEntityRelations.put("contact_tag", "tag_id");
        tempEntityRelations.put("contact_file", "file_id");
        tempEntityRelations.put("contact_phone", "phone_number_id");
        tempClassToTables.put(Contact.class, tempEntityRelations);
        tempEntityRelations = new HashMap<>();

        tempEntityRelations.put("deal_comment", "comment_id");
        tempEntityRelations.put("deal_tag", "tag_id");
        tempEntityRelations.put("deal_file", "file_id");
        tempEntityRelations.put("deal_company", "company_id");
        tempEntityRelations.put("deal_contact", "contact_id");
        tempClassToTables.put(Deal.class, tempEntityRelations);

        entityRelations = Collections.unmodifiableMap(tempClassToTables);
    }

    static {
        Map<String, String> tempColumnToMethod = new HashMap<>();
        tempColumnToMethod.put("comment_id", "getComments");
        tempColumnToMethod.put("tag_id", "getTags");
        tempColumnToMethod.put("file_id", "getFiles");
        tempColumnToMethod.put("phone_number_id", "getPhones");
        tempColumnToMethod.put("deal_id", "getDeals");
        tempColumnToMethod.put("company_id", "getCompanies");
        tempColumnToMethod.put("contact_id", "getContacts");
        columnToMethod = Collections.unmodifiableMap(tempColumnToMethod);
    }


    private Class entityClass;

    private String getRelatedColumnName(Object entity) {

        if(entity instanceof Company) return "company_id";
        if(entity instanceof Contact) return "contact_id";
        if(entity instanceof Deal) return "deal_id";
        return null;
    }

    private Map<String, String> getEntityRelations(Class clazz) {
        return entityRelations.get(clazz);
    }

    private String getMethodByColumn(String columnName) {
        return columnToMethod.get(columnName);
    }

    CommandSaveRelations(Class clazz, String name) {
        this.entityClass = clazz;
    }

    private List<Long> getRelatedIDs(Object entity, String columnName) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        System.out.println(entity + "  " + columnName);
        List<Long> result = new ArrayList<>();
        Collection<Identity> relatedEntities = null;
        String methodName = getMethodByColumn(columnName);
        System.out.println(methodName);
        relatedEntities = (Collection<Identity>) getRelatedEntities(entity, methodName);
        if (relatedEntities != null) {
            for (Identity relEntity : relatedEntities) {
                DaoManager.getInstance().getDaoByObject(relEntity).insertOrUpdate(relEntity);
                result.add(relEntity.getId());
            }
        }

        return result;
    }

    private void writeRelations(String table, String relatedColumn, Long entityId, String entityColumn, List<Long> relatedIDs) {
        String insertQuery = "INSERT INTO crm." + table + "( " + entityColumn + ", " + relatedColumn + " ) VALUES ";
        StringBuilder builder = new StringBuilder(insertQuery);
        for (Iterator<Long> iterator = relatedIDs.iterator(); iterator.hasNext(); ) {
            builder.append("( ");
            builder.append(entityId);
            builder.append(", ");
            Long id = iterator.next();
            builder.append(id);
            builder.append(" )");
            if (iterator.hasNext()) builder.append(", ");
        }
        builder.append(" ;");
        System.out.println(builder.toString());
        Connection connection = DaoManager.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(builder.toString());
        } catch (SQLException e) {
            throw new DAOException("Can't update relations with " + relatedColumn, e);
        }


    }

    private void clearRelations(String table, String column, Long id, String entityColumn) {


        String clearQuery = "DELETE FROM crm." + table + " WHERE " + entityColumn + " = " + id;
        Connection connection = DaoManager.getInstance().getConnection();
        System.out.println(clearQuery);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with " + column, e);
        }
    }

    private Object getRelatedEntities(Object entity, String methodName) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Object result;

        Method[] methods = entity.getClass().getMethods();
        Method method = null;
        for (Method m : methods){
            if (m.getName().equals(methodName)){
                method = m;
            }
        }
        result = method.invoke(entity, null);
        return result;
    }

    public void execute(Identity entity) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {

        Map<String, String> relatedTables = getEntityRelations(this.entityClass);

        for (String table : relatedTables.keySet()) {

            String entityColumn = getRelatedColumnName(entity);
            Long id = entity.getId();

            String column = relatedTables.get(table);
            List<Long> relatedIDs = getRelatedIDs(entity, column);
            clearRelations(table, column, id, entityColumn);

            if (!relatedIDs.isEmpty()) {
                writeRelations(table, column, id, entityColumn, relatedIDs);
            }
        }
    }
}
