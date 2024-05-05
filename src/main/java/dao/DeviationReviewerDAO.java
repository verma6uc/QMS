package dao;

import model.DeviationReviewer;
import utils.DatabaseUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for the DeviationReviewer model.
 */
public class DeviationReviewerDAO {

    private static final Logger LOGGER = Logger.getLogger(DeviationReviewerDAO.class.getName());

    /**
     * Inserts a new DeviationReviewer record in the database.
     *
     * @param deviationReviewer the DeviationReviewer to add
     * @return the generated ID for the new record
     */
    public Integer addDeviationReviewer(DeviationReviewer deviationReviewer) {
        String query = "INSERT INTO public.deviation_reviewers (deviation_id, reviewer_id, created_at, updated_at) VALUES (?, ?, ?, ?) RETURNING id;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, deviationReviewer.getDeviationId());
            stmt.setInt(2, deviationReviewer.getReviewerId());
            stmt.setTimestamp(3, deviationReviewer.getCreatedAt());
            stmt.setTimestamp(4, deviationReviewer.getUpdatedAt());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting DeviationReviewer", e);
        }
        return null;
    }

    /**
     * Updates an existing DeviationReviewer record.
     *
     * @param deviationReviewer the DeviationReviewer to update
     * @return true if the update was successful
     */
    public Boolean updateDeviationReviewer(DeviationReviewer deviationReviewer) {
        String query = "UPDATE public.deviation_reviewers SET deviation_id = ?, reviewer_id = ?, updated_at = ? WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, deviationReviewer.getDeviationId());
            stmt.setInt(2, deviationReviewer.getReviewerId());
            stmt.setTimestamp(3, deviationReviewer.getUpdatedAt());
            stmt.setInt(4, deviationReviewer.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating DeviationReviewer", e);
        }
        return false;
    }

    /**
     * Deletes a DeviationReviewer record.
     *
     * @param id the ID of the DeviationReviewer to delete
     * @return true if the deletion was successful
     */
    public Boolean deleteDeviationReviewer(int id) {
        String query = "DELETE FROM public.deviation_reviewers WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting DeviationReviewer", e);
        }
        return false;
    }

    /**
     * Retrieves a DeviationReviewer by ID.
     *
     * @param id the ID to search for
     * @return the DeviationReviewer if found
     */
    public DeviationReviewer getDeviationReviewerById(int id) {
        String query = "SELECT * FROM public.deviation_reviewers WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                DeviationReviewer reviewer = new DeviationReviewer();
                reviewer.setId(rs.getInt("id"));
                reviewer.setDeviationId(rs.getInt("deviation_id"));
                reviewer.setReviewerId(rs.getInt("reviewer_id"));
                reviewer.setCreatedAt(rs.getTimestamp("created_at"));
                reviewer.setUpdatedAt(rs.getTimestamp("updated_at"));
                return reviewer;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving DeviationReviewer by id", e);
        }
        return null;
    }

    /**
     * Retrieves all DeviationReviewers.
     *
     * @return a list of DeviationReviewers
     */
    public List<DeviationReviewer> getAllDeviationReviewers() {
        List<DeviationReviewer> reviewers = new ArrayList<>();
        String query = "SELECT * FROM public.deviation_reviewers;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                DeviationReviewer reviewer = new DeviationReviewer();
                reviewer.setId(rs.getInt("id"));
                reviewer.setDeviationId(rs.getInt("deviation_id"));
                reviewer.setReviewerId(rs.getInt("reviewer_id"));
                reviewer.setCreatedAt(rs.getTimestamp("created_at"));
                reviewer.setUpdatedAt(rs.getTimestamp("updated_at"));
                reviewers.add(reviewer);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all DeviationReviewers", e);
        }
        return reviewers;
    }
}