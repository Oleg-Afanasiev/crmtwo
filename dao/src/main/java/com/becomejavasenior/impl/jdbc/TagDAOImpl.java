package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.Tag;
import com.becomejavasenior.TagDAO;
import com.becomejavasenior.impl.TagImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class TagDAOImpl extends GenericDAO<Tag> implements TagDAO {

    private static final String saveNewTag =    "INSERT INTO crmtwo.crm.tag (name) " +
                                                "VALUES (?) RETURNING tag_id;";

    private static final String updateTag = "UPDATE crmtwo.crm.tag SET (name) = (?) " +
                                            "WHERE tag_id = ?;";

    private static final String getTagById =    "SELECT * " +
                                                "FROM crmtwo.crm.tag " +
                                                "WHERE tag_id = ?;";

    private static final String deleteTag = "DELETE " +
                                            "FROM crmtwo.crm.tag " +
                                            "WHERE tag_id = ?;";

    private static final String queryForGetRange = "SELECT * FROM crmtwo.crm.tag ORDER BY tag_id LIMIT ? offset ? ;";

    public TagDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(Tag entity) throws SQLException {
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
        if (id != null) {
            statement.setLong(2, id);
        }
    }

    @Override
    protected Tag mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Tag tag = new TagImpl();

        super.setPrivateField(tag, "id", resultSet.getLong("tag_id"));
        tag.setName(resultSet.getString("name"));

        return tag;
    }
}
