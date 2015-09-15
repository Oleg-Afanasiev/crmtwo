package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;
import com.becomejavasenior.Deal;
import com.becomejavasenior.impl.ContactImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.*;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class ContactDAOImpl extends GenericDAO<Contact> implements ContactDAO {

    private static final Map<String, String> methodToQueryMap;

    static {
        Map<String, String> tempMethodToQueryMap = new HashMap<>();
        tempMethodToQueryMap.put("getResponsibleUser", "SELECT responsible_user_id FROM crm.contact WHERE contact_id  = ?");
        tempMethodToQueryMap.put("getPhones", "SELECT phone_number_id FROM crm.contact_phone WHERE contact_id  = ?");
        tempMethodToQueryMap.put("getCompany", "SELECT company_id FROM crm.contact WHERE contact_id  = ?");
        tempMethodToQueryMap.put("getFiles", "SELECT file_id FROM crm.contact_file WHERE contact_id  = ?");
        tempMethodToQueryMap.put("getComments", "SELECT comment_id FROM crm.contact_comment WHERE contact_id  = ?");
        tempMethodToQueryMap.put("getTags", "SELECT tag_id FROM crm.contact_tag WHERE contact_id  = ?");
        tempMethodToQueryMap.put("getDeals", "SELECT deal_id FROM crm.deal_contact WHERE contact_id  = ?");
        methodToQueryMap = Collections.unmodifiableMap(tempMethodToQueryMap);
    }

    private static final String saveNewContact = "INSERT INTO crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING contact_id;";

    private static final String updateContact = "UPDATE crm.contact SET (company_id, responsible_user_id, name, job_position, email, skype, created, updated) =" +
            "(?, ?, ?, ?, ?, ?, ?, ?)\n" +
            "WHERE contact_id = ?;";

    private static final String getContactById = "SELECT contact_id, company_id, responsible_user_id, name, job_position, email, skype, created, updated " +
            "FROM crm.contact\n" +
            "WHERE contact.contact_id = ?\n" +
            "AND contact.is_deleted = FALSE;";

    private static final String deleteContact = "UPDATE crm.contact SET (is_deleted) =\n" +
            "(TRUE)\n" +
            "WHERE contact_id = ?;";

    private static final String queryForGetRange = "SELECT * FROM crm.contact WHERE is_deleted = FALSE ORDER BY contact_id LIMIT ? offset ? ;";

    public ContactDAOImpl(Connection connection) {
        this.connection = connection;
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
        return id == null ? saveNewContact : updateContact;
    }

    @Override
    protected String getQueryForGetById() {
        return getContactById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteContact;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Contact entity) throws SQLException {
        Long id = entity.getId();
        setLongOrNull(1, statement, entity.getCompany());
        statement.setLong(2, entity.getResponsibleUser().getId());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getJobPosition());
        statement.setString(5, entity.getEmail());
        statement.setString(6, entity.getSkype());
        statement.setTimestamp(7, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(8, new Timestamp(entity.getUpdated().getTime()));
        if (id != null) {
            statement.setLong(9, id);
        }
    }

    @Override
    protected Contact mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final Contact contact = new ContactImpl();
        super.setPrivateField(contact, "id", resultSet.getLong("contact_id"));
        contact.setName(resultSet.getString("name"));
        contact.setJobPosition(resultSet.getString("job_position"));
        contact.setEmail(resultSet.getString("email"));
        contact.setSkype(resultSet.getString("skype"));
        contact.setCreated(resultSet.getTimestamp("created"));
        contact.setUpdated(resultSet.getTimestamp("updated"));

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return loadEntities(method, contact, args);
            }
        };
        Contact proxy =
                (Contact) Proxy.newProxyInstance(Contact.class.getClassLoader(), new Class[]{Contact.class}, handler);
        return proxy;
    }

    private <T extends Identity> Object loadEntities(Method method, T instance, Object[] args)
            throws InvocationTargetException, IllegalAccessException, SQLException {
        Object result = method.invoke(instance, args);
        if (result != null) {
            return result;
        }
        String methodName = method.getName();

        switch (methodName) {
            case "getPhones": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<Phone> set = new HashSet<>();
                    PhoneDAO pd = DaoManager.getInstance().getPhoneDAO();
                    for (Long id : ids) {
                        set.add(pd.getById(id));
                    }
                    result = set;
                    ((Contact) instance).setPhones((Set<Phone>) result);
                }
            }
            break;
            case "getDeals": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<Deal> set = new HashSet<>();
                    DealDAO dd = DaoManager.getInstance().getDealDAO();
                    for (Long id : ids) {
                        set.add(dd.getById(id));
                    }
                    result = set;
                    ((Contact) instance).setDeals((Set<Deal>) result);
                }
            }
            break;
            case "getTags": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<Tag> set = new HashSet<Tag>();
                    TagDAO td = DaoManager.getInstance().getTagDAO();
                    for (Long id : ids) {
                        set.add(td.getById(id));
                    }
                    result = set;
                    ((Contact) instance).setTags((Set<Tag>) result);
                }
            }
            break;
            case "getFiles": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<File> set = new HashSet<File>();
                    FileDAO fd = DaoManager.getInstance().getFileDAO();
                    for (Long id : ids) {
                        set.add(fd.getById(id));
                    }
                    result = set;
                    ((Contact) instance).setFiles((Set<File>) result);
                }
            }
            break;
            case "getComments": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<Comment> set = new HashSet<Comment>();
                    CommentDAO cd = DaoManager.getInstance().getCommentDAO();
                    for (Long id : ids) {
                        set.add(cd.getById(id));
                    }
                    result = set;
                    ((Contact) instance).setComments((Set<Comment>) result);
                }
            }
            break;
            case "getResponsibleUser": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    result = DaoManager.getInstance().getUserDAO().getById(ids.iterator().next());
                    ((Contact) instance).setResponsibleUser((User) result);
                }
            }
            break;
            case "getCompany": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    result = DaoManager.getInstance().getCompanyDAO().getById(ids.iterator().next());
                    ((Contact) instance).setCompany((Company) result);
                }
            }
            break;
            default:
                result = method.invoke(instance, args);
                break;
        }
        return result;
    }

    @Override
    protected void saveRelations(Contact entity) throws SQLException {
        Set<Comment> comments = entity.getComments();
        if (comments != null && !comments.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (Comment comment : comments) {
                DaoManager.getInstance().getCommentDAO().insertOrUpdate(comment);
                ids.add(comment.getId());
            }
            clearRelationsWithComments(entity);
            writeRelationsWithComments(entity, ids);
        }

        Set<Tag> tags = entity.getTags();
        if (tags != null && !tags.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (Tag tag : tags) {
                DaoManager.getInstance().getTagDAO().insertOrUpdate(tag);
                ids.add(tag.getId());
            }
            clearRelationsWithTags(entity);
            writeRelationsWithTags(entity, ids);
        }

        Set<File> files = entity.getFiles();
        if (files != null && !files.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (File file : files) {
                DaoManager.getInstance().getFileDAO().insertOrUpdate(file);
                ids.add(file.getId());
            }
            clearRelationsWithFiles(entity);
            writeRelationsWithFiles(entity, ids);
        }

        Set<Deal> deals = entity.getDeals();
        if (deals != null && !deals.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (Deal deal : deals) {
                DaoManager.getInstance().getDealDAO().insertOrUpdate(deal);
                ids.add(deal.getId());
            }
            clearRelationsWithDeals(entity);
            writeRelationsWithDeals(entity, ids);
        }

        Set<Phone> phones = entity.getPhones();
        if (phones != null && !phones.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (Phone phone : phones) {
                DaoManager.getInstance().getPhoneDAO().insertOrUpdate(phone);
                ids.add(phone.getId());
            }
            clearRelationsWithPhones(entity);
            writeRelationsWithPhones(entity, ids);
        }


    }

    private void clearRelationsWithComments(Contact entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.contact_comment WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with comments", e);
        }
    }

    private void writeRelationsWithComments(Contact entity, Set<Long> comments) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.contact_comment (contact_id, comment_id) VALUES ";
        StringBuilder builder = new StringBuilder(insertQuery);
        for (Iterator<Long> iterator = comments.iterator(); iterator.hasNext(); ) {
            builder.append("( ");
            builder.append(entityId);
            builder.append(", ");
            Long id = iterator.next();
            builder.append(id);
            builder.append(" )");
            if (iterator.hasNext()) builder.append(", ");
        }
        builder.append(" ;");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(builder.toString());
        } catch (SQLException e) {
            throw new DAOException("Can't update relations with comments", e);
        }
    }

    private void clearRelationsWithTags(Contact entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.contact_tag WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with tags", e);
        }
    }

    private void writeRelationsWithTags(Contact entity, Set<Long> tags) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.contact_tag (contact_id, tag_id) VALUES ";
        StringBuilder builder = new StringBuilder(insertQuery);
        for (Iterator<Long> iterator = tags.iterator(); iterator.hasNext(); ) {
            builder.append("( ");
            builder.append(entityId);
            builder.append(", ");
            Long id = iterator.next();
            builder.append(id);
            builder.append(" )");
            if (iterator.hasNext()) builder.append(", ");
        }
        builder.append(" ;");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(builder.toString());
        } catch (SQLException e) {
            throw new DAOException("Can't update relations with tags", e);
        }
    }

    private void clearRelationsWithFiles(Contact entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.contact_file WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with files", e);
        }
    }

    private void writeRelationsWithFiles(Contact entity, Set<Long> files) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.contact_file (contact_id, file_id) VALUES ";
        StringBuilder builder = new StringBuilder(insertQuery);
        for (Iterator<Long> iterator = files.iterator(); iterator.hasNext(); ) {
            builder.append("( ");
            builder.append(entityId);
            builder.append(", ");
            Long id = iterator.next();
            builder.append(id);
            builder.append(" )");
            if (iterator.hasNext()) builder.append(", ");
        }
        builder.append(" ;");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(builder.toString());
        } catch (SQLException e) {
            throw new DAOException("Can't update relations with files", e);
        }
    }

    private void clearRelationsWithDeals(Contact entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.deal_contact WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with deals", e);
        }
    }

    private void writeRelationsWithDeals(Contact entity, Set<Long> deals) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.deal_contact (contact_id, deal_id) VALUES ";
        StringBuilder builder = new StringBuilder(insertQuery);
        for (Iterator<Long> iterator = deals.iterator(); iterator.hasNext(); ) {
            builder.append("( ");
            builder.append(entityId);
            builder.append(", ");
            Long id = iterator.next();
            builder.append(id);
            builder.append(" )");
            if (iterator.hasNext()) builder.append(", ");
        }
        builder.append(" ;");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(builder.toString());
        } catch (SQLException e) {
            throw new DAOException("Can't update relations with deals", e);
        }
    }

    private void clearRelationsWithPhones(Contact entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.contact_phone WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with phones", e);
        }
    }

    private void writeRelationsWithPhones(Contact entity, Set<Long> deals) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.contact_phone (contact_id, phone_number_id) VALUES ";
        StringBuilder builder = new StringBuilder(insertQuery);
        for (Iterator<Long> iterator = deals.iterator(); iterator.hasNext(); ) {
            builder.append("( ");
            builder.append(entityId);
            builder.append(", ");
            Long id = iterator.next();
            builder.append(id);
            builder.append(" )");
            if (iterator.hasNext()) builder.append(", ");
        }
        builder.append(" ;");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(builder.toString());
        } catch (SQLException e) {
            throw new DAOException("Can't update relations with phones", e);
        }
    }


}
