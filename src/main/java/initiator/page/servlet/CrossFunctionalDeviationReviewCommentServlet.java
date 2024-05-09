package initiator.page.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DeviationDAO;
import dao.UserDAO;
import dto.CrossFunctionalDeviationReviewDTO;
import model.User;

@WebServlet("/crossFunctionalDeviationReviewCommentServlet")
public class CrossFunctionalDeviationReviewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeviationDAO crossFunctionalDeviationReviewDao;

	public CrossFunctionalDeviationReviewCommentServlet() {
		super();
		crossFunctionalDeviationReviewDao = new DeviationDAO();
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

		if (user == null) {
			try {
				user = new UserDAO().getUserById(85).get();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int deviationId = Integer.parseInt(request.getParameter("deviationId"));
		int userId = user.getId(); // assuming userId is stored in session

		CrossFunctionalDeviationReviewDTO reviewDTO = new CrossFunctionalDeviationReviewDTO();

//		reviewDTO.setJustification(request.getParameter("justification"));
		reviewDTO.setDecision(request.getParameter("decision"));
		reviewDTO.setComments(request.getParameter("comments"));

		try {
			crossFunctionalDeviationReviewDao.submitCrossFunctionalComment(reviewDTO, deviationId, userId);
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