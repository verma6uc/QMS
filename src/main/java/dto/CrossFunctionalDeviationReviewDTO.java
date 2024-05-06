package dto;

public class CrossFunctionalDeviationReviewDTO {
	private Integer deviationId; // Assuming deviation_id is an Integer
    private String crossFunctionalRequired;
    private Integer department; // Assuming department ID is an integer
    private Integer userGroup; // Assuming user group ID is an integer
    private String decision;
    private String justification; // Optional, depending on the decision
    private String comments; // Although labeled as optional in HTML, it is not nullable in DTO as it defaults to an empty string if not provided
	public CrossFunctionalDeviationReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CrossFunctionalDeviationReviewDTO(Integer deviationId, String crossFunctionalRequired, Integer department,
			Integer userGroup, String decision, String justification, String comments) {
		super();
		this.deviationId = deviationId;
		this.crossFunctionalRequired = crossFunctionalRequired;
		this.department = department;
		this.userGroup = userGroup;
		this.decision = decision;
		this.justification = justification;
		this.comments = comments;
	}
	public Integer getDeviationId() {
		return deviationId;
	}
	public void setDeviationId(Integer deviationId) {
		this.deviationId = deviationId;
	}
	public String getCrossFunctionalRequired() {
		return crossFunctionalRequired;
	}
	public void setCrossFunctionalRequired(String crossFunctionalRequired) {
		this.crossFunctionalRequired = crossFunctionalRequired;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public Integer getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(Integer userGroup) {
		this.userGroup = userGroup;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

    
}