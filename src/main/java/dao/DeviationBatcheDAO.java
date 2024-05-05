package dao;

import model.DeviationBatche;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeviationBatcheDAO {

    private static final Logger LOGGER = Logger.getLogger(DeviationBatcheDAO.class.getName());

    /**
     * Inserts a new DeviationBatche into the database.
     *
     * @param deviationBatche the DeviationBatche object to insert.
     * @return the id of the created DeviationBatche or -1 if the operation failed.
     */
    public int insertDeviationBatche(DeviationBatche deviationBatche) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        int id = -1;
        String sql = "INSERT INTO public.deviation_batches (deviation_id, batch_id) VALUES (?, ?)";

        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, deviationBatche.getDeviationId());
            stmt.setInt(2, deviationBatche.getBatchId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.severe("Inserting deviation batche failed, no rows affected.");
                throw new SQLException("Creating deviation batche failed, no rows affected.");
            }
            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                LOGGER.severe("Creating deviation batche failed, no ID obtained.");
                throw new SQLException("Creating deviation batche failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error inserting deviation batche", ex);
        } finally {
            DatabaseUtility.disconnect(conn);
        }

        return id;
    }

    /**
     * Deletes a DeviationBatche from the database.
     *
     * @param deviationId the ID of the DeviationBatche to delete.
     * @param batchId the batch ID related to the deviation.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteDeviationBatche(int deviationId, int batchId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        String sql = "DELETE FROM public.deviation_batches WHERE deviation_id = ? AND batch_id = ?";

        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, deviationId);
            stmt.setInt(2, batchId);

            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting deviation batche", ex);
        } finally {
            DatabaseUtility.disconnect(conn);
        }

        return result > 0;
    }
}