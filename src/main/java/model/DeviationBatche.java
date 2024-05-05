package model;

/**
 * Represents a record from the 'deviation_batches' table.
 */
public class DeviationBatche {

    private int batchId;
    private int deviationId;

    /**
     * Creates a new instance of DeviationBatche.
     */
    public DeviationBatche() {}

    /**
     * Gets the batch ID.
     *
     * @return The batch ID.
     */
    public int getBatchId() {
        return batchId;
    }

    /**
     * Sets the batch ID.
     *
     * @param batchId The batch ID to set.
     */
    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

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

    @Override
    public String toString() {
        return "DeviationBatche{" +
                "batchId=" + batchId +
                ", deviationId=" + deviationId +
                '}';
    }
}