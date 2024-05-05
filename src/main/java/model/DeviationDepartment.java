package model;

import java.io.Serializable;

/**
 * Represents a record in the 'deviation_departments' table.
 * This class maps the relationship between deviations and departments.
 */
public class DeviationDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    private int departmentId;
    private int deviationId;

    /**
     * Gets the department ID associated with the deviation.
     *
     * @return The department ID.
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the department ID associated with the deviation.
     *
     * @param departmentId The department ID to set.
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Gets the deviation ID associated with the department.
     *
     * @return The deviation ID.
     */
    public int getDeviationId() {
        return deviationId;
    }

    /**
     * Sets the deviation ID associated with the department.
     *
     * @param deviationId The deviation ID to set.
     */
    public void setDeviationId(int deviationId) {
        this.deviationId = deviationId;
    }

    @Override
    public String toString() {
        return "DeviationDepartment{" +
                "departmentId=" + departmentId +
                ", deviationId=" + deviationId +
                '}';
    }
}