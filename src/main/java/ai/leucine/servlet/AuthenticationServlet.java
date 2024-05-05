package ai.leucine.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import ai.leucine.db.utils.DatabaseUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp"); // Redirect to dashboard
        } else {
            request.setAttribute("errorMessage", "Invalid username or password!");
            request.getRequestDispatcher("/index.jsp").forward(request, response); // Send back to the login page
        }
    }

    /**
     * Authenticate the user against the database.
     * 
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return true if authenticated successfully, false otherwise.
     */
    private boolean authenticateUser(String username, String password) {
        final String sql = "SELECT * FROM users WHERE username = ? AND password = ?"; // Ensure passwords are hashed

        try (Connection conn = DatabaseUtility.connect(); // Use the utility class to get connection
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            stmt.setString(2, password); // This should be a hashed password

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // If there's at least one row, the user is authenticated
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Log this exception
        }
        return false;
    }
}