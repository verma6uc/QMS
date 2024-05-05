package dao;

import model.CapaFile;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CapaFileDAO {
    private static final Logger LOGGER = Logger.getLogger(CapaFileDAO.class.getName());

    /**
     * Insert a new CAPA file record into the database.
     * @param capaFile the CAPA file to be inserted.
     * @return the ID of the inserted CAPA file, or -1 if the operation failed.
     */
    public int insertCapaFile(CapaFile capaFile) {
        String sql = "INSERT INTO capa_files (capa_id, file_path, file_description, uploaded_by, uploaded_at) VALUES (?, ?, ?, ?, ?) RETURNING id;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, capaFile.getCapaId());
            stmt.setString(2, capaFile.getFilePath());
            stmt.setString(3, capaFile.getFileDescription());
            stmt.setInt(4, capaFile.getUploadedBy());
            stmt.setTimestamp(5, capaFile.getUploadedAt());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.severe("Failed to insert CAPA file: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Update an existing CAPA file record in the database.
     * @param capaFile the CAPA file with updated details.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateCapaFile(CapaFile capaFile) {
        String sql = "UPDATE capa_files SET capa_id = ?, file_path = ?, file_description = ?, uploaded_by = ?, uploaded_at = ? WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, capaFile.getCapaId());
            stmt.setString(2, capaFile.getFilePath());
            stmt.setString(3, capaFile.getFileDescription());
            stmt.setInt(4, capaFile.getUploadedBy());
            stmt.setTimestamp(5, capaFile.getUploadedAt());
            stmt.setInt(6, capaFile.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.severe("Failed to update CAPA file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete a CAPA file record from the database.
     * @param id the ID of the CAPA file to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteCapaFile(int id) {
        String sql = "DELETE FROM capa_files WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.severe("Failed to delete CAPA file: " + e.getMessage());
            return false;
        }
    }
}