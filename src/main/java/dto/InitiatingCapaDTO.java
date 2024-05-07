package dto;

public class InitiatingCapaDTO {
	private Integer deviationId;
	private String description;
	private Integer responsibleUserId;
	private String actionType;
	private String targetClosureDate; // Using String to store date for simplicity. Could be changed to LocalDate
									// based on actual usage.
	private Boolean changeControlRequired;
	private Boolean interimControlRequired;
	private String interimControlDetails;
	private String effectivenessPlan;

	public InitiatingCapaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InitiatingCapaDTO(Integer deviationId, String description, Integer responsibleUserId, String actionType,
			String targetClosureDate, Boolean changeControlRequired, Boolean interimControlRequired,
			String interimControlDetails, String effectivenessPlan) {
		super();
		this.deviationId = deviationId;
		this.description = description;
		this.responsibleUserId = responsibleUserId;
		this.actionType = actionType;
		this.targetClosureDate = targetClosureDate;
		this.changeControlRequired = changeControlRequired;
		this.interimControlRequired = interimControlRequired;
		this.interimControlDetails = interimControlDetails;
		this.effectivenessPlan = effectivenessPlan;
	}

	public Integer getDeviationId() {
		return deviationId;
	}

	public void setDeviationId(Integer deviationId) {
		this.deviationId = deviationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getResponsibleUserId() {
		return responsibleUserId;
	}

	public void setResponsibleUserId(Integer responsibleUserId) {
		this.responsibleUserId = responsibleUserId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getTargetClosureDate() {
		return targetClosureDate;
	}

	public void setTargetClosureDate(String targetClosureDate) {
		this.targetClosureDate = targetClosureDate;
	}

	public Boolean getChangeControlRequired() {
		return changeControlRequired;
	}

	public void setChangeControlRequired(Boolean changeControlRequired) {
		this.changeControlRequired = changeControlRequired;
	}

	public Boolean getInterimControlRequired() {
		return interimControlRequired;
	}

	public void setInterimControlRequired(Boolean interimControlRequired) {
		this.interimControlRequired = interimControlRequired;
	}

	public String getInterimControlDetails() {
		return interimControlDetails;
	}

	public void setInterimControlDetails(String interimControlDetails) {
		this.interimControlDetails = interimControlDetails;
	}

	public String getEffectivenessPlan() {
		return effectivenessPlan;
	}

	public void setEffectivenessPlan(String effectivenessPlan) {
		this.effectivenessPlan = effectivenessPlan;
	}

	@Override
	public String toString() {
		return "InitiatingCapaDTO [deviationId=" + deviationId + ", description=" + description + ", responsibleUserId="
				+ responsibleUserId + ", actionType=" + actionType + ", targetClosureDate=" + targetClosureDate
				+ ", changeControlRequired=" + changeControlRequired + ", interimControlRequired="
				+ interimControlRequired + ", interimControlDetails=" + interimControlDetails + ", effectivenessPlan="
				+ effectivenessPlan + "]";
	}

	 

}
