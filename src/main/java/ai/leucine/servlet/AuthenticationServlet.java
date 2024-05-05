package ai.leucine.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ai.leucine.db.utils.DatabaseUtility;
import model.User;

@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/auth"})
public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = authenticateUser(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Store entire user object
            response.sendRedirect(request.getContextPath() + "/dashboard"); // Redirect to the dashboard
        } else {
            request.setAttribute("errorMessage", "Invalid username or password!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    /**
     * Authenticate the user against the database.
     * 
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return User object if authenticated successfully, null otherwise.
     */
    private User authenticateUser(String username, String password) {
        final String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseUtility.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            stmt.setString(2, password); // Assume password hashing is handled appropriately

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setRoleId(rs.getInt("role_id"));
                user.setDepartmentId(rs.getInt("department_id"));
                return user; // User authenticated successfully
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handling the exception appropriately
        }
        return null; // User not found or error in authentication
    }
}

 