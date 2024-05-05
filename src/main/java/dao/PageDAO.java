package dao;

import model.Page;
import utils.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PageDAO {

    private static final Logger LOGGER = Logger.getLogger(PageDAO.class.getName());

    public Optional<Integer> createPage(Page page) {
        String sql = "INSERT INTO public.pages (name, slug, created_at, updated_at) VALUES (?, ?, ?, ?) RETURNING page_id;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, page.getName());
            pstmt.setString(2, page.getSlug());
            pstmt.setTimestamp(3, page.getCreatedAt());
            pstmt.setTimestamp(4, page.getUpdatedAt());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(rs.getInt("page_id"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to create page", e);
        }
        return Optional.empty();
    }

    public Optional<Page> getPageById(int pageId) {
        String sql = "SELECT * FROM public.pages WHERE page_id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pageId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Page page = new Page();
                page.setPageId(rs.getInt("page_id"));
                page.setName(rs.getString("name"));
                page.setSlug(rs.getString("slug"));
                page.setCreatedAt(rs.getTimestamp("created_at"));
                page.setUpdatedAt(rs.getTimestamp("updated_at"));
                return Optional.of(page);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get page by ID", e);
        }
        return Optional.empty();
    }

    public boolean updatePage(Page page) {
        String sql = "UPDATE public.pages SET name = ?, slug = ?, updated_at = ? WHERE page_id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, page.getName());
            pstmt.setString(2, page.getSlug());
            pstmt.setTimestamp(3, page.getUpdatedAt());
            pstmt.setInt(4, page.getPageId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to update page", e);
        }
        return false;
    }

    public boolean deletePage(int pageId) {
        String sql = "DELETE FROM public.pages WHERE page_id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pageId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete page", e);
        }
        return false;
    }
}