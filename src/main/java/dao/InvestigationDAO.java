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
			} else {
				handleCapaWorkflow(formDTO);
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

	private void handleCapaWorkflow(InvestigationCapaFormDTO formDTO) throws SQLException {
		// Insert into capas table
		Connection connection = DatabaseUtility.connect();
		String capaInsertSql = "INSERT INTO capas (deviation_id, description, created_at) VALUES (?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(capaInsertSql)) {
			ps.setInt(1, Integer.parseInt(formDTO.getDeviationId()));
			ps.setString(2, "CAPA initiated directly from deviation"); // Placeholder description
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
		}

		// Update deviations table
		String deviationUpdateSql = "UPDATE deviations SET status = ?::deviation_status WHERE id = ?";
		try (PreparedStatement ps = connection.prepareStatement(deviationUpdateSql)) {
			ps.setString(1, Enums.DeviationStatus.CAPA_INITIATED.name()); // Assuming a status for CAPA
			ps.setInt(2, Integer.parseInt(formDTO.getDeviationId()));
			ps.executeUpdate();
		}
	}
}