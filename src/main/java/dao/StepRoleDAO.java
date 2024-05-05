package dao;

import model.StepRole;
import utils.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StepRoleDAO {
    private static final Logger LOGGER = Logger.getLogger(StepRoleDAO.class.getName());

    public Integer createStepRole(StepRole stepRole) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer generatedId = null;
        
        String sql = "INSERT INTO public.step_roles (role_id, step_id, access_granted) VALUES (?, ?, ?)";
        
        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setInt(1, stepRole.getRoleId());
            stmt.setInt(2, stepRole.getStepId());
            if (stepRole.getAccessGranted() != null) {
                stmt.setTimestamp(3, stepRole.getAccessGranted());
            } else {
                stmt.setNull(3, java.sql.Types.TIMESTAMP);
            }
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating StepRole", e);
        } finally {
            DatabaseUtility.disconnect(conn);
        }
        return generatedId;
    }

    public boolean deleteStepRole(int stepId, int roleId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "DELETE FROM public.step_roles WHERE step_id = ? AND role_id = ?";
        
        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, stepId);
            stmt.setInt(2, roleId);
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting StepRole", e);
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    public boolean updateStepRoleAccessTime(StepRole stepRole) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "UPDATE public.step_roles SET access_granted = ? WHERE step_id = ? AND role_id = ?";
        
        try {
            conn = DatabaseUtility.connect();
            stmt = conn.prepareStatement(sql);
            
            if (stepRole.getAccessGranted() != null) {
                stmt.setTimestamp(1, stepRole.getAccessGranted());
            } else {
                stmt.setNull(1, java.sql.Types.TIMESTAMP);
            }
            stmt.setInt(2, stepRole.getStepId());
            stmt.setInt(3, stepRole.getRoleId());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating StepRole access time", e);
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }
}
