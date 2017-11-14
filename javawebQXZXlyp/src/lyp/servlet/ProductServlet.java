package lyp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lyp.entity.GoodsInfo;
import lyp.entity.OrderGoodsInfo;
import lyp.entity.PageModel;
import lyp.service.GoodsService;
import lyp.serviceImpl.GoodsServiceImpl;

public class ProductServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	GoodsService gs=new GoodsServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("detail".equals(op)){
			int id= Integer.parseInt(request.getParameter("id"));
			GoodsInfo goods = gs.selectById(id);
			request.setAttribute("goods", goods);
		}else if("showTypeGoods".equals(op)){
			int id = Integer.parseInt(request.getParameter("id"));
			int pageNo=1;
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception e) { }
			PageModel<GoodsInfo> pm = new PageModel<GoodsInfo>();
			pm.setPageNo(pageNo);
			pm.setPageSize(9);
			pm=gs.getGoodsPageById(pm,id);
			request.setAttribute("pm", pm);
			List<OrderGoodsInfo> list = gs.getGoodsInfoTop10(id);
			request.setAttribute("list", list);
			request.setAttribute("id", id);
			request.setAttribute("op", "showTypeGoods");
		}else if("search".equals(op)){
			String keywords = request.getParameter("keywords");
			if(keywords!= null&&!keywords.equals("")){
			int pageNo=1;
			PageModel<GoodsInfo> pm = new PageModel<GoodsInfo>();
			if(pm.getTotalPage()==0){
				pageNo=1;
			}
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception e) {}
			pm.setPageNo(pageNo);
			System.out.println(pageNo);
			pm.setPageSize(9);
			pm = new GoodsServiceImpl().getGoodsPage(pm, keywords);
			request.getSession().setAttribute("pm", pm);
			request.getSession().setAttribute("keywords", keywords);
			request.getSession().setAttribute("op", "search");
			response.sendRedirect(request.getContextPath() + "/front/showGoodsList.jsp");
			//request.getRequestDispatcher("/front/showGoodsList.jsp").forward(request, response);
			}else{
				request.getSession().removeAttribute("pm");
				request.getSession().removeAttribute("keywords");
				request.getSession().removeAttribute("op");
				response.sendRedirect(request.getContextPath() + "/front/index.jsp");
			}
		}
	}
}
