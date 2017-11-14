package lyp.entity;

import java.text.DecimalFormat;

public class OrderGoodsInfo {
	private OrderInfo orderInfo = new OrderInfo();
	private GoodsInfo goods=new GoodsInfo();
	private double buyPrice;
	private int quantity;
	
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public GoodsInfo getGoods() {
		return goods;
	}
	public void setGoods(GoodsInfo goods) {
		this.goods = goods;
	}
	public double getCaculMoney() {
		DecimalFormat df= new DecimalFormat("#.00");
		double caculMoney=Double.parseDouble(df.format(this.quantity*this.goods.getDiscountPrice()));
		return caculMoney;
		
	}
}
