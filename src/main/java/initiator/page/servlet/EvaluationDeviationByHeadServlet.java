package initiator.page.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.QaDeviationRiskAssessmentDAO;
import dto.EvaluationDeviationByHeadDTO;
import model.Enums;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/EvaluationDeviationByHeadServlet")
public class EvaluationDeviationByHeadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private QaDeviationRiskAssessmentDAO evaluationDeviationByHeadDAO;

	public void init() {
		evaluationDeviationByHeadDAO = new QaDeviationRiskAssessmentDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		JsonObject jsonObject = new JsonObject();
		try {
			// Fetch data from request parameters
			int deviationId = Integer.parseInt(request.getParameter("deviationId"));
			Integer probabilityOfRecurrence = request.getParameter("probabilityOfRecurrence").isEmpty() ? null
					: Integer.parseInt(request.getParameter("probabilityOfRecurrence"));
			String probabilityJustification = request.getParameter("probabilityJustification");
			Integer additionalProcessingSteps = request.getParameter("additionalProcessingSteps").isEmpty() ? null
					: Integer.parseInt(request.getParameter("additionalProcessingSteps"));
			String stepsJustification = request.getParameter("stepsJustification");
			Integer microbiologicallyRelated = request.getParameter("microbiologicallyRelated").isEmpty() ? null
					: Integer.parseInt(request.getParameter("microbiologicallyRelated"));
			String microbiologicallyRelatedJustification = request
					.getParameter("microbiologicallyRelatedJustification");
			Integer productCrossContamination = request.getParameter("productCrossContamination").isEmpty() ? null
					: Integer.parseInt(request.getParameter("productCrossContamination"));
			String contaminationJustification = request.getParameter("contaminationJustification");
			Integer productImpact = request.getParameter("productImpact").isEmpty() ? null
					: Integer.parseInt(request.getParameter("productImpact"));
			String impactJustification = request.getParameter("impactJustification");
			Integer complexityOfInvestigation = request.getParameter("complexityOfInvestigation").isEmpty() ? null
					: Integer.parseInt(request.getParameter("complexityOfInvestigation"));
			String complexityJustification = request.getParameter("complexityJustification");
			Integer criticalWarrantedByQuality = request.getParameter("criticalWarrantedByQuality").isEmpty() ? null
					: Integer.parseInt(request.getParameter("criticalWarrantedByQuality"));
			String criticalJustification = request.getParameter("criticalJustification");
			Boolean isDeviationRepeated = request.getParameter("isDeviationRepeated") != null
					&& request.getParameter("isDeviationRepeated").equalsIgnoreCase("yes");
			String descriptionOfRisk = request.getParameter("descriptionOfRisk");
			Integer accountableDepartment = request.getParameter("accountableDepartment").isEmpty() ? null
					: Integer.parseInt(request.getParameter("accountableDepartment"));
			Date targetClosureDate = request.getParameter("targetClosureDate").isEmpty() ? null
					: new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("targetClosureDate"));

			// Create DTO and save assessment
			EvaluationDeviationByHeadDTO evaluationDto = new EvaluationDeviationByHeadDTO(deviationId,
					probabilityOfRecurrence, probabilityJustification, additionalProcessingSteps, stepsJustification,
					microbiologicallyRelated, microbiologicallyRelatedJustification, productCrossContamination,
					contaminationJustification, productImpact, impactJustification, complexityOfInvestigation,
					complexityJustification, criticalWarrantedByQuality, criticalJustification, isDeviationRepeated,
					descriptionOfRisk, accountableDepartment, targetClosureDate);
			evaluationDeviationByHeadDAO.saveAssessment(evaluationDto);

			// Calculate total risk score
			int totalRiskScore = calculateTotalRiskScore(evaluationDto);

			// Update deviation with risk category and closure date
			java.sql.Date assessmentDate = new java.sql.Date(System.currentTimeMillis()); // Assuming assessment date is
																							// current date
			evaluationDeviationByHeadDAO.updateDeviationRiskCategoryAndClosureDate(deviationId, totalRiskScore,
					assessmentDate);

			// Handle repeated deviation case
			if (evaluationDto.getIsDeviationRepeated()) {
//				evaluationDeviationByHeadDAO.updateDeviationForRepeatedCase(deviationId, descriptionOfRisk,
//						accountableDepartment.toString(), targetClosureDate);
			} else {
				// Transition to Approval by QA
				evaluationDeviationByHeadDAO.transitionToApprovalByQA(deviationId);
			}

			// Prepare success response
			jsonObject.addProperty("message", "Deviation assessment saved and transitioned successfully.");

		} catch (SQLException | ParseException e) {
			// Handle exceptions
			e.printStackTrace();
			jsonObject.addProperty("message", "Error during deviation assessment: " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		// Send response
		response.getWriter().print(new Gson().toJson(jsonObject));
	}

	private int calculateTotalRiskScore(EvaluationDeviationByHeadDTO evaluationDto) {
		int totalScore = 0;
		if (evaluationDto.getProbabilityOfRecurrence() != null) {
			totalScore += evaluationDto.getProbabilityOfRecurrence();
		}
		if (evaluationDto.getAdditionalProcessingSteps() != null) {
			totalScore += evaluationDto.getAdditionalProcessingSteps();
		}
		if (evaluationDto.getMicrobiologicallyRelated() != null) {
			totalScore += evaluationDto.getMicrobiologicallyRelated();
		}
		if (evaluationDto.getProductCrossContamination() != null) {
			totalScore += evaluationDto.getProductCrossContamination();
		}
		if (evaluationDto.getProductImpact() != null) {
			totalScore += evaluationDto.getProductImpact();
		}
		if (evaluationDto.getComplexityOfInvestigation() != null) {
			totalScore += evaluationDto.getComplexityOfInvestigation();
		}
		if (evaluationDto.getCriticalWarrantedByQuality() != null) {
			totalScore += evaluationDto.getCriticalWarrantedByQuality();
		}
		return totalScore;
	}
}