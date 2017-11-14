package lyp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyp.entity.UserInfo;
import lyp.service.UserService;
import lyp.serviceImpl.UserServiceImpl;
import lyp.utils.MyUtils;

public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getContextPath();
		HttpSession session=request.getSession();
		String op=request.getParameter("op");
		if("logout".equals(op)){
			session.invalidate();
			response.sendRedirect(path+"/login.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getContextPath();
		HttpSession session=request.getSession();
		String op=request.getParameter("op");
		if("login".equals(op)){
			String userName=request.getParameter("userName");
			String userPass=request.getParameter("userPass");
			if(userName==""||userName==null){
				session.setAttribute("namemsg", " 用户名不能为空!");
				response.sendRedirect(path+"/login.jsp");
				return;
			}
			if(userPass==""||userPass==null){
				session.setAttribute("passmsg", " 密码不能为空!");
				response.sendRedirect(path+"/login.jsp");
				return;
			}
			UserService userService=new UserServiceImpl();
			UserInfo user=(UserInfo) userService.userLogin(userName,userPass);
			if(user!=null&&userPass.equals(user.getUserPass())){
				session.setAttribute("user", user);
				session.setAttribute("userLoginTime", MyUtils.formatDate());
				session.setAttribute("ip", request.getRemoteAddr());
				response.sendRedirect(path+"/back/index.jsp");
			}else{
				request.setAttribute("msg", "用户名或密码错误!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			
		}
	}

}
