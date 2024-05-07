package dto;

public class ApprovalByQADTO {
	private Integer deviationId;
	private String approverComments;
	private String decision;
	private String justification;
	private Integer userId;

	public ApprovalByQADTO() {
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
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

	public Integer getDeviationId() {
		return deviationId;
	}

	public void setDeviationId(Integer deviationId) {
		this.deviationId = deviationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}