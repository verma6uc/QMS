package dao;

import model.Deviation;
import model.Enums;
import model.Enums.DeviationStatus;
import utils.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dto.ApprovalByQADTO;
import dto.CrossFunctionalDeviationReviewDTO;
import dto.DepartmentalDeviationReviewDTO;
import dto.DeviationInitiateDTO;

public class DeviationDAO {

	private static final Logger LOGGER = Logger.getLogger(DeviationDAO.class.getName());

	// Insert a new Deviation
	public Integer insertDeviation(Deviation deviation) {
		String sql = "INSERT INTO public.deviations (title, description, event_related_type, "
				+ "date_of_occurrence, date_of_identification, time_of_identification, "
				+ "date_of_initiation, document_details, file_attachments, "
				+ "impact_on_batches, impact_on_other_batches, risk_assessment_product, "
				+ "risk_assessment_facility, risk_assessment_equipment, risk_assessment_others, "
				+ "justification_for_delay, status, initiated_by_user_id, department_id, remarks) "
				+ "VALUES (?, ?, ?::event_related_enum, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::deviation_status, ?, ?, ?)";

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
		String sql = "UPDATE public.deviations SET title = ?, description = ?, "
				+ "event_related_type = ?::event_related_enum, "
				+ "date_of_occurrence = ?, date_of_identification = ?, time_of_identification = ?, "
				+ "date_of_initiation = ?, document_details = ?, file_attachments = ?, "
				+ "impact_on_batches = ?, impact_on_other_batches = ?, risk_assessment_product = ?, "
				+ "risk_assessment_facility = ?, risk_assessment_equipment = ?, risk_assessment_others = ?, "
				+ "justification_for_delay = ?, status = ?::deviation_status, initiated_by_user_id = ?, "
				+ "department_id = ?, remarks = ?, updated_at = ? " + "WHERE id = ?";

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

	public void initiateDeviation(DeviationInitiateDTO dto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// Start a transaction to ensure data consistency
			conn = DatabaseUtility.connect();
			conn.setAutoCommit(false);
			// 1. Insert into deviations table
			int deviationId = insertDeviationRecord(dto);

			// 2. Handle event-specific details based on eventRelatedType
			switch (dto.getEventRelatedType()) {
			case "PRODUCT":
				insertDeviationProduct(deviationId, dto.getProductId());
				insertDeviationBatches(deviationId, dto.getBatchIds());
				break;
			case "MATERIAL":
				insertDeviationMaterialLot(deviationId, dto.getMaterialId(), dto.getLotNumber());
				break;
			case "EQUIPMENT":
				insertDeviationEquipment(deviationId, dto.getEquipmentId());
				break;
			case "DOCUMENT":
				insertDeviationDocument(deviationId, dto.getDocumentId());
				break;
			default:
				break;
			}

			// If all successful, commit the transaction
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
		}
	}

