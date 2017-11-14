package lyp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lyp.entity.Bulletin;
import lyp.entity.PageModel;
import lyp.service.BulletinService;
import lyp.serviceImpl.BulletinServiceImpl;

public class ShowBulletin extends HttpServlet {
	BulletinService bs=new BulletinServiceImpl();

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if("showBulletin".equals(op)){
			int id=Integer.parseInt(request.getParameter("id"));
			Bulletin bull=bs.selectById(id);
			request.setAttribute("bull", bull);
			request.getRequestDispatcher("/front/showBulletin.jsp").forward(request, response);
		}else if("showBulletinList".equals(op)){
			int pageNo=1;
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			} catch (NumberFormatException e) {}
			PageModel<Bulletin> pm=new PageModel<Bulletin>();
			pm.setPageNo(pageNo);
			pm=bs.getBulletinPage(pm);
			request.setAttribute("pm", pm);
			request.getRequestDispatcher("/front/showBulletinList.jsp").forward(request, response);
		}
	}

}
