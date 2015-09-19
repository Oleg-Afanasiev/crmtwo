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
                return improveMethods(method, contact, args);
            }
        };
        Contact proxy =
                (Contact) Proxy.newProxyInstance(Contact.class.getClassLoader(), new Class[]{Contact.class}, handler);
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

    @Override
    protected void saveRelations(Contact entity) throws SQLException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        CommandSaveRelations commandSaveRelations;
        commandSaveRelations = CommandSaveRelations.valueOf(Contact.class.getSimpleName());
        commandSaveRelations.execute(entity);
    }
}
