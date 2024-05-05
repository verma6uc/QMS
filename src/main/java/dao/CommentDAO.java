package dao;

import model.Comment;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDAO {

    private static final Logger LOGGER = Logger.getLogger(CommentDAO.class.getName());

    /**
     * Inserts a new comment into the database.
     * 
     * @param comment the Comment object to be inserted
     * @return the generated comment ID if successful, otherwise -1
     */
    public int insertComment(Comment comment) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int generatedId = -1;
        String sql = "INSERT INTO public.\"comments\" (author_id, content, related_table, related_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

        try {
            conn = DatabaseUtility.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, comment.getAuthorId());
            ps.setString(2, comment.getContent());
            ps.setString(3, comment.getRelatedTable());
            ps.setInt(4, comment.getRelatedId());
            ps.setTimestamp(5, comment.getCreatedAt());
            ps.setTimestamp(6, comment.getUpdatedAt());
            rs = ps.executeQuery();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
            LOGGER.log(Level.INFO, "Comment inserted successfully with ID: " + generatedId);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting comment", e);
        } finally {
            DatabaseUtility.disconnect(conn);
        }
        return generatedId;
    }

    /**
     * Updates a comment in the database.
     *
     * @param comment the Comment object to be updated
     * @return true if the comment was successfully updated, false otherwise
     */
    public boolean updateComment(Comment comment) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE public.\"comments\" SET author_id = ?, content = ?, updated_at = ?, related_table = ?, related_id = ? WHERE id = ?;";
        boolean isUpdated = false;

        try {
            conn = DatabaseUtility.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, comment.getAuthorId());
            ps.setString(2, comment.getContent());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setString(4, comment.getRelatedTable());
            ps.setInt(5, comment.getRelatedId());
            ps.setInt(6, comment.getId());
            isUpdated = ps.executeUpdate() > 0;
            if (isUpdated) {
                LOGGER.log(Level.INFO, "Comment updated successfully for ID: " + comment.getId());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating comment", e);
        } finally {
            DatabaseUtility.disconnect(conn);
        }
        return isUpdated;
    }

    /**
     * Deletes a comment from the database.
     *
     * @param commentId the ID of the comment to be deleted
     * @return true if the comment was successfully deleted, false otherwise
     */
    public boolean deleteComment(int commentId) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM public.\"comments\" WHERE id = ?;";
        boolean isDeleted = false;

        try {
            conn = DatabaseUtility.connect();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, commentId);
            isDeleted = ps.executeUpdate() > 0;
            if (isDeleted) {
                LOGGER.log(Level.INFO, "Comment deleted successfully for ID: " + commentId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting comment", e);
        } finally {
            DatabaseUtility.disconnect(conn);
        }
        return isDeleted;
    }

    /**
     * Retrieves a list of all comments from the database.
     *
     * @return a List of Comment objects
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM public.\"comments\" ORDER BY created_at DESC;";

        try {
            conn = DatabaseUtility.connect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setAuthorId(rs.getInt("author_id"));
                comment.setContent(rs.getString("content"));
                comment.setCreatedAt(rs.getTimestamp("created_at"));
                comment.setUpdatedAt(rs.getTimestamp("updated_at"));
                comment.setRelatedTable(rs.getString("related_table"));
                comment.setRelatedId(rs.getInt("related_id"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving comments", e);
        } finally {
            DatabaseUtility.disconnect(conn);
        }
        return comments;
    }
}