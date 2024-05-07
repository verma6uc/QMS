package initiator.page.servlet;

import com.google.gson.Gson;

import dao.DeviationDAO;
import dto.ApprovalByQADTO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ApprovalByQA")
public class ApprovalByQAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeviationDAO approvalByQADAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovalByQAServlet() {
		super();
		approvalByQADAO = new DeviationDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = null;

		if (request.getSession().getAttribute("user") != null) {
			user = (User) request.getSession().getAttribute("user");
		}

		String approverComments = request.getParameter("approverComments");
		String decision = request.getParameter("decision");
		String justification = request.getParameter("justification");
		String deviationId = request.getParameter("deviationId");

		ApprovalByQADTO approvalByQADTO = new ApprovalByQADTO();
		approvalByQADTO.setApproverComments(approverComments);
		if (decision != null && !decision.isEmpty()) {
			decision = decision.toUpperCase().replaceAll(" ", "_");
			approvalByQADTO.setDecision(decision);
		}
		approvalByQADTO.setJustification(justification);
		if (deviationId != null && !deviationId.isEmpty()) {
			approvalByQADTO.setDeviationId(Integer.parseInt(deviationId));
		}
		approvalByQADTO.setUserId(user.getId());

		Map<String, String> responseMap = new HashMap<>();
		try {
			approvalByQADAO.updateDeviationStatus(approvalByQADTO);
			if (approvalByQADTO.getApproverComments() != null && !approvalByQADTO.getApproverComments().isEmpty()) {
				approvalByQADAO.insertComments(approvalByQADTO);
			}
			if (approvalByQADTO.getJustification() != null && !approvalByQADTO.getJustification().isEmpty()) {
				approvalByQADAO.insertJustification(approvalByQADTO);
			}
			responseMap.put("message", "Data Updated Successfully.");
		} catch (SQLException e) {
			responseMap.put("message", "Error: " + e.getMessage());
		}
		String jsonResponse = new Gson().toJson(responseMap);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse);
	}
}
