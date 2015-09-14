package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.TaskTypeDAO;
import com.becomejavasenior.TaskType;
import com.becomejavasenior.impl.TaskTypeImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class TaskTypeDAOImpl extends GenericDAO<TaskType> implements TaskTypeDAO {

    private static final String saveNewTaskType =   "INSERT INTO crm.task_type (name) " +
                                                    "VALUES (?) RETURNING task_type_id;";

    private static final String updateTaskType =    "UPDATE crm.task_type SET (name)=(?) " +
                                                    "WHERE task_type_id = ?";

    private static final String getTaskTypeById =   "SELECT task_type_id, name " +
                                                    "FROM crm.task_type " +
                                                    "WHERE task_type_id = ?";

    private static final String deleteTaskType =    "DELETE FROM crm.task_type " +
                                                    "WHERE task_type_id = ?";

    private static final String queryForGetRange =  "SELECT * FROM crm.task_type ORDER BY task_type_id LIMIT ? offset ? ;";


    public TaskTypeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(TaskType entity) throws SQLException {
        /*NOP*/
    }

    @Override
    protected String getQueryForGetRange() {
        return queryForGetRange;
    }

    @Override
    protected Map<String, String> getMethodToQueryMap() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getQueryForInsertOrUpdate(Long id) {
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
        if (id != null) {
            statement.setLong(2, id);
        }
    }

    @Override
    protected TaskType mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final TaskType taskType = new TaskTypeImpl();
        super.setPrivateField(taskType, "id", resultSet.getLong("task_type_id"));
        taskType.setName(resultSet.getString("name"));

        return taskType;
    }
}
