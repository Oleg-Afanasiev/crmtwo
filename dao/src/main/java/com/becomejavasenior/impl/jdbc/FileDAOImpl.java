package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.File;
import com.becomejavasenior.FileDAO;
import com.becomejavasenior.impl.FileImpl;

import java.sql.*;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class FileDAOImpl extends GenericDAO<File> implements FileDAO {

    public FileDAOImpl(Connection connection) {
        this.connection = connection;
    }

    private static final String saveNewFile =   "INSERT INTO crm.file (file_path, file_mime_type, created, updated) " +
                                                "VALUES (?, ?, ?, ?) RETURNING file_id;";

    private static final String updateFile =    "UPDATE crm.file SET (file_path, file_mime_type, created, updated) = " +
                                                "(?, ?, ?, ?) " +
                                                "WHERE file_id = ? ;";

    private static final String getFileById =   "SELECT f.file_id, f.file_path, f.file_mime_type, f.created, f.updated " +
                                                "FROM crm.file f " +
                                                "WHERE file_id = ?";

    private static final String deleteFile =    "DELETE FROM crm.file f " +
                                                "WHERE f.file_id = ?";

    private static final String queryForGetRange = "SELECT * FROM crm.file ORDER BY file_id LIMIT ? offset ? ;";


    @Override
    protected void saveRelations(File entity) throws SQLException {
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
        return id == null ? saveNewFile : updateFile;
    }

    @Override
    protected String getQueryForGetById() {
        return getFileById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteFile;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, File entity) throws SQLException {
        Long entityId = entity.getId();

        statement.setString(1, entity.getPath());
        statement.setString(2, entity.getMimeType());
        statement.setTimestamp(3, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(4, new Timestamp(entity.getUpdated().getTime()));
        if (entityId != null) {
            statement.setLong(5, entityId);
        }
    }

    @Override
    protected File mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        File file = new FileImpl();

        super.setPrivateField(file, "id", resultSet.getLong("file_id"));
        file.setPath(resultSet.getString("file_path"));
        file.setMimeType(resultSet.getString("file_mime_type"));
        file.setCreated(resultSet.getTimestamp("created"));
        file.setUpdated(resultSet.getTimestamp("updated"));

        return file;
    }
}
