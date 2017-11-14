package lyp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lyp.entity.CustomerInfo;
import lyp.entity.OrderGoodsInfo;
import lyp.entity.OrderInfo;
import lyp.serviceImpl.CartServiceImpl;
import lyp.serviceImpl.CustomerServiceImpl;
import lyp.serviceImpl.OrderInfoServiceImpl;
import lyp.utils.MyUtils;

public class PayManage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerInfo cust = (CustomerInfo) request.getSession().getAttribute("customerLogin");
		String path = (String) request.getSession().getAttribute("path");
		if (cust == null) {
			// 还没有登录，就去登录页面
			request.getRequestDispatcher("/front/login_register.jsp").forward(request, response);
		} else {
			CartServiceImpl cart = (CartServiceImpl) request.getSession().getAttribute("cart");
			if (cart == null) {
				response.sendRedirect(path + "/front/index.jsp");
				return;
			}
			String op = request.getParameter("op");
			if ("peisong".equals(op)) {
				request.getRequestDispatcher("/front/peisong.jsp").forward(request, response);
			} else if ("detail".equals(op)) {
				// 更新配送信息。
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String telphone = request.getParameter("telPhone");
				String movePhone = request.getParameter("movePhone");
				String address = request.getParameter("address");
				cust.setEmail(email);
				cust.getCustDetail().setName(name);
				cust.getCustDetail().setTelPhone(telphone);
				cust.getCustDetail().setMovePhone(movePhone);
				cust.getCustDetail().setAddress(address);
				CustomerInfo ct = new CustomerServiceImpl().getCustomer(email);
				if (ct.getCustDetail() != null) {
					boolean n = ct.getCustDetail().getName().equals(cust.getCustDetail().getName());
					boolean t = ct.getCustDetail().getTelPhone().equals(cust.getCustDetail().getTelPhone());
					boolean m = ct.getCustDetail().getMovePhone().equals(cust.getCustDetail().getMovePhone());
					boolean a = ct.getCustDetail().getAddress().equals(cust.getCustDetail().getAddress());
					if (!n || !t || !m || !a) {
						new CustomerServiceImpl().updateCustDetail(cust);
					}
				}else{
					new CustomerServiceImpl().updateCustDetail(cust);
				}
				request.getRequestDispatcher("/front/confirm.jsp").forward(request, response);
			} else if ("add".equals(op)) {
				OrderInfo orderInfo = new OrderInfo();
				String orderId = new MyUtils().getOrderId();
				orderInfo.setOrderId(orderId);
				orderInfo.setCustomer(cust);
				List<OrderGoodsInfo> list = new ArrayList<OrderGoodsInfo>(cart.getCart().values());
				orderInfo.setGoodsList(list);
				orderInfo.setOrderTime(new Date());
				try {
					new OrderInfoServiceImpl().addOrderInfo(orderInfo);
					request.getSession().setAttribute("orderId", orderId);
					cart.clearGoods();
					response.sendRedirect(path + "/front/ok.jsp");
				} catch (Exception e) {
					request.getRequestDispatcher("/front/fail.jsp").forward(request, response);
				}
			}

		}
	}

}
