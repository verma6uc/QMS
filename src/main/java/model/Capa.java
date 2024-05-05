package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents a Corrective and Preventive Action (CAPA) record.
 */
public class Capa {

    private int id;
    private Enums.CapaType actionType;
    private Date completionDate;
    private Timestamp createdAt;
    private String description;
    private int deviationId;
    private Date dueDate;
    private int responsibleUserId;
    private Enums.CapaStatus status;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the CAPA.
     *
     * @return The CAPA ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the CAPA.
     *
     * @param id The CAPA ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the action type of the CAPA (Preventive or Corrective).
     *
     * @return The action type.
     */
    public Enums.CapaType getActionType() {
        return actionType;
    }

    /**
     * Sets the action type of the CAPA (Preventive or Corrective).
     *
     * @param actionType The action type.
     */
    public void setActionType(Enums.CapaType actionType) {
        this.actionType = actionType;
    }

    /**
     * Gets the completion date of the CAPA.
     *
     * @return The completion date.
     */
    public Date getCompletionDate() {
        return completionDate;
    }

    /**
     * Sets the completion date of the CAPA.
     *
     * @param completionDate The completion date.
     */
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * Gets the timestamp when the CAPA was created.
     *
     * @return The creation timestamp.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the CAPA was created.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the description of the CAPA.
     *
     * @return The CAPA description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the CAPA.
     *
     * @param description The CAPA description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the ID of the deviation associated with the CAPA.
     *
     * @return The deviation ID.
     */
    public int getDeviationId() {
        return deviationId;
    }

    /**
     * Sets the ID of the deviation associated with the CAPA.
     *
     * @param deviationId The deviation ID.
     */
    public void setDeviationId(int deviationId) {
        this.deviationId = deviationId;
    }

    /**
     * Gets the due date of the CAPA.
     *
     * @return The due date.
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the CAPA.
     *
     * @param dueDate The due date.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the ID of the responsible user for the CAPA.
     *
     * @return The responsible user ID.
     */
    public int getResponsibleUserId() {
        return responsibleUserId;
    }

    /**
     * Sets the ID of the responsible user for the CAPA.
     *
     * @param responsibleUserId The responsible user ID.
     */
    public void setResponsibleUserId(int responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    /**
     * Gets the status of the CAPA (e.g., Open, In Progress, Closed).
     *
     * @return The CAPA status.
     */
    public Enums.CapaStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the CAPA (e.g., Open, In Progress, Closed).
     *
     * @param status The CAPA status.
     */
    public void setStatus(Enums.CapaStatus status) {
        this.status = status;
    }

    /**
     * Gets the timestamp when the CAPA was last updated.
     *
     * @return The update timestamp.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the CAPA was last updated.
     *
     * @param updatedAt The update timestamp.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Capa{" +
                "id=" + id +
                ", actionType=" + actionType +
                ", completionDate=" + completionDate +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", deviationId=" + deviationId +
                ", dueDate=" + dueDate +
                ", responsibleUserId=" + responsibleUserId +
                ", status=" + status +
                ", updatedAt=" + updatedAt +
                '}';
    }
}