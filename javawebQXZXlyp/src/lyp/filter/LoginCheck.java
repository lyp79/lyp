package lyp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyp.entity.UserInfo;

public class LoginCheck implements Filter {

	public LoginCheck() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest res = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)response;
		HttpSession session = res.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			session.setAttribute("msg", "²Ù×÷Ê§°Ü,ÇëÏÈµÇÂ¼!");
			rep.sendRedirect(res.getContextPath()+"/login.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