	private int insertDeviationRecord(DeviationInitiateDTO dto) throws SQLException {
		Connection conn = DatabaseUtility.connect();
		String sql = "INSERT INTO deviations (date_of_occurrence, date_of_identification, time_of_identification, "
				+ "justification_for_delay, event_related_type, description,initiated_by_user_id,deviation_root_cause,immediate_corrective_action,risk_assessment,standard_proceduure) VALUES (?, ?, ?, ?, ?::event_related_enum, ?,?,?,?,?,?) RETURNING id"; // Assuming
		// //
		// key

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			// Set parameters using dto.get...() and handle potential null values
			java.sql.Date sqlDateOfOccurrence = java.sql.Date.valueOf(dto.getDateOfOccurrence());
			java.sql.Date sqlDateOfIdentification = java.sql.Date.valueOf(dto.getDateOfIdentification());

			// Ensure time string is in the format HH:mm:ss
			String timeOfIdentification = dto.getTimeOfIdentification();
			if (!timeOfIdentification.contains(":")) {
				throw new IllegalArgumentException("Time must be in the format HH:mm:ss");
			}
			if (timeOfIdentification.length() == 5) { // HH:mm
				timeOfIdentification += ":00"; // Assume 00 seconds if not specified
			}

			java.sql.Time sqlTimeOfIdentification = java.sql.Time.valueOf(timeOfIdentification);

			stmt.setDate(1, sqlDateOfOccurrence);
			stmt.setDate(2, sqlDateOfIdentification);
			stmt.setTime(3, sqlTimeOfIdentification);
			stmt.setString(4, dto.getJustificationForDelay());
			stmt.setString(5, dto.getEventRelatedType());
			stmt.setString(6, dto.getDescription());
			stmt.setString(7, dto.getDeviationRootCause());
			stmt.setString(8, dto.getImmediateCorrectiveAction());
			stmt.setString(9, dto.getRiskAssessment());
			stmt.setString(10, dto.getStandardProcedure());
			stmt.setInt(11, dto.getInitiatedByUserId());
			// Execute and retrieve the generated deviationId
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("id");
				} else {
					throw new SQLException("Failed to retrieve generated deviation ID.");
				}
			}
		}
	}

	private void insertDeviationProduct(int deviationId, Integer productId) throws SQLException {
		Connection conn = DatabaseUtility.connect();
		String sql = "INSERT INTO deviation_products (deviation_id, product_id) VALUES (?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, deviationId);
			if (productId != null) {
				stmt.setInt(2, productId);
			} else {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}
			stmt.executeUpdate();
		}
	}

	private void insertDeviationMaterialLot(int deviationId, Integer materialId, String lotNumber) throws SQLException {
		Connection conn = DatabaseUtility.connect();
		String sql = "INSERT INTO deviation_material_lots (deviation_id, material_id, lot_number) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, deviationId);
			if (materialId != null) {
				stmt.setInt(2, materialId);
			} else {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}
			stmt.setString(3, lotNumber);
			stmt.executeUpdate();
		}
	}

	private void insertDeviationEquipment(int deviationId, Integer equipmentId) throws SQLException {
		Connection conn = DatabaseUtility.connect();
		String sql = "INSERT INTO deviation_equipments (deviation_id, equipment_id) VALUES (?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, deviationId);
			if (equipmentId != null) {
				stmt.setInt(2, equipmentId);
			} else {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}
			stmt.executeUpdate();
		}
	}

	private void insertDeviationDocument(int deviationId, Integer documentId) throws SQLException {
		Connection conn = DatabaseUtility.connect();
		String sql = "INSERT INTO deviation_documents (deviation_id, document_id) VALUES (?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, deviationId);
			if (documentId != null) {
				stmt.setInt(2, documentId);
			} else {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}
			stmt.executeUpdate();
		}
	}

	private void insertDeviationBatches(int deviationId, List<Integer> batchIds) throws SQLException {
		Connection conn = DatabaseUtility.connect();

		String sql = "INSERT INTO deviation_batches (deviation_id, batch_id) VALUES (?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, deviationId);
			for (Integer batchId : batchIds) {
				if (batchId != null) {
					stmt.setInt(2, batchId);
				} else {
					stmt.setNull(2, java.sql.Types.INTEGER);
				}
				stmt.addBatch();
			}
			stmt.executeBatch();
		}
	}

	public void updateDeviationStatusAndAddComment(DepartmentalDeviationReviewDTO dto) throws SQLException {
		Connection conn = DatabaseUtility.connect();
		try {
			conn.setAutoCommit(false); // Start transaction for atomicity

			// 1. Update deviation status
			String updateDeviationSql = "UPDATE deviations SET status = ?::deviation_status, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
			PreparedStatement updateStmt = conn.prepareStatement(updateDeviationSql);
			updateStmt.setString(1, dto.getDecision());
			updateStmt.setInt(2, dto.getDeviationId());
			updateStmt.executeUpdate();

			// 2. Insert review comments (always required)
			String insertCommentSql = "INSERT INTO comments (content, author_id, related_id, created_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
			PreparedStatement insertCommentStmt = conn.prepareStatement(insertCommentSql);
			insertCommentStmt.setString(1, dto.getReviewComments());
			// Assuming you have a mechanism to get the current user's ID:
			insertCommentStmt.setInt(2, dto.getAuthorId()); // Replace with actual user ID retrieval logic
			insertCommentStmt.setInt(3, dto.getDeviationId());
			insertCommentStmt.executeUpdate();

			// 3. Insert justification (if needed)
			if (dto.getDecision().equals(DeviationStatus.REJECTED.name())
					|| dto.getDecision().equals(DeviationStatus.MORE_INFO.name())) {
				String insertJustificationSql = "INSERT INTO comments (content, author_id, related_id, created_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
				PreparedStatement insertJustificationStmt = conn.prepareStatement(insertJustificationSql);
				insertJustificationStmt.setString(1, dto.getJustificationForDecision());
				insertJustificationStmt.setInt(2, dto.getAuthorId()); // Replace with actual user ID retrieval logic
				insertJustificationStmt.setInt(3, dto.getDeviationId());
				insertJustificationStmt.executeUpdate();
			}

			conn.commit(); // Commit transaction if all steps succeed
		} catch (SQLException e) {
			conn.rollback(); // Rollback on error to maintain data consistency
			throw e;
		} finally {
			conn.setAutoCommit(true); // Restore auto-commit
			conn.close();
		}
	}

	public void submitDecision(CrossFunctionalDeviationReviewDTO reviewDTO, int deviationId, int userId)
			throws SQLException {
		// Update the status of the deviation
		updateDeviationStatus(reviewDTO.getDecision(), deviationId, reviewDTO.getJustification());

		// Log the comments
		logComments(reviewDTO.getComments(), deviationId, userId);
		logComments(reviewDTO.getJustification(), deviationId, userId);

		// Update department and user/user group associations (assuming separate tables
		// exist)
		if (reviewDTO.getDecision().equalsIgnoreCase("APPROVED_BY_QA")) {
			updateDepartmentAssociations(deviationId, reviewDTO.getDepartment());
			updateUserGroupAssociations(deviationId, reviewDTO.getUserGroup());
		}
	}

	private void updateDeviationStatus(String decision, int deviationId, String justification) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String sql = "UPDATE deviations SET status = ?::deviation_status , updated_at = ? WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, decision);
			statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			statement.setInt(3, deviationId);
			statement.executeUpdate();
		}
	}

	private void logComments(String comments, int deviationId, int userId) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		if (comments != null && !comments.isEmpty()) {
			String sql = "INSERT INTO comments (content, author_id, related_id, created_at) VALUES (?,  ?, ?, ?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, comments);
				statement.setInt(2, userId);
				statement.setInt(3, deviationId);
				statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
				statement.executeUpdate();
			}
		}
	}

	private void updateDepartmentAssociations(int deviationId, List<Integer> departmentIds) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		// Assuming a table 'deviation_departments' exists with columns 'deviation_id'
		// and 'department_id'
		String sqlDelete = "DELETE FROM deviation_departments WHERE deviation_id = ?";
		String sqlInsert = "INSERT INTO deviation_departments (deviation_id, department_id) VALUES (?, ?)";

		try (PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete);
				PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {

			deleteStatement.setInt(1, deviationId);
			deleteStatement.executeUpdate();

			for (Integer departmentId : departmentIds) {
				insertStatement.setInt(1, deviationId);
				insertStatement.setInt(2, departmentId);
				insertStatement.executeUpdate();
			}
		}
	}

	private void updateUserGroupAssociations(int deviationId, List<Integer> userGroupIds) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		// Assuming a table 'deviation_user_groups' exists with columns 'deviation_id'
		// and 'user_group_id'
		String sqlDelete = "DELETE FROM deviation_reviewers WHERE deviation_id = ?";
		String sqlInsert = "INSERT INTO deviation_reviewers (deviation_id, reviewer_id) VALUES (?, ?)";

		try (PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete);
				PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {

			deleteStatement.setInt(1, deviationId);
			deleteStatement.executeUpdate();

			for (Integer userGroupId : userGroupIds) {
				insertStatement.setInt(1, deviationId);
				insertStatement.setInt(2, userGroupId);
				insertStatement.executeUpdate();
			}
		}
	}

