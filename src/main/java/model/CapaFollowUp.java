package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents a follow-up action for a Corrective and Preventive Action (CAPA).
 */
public class CapaFollowUp {

    private int id;
    private int capaId;
    private int conductedBy;
    private Timestamp createdAt;
    private Date followUpDate;
    private String followUpDescription;
    private String outcome;
    private Enums.FollowUpStatus status;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the follow-up action.
     *
     * @return The ID of the follow-up action.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the follow-up action.
     *
     * @param id The ID of the follow-up action.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the associated CAPA.
     *
     * @return The ID of the associated CAPA.
     */
    public int getCapaId() {
        return capaId;
    }

    /**
     * Sets the ID of the associated CAPA.
     *
     * @param capaId The ID of the associated CAPA.
     */
    public void setCapaId(int capaId) {
        this.capaId = capaId;
    }

    /**
     * Gets the ID of the user who conducted the follow-up.
     *
     * @return The ID of the user who conducted the follow-up.
     */
    public int getConductedBy() {
        return conductedBy;
    }

    /**
     * Sets the ID of the user who conducted the follow-up.
     *
     * @param conductedBy The ID of the user who conducted the follow-up.
     */
    public void setConductedBy(int conductedBy) {
        this.conductedBy = conductedBy;
    }

    /**
     * Gets the timestamp when the follow-up action was created.
     *
     * @return The timestamp when the follow-up action was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the follow-up action was created.
     *
     * @param createdAt The timestamp when the follow-up action was created.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the date of the follow-up action.
     *
     * @return The date of the follow-up action.
     */
    public Date getFollowUpDate() {
        return followUpDate;
    }

    /**
     * Sets the date of the follow-up action.
     *
     * @param followUpDate The date of the follow-up action.
     */
    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    /**
     * Gets the description of the follow-up action.
     *
     * @return The description of the follow-up action.
     */
    public String getFollowUpDescription() {
        return followUpDescription;
    }

    /**
     * Sets the description of the follow-up action.
     *
     * @param followUpDescription The description of the follow-up action.
     */
    public void setFollowUpDescription(String followUpDescription) {
        this.followUpDescription = followUpDescription;
    }

    /**
     * Gets the outcome of the follow-up action.
     *
     * @return The outcome of the follow-up action.
     */
    public String getOutcome() {
        return outcome;
    }

    /**
     * Sets the outcome of the follow-up action.
     *
     * @param outcome The outcome of the follow-up action.
     */
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    /**
     * Gets the status of the follow-up action.
     *
     * @return The status of the follow-up action.
     */
    public Enums.FollowUpStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the follow-up action.
     *
     * @param status The status of the follow-up action.
     */
    public void setStatus(Enums.FollowUpStatus status) {
        this.status = status;
    }

    /**
     * Gets the timestamp when the follow-up action was last updated.
     *
     * @return The timestamp when the follow-up action was last updated.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the follow-up action was last updated.
     *
     * @param updatedAt The timestamp when the follow-up action was last updated.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CapaFollowUp{" +
                "id=" + id +
                ", capaId=" + capaId +
                ", conductedBy=" + conductedBy +
                ", createdAt=" + createdAt +
                ", followUpDate=" + followUpDate +
                ", followUpDescription='" + followUpDescription + '\'' +
                ", outcome='" + outcome + '\'' +
                ", status=" + status +
                ", updatedAt=" + updatedAt +
                '}';
    }
}