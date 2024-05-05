package model;

import java.sql.Timestamp;

/**
 * Represents a CAPA file.
 */
public class CapaFile {

    /**
     * The ID of the CAPA file.
     */
    private int id;

    /**
     * The ID of the associated CAPA.
     */
    private int capaId;

    /**
     * The file path.
     */
    private String filePath;

    /**
     * The file description.
     */
    private String fileDescription;

    /**
     * The ID of the user who uploaded the file.
     */
    private int uploadedBy;

    /**
     * The timestamp of when the file was uploaded.
     */
    private Timestamp uploadedAt;

    /**
     * The timestamp of when the record was created.
     */
    private Timestamp createdAt;

    /**
     * The timestamp of when the record was last updated.
     */
    private Timestamp updatedAt;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapaId() {
        return capaId;
    }

    public void setCapaId(int capaId) {
        this.capaId = capaId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public int getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(int uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Timestamp getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString method

    @Override
    public String toString() {
        return "CapaFile{" +
                "id=" + id +
                ", capaId=" + capaId +
                ", filePath='" + filePath + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                ", uploadedBy=" + uploadedBy +
                ", uploadedAt=" + uploadedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}