package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents a risk assessment associated with a CAPA.
 */
public class CapaRiskAssessment {

    private int id;
    private int capaId;
    private int assessedBy;
    private Date assessmentDate;
    private String riskDescription;
    private int riskScore;
    private String mitigationPlan;
    private int residualRiskScore;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the risk assessment.
     *
     * @return the ID of the risk assessment
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the risk assessment.
     *
     * @param id the ID of the risk assessment
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the associated CAPA.
     *
     * @return the ID of the associated CAPA
     */
    public int getCapaId() {
        return capaId;
    }

    /**
     * Sets the ID of the associated CAPA.
     *
     * @param capaId the ID of the associated CAPA
     */
    public void setCapaId(int capaId) {
        this.capaId = capaId;
    }

    /**
     * Gets the ID of the user who conducted the assessment.
     *
     * @return the ID of the user who conducted the assessment
     */
    public int getAssessedBy() {
        return assessedBy;
    }

    /**
     * Sets the ID of the user who conducted the assessment.
     *
     * @param assessedBy the ID of the user who conducted the assessment
     */
    public void setAssessedBy(int assessedBy) {
        this.assessedBy = assessedBy;
    }

    /**
     * Gets the date of the assessment.
     *
     * @return the date of the assessment
     */
    public Date getAssessmentDate() {
        return assessmentDate;
    }

    /**
     * Sets the date of the assessment.
     *
     * @param assessmentDate the date of the assessment
     */
    public void setAssessmentDate(Date assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    /**
     * Gets the description of the risk.
     *
     * @return the description of the risk
     */
    public String getRiskDescription() {
        return riskDescription;
    }

    /**
     * Sets the description of the risk.
     *
     * @param riskDescription the description of the risk
     */
    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    /**
     * Gets the risk score.
     *
     * @return the risk score
     */
    public int getRiskScore() {
        return riskScore;
    }

    /**
     * Sets the risk score.
     *
     * @param riskScore the risk score
     */
    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    /**
     * Gets the mitigation plan for the risk.
     *
     * @return the mitigation plan for the risk
     */
    public String getMitigationPlan() {
        return mitigationPlan;
    }

    /**
     * Sets the mitigation plan for the risk.
     *
     * @param mitigationPlan the mitigation plan for the risk
     */
    public void setMitigationPlan(String mitigationPlan) {
        this.mitigationPlan = mitigationPlan;
    }

    /**
     * Gets the residual risk score after mitigation.
     *
     * @return the residual risk score after mitigation
     */
    public int getResidualRiskScore() {
        return residualRiskScore;
    }

    /**
     * Sets the residual risk score after mitigation.
     *
     * @param residualRiskScore the residual risk score after mitigation
     */
    public void setResidualRiskScore(int residualRiskScore) {
        this.residualRiskScore = residualRiskScore;
    }

    /**
     * Gets the timestamp when the risk assessment was created.
     *
     * @return the timestamp when the risk assessment was created
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the risk assessment was created.
     *
     * @param createdAt the timestamp when the risk assessment was created
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the risk assessment was last updated.
     *
     * @return the timestamp when the risk assessment was last updated
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the risk assessment was last updated.
     *
     * @param updatedAt the timestamp when the risk assessment was last updated
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CapaRiskAssessment{" +
                "id=" + id +
                ", capaId=" + capaId +
                ", assessedBy=" + assessedBy +
                ", assessmentDate=" + assessmentDate +
                ", riskDescription='" + riskDescription + '\'' +
                ", riskScore=" + riskScore +
                ", mitigationPlan='" + mitigationPlan + '\'' +
                ", residualRiskScore=" + residualRiskScore +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}