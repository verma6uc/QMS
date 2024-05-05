package dao;

import model.InvestigationInvestigator;
import utils.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for the InvestigationInvestigator model.
 */
public class InvestigationInvestigatorDAO {

    private static final Logger logger = Logger.getLogger(InvestigationInvestigatorDAO.class.getName());

    /**
     * Inserts a new InvestigationInvestigator into the database.
     *
     * @param ii The InvestigationInvestigator object to be inserted.
     * @return The ID of the created InvestigationInvestigator entry, or -1 if an error occurred.
     */
    public int insertInvestigationInvestigator(InvestigationInvestigator ii) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        String insertQuery = "INSERT INTO investigation_investigators (investigation_id, investigator_id, assigned_date) VALUES (?, ?, ?)";

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, ii.getInvestigationId());
            pstmt.setInt(2, ii.getInvestigatorId());
            pstmt.setTimestamp(3, ii.getAssignedDate());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating investigation investigator failed, no rows affected.");
            }

            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating investigation investigator failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting investigation investigator", e);
            return -1;
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException logOrIgnore) {}
            if (conn != null) DatabaseUtility.disconnect(conn);
        }
    }

    /**
     * Updates an existing InvestigationInvestigator in the database.
     *
     * @param ii The InvestigationInvestigator object to update.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateInvestigationInvestigator(InvestigationInvestigator ii) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String updateQuery = "UPDATE investigation_investigators SET investigator_id = ?, assigned_date = ? WHERE investigation_id = ?";

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, ii.getInvestigatorId());
            pstmt.setTimestamp(2, ii.getAssignedDate());
            pstmt.setInt(3, ii.getInvestigationId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating investigation investigator", e);
            return false;
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException logOrIgnore) {}
            if (conn != null) DatabaseUtility.disconnect(conn);
        }
    }

    /**
     * Deletes an InvestigationInvestigator from the database.
     *
     * @param investigationId The ID of the investigation to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteInvestigationInvestigator(int investigationId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String deleteQuery = "DELETE FROM investigation_investigators WHERE investigation_id = ?";

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, investigationId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting investigation investigator", e);
            return false;
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException logOrIgnore) {}
            if (conn != null) DatabaseUtility.disconnect(conn);
        }
    }
}