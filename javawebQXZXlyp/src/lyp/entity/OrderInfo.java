package lyp.entity;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private String orderId;
	private CustomerInfo customer = new CustomerInfo();
	private int orderStatus;
	private Date orderTime;
	private List<OrderGoodsInfo> goodsList;

	public List<OrderGoodsInfo> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<OrderGoodsInfo> goodsList) {
		this.goodsList = goodsList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public CustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
}
