package dao;

import model.QaDeviationRiskAssessment;
import model.Enums;
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

public class QaDeviationRiskAssessmentDAO {

    private static final Logger LOGGER = Logger.getLogger(QaDeviationRiskAssessmentDAO.class.getName());

    public Integer createQaDeviationRiskAssessment(QaDeviationRiskAssessment assessment) {
        String query = "INSERT INTO public.qa_deviation_risk_assessments(deviation_id, factor_type, justification, score, created_at, updated_at) VALUES (?, ?::risk_factor_type, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, assessment.getDeviationId());
            pstmt.setString(2, assessment.getFactorType().name());
            pstmt.setString(3, assessment.getJustification());
            pstmt.setInt(4, assessment.getScore());
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while inserting the qa_deviation_risk_assessment", ex);
        }
        return null;
    }

    public boolean updateQaDeviationRiskAssessment(QaDeviationRiskAssessment assessment) {
        String query = "UPDATE public.qa_deviation_risk_assessments SET factor_type = ?::risk_factor_type, justification = ?, score = ?, updated_at = ? WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, assessment.getFactorType().name());
            pstmt.setString(2, assessment.getJustification());
            pstmt.setInt(3, assessment.getScore());
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(5, assessment.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while updating the qa_deviation_risk_assessment", ex);
        }
        return false;
    }

    public boolean deleteQaDeviationRiskAssessment(int id) {
        String query = "DELETE FROM public.qa_deviation_risk_assessments WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while deleting the qa_deviation_risk_assessment", ex);
        }
        return false;
    }

    public QaDeviationRiskAssessment getQaDeviationRiskAssessmentById(int id) {
        String query = "SELECT * FROM public.qa_deviation_risk_assessments WHERE id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToQaDeviationRiskAssessment(rs);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while retrieving the qa_deviation_risk_assessment", ex);
        }
        return null;
    }

    private QaDeviationRiskAssessment mapToQaDeviationRiskAssessment(ResultSet rs) throws SQLException {
        QaDeviationRiskAssessment assessment = new QaDeviationRiskAssessment();
        assessment.setId(rs.getInt("id"));
        assessment.setDeviationId(rs.getInt("deviation_id"));
        assessment.setFactorType(Enums.RiskFactorType.valueOf(rs.getString("factor_type")));
        assessment.setJustification(rs.getString("justification"));
        assessment.setScore(rs.getInt("score"));
        assessment.setCreatedAt(rs.getTimestamp("created_at"));
        assessment.setUpdatedAt(rs.getTimestamp("updated_at"));
        return assessment;
    }
}