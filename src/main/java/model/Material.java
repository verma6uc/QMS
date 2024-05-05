package model;

import java.sql.Timestamp;

/**
 * Represents a material used in the system.
 */
public class Material {

    private int id;
    private String name;
    private String description;
    private Enums.MaterialCategory category;
    private Enums.MaterialStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Material() {
    }

    /**
     * Gets the ID of the material.
     *
     * @return The ID of the material.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the material.
     *
     * @param id The ID of the material.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the material.
     *
     * @return The name of the material.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the material.
     *
     * @param name The name of the material.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the material.
     *
     * @return The description of the material.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the material.
     *
     * @param description The description of the material.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the category of the material.
     *
     * @return The category of the material.
     */
    public Enums.MaterialCategory getCategory() {
        return category;
    }

    /**
     * Sets the category of the material.
     *
     * @param category The category of the material.
     */
    public void setCategory(Enums.MaterialCategory category) {
        this.category = category;
    }

    /**
     * Gets the status of the material.
     *
     * @return The status of the material.
     */
    public Enums.MaterialStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the material.
     *
     * @param status The status of the material.
     */
    public void setStatus(Enums.MaterialStatus status) {
        this.status = status;
    }

    /**
     * Gets the timestamp of when the material was created.
     *
     * @return The timestamp of when the material was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp of when the material was created.
     *
     * @param createdAt The timestamp of when the material was created.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp of when the material was last updated.
     *
     * @return The timestamp of when the material was last updated.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp of when the material was last updated.
     *
     * @param updatedAt The timestamp of when the material was last updated.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}