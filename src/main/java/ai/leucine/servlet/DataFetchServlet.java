package ai.leucine.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ai.leucine.db.utils.DatabaseUtility;

@WebServlet("/data/fetch")
public class DataFetchServlet extends HttpServlet {
	private static final long serialVersionUID = 7227470019021346238L;
	private static final Logger logger = Logger.getLogger(DataFetchServlet.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String elementType = request.getParameter("elementType");
		if (elementType != null) {
			try {
				// Request parameters
				String sql = request.getParameter("sql");
				String search = request.getParameter("search");

				handleInputSql(sql, search, response);
			} catch (IllegalArgumentException | UnsupportedOperationException e) {
				sendErrorResponse(response, "Invalid request: " + e.getMessage());
			} catch (Exception e) {
				sendErrorResponse(response, "Server error: " + e.getLocalizedMessage());
			}
		} else {

			try {
				// Request parameters
				String sql = request.getParameter("sql");
				String search = request.getParameter("search");

				handleEntityList(sql, search, response);
			} catch (IllegalArgumentException | UnsupportedOperationException e) {
				sendErrorResponse(response, "Invalid request: " + e.getMessage());
			} catch (Exception e) {
				sendErrorResponse(response, "Server error: " + e.getLocalizedMessage());
			}
		}
	}

	private void handleInputSql(String sql, String search, HttpServletResponse response)
			throws SQLException, IOException {

		List<Object> parameters = new ArrayList<Object>();
		if (sql.contains("?")) {
			if (search != null) {
				if (sql.toLowerCase().contains("like")) {
					parameters.add("%" + search + "%");
				}
			} else {
				if (sql.toLowerCase().contains("like")) {
					parameters.add("%%");
					sql += " limit 10;";
				}
			}
		}

		ArrayList<HashMap<String, String>> result = DatabaseUtility.executeQueryForPreview(sql, parameters);

		Gson gson = new Gson();
		JsonElement element = null;
		for (HashMap<String, String> map : result) {
			element = JsonParser.parseString(gson.toJson(map));
		}

		response.getWriter().write(new Gson().toJson(element));

	}

	private Integer parseIntegerParam(HttpServletRequest request, String param) {
		try {
			return Integer.parseInt(request.getParameter(param));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private void handleEntityList(String sql, String search, HttpServletResponse response)
			throws IOException, SQLException {

		List<Object> parameters = new ArrayList<Object>();
		if (sql.contains("?")) {
			if (search != null) {
				if (sql.toLowerCase().contains("like")) {
					parameters.add("%" + search + "%");
				}
			} else {
				if (sql.toLowerCase().contains("like")) {
					parameters.add("%%");
					sql += " limit 10;";
				}
			}
		}

		ArrayList<HashMap<String, String>> result = DatabaseUtility.executeQueryForPreview(sql, parameters);

		Gson gson = new Gson();
		JsonArray jsonArray = new JsonArray();

		for (HashMap<String, String> map : result) {
			JsonElement element = JsonParser.parseString(gson.toJson(map));
			jsonArray.add(element);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("total_count", result.size());
		jsonObject.addProperty("incomplete_results", true);
		jsonObject.add("items", jsonArray);
		response.getWriter().write(new Gson().toJson(jsonObject));
	}

	private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "failure");
		jsonObject.addProperty("message", message);
		response.getWriter().write(new Gson().toJson(jsonObject));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public enum FetchExternalEntityMethod {
		ENTITY_LIST, CREATE, UPDATE, DELETE
	}
}
