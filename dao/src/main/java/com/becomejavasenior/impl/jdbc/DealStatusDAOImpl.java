package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.DealStatusDAO;
import com.becomejavasenior.Identity;
import com.becomejavasenior.DealStatus;
import com.becomejavasenior.impl.DealStatusImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class DealStatusDAOImpl extends GenericDAO<DealStatus> implements DealStatusDAO {

    private static final String saveNewDealStatus = "INSERT INTO crm.deal_status (name) VALUES (?) RETURNING status_id;";

    private static final String updateDealStatus = "UPDATE crm.deal_status SET (name) = (?)\n" +
            "WHERE status_id = ?;";

    private static final String getDealStatusById = "SELECT status_id, name\n" +
            "FROM crm.deal_status\n" +
            "WHERE status_id = ?;";

    private static final String deleteDealStatus = "DELETE FROM crm.deal_status\n" +
            "WHERE status_id = ?;";

    private static final String queryForGetRange = "SELECT * FROM crm.deal_status ORDER BY status_id LIMIT ? offset ? ;";

    public DealStatusDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(DealStatus entity) throws SQLException {
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
        return id == null ? saveNewDealStatus : updateDealStatus;
    }

    @Override
    protected String getQueryForGetById() {
        return getDealStatusById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteDealStatus;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, DealStatus entity) throws SQLException {
        Long id = entity.getId();
        statement.setString(1, entity.getName());
        if (id != null) {
            statement.setLong(2, id);
        }

    }

    @Override
    protected DealStatus mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        DealStatus dealStatus = new DealStatusImpl();
        super.setPrivateField(dealStatus, "id", resultSet.getLong("status_id"));
        dealStatus.setName(resultSet.getString("name"));
        return dealStatus;
    }
}
