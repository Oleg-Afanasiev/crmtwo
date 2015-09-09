package com.becomejavasenior.task;

import com.becomejavasenior.GenericDAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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

    String saveNewTask =    "INSERT INTO crmtwo.crm.task (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, comment, created, updated, is_deleted)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING task_id;";

    String updateTask = "UPDATE crmtwo.crm.task SET (task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, comment, created, updated, is_deleted)\n" +
                        "=(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n" +
                        "WHERE task_id = ?;";

    String getTaskById =    "SELECT task_id, task_type_id, responsible_user_id, company_id, deal_id, contact_id, period_id, due_date, comment, created, updated, is_deleted\n" +
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
        statement.setString(8, entity.getComments());
    }

    @Override
    protected Task mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        return null;
    }
}
