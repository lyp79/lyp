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
	 * ��Ӷ���
	 */
	@Override
	public void addOrderInfo(OrderInfo orderInfo) {
		orderDao.save(orderInfo);
	}
	
	/**
	 * Ĭ�Ϸ�ҳ����
	 */
	@Override
	public PageModel<OrderInfo> getOrderByPage(PageModel<OrderInfo> pm) {
		pm = orderDao.selectByPage(pm);
		return pm;
	}
	
	/**
	 * �ı䶩��״̬
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
	 * ɾ��һ������
	 */
	@Override
	public void delOrder(String orderId) {
		OrderInfo ogi = orderDao.selecteById(orderId);
		orderDao.delOrder(orderId,ogi);
	}

	/**
	 * ɾ���������
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
	 * ����״̬��ҳ����
	 */
	@Override
	public PageModel<OrderInfo> getOrderByPage(PageModel<OrderInfo> pm, int orderStatus) {
		pm = orderDao.selectByPageStatus(pm,orderStatus);
		return pm;
	}
	
	/**
	 * �ؼ��ַ�ҳ����
	 */
	@Override
	public PageModel<OrderInfo> getOrderByPageKey(PageModel<OrderInfo> pm, String keywords) {
		pm = orderDao.selectByPageKey(pm,keywords);
		return pm;
	}
	
	/**
	 * ������Ų���
	 */
	@Override
	public OrderInfo getOrderById(String id) {
		return orderDao.selecteById(id);
	}

	/**
	 * ͨ���ͻ���Ų��Ҷ���
	 * @param custId
	 * @return
	 */
	@Override
	public List<OrderInfo> getOrdersByCustId(int custId) {
		List<String> orderList = orderDao.selectForOrderId(custId);
		return orderDao.selecteByCustId(orderList);
	}

}
