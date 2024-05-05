package model;

import java.sql.Timestamp;

/**
 * Represents a workflow.
 */
public class Workflow {

    private int id;
    private String workflowName;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and setters

    /**
     * Gets the ID of the workflow.
     *
     * @return The ID of the workflow.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the workflow.
     *
     * @param id The ID of the workflow.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the workflow.
     *
     * @return The name of the workflow.
     */
    public String getWorkflowName() {
        return workflowName;
    }

    /**
     * Sets the name of the workflow.
     *
     * @param workflowName The name of the workflow.
     */
    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    /**
     * Gets the description of the workflow.
     *
     * @return The description of the workflow.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the workflow.
     *
     * @param description The description of the workflow.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the creation timestamp of the workflow.
     *
     * @return The creation timestamp of the workflow.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the workflow.
     *
     * @param createdAt The creation timestamp of the workflow.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the update timestamp of the workflow.
     *
     * @return The update timestamp of the workflow.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the update timestamp of the workflow.
     *
     * @param updatedAt The update timestamp of the workflow.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString method

    @Override
    public String toString() {
        return "Workflow{" +
                "id=" + id +
                ", workflowName='" + workflowName + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}