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

public class UserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService us=new UserServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String path=request.getContextPath();
		HttpSession session = request.getSession();
		if ("changePass".equals(op)) {
			String userName = ((UserInfo)session.getAttribute("user")).getUserName();
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("/back/changePass.jsp").forward(request, response);
		} else if ("changeName".equals(op)) {
			String userName = ((UserInfo)session.getAttribute("user")).getUserName();
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("/back/changeName.jsp").forward(request, response);

		} else if ("logoutUser".equals(op)) {
			session.invalidate();
			response.sendRedirect(path + "/login.jsp");
		} else {
			session.setAttribute("msg", "你还没登录,操作失败!");
			response.sendRedirect(path + "/login.jsp");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		HttpSession session = request.getSession();
		String path=request.getContextPath();
		if ("changePass".equals(op)) {
			UserInfo user=(UserInfo)session.getAttribute("user");
			String userName = user.getUserName();
			String oldUserPass = request.getParameter("oldUserPass");
			String newUserPass = request.getParameter("newUserPass");
			String reNewUserPass = request.getParameter("reNewUserPass");
			if(newUserPass.equals(reNewUserPass)&&user.getUserPass().equals(oldUserPass)){
				try {
					us.changePass(userName, oldUserPass, newUserPass);
					session.setAttribute("user", us.userLogin(userName, newUserPass));
					session.setAttribute("msg", "密码修改成功!");
					response.sendRedirect(path + "/back/main.jsp");
				} catch (Exception e) {
					request.setAttribute("msg", "密码修改失败!");
					request.getRequestDispatcher("/back/changePass.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("msg", "密码修改失败!");
				request.getRequestDispatcher("/back/changePass.jsp").forward(request, response);
			}
			
		} else if ("changeName".equals(op)) {
			UserInfo user=(UserInfo)session.getAttribute("user");
			String userName = user.getUserName();
			String userPass = request.getParameter("userPass");
			String newUserName = request.getParameter("newUserName");
			String reNewUserName = request.getParameter("reNewUserName");
			if (newUserName.equals(reNewUserName)&&user.getUserPass().equals(userPass)) {
				try {
					us.changeName(userName, userPass, newUserName);
					session.setAttribute("msg", "用户名修改成功!");
					session.setAttribute("user", us.userLogin(reNewUserName, userPass));
					response.sendRedirect(path + "/back/main.jsp");
				} catch (Exception e) {
					request.setAttribute("msg", "用户名修改失败!");
					request.getRequestDispatcher("/back/changeName.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("msg", "用户名修改失败!");
				request.getRequestDispatcher("/back/changeName.jsp").forward(request, response);
			}
		} else if ("logoutUser".equals(op)) {
			session.invalidate();
			response.sendRedirect(path + "/login.jsp");
		} 
	}

}
