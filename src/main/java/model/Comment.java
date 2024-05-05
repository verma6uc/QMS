package model;

import java.sql.Timestamp;

/**
 * Represents a comment entity.
 */
public class Comment {

    private int id;
    private int authorId;
    private String content;
    private Timestamp createdAt;
    private int relatedId;
    private String relatedTable;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the comment.
     *
     * @return the ID of the comment
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the comment.
     *
     * @param id the ID of the comment
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the author of the comment.
     *
     * @return the ID of the author
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Sets the ID of the author of the comment.
     *
     * @param authorId the ID of the author
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     * Gets the content of the comment.
     *
     * @return the content of the comment
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the comment.
     *
     * @param content the content of the comment
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the timestamp when the comment was created.
     *
     * @return the timestamp when the comment was created
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the comment was created.
     *
     * @param createdAt the timestamp when the comment was created
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the ID of the related entity.
     *
     * @return the ID of the related entity
     */
    public int getRelatedId() {
        return relatedId;
    }

    /**
     * Sets the ID of the related entity.
     *
     * @param relatedId the ID of the related entity
     */
    public void setRelatedId(int relatedId) {
        this.relatedId = relatedId;
    }

    /**
     * Gets the name of the related table.
     *
     * @return the name of the related table
     */
    public String getRelatedTable() {
        return relatedTable;
    }

    /**
     * Sets the name of the related table.
     *
     * @param relatedTable the name of the related table
     */
    public void setRelatedTable(String relatedTable) {
        this.relatedTable = relatedTable;
    }

    /**
     * Gets the timestamp when the comment was last updated.
     *
     * @return the timestamp when the comment was last updated
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the comment was last updated.
     *
     * @param updatedAt the timestamp when the comment was last updated
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", relatedId=" + relatedId +
                ", relatedTable='" + relatedTable + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}