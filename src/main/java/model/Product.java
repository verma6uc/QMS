package model;

import java.sql.Timestamp;

/**
 * Represents a product.
 */
public class Product {

    private int id;
    private String name;
    private String description;
    private Enums.ProductCategory category;
    private Enums.ProductStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the product.
     *
     * @return the ID of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id the ID of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the product.
     *
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description the description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the category of the product.
     *
     * @return the category of the product
     */
    public Enums.ProductCategory getCategory() {
        return category;
    }

    /**
     * Sets the category of the product.
     *
     * @param category the category of the product
     */
    public void setCategory(Enums.ProductCategory category) {
        this.category = category;
    }

    /**
     * Gets the status of the product.
     *
     * @return the status of the product
     */
    public Enums.ProductStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the product.
     *
     * @param status the status of the product
     */
    public void setStatus(Enums.ProductStatus status) {
        this.status = status;
    }

    /**
     * Gets the creation timestamp of the product.
     *
     * @return the creation timestamp of the product
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the product.
     *
     * @param createdAt the creation timestamp of the product
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the update timestamp of the product.
     *
     * @return the update timestamp of the product
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the update timestamp of the product.
     *
     * @param updatedAt the update timestamp of the product
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Product{" +
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