package initiator.page.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.CapaDAO;
import dto.InitiatingCapaDTO;
import model.Enums;

/**
 * Servlet implementation class InitiateCapaServlet
 */
@WebServlet("/initiateCapa")
public class InitiateCapaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitiateCapaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonObject jsonResponse = new JsonObject();
		try {
			Integer deviationId = Integer.parseInt(request.getParameter("deviationId"));
			String description = request.getParameter("description");
			Integer responsibleUserId = Integer.parseInt(request.getParameter("responsibleUserId"));
			String actionType = !request.getParameter("actionType").isEmpty()
					? Enums.CapaType.valueOf(request.getParameter("actionType").toUpperCase()).name()
					: null;
			String completionDate = request.getParameter("completionDate");
			Boolean changeControlRequired = !request.getParameter("changeControlRequired").isEmpty()
					? Boolean.parseBoolean(request.getParameter("changeControlRequired"))
					: null;
			Boolean interimControlRequired = !request.getParameter("interimControlRequired").isEmpty()
					? Boolean.parseBoolean(request.getParameter("interimControlRequired"))
					: null;
			String interimControlDetails = request.getParameter("interimControlDetails");
			String effectivenessPlan = request.getParameter("effectivenessPlan");
			if (completionDate.isEmpty()) {
				jsonResponse.addProperty("message", "Completion date cannot be empty.");
				response.getWriter().append(jsonResponse.toString());
				return;
			}
			InitiatingCapaDTO capaDTO = new InitiatingCapaDTO(deviationId, description, responsibleUserId, actionType,
					completionDate, changeControlRequired, interimControlRequired, interimControlDetails,
					effectivenessPlan);
			new CapaDAO().initiateCapa(capaDTO);
			jsonResponse.addProperty("message", "Successfully added new CAPA.");
		} catch (NumberFormatException e) {
			jsonResponse.addProperty("message", "Invalid input. Please enter correct values.");
		} catch (IllegalArgumentException e) {
			jsonResponse.addProperty("message", "Invalid CAPA type.");
		} catch (SQLException e) {
			e.printStackTrace();
			jsonResponse.addProperty("message", "An error occurred while saving the CAPA.");
		}
		response.getWriter().append(jsonResponse.toString());
	}

}
