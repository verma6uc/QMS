package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet filter for handling Cross-Origin Resource Sharing (CORS) headers.
 * This ensures that web applications running on one domain can request and
 * receive resources (like APIs) from a server running on a different domain.
 *
 * @author Anurag Gupta
 * @email anurag.gupta@leucinetech.com
 */
@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization code, if needed
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) servletResponse;

		// Authorize (allow) all domains to consume the content
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST, DELETE");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, Authorization");

		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		// Cleanup code, if needed
	}
}