package initiator.page.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DeviationDAO;
import dto.DepartmentalDeviationReviewDTO;
import model.User;
import model.Enums.DeviationStatus;

@WebServlet("/DepartmentalReviewServlet")
public class DepartmentalReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = null;

		if (request.getSession().getAttribute("user") != null) {
			user = (User) request.getSession().getAttribute("user");
		}

		// 1. Get parameters from the request (assuming names match DTO fields)
		Integer deviationId = Integer.parseInt(request.getParameter("deviationId"));
		String reviewComments = request.getParameter("reviewComments");
		String decision = request.getParameter("decision");
		String justificationForDecision = request.getParameter("justificationForDecision");

		// 2. Validate input (basic checks, more validation can be added)
		if (reviewComments == null || reviewComments.trim().isEmpty() || decision == null
				|| decision.trim().isEmpty()) {
			sendErrorResponse(response, "Missing required fields (reviewComments, decision).");
			return;
		}

		// 3. Create DTO and call DAO
		DeviationStatus decisionEnum = DeviationStatus.valueOf(decision.toUpperCase().replace(" ", "_"));
		decision = decisionEnum.name();
		DepartmentalDeviationReviewDTO dto = new DepartmentalDeviationReviewDTO(deviationId, reviewComments,
				decisionEnum.name(), justificationForDecision, user.getId());
		DeviationDAO dao = new DeviationDAO();

		try {
			dao.updateDeviationStatusAndAddComment(dto);
			sendSuccessResponse(response, "Deviation review submitted successfully.");
		} catch (SQLException e) {
			e.printStackTrace(); // Log the error for debugging
			sendErrorResponse(response, "Database error occurred during review submission.");
		}
	}

	// Helper methods for JSON responses
	private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("message", message);
		response.getWriter().write(new Gson().toJson(errorResponse));
	}

	private void sendSuccessResponse(HttpServletResponse response, String message) throws IOException {
		Map<String, String> successResponse = new HashMap<>();
		successResponse.put("message", message);
		response.getWriter().write(new Gson().toJson(successResponse));
	}
}