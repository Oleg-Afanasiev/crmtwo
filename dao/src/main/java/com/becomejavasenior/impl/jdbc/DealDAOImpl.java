package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;
import com.becomejavasenior.Deal;
import com.becomejavasenior.impl.DealImpl;
import com.becomejavasenior.DealStatus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.*;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class DealDAOImpl extends GenericDAO<Deal> implements DealDAO {

    private static final Map<String, String> methodToQueryMap;

    static {
        Map<String, String> tempMethodToQueryMap = new HashMap<>();
        tempMethodToQueryMap.put("getTags", "SELECT tag_id FROM crm.deal_tag WHERE deal_id  = ?");
        tempMethodToQueryMap.put("getFiles", "SELECT file_id FROM crm.deal_file WHERE deal_id  = ?");
        tempMethodToQueryMap.put("getComments", "SELECT comment_id FROM crm.deal_comment WHERE deal_id = ?");
        tempMethodToQueryMap.put("getResponsibleUser", "SELECT responsible_user_id FROM crm.deal WHERE deal_id  = ?");
        tempMethodToQueryMap.put("getDealStatus", "SELECT status_id FROM crm.deal WHERE deal_id  = ?");
        tempMethodToQueryMap.put("getContacts", "SELECT contact_id FROM crm.deal_contact WHERE deal_id  = ?");
        tempMethodToQueryMap.put("getCompanies", "SELECT company_id FROM crm.deal_company WHERE deal_id  = ?");
        methodToQueryMap = Collections.unmodifiableMap(tempMethodToQueryMap);
    }

    private static final String saveNewDeal = "INSERT INTO crm.deal (responsible_user_id, status_id, name, budget, created, updated) " +
            "VALUES (?, ?, ?, ?, ?, ?) RETURNING deal_id;";

    private static final String updateDeal = "UPDATE crm.deal SET (responsible_user_id, status_id, name, budget, created, updated) = " +
            "(?, ?, ?, ?, ?, ?) " +
            "WHERE deal_id = ?;";

    private static final String getDealById = "SELECT deal_id, responsible_user_id, status_id, name, budget, created, updated " +
            "FROM crm.deal " +
            "WHERE deal_id = ? " +
            "AND is_deleted = FALSE ;";

    private static final String deleteDeal = "UPDATE crm.deal SET (is_deleted) = (TRUE) " +
            "WHERE deal_id = ?;";

    private static final String queryForGetRange = "SELECT * FROM crm.deal WHERE is_deleted = FALSE ORDER BY deal_id LIMIT ? offset ? ;";


    public DealDAOImpl(Connection connection) {
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
        return id == null ? saveNewDeal : updateDeal;
    }

    @Override
    protected String getQueryForGetById() {
        return getDealById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteDeal;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Deal entity) throws SQLException {
        Long id = entity.getId();

        statement.setLong(1, entity.getResponsibleUser().getId());
        statement.setLong(2, entity.getDealStatus().getId());
        statement.setString(3, entity.getName());
        statement.setBigDecimal(4, entity.getBudget());
        statement.setTimestamp(5, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(6, new Timestamp(entity.getUpdated().getTime()));
        if (id != null) {
            statement.setLong(7, id);
        }
    }

    @Override
    protected Deal mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final Deal deal = new DealImpl();
        super.setPrivateField(deal, "id", resultSet.getLong("deal_id"));
        deal.setName(resultSet.getString("name"));
        deal.setBudget(resultSet.getBigDecimal("budget"));
        deal.setCreated(resultSet.getTimestamp("created"));
        deal.setUpdated(resultSet.getTimestamp("updated"));

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return loadEntities(method, deal, args);
            }
        };
        Deal proxy =
                (Deal) Proxy.newProxyInstance(Deal.class.getClassLoader(), new Class[]{Deal.class}, handler);
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
            case "getCompanies": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<Company> set = new HashSet<>();
                    CompanyDAO dao = DaoManager.getInstance().getCompanyDAO();
                    for (Long id : ids) {
                        set.add(dao.getById(id));
                    }
                    result = set;
                    ((Deal) instance).setCompanies((Set<Company>) result);
                }
            }
            break;
            case "getDealStatus": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    DealStatusDAO dao = DaoManager.getInstance().getDealStatusDAO();
                    result = dao.getById(ids.iterator().next());
                    ((Deal) instance).setDealStatus((DealStatus) result);
                }
            }
            break;
            case "getTags": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<Tag> set = new HashSet<Tag>();
                    TagDAO dao = DaoManager.getInstance().getTagDAO();
                    for (Long id : ids) {
                        set.add(dao.getById(id));
                    }
                    result = set;
                    ((Deal) instance).setTags((Set<Tag>) result);
                }
            }
            break;
            case "getFiles": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<File> set = new HashSet<File>();
                    FileDAO dao = DaoManager.getInstance().getFileDAO();
                    for (Long id : ids) {
                        set.add(dao.getById(id));
                    }
                    result = set;
                    ((Deal) instance).setFiles((Set<File>) result);
                }
            }
            break;
            case "getComments": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<Comment> set = new HashSet<Comment>();
                    CommentDAO dao = DaoManager.getInstance().getCommentDAO();
                    for (Long id : ids) {
                        set.add(dao.getById(id));
                    }
                    result = set;
                    ((Deal) instance).setComments((Set<Comment>) result);
                }
            }
            break;
            case "getResponsibleUser": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    result = DaoManager.getInstance().getUserDAO().getById(ids.iterator().next());
                    ((Deal) instance).setResponsibleUser((User) result);
                }
            }
            break;
            case "getContacts": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<Contact> set = new HashSet<Contact>();
                    ContactDAO dao = DaoManager.getInstance().getContactDAO();
                    for (Long id : ids) {
                        set.add(dao.getById(id));
                    }
                    result = set;
                    ((Deal) instance).setContacts((Set<Contact>) result);
                }
            }
            default:
                result = method.invoke(instance, args);
                break;
        }
        return result;
    }

    @Override
    protected void saveRelations(Deal entity) throws SQLException {

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

    }

    private void clearRelationsWithComments(Deal entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.deal_comment WHERE deal_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with comments", e);
        }
    }

    private void writeRelationsWithComments(Deal entity, Set<Long> comments) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.deal_comment (deal_id, comment_id) VALUES ";
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

    private void clearRelationsWithTags(Deal entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.deal_tag WHERE deal_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with tags", e);
        }
    }

    private void writeRelationsWithTags(Deal entity, Set<Long> tags) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.deal_tag (deal_id, tag_id) VALUES ";
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

    private void clearRelationsWithFiles(Deal entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.deal_file WHERE deal_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with files", e);
        }
    }

    private void writeRelationsWithFiles(Deal entity, Set<Long> files) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.deal_file (deal_id, file_id) VALUES ";
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

}