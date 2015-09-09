package com.becomejavasenior.task;

import com.becomejavasenior.GenericDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class TaskTypeDAOImpl extends GenericDAO<TaskType> implements TaskTypeDAO {

    String saveNewTaskType =    "INSERT INTO crmtwo.crm.task_type (name) \n" +
                                "VALUES (?) RETURNING task_type_id;";

    String updateTaskType = "UPDATE crmtwo.crm.task_type SET (name)=(?)\n" +
                            "WHERE task_type_id = ?";

    String getTaskTypeById =    "SELECT task_type_id, name\n" +
                                "FROM crmtwo.crm.task_type\n" +
                                "WHERE task_type_id = ?";

    String deleteTaskType = "DELETE FROM crmtwo.crm.task_type\n" +
                            "WHERE task_type_id = ?";


    public TaskTypeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(TaskType entity) throws SQLException {
        /*NOP*/
    }

    @Override
    protected Map<String, String> getConfig() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
        return id == null ? saveNewTaskType : updateTaskType;
    }

    @Override
    protected String getQueryForGetById() {
        return getTaskTypeById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteTaskType;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, TaskType entity) throws SQLException {
        Long id = entity.getId();
        statement.setString(1, entity.getName());
        if(id != null){
            statement.setLong(2, id);
        }
    }

    @Override
    protected TaskType mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final TaskType taskType = new TaskTypeImpl();
        super.setPrivateField(taskType, "id", resultSet.getLong("task_type_id"));
        taskType.setName(resultSet.getString("name"));

        return taskType;
    }
}
