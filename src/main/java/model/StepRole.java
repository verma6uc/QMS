package model;

import java.sql.Timestamp;

/**
 * Represents a StepRole entity.
 */
public class StepRole {

    private int roleId;
    private int stepId;
    private Timestamp accessGranted;

    /**
     * Gets the role ID.
     *
     * @return the role ID
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID.
     *
     * @param roleId the role ID to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the step ID.
     *
     * @return the step ID
     */
    public int getStepId() {
        return stepId;
    }

    /**
     * Sets the step ID.
     *
     * @param stepId the step ID to set
     */
    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    /**
     * Gets the access granted timestamp.
     *
     * @return the access granted timestamp
     */
    public Timestamp getAccessGranted() {
        return accessGranted;
    }

    /**
     * Sets the access granted timestamp.
     *
     * @param accessGranted the access granted timestamp to set
     */
    public void setAccessGranted(Timestamp accessGranted) {
        this.accessGranted = accessGranted;
    }

    @Override
    public String toString() {
        return "StepRole{" +
                "roleId=" + roleId +
                ", stepId=" + stepId +
                ", accessGranted=" + accessGranted +
                '}';
    }
}