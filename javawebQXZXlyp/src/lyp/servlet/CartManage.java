package lyp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lyp.entity.GoodsInfo;
import lyp.serviceImpl.CartServiceImpl;
import lyp.serviceImpl.GoodsServiceImpl;

public class CartManage extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String path = (String) request.getSession().getAttribute("path");
		CartServiceImpl cart = null;
		if(request.getSession().getAttribute("cart")==null){
			cart = new CartServiceImpl();
			request.getSession().setAttribute("cart", cart);
		}else{
			cart =(CartServiceImpl)request.getSession().getAttribute("cart");
		}
		if("add".equals(op)){
			int goodsId =Integer.parseInt(request.getParameter("id"));
			int quantity = Integer.parseInt(request.getParameter("num"));
			GoodsInfo goods = new GoodsServiceImpl().selectById(goodsId);
			cart.addGoods(goods,quantity);
		}else if("change".equals(op)){
			int goodsId = Integer.parseInt(request.getParameter("id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			cart.updateGoodsQuantity(goodsId, quantity);
		}else if("remove".equals(op)){
			int goodsId = Integer.parseInt(request.getParameter("id"));
			cart.removeGoods(goodsId);
		}else if("clear".equals(op)){
			cart.clearGoods();
		}
		response.sendRedirect(path+"/front/showCart.jsp");
	}

}
