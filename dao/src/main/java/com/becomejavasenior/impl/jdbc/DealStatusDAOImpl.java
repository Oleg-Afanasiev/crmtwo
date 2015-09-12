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

    String saveNewDealStatus = "INSERT INTO crmtwo.crm.deal_status (name) VALUES (?) RETURNING status_id;";

    String updateDealStatus = "UPDATE crmtwo.crm.deal_status SET (name) = (?)\n" +
            "WHERE status_id = ?;";

    String getDealStatusById = "SELECT status_id, name\n" +
            "FROM crmtwo.crm.deal_status\n" +
            "WHERE status_id = ?;";

    String deleteDealStatus = "DELETE FROM crmtwo.crm.deal_status\n" +
            "WHERE status_id = ?;";

    public DealStatusDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(DealStatus entity) throws SQLException {
        /*NOP*/
    }

    @Override
    protected Map<String, String> getConfig() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
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
        Long id = ((Identity) entity).getId();
        statement.setString(1, entity.getName());
        if (id != null) {
            statement.setLong(2, id);
        }

    }

    @Override
    protected DealStatus mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        DealStatus dealStatus = new DealStatusImpl();
        super.setPrivateField(dealStatus, "id", resultSet.getLong("status_id"));
        dealStatus.setName(resultSet.getString("name"));
        return dealStatus;
    }
}