//	For Approved by QA
	public void updateDeviationStatus(ApprovalByQADTO approvalByQADTO) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String updateQuery = "UPDATE public.deviations SET status = ?::deviation_status, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
			statement.setString(1, approvalByQADTO.getDecision());
			statement.setInt(2, approvalByQADTO.getDeviationId());
			statement.executeUpdate();
		}
	}

	public void insertComments(ApprovalByQADTO approvalByQADTO) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String insertQuery = "INSERT INTO public.comments (\"content\", author_id, related_id, created_at) VALUES (?, ?,  ?, CURRENT_TIMESTAMP)";
		try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
			statement.setString(1, approvalByQADTO.getApproverComments());
			if (approvalByQADTO.getUserId() != null) {
				statement.setInt(2, approvalByQADTO.getUserId());
			} else {
				statement.setNull(2, java.sql.Types.INTEGER);
			}
			statement.setInt(3, approvalByQADTO.getDeviationId());
			statement.executeUpdate();
		}
	}

	public void insertJustification(ApprovalByQADTO approvalByQADTO) throws SQLException {
		Connection connection = DatabaseUtility.connect();
		String insertQuery = "INSERT INTO public.comments (\"content\", author_id, related_id, created_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
		try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
			statement.setString(1, approvalByQADTO.getJustification());
			if (approvalByQADTO.getUserId() != null) {
				statement.setInt(2, approvalByQADTO.getUserId());
			} else {
				statement.setNull(2, java.sql.Types.INTEGER);
			}
			statement.setInt(3, approvalByQADTO.getDeviationId());
			statement.executeUpdate();
		}
	}
}