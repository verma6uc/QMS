package dao;

import model.CapaAction;
import model.Enums.CapaActionStatus;
import utils.DatabaseUtility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CapaActionDAO {

	private static final Logger LOGGER = Logger.getLogger(CapaActionDAO.class.getName());

	public int createCapaAction(CapaAction capaAction) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String insertQuery = "INSERT INTO public.capa_actions (capa_id, actionee_id, action_description, status, due_date, completion_date, created_at, updated_at) VALUES (?, ?, ?, ?::capa_action_status, ?, ?, ?, ?) RETURNING id;";
		try {
			conn = DatabaseUtility.connect();
			stmt = conn.prepareStatement(insertQuery);
			stmt.setInt(1, capaAction.getCapaId());
			stmt.setInt(2, capaAction.getActioneeId());
			stmt.setString(3, capaAction.getActionDescription());
			stmt.setString(4, capaAction.getStatus().name());
			stmt.setDate(5, new java.sql.Date(capaAction.getDueDate().getTime()));
			if (capaAction.getCompletionDate() != null) {
				stmt.setDate(6, new java.sql.Date(capaAction.getCompletionDate().getTime()));
			} else {
				stmt.setNull(6, Types.DATE);
			}
			stmt.setTimestamp(7, capaAction.getCreatedAt());
			stmt.setTimestamp(8, capaAction.getUpdatedAt());

			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error creating CAPA Action", e);
		} finally {
			DatabaseUtility.disconnect(conn);
		}
		return -1;
	}

	public boolean updateCapaAction(CapaAction capaAction) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String updateQuery = "UPDATE public.capa_actions SET capa_id = ?, actionee_id = ?, action_description = ?, status = ?::capa_action_status, due_date = ?, completion_date = ?, updated_at = ? WHERE id = ?;";
		try {
			conn = DatabaseUtility.connect();
			stmt = conn.prepareStatement(updateQuery);
			stmt.setInt(1, capaAction.getCapaId());
			stmt.setInt(2, capaAction.getActioneeId());
			stmt.setString(3, capaAction.getActionDescription());
			stmt.setString(4, capaAction.getStatus().name());
			stmt.setDate(5, new java.sql.Date(capaAction.getDueDate().getTime()));
			if (capaAction.getCompletionDate() != null) {
				stmt.setDate(6, new java.sql.Date(capaAction.getCompletionDate().getTime()));
			} else {
				stmt.setNull(6, Types.DATE);
			}
			stmt.setTimestamp(7, capaAction.getUpdatedAt());
			stmt.setInt(8, capaAction.getId());

			int affectedRows = stmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error updating CAPA Action", e);
		} finally {
			DatabaseUtility.disconnect(conn);
		}
		return false;
	}

	public boolean deleteCapaAction(int capaActionId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String deleteQuery = "DELETE FROM public.capa_actions WHERE id = ?;";
		try {
			conn = DatabaseUtility.connect();
			stmt = conn.prepareStatement(deleteQuery);
			stmt.setInt(1, capaActionId);

			int affectedRows = stmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error deleting CAPA Action", e);
		} finally {
			DatabaseUtility.disconnect(conn);
		}
		return false;
	}
}