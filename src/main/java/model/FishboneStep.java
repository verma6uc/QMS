package model;

import java.sql.Timestamp;

/**
 * Represents a fishbone step in an investigation.
 */
public class FishboneStep {

    private int id;
    private int investigationId;
    private String category;
    private String cause;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the fishbone step.
     *
     * @return The ID of the fishbone step.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the fishbone step.
     *
     * @param id The ID of the fishbone step.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the investigation associated with the fishbone step.
     *
     * @return The ID of the investigation.
     */
    public int getInvestigationId() {
        return investigationId;
    }

    /**
     * Sets the ID of the investigation associated with the fishbone step.
     *
     * @param investigationId The ID of the investigation.
     */
    public void setInvestigationId(int investigationId) {
        this.investigationId = investigationId;
    }

    /**
     * Gets the category of the fishbone step.
     *
     * @return The category of the fishbone step.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the fishbone step.
     *
     * @param category The category of the fishbone step.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the cause identified in the fishbone step.
     *
     * @return The cause identified in the fishbone step.
     */
    public String getCause() {
        return cause;
    }

    /**
     * Sets the cause identified in the fishbone step.
     *
     * @param cause The cause identified in the fishbone step.
     */
    public void setCause(String cause) {
        this.cause = cause;
    }

    /**
     * Gets the description of the fishbone step.
     *
     * @return The description of the fishbone step.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the fishbone step.
     *
     * @param description The description of the fishbone step.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the timestamp when the fishbone step was created.
     *
     * @return The timestamp when the fishbone step was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the fishbone step was created.
     *
     * @param createdAt The timestamp when the fishbone step was created.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the fishbone step was last updated.
     *
     * @return The timestamp when the fishbone step was last updated.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the fishbone step was last updated.
     *
     * @param updatedAt The timestamp when the fishbone step was last updated.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "FishboneStep{" +
                "id=" + id +
                ", investigationId=" + investigationId +
                ", category='" + category + '\'' +
                ", cause='" + cause + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}