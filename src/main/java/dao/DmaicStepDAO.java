package dao;

import model.DmaicStep;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DmaicStepDAO {

    private static final Logger LOGGER = Logger.getLogger(DmaicStepDAO.class.getName());

    public Integer createDmaicStep(DmaicStep dmaicStep) {
        Connection conn = DatabaseUtility.connect();
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        String sql = "INSERT INTO public.dmaic_steps (investigation_id, phase, description, findings, recommendations, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, dmaicStep.getInvestigationId());
            stmt.setString(2, dmaicStep.getPhase());
            stmt.setString(3, dmaicStep.getDescription());
            stmt.setString(4, dmaicStep.getFindings());
            stmt.setString(5, dmaicStep.getRecommendations());
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating dmaic step failed, no rows affected.");
            }

            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating dmaic step failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating DMAIC step", e);
            return null;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    public boolean updateDmaicStep(DmaicStep dmaicStep) {
        Connection conn = DatabaseUtility.connect();
        PreparedStatement stmt = null;
        String sql = "UPDATE public.dmaic_steps SET phase=?, description=?, findings=?, recommendations=?, updated_at=? WHERE id=?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dmaicStep.getPhase());
            stmt.setString(2, dmaicStep.getDescription());
            stmt.setString(3, dmaicStep.getFindings());
            stmt.setString(4, dmaicStep.getRecommendations());
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(6, dmaicStep.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating DMAIC step", e);
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    public DmaicStep getDmaicStepById(int id) {
        Connection conn = DatabaseUtility.connect();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM public.dmaic_steps WHERE id=?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                DmaicStep dmaicStep = new DmaicStep();
                dmaicStep.setId(rs.getInt("id"));
                dmaicStep.setInvestigationId(rs.getInt("investigation_id"));
                dmaicStep.setPhase(rs.getString("phase"));
                dmaicStep.setDescription(rs.getString("description"));
                dmaicStep.setFindings(rs.getString("findings"));
                dmaicStep.setRecommendations(rs.getString("recommendations"));
                dmaicStep.setCreatedAt(rs.getTimestamp("created_at"));
                dmaicStep.setUpdatedAt(rs.getTimestamp("updated_at"));
                return dmaicStep;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving DMAIC step by ID", e);
        } finally {
            DatabaseUtility.disconnect(conn);
        }
        return null;
    }

    public boolean deleteDmaicStep(int id) {
        Connection conn = DatabaseUtility.connect();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM public.dmaic_steps WHERE id=?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting DMAIC step", e);
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }
}