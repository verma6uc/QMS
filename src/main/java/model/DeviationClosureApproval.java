package model;

import java.sql.Timestamp;

/**
 * Represents a deviation closure approval.
 */
public class DeviationClosureApproval {

    private int id;
    private int deviationId;
    private int approvedByUserId;
    private Timestamp approvalDate;
    private String comments;

    // Getters and Setters

    /**
     * Gets the ID of the deviation closure approval.
     *
     * @return The ID of the deviation closure approval.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the deviation closure approval.
     *
     * @param id The ID of the deviation closure approval.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the associated deviation.
     *
     * @return The ID of the associated deviation.
     */
    public int getDeviationId() {
        return deviationId;
    }

    /**
     * Sets the ID of the associated deviation.
     *
     * @param deviationId The ID of the associated deviation.
     */
    public void setDeviationId(int deviationId) {
        this.deviationId = deviationId;
    }

    /**
     * Gets the ID of the user who approved the closure.
     *
     * @return The ID of the user who approved the closure.
     */
    public int getApprovedByUserId() {
        return approvedByUserId;
    }

    /**
     * Sets the ID of the user who approved the closure.
     *
     * @param approvedByUserId The ID of the user who approved the closure.
     */
    public void setApprovedByUserId(int approvedByUserId) {
        this.approvedByUserId = approvedByUserId;
    }

    /**
     * Gets the date and time of the approval.
     *
     * @return The date and time of the approval.
     */
    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    /**
     * Sets the date and time of the approval.
     *
     * @param approvalDate The date and time of the approval.
     */
    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    /**
     * Gets any comments associated with the approval.
     *
     * @return The comments associated with the approval.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments associated with the approval.
     *
     * @param comments The comments associated with the approval.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    // toString

    @Override
    public String toString() {
        return "DeviationClosureApproval{" +
                "id=" + id +
                ", deviationId=" + deviationId +
                ", approvedByUserId=" + approvedByUserId +
                ", approvalDate=" + approvalDate +
                ", comments='" + comments + '\'' +
                '}';
    }
}