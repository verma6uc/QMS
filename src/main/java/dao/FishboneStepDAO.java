package dao;

import model.FishboneStep;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FishboneStepDAO {
    private final Logger logger = Logger.getLogger(FishboneStepDAO.class.getName());

    /**
     * Insert a new FishboneStep into the database.
     * @param fishboneStep The FishboneStep to be inserted.
     * @return The ID of the inserted FishboneStep if successful, null otherwise.
     */
    public Integer createFishboneStep(FishboneStep fishboneStep) {
        String sql = "INSERT INTO public.fishbone_steps (investigation_id, category, description, cause, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, fishboneStep.getInvestigationId());
            pstmt.setString(2, fishboneStep.getCategory());
            pstmt.setString(3, fishboneStep.getDescription());
            pstmt.setString(4, fishboneStep.getCause());
            pstmt.setTimestamp(5, fishboneStep.getCreatedAt());
            pstmt.setTimestamp(6, fishboneStep.getUpdatedAt());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            logger.severe("Error inserting fishbone step: " + e.getMessage());
        }
        return null;
    }

    /**
     * Update a FishboneStep in the database.
     * @param fishboneStep The FishboneStep to be updated.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateFishboneStep(FishboneStep fishboneStep) {
        String sql = "UPDATE public.fishbone_steps SET investigation_id = ?, category = ?, description = ?, cause = ?, updated_at = ? WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, fishboneStep.getInvestigationId());
            pstmt.setString(2, fishboneStep.getCategory());
            pstmt.setString(3, fishboneStep.getDescription());
            pstmt.setString(4, fishboneStep.getCause());
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(6, fishboneStep.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.severe("Error updating fishbone step: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete a FishboneStep from the database.
     * @param id The ID of the FishboneStep to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteFishboneStep(int id) {
        String sql = "DELETE FROM public.fishbone_steps WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.severe("Error deleting fishbone step: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieve a FishboneStep by its ID.
     * @param id The ID of the FishboneStep to retrieve.
     * @return A FishboneStep object or null if not found.
     */
    public FishboneStep getFishboneStepById(int id) {
        String sql = "SELECT * FROM public.fishbone_steps WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    FishboneStep fishboneStep = new FishboneStep();
                    fishboneStep.setId(rs.getInt("id"));
                    fishboneStep.setInvestigationId(rs.getInt("investigation_id"));
                    fishboneStep.setCategory(rs.getString("category"));
                    fishboneStep.setDescription(rs.getString("description"));
                    fishboneStep.setCause(rs.getString("cause"));
                    fishboneStep.setCreatedAt(rs.getTimestamp("created_at"));
                    fishboneStep.setUpdatedAt(rs.getTimestamp("updated_at"));
                    return fishboneStep;
                }
            }
        } catch (SQLException e) {
            logger.severe("Error retriving fishbone step: " + e.getMessage());
        }
        return null;
    }
}