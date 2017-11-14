package lyp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodeFilter implements Filter {
	FilterConfig fConfig;

	public EncodeFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String encode = "UTF-8";
		if (fConfig != null && fConfig.getInitParameter("encode") != null) {
			encode = fConfig.getInitParameter("encode");
		}
		request.setCharacterEncoding(encode);
		response.setContentType("text/html");
		response.setCharacterEncoding(encode);
		HttpServletRequest req = (HttpServletRequest)request;
		req.getSession().setAttribute("path",req.getContextPath());
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}

}
