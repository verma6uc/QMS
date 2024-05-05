package dao;

import model.PageRole;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PageRoleDAO {
    private static final Logger LOGGER = Logger.getLogger(PageRoleDAO.class.getName());

    /**
     * Inserts a new PageRole into the database.
     * @param pageRole The PageRole to be inserted.
     * @return The id of the newly created page role if successful, otherwise -1.
     */
    public int insertPageRole(PageRole pageRole) {
        String query = "INSERT INTO public.page_roles (page_id, role_id, access_granted) VALUES (?, ?, ?) RETURNING page_id";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pageRole.getPageId());
            stmt.setInt(2, pageRole.getRoleId());
            stmt.setTimestamp(3, pageRole.getAccessGranted());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("page_id");
            }
        } catch (SQLException e) {
            LOGGER.severe("Error inserting page role: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Deletes a page role from the database.
     * @param pageId The page ID.
     * @param roleId The role ID.
     * @return True if the deletion is successful, False otherwise.
     */
    public boolean deletePageRole(int pageId, int roleId) {
        String query = "DELETE FROM public.page_roles WHERE page_id = ? AND role_id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pageId);
            stmt.setInt(2, roleId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.severe("Error deleting page role: " + e.getMessage());
        }
        return false;
    }

    /**
     * Updates an existing page role in the database.
     * @param pageRole The PageRole object containing updated fields.
     * @return True if the update is successful, False otherwise.
     */
    public boolean updatePageRole(PageRole pageRole) {
        String query = "UPDATE public.page_roles SET access_granted = ? WHERE page_id = ? AND role_id = ?";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTimestamp(1, pageRole.getAccessGranted());
            stmt.setInt(2, pageRole.getPageId());
            stmt.setInt(3, pageRole.getRoleId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.severe("Error updating page role: " + e.getMessage());
        }
        return false;
    }
}