package model;

import java.io.Serializable;

/**
 * Represents a deviation product.
 */
public class DeviationProduct implements Serializable {

    private int deviationId;
    private int productId;

    /**
     * Gets the deviation ID.
     *
     * @return The deviation ID.
     */
    public int getDeviationId() {
        return deviationId;
    }

    /**
     * Sets the deviation ID.
     *
     * @param deviationId The deviation ID to set.
     */
    public void setDeviationId(int deviationId) {
        this.deviationId = deviationId;
    }

    /**
     * Gets the product ID.
     *
     * @return The product ID.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the product ID.
     *
     * @param productId The product ID to set.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "DeviationProduct{" +
                "deviationId=" + deviationId +
                ", productId=" + productId +
                '}';
    }
}