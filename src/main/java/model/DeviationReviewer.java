package model;

import java.sql.Timestamp;

/**
 * Represents a deviation reviewer.
 */
public class DeviationReviewer {

    private int id;
    private int deviationId;
    private int reviewerId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the deviation reviewer.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the deviation reviewer.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the associated deviation.
     *
     * @return the deviation ID
     */
    public int getDeviationId() {
        return deviationId;
    }

    /**
     * Sets the ID of the associated deviation.
     *
     * @param deviationId the deviation ID to set
     */
    public void setDeviationId(int deviationId) {
        this.deviationId = deviationId;
    }

    /**
     * Gets the ID of the reviewer.
     *
     * @return the reviewer ID
     */
    public int getReviewerId() {
        return reviewerId;
    }

    /**
     * Sets the ID of the reviewer.
     *
     * @param reviewerId the reviewer ID to set
     */
    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return the creation timestamp
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the update timestamp.
     *
     * @return the update timestamp
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the update timestamp.
     *
     * @param updatedAt the update timestamp to set
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "DeviationReviewer{" +
                "id=" + id +
                ", deviationId=" + deviationId +
                ", reviewerId=" + reviewerId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}