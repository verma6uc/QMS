package dao;

import model.Notification;
import model.Enums;
import utils.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationDAO {

    private static final Logger LOGGER = Logger.getLogger(NotificationDAO.class.getName());

    public int createNotification(Notification notification) {
        String sql = "INSERT INTO public.notifications (user_id, title, message, status, created_at, updated_at) VALUES (?, ?, ?, ?::notification_status, ?, ?) RETURNING id;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notification.getUserId());
            stmt.setString(2, notification.getTitle());
            stmt.setString(3, notification.getMessage());
            stmt.setString(4, notification.getStatus().name());
            stmt.setTimestamp(5, notification.getCreatedAt());
            stmt.setTimestamp(6, notification.getUpdatedAt());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating notification", e);
        }
        return -1;
    }

    public Notification getNotificationById(int id) {
        String sql = "SELECT * FROM public.notifications WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractNotification(rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving notification by ID", e);
        }
        return null;
    }

    public boolean updateNotification(Notification notification) {
        String sql = "UPDATE public.notifications SET user_id = ?, title = ?, message = ?, status = ?::notification_status, updated_at = ? WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notification.getUserId());
            stmt.setString(2, notification.getTitle());
            stmt.setString(3, notification.getMessage());
            stmt.setString(4, notification.getStatus().name());
            stmt.setTimestamp(5, notification.getUpdatedAt());
            stmt.setInt(6, notification.getId());

            int updatedRows = stmt.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating notification", e);
        }
        return false;
    }

    public boolean deleteNotification(int id) {
        String sql = "DELETE FROM public.notifications WHERE id = ?;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int deletedRows = stmt.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting notification", e);
        }
        return false;
    }

    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM public.notifications;";
        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                notifications.add(extractNotification(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all notifications", e);
        }
        return notifications;
    }

    private Notification extractNotification(ResultSet rs) throws SQLException {
        Notification notification = new Notification();
        notification.setId(rs.getInt("id"));
        notification.setUserId(rs.getInt("user_id"));
        notification.setTitle(rs.getString("title"));
        notification.setMessage(rs.getString("message"));
        notification.setStatus(Enums.NotificationStatus.valueOf(rs.getString("status")));
        notification.setCreatedAt(rs.getTimestamp("created_at"));
        notification.setUpdatedAt(rs.getTimestamp("updated_at"));
        return notification;
    }
}