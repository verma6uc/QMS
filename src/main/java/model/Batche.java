package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Represents a batch of products.
 */
public class Batche {

    private int id;
    private String batchNumber;
    private Date manufactureDate;
    private Date expiryDate;
    private Enums.BatchStatus status;
    private int productId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the batch.
     *
     * @return The ID of the batch.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the batch.
     *
     * @param id The ID of the batch.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the batch number.
     *
     * @return The batch number.
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Sets the batch number.
     *
     * @param batchNumber The batch number.
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Gets the manufacture date of the batch.
     *
     * @return The manufacture date of the batch.
     */
    public Date getManufactureDate() {
        return manufactureDate;
    }

    /**
     * Sets the manufacture date of the batch.
     *
     * @param manufactureDate The manufacture date of the batch.
     */
    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    /**
     * Gets the expiry date of the batch.
     *
     * @return The expiry date of the batch.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the batch.
     *
     * @param expiryDate The expiry date of the batch.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the status of the batch.
     *
     * @return The status of the batch.
     */
    public Enums.BatchStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the batch.
     *
     * @param status The status of the batch.
     */
    public void setStatus(Enums.BatchStatus status) {
        this.status = status;
    }

    /**
     * Gets the ID of the product associated with the batch.
     *
     * @return The ID of the product associated with the batch.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the ID of the product associated with the batch.
     *
     * @param productId The ID of the product associated with the batch.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Gets the timestamp when the batch was created.
     *
     * @return The timestamp when the batch was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the batch was created.
     *
     * @param createdAt The timestamp when the batch was created.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the batch was last updated.
     *
     * @return The timestamp when the batch was last updated.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the batch was last updated.
     *
     * @param updatedAt The timestamp when the batch was last updated.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Batche{" +
                "id=" + id +
                ", batchNumber='" + batchNumber + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", expiryDate=" + expiryDate +
                ", status=" + status +
                ", productId=" + productId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}