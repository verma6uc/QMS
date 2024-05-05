package dao;

import model.Equipment;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.Logger;

public class EquipmentDAO {
    private static final Logger LOGGER = Logger.getLogger(EquipmentDAO.class.getName());

    public Integer createEquipment(Equipment equipment) {
        String sql = "INSERT INTO public.equipments (name, description, category, location, status) VALUES (?, ?, ?::equipment_category, ?, ?::equipment_status)";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getDescription());
            stmt.setString(3, equipment.getCategory().name());
            stmt.setString(4, equipment.getLocation());
            stmt.setString(5, equipment.getStatus().name());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating equipment failed, no rows affected.");
            }

            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating equipment failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.severe("Error creating equipment: " + e.getMessage());
            return null;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    public boolean updateEquipment(Equipment equipment) {
        String sql = "UPDATE public.equipments SET name=?, description=?, category=?::equipment_category, location=?, status=?::equipment_status WHERE id=?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getDescription());
            stmt.setString(3, equipment.getCategory().name());
            stmt.setString(4, equipment.getLocation());
            stmt.setString(5, equipment.getStatus().name());
            stmt.setInt(6, equipment.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.severe("Error updating equipment: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    public boolean deleteEquipment(int id) {
        String sql = "DELETE FROM public.equipments WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.severe("Error deleting equipment: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    public Equipment getEquipmentById(int id) {
        String sql = "SELECT id, name, description, category, location, status, created_at, updated_at FROM public.equipments WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Equipment equipment = new Equipment();
                equipment.setId(rs.getInt("id"));
                equipment.setName(rs.getString("name"));
                equipment.setDescription(rs.getString("description"));
               // equipment.setCategory(Enum.valueOf(Equipment.EquipmentCategory.class, rs.getString("category")));
                equipment.setLocation(rs.getString("location"));
               // equipment.setStatus(Enum.valueOf(Equipment.EquipmentStatus.class, rs.getString("status")));
                equipment.setCreatedAt(rs.getTimestamp("created_at"));
                equipment.setUpdatedAt(rs.getTimestamp("updated_at"));
                return equipment;
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOGGER.severe("Error retrieving equipment: " + e.getMessage());
            return null;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }
}