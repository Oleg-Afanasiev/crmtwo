package com.becomejavasenior.deal;

import com.becomejavasenior.GenericDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class DealDAOImpl extends GenericDAO<Deal> implements DealDAO {
    @Override
    protected Map<String, String> getConfig() {
        return null;
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
        return null;
    }

    @Override
    protected String getQueryForGetById() {
        return null;
    }

    @Override
    protected String getQueryForDelete() {
        return null;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Deal entity) throws SQLException {

    }

    @Override
    protected Deal mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        return null;
    }
}
