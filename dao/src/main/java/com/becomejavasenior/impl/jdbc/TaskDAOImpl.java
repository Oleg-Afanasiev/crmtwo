package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;
import com.becomejavasenior.Deal;
import com.becomejavasenior.Task;
import com.becomejavasenior.impl.TaskImpl;
import com.becomejavasenior.TaskPeriod;
import com.becomejavasenior.TaskType;

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

    private static final Map<String, String> methodToQueryMap;

    static {
        Map<String, String> tempMethodToQueryMap = new HashMap<>();
        tempMethodToQueryMap.put("getComments", "SELECT comment_id FROM crmtwo.crm.task_comment WHERE task_id = ?");
        tempMethodToQueryMap.put("getResponsibleUser", "SELECT responsible_user_id FROM crmtwo.crm.task WHERE task_id  = ?");
        tempMethodToQueryMap.put("getDeal", "SELECT deal_id FROM crm.task WHERE task_id  = ?");
        tempMethodToQueryMap.put("getContact", "SELECT contact_id FROM crm.task WHERE task_id  = ?");
        tempMethodToQueryMap.put("getCompany", "SELECT company_id FROM crm.task WHERE task.task_id  = ?");
        tempMethodToQueryMap.put("getTaskType", "SELECT task_type_id FROM crm.task WHERE task_id = ?");
        tempMethodToQueryMap.put("getTaskPeriod", "SELECT period_id FROM crm.task WHERE task_id = ?");
        methodToQueryMap = Collections.unmodifiableMap(tempMethodToQueryMap);
    }

    private static final String saveNewTask =   "INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated) " +
                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING task_id;";

    private static final String updateTask =    "UPDATE crmtwo.crm.task SET (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated) " +
                                                "=(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                                                "WHERE task_id = ?;";

    private static final String getTaskById =   "SELECT task_id, task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, description, created, updated " +
                                                "FROM crmtwo.crm.task " +
                                                "WHERE task_id = ? " +
                                                "AND task.is_deleted = FALSE;";

    private static final String deleteTask =    "UPDATE crmtwo.crm.task SET (is_deleted) " +
                                                "=(TRUE ) " +
                                                "WHERE task_id = ?;";

    private static final String queryForGetRange = "SELECT * FROM crmtwo.crm.task WHERE is_deleted = FALSE ORDER BY task_id LIMIT ? offset ? ;";

    public TaskDAOImpl(Connection connection) {
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
        if (id != null) {
            statement.setLong(12, id);
        }
    }

    @Override
    protected Task mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final Task task = new TaskImpl();
        super.setPrivateField(task, "id", resultSet.getLong("task_id"));
        task.setDueDate(resultSet.getTimestamp("due_date"));
        task.setDescription(resultSet.getString("description"));
        task.setCreated(resultSet.getTimestamp("created"));
        task.setUpdated(resultSet.getTimestamp("updated"));

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return improveMethods(method, task, args);
            }
        };
        Task proxy =
                (Task) Proxy.newProxyInstance(Task.class.getClassLoader(), new Class[]{Task.class}, handler);
        return proxy;
    }

    private <T extends Identity> Object improveMethods(Method method, T instance, Object[] args)
            throws InvocationTargetException, IllegalAccessException, SQLException {
        Object result = method.invoke(instance, args);
        if (result != null) {
            return result;
        }
        String methodName = method.getName();

        switch (methodName) {
            case "getDeal": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoManager.getInstance().getDealDAO().getById(ids.iterator().next());
                ((Task) instance).setDeal((Deal) result);
            }
            break;
            case "getComments": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                Set<Comment> set = new HashSet<Comment>();
                CommentDAO dao = DaoManager.getInstance().getCommentDAO();
                for (Long id : ids) {
                    set.add(dao.getById(id));
                }
                result = set;
                ((Task) instance).setComments((Set<Comment>) result);
            }
            break;
            case "getResponsibleUser": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoManager.getInstance().getUserDAO().getById(ids.iterator().next());
                ((Task) instance).setResponsibleUser((User) result);
            }
            break;
            case "getTaskType": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoManager.getInstance().getTaskTypeDAO().getById(ids.iterator().next());
                ((Task) instance).setTaskType((TaskType) result);
            }
            break;
            case "getTaskPeriod": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoManager.getInstance().getTaskPeriodDAO().getById(ids.iterator().next());
                ((Task) instance).setTaskPeriod((TaskPeriod) result);
            }
            break;
            case "getCompany": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoManager.getInstance().getCompanyDAO().getById(ids.iterator().next());
                ((Task) instance).setCompany((Company) result);
            }
            break;
            case "getContact": {
                Collection<Long> ids = getRelatedIds(methodName, instance);
                result = DaoManager.getInstance().getContactDAO().getById(ids.iterator().next());
                ((Task) instance).setContact((Contact) result);
            }
            break;
            default:
                result = method.invoke(instance, args);
                break;
        }
        return result;
    }

    @Override
    protected void saveRelations(Task entity) throws SQLException {

        Set<Comment> comments = entity.getComments();
        if (comments != null && !comments.isEmpty()) {
            Set<Long> ids = new HashSet<>();
            for (Comment comment : comments) {
                DaoManager.getInstance().getCommentDAO().insertOrUpdate(comment);
                ids.add(comment.getId());
            }
            clearRelationsWithComments(entity, ids);
            writeRelationsWithComments(entity, ids);
        }
    }

    private void clearRelationsWithComments(Task entity, Set<Long> comments) throws SQLException {
        String clearQuery = "DELETE FROM crmtwo.crm.task_comment WHERE task_id = " + entity.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearQuery);
        } catch (SQLException e) {
            throw new DAOException("Can't delete relations with comments", e);
        }
    }

    private void writeRelationsWithComments(Task entity, Set<Long> comments) throws SQLException {
        Long entityId = entity.getId();
        String insertQuery = "INSERT INTO crmtwo.crm.task_comment (task_id, comment_id) VALUES ";
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
}
