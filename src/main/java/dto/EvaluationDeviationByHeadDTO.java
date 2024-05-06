package dto;

public class EvaluationDeviationByHeadDTO {

    private Integer deviationId;
    private Integer probabilityOfRecurrence;
    private String probabilityJustification;
    private Integer additionalProcessingSteps;
    private String stepsJustification;
    private Integer microbiologicallyRelated;
    private String microbiologicallyRelatedJustification;
    private Integer productCrossContamination;
    private String contaminationJustification;
    private Integer productImpact;
    private String impactJustification;
    private Integer complexityOfInvestigation;
    private String complexityJustification;
    private Integer criticalWarrantedByQuality;
    private String criticalJustification;
	public EvaluationDeviationByHeadDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EvaluationDeviationByHeadDTO(Integer deviationId, Integer probabilityOfRecurrence,
			String probabilityJustification, Integer additionalProcessingSteps, String stepsJustification,
			Integer microbiologicallyRelated, String microbiologicallyRelatedJustification,
			Integer productCrossContamination, String contaminationJustification, Integer productImpact,
			String impactJustification, Integer complexityOfInvestigation, String complexityJustification,
			Integer criticalWarrantedByQuality, String criticalJustification) {
		super();
		this.deviationId = deviationId;
		this.probabilityOfRecurrence = probabilityOfRecurrence;
		this.probabilityJustification = probabilityJustification;
		this.additionalProcessingSteps = additionalProcessingSteps;
		this.stepsJustification = stepsJustification;
		this.microbiologicallyRelated = microbiologicallyRelated;
		this.microbiologicallyRelatedJustification = microbiologicallyRelatedJustification;
		this.productCrossContamination = productCrossContamination;
		this.contaminationJustification = contaminationJustification;
		this.productImpact = productImpact;
		this.impactJustification = impactJustification;
		this.complexityOfInvestigation = complexityOfInvestigation;
		this.complexityJustification = complexityJustification;
		this.criticalWarrantedByQuality = criticalWarrantedByQuality;
		this.criticalJustification = criticalJustification;
	}
	public Integer getDeviationId() {
		return deviationId;
	}
	public void setDeviationId(Integer deviationId) {
		this.deviationId = deviationId;
	}
	public Integer getProbabilityOfRecurrence() {
		return probabilityOfRecurrence;
	}
	public void setProbabilityOfRecurrence(Integer probabilityOfRecurrence) {
		this.probabilityOfRecurrence = probabilityOfRecurrence;
	}
	public String getProbabilityJustification() {
		return probabilityJustification;
	}
	public void setProbabilityJustification(String probabilityJustification) {
		this.probabilityJustification = probabilityJustification;
	}
	public Integer getAdditionalProcessingSteps() {
		return additionalProcessingSteps;
	}
	public void setAdditionalProcessingSteps(Integer additionalProcessingSteps) {
		this.additionalProcessingSteps = additionalProcessingSteps;
	}
	public String getStepsJustification() {
		return stepsJustification;
	}
	public void setStepsJustification(String stepsJustification) {
		this.stepsJustification = stepsJustification;
	}
	public Integer getMicrobiologicallyRelated() {
		return microbiologicallyRelated;
	}
	public void setMicrobiologicallyRelated(Integer microbiologicallyRelated) {
		this.microbiologicallyRelated = microbiologicallyRelated;
	}
	public String getMicrobiologicallyRelatedJustification() {
		return microbiologicallyRelatedJustification;
	}
	public void setMicrobiologicallyRelatedJustification(String microbiologicallyRelatedJustification) {
		this.microbiologicallyRelatedJustification = microbiologicallyRelatedJustification;
	}
	public Integer getProductCrossContamination() {
		return productCrossContamination;
	}
	public void setProductCrossContamination(Integer productCrossContamination) {
		this.productCrossContamination = productCrossContamination;
	}
	public String getContaminationJustification() {
		return contaminationJustification;
	}
	public void setContaminationJustification(String contaminationJustification) {
		this.contaminationJustification = contaminationJustification;
	}
	public Integer getProductImpact() {
		return productImpact;
	}
	public void setProductImpact(Integer productImpact) {
		this.productImpact = productImpact;
	}
	public String getImpactJustification() {
		return impactJustification;
	}
	public void setImpactJustification(String impactJustification) {
		this.impactJustification = impactJustification;
	}
	public Integer getComplexityOfInvestigation() {
		return complexityOfInvestigation;
	}
	public void setComplexityOfInvestigation(Integer complexityOfInvestigation) {
		this.complexityOfInvestigation = complexityOfInvestigation;
	}
	public String getComplexityJustification() {
		return complexityJustification;
	}
	public void setComplexityJustification(String complexityJustification) {
		this.complexityJustification = complexityJustification;
	}
	public Integer getCriticalWarrantedByQuality() {
		return criticalWarrantedByQuality;
	}
	public void setCriticalWarrantedByQuality(Integer criticalWarrantedByQuality) {
		this.criticalWarrantedByQuality = criticalWarrantedByQuality;
	}
	public String getCriticalJustification() {
		return criticalJustification;
	}
	public void setCriticalJustification(String criticalJustification) {
		this.criticalJustification = criticalJustification;
	}
	@Override
	public String toString() {
		return "EvaluationDeviationByHeadDTO [deviationId=" + deviationId + ", probabilityOfRecurrence="
				+ probabilityOfRecurrence + ", probabilityJustification=" + probabilityJustification
				+ ", additionalProcessingSteps=" + additionalProcessingSteps + ", stepsJustification="
				+ stepsJustification + ", microbiologicallyRelated=" + microbiologicallyRelated
				+ ", microbiologicallyRelatedJustification=" + microbiologicallyRelatedJustification
				+ ", productCrossContamination=" + productCrossContamination + ", contaminationJustification="
				+ contaminationJustification + ", productImpact=" + productImpact + ", impactJustification="
				+ impactJustification + ", complexityOfInvestigation=" + complexityOfInvestigation
				+ ", complexityJustification=" + complexityJustification + ", criticalWarrantedByQuality="
				+ criticalWarrantedByQuality + ", criticalJustification=" + criticalJustification + "]";
	}
	
}
