package model;

import java.sql.Timestamp;

/**
 * Represents a role in the system.
 */
public class Role {

    private int id;
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the role.
     *
     * @return the ID of the role
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the role.
     *
     * @param id the ID of the role
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     *
     * @return the name of the role
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     *
     * @param name the name of the role
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the role.
     *
     * @return the description of the role
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the role.
     *
     * @param description the description of the role
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the timestamp when the role was created.
     *
     * @return the timestamp when the role was created
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the role was created.
     *
     * @param createdAt the timestamp when the role was created
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the role was last updated.
     *
     * @return the timestamp when the role was last updated
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the role was last updated.
     *
     * @param updatedAt the timestamp when the role was last updated
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}