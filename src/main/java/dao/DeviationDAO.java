package dao;

import model.Deviation;
import model.Enums;
import utils.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeviationDAO {

    private static final Logger LOGGER = Logger.getLogger(DeviationDAO.class.getName());

    // Insert a new Deviation
    public Integer insertDeviation(Deviation deviation) {
        String sql = "INSERT INTO public.deviations (title, description, event_related_type, " +
                "date_of_occurrence, date_of_identification, time_of_identification, " +
                "date_of_initiation, document_details, file_attachments, " +
                "impact_on_batches, impact_on_other_batches, risk_assessment_product, " +
                "risk_assessment_facility, risk_assessment_equipment, risk_assessment_others, " +
                "justification_for_delay, status, initiated_by_user_id, department_id, remarks) " +
                "VALUES (?, ?, ?::event_related_enum, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::deviation_status, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet keySet = null;

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, deviation.getTitle());
            pstmt.setString(2, deviation.getDescription());
            pstmt.setString(3, deviation.getEventRelatedType().name());
            pstmt.setDate(4, new java.sql.Date(deviation.getDateOfOccurrence().getTime()));
            pstmt.setDate(5, new java.sql.Date(deviation.getDateOfIdentification().getTime()));
            pstmt.setTime(6, deviation.getTimeOfIdentification());
            pstmt.setDate(7, new java.sql.Date(deviation.getDateOfInitiation().getTime()));
            pstmt.setString(8, deviation.getDocumentDetails());
            pstmt.setString(9, deviation.getFileAttachments());
            pstmt.setBoolean(10, deviation.isImpactOnBatches());
            pstmt.setBoolean(11, deviation.isImpactOnOtherBatches());
            pstmt.setString(12, deviation.getRiskAssessmentProduct());
            pstmt.setString(13, deviation.getRiskAssessmentFacility());
            pstmt.setString(14, deviation.getRiskAssessmentEquipment());
            pstmt.setString(15, deviation.getRiskAssessmentOthers());
            pstmt.setString(16, deviation.getJustificationForDelay());
            pstmt.setString(17, deviation.getStatus().name());
            pstmt.setInt(18, deviation.getInitiatedByUserId());
            pstmt.setInt(19, deviation.getDepartmentId());
            pstmt.setString(20, deviation.getRemarks());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                keySet = pstmt.getGeneratedKeys();
                if (keySet.next()) {
                    return keySet.getInt(1);
                }
            }
            return null;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error inserting deviation", ex);
            return null;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    // Update an existing Deviation
    public Boolean updateDeviation(Deviation deviation) {
        String sql = "UPDATE public.deviations SET title = ?, description = ?, " +
                "event_related_type = ?::event_related_enum, " +
                "date_of_occurrence = ?, date_of_identification = ?, time_of_identification = ?, " +
                "date_of_initiation = ?, document_details = ?, file_attachments = ?, " +
                "impact_on_batches = ?, impact_on_other_batches = ?, risk_assessment_product = ?, " +
                "risk_assessment_facility = ?, risk_assessment_equipment = ?, risk_assessment_others = ?, " +
                "justification_for_delay = ?, status = ?::deviation_status, initiated_by_user_id = ?, " +
                "department_id = ?, remarks = ?, updated_at = ? " +
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deviation.getTitle());
            pstmt.setString(2, deviation.getDescription());
            pstmt.setString(3, deviation.getEventRelatedType().name());
            pstmt.setDate(4, new java.sql.Date(deviation.getDateOfOccurrence().getTime()));
            pstmt.setDate(5, new java.sql.Date(deviation.getDateOfIdentification().getTime()));
            pstmt.setTime(6, deviation.getTimeOfIdentification());
            pstmt.setDate(7, new java.sql.Date(deviation.getDateOfInitiation().getTime()));
            pstmt.setString(8, deviation.getDocumentDetails());
            pstmt.setString(9, deviation.getFileAttachments());
            pstmt.setBoolean(10, deviation.isImpactOnBatches());
            pstmt.setBoolean(11, deviation.isImpactOnOtherBatches());
            pstmt.setString(12, deviation.getRiskAssessmentProduct());
            pstmt.setString(13, deviation.getRiskAssessmentFacility());
            pstmt.setString(14, deviation.getRiskAssessmentEquipment());
            pstmt.setString(15, deviation.getRiskAssessmentOthers());
            pstmt.setString(16, deviation.getJustificationForDelay());
            pstmt.setString(17, deviation.getStatus().name());
            pstmt.setInt(18, deviation.getInitiatedByUserId());
            pstmt.setInt(19, deviation.getDepartmentId());
            pstmt.setString(20, deviation.getRemarks());
            pstmt.setTimestamp(21, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(22, deviation.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating deviation", ex);
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }

    // Delete a Deviation
    public boolean deleteDeviation(int id) {
        String sql = "DELETE FROM public.deviations WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseUtility.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting deviation", ex);
            return false;
        } finally {
            DatabaseUtility.disconnect(conn);
        }
    }
}