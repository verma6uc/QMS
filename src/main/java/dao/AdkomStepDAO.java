package dao;

import model.AdkomStep;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdkomStepDAO {
    private static final Logger LOGGER = Logger.getLogger(AdkomStepDAO.class.getName());

    /**
     * Inserts a new AdkomStep into the database.
     * @param adkomStep the AdkomStep to be inserted.
     * @return the id of the newly inserted AdkomStep.
     */
    public int insertAdkomStep(AdkomStep adkomStep) {
        int generatedId = -1;
        String sql = "INSERT INTO public.adkom_steps (investigation_id, step, assessment, result, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, adkomStep.getInvestigationId());
            pstmt.setString(2, adkomStep.getStep());
            pstmt.setString(3, adkomStep.getAssessment());
            pstmt.setBoolean(4, adkomStep.isResult());
            pstmt.setTimestamp(5, adkomStep.getCreatedAt());
            pstmt.setTimestamp(6, adkomStep.getUpdatedAt());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Inserting AdkomStep failed", e);
        }
        return generatedId;
    }

    /**
     * Updates an existing AdkomStep in the database.
     * @param adkomStep the AdkomStep to update.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateAdkomStep(AdkomStep adkomStep) {
        String sql = "UPDATE public.adkom_steps SET investigation_id = ?, step = ?, assessment = ?, result = ?, updated_at = ? WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, adkomStep.getInvestigationId());
            pstmt.setString(2, adkomStep.getStep());
            pstmt.setString(3, adkomStep.getAssessment());
            pstmt.setBoolean(4, adkomStep.isResult());
            pstmt.setTimestamp(5, adkomStep.getUpdatedAt());
            pstmt.setInt(6, adkomStep.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Updating AdkomStep failed", e);
            return false;
        }
    }

    /**
     * Deletes an AdkomStep from the database.
     * @param adkomStepId the id of the AdkomStep to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteAdkomStep(int adkomStepId) {
        String sql = "DELETE FROM public.adkom_steps WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, adkomStepId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Deleting AdkomStep failed", e);
            return false;
        }
    }

    
}