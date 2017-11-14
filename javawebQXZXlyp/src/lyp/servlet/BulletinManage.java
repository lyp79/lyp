package lyp.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyp.entity.Bulletin;
import lyp.entity.PageModel;
import lyp.entity.UserInfo;
import lyp.service.BulletinService;
import lyp.serviceImpl.BulletinServiceImpl;

public class BulletinManage extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	BulletinService bs = new BulletinServiceImpl();
	PageModel<Bulletin> pm = new PageModel<Bulletin>();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		if ("toAdd".equals(op)) {
			response.sendRedirect(path + "/back/bulletinAdd.jsp");
		} else if ("toUpdate".equals(op)) {
			Bulletin bulletin = bs.selectById(Integer.parseInt(id));
			session.setAttribute("bulletin", bulletin);
			response.sendRedirect(path + "/back/bulletinUpdate.jsp");
		} else if ("bulletinDelete".equals(op)) {
			bulletinDel(request, response, id, session, path);
		} else if ("manage".equals(op)) {
			manage(request, response);
		} else if ("query".equals(op)) {
			query(request, response, session, path);

		} else if ("bulletinAdd".equals(op)) {
			bulletinAdd(request, response, session, path);
		} else if ("bulletinUpdate".equals(op)) {
			bulletinUpdate(request, response, session, path);
		} else if ("delcheck".equals(op)) {
			delcheck(request, response, session, path);
		}
	}

	private void delcheck(HttpServletRequest request, HttpServletResponse response, HttpSession session, String path) throws IOException, ServletException {
		String cleckid = request.getParameter("cleckid");
		if (!cleckid.equals("") && cleckid != null) {
			try {
				bs.delBulletins(cleckid);
				session.setAttribute("msg", "删除成功!");
				response.sendRedirect(path + "/back/bulletinManage?op=manage");
			} catch (Exception e) {
				request.setAttribute("msg", "删除失败!");
				request.getRequestDispatcher("/back/bulletinManage?op=manage").forward(request, response);
			}
		} else {
			session.setAttribute("msg", "操作失败!");
			response.sendRedirect(path + "/back/bulletinManage?op=manage");
		}
	}

	private void bulletinAdd(HttpServletRequest request, HttpServletResponse response, HttpSession session, String path) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		UserInfo user = (UserInfo) session.getAttribute("user");
		Bulletin bulletin = new Bulletin();
		bulletin.setTitle(title);
		bulletin.setContent(content);
		bulletin.getUser().setUserId(user.getUserId());
		bulletin.setCreateTime(new Date());
		try {
			bs.addBulletin(bulletin);
			session.setAttribute("msg", "公告添加成功!");
			response.sendRedirect(path + "/back/bulletinManage?op=manage");
		} catch (Exception e) {
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("msg", "操作失败," + e);
			e.printStackTrace();
			request.getRequestDispatcher("/back/bulletinAdd.jsp").forward(request, response);
		}
	}

	private void bulletinUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session, String path) throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Bulletin bulletin = new Bulletin();
		bulletin.setId(Integer.parseInt(id));
		bulletin.setTitle(title);
		bulletin.setContent(content);
		try {
			bs.updateBulletin(bulletin);
			session.setAttribute("msg", "公告修改成功!");
			response.sendRedirect(path + "/back/bulletinManage?op=manage");
		} catch (Exception e) {
			request.setAttribute("msg", "操作失败," + e);
			request.getRequestDispatcher("/back/bulletinUpdate.jsp").forward(request, response);
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response, HttpSession session, String path) throws IOException, ServletException {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} catch (Exception e) {}
		if(pm.getTotalPage()==0){
			pageNo = 1;
		}
		pm.setPageNo(pageNo);
		pm.setPageSize(3);
		String keywords = request.getParameter("keywords");
		if (keywords != null && !keywords.equals("")) {
			pm = bs.getBulletinPage(pm, keywords);
			request.setAttribute("pm", pm);
			request.setAttribute("keywords", keywords);
			request.setAttribute("op", "query");
			request.getRequestDispatcher("/back/bulletinManage.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/back/bulletinManage?op=manage").forward(request, response);
		}
	}

	private void manage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} catch (Exception e) {}
		if(pm.getTotalPage()==0){
			pageNo = 1;
		}
		pm.setPageNo(pageNo);
		pm.setPageSize(6);
		request.setAttribute("pm", bs.getBulletinPage(pm));
		request.setAttribute("op", "manage");
		request.getRequestDispatcher("/back/bulletinManage.jsp").forward(request, response);
	}

	private void bulletinDel(HttpServletRequest request, HttpServletResponse response, String id, HttpSession session, String path) throws ServletException, IOException {
		try {
			bs.delBulletin(Integer.parseInt(id));
			session.setAttribute("msg", "公告删除成功");
			response.sendRedirect(path + "/back/bulletinManage?op=manage");
		} catch (Exception e) {
			request.setAttribute("msg", "操作失败," + e.getMessage());
			request.getRequestDispatcher("/back/bulletinManage.jsp").forward(request, response);
		}
	}

}
