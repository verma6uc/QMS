package dao;

import model.CapaFollowUp;
import utils.DatabaseUtility;
import java.sql.*;
import java.util.logging.Logger;

public class CapaFollowUpDAO {

    private static final String INSERT_CAPA_FOLLOW_UP_SQL = "INSERT INTO public.capa_follow_ups (capa_id, conducted_by, follow_up_date, follow_up_description, outcome, status) VALUES (?, ?, ?, ?, ?, ?::follow_up_status) RETURNING id;";
    private static final String UPDATE_CAPA_FOLLOW_UP_SQL = "UPDATE public.capa_follow_ups SET capa_id = ?, conducted_by = ?, follow_up_date = ?, follow_up_description = ?, outcome = ?, status = ?::follow_up_status, updated_at = CURRENT_TIMESTAMP WHERE id = ?;";
    private static final String DELETE_CAPA_FOLLOW_UP_SQL = "DELETE FROM public.capa_follow_ups WHERE id = ?;";

    private static final Logger logger = Logger.getLogger(CapaFollowUpDAO.class.getName());

    public int insertCapaFollowUp(CapaFollowUp capaFollowUp) {
        int generatedId = 0;

        try (Connection connection = DatabaseUtility.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAPA_FOLLOW_UP_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, capaFollowUp.getCapaId());
            preparedStatement.setInt(2, capaFollowUp.getConductedBy());
            preparedStatement.setDate(3, new java.sql.Date(capaFollowUp.getFollowUpDate().getTime()));
            preparedStatement.setString(4, capaFollowUp.getFollowUpDescription());
            preparedStatement.setString(5, capaFollowUp.getOutcome());
            preparedStatement.setString(6, capaFollowUp.getStatus().name());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        generatedId = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            logger.severe("SQL exception occurred during the insert of a CAPA follow-up: " + e.getMessage());
        }
        return generatedId;
    }

    public boolean updateCapaFollowUp(CapaFollowUp capaFollowUp) {
        boolean rowUpdated = false;

        try (Connection connection = DatabaseUtility.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAPA_FOLLOW_UP_SQL)) {

            preparedStatement.setInt(1, capaFollowUp.getCapaId());
            preparedStatement.setInt(2, capaFollowUp.getConductedBy());
            preparedStatement.setDate(3, new java.sql.Date(capaFollowUp.getFollowUpDate().getTime()));
            preparedStatement.setString(4, capaFollowUp.getFollowUpDescription());
            preparedStatement.setString(5, capaFollowUp.getOutcome());
            preparedStatement.setString(6, capaFollowUp.getStatus().name());
            preparedStatement.setInt(7, capaFollowUp.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.severe("SQL exception occurred during the update of a CAPA follow-up: " + e.getMessage());
        }
        return rowUpdated;
    }

    public boolean deleteCapaFollowUp(int id) {
        boolean rowDeleted = false;

        try (Connection connection = DatabaseUtility.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAPA_FOLLOW_UP_SQL)) {

            preparedStatement.setInt(1, id);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.severe("SQL exception occurred during the delete of a CAPA follow-up: " + e.getMessage());
        }
        return rowDeleted;
    }
}