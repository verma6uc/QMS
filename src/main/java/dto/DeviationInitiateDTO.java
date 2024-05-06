package dto;

import java.util.List;

public class DeviationInitiateDTO {

    private String dateOfOccurrence; // Using String to represent date since formatting on the DTO level varies and often handled on service or controller level.
    private String dateOfIdentification;
    private String timeOfIdentification;
    private String justificationForDelay;
    private String eventRelatedType;
    
    // Event-specific details
    private Integer productId; // Assuming 'id' from the database is of type integer.
    private List<Integer> batchIds; // Assumes multiple batch ids can be selected.
    private Integer materialId;
    private String lotNumber;
    private Integer equipmentId;
    private Integer documentId;

    private String description;

    // Getters and setters for all fields.

    public String getDateOfOccurrence() {
        return dateOfOccurrence;
    }

    public void setDateOfOccurrence(String dateOfOccurrence) {
        this.dateOfOccurrence = dateOfOccurrence;
    }

    public String getDateOfIdentification() {
        return dateOfIdentification;
    }

    public void setDateOfIdentification(String dateOfIdentification) {
        this.dateOfIdentification = dateOfIdentification;
    }

    public String getTimeOfIdentification() {
        return timeOfIdentification;
    }

    public void setTimeOfIdentification(String timeOfIdentification) {
        this.timeOfIdentification = timeOfIdentification;
    }

    public String getJustificationForDelay() {
        return justificationForDelay;
    }

    public void setJustificationForDelay(String justificationForDelay) {
        this.justificationForDelay = justificationForDelay;
    }

    public String getEventRelatedType() {
        return eventRelatedType;
    }

    public void setEventRelatedType(String eventRelatedType) {
        this.eventRelatedType = eventRelatedType;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public List<Integer> getBatchIds() {
        return batchIds;
    }

    public void setBatchIds(List<Integer> batchIds) {
        this.batchIds = batchIds;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}