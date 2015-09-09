package com.becomejavasenior.task;

import com.becomejavasenior.*;
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
public class TaskDAOImpl extends GenericDAO<Task> implements TaskDAO {

    private static Map<String, String> CONFIG_GET_ID = new HashMap<>();

    static {
        CONFIG_GET_ID.put("getComments", "SELECT comment_id FROM crmtwo.crm.task_comment WHERE task_id = ?");
        CONFIG_GET_ID.put("getResponsibleUser", "SELECT responsible_user_id FROM crmtwo.crm.task WHERE task_id  = ?");
        CONFIG_GET_ID.put("getDeal", "SELECT deal_id FROM crm.task WHERE task_id  = ?");
        CONFIG_GET_ID.put("getContact", "SELECT contact_id FROM crm.task WHERE task_id  = ?");
        CONFIG_GET_ID.put("getCompany", "SELECT company_id FROM crm.task WHERE task.task_id  = ?");
        CONFIG_GET_ID.put("getTaskType", "SELECT task_type_id FROM crm.task WHERE task_id = ?");
    }

    String saveNewTask =    "INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING task_id;";

    String updateTask = "UPDATE crmtwo.crm.task SET (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted)\n" +
                        "=(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n" +
                        "WHERE task_id = ?;";

    String getTaskById =    "SELECT task_id, task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated, is_deleted\n" +
                            "FROM crmtwo.crm.task\n" +
                            "WHERE task_id = ?\n" +
                            "AND task.is_deleted = FALSE;";

    String deleteTask = "UPDATE crmtwo.crm.task SET (is_deleted)\n" +
                        "=(TRUE )\n" +
                        "WHERE task_id = ?;";

    public TaskDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Map<String, String> getConfig() {
        return CONFIG_GET_ID;
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
        return id == null ? saveNewTask : updateTask;
    }

    @Override
    protected String getQueryForGetById() {
        return getTaskById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteTask;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Task entity) throws SQLException {
        Long id = entity.getId();
        statement.setLong(1, entity.getTaskType().getId());
        statement.setLong(2, entity.getResponsibleUser().getId());
        statement.setLong(3, entity.getCompany().getId());
        statement.setLong(4, entity.getDeal().getId());
        statement.setLong(5, entity.getContact().getId());
        statement.setLong(6, entity.getTaskPeriod().getId());
        statement.setTimestamp(7, new Timestamp(entity.getDueDate().getTime()));
        statement.setString(8, entity.getDescription());
        statement.setTimestamp(9, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(10, new Timestamp(entity.getUpdated().getTime()));
        statement.setBoolean(11, entity.isDeleted());
        if(id != null){
            statement.setLong(12, id);
        }
    }

    @Override
    protected Task mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final Task task = new TaskImpl();
        super.setPrivateField(task, "id", resultSet.getLong("task_id"));
        task.setDueDate(resultSet.getTimestamp("due_date"));
        task.setDescription(resultSet.getString("description"));
        task.setCreated(resultSet.getTimestamp("created"));
        task.setUpdated(resultSet.getTimestamp("updated"));
        task.setIsDeleted(resultSet.getBoolean("is_deleted"));

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return loadEntities(method, task, args);
            }
        };
        Task proxy =
                (Task) Proxy.newProxyInstance(Task.class.getClassLoader(), new Class[]{Task.class}, handler);
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
            case "getDeal":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoFactoryDMTS.getDealDAO().getById(ids.iterator().next());
                ((Task)instance).setDeal((Deal) result);
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
                ((Task)instance).setComments((Set<Comment>) result);
            }
            break;
            case "getResponsibleUser":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoFactoryDMTS.getUserDAO().getById(ids.iterator().next());
                ((Task)instance).setResponsibleUser((User) result);
            }
            break;
            case "getTaskType":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoFactoryDMTS.getTaskTypeDAO().getById(ids.iterator().next());
                ((Task)instance).setTaskType((TaskType) result);
            }
            break;
            case "getCompany":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoFactoryDMTS.getCompanyDAO().getById(ids.iterator().next());
                ((Task)instance).setCompany((Company) result);
            }
            break;
            case "getContact":
            {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoFactoryDMTS.getContactDAO().getById(ids.iterator().next());
                ((Task)instance).setContact((Contact) result);
            }
            break;
            default:
                result = method.invoke(instance, args);
                break;
        }
        return result;
    }


}
