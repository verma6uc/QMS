package dao;

import model.Enums;
import model.Investigation;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvestigationDAO {
    private static final Logger LOGGER = Logger.getLogger(InvestigationDAO.class.getName());

    public Integer createInvestigation(Investigation investigation) {
        String sql = "INSERT INTO public.investigations (title, description, deviation_id, lead_investigator_id, methodology, status, started_at, closed_at, created_at, updated_at) VALUES (?, ?, ?, ?, ?::investigation_methodology_enum, ?::investigation_status_enum, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, investigation.getTitle());
            pstmt.setString(2, investigation.getDescription());
            pstmt.setInt(3, investigation.getDeviationId());
            pstmt.setInt(4, investigation.getLeadInvestigatorId());
            pstmt.setString(5, investigation.getMethodology().name());
            pstmt.setString(6, investigation.getStatus().name());
            pstmt.setTimestamp(7, investigation.getStartedAt());
            pstmt.setTimestamp(8, investigation.getClosedAt());
            pstmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));  // Created at
            pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // Updated at

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to create new investigation", e);
        }
        return null;
    }

    public Boolean updateInvestigation(Investigation investigation) {
        String sql = "UPDATE public.investigations SET title = ?, description = ?, lead_investigator_id = ?, methodology = ?::investigation_methodology_enum, status = ?::investigation_status_enum, started_at = ?, closed_at = ?, updated_at = ? WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, investigation.getTitle());
            pstmt.setString(2, investigation.getDescription());
            pstmt.setInt(3, investigation.getLeadInvestigatorId());
            pstmt.setString(4, investigation.getMethodology().name());
            pstmt.setString(5, investigation.getStatus().name());
            pstmt.setTimestamp(6, investigation.getStartedAt());
            pstmt.setTimestamp(7, investigation.getClosedAt());
            pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // Updated at
            pstmt.setInt(9, investigation.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to update investigation", e);
        }
        return false;
    }

    public Boolean deleteInvestigation(int id) {
        String sql = "DELETE FROM public.investigations WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete investigation", e);
        }
        return false;
    }

    public Investigation getInvestigationById(int id) {
        String sql = "SELECT * FROM public.investigations WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Investigation investigation = new Investigation();
                investigation.setId(rs.getInt("id"));
                investigation.setTitle(rs.getString("title"));
                investigation.setDescription(rs.getString("description"));
                investigation.setDeviationId(rs.getInt("deviation_id"));
                investigation.setLeadInvestigatorId(rs.getInt("lead_investigator_id"));
                investigation.setMethodology(Enums.InvestigationMethodologyEnum.valueOf(rs.getString("methodology")));
                investigation.setStatus(Enums.InvestigationStatusEnum.valueOf(rs.getString("status")));
                investigation.setStartedAt(rs.getTimestamp("started_at"));
                investigation.setClosedAt(rs.getTimestamp("closed_at"));
                investigation.setCreatedAt(rs.getTimestamp("created_at"));
                investigation.setUpdatedAt(rs.getTimestamp("updated_at"));
                return investigation;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get investigation by ID", e);
        }
        return null;
    }
}