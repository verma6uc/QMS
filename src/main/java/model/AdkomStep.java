package model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Represents an AdkomStep entity.
 */
public class AdkomStep {
    /**
     * Primary key.
     */
    private int id;

    /**
     * Assessment text.
     */
    private String assessment;

    /**
     * Creation timestamp.
     */
    private Timestamp createdAt;

    /**
     * Foreign key linking to investigation.
     */
    private int investigationId;

    /**
     * Result of the step.
     */
    private boolean result;

    /**
     * Descriptive step.
     */
    private String step;

    /**
     * Last update timestamp.
     */
    private Timestamp updatedAt;

    public AdkomStep() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getAssessment() {
        return assessment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public int getInvestigationId() {
        return investigationId;
    }

    public boolean isResult() {
        return result;
    }

    public String getStep() {
        return step;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setInvestigationId(int investigationId) {
        this.investigationId = investigationId;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "AdkomStep{" +
                "id=" + id +
                ", assessment='" + assessment + '\'' +
                ", createdAt=" + createdAt +
                ", investigationId=" + investigationId +
                ", result=" + result +
                ", step='" + step + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdkomStep)) return false;
        AdkomStep adkomStep = (AdkomStep) o;
        return getId() == adkomStep.getId() &&
                getInvestigationId() == adkomStep.getInvestigationId() &&
                isResult() == adkomStep.isResult() &&
                Objects.equals(getAssessment(), adkomStep.getAssessment()) &&
                Objects.equals(getCreatedAt(), adkomStep.getCreatedAt()) &&
                Objects.equals(getStep(), adkomStep.getStep()) &&
                Objects.equals(getUpdatedAt(), adkomStep.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAssessment(), getCreatedAt(), getInvestigationId(), isResult(), getStep(), getUpdatedAt());
    }
}