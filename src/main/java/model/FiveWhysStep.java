package model;

import java.sql.Timestamp;

/**
 * Represents a step in a Five Whys investigation.
 */
public class FiveWhysStep {

    private int id;
    private int investigationId;
    private int whyNumber;
    private String cause;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the Five Whys step.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the Five Whys step.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the associated investigation.
     *
     * @return the investigation ID
     */
    public int getInvestigationId() {
        return investigationId;
    }

    /**
     * Sets the ID of the associated investigation.
     *
     * @param investigationId the investigation ID to set
     */
    public void setInvestigationId(int investigationId) {
        this.investigationId = investigationId;
    }

    /**
     * Gets the "why" number of the step.
     *
     * @return the why number
     */
    public int getWhyNumber() {
        return whyNumber;
    }

    /**
     * Sets the "why" number of the step.
     *
     * @param whyNumber the why number to set
     */
    public void setWhyNumber(int whyNumber) {
        this.whyNumber = whyNumber;
    }

    /**
     * Gets the identified cause in this step.
     *
     * @return the cause
     */
    public String getCause() {
        return cause;
    }

    /**
     * Sets the identified cause in this step.
     *
     * @param cause the cause to set
     */
    public void setCause(String cause) {
        this.cause = cause;
    }

    /**
     * Gets the description of the step.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the step.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the timestamp of when the step was created.
     *
     * @return the creation timestamp
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp of when the step was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp of when the step was last updated.
     *
     * @return the update timestamp
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp of when the step was last updated.
     *
     * @param updatedAt the update timestamp to set
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "FiveWhysStep{" +
                "id=" + id +
                ", investigationId=" + investigationId +
                ", whyNumber=" + whyNumber +
                ", cause='" + cause + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}