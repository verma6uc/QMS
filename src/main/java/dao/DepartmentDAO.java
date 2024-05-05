package dao;

import model.Department;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.Logger;

public class DepartmentDAO {

    private static final Logger logger = Logger.getLogger(DepartmentDAO.class.getName());

    /**
     * Inserts a new department into the database.
     * @param department The department object to insert.
     * @return The ID of the created department, or -1 if the operation failed.
     */
    public Integer createDepartment(Department department) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int departmentId = -1;
        String sql = "INSERT INTO public.departments (name, description) VALUES (?, ?) RETURNING id;";

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, department.getName());
            pstmt.setString(2, department.getDescription());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                departmentId = rs.getInt(1);
            }
            return departmentId;
        } catch (SQLException e) {
            logger.severe("Error creating department: " + e.getMessage());
            return -1;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    /**
     * Updates an existing department in the database.
     * @param department The department object to update.
     * @return true if the department was updated successfully, false otherwise.
     */
    public Boolean updateDepartment(Department department) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE public.departments SET name = ?, description = ? WHERE id = ?;";

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, department.getName());
            pstmt.setString(2, department.getDescription());
            pstmt.setInt(3, department.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.severe("Error updating department: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    /**
     * Deletes a department from the database.
     * @param departmentId The ID of the department to delete.
     * @return true if the department was deleted successfully, false otherwise.
     */
    public Boolean deleteDepartment(Integer departmentId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM public.departments WHERE id = ?;";

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, departmentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.severe("Error deleting department: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    /**
     * Fetches a department by its ID.
     * @param departmentId The ID of the department to fetch.
     * @return The department object, or null if not found.
     */
    public Department getDepartmentById(Integer departmentId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Department department = null;
        String sql = "SELECT id, name, description FROM public.departments WHERE id = ?;";

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, departmentId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                department.setDescription(rs.getString("description"));
            }
            return department;
        } catch (SQLException e) {
            logger.severe("Error fetching department: " + e.getMessage());
            return null;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }
}