package com.becomejavasenior;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class PhoneDAOImpl extends GenericDAO<Phone> implements PhoneDAO {

    String saveNewPhone =   "INSERT INTO crmtwo.crm.phone (phone_type, phone_number) " +
                            "VALUES (?, ?) RETURNING phone_number_id;";

    String updatePhone =    "UPDATE crmtwo.crm.phone SET (phone_type, phone_number) = (?, ?) \n" +
                            "WHERE phone_number_id = ?;";

    String getPhoneById =   "SELECT phone_number_id, phone_type, phone_number\n" +
                            "FROM crm.phone\n" +
                            "WHERE phone_number_id = ?;";

    String deletePhone =    "DELETE \n" +
                            "FROM crm.phone\n" +
                            "WHERE phone_number_id = ?;";



    public PhoneDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Map<String, String> getConfig() {
        return null;
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
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
        if(id != null){
            statement.setLong(3, id);
        }
    }

    @Override
    protected Phone mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Phone phone = new PhoneImpl();
        super.setPrivateField(phone, "id", resultSet.getLong("phone_number_id"));
        phone.setPhoneType(resultSet.getInt("phone_type"));
        phone.setNumber(resultSet.getString("phone_number"));
        return phone;
    }
}
