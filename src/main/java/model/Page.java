package model;

import java.sql.Timestamp;

/**
 * Represents a page in the system.
 */
public class Page {

    private int pageId;
    private String name;
    private String slug;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Gets the ID of the page.
     *
     * @return The page ID.
     */
    public int getPageId() {
        return pageId;
    }

    /**
     * Sets the ID of the page.
     *
     * @param pageId The page ID to set.
     */
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    /**
     * Gets the name of the page.
     *
     * @return The page name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the page.
     *
     * @param name The page name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the slug of the page.
     *
     * @return The page slug.
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Sets the slug of the page.
     *
     * @param slug The page slug to set.
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * Gets the timestamp when the page was created.
     *
     * @return The creation timestamp.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the page was created.
     *
     * @param createdAt The creation timestamp to set.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the page was last updated.
     *
     * @return The last updated timestamp.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the page was last updated.
     *
     * @param updatedAt The last updated timestamp to set.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageId=" + pageId +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}