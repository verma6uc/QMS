package dao;

import model.Batche;
import model.Enums;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BatcheDAO {

    private static final Logger logger = Logger.getLogger(BatcheDAO.class.getName());

    /**
     * Inserts a new batch into the database.
     * 
     * @param batch the Batche object to be inserted.
     * @return the id of the newly created batch if successful, -1 otherwise.
     */
    public Integer insertBatch(Batche batch) {
        String query = "INSERT INTO batches(product_id, batch_number, status, manufacture_date, expiry_date) VALUES(?,?,?,?,?) RETURNING id;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, batch.getProductId());
            pstmt.setString(2, batch.getBatchNumber());
            pstmt.setString(3, batch.getStatus().name());
            pstmt.setDate(4, batch.getManufactureDate());
            pstmt.setDate(5, batch.getExpiryDate());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting batch", e);
        }
        return -1;
    }

    /**
     * Updates an existing batch.
     * 
     * @param batch the Batche object to be updated.
     * @return true if the update was successful, false otherwise.
     */
    public Boolean updateBatch(Batche batch) {
        String query = "UPDATE batches SET product_id = ?, batch_number = ?, status = ?, manufacture_date = ?, expiry_date = ? WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, batch.getProductId());
            pstmt.setString(2, batch.getBatchNumber());
            pstmt.setString(3, batch.getStatus().name());
            pstmt.setDate(4, batch.getManufactureDate());
            pstmt.setDate(5, batch.getExpiryDate());
            pstmt.setInt(6, batch.getId());
            int updatedRows = pstmt.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating batch", e);
        }
        return false;
    }

    /**
     * Deletes a batch specified by the ID.
     * 
     * @param id the id of the batch to be deleted.
     * @return true if the batch was deleted, false otherwise.
     */
    public Boolean deleteBatch(Integer id) {
        String query = "DELETE FROM batches WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int deletedRows = pstmt.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting batch", e);
        }
        return false;
    }

    /**
     * Retrieves a batch by its ID.
     * 
     * @param id the ID of the batch.
     * @return an Optional containing the found batch if it exists, otherwise an empty Optional.
     */
    public Optional<Batche> getBatchById(Integer id) {
        String query = "SELECT * FROM batches WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Batche batch = new Batche();
                batch.setId(rs.getInt("id"));
                batch.setProductId(rs.getInt("product_id"));
                batch.setBatchNumber(rs.getString("batch_number"));
                batch.setStatus(Enums.BatchStatus.valueOf(rs.getString("status")));
                batch.setManufactureDate(rs.getDate("manufacture_date"));
                batch.setExpiryDate(rs.getDate("expiry_date"));
                return Optional.of(batch);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving batch by ID", e);
        }
        return Optional.empty();
    }
}