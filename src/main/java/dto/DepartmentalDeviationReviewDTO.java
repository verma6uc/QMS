package dto;

public class DepartmentalDeviationReviewDTO {
	private Integer deviationId; // Assuming deviation_id is an Integer
	private String reviewComments; // Required input
	private String decision; // Required input, dropdown values
	private String justificationForDecision; // Optional, shown based on 'decision' value
	private Integer authorId;

	public DepartmentalDeviationReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentalDeviationReviewDTO(Integer deviationId, String reviewComments, String decision,
			String justificationForDecision, Integer authorId) {
		super();
		this.deviationId = deviationId;
		this.reviewComments = reviewComments;
		this.decision = decision;
		this.justificationForDecision = justificationForDecision;
		this.authorId = authorId;
	}

	public Integer getDeviationId() {
		return deviationId;
	}

	public void setDeviationId(Integer deviationId) {
		this.deviationId = deviationId;
	}

	public String getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getJustificationForDecision() {
		return justificationForDecision;
	}

	public void setJustificationForDecision(String justificationForDecision) {
		this.justificationForDecision = justificationForDecision;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "DepartmentalDeviationReviewDTO [deviationId=" + deviationId + ", reviewComments=" + reviewComments
				+ ", decision=" + decision + ", justificationForDecision=" + justificationForDecision + "]";
	}

}