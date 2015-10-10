package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;
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
        company.setWebAddress(resultSet.getString("web_address"));
        company.setAddress(resultSet.getString("address"));
        company.setCreated(resultSet.getTimestamp("created"));
        company.setUpdated(resultSet.getTimestamp("updated"));

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return improveMethods(method, company, args);
            }
        };
        Company proxy =
                (Company) Proxy.newProxyInstance(Company.class.getClassLoader(), new Class[]{Company.class}, handler);
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
    protected void saveRelations(Company entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        CommandSaveRelations commandSaveRelations;
        commandSaveRelations = CommandSaveRelations.valueOf(Company.class.getSimpleName());
        commandSaveRelations.execute(entity);
    }

    @Override
    protected String getQueryForGetRange() {
        return queryForGetRange;
    }

}





