package dao;

import model.QaDeviationRiskAssessment;
import model.Enums;
import model.Enums.DeviationStatus;
import utils.DatabaseUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dto.EvaluationDeviationByHeadDTO;

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
		try (Connection conn = DatabaseUtility.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
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
		try (Connection conn = DatabaseUtility.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
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
		try (Connection conn = DatabaseUtility.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
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

	public void saveAssessment(EvaluationDeviationByHeadDTO evaluationDto) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String sql = "INSERT INTO qa_deviation_risk_assessments (deviation_id, factor_type, justification, score, created_at, updated_at) VALUES (?, ?::risk_factor_type, ?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, evaluationDto.getDeviationId());
			statement.setString(2, Enums.RiskFactorType.PROBABILITY.name());
			statement.setString(3, evaluationDto.getProbabilityJustification());
			statement.setInt(4, evaluationDto.getProbabilityOfRecurrence());
			statement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
			statement.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
			statement.executeUpdate();

			if (evaluationDto.getAdditionalProcessingSteps() != null) {
				statement.clearParameters();
				statement.setInt(1, evaluationDto.getDeviationId());
				statement.setString(2, Enums.RiskFactorType.PROCESS_STEPS.name());
				statement.setString(3, evaluationDto.getStepsJustification());
				statement.setObject(4, evaluationDto.getAdditionalProcessingSteps(), java.sql.Types.INTEGER);
				statement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
				statement.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
				statement.executeUpdate();
			}

			if (evaluationDto.getMicrobiologicallyRelated() != null) {
				statement.clearParameters();
				statement.setInt(1, evaluationDto.getDeviationId());
				statement.setString(2, Enums.RiskFactorType.MICROBIOLOGICAL.name());
				statement.setString(3, evaluationDto.getMicrobiologicallyRelatedJustification());
				statement.setObject(4, evaluationDto.getMicrobiologicallyRelated(), java.sql.Types.INTEGER);
				statement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
				statement.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
				statement.executeUpdate();
			}

			if (evaluationDto.getProductCrossContamination() != null) {
				statement.clearParameters();
				statement.setInt(1, evaluationDto.getDeviationId());
				statement.setString(2, Enums.RiskFactorType.CROSS_CONTAMINATION.name());
				statement.setString(3, evaluationDto.getContaminationJustification());
				statement.setObject(4, evaluationDto.getProductCrossContamination(), java.sql.Types.INTEGER);
				statement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
				statement.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
				statement.executeUpdate();
			}

			if (evaluationDto.getProductImpact() != null) {
				statement.clearParameters();
				statement.setInt(1, evaluationDto.getDeviationId());
				statement.setString(2, Enums.RiskFactorType.PRODUCT_IMPACT.name());
				statement.setString(3, evaluationDto.getImpactJustification());
				statement.setObject(4, evaluationDto.getProductImpact(), java.sql.Types.INTEGER);
				statement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
				statement.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
				statement.executeUpdate();
			}

			if (evaluationDto.getComplexityOfInvestigation() != null) {
				statement.clearParameters();
				statement.setInt(1, evaluationDto.getDeviationId());
				statement.setString(2, Enums.RiskFactorType.INVESTIGATION_COMPLEXITY.name());
				statement.setString(3, evaluationDto.getComplexityJustification());
				statement.setObject(4, evaluationDto.getComplexityOfInvestigation(), java.sql.Types.INTEGER);
				statement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
				statement.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
				statement.executeUpdate();
			}

			if (evaluationDto.getCriticalWarrantedByQuality() != null) {
				statement.clearParameters();
				statement.setInt(1, evaluationDto.getDeviationId());
				statement.setString(2, Enums.RiskFactorType.QUALITY_CRITICAL.name());
				statement.setString(3, evaluationDto.getCriticalJustification());
				statement.setObject(4, evaluationDto.getCriticalWarrantedByQuality(), java.sql.Types.INTEGER);
				statement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
				statement.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
				statement.executeUpdate();
			}
		}
	}

	public void updateDeviationRiskCategoryAndClosureDate(Integer deviationId, int totalRiskScore, Date assessmentDate)
			throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String categoryUpdateSql = "UPDATE deviations SET risk_category = ? WHERE id = ?";
		String closureDateUpdateSql = "UPDATE deviations SET target_closure_date = ? WHERE id = ?";

		String riskCategory;
		if (totalRiskScore >= 0 && totalRiskScore <= 5) {
			riskCategory = "Minor";
		} else if (totalRiskScore >= 6 && totalRiskScore <= 10) {
			riskCategory = "Major";
		} else {
			riskCategory = "Critical";
		}

		try (PreparedStatement categoryStatement = connection.prepareStatement(categoryUpdateSql);
				PreparedStatement closureDateStatement = connection.prepareStatement(closureDateUpdateSql)) {

			// Update risk_category
			categoryStatement.setString(1, riskCategory);
			categoryStatement.setInt(2, deviationId);
			categoryStatement.executeUpdate();

			// Update closure_date based on risk_category
			long daysToAdd = 0;
			if ("Minor".equals(riskCategory)) {
				daysToAdd = 30;
			} else if ("Major".equals(riskCategory)) {
				daysToAdd = 15;
			} else if ("Critical".equals(riskCategory)) {
				daysToAdd = 7;
			}

			if (daysToAdd > 0) {
				long newClosureDateInMillis = assessmentDate.getTime() + daysToAdd * 24 * 60 * 60 * 1000;
				Date newClosureDate = new Date(newClosureDateInMillis);
				closureDateStatement.setDate(1, new java.sql.Date(newClosureDate.getTime()));
				closureDateStatement.setInt(2, deviationId);
				closureDateStatement.executeUpdate();
			}
		}
	}

	public void updateDeviationForRepeatedCase(int deviationId, String pastDescription, String accountableDepartment,
			Date pastClosureDate) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String sql = "UPDATE deviations SET past_description = ?, accountable_department = ?, past_closure_date = ? WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, pastDescription);
			statement.setString(2, accountableDepartment);

			if (pastClosureDate != null) {
				statement.setDate(3, new java.sql.Date(pastClosureDate.getTime()));
			} else {
				statement.setNull(3, java.sql.Types.DATE);
			}

			statement.setInt(4, deviationId);
			statement.executeUpdate();
		}
	}

	public void transitionToApprovalByQA(int deviationId) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String sql = "UPDATE deviations SET status = ?::deviation_status WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, DeviationStatus.APPROVED_BY_QA.name());
			statement.setInt(2, deviationId);
			statement.executeUpdate();
		}
	}
}