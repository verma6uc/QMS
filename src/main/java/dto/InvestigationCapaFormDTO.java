package dto;

import java.util.List;

public class InvestigationCapaFormDTO {
	// Fields corresponding to the form inputs
	private boolean investigationRequired;
	private String descriptionOfInvestigation;
	private List<Integer> investigatorIds;
    private String deviationId;  	

	// Default constructor
	public InvestigationCapaFormDTO() {
	}

	// Getters and setters
	public boolean isInvestigationRequired() {
		return investigationRequired;
	}

	public void setInvestigationRequired(boolean investigationRequired) {
		this.investigationRequired = investigationRequired;
	}

	public String getDescriptionOfInvestigation() {
		return descriptionOfInvestigation;
	}

	public void setDescriptionOfInvestigation(String descriptionOfInvestigation) {
		this.descriptionOfInvestigation = descriptionOfInvestigation;
	}

	public List<Integer> getInvestigatorIds() {
		return investigatorIds;
	}

	public void setInvestigatorIds(List<Integer> investigatorIds) {
		this.investigatorIds = investigatorIds;
	}

	
	public String getDeviationId() {
		return deviationId;
	}

	public void setDeviationId(String deviationId) {
		this.deviationId = deviationId;
	}

	// toString method for debugging
	@Override
	public String toString() {
		return "InvestigationCapaFormDTO{" + "investigationRequired=" + investigationRequired
				+ ", descriptionOfInvestigation='" + descriptionOfInvestigation + '\'' + ", investigatorIds="
				+ investigatorIds + '}';
	}
}
