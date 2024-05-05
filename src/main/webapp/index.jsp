<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Deviation Report</title>
    <link rel="stylesheet" href="https://bootstrapdemos.wrappixel.com/spike/dist/assets/css/styles.css"> 
</head>
<body>
    <h1>Create Deviation Report</h1>
    <form action="submitDeviation" method="post">
        <fieldset>
            <legend>Basic Information</legend>
            <label for="dateOccurrence">Date of Occurrence:</label>
            <input type="date" id="dateOccurrence" name="dateOccurrence" required><br>

            <label for="timeIdentification">Time of Identification:</label>
            <input type="time" id="timeIdentification" name="timeIdentification" required><br>

            <label for="dateIdentification">Date of Identification:</label>
            <input type="date" id="dateIdentification" name="dateIdentification" required><br>

            <div id="delayJustificationContainer" style="display:none;">
                <label for="justificationForDelay">Justification for Delay:</label>
                <textarea id="justificationForDelay" name="justificationForDelay"></textarea>
            </div>
        </fieldset>

        <fieldset>
            <legend>Deviation Details</legend>
            <label for="deviationType">Deviation Type:</label>
            <select id="deviationType" name="deviationType" onchange="showConditionalFields(this.value)">
                <option value="">Select Type</option>
                <option value="Product">Product</option>
                <option value="Material">Material</option>
                <option value="Equipment">Equipment</option>
                <option value="Document">Document</option>
            </select><br>

            <div id="productInfo" style="display:none;">
                <label for="productSelection">Product:</label>
                <select id="productSelection" name="productSelection">
                    <!-- Options will be loaded based on server-side data -->
                </select><br>
            </div>

            <div id="materialInfo" style="display:none;">
                <label for="materialSelection">Material:</label>
                <select id="materialSelection" name="materialSelection">
                    <!-- Options will be loaded based on server-side data -->
                </select>
                <label for="lotNumber">Lot Number:</label>
                <input type="text" id="lotNumber" name="lotNumber"><br>
            </div>

            <div id="equipmentInfo" style="display:none;">
                <label for="equipmentId">Equipment ID:</label>
                <input type="text" id="equipmentId" name="equipmentId"><br>
            </div>

            <div id="documentInfo" style="display:none;">
                <label for="documentSelection">Document:</label>
                <select id="documentSelection" name="documentSelection">
                    <!-- Options will be loaded based on server-side data -->
                </select><br>
            </div>
        </fieldset>

        <fieldset>
            <legend>Description and Impact</legend>
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea><br>

            <label for="impactOnBatches">Impact on batches involved:</label>
            <input type="checkbox" id="impactOnBatches" name="impactOnBatches" value="Yes" onchange="toggleBatchAffected(this.checked)">
            <div id="batchInvolvedInfo" style="display:none;">
                <label for="batchesInvolved">Batches Involved:</label>
                <input type="text" id="batchesInvolved" name="batchesInvolved"><br>
            </div>
        </fieldset>

        <fieldset>
            <legend>Additional Information</legend>
            <label for="additionalComments">Additional Comments:</label>
            <textarea id="additionalComments" name="additionalComments"></textarea><br>

            <label for="fileAttachment">File Attachment:</label>
            <input type="file" id="fileAttachment" name="fileAttachment"><br>
        </fieldset>

        <input type="submit" value="Submit">
    </form>

    <script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function() {
        const deviationTypeSelect = document.getElementById('deviationType');
        const dateIdentification = document.getElementById('dateIdentification');

        // Show or hide conditional fields based on deviation type selected
        function showConditionalFields(value) {
            hideAllConditionalFields();
            switch(value) {
                case 'Product':
                    document.getElementById('productInfo').style.display = 'block';
                    break;
                case 'Material':
                    document.getElementById('materialInfo').style.display = 'block';
                    break;
                case 'Equipment':
                    document.getElementById('equipmentInfo').style.display = 'block';
                    break;
                case 'Document':
                    document.getElementById('documentInfo').style.display = 'block';
                    break;
                default:
                    hideAllConditionalFields();
            }
        }

        // Hide all conditional fields initially or as needed
        function hideAllConditionalFields() {
            document.getElementById('productInfo').style.display = 'none';
            document.getElementById('materialInfo').style.display = 'none';
            document.getElementById('equipmentInfo').style.display = 'none';
            document.getElementById('documentInfo').style.display = 'none';
        }

        // Show or hide batches involved input based on checkbox
        function toggleBatchAffected(checked) {
            document.getElementById('batchInvolvedInfo').style.display = checked ? 'block' : 'none';
        }

        // Check for reporting delay and show justification if needed
        function checkReportingDelay() {
            const currentDateTime = new Date();
            const identificationDatetime = new Date(dateIdentification.value);
            if (currentDateTime - identificationDatetime > 86400000) { // 86400000ms = 24 hours
                document.getElementById('delayJustificationContainer').style.display = 'block';
            } else {
                document.getElementById('delayJustificationContainer').style.display = 'none';
            }
        }

        // Bind change events to form elements
        deviationTypeSelect.addEventListener('change', function() {
            showConditionalFields(this.value);
        });

        dateIdentification.addEventListener('change', checkReportingDelay);

        const form = document.querySelector('form');

        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault(); // Prevent form submission
                alert('Please fill all required fields and correct the errors.');
            }
            // Additional custom validations can be added here
        });

        // Initialize
        hideAllConditionalFields(); // Make sure to hide all conditional fields on initial load
        checkReportingDelay(); // Check reporting delay on initial load
    });</script>
</body>
</html>