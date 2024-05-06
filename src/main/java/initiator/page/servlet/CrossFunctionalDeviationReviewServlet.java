package initiator.page.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DeviationDAO;
import dto.CrossFunctionalDeviationReviewDTO;
import model.User;

@WebServlet("/crossFunctionalDeviationReview")
public class CrossFunctionalDeviationReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeviationDAO crossFunctionalDeviationReviewDao;

	public CrossFunctionalDeviationReviewServlet() {
		super();
		crossFunctionalDeviationReviewDao = new DeviationDAO	();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		User user = null;

		if (request.getSession().getAttribute("user") != null) {
			user = (User) request.getSession().getAttribute("user");
		}
		
		int deviationId = Integer.parseInt(request.getParameter("deviationId"));
		int userId = user.getId(); // assuming userId is stored in session

		CrossFunctionalDeviationReviewDTO reviewDTO = new CrossFunctionalDeviationReviewDTO();
		reviewDTO.setCrossFunctionalRequired(request.getParameter("crossFunctionalRequired"));
		if (!reviewDTO.getCrossFunctionalRequired().isEmpty()) {
			String[] departmentIds = request.getParameterValues("department[]");
			if (departmentIds != null) {
				reviewDTO.setDepartment(Arrays.stream(departmentIds).map(Integer::parseInt).collect(Collectors.toList()));
			}
			String[] userGroupIds = request.getParameterValues("userGroup[]");
			if (userGroupIds != null) {
				reviewDTO.setUserGroup(Arrays.stream(userGroupIds).map(Integer::parseInt).collect(Collectors.toList()));
			}
		}

		reviewDTO.setDecision(request.getParameter("decision"));
		reviewDTO.setJustification(request.getParameter("justification"));
		reviewDTO.setComments(request.getParameter("comments"));
		
		try {
			crossFunctionalDeviationReviewDao.submitDecision(reviewDTO, deviationId, userId);
			String jsonResponse = gson.toJson(new StatusMessage("Decision submitted successfully!"));
			response.getWriter().write(jsonResponse);
		} catch (SQLException e) {
			e.printStackTrace();
			String jsonResponse = gson.toJson(new StatusMessage("Error occurred while submitting decision!"));
			response.getWriter().write(jsonResponse);
		}
	}

	// Inner class for status messages
	class StatusMessage {
		private String message;

		public StatusMessage(String message) {
			this.message = message;
		}
	}
}
