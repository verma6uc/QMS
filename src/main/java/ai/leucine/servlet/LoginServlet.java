package ai.leucine.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mchange.v2.cfg.DelayedLogItem.Level;

import dao.PageDAO;
import dao.PageRoleDAO;
import dao.UserDAO;
import model.Page;
import model.User;

/**
 * Servlet implementation for handling login requests.
 * This servlet processes user login credentials and authenticates them against the database.
 * Upon successful authentication, it redirects the user to the appropriate page.
 * If authentication fails, it redirects back to the login page with an error message.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
    private final UserDAO userDAO = new UserDAO();
    private final PageRoleDAO pageRoleDAO = new PageRoleDAO();
    private final PageDAO pageDAO = new PageDAO();

    /**
     * Handles the HTTP POST request for user login. It retrieves the username and
     * password from the request, authenticates the user using UserDAO, and manages
     * the session upon successful login. If the authentication fails, it sets an
     * error message and redirects back to the login page.
     *
     * @param request  The HttpServletRequest object that contains the request the
     *                 client has made of the servlet.
     * @param response The HttpServletResponse object that contains the response the
     *                 servlet sends to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");

        if (user == null && email != null && password != null) {
            try {
                Optional<User> optionalUser = userDAO.login(email, password);
                if (optionalUser.isPresent()) {
                    user = optionalUser.get();
                } else {
                    user = null;
                }
            } catch (SQLException e) {
                request.setAttribute("error", "Internal Server Error. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } else if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        try {
            if (user != null) {
                // Successful login
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(86400); // Session timeout in seconds (1 day)

                Optional<Integer> firstPageId = pageRoleDAO.getFirstPageIdForRole(user.getRoleId());
                if (firstPageId.isPresent()) {
                    Optional<Page> firstPage = pageDAO.getPageById(firstPageId.get());
                    if (firstPage.isPresent()) {
                        String firstPageSlug = firstPage.get().getSlug();
                        System.out.println("Slug : " + firstPageSlug);
                        response.sendRedirect(firstPageSlug);
                        return;
                    }
                }

//                // Fallback slug if no specific page is linked to the role
//                String fallbackSlug = "default-dashboard"; // Change this to an appropriate default page
//                response.sendRedirect(request.getContextPath() + "/" + fallbackSlug);
            } else {
                // Login failed
                request.setAttribute("error", "Invalid username or password. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (IOException | ServletException e) {
            
            request.setAttribute("error", "Invalid username or password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Handles GET requests by forwarding them to the login page.
     *
     * @param request  The HttpServletRequest object containing client request
     *                 information.
     * @param response The HttpServletResponse object for sending the response to
     *                 the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs while handling the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}