package lyp.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lyp.entity.CustomerInfo;
import lyp.entity.OrderInfo;
import lyp.service.CustomerService;
import lyp.serviceImpl.CustomerServiceImpl;
import lyp.serviceImpl.OrderInfoServiceImpl;

public class CustFrontManage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	private CustomerService custService = new CustomerServiceImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("login".equals(op)) {
			String userName = request.getParameter("userName");
			String userPwd = request.getParameter("userPwd");
			String yzm = request.getParameter("yzm");
			String code = (String) request.getSession().getAttribute("code");
			request.getSession().removeAttribute("code");
			if (userName == null || userName.equals("")) {
				request.setAttribute("msg", "用户名不能为空。");
				request.getRequestDispatcher("/front/login_register.jsp").forward(request, response);
				return;
			} else if (userPwd == null || userPwd.equals("")) {
				request.setAttribute("msg", "密码不能为空。");
				request.getRequestDispatcher("/front/login_register.jsp").forward(request, response);
				return;
			} else if (!code.equals(yzm)) {
				request.setAttribute("msg", "验证码错误。");
				request.getRequestDispatcher("/front/login_register.jsp").forward(request, response);
				return;
			}
			CustomerInfo cust = custService.login(userName, userPwd);
			if (cust != null) {
				request.getSession().setAttribute("customerLogin", cust);
				response.sendRedirect(request.getContextPath() + "/front/index.jsp");
			} else {
				request.setAttribute("msg", "用户名或密码错误。");
				request.getRequestDispatcher("/front/login_register.jsp").forward(request, response);
			}

		} else if ("loginOut".equals(op)) {
			request.getSession().removeAttribute("customerLogin");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/front/index.jsp");
		} else if ("register".equals(op)) {
			CustomerInfo cust = new CustomerInfo();
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			String repwd = request.getParameter("repwd");
			if (!pwd.equals(repwd)) {
				request.setAttribute("remsg", "两次密码输入不一致。");
				request.getRequestDispatcher("/front/login_register.jsp").forward(request, response);
				return;
			}
			cust.setEmail(email);
			cust.setPass(pwd);
			cust.setRegTime(new Date());
			String chkGaoji = request.getParameter("chkGaoji");
			if (chkGaoji != null && chkGaoji.equals("0")) {
				String name = request.getParameter("name");
				String telphone = request.getParameter("telPhone");
				String movePhone = request.getParameter("movePhone");
				String address = request.getParameter("address");
				cust.getCustDetail().setName(name);
				cust.getCustDetail().setTelPhone(telphone);
				cust.getCustDetail().setMovePhone(movePhone);
				cust.getCustDetail().setAddress(address);
			} else {
				cust.setCustDetail(null);
			}
			custService.addCustomer(cust);
			request.getSession().setAttribute("msg", "注册成功请登录。");
			response.sendRedirect(request.getContextPath() + "/front/login_register.jsp");
		}else if("showCustDetail".equals(op)){
			if(request.getSession().getAttribute("orderList")==null){
			int custId = Integer.parseInt(request.getParameter("id"));
			List<OrderInfo> orderList = new OrderInfoServiceImpl().getOrdersByCustId(custId);
			CustomerInfo cust = orderList.get(0).getCustomer();
			request.getSession().setAttribute("cust", cust);
			request.getSession().setAttribute("orderList", orderList);
			}
			response.sendRedirect(request.getContextPath()+"/front/showMe.jsp");
		}
	}

}
