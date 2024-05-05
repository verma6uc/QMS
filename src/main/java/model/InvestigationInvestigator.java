package model;

import java.sql.Timestamp;

/**
 * Represents an entry in the investigation_investigators table, linking investigators to investigations.
 */
public class InvestigationInvestigator {

    private int investigationId;
    private int investigatorId;
    private Timestamp assignedDate;

    /**
     * Gets the ID of the investigation.
     *
     * @return The investigation ID.
     */
    public int getInvestigationId() {
        return investigationId;
    }

    /**
     * Sets the ID of the investigation.
     *
     * @param investigationId The investigation ID to set.
     */
    public void setInvestigationId(int investigationId) {
        this.investigationId = investigationId;
    }

    /**
     * Gets the ID of the investigator.
     *
     * @return The investigator ID.
     */
    public int getInvestigatorId() {
        return investigatorId;
    }

    /**
     * Sets the ID of the investigator.
     *
     * @param investigatorId The investigator ID to set.
     */
    public void setInvestigatorId(int investigatorId) {
        this.investigatorId = investigatorId;
    }

    /**
     * Gets the date and time when the investigator was assigned to the investigation.
     *
     * @return The assigned date and time.
     */
    public Timestamp getAssignedDate() {
        return assignedDate;
    }

    /**
     * Sets the date and time when the investigator was assigned to the investigation.
     *
     * @param assignedDate The assigned date and time to set.
     */
    public void setAssignedDate(Timestamp assignedDate) {
        this.assignedDate = assignedDate;
    }

    @Override
    public String toString() {
        return "InvestigationInvestigator{" +
                "investigationId=" + investigationId +
                ", investigatorId=" + investigatorId +
                ", assignedDate=" + assignedDate +
                '}';
    }
}