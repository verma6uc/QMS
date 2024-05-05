package dao;

import model.Step;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.Logger;

public class StepDAO {
    private static final Logger logger = Logger.getLogger(StepDAO.class.getName());

    /**
     * Inserts a new step into the database.
     * @param step the step to insert.
     * @return the generated id of the inserted step or -1 if an error occurs.
     */
    public int insertStep(Step step) {
        Connection conn = DatabaseUtility.connect();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "INSERT INTO public.steps (description, details, step_number, page_id, workflow_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, step.getDescription());
            stmt.setString(2, step.getDetails());
            stmt.setInt(3, step.getStepNumber());
            stmt.setInt(4, step.getPageId());
            stmt.setInt(5, step.getWorkflowId());
            stmt.setTimestamp(6, step.getCreatedAt());
            stmt.setTimestamp(7, step.getUpdatedAt());
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.severe("Error inserting step: " + e.getMessage());
        } finally {
            DatabaseUtility.disconnect(conn);
        }
        return -1;
    }

    /**
     * Updates an existing step.
     * @param step the step to update.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateStep(Step step) {
        Connection conn = DatabaseUtility.connect();
        PreparedStatement stmt = null;
        String query = "UPDATE public.steps SET description = ?, details = ?, step_number = ?, page_id = ?, workflow_id = ?, updated_at = ? WHERE id = ?;";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, step.getDescription());
            stmt.setString(2, step.getDetails());
            stmt.setInt(3, step.getStepNumber());
            stmt.setInt(4, step.getPageId());
            stmt.setInt(5, step.getWorkflowId());
            stmt.setTimestamp(6, step.getUpdatedAt());
            stmt.setInt(7, step.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.severe("Error updating step: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    /**
     * Deletes a step from the database.
     * @param stepId the id of the step to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteStep(int stepId) {
        Connection conn = DatabaseUtility.connect();
        PreparedStatement stmt = null;
        String query = "DELETE FROM public.steps WHERE id = ?;";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, stepId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.severe("Error deleting step: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }
}