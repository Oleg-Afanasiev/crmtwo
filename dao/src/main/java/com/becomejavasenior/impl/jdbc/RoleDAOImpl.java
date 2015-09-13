package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.RoleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


/**
 * Created by Dmytro Tsapko on 9/13/2015.
 */
public class RoleDAOImpl extends GenericDAO<Role> implements RoleDAO {

    private static final String queryForGetRange = "SELECT * FROM crmtwo.crm.role WHERE is_deleted = FALSE ORDER BY role_id LIMIT ? offset ? ;";

    private static final String queryForInsert = "INSERT INTO crmtwo.crm.role (role_id) VALUES (?) RETURNING role_id ;";

    private static final String queryForUpdate = "UPDATE crmtwo.crm.role SET (role_id) = (?) WHERE role_id = ? ;";

    private static final String queryForGetById = "SELECT * FROM crmtwo.crm.role WHERE is_deleted = FALSE AND role_id = ? ;";

    private static final String queryForDelete = "UPDATE crmtwo.crm.role SET (is_deleted) = (TRUE) WHERE role_id = ? ;";

    public RoleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(Role entity) throws SQLException {
        /*NOP*/
    }

    @Override
    protected String getQueryForGetRange() {
        return queryForGetRange;
    }

    @Override
    protected Map<String, String> getMethodToQueryMap() {
        return null;
    }

    @Override
    protected String getQueryForInsertOrUpdate(Long id) {
        return id == null ? queryForInsert : queryForUpdate;
    }

    @Override
    protected String getQueryForGetById() {
        return queryForGetById;
    }

    @Override
    protected String getQueryForDelete() {
        return queryForDelete;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Role entity) throws SQLException {
        Long id = entity.getId();
        statement.setString(1, entity.getName());
        if(id != null){
            statement.setLong(2, id);
        }
    }

    @Override
    protected Role mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Role role = new RoleImpl();
        super.setPrivateField(role, "id", resultSet.getLong("role_id"));
        role.setName(resultSet.getString("name"));
        return role;
    }
}
