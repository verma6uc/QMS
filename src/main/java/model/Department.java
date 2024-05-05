package model;

import java.sql.Timestamp;

/**
 * Represents a department within the organization.
 */
public class Department {

    private int id;
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the unique identifier for the department.
     *
     * @return The department ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the department.
     *
     * @param id The department ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the department.
     *
     * @return The department name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the department.
     *
     * @param name The department name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the department.
     *
     * @return The department description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the department.
     *
     * @param description The department description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the timestamp when the department was created.
     *
     * @return The department creation timestamp.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the department was created.
     *
     * @param createdAt The department creation timestamp.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the department was last updated.
     *
     * @return The department last updated timestamp.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the department was last updated.
     *
     * @param updatedAt The department last updated timestamp.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}