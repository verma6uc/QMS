package crossfunctional.page.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cross-functional-deviation-review")
public class CrossFunctionalDeviationReviewServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/crossfunctional/crossfunctionaldeviationreview.jsp").forward(request, response);
		
		
	}
}
