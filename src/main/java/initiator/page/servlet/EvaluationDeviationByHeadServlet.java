package initiator.page.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.QaDeviationRiskAssessmentDAO;
import dto.EvaluationDeviationByHeadDTO;
import model.Enums;

@WebServlet("/EvaluationDeviationByHeadServlet")
public class EvaluationDeviationByHeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		EvaluationDeviationByHeadDTO evaluationDto = gson.fromJson(request.getReader(),
				EvaluationDeviationByHeadDTO.class);
		QaDeviationRiskAssessmentDAO dao = new QaDeviationRiskAssessmentDAO();
		try {
			// Save Risk Assessment
			dao.saveAssessment(evaluationDto);
			// Calculate total risk
			int totalRiskScore = 0;
			if (evaluationDto.getProbabilityOfRecurrence() != null) {
				totalRiskScore += evaluationDto.getProbabilityOfRecurrence();
			}
			if (evaluationDto.getAdditionalProcessingSteps() != null) {
				totalRiskScore += evaluationDto.getAdditionalProcessingSteps();
			}
			if (evaluationDto.getMicrobiologicallyRelated() != null) {
				totalRiskScore += evaluationDto.getMicrobiologicallyRelated();
			}
			if (evaluationDto.getProductCrossContamination() != null) {
				totalRiskScore += evaluationDto.getProductCrossContamination();
			}
			if (evaluationDto.getProductImpact() != null) {
				totalRiskScore += evaluationDto.getProductImpact();
			}
			if (evaluationDto.getComplexityOfInvestigation() != null) {
				totalRiskScore += evaluationDto.getComplexityOfInvestigation();
			}
			if (evaluationDto.getCriticalWarrantedByQuality() != null) {
				totalRiskScore += evaluationDto.getCriticalWarrantedByQuality();
			}
			java.sql.Date assessmentDate = new java.sql.Date(System.currentTimeMillis());
			dao.updateDeviationRiskCategoryAndClosureDate(evaluationDto.getDeviationId(), totalRiskScore,
					assessmentDate);
			// Handle Repeated Deviation
			if (evaluationDto.getIsDeviationRepeated()) {
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//				Date formattedDate = null;
//				if (evaluationDto.getTargetClosureDate() != null) {
//					String dateString = dateFormat.format(evaluationDto.getTargetClosureDate());
//					try {
//						formattedDate = new Date(dateFormat.parse(dateString).getTime());
//					} catch (ParseException e) {
//						// Handle parsing errors
//						e.printStackTrace();
//					}
//				}
//				dao.updateDeviationForRepeatedCase(evaluationDto.getDeviationId(), evaluationDto.getDescriptionOfRisk(),
//						evaluationDto.getAccountableDepartment().toString(), formattedDate);
			} else {
				dao.transitionToApprovalByQA(evaluationDto.getDeviationId());
			}

			response.getWriter().write("{\"message\":\"Success\"}");
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().write("{\"message\":\"Error Occured" + e.getMessage() + "\"}");
		}
	}

}