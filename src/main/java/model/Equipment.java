package model;

import java.sql.Timestamp;

/**
 * Represents an equipment record.
 */
public class Equipment {

    private int id;
    private String name;
    private String description;
    private Enums.EquipmentCategory category;
    private String location;
    private Enums.EquipmentStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the equipment.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the equipment.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the equipment.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the equipment.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the equipment.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the equipment.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the category of the equipment.
     *
     * @return the category
     */
    public Enums.EquipmentCategory getCategory() {
        return category;
    }

    /**
     * Sets the category of the equipment.
     *
     * @param category the category to set
     */
    public void setCategory(Enums.EquipmentCategory category) {
        this.category = category;
    }

    /**
     * Gets the location of the equipment.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the equipment.
     *
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the status of the equipment.
     *
     * @return the status
     */
    public Enums.EquipmentStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the equipment.
     *
     * @param status the status to set
     */
    public void setStatus(Enums.EquipmentStatus status) {
        this.status = status;
    }

    /**
     * Gets the creation timestamp of the equipment.
     *
     * @return the createdAt timestamp
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the equipment.
     *
     * @param createdAt the createdAt timestamp to set
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last updated timestamp of the equipment.
     *
     * @return the updatedAt timestamp
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last updated timestamp of the equipment.
     *
     * @param updatedAt the updatedAt timestamp to set
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}