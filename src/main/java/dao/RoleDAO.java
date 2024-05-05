package dao;

import utils.DatabaseUtility;
import model.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RoleDAO {
    private static final Logger LOGGER = Logger.getLogger(RoleDAO.class.getName());

    public Integer createRole(Role role) {
        String sql = "INSERT INTO public.roles (name, description, created_at, updated_at) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        
        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, role.getName());
            pstmt.setString(2, role.getDescription());
            pstmt.setTimestamp(3, role.getCreatedAt());
            pstmt.setTimestamp(4, role.getUpdatedAt());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating role failed, no rows affected.");
            }
            
            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating role failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.severe("Error creating role: " + e.getMessage());
            return null;
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException logOrIgnore) {}
            DatabaseUtility.disconnect(conn);
        }
    }

    public boolean updateRole(Role role) {
        String sql = "UPDATE public.roles SET name = ?, description = ?, updated_at = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, role.getName());
            pstmt.setString(2, role.getDescription());
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(4, role.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.severe("Error updating role: " + e.getMessage());
            return false;
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException logOrIgnore) {}
            DatabaseUtility.disconnect(conn);
        }
    }

    public boolean deleteRole(int roleId) {
        String sql = "DELETE FROM public.roles WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, roleId);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.severe("Error deleting role: " + e.getMessage());
            return false;
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException logOrIgnore) {}
            DatabaseUtility.disconnect(conn);
        }
    }

    public Role getRoleById(int id) {
        String sql = "SELECT * FROM public.roles WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("description"));
                role.setCreatedAt(rs.getTimestamp("created_at"));
                role.setUpdatedAt(rs.getTimestamp("updated_at"));
                return role;
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOGGER.severe("Error retrieving role: " + e.getMessage());
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException logOrIgnore) {}
            DatabaseUtility.disconnect(conn);
        }
    }
    
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM public.roles";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("description"));
                role.setCreatedAt(rs.getTimestamp("created_at"));
                role.setUpdatedAt(rs.getTimestamp("updated_at"));
                roles.add(role);
            }
        } catch (SQLException e) {
            LOGGER.severe("Error getting all roles: " + e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException logOrIgnore) {}
            DatabaseUtility.disconnect(conn);
        }
        return roles;
    }
}