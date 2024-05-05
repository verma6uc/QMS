package dao;

import model.Capa;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.*;

/**
 * Data Access Object for handling database operations related to the Capa entity.
 */
public class CapaDAO {

    private static final Logger logger = Logger.getLogger(CapaDAO.class.getName());

    /**
     * Inserts a new CAPA record into the database.
     *
     * @param capa the CAPA record to add.
     * @return the id of the newly created CAPA.
     */
    public int addCapa(Capa capa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        int capaId = -1;

        String sql = "INSERT INTO public.capas (action_type, completion_date, created_at, description, deviation_id, due_date, responsible_user_id, status, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?::capa_status, ?) RETURNING id;";
        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, capa.getActionType().name());
            stmt.setDate(2, new java.sql.Date(capa.getCompletionDate().getTime()));
            stmt.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
            stmt.setString(4, capa.getDescription());
            stmt.setInt(5, capa.getDeviationId());
            stmt.setDate(6, new java.sql.Date(capa.getDueDate().getTime()));
            stmt.setInt(7, capa.getResponsibleUserId());
            stmt.setString(8, capa.getStatus().name());
            stmt.setTimestamp(9, new Timestamp(new java.util.Date().getTime()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating CAPA failed, no rows affected.");
            }

            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                capaId = generatedKeys.getInt(1);
            }
            logger.info("CAPA created successfully with ID: " + capaId);
        } catch (SQLException ex) {
            logger.severe("Error inserting CAPA: " + ex.getMessage());
        } finally {
            DatabaseUtility.disconnect(conn);
        }

        return capaId;
    }

    /**
     * Updates an existing CAPA record in the database.
     *
     * @param capa the CAPA record to update.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateCapa(Capa capa) {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE public.capas SET action_type = ?, completion_date = ?, description = ?, deviation_id = ?, due_date = ?, responsible_user_id = ?, status = ?::capa_status, updated_at = ? WHERE id = ?;";
        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, capa.getActionType().name());
            stmt.setDate(2, new java.sql.Date(capa.getCompletionDate().getTime()));
            stmt.setString(3, capa.getDescription());
            stmt.setInt(4, capa.getDeviationId());
            stmt.setDate(5, new java.sql.Date(capa.getDueDate().getTime()));
            stmt.setInt(6, capa.getResponsibleUserId());
            stmt.setString(7, capa.getStatus().name());
            stmt.setTimestamp(8, new Timestamp(new java.util.Date().getTime()));
            stmt.setInt(9, capa.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                logger.info("No CAPA found with ID: " + capa.getId());
                return false;
            }
            logger.info("CAPA updated successfully with ID: " + capa.getId());
            return true;
        } catch (SQLException ex) {
            logger.severe("Error updating CAPA: " + ex.getMessage());
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    /**
     * Deletes a CAPA record from the database.
     *
     * @param capaId the id of the CAPA to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteCapa(int capaId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM public.capas WHERE id = ?;";
        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, capaId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                logger.info("No CAPA found with ID: " + capaId);
                return false;
            }
            logger.info("CAPA deleted successfully with ID: " + capaId);
            return true;
        } catch (SQLException ex) {
            logger.severe("Error deleting CAPA: " + ex.getMessage());
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }
}