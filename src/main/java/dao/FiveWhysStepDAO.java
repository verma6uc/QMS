package dao;

import model.FiveWhysStep;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.Logger;

/**
 * Data Access Object for managing FiveWhysStep entities in the database.
 */
public class FiveWhysStepDAO {
    private static final Logger LOGGER = Logger.getLogger(FiveWhysStepDAO.class.getName());

    /**
     * Inserts a new FiveWhysStep into the database.
     *
     * @param step the FiveWhysStep to insert
     * @return the generated ID of the inserted step
     */
    public Integer createFiveWhysStep(FiveWhysStep step) {
        String query = "INSERT INTO public.five_whys_steps (investigation_id, why_number, description, cause, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, step.getInvestigationId());
            pstmt.setInt(2, step.getWhyNumber());
            pstmt.setString(3, step.getDescription());
            pstmt.setString(4, step.getCause());
            pstmt.setTimestamp(5, step.getCreatedAt());
            pstmt.setTimestamp(6, step.getUpdatedAt());
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            LOGGER.severe("Error inserting FiveWhysStep: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Updates an existing FiveWhysStep in the database.
     *
     * @param step the FiveWhysStep to update
     * @return true if the update was successful, false otherwise
     */
    public Boolean updateFiveWhysStep(FiveWhysStep step) {
        String query = "UPDATE public.five_whys_steps SET investigation_id=?, why_number=?, description=?, cause=?, updated_at=? WHERE id=?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, step.getInvestigationId());
            pstmt.setInt(2, step.getWhyNumber());
            pstmt.setString(3, step.getDescription());
            pstmt.setString(4, step.getCause());
            pstmt.setTimestamp(5, step.getUpdatedAt());
            pstmt.setInt(6, step.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            LOGGER.severe("Error updating FiveWhysStep: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Deletes a FiveWhysStep from the database.
     *
     * @param id the ID of the FiveWhysStep to delete
     * @return true if the delete was successful, false otherwise
     */
    public Boolean deleteFiveWhysStep(int id) {
        String query = "DELETE FROM public.five_whys_steps WHERE id=?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            LOGGER.severe("Error deleting FiveWhysStep: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Finds a FiveWhysStep by its ID.
     *
     * @param id the ID of the FiveWhysStep to find
     * @return the found FiveWhysStep, or null if not found
     */
    public FiveWhysStep getFiveWhysStepById(int id) {
        FiveWhysStep step = null;
        String query = "SELECT * FROM public.five_whys_steps WHERE id=?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                step = new FiveWhysStep();
                step.setId(rs.getInt("id"));
                step.setInvestigationId(rs.getInt("investigation_id"));
                step.setWhyNumber(rs.getInt("why_number"));
                step.setDescription(rs.getString("description"));
                step.setCause(rs.getString("cause"));
                step.setCreatedAt(rs.getTimestamp("created_at"));
                step.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
        } catch (SQLException ex) {
            LOGGER.severe("Error retrieving FiveWhysStep: " + ex.getMessage());
        }
        return step;
    }
}