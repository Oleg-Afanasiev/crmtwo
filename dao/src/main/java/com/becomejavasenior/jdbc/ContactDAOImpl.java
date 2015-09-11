package com.becomejavasenior.jdbc;

import com.becomejavasenior.*;
import com.becomejavasenior.jdbc.deal.Deal;
import com.becomejavasenior.jdbc.deal.DealDAO;

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

    private static Map<String, String> CONFIG_GET_ID = new HashMap<>();

    static {
        CONFIG_GET_ID.put("getResponsibleUser", "SELECT responsible_user_id FROM crmtwo.crm.contact WHERE contact_id  = ?");
        CONFIG_GET_ID.put("getPhones", "SELECT phone_number_id FROM crmtwo.crm.contact_phone WHERE contact_id  = ?");
        CONFIG_GET_ID.put("getCompany", "SELECT company_id FROM crmtwo.crm.contact WHERE contact_id  = ?");
        CONFIG_GET_ID.put("getFiles", "SELECT file_id FROM crmtwo.crm.contact_file WHERE contact_id  = ?");
        CONFIG_GET_ID.put("getComments", "SELECT comment_id FROM crmtwo.crm.contact_comment WHERE contact_id  = ?");
        CONFIG_GET_ID.put("getTags", "SELECT tag_id FROM crmtwo.crm.contact_tag WHERE contact_id  = ?");
        CONFIG_GET_ID.put("getDeals", "SELECT deal_id FROM crmtwo.crm.deal_contact WHERE contact_id  = ?");

    }

    String saveNewContact = "INSERT INTO crmtwo.crm.contact (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? ) RETURNING contact_id;";

    String updateContact = "UPDATE crmtwo.crm.contact SET (company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted) =\n" +
            "(?, ?, ?, ?, ?, ?, ?, ?, ? )\n" +
            "WHERE contact_id = ?;";

    String getContactById = "SELECT contact_id, company_id, responsible_user_id, name, job_position, email, skype, created, updated, is_deleted\n" +
            "FROM crmtwo.crm.contact\n" +
            "WHERE contact.contact_id = ?\n" +
            "AND contact.is_deleted = FALSE;";

    String deleteContact = "UPDATE crmtwo.crm.contact SET (is_deleted) =\n" +
            "(TRUE)\n" +
            "WHERE contact_id = ?;";

    public ContactDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Map<String, String> getConfig() {
        return CONFIG_GET_ID;
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
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
        statement.setLong(1, entity.getCompany().getId());
        statement.setLong(2, entity.getResponsibleUser().getId());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getJobPosition());
        statement.setString(5, entity.getEmail());
        statement.setString(6, entity.getSkype());
        statement.setTimestamp(7, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(8, new Timestamp(entity.getUpdated().getTime()));
        statement.setBoolean(9, entity.isDeleted());
        if (id != null) {
            statement.setLong(10, id);
        }
    }

    @Override
    protected Contact mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final Contact contact = new ContactImpl();
        super.setPrivateField(contact, "id", resultSet.getLong("contact_id"));
        contact.setName(resultSet.getString("name"));
        contact.setJobPosition(resultSet.getString("job_position"));
        contact.setEmail(resultSet.getString("email"));
        contact.setSkype(resultSet.getString("skype"));
        contact.setCreated(resultSet.getTimestamp("created"));
        contact.setUpdated(resultSet.getTimestamp("updated"));
        contact.setIsDeleted(resultSet.getBoolean("is_deleted"));

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
                Set<Phone> set = new HashSet<>();
                PhoneDAO pd = DaoManager.getInstance().getPhoneDAO();
                for (Long id : ids) {
                    set.add(pd.getById(id));
                }
                result = set;
                ((Contact) instance).setPhones((Set<Phone>) result);
            }
            break;
            case "getDeals": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<Deal> set = new HashSet<>();
                DealDAO dd = DaoManager.getInstance().getDealDAO();
                for (Long id : ids) {
                    set.add(dd.getById(id));
                }
                result = set;
                ((Contact) instance).setDeals((Set<Deal>) result);
            }
            break;
            case "getTags": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<Tag> set = new HashSet<Tag>();
                TagDAO td = DaoManager.getInstance().getTagDAO();
                for (Long id : ids) {
                    set.add(td.getById(id));
                }
                result = set;
                ((Contact) instance).setTags((Set<Tag>) result);
            }
            break;
            case "getFiles": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<File> set = new HashSet<File>();
                FileDAO fd = DaoManager.getInstance().getFileDAO();
                for (Long id : ids) {
                    set.add(fd.getById(id));
                }
                result = set;
                ((Contact) instance).setFiles((Set<File>) result);
            }
            break;
            case "getComments": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<Comment> set = new HashSet<Comment>();
                CommentDAO cd = DaoManager.getInstance().getCommentDAO();
                for (Long id : ids) {
                    set.add(cd.getById(id));
                }
                result = set;
                ((Contact) instance).setComments((Set<Comment>) result);
            }
            break;
            case "getResponsibleUser": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoManager.getInstance().getUserDAO().getById(ids.iterator().next());
                ((Contact) instance).setResponsibleUser((User) result);
            }
            break;
            case "getCompany": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoManager.getInstance().getCompanyDAO().getById(ids.iterator().next());
                ((Contact) instance).setCompany((Company) result);
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
                DaoManager.getInstance().getCommentDAO().saveOrUpdate(comment);
                ids.add(comment.getId());
            }
            clearRelationsWithComments(entity);
            writeRelationsWithComments(entity, ids);
        }

        Set<Tag> tags = entity.getTags();
        if (tags != null && !tags.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (Tag tag : tags) {
                DaoManager.getInstance().getTagDAO().saveOrUpdate(tag);
                ids.add(tag.getId());
            }
            clearRelationsWithTags(entity);
            writeRelationsWithTags(entity, ids);
        }

        Set<File> files = entity.getFiles();
        if (files != null && !files.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (File file : files) {
                DaoManager.getInstance().getFileDAO().saveOrUpdate(file);
                ids.add(file.getId());
            }
            clearRelationsWithFiles(entity);
            writeRelationsWithFiles(entity, ids);
        }

        Set<Deal> deals = entity.getDeals();
        if (deals != null && !deals.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (Deal deal : deals) {
                DaoManager.getInstance().getDealDAO().saveOrUpdate(deal);
                ids.add(deal.getId());
            }
            clearRelationsWithDeals(entity);
            writeRelationsWithDeals(entity, ids);
        }

        Set<Phone> phones = entity.getPhones();
        if (phones != null && !phones.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (Phone phone : phones) {
                DaoManager.getInstance().getPhoneDAO().saveOrUpdate(phone);
                ids.add(phone.getId());
            }
            clearRelationsWithPhones(entity);
            writeRelationsWithPhones(entity, ids);
        }


    }

    private void clearRelationsWithComments(Contact entity) throws SQLException {
        String clearQuery = "DELETE FROM crmtwo.crm.contact_comment WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with comments", e);
        }
    }

    private void writeRelationsWithComments(Contact entity, Set<Long> comments) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crmtwo.crm.contact_comment (contact_id, comment_id) VALUES ";
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
        String clearQuery = "DELETE FROM crmtwo.crm.contact_tag WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with tags", e);
        }
    }

    private void writeRelationsWithTags(Contact entity, Set<Long> tags) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crmtwo.crm.contact_tag (contact_id, tag_id) VALUES ";
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
        String clearQuery = "DELETE FROM crmtwo.crm.contact_file WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with files", e);
        }
    }

    private void writeRelationsWithFiles(Contact entity, Set<Long> files) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crmtwo.crm.contact_file (contact_id, file_id) VALUES ";
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
        String clearQuery = "DELETE FROM crmtwo.crm.deal_contact WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with deals", e);
        }
    }

    private void writeRelationsWithDeals(Contact entity, Set<Long> deals) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crmtwo.crm.deal_contact (contact_id, deal_id) VALUES ";
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
        String clearQuery = "DELETE FROM crmtwo.crm.contact_phone WHERE contact_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with phones", e);
        }
    }

    private void writeRelationsWithPhones(Contact entity, Set<Long> deals) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crmtwo.crm.contact_phone (contact_id, phone_number_id) VALUES ";
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
