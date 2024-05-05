package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents a deviation record.
 */
public class Deviation {

    private int id;
    private Timestamp createdAt;
    private Date dateOfIdentification;
    private Date dateOfInitiation;
    private Date dateOfOccurrence;
    private int departmentId;
    private String description;
    private String documentDetails;
    private Enums.EventRelatedEnum eventRelatedType;
    private String fileAttachments; // Assuming JSONB is represented as a String
    private boolean impactOnBatches;
    private boolean impactOnOtherBatches;
    private int initiatedByUserId;
    private String justificationForDelay;
    private String otherDetails;
    private String remarks;
    private String riskAssessmentEquipment;
    private String riskAssessmentFacility;
    private String riskAssessmentOthers;
    private String riskAssessmentProduct;
    private Enums.DeviationStatus status;
    private Time timeOfIdentification;
    private String title;
    private Timestamp updatedAt;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDateOfIdentification() {
        return dateOfIdentification;
    }

    public void setDateOfIdentification(Date dateOfIdentification) {
        this.dateOfIdentification = dateOfIdentification;
    }

    public Date getDateOfInitiation() {
        return dateOfInitiation;
    }

    public void setDateOfInitiation(Date dateOfInitiation) {
        this.dateOfInitiation = dateOfInitiation;
    }

    public Date getDateOfOccurrence() {
        return dateOfOccurrence;
    }

    public void setDateOfOccurrence(Date dateOfOccurrence) {
        this.dateOfOccurrence = dateOfOccurrence;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(String documentDetails) {
        this.documentDetails = documentDetails;
    }

    public Enums.EventRelatedEnum getEventRelatedType() {
        return eventRelatedType;
    }

    public void setEventRelatedType(Enums.EventRelatedEnum eventRelatedType) {
        this.eventRelatedType = eventRelatedType;
    }

    public String getFileAttachments() {
        return fileAttachments;
    }

    public void setFileAttachments(String fileAttachments) {
        this.fileAttachments = fileAttachments;
    }

    public boolean isImpactOnBatches() {
        return impactOnBatches;
    }

    public void setImpactOnBatches(boolean impactOnBatches) {
        this.impactOnBatches = impactOnBatches;
    }

    public boolean isImpactOnOtherBatches() {
        return impactOnOtherBatches;
    }

    public void setImpactOnOtherBatches(boolean impactOnOtherBatches) {
        this.impactOnOtherBatches = impactOnOtherBatches;
    }

    public int getInitiatedByUserId() {
        return initiatedByUserId;
    }

    public void setInitiatedByUserId(int initiatedByUserId) {
        this.initiatedByUserId = initiatedByUserId;
    }

    public String getJustificationForDelay() {
        return justificationForDelay;
    }

    public void setJustificationForDelay(String justificationForDelay) {
        this.justificationForDelay = justificationForDelay;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRiskAssessmentEquipment() {
        return riskAssessmentEquipment;
    }

    public void setRiskAssessmentEquipment(String riskAssessmentEquipment) {
        this.riskAssessmentEquipment = riskAssessmentEquipment;
    }

    public String getRiskAssessmentFacility() {
        return riskAssessmentFacility;
    }

    public void setRiskAssessmentFacility(String riskAssessmentFacility) {
        this.riskAssessmentFacility = riskAssessmentFacility;
    }

    public String getRiskAssessmentOthers() {
        return riskAssessmentOthers;
    }

    public void setRiskAssessmentOthers(String riskAssessmentOthers) {
        this.riskAssessmentOthers = riskAssessmentOthers;
    }

    public String getRiskAssessmentProduct() {
        return riskAssessmentProduct;
    }

    public void setRiskAssessmentProduct(String riskAssessmentProduct) {
        this.riskAssessmentProduct = riskAssessmentProduct;
    }

    public Enums.DeviationStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.DeviationStatus status) {
        this.status = status;
    }

    public Time getTimeOfIdentification() {
        return timeOfIdentification;
    }

    public void setTimeOfIdentification(Time timeOfIdentification) {
        this.timeOfIdentification = timeOfIdentification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString method (optional, but helpful for debugging)

     
}