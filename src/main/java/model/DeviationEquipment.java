package model;

import java.sql.Timestamp;

/**
 * Represents a deviation equipment record.
 */
public class DeviationEquipment {

    private int deviationId;
    private int equipmentId;

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
     * Gets the equipment ID.
     *
     * @return The equipment ID.
     */
    public int getEquipmentId() {
        return equipmentId;
    }

    /**
     * Sets the equipment ID.
     *
     * @param equipmentId The equipment ID to set.
     */
    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public String toString() {
        return "DeviationEquipment{" +
                "deviationId=" + deviationId +
                ", equipmentId=" + equipmentId +
                '}';
    }
}