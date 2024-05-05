package model;

import java.sql.Timestamp;

/**
 * Represents a section within a page.
 */
public class Section {

    /**
     * The unique identifier for the section.
     */
    private int sectionId;

    /**
     * The name of the section.
     */
    private String name;

    /**
     * The functionality description of the section.
     */
    private String functionality;

    /**
     * The type of the section.
     */
    private String typeOfSection;

    /**
     * The identifier of the page this section belongs to.
     */
    private int pageId;

    /**
     * The timestamp when the section was created.
     */
    private Timestamp createdAt;

    /**
     * The timestamp when the section was last updated.
     */
    private Timestamp updatedAt;

    // Getters and Setters

    /**
     * Gets the unique identifier for the section.
     *
     * @return The section ID.
     */
    public int getSectionId() {
        return sectionId;
    }

    /**
     * Sets the unique identifier for the section.
     *
     * @param sectionId The section ID to set.
     */
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * Gets the name of the section.
     *
     * @return The section name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the section.
     *
     * @param name The section name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the functionality description of the section.
     *
     * @return The functionality description.
     */
    public String getFunctionality() {
        return functionality;
    }

    /**
     * Sets the functionality description of the section.
     *
     * @param functionality The functionality description to set.
     */
    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    /**
     * Gets the type of the section.
     *
     * @return The section type.
     */
    public String getTypeOfSection() {
        return typeOfSection;
    }

    /**
     * Sets the type of the section.
     *
     * @param typeOfSection The section type to set.
     */
    public void setTypeOfSection(String typeOfSection) {
        this.typeOfSection = typeOfSection;
    }

    /**
     * Gets the identifier of the page this section belongs to.
     *
     * @return The page ID.
     */
    public int getPageId() {
        return pageId;
    }

    /**
     * Sets the identifier of the page this section belongs to.
     *
     * @param pageId The page ID to set.
     */
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    /**
     * Gets the timestamp when the section was created.
     *
     * @return The creation timestamp.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the section was created.
     *
     * @param createdAt The creation timestamp to set.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the section was last updated.
     *
     * @return The update timestamp.
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when the section was last updated.
     *
     * @param updatedAt The update timestamp to set.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString method

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", name='" + name + '\'' +
                ", functionality='" + functionality + '\'' +
                ", typeOfSection='" + typeOfSection + '\'' +
                ", pageId=" + pageId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}