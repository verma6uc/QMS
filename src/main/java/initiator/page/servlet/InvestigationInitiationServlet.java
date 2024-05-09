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

import dao.InvestigationDAO;
import dto.InvestigationCapaFormDTO;

@WebServlet("/InvestigationInitiationServlet")
public class InvestigationInitiationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InvestigationDAO investigationCapaDAO;

	@Override
	public void init() throws ServletException {
		investigationCapaDAO = new InvestigationDAO();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		resp.setContentType("application/json");

		try {
			InvestigationCapaFormDTO formDTO = getFormData(req);
			investigationCapaDAO.submitInvestigationCapaForm(formDTO);
			String message = formDTO.isInvestigationRequired() ? "Investigation workflow initiated"
					: "CAPA workflow initiated";
			resp.getWriter().write(gson.toJson(new ResponseMessage(message)));
		} catch (SQLException | NumberFormatException e) {
			resp.setStatus(500);
			resp.getWriter().write(gson.toJson(new ResponseMessage("Error processing request: " + e.getMessage())));
		}
	}

	private InvestigationCapaFormDTO getFormData(HttpServletRequest req) {
		InvestigationCapaFormDTO formDTO = new InvestigationCapaFormDTO();
		formDTO.setInvestigationRequired(Boolean.parseBoolean(req.getParameter("investigationRequired")));
		formDTO.setDescriptionOfInvestigation(req.getParameter("description"));
		formDTO.setDeviationId(req.getParameter("deviationId"));

		String investigatorIdsParam = req.getParameter("investigatorIds");
		if (investigatorIdsParam != null && !investigatorIdsParam.isEmpty()) {
			List<Integer> investigatorIds = Arrays.stream(investigatorIdsParam.split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());
			formDTO.setInvestigatorIds(investigatorIds);
		}

		return formDTO;
	}

	private static class ResponseMessage {
		private final String message;

		public ResponseMessage(String message) {
			this.message = message;
		}
	}
}