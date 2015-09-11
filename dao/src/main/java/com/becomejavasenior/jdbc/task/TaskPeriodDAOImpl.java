package com.becomejavasenior.jdbc.task;

import com.becomejavasenior.jdbc.GenericDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class TaskPeriodDAOImpl extends GenericDAO<TaskPeriod> implements TaskPeriodDAO {

    String saveNewTaskPeriod = "INSERT INTO crmtwo.crm.task_period (period_name)\n" +
            "VALUES (?) RETURNING period_id;";

    String updateTaskPeriod = "UPDATE crmtwo.crm.task_period SET (period_name)=(?)\n" +
            "WHERE period_id = ?;";


    String getTaskPeriodById = "SELECT period_id, period_name\n" +
            "FROM crmtwo.crm.task_period\n" +
            "WHERE period_id = ?;";

    String deleteTaskPeriod = "DELETE FROM crmtwo.crm.task_period\n" +
            "WHERE period_id = ?;";


    public TaskPeriodDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(TaskPeriod entity) throws SQLException {
        /*NOP*/
    }

    @Override
    protected Map<String, String> getConfig() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
        return id == null ? saveNewTaskPeriod : updateTaskPeriod;
    }

    @Override
    protected String getQueryForGetById() {
        return getTaskPeriodById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteTaskPeriod;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, TaskPeriod entity) throws SQLException {
        Long id = entity.getId();
        statement.setString(1, entity.getName());
        if (id != null) {
            statement.setLong(2, id);
        }
    }

    @Override
    protected TaskPeriod mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final TaskPeriod taskPeriod = new TaskPeriodImpl();
        super.setPrivateField(taskPeriod, "id", resultSet.getLong("period_id"));
        taskPeriod.setName(resultSet.getString("period_name"));
        return taskPeriod;
    }
}
