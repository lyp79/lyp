package lyp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyp.entity.CustomerInfo;
import lyp.entity.PageModel;
import lyp.service.CustomerService;
import lyp.serviceImpl.CustomerServiceImpl;

public class CustomerManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PageModel<CustomerInfo> pm=new PageModel<CustomerInfo>();
	CustomerService cs=new CustomerServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		if("manage".equals(op)){
			int pageNo = 1;
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception e) {}
			if(pm.getTotalPage()==0){
				pageNo = 1;
			}
			pm.setPageNo(pageNo);
			pm.setPageSize(6);
			session.setAttribute("pm", cs.getCustomerByPage(pm));
			session.setAttribute("op", "manage");
			response.sendRedirect(path+"/back/customerManage.jsp");
		}else if("query".equals(op)){
			int pageNo = 1;
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception e) {}
			if(pm.getTotalPage()==0){
				pageNo = 1;
			}
			pm.setPageNo(pageNo);
			pm.setPageSize(6);
			String keywords=request.getParameter("keywords");
			if(keywords!=null&&!keywords.equals("")){
			session.setAttribute("pm", cs.getCustomerByPage(pm,keywords));
			session.setAttribute("op", "query");
			session.setAttribute("keywords", keywords);
			response.sendRedirect(path+"/back/customerManage.jsp");
			}else{
				request.getRequestDispatcher("/back/customerManage?op=manage").forward(request, response);
			}
		}else if("toUpdate".equals(op)){
			String id=request.getParameter("id");
			CustomerInfo customer=cs.getCustomerById(Integer.parseInt(id));
			request.setAttribute("customer", customer);
			request.getRequestDispatcher("/back/customerUpdate.jsp").forward(request, response);
		}else if("customerDel".equals(op)){
			try {
				cs.delCustomer(request.getParameter("id"));
				request.setAttribute("msg", "删除客户成功!");
				request.getRequestDispatcher("/back/customerManage?op=manage").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "操作失败.");
				request.getRequestDispatcher("/back/customerManage.jsp").forward(request, response);
			}
		}else if("customerUpdate".equals(op)){
			CustomerInfo customer=new CustomerInfo();
			try {
				int id =Integer.parseInt(request.getParameter("id"));
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String telPhone = request.getParameter("telPhone");
				String movePhone = request.getParameter("movePhone");
				String address = request.getParameter("address");
				customer.setId(id);
				customer.setEmail(email);
				customer.getCustDetail().setName(name);
				customer.getCustDetail().setTelPhone(telPhone);
				customer.getCustDetail().setMovePhone(movePhone);
				customer.getCustDetail().setAddress(address);
				cs.updateCust(customer);
				request.setAttribute("msg", "客户资料成功!");
				request.getRequestDispatcher("/back/customerManage?op=manage").forward(request, response);
			} catch (NumberFormatException e) {
				request.setAttribute("msg", "操作失败!");
				request.getRequestDispatcher("/back/customerManage.jsp").forward(request, response);
			}
		} else if ("delcheck".equals(op)) {
			String cleckid = request.getParameter("cleckid");
			if (!cleckid.equals("") && cleckid != null) {
				try {
					cs.delCustomer(cleckid);
					session.setAttribute("msg", "删除成功!");
					response.sendRedirect(path + "/back/customerManage?op=manage");
				} catch (Exception e) {
					request.setAttribute("msg", "删除失败!");
					request.getRequestDispatcher("/back/customerManage?op=manage").forward(request, response);
				}
			} else {
				session.setAttribute("msg", "操作失败!");
				response.sendRedirect(path + "/back/customerManage?op=manage");
			}
		}
	}
	
}
