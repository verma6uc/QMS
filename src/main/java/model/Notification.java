package model;

import java.sql.Timestamp;

/**
 * Represents a notification entity.
 */
public class Notification {

    private int id;
    private String title;
    private String message;
    private Enums.NotificationStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int userId;

    public Notification() {
    }

    /**
     * Gets the ID of the notification.
     *
     * @return the notification ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the notification.
     *
     * @param id the notification ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the notification.
     *
     * @return the notification title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the notification.
     *
     * @param title the notification title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the message of the notification.
     *
     * @return the notification message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the notification.
     *
     * @param message the notification message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the status of the notification.
     *
     * @return the notification status
     */
    public Enums.NotificationStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the notification.
     *
     * @param status the notification status to set
     */
    public void setStatus(Enums.NotificationStatus status) {
        this.status = status;
    }

    /**
     * Gets the creation timestamp of the notification.
     *
     * @return the creation timestamp
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the notification.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the update timestamp of the notification.
     *
     * @return the update timestamp
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the update timestamp of the notification.
     *
     * @param updatedAt the update timestamp to set
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the ID of the user associated with the notification.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user associated with the notification.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", userId=" + userId +
                '}';
    }
}