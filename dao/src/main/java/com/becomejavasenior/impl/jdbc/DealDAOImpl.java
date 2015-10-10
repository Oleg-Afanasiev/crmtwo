package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.DealImpl;

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
                return improveMethods(method, deal, args);
            }
        };
        Deal proxy =
                (Deal) Proxy.newProxyInstance(Deal.class.getClassLoader(), new Class[]{Deal.class}, handler);
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
    protected void saveRelations(Deal entity) throws SQLException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        CommandSaveRelations commandSaveRelations;
        commandSaveRelations = CommandSaveRelations.valueOf(Deal.class.getSimpleName());
        commandSaveRelations.execute(entity);
    }
}
