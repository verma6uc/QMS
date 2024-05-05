### Investigation and CAPA Workflow Details

#### Step 1: Investigation Trigger

*   **Page**: Investigation Initiation Dashboard
*   **Page Slug**: `/investigation-initiation`
*   **Section 1: Investigation Trigger**
    *   **Functionality**: Automatically initiates an investigation based on predefined risk assessment scores and the nature of the deviation as determined by previous reviews.
    *   **Role Associated**: QA Manager or designated investigator.
    *   **Fields**:
        *   **Trigger Reason**: Displayed as a read-only field, explaining why the investigation was triggered (auto-filled based on system rules).

#### Step 2: Investigation Process

*   **Page**: Investigation Details Form
*   **Page Slug**: `/investigation-details-form`
*   **Section 2: Investigation Process**
    *   **Fields**:
        *   **Investigation Tools**: Dropdown menu to select the tool(s) used such as 5 Whys, Fishbone Diagrams, etc.
        *   **Findings**: Text area for entering detailed findings from the investigation (max 5000 characters).
    *   **Role Associated**: Investigators assigned to the case.
    *   **Functionality**: Allows investigators to document their process, tools used, and findings in a structured format.

#### Step 3: CAPA Planning

*   **Page**: CAPA Planning Form
*   **Page Slug**: `/capa-planning`
*   **Section 3: CAPA Planning**
    *   **Fields**:
        *   **Action Items**: Detailed list of corrective and preventive actions proposed based on the investigation findings.
        *   **Responsible Stakeholders**: Dropdown to assign responsibilities for each CAPA item to different stakeholders or departments.
    *   **Role Associated**: QA Manager or CAPA Coordinator.
    *   **Functionality**: Facilitates the definition and assignment of corrective and preventive actions, ensuring each action item is clearly defined and assigned.

#### Step 4: Implementation and Monitoring

*   **Page**: CAPA Implementation Dashboard
*   **Page Slug**: `/capa-implementation`
*   **Section 4: Implementation and Monitoring**
    *   **Fields**:
        *   **Implementation Status**: Tracks the status of each CAPA item (e.g., Not Started, In Progress, Completed).
        *   **Effectiveness Review**: Scheduled reviews to assess the effectiveness of implemented actions, with fields to enter follow-up dates and review outcomes.
    *   **Role Associated**: CAPA Implementers and QA Reviewers.
    *   **Functionality**: Allows monitoring of CAPA implementation progress and the scheduling of effectiveness reviews to ensure that actions are not only implemented but are also effective in preventing recurrence.

### Notes:

*   **Interactive Tools**: Integration of dynamic forms and workflow tools to guide investigators and CAPA coordinators through the process.
*   **Real-Time Updates and Notifications**: Ensure that all stakeholders are informed of progress and changes in CAPA status or investigation findings.
*   **Comprehensive Reporting**: Provides robust reporting tools to analyze trends in investigations and CAPA effectiveness, supporting continuous improvement.

This workflow supports a structured approach to managing deviations through detailed investigations and the systematic implementation of corrective and preventive actions, ensuring that issues are not only resolved but also analyzed for root causes to prevent future occurrences.