package lyp.dao;

import java.util.List;

import lyp.entity.OrderInfo;
import lyp.entity.PageModel;

public interface OrderInfoDao {

	void save(OrderInfo orderInfo);

	PageModel<OrderInfo> selectByPage(PageModel<OrderInfo> pm);

	void changeStatus(String orderId, int orderStatus);

	void delOrder(String orderId,OrderInfo oi);

	PageModel<OrderInfo> selectByPageStatus(PageModel<OrderInfo> pm, int orderStatus);

	PageModel<OrderInfo> selectByPageKey(PageModel<OrderInfo> pm, String keywords);

	void delOrders(String cleckid);

	OrderInfo selecteById(String id);

	List<String> selectForOrderId(int custId);

	List<OrderInfo> selecteByCustId(List<String> orderList);

}
