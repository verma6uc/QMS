### Deviation Initiation Workflow Details

#### Step 1: Trigger

*   **Trigger Condition**: Occurrence of an event that deviates from standard procedures.
*   **Role Associated**: Any authorized user (e.g., Operator, Technician, Quality Control Analyst) who observes or identifies the deviation.

#### Step 2: User Action

*   **Page**: Deviation Initiation Form
*   **Page Slug**: `/deviation-initiation`
*   **Section 1: Deviation Details**
    *   **Fields**:
        *   **Deviation No.**: Automatically generated once the form is submitted.
        *   **Initiating Department**: Dropdown, auto-filled based on the user's login credentials but editable if permissions allow.
        *   **Date of Occurrence**: Date picker, user-selected.
        *   **Date of Identification**: Date picker, user-selected.
        *   **Time of Identification**: Time entry field.
        *   **Date of Initiation**: Automatically set to the server system date, not editable by the user.
    *   **Role Associated**: Initiator (the user filling out the form).

#### Step 3: System Action

*   **System Process**:
    *   **Generate Unique Deviation Number**: The system auto-generates a unique identifier for the deviation when the form is first saved or submitted.
    *   **Record Initiation Date**: Captures the current date from the server as the initiation date.
    *   **Justification for Delay**: A text field that becomes mandatory if the initiation occurs more than 24 hours after the identification date. This field should prompt for justification of the delay.
*   **Role Associated**: System/backend process.

#### Step 4: Submission

*   **Page**: Deviation Initiation Form
*   **Section 2: Additional Deviation Information**
    *   **Fields**:
        *   **Event Related To**: Dropdown menu with options like Product, Material, Equipment/Instrument, Document, Software, Others. Selection of a category dynamically adjusts the form to request more specific information relevant to the chosen category.
        *   **Description of the Deviation/Incident**: Text field for detailed description (max 5000 characters).
        *   **Standard Procedure**: Text field describing the standard procedure that was deviated from (max 5000 characters).
        *   **Reason/Root Cause for Deviation/Incident**: Text field for preliminary thoughts on why the deviation occurred, if apparent (max 5000 characters).
        *   **Remediation Action Taken/Immediate Corrective Action**: Text field for any immediate actions taken in response to the deviation (max 5000 characters).
    *   **Dynamic Fields Based on Event Related To**:
        *   **Product Details**: Activated if 'Product' is selected. Fields for Product Name, Batch No., and Stage.
        *   **Material Details**: Activated if 'Material' is selected. Fields for Material Name, Batch/Lot No.
        *   **Equipment Details**: Activated if 'Equipment/Instrument' is selected. Fields for Equipment Name, Equipment ID.
        *   **Document Details**: Activated if 'Document' is selected. Fields for Document Name, Version No., Unique Code.
        *   **Other Details**: Activated if 'Others' is selected. Free text field for additional information.
*   **Role Associated**: Initiator.

This setup for the deviation initiation process ensures that all necessary information is captured systematically and accurately, facilitating a structured response and investigation into the deviation. The design of this form and workflow also ensures adherence to regulatory and company-specific requirements, enhancing both compliance and efficiency.