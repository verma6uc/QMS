package dao;

import model.Workflow;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.*;

/**
 * Data access object to handle the database operations required for Workflow.
 */
public class WorkflowDAO {
    private static final Logger logger = Logger.getLogger(WorkflowDAO.class.getName());

    /**
     * Inserts a new Workflow into the database.
     *
     * @param workflow The Workflow object to be inserted.
     * @return The id of the created workflow or -1 if the operation failed.
     */
    public int insertWorkflow(Workflow workflow) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        String SQL_INSERT = "INSERT INTO public.workflow (workflow_name, description, created_at, updated_at) VALUES (?, ?, ?, ?)";

        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, workflow.getWorkflowName());
            stmt.setString(2, workflow.getDescription());
            stmt.setTimestamp(3, workflow.getCreatedAt());
            stmt.setTimestamp(4, workflow.getUpdatedAt());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating workflow failed, no rows affected.");
            }

            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating workflow failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting workflow", e);
            return -1;
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
            DatabaseUtility.disconnect(conn);
        }
    }

    /**
     * Updates an existing Workflow record in the database.
     *
     * @param workflow The Workflow object to be updated.
     * @return boolean representing success or failure of the update.
     */
    public boolean updateWorkflow(Workflow workflow) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String SQL_UPDATE = "UPDATE public.workflow SET workflow_name = ?, description = ?, updated_at = ? WHERE id = ?";

        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, workflow.getWorkflowName());
            stmt.setString(2, workflow.getDescription());
            stmt.setTimestamp(3, workflow.getUpdatedAt());
            stmt.setInt(4, workflow.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating workflow", e);
            return false;
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
            DatabaseUtility.disconnect(conn);
        }
    }

    /**
     * Deletes a Workflow record from the database.
     *
     * @param workflowId The id of the Workflow to be deleted.
     * @return boolean representing success or failure of the deletion.
     */
    public boolean deleteWorkflow(int workflowId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String SQL_DELETE = "DELETE FROM public.workflow WHERE id = ?";

        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1, workflowId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting workflow", e);
            return false;
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
            DatabaseUtility.disconnect(conn);
        }
    }
}