package ai.leucine.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PageDAO;
import dao.PageRoleDAO;
import dao.UserDAO;
import model.Page;
import model.User;

/**
 * Servlet implementation for handling login requests.
 * This servlet processes user login credentials and authenticates them against the database.
 * Upon successful authentication, it redirects the user to the appropriate page.
 * If authentication fails, it redirects back to the index page with an error message.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDAO = new UserDAO();
    private final PageRoleDAO pageRoleDAO = new PageRoleDAO();
    private final PageDAO pageDAO = new PageDAO();

    /**
     * Handles the HTTP POST request for user login. It retrieves the username and
     * password from the request, authenticates the user using UserDAO, and manages
     * the session upon successful login. If the authentication fails, it sets an
     * error message and redirects back to the index page.
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
        HttpSession session = request.getSession(false);
        
        User user = (User) session.getAttribute("user");
        
		/*
		 * if (session == null || session.getAttribute("user") == null) { // User is not
		 * logged in, redirect to login page response.sendRedirect("/index.jsp");
		 * return; }
		 */

        if (user == null && email != null && password != null) {
            try {
            	user = userDAO.login(email, password);
                
            } catch (Exception e) {
                request.setAttribute("error", "Internal Server Error. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                return;
            }

        } else if (user == null) {
            request.setAttribute("error", "Invalid username or password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            if (user != null) {
                // Successful login
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(86400); // Session timeout in seconds (1 day)

                // Retrieve the list of pages the user has access to
                List<Page> userPages = pageDAO.getPagesByUserId(user.getId());

                if (!userPages.isEmpty()) {
                    // Redirect to the first page slug the user has access to
                    Page firstPage = userPages.get(0);
                    String firstPageSlug = firstPage.getSlug();
                    response.sendRedirect(firstPageSlug );
                    return;
                } else {
                    // If no pages are found, set an error message
                    request.setAttribute("error", "No accessible pages found for your role.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            } else {
                // Login failed
                request.setAttribute("error", "Invalid email or password. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            request.setAttribute("error", "An unexpected error occurred. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Handles GET requests by forwarding them to the index page.
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
        doPost(request, response);
    }
}
