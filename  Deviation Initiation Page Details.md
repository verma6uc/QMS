### Deviation Initiation Page Details

**Page Name:** Deviation Initiation Form  
**Slug:** `/deviation-initiation-form`  
**Role:** Initiator

**Description:**  
This page allows initiators to formally start the process of recording a deviation from standard procedures. It gathers all necessary details about the deviation event, such as when and where it occurred, and categorizes the event for further action.

**List of Sections:**

1.  **Deviation Information**
    
    *   **Name:** Deviation Details
    *   **Functionality:**
        *   Users can enter the unique deviation details including the event type, relevant product/material/equipment/document/software, and other details.
        *   Dropdown to select the event related to specific categories (Product, Material, Equipment, Document, Software, Others) which dynamically updates the form to input related information like Product name, Batch No, etc.
        *   Conditional fields display based on the selection in the 'Event Related To' dropdown. For instance, if 'Product' is selected, fields to enter Product name, Batch No, and Stage appear.
        *   Date and time fields to capture the Date of Occurrence, Date of Identification, and Time of Identification. Automatic validation checks if the date of initiation is more than 24 hours after the identification, prompting a justification field.
    *   **Type of Section:** Form Group
2.  **Justification for Delay**
    
    *   **Name:** Delay Justification
    *   **Functionality:**
        *   Text area appears only if the initiation of the deviation report is delayed beyond 24 working hours from the identification of the deviation. This section is conditional and prompts the user to provide a detailed justification for the delay.
    *   **Type of Section:** Conditional Text Area
3.  **Risk Assessment**
    
    *   **Name:** Initial Risk Assessment
    *   **Functionality:**
        *   Provides text areas for users to assess and describe potential risks on Product, Facility, Equipment, and other categories if applicable.
        *   Each category has its own text area to ensure detailed documentation of potential impacts and initial observations about the deviation.
    *   **Type of Section:** Form Group with Multiple Text Areas
4.  **Impact and Batch Details**
    
    *   **Name:** Impact Details
    *   **Functionality:**
        *   Checkbox options to indicate if the deviation impacts the status of batches involved. If 'Yes' is selected, multiple selection fields become available to choose affected batches directly from an integrated SAP system or manually enter batch details.
        *   Similar options are available for other batches potentially affected by the deviation.
    *   **Type of Section:** Checkbox with Conditional Dropdowns
5.  **Documentation and Submission**
    
    *   **Name:** Documentation
    *   **Functionality:**
        *   Allows users to attach files related to the deviation, with a drag and drop interface or file browser. Supports multiple file uploads up to 99 files with a size limit of 1GB per file.
        *   Text area for entering any additional remarks regarding the deviation.
        *   Submission buttons include 'Save', 'Save & Exit', and 'Submit', which triggers the next step in the workflow depending on the user’s selection.
    *   **Type of Section:** File Upload and Text Area

This page layout ensures that all necessary data about a deviation is captured effectively, enabling a thorough review and appropriate follow-up actions within the system’s workflows.