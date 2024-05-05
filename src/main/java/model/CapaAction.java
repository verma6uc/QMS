package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents a CAPA action.
 */
public class CapaAction {

    private int id;
    private int capaId;
    private int actioneeId;
    private String actionDescription;
    private Enums.CapaActionStatus status;
    private Date dueDate;
    private Date completionDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and setters

    /**
     * Gets the ID of the CAPA action.
     *
     * @return The ID of the CAPA action.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the CAPA action.
     *
     * @param id The ID of the CAPA action.
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
     * Gets the ID of the user assigned to the action.
     *
     * @return The ID of the user assigned to the action.
     */
    public int getActioneeId() {
        return actioneeId;
    }

    /**
     * Sets the ID of the user assigned to the action.
     *
     * @param actioneeId The ID of the user assigned to the action.
     */
    public void setActioneeId(int actioneeId) {
        this.actioneeId = actioneeId;
    }

    /**
     * Gets the description of the action.
     *
     * @return The description of the action.
     */
    public String getActionDescription() {
        return actionDescription;
    }

    /**
     * Sets the description of the action.
     *
     * @param actionDescription The description of the action.
     */
    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    /**
     * Gets the status of the action.
     *
     * @return The status of the action.
     */
    public Enums.CapaActionStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the action.
     *
     * @param status The status of the action.
     */
    public void setStatus(Enums.CapaActionStatus status) {
        this.status = status;
    }

    /**
     * Gets the due date for the action.
     *
     * @return The due date for the action.
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for the action.
     *
     * @param dueDate The due date for the action.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the completion date for the action.
     *
     * @return The completion date for the action.
     */
    public Date getCompletionDate() {
        return completionDate;
    }

    /**
     * Sets the completion date for the action.
     *
     * @param completionDate The completion date for the action.
     */
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * Gets the timestamp when the action was created.
     *
     * @return The timestamp when the action was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the action was created.
     *
     * @param createdAt The timestamp when the action was created.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the action was last updated.
     *
     * @return The timestamp when the action was last updated.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the action was last updated.
     *
     * @param updatedAt The timestamp when the action was last updated.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString method

    @Override
    public String toString() {
        return "CapaAction{" +
                "id=" + id +
                ", capaId=" + capaId +
                ", actioneeId=" + actioneeId +
                ", actionDescription='" + actionDescription + '\'' +
                ", status=" + status +
                ", dueDate=" + dueDate +
                ", completionDate=" + completionDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}