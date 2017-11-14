package lyp.service;

import java.util.List;

import lyp.entity.OrderInfo;
import lyp.entity.PageModel;

public interface OrderInfoService {

	void addOrderInfo(OrderInfo orderInfo);
	
	PageModel<OrderInfo> getOrderByPage(PageModel<OrderInfo> pm);

	void changeStatus(String orderId, int orderStatus);

	void delOrder(String orderId);

	PageModel<OrderInfo> getOrderByPage(PageModel<OrderInfo> pm, int orderStatus);

	PageModel<OrderInfo> getOrderByPageKey(PageModel<OrderInfo> pm, String keywords);

	void delOrders(String cleckid);

	OrderInfo getOrderById(String id);

	List<OrderInfo> getOrdersByCustId(int custId);

}
