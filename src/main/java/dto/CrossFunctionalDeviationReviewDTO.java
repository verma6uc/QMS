package dto;

import java.util.List;

public class CrossFunctionalDeviationReviewDTO {
	private String crossFunctionalRequired;
	private List<Integer> department; // Assuming each department has an integer ID
	private List<Integer> userGroup; // Assuming the user ID is an integer
	private String decision;
	private String justification;
	private String comments;

	// Getters and Setters
	public String getCrossFunctionalRequired() {
		return crossFunctionalRequired;
	}

	public void setCrossFunctionalRequired(String crossFunctionalRequired) {
		this.crossFunctionalRequired = crossFunctionalRequired;
	}

	public List<Integer> getDepartment() {
		return department;
	}

	public void setDepartment(List<Integer> department) {
		this.department = department;
	}

	public List<Integer> getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(List<Integer> userGroup) {
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