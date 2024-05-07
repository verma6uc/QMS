package dto;

public class PerformRCADTO {
	private Integer investigationId;
	private String investigationTool;
	private String rootCauseConclusion;
	private String riskImpactAssessment;

	// Fields for 5 Why Analysis
	private String whyQuestion1;
	private String whyAnswer1;
	private String whyQuestion2;
	private String whyAnswer2;
	private String whyQuestion3;
	private String whyAnswer3;
	private String whyQuestion4;
	private String whyAnswer4;
	private String whyQuestion5;
	private String whyAnswer5;

	// Fields for ADKOM Analysis
	private String abilityAssessment;
	private Boolean abilityResult;
	private String directionAssessment;
	private Boolean directionResult;
	private String knowledgeAssessment;
	private Boolean knowledgeResult;
	private String opportunityAssessment;
	private Boolean opportunityResult;
	private String motivationAssessment;
	private Boolean motivationResult;

	public PerformRCADTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PerformRCADTO(Integer investigationId, String investigationTool, String rootCauseConclusion,
			String riskImpactAssessment, String whyQuestion1, String whyAnswer1, String whyQuestion2, String whyAnswer2,
			String whyQuestion3, String whyAnswer3, String whyQuestion4, String whyAnswer4, String whyQuestion5,
			String whyAnswer5, String abilityAssessment, Boolean abilityResult, String directionAssessment,
			Boolean directionResult, String knowledgeAssessment, Boolean knowledgeResult, String opportunityAssessment,
			Boolean opportunityResult, String motivationAssessment, Boolean motivationResult) {
		super();
		this.investigationId = investigationId;
		this.investigationTool = investigationTool;
		this.rootCauseConclusion = rootCauseConclusion;
		this.riskImpactAssessment = riskImpactAssessment;
		this.whyQuestion1 = whyQuestion1;
		this.whyAnswer1 = whyAnswer1;
		this.whyQuestion2 = whyQuestion2;
		this.whyAnswer2 = whyAnswer2;
		this.whyQuestion3 = whyQuestion3;
		this.whyAnswer3 = whyAnswer3;
		this.whyQuestion4 = whyQuestion4;
		this.whyAnswer4 = whyAnswer4;
		this.whyQuestion5 = whyQuestion5;
		this.whyAnswer5 = whyAnswer5;
		this.abilityAssessment = abilityAssessment;
		this.abilityResult = abilityResult;
		this.directionAssessment = directionAssessment;
		this.directionResult = directionResult;
		this.knowledgeAssessment = knowledgeAssessment;
		this.knowledgeResult = knowledgeResult;
		this.opportunityAssessment = opportunityAssessment;
		this.opportunityResult = opportunityResult;
		this.motivationAssessment = motivationAssessment;
		this.motivationResult = motivationResult;
	}

	public Integer getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(Integer investigationId) {
		this.investigationId = investigationId;
	}

	public String getInvestigationTool() {
		return investigationTool;
	}

	public void setInvestigationTool(String investigationTool) {
		this.investigationTool = investigationTool;
	}

	public String getRootCauseConclusion() {
		return rootCauseConclusion;
	}

	public void setRootCauseConclusion(String rootCauseConclusion) {
		this.rootCauseConclusion = rootCauseConclusion;
	}

	public String getRiskImpactAssessment() {
		return riskImpactAssessment;
	}

	public void setRiskImpactAssessment(String riskImpactAssessment) {
		this.riskImpactAssessment = riskImpactAssessment;
	}

	public String getWhyQuestion1() {
		return whyQuestion1;
	}

	public void setWhyQuestion1(String whyQuestion1) {
		this.whyQuestion1 = whyQuestion1;
	}

	public String getWhyAnswer1() {
		return whyAnswer1;
	}

	public void setWhyAnswer1(String whyAnswer1) {
		this.whyAnswer1 = whyAnswer1;
	}

	public String getWhyQuestion2() {
		return whyQuestion2;
	}

	public void setWhyQuestion2(String whyQuestion2) {
		this.whyQuestion2 = whyQuestion2;
	}

	public String getWhyAnswer2() {
		return whyAnswer2;
	}

	public void setWhyAnswer2(String whyAnswer2) {
		this.whyAnswer2 = whyAnswer2;
	}

	public String getWhyQuestion3() {
		return whyQuestion3;
	}

	public void setWhyQuestion3(String whyQuestion3) {
		this.whyQuestion3 = whyQuestion3;
	}

	public String getWhyAnswer3() {
		return whyAnswer3;
	}

	public void setWhyAnswer3(String whyAnswer3) {
		this.whyAnswer3 = whyAnswer3;
	}

	public String getWhyQuestion4() {
		return whyQuestion4;
	}

	public void setWhyQuestion4(String whyQuestion4) {
		this.whyQuestion4 = whyQuestion4;
	}

	public String getWhyAnswer4() {
		return whyAnswer4;
	}

	public void setWhyAnswer4(String whyAnswer4) {
		this.whyAnswer4 = whyAnswer4;
	}

	public String getWhyQuestion5() {
		return whyQuestion5;
	}

	public void setWhyQuestion5(String whyQuestion5) {
		this.whyQuestion5 = whyQuestion5;
	}

	public String getWhyAnswer5() {
		return whyAnswer5;
	}

	public void setWhyAnswer5(String whyAnswer5) {
		this.whyAnswer5 = whyAnswer5;
	}

	public String getAbilityAssessment() {
		return abilityAssessment;
	}

	public void setAbilityAssessment(String abilityAssessment) {
		this.abilityAssessment = abilityAssessment;
	}

	public Boolean getAbilityResult() {
		return abilityResult;
	}

	public void setAbilityResult(Boolean abilityResult) {
		this.abilityResult = abilityResult;
	}

	public String getDirectionAssessment() {
		return directionAssessment;
	}

	public void setDirectionAssessment(String directionAssessment) {
		this.directionAssessment = directionAssessment;
	}

	public Boolean getDirectionResult() {
		return directionResult;
	}

	public void setDirectionResult(Boolean directionResult) {
		this.directionResult = directionResult;
	}

	public String getKnowledgeAssessment() {
		return knowledgeAssessment;
	}

	public void setKnowledgeAssessment(String knowledgeAssessment) {
		this.knowledgeAssessment = knowledgeAssessment;
	}

	public Boolean getKnowledgeResult() {
		return knowledgeResult;
	}

	public void setKnowledgeResult(Boolean knowledgeResult) {
		this.knowledgeResult = knowledgeResult;
	}

	public String getOpportunityAssessment() {
		return opportunityAssessment;
	}

	public void setOpportunityAssessment(String opportunityAssessment) {
		this.opportunityAssessment = opportunityAssessment;
	}

	public Boolean getOpportunityResult() {
		return opportunityResult;
	}

	public void setOpportunityResult(Boolean opportunityResult) {
		this.opportunityResult = opportunityResult;
	}

	public String getMotivationAssessment() {
		return motivationAssessment;
	}

	public void setMotivationAssessment(String motivationAssessment) {
		this.motivationAssessment = motivationAssessment;
	}

	public Boolean getMotivationResult() {
		return motivationResult;
	}

	public void setMotivationResult(Boolean motivationResult) {
		this.motivationResult = motivationResult;
	}

}