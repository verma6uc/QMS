package model;

import java.sql.Timestamp;

/**
 * Represents a QA deviation risk assessment.
 */
public class QaDeviationRiskAssessment {

    private int id;
    private int deviationId;
    private Enums.RiskFactorType factorType;
    private String justification;
    private int score;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and setters

    /**
     * Gets the ID of the risk assessment.
     *
     * @return The ID of the risk assessment.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the risk assessment.
     *
     * @param id The ID of the risk assessment.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the associated deviation.
     *
     * @return The ID of the associated deviation.
     */
    public int getDeviationId() {
        return deviationId;
    }

    /**
     * Sets the ID of the associated deviation.
     *
     * @param deviationId The ID of the associated deviation.
     */
    public void setDeviationId(int deviationId) {
        this.deviationId = deviationId;
    }

    /**
     * Gets the type of risk factor.
     *
     * @return The type of risk factor.
     */
    public Enums.RiskFactorType getFactorType() {
        return factorType;
    }

    /**
     * Sets the type of risk factor.
     *
     * @param factorType The type of risk factor.
     */
    public void setFactorType(Enums.RiskFactorType factorType) {
        this.factorType = factorType;
    }

    /**
     * Gets the justification for the risk assessment.
     *
     * @return The justification for the risk assessment.
     */
    public String getJustification() {
        return justification;
    }

    /**
     * Sets the justification for the risk assessment.
     *
     * @param justification The justification for the risk assessment.
     */
    public void setJustification(String justification) {
        this.justification = justification;
    }

    /**
     * Gets the risk score.
     *
     * @return The risk score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the risk score.
     *
     * @param score The risk score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString method

    @Override
    public String toString() {
        return "QaDeviationRiskAssessment{" +
                "id=" + id +
                ", deviationId=" + deviationId +
                ", factorType=" + factorType +
                ", justification='" + justification + '\'' +
                ", score=" + score +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}