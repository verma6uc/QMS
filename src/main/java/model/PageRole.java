package model;

import java.sql.Timestamp;

/**
 * Represents a page role association.
 */
public class PageRole {

    private int pageId;
    private int roleId;
    private Timestamp accessGranted;

    /**
     * Gets the page ID.
     *
     * @return The page ID.
     */
    public int getPageId() {
        return pageId;
    }

    /**
     * Sets the page ID.
     *
     * @param pageId The page ID to set.
     */
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    /**
     * Gets the role ID.
     *
     * @return The role ID.
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID.
     *
     * @param roleId The role ID to set.
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the access granted timestamp.
     *
     * @return The access granted timestamp.
     */
    public Timestamp getAccessGranted() {
        return accessGranted;
    }

    /**
     * Sets the access granted timestamp.
     *
     * @param accessGranted The access granted timestamp to set.
     */
    public void setAccessGranted(Timestamp accessGranted) {
        this.accessGranted = accessGranted;
    }

    @Override
    public String toString() {
        return "PageRole{" +
                "pageId=" + pageId +
                ", roleId=" + roleId +
                ", accessGranted=" + accessGranted +
                '}';
    }
}