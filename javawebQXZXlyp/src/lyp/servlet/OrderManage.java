package lyp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lyp.entity.CustomerInfo;
import lyp.entity.OrderInfo;
import lyp.entity.PageModel;
import lyp.service.OrderInfoService;
import lyp.serviceImpl.CustomerServiceImpl;
import lyp.serviceImpl.OrderInfoServiceImpl;

public class OrderManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderInfoService os = new OrderInfoServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("manage".equals(op)){
			int pageNo = 1;
	    	try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception e) {}
	    	PageModel<OrderInfo> pm = new PageModel<OrderInfo>();
	    	pm.setPageNo(pageNo);
	    	pm=os.getOrderByPage(pm);
	    	request.setAttribute("pm", pm);
	    	request.setAttribute("op", "manage");
			request.getRequestDispatcher("/back/orderManage.jsp").forward(request, response);
		}else if("toShow".equals(op)){
			String id=request.getParameter("id");
			CustomerInfo customer=new CustomerServiceImpl().getCustomerById(Integer.parseInt(id));
			request.setAttribute("customer", customer);
			request.getRequestDispatcher("/back/customerShow.jsp").forward(request, response);
		}else if("changeStatus".equals(op)){
			int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
			String orderId = request.getParameter("id");
			try {
				os.changeStatus(orderId,orderStatus);
				if(orderStatus==0){
				request.setAttribute("msg", "订单确认成功！");
				}else{
					request.setAttribute("msg", "订单取消成功！");	
				}
			} catch (Exception e) {
				request.setAttribute("msg", "操作失败！");
			}
			request.getRequestDispatcher("/back/orderManage?op=manage").forward(request, response);
		}else if("delOrder".equals(op)){
			String orderId = request.getParameter("id");
			try {
				os.delOrder(orderId);
				request.setAttribute("msg", "订单删除成功！");
			} catch (Exception e) {
				request.setAttribute("msg", "订单删除失败！");
			}
			request.getRequestDispatcher("/back/orderManage?op=manage").forward(request, response);
		}else if("toShowOrder".equals(op)){
			int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
			int pageNo = 1;
	    	try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception e) {}
	    	PageModel<OrderInfo> pm = new PageModel<OrderInfo>();
	    	pm.setPageNo(pageNo);
	    	pm=os.getOrderByPage(pm,orderStatus);
	    	request.setAttribute("pm", pm);
	    	request.setAttribute("orderStatus", orderStatus);
	    	request.setAttribute("op", "toShowOrder");
			request.getRequestDispatcher("/back/orderManage.jsp").forward(request, response);
			
		}else if("query".equals(op)){
			int pageNo = 1;
	    	try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception e) {}
	    	String keywords = request.getParameter("keywords");
	    	if(keywords!=null&&!keywords.equals("")){
	    	PageModel<OrderInfo> pm = new PageModel<OrderInfo>();
	    	pm.setPageNo(pageNo);
	    	pm=os.getOrderByPageKey(pm,keywords);
	    	if(pm.getData()==null){
	    		request.setAttribute("msg", "你搜索的数据不存在！");
	    		request.getRequestDispatcher("/back/orderManage?op=manage").forward(request, response);
	    	}
	    	request.setAttribute("pm", pm);
	    	request.setAttribute("op", "query");
	    	request.setAttribute("keywords", keywords);
			request.getRequestDispatcher("/back/orderManage.jsp").forward(request, response);
	    	}else{
	    	request.getRequestDispatcher("/back/orderManage?op=manage").forward(request, response);
	    	}
		}else if("delcheck".equals(op)){
			String cleckid = request.getParameter("cleckid");
			System.out.println(cleckid);
			if (!cleckid.equals("") && cleckid != null) {
				try {
					os.delOrders(cleckid);
					request.getSession().setAttribute("msg", "删除成功!");
					response.sendRedirect(request.getContextPath() + "/back/orderManage?op=manage");
				} catch (Exception e) {
					request.setAttribute("msg", "删除失败!");
					request.getRequestDispatcher("/back/orderManage?op=manage").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "没有选中任何项!");
				request.getRequestDispatcher("/back/orderManage?op=manage").forward(request, response);
			}
		}else if("showOrderDetail".equals(op)){
			String id=request.getParameter("id");
			OrderInfo orderInfo = os.getOrderById(id);
			request.setAttribute("orderInfo", orderInfo);
			request.getRequestDispatcher("/back/showOrderDetail.jsp").forward(request, response);
			
		}
		
	}

}
