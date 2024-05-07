package initiator.page.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.InvestigationDAO;
import dto.PerformRCADTO;
import model.Enums;

/**
 * Servlet implementation class PerformRCAServlet
 */
@WebServlet("/performRCA")
public class PerformRCAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PerformRCAServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PerformRCADTO rcaDTO = new PerformRCADTO();

		if (request.getParameter("investigationId") == null || request.getParameter("investigationId").isEmpty()) {
			response.getWriter().append("{\"message\":\"Invalid investigation ID\"}");
		}
		try {
			rcaDTO.setInvestigationId(Integer.parseInt(request.getParameter("investigationId")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.getWriter().append("{\"message\":\"Invalid investigation ID\"}");
			return;
		}

		rcaDTO.setInvestigationTool(request.getParameter("investigationTool"));
		rcaDTO.setRootCauseConclusion(request.getParameter("rootCauseConclusion"));
		rcaDTO.setRiskImpactAssessment(request.getParameter("riskImpactAssessment"));
		rcaDTO.setWhyQuestion1(request.getParameter("whyQuestion1"));
		rcaDTO.setWhyAnswer1(request.getParameter("whyAnswer1"));
		rcaDTO.setWhyQuestion2(request.getParameter("whyQuestion2"));
		rcaDTO.setWhyAnswer2(request.getParameter("whyAnswer2"));
		rcaDTO.setWhyQuestion3(request.getParameter("whyQuestion3"));
		rcaDTO.setWhyAnswer3(request.getParameter("whyAnswer3"));
		rcaDTO.setWhyQuestion4(request.getParameter("whyQuestion4"));
		rcaDTO.setWhyAnswer4(request.getParameter("whyAnswer4"));
		rcaDTO.setWhyQuestion5(request.getParameter("whyQuestion5"));
		rcaDTO.setWhyAnswer5(request.getParameter("whyAnswer5"));
		rcaDTO.setAbilityAssessment(request.getParameter("abilityAssessment"));
		rcaDTO.setAbilityResult(formatBoolean(request.getParameter("abilityResult")));
		rcaDTO.setDirectionAssessment(request.getParameter("directionAssessment"));
		rcaDTO.setDirectionResult(formatBoolean(request.getParameter("directionResult")));
		rcaDTO.setKnowledgeAssessment(request.getParameter("knowledgeAssessment"));
		rcaDTO.setKnowledgeResult(formatBoolean(request.getParameter("knowledgeResult")));
		rcaDTO.setOpportunityAssessment(request.getParameter("opportunityAssessment"));
		rcaDTO.setOpportunityResult(formatBoolean(request.getParameter("opportunityResult")));
		rcaDTO.setMotivationAssessment(request.getParameter("motivationAssessment"));
		rcaDTO.setMotivationResult(formatBoolean(request.getParameter("motivationResult")));
		InvestigationDAO performRCADAO = new InvestigationDAO();
		try {
			performRCADAO.performRCA(rcaDTO);
		} catch (SQLException e) {
			response.getWriter().append("{\"message\":\"Database Error. Check logs.\"}");
			return;
		}
		response.getWriter().append("{\"message\":\"RCA performed successfully\"}");
	}

	private Boolean formatBoolean(String parameter) {
		if (parameter == null || parameter.isEmpty()) {
			return null;
		}
		try {
			return Boolean.valueOf(parameter);
		} catch (Exception e) {
			return null;
		}
	}
}