package model;

public class Enums {

	public enum EventRelatedEnum {
		SOFTWARE, OTHERS, MATERIAL, PRODUCT, EQUIPMENT, DOCUMENT
	}

	public enum EquipmentCategory {
		SAFETY, LABORATORY, PRODUCTION, OTHER, MAINTENANCE
	}

	public enum EquipmentStatus {
		UNDER_MAINTENANCE, OPERATIONAL, DECOMMISSIONED, OUT_OF_SERVICE
	}

	public enum CapaActionStatus {
		COMPLETED, IN_PROGRESS, PENDING
	}

	public enum MaterialStatus {
		OUT_OF_STOCK, IN_STOCK, LOW_STOCK, DISCONTINUED
	}

	public enum CapaStatus {
		IN_PROGRESS, CLOSED, COMPLETED, OPEN
	}

	public enum BatchStatus {
		IN_REVIEW, ACTIVE, RECALLED, EXPIRED
	}

	public enum NotificationStatus {
		UNREAD, ARCHIVED, READ
	}

	public enum DocumentTypes {
		TRAINING_RECORD, CAPA_FORM, DEVIATION_REPORT, CHANGE_CONTROL
	}

	public enum ProductStatus {
		DEVELOPMENT, ACTIVE, RECALLED, DISCONTINUED
	}

	public enum DeviationStatus {
		APPROVED_BY_QA, UNDER_CFT_REVIEW, PENDING_CFT_REVIEW, OPEN, UNDER_REVIEW, CLOSED, MORE_INFO,
		APPROVED_DEPARTMENT_REVIEW, REJECTED, ONGOING_DEPARTMENT_REVIEW, PENDING_DEPARTMENT_REVIEW,
		PENDING_FINAL_APPROVAL, PENDING_QA_REVIEW
	}

	public enum CapaType {
		PREVENTIVE, CORRECTIVE
	}

	public enum FollowUpStatus {
		SCHEDULED, DELAYED, COMPLETED
	}

	public enum MaterialCategory {
		OTHER, RAW_MATERIAL, PACKAGING, CHEMICAL
	}

	public enum ActionTypes {
		PREVENTIVE, CORRECTIVE
	}

	public enum RoleNames {
		USER, PRODUCTION, QA, AUDITOR, SUPERVISOR, ADMIN
	}

	public enum InvestigationMethodologyEnum {
		DMAIC, FIVE_WHY, ADKOM, FISHBONE
	}

	public enum InvestigationStatusEnum {
		CLOSED, ON_HOLD, OPEN
	}

	public enum ReviewDecisionTypes {
		REJECT, REQUIRE_REVISION, APPROVE
	}

	public enum ChangeControlStatus {
		APPROVED, DRAFT, CANCELLED, IMPLEMENTED, PENDING_APPROVAL
	}

	public enum ProductCategory {
		BIOTECHNOLOGY, PHARMACEUTICAL, OTHER, HEALTHCARE, MEDICAL_DEVICE
	}

	public enum RiskFactorType {
		PROCESS_STEPS, MICROBIOLOGICAL, CROSS_CONTAMINATION, PRODUCT_IMPACT, INVESTIGATION_COMPLEXITY, QUALITY_CRITICAL,
		PROBABILITY
	}
}