package dao;

import model.Material;
import model.Enums;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.Logger;

public class MaterialDAO {

    private static final Logger logger = Logger.getLogger(MaterialDAO.class.getName());

    public Integer createMaterial(Material material) {
        String sql = "INSERT INTO public.materials (name, description, category, status, created_at, updated_at) VALUES (?, ?, ?::material_category, ?::material_status, ?, ?) RETURNING id;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, material.getName());
            pstmt.setString(2, material.getDescription());
            pstmt.setString(3, material.getCategory().name());
            pstmt.setString(4, material.getStatus().name());
            pstmt.setTimestamp(5, material.getCreatedAt());
            pstmt.setTimestamp(6, material.getUpdatedAt());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.severe("Error creating material: " + e.getMessage());
        }
        return null;
    }

    public Material getMaterialById(int id) {
        String sql = "SELECT * FROM public.materials WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Material material = new Material();
                material.setId(rs.getInt("id"));
                material.setName(rs.getString("name"));
                material.setDescription(rs.getString("description"));
                material.setCategory(Enums.MaterialCategory.valueOf(rs.getString("category")));
                material.setStatus(Enums.MaterialStatus.valueOf(rs.getString("status")));
                material.setCreatedAt(rs.getTimestamp("created_at"));
                material.setUpdatedAt(rs.getTimestamp("updated_at"));
                return material;
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving material by ID: " + e.getMessage());
        }
        return null;
    }

    public boolean updateMaterial(Material material) {
        String sql = "UPDATE public.materials SET name = ?, description = ?, category = ?::material_category, status = ?::material_status, updated_at = ? WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, material.getName());
            pstmt.setString(2, material.getDescription());
            pstmt.setString(3, material.getCategory().name());
            pstmt.setString(4, material.getStatus().name());
            pstmt.setTimestamp(5, material.getUpdatedAt());
            pstmt.setInt(6, material.getId());

            int updatedRows = pstmt.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.severe("Error updating material: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteMaterial(int id) {
        String sql = "DELETE FROM public.materials WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int deletedRows = pstmt.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            logger.severe("Error deleting material: " + e.getMessage());
        }
        return false;
    }
}