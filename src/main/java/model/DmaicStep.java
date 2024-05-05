package model;

import java.sql.Timestamp;

/**
 * Represents a DMAIC step in an investigation.
 */
public class DmaicStep {

    private int id;
    private int investigationId;
    private String phase;
    private String description;
    private String findings;
    private String recommendations;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the DMAIC step.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the DMAIC step.
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
     * Gets the phase of the DMAIC step (e.g., Define, Measure, Analyze, Improve, Control).
     *
     * @return the phase
     */
    public String getPhase() {
        return phase;
    }

    /**
     * Sets the phase of the DMAIC step.
     *
     * @param phase the phase to set
     */
    public void setPhase(String phase) {
        this.phase = phase;
    }

    /**
     * Gets the description of the DMAIC step.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the DMAIC step.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the findings from the DMAIC step.
     *
     * @return the findings
     */
    public String getFindings() {
        return findings;
    }

    /**
     * Sets the findings from the DMAIC step.
     *
     * @param findings the findings to set
     */
    public void setFindings(String findings) {
        this.findings = findings;
    }

    /**
     * Gets the recommendations from the DMAIC step.
     *
     * @return the recommendations
     */
    public String getRecommendations() {
        return recommendations;
    }

    /**
     * Sets the recommendations from the DMAIC step.
     *
     * @param recommendations the recommendations to set
     */
    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    /**
     * Gets the timestamp when the DMAIC step was created.
     *
     * @return the creation timestamp
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the DMAIC step was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the DMAIC step was last updated.
     *
     * @return the update timestamp
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the DMAIC step was last updated.
     *
     * @param updatedAt the update timestamp to set
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "DmaicStep{" +
                "id=" + id +
                ", investigationId=" + investigationId +
                ", phase='" + phase + '\'' +
                ", description='" + description + '\'' +
                ", findings='" + findings + '\'' +
                ", recommendations='" + recommendations + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}