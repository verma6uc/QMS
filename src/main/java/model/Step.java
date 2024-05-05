package model;

import java.sql.Timestamp;

/**
 * Represents a step in a workflow.
 */
public class Step {

    private int id;
    private String description;
    private String details;
    private int stepNumber;
    private int pageId;
    private int workflowId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and setters

    /**
     * Gets the ID of the step.
     *
     * @return the ID of the step
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the step.
     *
     * @param id the ID of the step
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the description of the step.
     *
     * @return the description of the step
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the step.
     *
     * @param description the description of the step
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the details of the step.
     *
     * @return the details of the step
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the details of the step.
     *
     * @param details the details of the step
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets the step number.
     *
     * @return the step number
     */
    public int getStepNumber() {
        return stepNumber;
    }

    /**
     * Sets the step number.
     *
     * @param stepNumber the step number
     */
    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    /**
     * Gets the ID of the page the step belongs to.
     *
     * @return the ID of the page
     */
    public int getPageId() {
        return pageId;
    }

    /**
     * Sets the ID of the page the step belongs to.
     *
     * @param pageId the ID of the page
     */
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    /**
     * Gets the ID of the workflow the step belongs to.
     *
     * @return the ID of the workflow
     */
    public int getWorkflowId() {
        return workflowId;
    }

    /**
     * Sets the ID of the workflow the step belongs to.
     *
     * @param workflowId the ID of the workflow
     */
    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * Gets the timestamp of when the step was created.
     *
     * @return the timestamp of when the step was created
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp of when the step was created.
     *
     * @param createdAt the timestamp of when the step was created
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp of when the step was last updated.
     *
     * @return the timestamp of when the step was last updated
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp of when the step was last updated.
     *
     * @param updatedAt the timestamp of when the step was last updated
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString method

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", details='" + details + '\'' +
                ", stepNumber=" + stepNumber +
                ", pageId=" + pageId +
                ", workflowId=" + workflowId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}