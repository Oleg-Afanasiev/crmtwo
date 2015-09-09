package com.becomejavasenior;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class TagDAOImpl extends GenericDAO<Tag> implements TagDAO {

    String saveNewTag = "INSERT INTO crmtwo.crm.tag (name) \n" +
                        "VALUES (?) RETURNING tag_id;";

    String updateTag =  "UPDATE crmtwo.crm.tag SET (name) = (?)\n" +
                        "WHERE tag_id = ?;";

    String getTagById = "SELECT *\n" +
                        "FROM crmtwo.crm.tag\n" +
                        "WHERE tag_id = ?;";

    String deleteTag =  "DELETE\n" +
                        "FROM crmtwo.crm.tag\n" +
                        "WHERE tag_id = ?;";

    public TagDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(Tag entity) throws SQLException {
        /*NOP*/
    }

    @Override
    protected Map<String, String> getConfig() {
        return null;
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
        return id == null ? saveNewTag : updateTag;
    }

    @Override
    protected String getQueryForGetById() {
        return getTagById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteTag;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Tag entity) throws SQLException {
        Long id = entity.getId();
        statement.setString(1, entity.getName());
        if(id != null){
            statement.setLong(2, id);
        }
    }

    @Override
    protected Tag mapFieldsForGetById(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Tag tag = new TagImpl();

        super.setPrivateField(tag, "id", resultSet.getLong("tag_id"));
        tag.setName(resultSet.getString("name"));

        return tag;
    }
}
