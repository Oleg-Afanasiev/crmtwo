package com.becomejavasenior;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class ContactDAOImpl extends GenericDAO<Contact> implements ContactDAO {

    private static Map<String, String> CONFIG_GET_ID = new HashMap<>();

    static {
        CONFIG_GET_ID.put("getPhones", "SELECT phone_number_id FROM crm.company_phone WHERE company_id  = ?");
        CONFIG_GET_ID.put("getDeals", "SELECT deal_id FROM crm.deal_company WHERE company_id  = ?");
        CONFIG_GET_ID.put("getTags", "SELECT tag_id FROM crm.company_tag WHERE company_id  = ?");
        CONFIG_GET_ID.put("getFiles", "SELECT file_id FROM crm.company_file WHERE company_id  = ?");
        CONFIG_GET_ID.put("getComments", "SELECT comment_id FROM crm.company_comment WHERE company_id  = ?");
        CONFIG_GET_ID.put("getResponsibleUser", "SELECT responsible_user_id FROM crm.company WHERE company_id  = ?");

    }

    String saveNewContact = "";

    String updateContact = "";

    String getContactById = "";

    String deleteContact = "";

    public ContactDAOImpl(Connection connection) {
        this.connection = connection;
    }

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
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Contact entity) throws SQLException {

    }

    @Override
    protected Contact mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        return null;
    }
}
