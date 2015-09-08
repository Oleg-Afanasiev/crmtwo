package com.becomejavasenior.deal;

import com.becomejavasenior.*;

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

    private static Map<String, String> CONFIG_GET_ID = new HashMap<>();

    static {
        CONFIG_GET_ID.put("getTags", "SELECT tag_id FROM crm.deal_tag WHERE deal_id  = ?");
        CONFIG_GET_ID.put("getFiles", "SELECT file_id FROM crm.deal_file WHERE deal_id  = ?");
        CONFIG_GET_ID.put("getComments", "SELECT comment_id FROM crmtwo.crm.deal_comment WHERE deal_id = ?");
        CONFIG_GET_ID.put("getResponsibleUser", "SELECT responsible_user_id FROM crmtwo.crm.deal WHERE deal_id  = ?");
        CONFIG_GET_ID.put("getDealStatus", "SELECT status_id FROM crm.deal WHERE deal_id  = ?");
        CONFIG_GET_ID.put("getContacts", "SELECT contact_id FROM crm.deal_contact WHERE deal_id  = ?");
        CONFIG_GET_ID.put("getCompanies", "SELECT company_id FROM crm.deal_company WHERE deal_id  = ?");
    }

    String saveNewDeal =    "INSERT INTO crmtwo.crm.deal (responsible_user_id, status_id, name, budget, created, updated, is_deleted)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING deal_id;";

    String updateDeal = "UPDATE crmtwo.crm.deal SET (responsible_user_id, status_id, name, budget, created, updated, is_deleted) =\n" +
                        "(?, ?, ?, ?, ?, ?, ?)\n" +
                        "WHERE deal_id = ?;";

    String getDealById =    "SELECT deal_id, responsible_user_id, status_id, name, budget, created, updated, is_deleted \n" +
                            "FROM crm.deal\n" +
                            "WHERE deal_id = ? \n" +
                            "AND is_deleted = FALSE ;";

    String deleteDeal = "UPDATE crmtwo.crm.deal SET (is_deleted) =\n" +
                        "(TRUE)\n" +
                        "WHERE deal_id = ?;";


    public DealDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Map<String, String> getConfig() {
        return CONFIG_GET_ID;
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
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
        statement.setBoolean(7, entity.isDeleted());
        if(id != null){
            statement.setLong(8, id);
        }
    }

    @Override
    protected Deal mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final Deal deal = new DealImpl();
        super.setPrivateField(deal, "id", resultSet.getLong("deal_id"));
        deal.setName(resultSet.getString("name"));
        deal.setBudget(resultSet.getBigDecimal("budget"));
        deal.setCreated(resultSet.getTimestamp("created"));
        deal.setUpdated(resultSet.getTimestamp("updated"));
        deal.setIsDeleted(resultSet.getBoolean("is_deleted"));

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
        if(result != null){
            return result;
        }
        String methodName = method.getName();

        switch (methodName) {
            case "getCompanies":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<Company> set = new HashSet<>();
                CompanyDAO dao = DaoFactoryDMTS.getCompanyDAO();
                for(Long id : ids){
                    set.add(dao.getById(id));
                }
                result = set;
                ((Deal)instance).setCompanies((Set<Company>) result);
            }
            break;
            case "getDealStatus":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                DealStatusDAO dao = DaoFactoryDMTS.getDealStatusDAO();
                result = dao.getById(ids.iterator().next());
                ((Deal)instance).setDealStatus((DealStatus) result);
            }
            break;
            case "getTags":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<Tag> set = new HashSet<Tag>();
                TagDAO dao = DaoFactoryDMTS.getTagDAO();
                for(Long id : ids){
                    set.add(dao.getById(id));
                }
                result = set;
                ((Deal)instance).setTags((Set<Tag>) result);
            }
            break;
            case "getFiles":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<File> set = new HashSet<File>();
                FileDAO dao = DaoFactoryDMTS.getFileDAO();
                for(Long id : ids){
                    set.add(dao.getById(id));
                }
                result = set;
                ((Deal)instance).setFiles((Set<File>) result);
            }
            break;
            case "getComments":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<Comment> set = new HashSet<Comment>();
                CommentDAO dao = DaoFactoryDMTS.getCommentDAO();
                for(Long id : ids){
                    set.add(dao.getById(id));
                }
                result = set;
                ((Deal)instance).setComments((Set<Comment>) result);
            }
            break;
            case "getResponsibleUser":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoFactoryDMTS.getUserDAO().getById(ids.iterator().next());
                ((Deal)instance).setResponsibleUser((User) result);
            }
            break;
            case "getContacts":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<Contact> set = new HashSet<Contact>();
                ContactDAO dao = DaoFactoryDMTS.getContactDAO();
                for(Long id : ids){
                    set.add(dao.getById(id));
                }
                result = set;
                ((Deal)instance).setContacts((Set<Contact>) result);
            }
            default:
                result = method.invoke(instance, args);
                break;
        }
        return result;
    }

}
