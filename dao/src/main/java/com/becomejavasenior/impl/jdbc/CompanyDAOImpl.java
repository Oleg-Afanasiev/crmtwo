package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;
import com.becomejavasenior.Deal;
import com.becomejavasenior.impl.CompanyImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.*;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class CompanyDAOImpl extends GenericDAO<Company> implements CompanyDAO {

    private static final Map<String, String> methodToQueryMap;

    static {
        Map<String, String> tempMethodToQueryMap = new HashMap<>();
        tempMethodToQueryMap.put("getPhones", "SELECT phone_number_id FROM crm.company_phone WHERE company_id  = ?");
        tempMethodToQueryMap.put("getDeals", "SELECT deal_id FROM crm.deal_company WHERE company_id  = ?");
        tempMethodToQueryMap.put("getTags", "SELECT tag_id FROM crm.company_tag WHERE company_id  = ?");
        tempMethodToQueryMap.put("getFiles", "SELECT file_id FROM crm.company_file WHERE company_id  = ?");
        tempMethodToQueryMap.put("getComments", "SELECT comment_id FROM crm.company_comment WHERE company_id  = ?");
        tempMethodToQueryMap.put("getResponsibleUser", "SELECT responsible_user_id FROM crm.company WHERE company_id  = ?");
        methodToQueryMap = Collections.unmodifiableMap(tempMethodToQueryMap);
    }

    @Override
    protected Map<String, String> getMethodToQueryMap() {
        return methodToQueryMap;
    }

    private static final String saveNewCompany =    "INSERT INTO crm.company (responsible_user_id, name, email, web_address, address, created, updated) " +
                                                    "VALUES (?, ?, ? , ? , ?, ? , ?) RETURNING company_id;";

    private static final String updateCompany = "UPDATE crm.company SET (responsible_user_id, name, email, web_address, address, created, updated) " +
                                                "= (?, ?, ?, ?, ?, ? , ?)" +
                                                "WHERE company_id = ?;";

    private static final String getCompanyById =    "SELECT company_id, responsible_user_id, name, email, web_address, address, created, updated " +
                                                    "FROM crm.company " +
                                                    "WHERE company_id = ? " +
                                                    "AND is_deleted = FALSE ;";

    private static final String deleteCompany = "UPDATE crm.company SET (is_deleted) = (TRUE) " +
                                                "WHERE company_id = ?;";

    private static final String queryForGetRange = "SELECT * FROM crm.company WHERE is_deleted = FALSE ORDER BY company_id LIMIT ? offset ? ;";

    public CompanyDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected String getQueryForInsertOrUpdate(Long id) {
        return id == null ? saveNewCompany : updateCompany;
    }

    @Override
    protected String getQueryForGetById() {
        return getCompanyById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteCompany;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Company entity) throws SQLException {
        Long id = entity.getId();

        statement.setLong(1, entity.getResponsibleUser().getId());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getWebAddress());
        statement.setString(5, entity.getAddress());
        statement.setTimestamp(6, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(7, new Timestamp(entity.getUpdated().getTime()));
        if (id != null) {
            statement.setLong(8, id);
        }
    }


    @Override
    protected Company mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final Company company = new CompanyImpl();
        super.setPrivateField(company, "id", resultSet.getLong("company_id"));
        company.setName(resultSet.getString("name"));
        company.setEmail(resultSet.getString("email"));
        company.setWebAdress(resultSet.getString("web_address"));
        company.setAdress(resultSet.getString("address"));
        company.setCreated(resultSet.getTimestamp("created"));
        company.setUpdated(resultSet.getTimestamp("updated"));

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return impruveMethods(method, company, args);
            }
        };
        Company proxy =
                (Company) Proxy.newProxyInstance(Company.class.getClassLoader(), new Class[]{Company.class}, handler);
        return proxy;
    }

    private <T extends Identity> Object impruveMethods(Method method, T instance, Object[] args)
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
                    PhoneDAO dao = DaoManager.getInstance().getPhoneDAO();
                    for (Long id : ids) {
                        set.add(dao.getById(id));
                    }
                    result = set;
                    ((Company) instance).setPhones((Set<Phone>) result);
                }
            }
            break;
            case "getDeals": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    Set<Deal> set = new HashSet<>();
                    DealDAO dao = DaoManager.getInstance().getDealDAO();
                    for (Long id : ids) {
                        set.add(dao.getById(id));
                    }
                    result = set;
                    ((Company) instance).setDeals((Set<Deal>) result);
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
                    ((Company) instance).setTags((Set<Tag>) result);
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
                    ((Company) instance).setFiles((Set<File>) result);
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
                    ((Company) instance).setComments((Set<Comment>) result);
                }
            }
            break;
            case "getResponsibleUser": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                if (!ids.isEmpty()) {
                    result = DaoManager.getInstance().getUserDAO().getById(ids.iterator().next());
                    ((Company) instance).setResponsibleUser((User) result);
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
    protected void saveRelations(Company entity) throws SQLException {

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

    @Override
    protected String getQueryForGetRange() {
        return queryForGetRange;
    }

    private void clearRelationsWithComments(Company entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.company_comment WHERE company_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with comments", e);
        }
    }

    private void writeRelationsWithComments(Company entity, Set<Long> comments) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.company_comment (company_id, comment_id) VALUES ";
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

    private void clearRelationsWithTags(Company entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.company_tag WHERE company_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with tags", e);
        }
    }

    private void writeRelationsWithTags(Company entity, Set<Long> tags) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.company_tag (company_id, tag_id) VALUES ";
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

    private void clearRelationsWithFiles(Company entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.company_file WHERE company_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with files", e);
        }
    }

    private void writeRelationsWithFiles(Company entity, Set<Long> files) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.company_file (company_id, file_id) VALUES ";
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

    private void clearRelationsWithDeals(Company entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.deal_company WHERE company_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with deals", e);
        }
    }

    private void writeRelationsWithDeals(Company entity, Set<Long> deals) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.deal_company (company_id, deal_id) VALUES ";
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

    private void clearRelationsWithPhones(Company entity) throws SQLException {
        String clearQuery = "DELETE FROM crm.company_phone WHERE company_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with phones", e);
        }
    }

    private void writeRelationsWithPhones(Company entity, Set<Long> deals) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crm.company_phone (company_id, phone_number_id) VALUES ";
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





