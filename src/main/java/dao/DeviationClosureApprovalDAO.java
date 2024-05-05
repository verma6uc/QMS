package dao;

import model.DeviationClosureApproval;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for handling 'deviation_closure_approvals' table operations.
 */
public class DeviationClosureApprovalDAO {

    private static final Logger LOGGER = Logger.getLogger(DeviationClosureApprovalDAO.class.getName());

    /**
     * Inserts a DeviationClosureApproval into the database.
     *
     * @param deviationClosureApproval The DeviationClosureApproval object to be inserted.
     * @return The generated id if insertion is successful, otherwise -1.
     */
    public Integer createDeviationClosureApproval(DeviationClosureApproval deviationClosureApproval) {
        String query = "INSERT INTO public.deviation_closure_approvals(deviation_id, approved_by_user_id, approval_date, comments) VALUES (?, ?, ?, ?) RETURNING id;";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, deviationClosureApproval.getDeviationId());
            stmt.setInt(2, deviationClosureApproval.getApprovedByUserId());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));  // approval_date is set to current date/time
            stmt.setString(4, deviationClosureApproval.getComments());

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error insert DeviationClosureApproval", e);
        } finally {
            DatabaseUtility.disconnect(conn);
        }
        return -1;
    }

    /**
     * Deletes a DeviationClosureApproval from the database.
     *
     * @param id The ID of the DeviationClosureApproval to delete.
     * @return true if deletion was successful, else false.
     */
    public Boolean deleteDeviationClosureApproval(int id) {
        String query = "DELETE FROM public.deviation_closure_approvals WHERE id = ?;";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting DeviationClosureApproval with ID: " + id, e);
        } finally {
            DatabaseUtility.disconnect(conn);
        }
        return false;
    }
}