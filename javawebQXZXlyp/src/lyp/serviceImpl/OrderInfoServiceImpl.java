package lyp.serviceImpl;

import java.util.List;

import lyp.dao.OrderInfoDao;
import lyp.daoImpl.OrderInfoDaoImpl;
import lyp.entity.OrderInfo;
import lyp.entity.PageModel;
import lyp.service.OrderInfoService;

public class OrderInfoServiceImpl implements OrderInfoService {

	private OrderInfoDao orderDao = new OrderInfoDaoImpl();
	
	/**
	 * 添加订单
	 */
	@Override
	public void addOrderInfo(OrderInfo orderInfo) {
		orderDao.save(orderInfo);
	}
	
	/**
	 * 默认分页查找
	 */
	@Override
	public PageModel<OrderInfo> getOrderByPage(PageModel<OrderInfo> pm) {
		pm = orderDao.selectByPage(pm);
		return pm;
	}
	
	/**
	 * 改变订单状态
	 */
	@Override
	public void changeStatus(String orderId, int orderStatus) {
		if(orderStatus==0){
			orderStatus=1;
		}else{
			orderStatus=0;
		}
		orderDao.changeStatus(orderId,orderStatus);
	}
	
	/**
	 * 删除一个订单
	 */
	@Override
	public void delOrder(String orderId) {
		OrderInfo ogi = orderDao.selecteById(orderId);
		orderDao.delOrder(orderId,ogi);
	}

	/**
	 * 删除多个订单
	 */
	@Override
	public void delOrders(String cleckid) {
		String[] ids = cleckid.split(",");
		for (String orderId : ids) {
			OrderInfo ogi = orderDao.selecteById(orderId);
			orderDao.delOrder(orderId,ogi);
		}
	}
	
	/**
	 * 订单状态分页查找
	 */
	@Override
	public PageModel<OrderInfo> getOrderByPage(PageModel<OrderInfo> pm, int orderStatus) {
		pm = orderDao.selectByPageStatus(pm,orderStatus);
		return pm;
	}
	
	/**
	 * 关键字分页查找
	 */
	@Override
	public PageModel<OrderInfo> getOrderByPageKey(PageModel<OrderInfo> pm, String keywords) {
		pm = orderDao.selectByPageKey(pm,keywords);
		return pm;
	}
	
	/**
	 * 订单编号查找
	 */
	@Override
	public OrderInfo getOrderById(String id) {
		return orderDao.selecteById(id);
	}

	/**
	 * 通过客户编号查找订单
	 * @param custId
	 * @return
	 */
	@Override
	public List<OrderInfo> getOrdersByCustId(int custId) {
		List<String> orderList = orderDao.selectForOrderId(custId);
		return orderDao.selecteByCustId(orderList);
	}

}
