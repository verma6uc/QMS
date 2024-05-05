package ai.leucine.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import ai.leucine.db.utils.DatabaseUtility;
import model.User;

@WebServlet(name = "PageServlet", urlPatterns = {"/page"})
public class PageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // User is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        Integer pageId = Integer.parseInt(request.getParameter("page_id"));
        
        ArrayList<String> sections = getPageSections(user.getRoleId(), pageId);

        if (!sections.isEmpty()) {
            request.setAttribute("sections", sections);
            request.getRequestDispatcher("/pageView.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "You do not have access to any sections on this page or the page does not exist.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * Retrieves a list of sections for a specific page, accessible to the specified role.
     * 
     * @param roleId The role ID of the user.
     * @param pageId The page ID for which sections are needed.
     * @return A list of strings representing section details or names.
     */
    private ArrayList<String> getPageSections(int roleId, int pageId) {
        String sql = "SELECT section_name FROM page_sections ps JOIN page_roles pr ON ps.page_id = pr.page_id WHERE pr.page_id = ? AND pr.role_id = ?";
        ArrayList<String> sections = new ArrayList<>();

        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, pageId);
            stmt.setInt(2, roleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sections.add(rs.getString("section_name")); // Assuming there's a 'section_name' column in your query result
            }
        } catch (Exception e) {
            e.printStackTrace(); // Better to use a logging framework
        }

        return sections;
    }
}