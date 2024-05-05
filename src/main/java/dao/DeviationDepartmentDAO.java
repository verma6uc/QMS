package dao;

import model.DeviationDepartment;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles database operations for DeviationDepartment objects.
 */
public class DeviationDepartmentDAO {

    private static final Logger logger = Logger.getLogger(DeviationDepartmentDAO.class.getName());

    /**
     * Inserts a new DeviationDepartment record into the database.
     * @param deviationDepartment The DeviationDepartment object to insert.
     * @return The ID of the created DeviationDepartment record.
     */
    public Integer insertDeviationDepartment(DeviationDepartment deviationDepartment) {
        String sql = "INSERT INTO public.deviation_departments (deviation_id, department_id) VALUES (?, ?)";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, deviationDepartment.getDeviationId());
            pstmt.setInt(2, deviationDepartment.getDepartmentId());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        return id;
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to insert DeviationDepartment", e);
        }
        return null;
    }

    /**
     * Deletes a DeviationDepartment record from the database.
     * @param deviationId The ID of the deviation.
     * @param departmentId The ID of the department.
     * @return true if the record was deleted successfully, false otherwise.
     */
    public boolean deleteDeviationDepartment(int deviationId, int departmentId) {
        String sql = "DELETE FROM public.deviation_departments WHERE deviation_id = ? AND department_id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, deviationId);
            pstmt.setInt(2, departmentId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to delete DeviationDepartment", e);
        }
        return false;
    }

    /**
     * Retrieves a list of DeviationDepartment records by deviation ID.
     * @param deviationId The ID of the deviation to retrieve departments for.
     * @return A list of DeviationDepartment objects.
     */
    public List<DeviationDepartment> getDepartmentsByDeviationId(int deviationId) {
        List<DeviationDepartment> departments = new ArrayList<>();
        String sql = "SELECT * FROM public.deviation_departments WHERE deviation_id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, deviationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DeviationDepartment dd = new DeviationDepartment();
                    dd.setDeviationId(rs.getInt("deviation_id"));
                    dd.setDepartmentId(rs.getInt("department_id"));
                    departments.add(dd);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve departments by deviation ID", e);
        }
        return departments;
    }

    /**
     * Retrieves a list of DeviationDepartment records by department ID.
     * @param departmentId The ID of the department to retrieve deviations for.
     * @return A list of DeviationDepartment objects.
     */
    public List<DeviationDepartment> getDeviationsByDepartmentId(int departmentId) {
        List<DeviationDepartment> deviations = new ArrayList<>();
        String sql = "SELECT * FROM public.deviation_departments WHERE department_id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DeviationDepartment dd = new DeviationDepartment();
                    dd.setDeviationId(rs.getInt("deviation_id"));
                    dd.setDepartmentId(rs.getInt("department_id"));
                    deviations.add(dd);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve deviations by department ID", e);
        }
        return deviations;
    }
}
