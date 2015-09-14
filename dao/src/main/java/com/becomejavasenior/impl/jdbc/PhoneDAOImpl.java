package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.Phone;
import com.becomejavasenior.PhoneDAO;
import com.becomejavasenior.impl.PhoneImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class PhoneDAOImpl extends GenericDAO<Phone> implements PhoneDAO {

    private static final String saveNewPhone =  "INSERT INTO crm.phone (phone_type, phone_number) " +
                                                "VALUES (?, ?) RETURNING phone_number_id;";

    private static final String updatePhone =   "UPDATE crm.phone SET (phone_type, phone_number) = (?, ?) " +
                                                "WHERE phone_number_id = ?;";

    private static final String getPhoneById =  "SELECT phone_number_id, phone_type, phone_number " +
                                                "FROM crm.phone " +
                                                "WHERE phone_number_id = ?;";

    private static final String deletePhone =   "DELETE FROM crm.phone " +
                                                "WHERE phone_number_id = ?;";

    private static final String queryForGetRange = "SELECT * FROM crm.phone ORDER BY phone_number_id LIMIT ? offset ? ;";

    public PhoneDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(Phone entity) throws SQLException {
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
        return id == null ? saveNewPhone : updatePhone;
    }

    @Override
    protected String getQueryForGetById() {
        return getPhoneById;
    }

    @Override
    protected String getQueryForDelete() {
        return deletePhone;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Phone entity) throws SQLException {
        Long id = entity.getId();
        statement.setInt(1, entity.getPhoneType());
        statement.setString(2, entity.getNumber());
        if (id != null) {
            statement.setLong(3, id);
        }
    }

    @Override
    protected Phone mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Phone phone = new PhoneImpl();
        super.setPrivateField(phone, "id", resultSet.getLong("phone_number_id"));
        phone.setPhoneType(resultSet.getInt("phone_type"));
        phone.setNumber(resultSet.getString("phone_number"));
        return phone;
    }
}
