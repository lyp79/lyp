package lyp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyp.entity.GoodsType;
import lyp.entity.PageModel;
import lyp.service.TypeService;
import lyp.serviceImpl.TypeServiceImpl;

public class TypeManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TypeService ts=new TypeServiceImpl();
	PageModel<GoodsType> pm = new PageModel<GoodsType>();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		if ("toAdd".equals(op)) {
			response.sendRedirect(path + "/back/typeAdd.jsp");
		} else if ("typeDel".equals(op)) {
			typeDel(request, response, session,path);
		} else if ("toUpdate".equals(op)) {
			toUpdate(request, response, path, session);
		} else if ("manage".equals(op)) {
			manage(request, response);
		}else if ("query".equals(op)) {
			query(request, response, session, path);
		}else if ("typeAdd".equals(op)) {
			typeAdd(request, response, path, session);
		} else if ("typeUpdate".equals(op)) {
			typeUpdate(request, response, path, session);
		}else if ("query".equals(op)) {
			query(request, response, session, path);

		}

	}

	private void toUpdate(HttpServletRequest request, HttpServletResponse response, String path, HttpSession session) throws IOException, ServletException {
		String typeId = request.getParameter("typeId");
		GoodsType type = ts.selectById(Integer.parseInt(typeId));
		request.setAttribute("type", type);
		request.getRequestDispatcher("/back/typeUpdate.jsp").forward(request, response);
	}

	private void typeDel(HttpServletRequest request, HttpServletResponse response, HttpSession session, String path) throws ServletException, IOException {
		String typeId = request.getParameter("typeId");
		try {
			ts.delType(Integer.parseInt(typeId));
			session.setAttribute("msg", "商品类型删除成功！");
			response.sendRedirect(path + "/back/typeManage.jsp?op=manage");
		} catch (Exception e) {
			request.setAttribute("msg", "操作失败," + e.getMessage());
			request.getRequestDispatcher("/back/typeManage.jsp").forward(request, response);
		}
	}

	private void typeUpdate(HttpServletRequest request, HttpServletResponse response, String path, HttpSession session) throws ServletException, IOException {
		int typeId=Integer.parseInt(request.getParameter("typeId"));
		String typeName = request.getParameter("typeName");
		GoodsType type = new GoodsType();
		type.setTypeId(typeId);
		type.setTypeName(typeName);
		try {
			ts.updateType(type);
			session.setAttribute("msg", "商品类型修改成功！");
			response.sendRedirect(path + "/back/typeManage?op=manage");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "操作失败," + e.getMessage());
			request.getRequestDispatcher("/back/typeUpdate.jsp").forward(request, response);
		}
	}

	private void typeAdd(HttpServletRequest request, HttpServletResponse response, String path, HttpSession session)
			throws ServletException, IOException {
		GoodsType type = new GoodsType();
		String typeName = request.getParameter("typeName");
		type.setTypeName(typeName);
		try {
			ts.addType(type);
			session.setAttribute("msg", "商品类型添加成功！");
			response.sendRedirect(path + "/back/typeManage?op=manage");
		} catch (Exception e) {
			session.setAttribute("msg", "操作失败," + e.getMessage());
			request.getRequestDispatcher("/back/typeAdd.jsp").forward(request, response);
		}
	}
	

	private void manage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} catch (Exception e) {
			pageNo = 1;
		}
		if(pm.getTotalPage()==0){
			pageNo = 1;
		}
		pm.setPageNo(pageNo);
		pm = ts.getTypePage(pm);
		request.setAttribute("pm", pm);
		request.setAttribute("op", "manage");
		request.getRequestDispatcher("/back/typeManage.jsp").forward(request, response);
	}
	
	private void query(HttpServletRequest request, HttpServletResponse response, HttpSession session, String path) throws IOException, ServletException {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} catch (Exception e) {
			pageNo = 1;
		}
		if(pm.getTotalPage()==0){
			pageNo = 1;
		}
		pm.setPageNo(pageNo);
		String keywords = request.getParameter("keywords");
		if (keywords == null || keywords.equals("")) {
			request.setAttribute("pm", ts.getTypePage(pm));
			request.setAttribute("op", "manage");
			request.getRequestDispatcher("/back/typeManage.jsp").forward(request, response);
		} else {
			pm = ts.getTypePage(pm,keywords);
			request.setAttribute("keywords", keywords);
			request.setAttribute("pm", pm);
			request.setAttribute("op", "query");
		}
		request.getRequestDispatcher("/back/typeManage.jsp").forward(request, response);
	}
}
