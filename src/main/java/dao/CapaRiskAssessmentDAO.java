package dao;

import model.CapaRiskAssessment;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.Logger;

public class CapaRiskAssessmentDAO {

    private static final Logger LOGGER = Logger.getLogger(CapaRiskAssessmentDAO.class.getName());

    public Integer createCapaRiskAssessment(CapaRiskAssessment capaRiskAssessment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer generatedId = null;

        String sql = "INSERT INTO public.capa_risk_assessments (capa_id, assessed_by, assessment_date, risk_description, risk_score, mitigation_plan, residual_risk_score, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try {
            connection = DatabaseUtility.connect();
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, capaRiskAssessment.getCapaId());
            preparedStatement.setInt(2, capaRiskAssessment.getAssessedBy());
            preparedStatement.setDate(3, new java.sql.Date(capaRiskAssessment.getAssessmentDate().getTime()));
            preparedStatement.setString(4, capaRiskAssessment.getRiskDescription());
            preparedStatement.setInt(5, capaRiskAssessment.getRiskScore());
            preparedStatement.setString(6, capaRiskAssessment.getMitigationPlan());
            preparedStatement.setInt(7, capaRiskAssessment.getResidualRiskScore());
            preparedStatement.setTimestamp(8, capaRiskAssessment.getCreatedAt());
            preparedStatement.setTimestamp(9, capaRiskAssessment.getUpdatedAt());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
            LOGGER.info("CapaRiskAssessment created successfully with ID: " + generatedId);
            return generatedId;
        } catch (SQLException e) {
            LOGGER.severe("Error creating CapaRiskAssessment: " + e.getMessage());
            return null;
        } finally {
            DatabaseUtility.disconnect(connection);
        }
    }
}