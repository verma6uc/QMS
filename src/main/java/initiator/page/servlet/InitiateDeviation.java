package initiator.page.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DeviationDAO;
import dto.DeviationInitiateDTO;
import model.User;

@WebServlet("/initiateDeviation")
public class InitiateDeviation extends HttpServlet {

	// Assuming you have a mechanism to obtain a Connection object (e.g., from a
	// connection pool)
	private DeviationDAO deviationDAO; // Initialize in init() method

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		deviationDAO = new DeviationDAO();

		User user = null;

		if (request.getSession().getAttribute("user") != null) {
			user = (User) request.getSession().getAttribute("user");
		}

		// 1. Retrieve form data
		String dateOfOccurrence = request.getParameter("dateOfOccurrence");
		String dateOfIdentification = request.getParameter("dateOfIdentification");
		String timeOfIdentification = request.getParameter("timeOfIdentification");
		String justificationForDelay = request.getParameter("justificationForDelay");
		String eventRelatedType = request.getParameter("eventRelatedTo");
		String description = request.getParameter("description");
		String deviationRootCause = request.getParameter("deviationRootCause");
		String riskAssessment = request.getParameter("riskAssessment");
		String standardProcedure = request.getParameter("standardProcedure");
		String immediateCorrectiveAction = request.getParameter("immediateCorrectiveAction");
		String impactOnOtherBatches = request.getParameter("impactOnOtherBatches");

		// ... (Similarly, retrieve other fields based on eventRelatedType) ...

		// 2. Basic validation (more thorough validation can be added)
		if (dateOfOccurrence.isEmpty() || dateOfIdentification.isEmpty() || eventRelatedType.isEmpty()
				|| description.isEmpty()) {
			// Handle missing required fields (e.g., send error response)
			return;
		}

		// 3. Create DTO and populate with retrieved data
		DeviationInitiateDTO dto = new DeviationInitiateDTO();
		dto.setDateOfOccurrence(dateOfOccurrence);
		dto.setDateOfIdentification(dateOfIdentification);
		dto.setTimeOfIdentification(timeOfIdentification);
		dto.setJustificationForDelay(justificationForDelay);
		dto.setEventRelatedType(eventRelatedType);
		dto.setDescription(description);
		dto.setInitiatedByUserId(user.getId());
		dto.setRiskAssessment(riskAssessment);
		dto.setStandardProcedure(standardProcedure);
		dto.setImmediateCorrectiveAction(immediateCorrectiveAction);
		dto.setDeviationRootCause(deviationRootCause);
		dto.setImpactOnOtherBatches(Boolean.parseBoolean(impactOnOtherBatches));
		// ... (Populate other DTO fields based on eventRelatedType) ...

		switch (eventRelatedType) {
		case "PRODUCT":
			if (!request.getParameter("productId").isEmpty()) {
				dto.setProductId(Integer.parseInt(request.getParameter("productId")));
			}
			if (!request.getParameter("batchIds").isEmpty()) {
				List<Integer> batchIds = Arrays.stream(request.getParameter("batchIds").split(","))
						.map(Integer::parseInt).collect(Collectors.toList());
				dto.setBatchIds(batchIds);
			}
			break;
		case "MATERIAL":
			if (!request.getParameter("materialId").isEmpty()) {
				dto.setMaterialId(Integer.parseInt(request.getParameter("materialId")));
			}
			dto.setLotNumber(request.getParameter("lotNumber"));
			break;
		case "EQUIPMENT":
			if (!request.getParameter("equipmentId").isEmpty()) {
				dto.setEquipmentId(Integer.parseInt(request.getParameter("equipmentId")));
			}
			break;
		case "DOCUMENT":
			if (!request.getParameter("documentId").isEmpty()) {
				dto.setDocumentId(Integer.parseInt(request.getParameter("documentId")));
			}
			break;
		// ... handle other cases ...
		default:
			// Handle the default case or throw an exception
			break;
		}

		// 4. Call DAO method to initiate deviation

		try {
			deviationDAO.initiateDeviation(dto);

			// 5. Send success response
			String jsonResponse = new Gson()
					.toJson(new StandardResponse("success", "Deviation initiated successfully."));
			response.setContentType("application/json");
			response.getWriter().print(jsonResponse);
		} catch (SQLException e) {
			// Handle database errors (e.g., send error response with details)
			e.printStackTrace(); // Log the error for debugging
		}
	}

	@Override
	public void init() throws ServletException {
		// Initialize deviationDAO here (e.g., using dependency injection)
		// Example: deviationDAO = new DeviationDAO((Connection)
		// getServletContext().getAttribute("DBConnection"));
	}

	// Helper class for standardized JSON responses
	private static class StandardResponse {
		private String status;
		private String message;

		public StandardResponse(String status, String message) {
			this.status = status;
			this.message = message;
		}
	}
}