package com.becomejavasenior;

import com.becomejavasenior.deal.Deal;
import com.becomejavasenior.deal.DealDAO;

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

    private static Map<String, String> CONFIG_GET_ID = new HashMap<>();

    static {
        CONFIG_GET_ID.put("getPhones", "SELECT phone_number_id FROM crm.company_phone WHERE company_id  = ?");
        CONFIG_GET_ID.put("getDeals", "SELECT deal_id FROM crm.deal_company WHERE company_id  = ?");
        CONFIG_GET_ID.put("getTags", "SELECT tag_id FROM crm.company_tag WHERE company_id  = ?");
        CONFIG_GET_ID.put("getFiles", "SELECT file_id FROM crm.company_file WHERE company_id  = ?");
        CONFIG_GET_ID.put("getComments", "SELECT comment_id FROM crm.company_comment WHERE company_id  = ?");
        CONFIG_GET_ID.put("getResponsibleUser", "SELECT responsible_user_id FROM crm.company WHERE company_id  = ?");

    }

    @Override
    protected Map<String, String> getConfig(){
        return CONFIG_GET_ID;
    }

    String saveNewCompany = "INSERT INTO crmtwo.crm.company (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)\n" +
                            "VALUES (?, ?, ? , ? , ?, ? , ?, ?) RETURNING company_id;";

    String updateCompany =  "UPDATE crmtwo.crm.company SET (responsible_user_id, name, email, web_address, address, created, updated, is_deleted)\n" +
                            "= (?, ?, ? , ? , ?, ? , ?, ?)\n" +
                            "WHERE company_id = ?;";

    String getCompanyById = "SELECT company_id, responsible_user_id, name, email, web_address, address, created, updated, is_deleted\n" +
                            "FROM crmtwo.crm.company\n" +
                            "WHERE company_id = ?;";

    String deleteCompany =  "DELETE\n" +
                            "FROM crmtwo.crm.company\n" +
                            "WHERE company_id = ?;";

    public CompanyDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
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
        Long id = ((Identity)entity).getId();

        statement.setLong(1,entity.getResponsibleUser().getId());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getWebAdress());
        statement.setString(5, entity.getAdress());
        statement.setTimestamp(6, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(7, new Timestamp(entity.getUpdated().getTime()));
        statement.setBoolean(8, entity.isDeleted());
        if(id != null){
            statement.setLong(9, id);
        }
    }

    @Override
    protected Company mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final Company company = new CompanyImpl();
        super.setPrivateField(company, "id", resultSet.getLong("company_id"));
        company.setName(resultSet.getString("name"));
        company.setEmail(resultSet.getString("email"));
        company.setWebAdress(resultSet.getString("web_address"));
        company.setAdress(resultSet.getString("address"));
        company.setCreated(resultSet.getTimestamp("created"));
        company.setUpdated(resultSet.getTimestamp("updated"));
        company.setIsDeleted(resultSet.getBoolean("is_deleted"));

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return loadEntities(method, company, args);
            }
        };
        Company proxy =
                (Company)Proxy.newProxyInstance(Company.class.getClassLoader(), new Class[]{Company.class}, handler);
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
            case "getPhones":
                            {
                                Collection<Long> ids = getRelatedIds(methodName, instance);
                                Set<Phone> set = new HashSet<>();
                                PhoneDAO pd = DaoFactoryDMTS.getPhoneDAO();
                                for(Long id : ids){
                                    set.add(pd.getById(id));
                                }
                                result = set;
                                ((Company)instance).setPhones((Set<Phone>) result);
                            }
                break;
            case "getDeals":
                            {
                                Collection<Long> ids = getRelatedIds(methodName, instance);
                                Set<Deal> set = new HashSet<>();
                                DealDAO dd = DaoFactoryDMTS.getDealDAO();
                                for(Long id : ids){
                                    set.add(dd.getById(id));
                                }
                                result = set;
                                ((Company)instance).setDeals((Set<Deal>) result);
                            }
                break;
            case "getTags":
                            {
                                Collection<Long> ids = getRelatedIds(methodName, instance);
                                Set<Tag> set = new HashSet<Tag>();
                                TagDAO td = DaoFactoryDMTS.getTagDAO();
                                for(Long id : ids){
                                    set.add(td.getById(id));
                                }
                                result = set;
                                ((Company)instance).setTags((Set<Tag>) result);
                            }
                break;
            case "getFiles":
                            {
                                Collection<Long> ids = getRelatedIds(methodName, instance);
                                Set<File> set = new HashSet<File>();
                                FileDAO fd = DaoFactoryDMTS.getFileDAO();
                                for(Long id : ids){
                                    set.add(fd.getById(id));
                                }
                                result = set;
                                ((Company)instance).setFiles((Set<File>) result);
                            }
                break;
            case "getComments":
                            {
                                Collection<Long> ids = getRelatedIds(methodName, instance);
                                Set<Comment> set = new HashSet<Comment>();
                                CommentDAO cd = DaoFactoryDMTS.getCommentDAO();
                                for(Long id : ids){
                                    set.add(cd.getById(id));
                                }
                                result = set;
                                ((Company)instance).setComments((Set<Comment>) result);
                            }
                break;
            case "getResponsibleUser":
                                        {
                                            Collection<Long> ids = getRelatedIds(methodName, instance);
                                            result = DaoFactoryDMTS.getUserDAO().getById(ids.iterator().next());
                                            ((Company)instance).setResponsibleUser((User) result);
                                        }
                break;
            default:
                result = method.invoke(instance, args);
                break;
        }
        return result;
    }
}





