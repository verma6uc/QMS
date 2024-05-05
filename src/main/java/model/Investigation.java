package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents an Investigation entity within the system.
 */
public class Investigation {

    private int id;
    private Timestamp closedAt;
    private Timestamp createdAt;
    private String description;
    private int deviationId;
    private int leadInvestigatorId;
    private Enums.InvestigationMethodologyEnum methodology;
    private Timestamp startedAt;
    private Enums.InvestigationStatusEnum status;
    private String title;
    private Timestamp updatedAt;

    /**
     * Gets the unique ID for this investigation.
     * @return the investigation ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID for this investigation.
     * @param id the investigation ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the closed timestamp of this investigation.
     * @return the closed timestamp.
     */
    public Timestamp getClosedAt() {
        return closedAt;
    }

    /**
     * Sets the closed timestamp of this investigation.
     * @param closedAt the closed timestamp to set.
     */
    public void setClosedAt(Timestamp closedAt) {
        this.closedAt = closedAt;
    }

    /**
     * Gets the created timestamp of this investigation.
     * @return the created timestamp.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the created timestamp of this investigation.
     * @param createdAt the created timestamp to set.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the description of this investigation.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this investigation.
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the deviation ID associated with this investigation.
     * @return the deviation ID.
     */
    public int getDeviationId() {
        return deviationId;
    }

    /**
     * Sets the deviation ID associated with this investigation.
     * @param deviationId the deviation ID to set.
     */
    public void setDeviationId(int deviationId) {
        this.deviationId = deviationId;
    }

    /**
     * Gets the lead investigator ID of this investigation.
     * @return the lead investigator ID.
     */
    public int getLeadInvestigatorId() {
        return leadInvestigatorId;
    }

    /**
     * Sets the lead investigator ID of this investigation.
     * @param leadInvestigatorId the lead investigator ID to set.
     */
    public void setLeadInvestigatorId(int leadInvestigatorId) {
        this.leadInvestigatorId = leadInvestigatorId;
    }

    /**
     * Gets the methodology of this investigation.
     * @return the investigation methodology.
     */
    public Enums.InvestigationMethodologyEnum getMethodology() {
        return methodology;
    }

    /**
     * Sets the methodology of this investigation.
     * @param methodology the investigation methodology to set.
     */
    public void setMethodology(Enums.InvestigationMethodologyEnum methodology) {
        this.methodology = methodology;
    }

    /**
     * Gets the started timestamp of this investigation.
     * @return the started timestamp.
     */
    public Timestamp getStartedAt() {
        return startedAt;
    }

    /**
     * Sets the started timestamp of this investigation.
     * @param startedAt the started timestamp to set.
     */
    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    /**
     * Gets the status of this investigation.
     * @return the investigation status.
     */
    public Enums.InvestigationStatusEnum getStatus() {
        return status;
    }

    /**
     * Sets the status of this investigation.
     * @param status the investigation status to set.
     */
    public void setStatus(Enums.InvestigationStatusEnum status) {
        this.status = status;
    }

    /**
     * Gets the title of this investigation.
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this investigation.
     * @param title the title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the updated timestamp of this investigation.
     * @return the updated timestamp.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the updated timestamp of this investigation.
     * @param updatedAt the updated timestamp to set.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Returns the string representation of this investigation.
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "Investigation{" +
                "id=" + id +
                ", closedAt=" + closedAt +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", deviationId=" + deviationId +
                ", leadInvestigatorId=" + leadInvestigatorId +
                ", methodology=" + methodology +
                ", startedAt=" + startedAt +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}