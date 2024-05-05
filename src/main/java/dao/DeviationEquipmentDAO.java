package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.DeviationEquipment;
import utils.DatabaseUtility;

public class DeviationEquipmentDAO {
    private static final Logger LOGGER = Logger.getLogger(DeviationEquipmentDAO.class.getName());

    /**
     * Inserts a new DeviationEquipment record into the database.
     *
     * @param deviationEquipment the DeviationEquipment to be added.
     * @return the identifier of the inserted DeviationEquipment, or -1 if the operation failed.
     */
    public int createDeviationEquipment(DeviationEquipment deviationEquipment) {
        String sql = "INSERT INTO public.deviation_equipments (deviation_id, equipment_id) VALUES (?, ?)";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, deviationEquipment.getDeviationId());
            stmt.setInt(2, deviationEquipment.getEquipmentId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            return -1;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Creating DeviationEquipment failed", e);
            return -1;
        }
    }

    /**
     * Deletes a DeviationEquipment record from the database.
     *
     * @param deviationId the deviation ID of the record to be deleted.
     * @param equipmentId the equipment ID of the record to be deleted.
     * @return true if the record was deleted successfully, false otherwise.
     */
    public boolean deleteDeviationEquipment(int deviationId, int equipmentId) {
        String sql = "DELETE FROM public.deviation_equipments WHERE deviation_id = ? AND equipment_id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deviationId);
            stmt.setInt(2, equipmentId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Deleting DeviationEquipment failed", e);
            return false;
        }
    }
}