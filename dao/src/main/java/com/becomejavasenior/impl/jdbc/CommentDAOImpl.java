package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.Comment;
import com.becomejavasenior.CommentDAO;
import com.becomejavasenior.impl.CommentImpl;

import java.sql.*;
import java.util.Map;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class CommentDAOImpl extends GenericDAO<Comment> implements CommentDAO {

    String saveNewComment = "INSERT INTO crmtwo.crm.comment (name, comment, created, updated)\n" +
            "VALUES (?, ?, ?, ?) RETURNING comment_id;";

    String updateComment = "UPDATE crmtwo.crm.comment c\n" +
            "SET (name, comment, created, updated) = (?, ?, ?, ?)\n" +
            "WHERE c.comment_id = ?";

    String getCommentById = "SELECT comment_id, name, comment, created, updated\n" +
            "FROM crm.comment\n" +
            "WHERE comment_id = ?";

    String deleteComment = "DELETE \n" +
            "FROM crm.comment\n" +
            "WHERE comment_id = ?";

    public CommentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void saveRelations(Comment entity) {
        /*NOP*/
    }

    @Override
    protected String getQueryForGetRange() {
        return "SELECT * FROM crmtwo.crm.comment WHERE is_deleted = FALSE ORDER BY comment_id LIMIT ? offset ? ;";
    }

    @Override
    protected Map<String, String> getMethodToQueryMap() {
        return null;
    }

    @Override
    protected String getQueryForSaveOrUpdate(Long id) {
        return id == null ? saveNewComment : updateComment;
    }

    @Override
    protected String getQueryForGetById() {
        return getCommentById;
    }

    @Override
    protected String getQueryForDelete() {
        return deleteComment;
    }

    @Override
    protected void setParamsForSaveOrUpdate(PreparedStatement statement, Comment entity) throws SQLException {
        Long id = entity.getId();

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getComment());
        statement.setTimestamp(3, new Timestamp(entity.getCreated().getTime()));
        statement.setTimestamp(4, new Timestamp(entity.getUpdated().getTime()));
        if (id != null) {
            statement.setLong(5, id);
        }
    }

    @Override
    protected Comment mapFieldsFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Comment comment = new CommentImpl();
        super.setPrivateField(comment, "id", resultSet.getLong("comment_id"));
        comment.setName(resultSet.getString("name"));
        comment.setComment(resultSet.getString("comment"));
        comment.setCreated(resultSet.getTimestamp("created"));
        comment.setUpdated(resultSet.getTimestamp("updated"));
        return comment;
    }
}
