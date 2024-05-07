package dao;

import model.Enums;
import model.Investigation;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import dto.InvestigationCapaFormDTO;
import dto.PerformRCADTO;

public class InvestigationDAO {
	private static final Logger LOGGER = Logger.getLogger(InvestigationDAO.class.getName());

	public Integer createInvestigation(Investigation investigation) {
		String sql = "INSERT INTO public.investigations (title, description, deviation_id, lead_investigator_id, methodology, status, started_at, closed_at, created_at, updated_at) VALUES (?, ?, ?, ?, ?::investigation_methodology_enum, ?::investigation_status_enum, ?, ?, ?, ?) RETURNING id";
		try (Connection conn = DatabaseUtility.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, investigation.getTitle());
			pstmt.setString(2, investigation.getDescription());
			pstmt.setInt(3, investigation.getDeviationId());
			pstmt.setInt(4, investigation.getLeadInvestigatorId());
			pstmt.setString(5, investigation.getMethodology().name());
			pstmt.setString(6, investigation.getStatus().name());
			pstmt.setTimestamp(7, investigation.getStartedAt());
			pstmt.setTimestamp(8, investigation.getClosedAt());
			pstmt.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // Created at
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
		try (Connection conn = DatabaseUtility.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
		try (Connection conn = DatabaseUtility.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
		try (Connection conn = DatabaseUtility.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

	// Initiate Investigation and capa workflow
	public void submitInvestigationCapaForm(InvestigationCapaFormDTO formDTO) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		try {
			connection.setAutoCommit(false);

			if (formDTO.isInvestigationRequired()) {
				handleInvestigationWorkflow(formDTO);
			}

			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {
			connection.setAutoCommit(true);
		}
	}

	private void handleInvestigationWorkflow(InvestigationCapaFormDTO formDTO) throws SQLException {
		// Insert into investigations table
		Connection connection = DatabaseUtility.connect();
		String investigationInsertSql = "INSERT INTO investigations (deviation_id, description, started_at) VALUES (?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(investigationInsertSql,
				Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, Integer.parseInt(formDTO.getDeviationId()));
			ps.setString(2, formDTO.getDescriptionOfInvestigation());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();

			// Get the generated investigation ID
			int investigationId;
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					investigationId = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Failed to retrieve last inserted ID");
				}
			}

			// Insert into investigation_investigators table
			String investigatorInsertSql = "INSERT INTO investigation_investigators (investigation_id, investigator_id) VALUES (?, ?)";
			try (PreparedStatement psInv = connection.prepareStatement(investigatorInsertSql)) {
				for (Integer investigatorId : formDTO.getInvestigatorIds()) {
					psInv.setInt(1, investigationId);
					psInv.setInt(2, investigatorId);
					psInv.executeUpdate();
				}
			}
		}

		// Update deviations table
		String deviationUpdateSql = "UPDATE deviations SET investigation_required = ?, status = ?::deviation_status WHERE id = ?";
		try (PreparedStatement ps = connection.prepareStatement(deviationUpdateSql)) {
			ps.setBoolean(1, true);
			ps.setString(2, Enums.DeviationStatus.PERFORMING_RCA.name());
			ps.setInt(3, Integer.parseInt(formDTO.getDeviationId()));
			ps.executeUpdate();
		}
	}

	// RCA
	public void performRCA(PerformRCADTO rcaDTO) throws SQLException {
		int investigationId = rcaDTO.getInvestigationId();

		if (rcaDTO.getInvestigationTool().equals(Enums.InvestigationMethodologyEnum.FIVE_WHY.name())) {
			performFiveWhys(investigationId, rcaDTO);
		} else if (rcaDTO.getInvestigationTool().equals(Enums.InvestigationMethodologyEnum.ADKOM.name())) {
			performADKOM(investigationId, rcaDTO);
		}

		updateDeviationStatus(rcaDTO.getInvestigationId());
	}

	private void performFiveWhys(int investigationId, PerformRCADTO rcaDTO) throws SQLException {
		insertFiveWhysStep(investigationId, 1, rcaDTO.getWhyQuestion1(), rcaDTO.getWhyAnswer1());
		insertFiveWhysStep(investigationId, 2, rcaDTO.getWhyQuestion2(), rcaDTO.getWhyAnswer2());
		insertFiveWhysStep(investigationId, 3, rcaDTO.getWhyQuestion3(), rcaDTO.getWhyAnswer3());
		insertFiveWhysStep(investigationId, 4, rcaDTO.getWhyQuestion4(), rcaDTO.getWhyAnswer4());
		insertFiveWhysStep(investigationId, 5, rcaDTO.getWhyQuestion5(), rcaDTO.getWhyAnswer5());
	}

	private void insertFiveWhysStep(int investigationId, int stepNumber, String question, String answer)
			throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String sql = "INSERT INTO five_whys_steps (investigation_id, why_number, question, answer) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, investigationId);
			statement.setInt(2, stepNumber);
			statement.setString(3, question);
			statement.setString(4, answer);
			statement.executeUpdate();
		}
	}

	private void performADKOM(int investigationId, PerformRCADTO rcaDTO) throws SQLException {
		insertADKOMStep(investigationId, "ABILITY", rcaDTO.getAbilityAssessment(), rcaDTO.getAbilityResult());
		insertADKOMStep(investigationId, "DIRECTION", rcaDTO.getDirectionAssessment(), rcaDTO.getDirectionResult());
		insertADKOMStep(investigationId, "KNOWLEDGE", rcaDTO.getKnowledgeAssessment(), rcaDTO.getKnowledgeResult());
		insertADKOMStep(investigationId, "OPPORTUNITY", rcaDTO.getOpportunityAssessment(),
				rcaDTO.getOpportunityResult());
		insertADKOMStep(investigationId, "MOTIVATION", rcaDTO.getMotivationAssessment(), rcaDTO.getMotivationResult());
	}

	private void insertADKOMStep(int investigationId, String step, String assessment, Boolean result)
			throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String sql = "INSERT INTO adkom_steps (investigation_id, step, assessment, result) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, investigationId);
			statement.setString(2, step);
			statement.setString(3, assessment);
			if (result != null) {
				statement.setBoolean(4, result);
			} else {
				statement.setNull(4, java.sql.Types.BOOLEAN);
			}
			statement.executeUpdate();
		}
	}

	private void updateDeviationStatus(Integer deviationId) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String sql = "UPDATE deviations SET status = ?::deviation_status WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, Enums.DeviationStatus.CAPA_INITIATED.name());
			statement.setInt(2, deviationId);
			statement.executeUpdate();
		}
	}
}