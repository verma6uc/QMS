package dao;

import model.DeviationProduct;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeviationProductDAO {

    private static final Logger LOGGER = Logger.getLogger(DeviationProductDAO.class.getName());

    /**
     * Inserts a new DeviationProduct into the database.
     * 
     * @param deviationProduct The DeviationProduct to insert.
     * @return The id of the newly created DeviationProduct, or -1 if the operation fails.
     */
    public int insertDeviationProduct(DeviationProduct deviationProduct) {
        String sql = "INSERT INTO public.deviation_products (deviation_id, product_id) VALUES (?, ?)";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, deviationProduct.getDeviationId());
            pstmt.setInt(2, deviationProduct.getProductId());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            } else {
                LOGGER.severe("Creating deviation product failed, no rows affected.");
                return -1;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error inserting deviation product", ex);
        } 
        return -1;
    }

    /**
     * Deletes a DeviationProduct by its IDs.
     * 
     * @param deviationId The deviation ID.
     * @param productId The product ID.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteDeviationProduct(int deviationId, int productId) {
        String sql = "DELETE FROM public.deviation_products WHERE deviation_id = ? AND product_id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, deviationId);
            pstmt.setInt(2, productId);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting deviation product", ex);
        } 
        return false;
    }

    /**
     * Updates a DeviationProduct in the database.
     * 
     * @param deviationProduct The updated deviation product detail.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateDeviationProduct(DeviationProduct deviationProduct) {
        String sql = "UPDATE public.deviation_products SET product_id = ? WHERE deviation_id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, deviationProduct.getProductId());
            pstmt.setInt(2, deviationProduct.getDeviationId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating deviation product", ex);
        }  
        return false;
    }
}