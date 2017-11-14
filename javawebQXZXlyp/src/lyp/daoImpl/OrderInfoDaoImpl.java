package lyp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lyp.dao.GetEntity;
import lyp.dao.OrderInfoDao;
import lyp.entity.OrderGoodsInfo;
import lyp.entity.OrderInfo;
import lyp.entity.PageModel;

public class OrderInfoDaoImpl extends BaseDaoImpl implements OrderInfoDao {

	/**
	 * 添加订单
	 */
	@Override
	public void save(OrderInfo orderInfo) {
		Connection conn = null;
		try {
			conn = this.getConn();
			conn.setAutoCommit(false);
			String sql = "insert into orderinfo(orderId,customerId,orderStatus,orderTime) values(?,?,?,?)";
			Object[] params = { orderInfo.getOrderId(), orderInfo.getCustomer().getId(), orderInfo.getOrderStatus(),
					new java.sql.Timestamp(orderInfo.getOrderTime().getTime()) };
			this.executeUpdateSQL(conn, sql, params);
			sql = "insert into ordergoodsinfo(custOrderId,goodsId,buyPrice,quantity) values(?,?,?,?)";
			for (OrderGoodsInfo ogi : orderInfo.getGoodsList()) {
				Object[] param2 = { orderInfo.getOrderId(), ogi.getGoods().getGoodsId(),ogi.getGoods().getDiscountPrice(), ogi.getQuantity() };
				this.executeUpdateSQL(conn, sql, param2);
			}
			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			throw new RuntimeException(e);
		} finally {
			this.closeAll(null, null, conn);
		}

	}

	/**
	 * 分页查找
	 */
	@Override
	public PageModel<OrderInfo> selectByPage(PageModel<OrderInfo> pm) {
		String sql1 = "select o.*,c.*,d.* from orderinfo o inner join customerinfo c on o.customerId=c.id left join customerdetailinfo d on c.id=d.customerId order by orderId limit ?,?";
		String sql2 = "select count(*) from orderInfo";
		pm = this.selectByPage(new GetOrderInfo(), pm, sql1, sql2, null);
		return pm;
	}

	private class GetOrderInfo implements GetEntity<OrderInfo> {

		@Override
		public OrderInfo getEntity(ResultSet rs) {
			OrderInfo oi = new OrderInfo();
			try {
				oi.setOrderId(rs.getString("orderId"));
				oi.setOrderStatus(rs.getInt("orderStatus"));
				oi.setOrderTime(rs.getTimestamp("orderTime"));
				oi.getCustomer().setId(rs.getInt("customerId"));
				oi.getCustomer().setEmail(rs.getString("email"));
				oi.getCustomer().setRegTime(rs.getTimestamp("regTime"));
				oi.getCustomer().getCustDetail().setName(rs.getString("name"));
				oi.getCustomer().getCustDetail().setTelPhone(rs.getString("telPhone"));
				oi.getCustomer().getCustDetail().setMovePhone(rs.getString("movePhone"));
				oi.getCustomer().getCustDetail().setAddress(rs.getString("address"));

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return oi;
		}

	}

	/**
	 * 改变订单状态
	 */
	@Override
	public void changeStatus(String orderId, int orderStatus) {
		String sql = "update orderinfo set orderStatus=? where orderId=?";
		Object[] params = { orderStatus, orderId };
		this.aduSQL(sql, params);
	}

	/**
	 * 删除一个订单
	 */
	@Override
	public void delOrder(String orderId ,OrderInfo oi) {
		if(!oi.getGoodsList().isEmpty()){
			String sql = "delete from ordergoodsinfo where custOrderId=?";
			this.aduSQL(sql, orderId);
		}
		String sql = "delete from orderinfo where orderId=?";
		this.aduSQL(sql, orderId);
	}

	/**
	 * 删除多个订单
	 */
	@Override
	public void delOrders(String cleckid) {
		String[] cleckids = cleckid.split(",");
		for (String id : cleckids) {
			String sql = "delete from ordergoodsinfo where custOrderId=?";
			this.aduSQL(sql, id);
			sql = "delete from orderinfo where orderId=?";
			this.aduSQL(sql, id);
		}
	}

	/**
	 * 订单状态分页查询
	 */
	@Override
	public PageModel<OrderInfo> selectByPageStatus(PageModel<OrderInfo> pm, int orderStatus) {
		String sql1 = "select o.*,c.*,d.* from orderinfo o inner join customerinfo c on o.customerId=c.id left join customerdetailinfo d on c.id=d.customerId where orderStatus=? order by orderId limit ?,?";
		String sql2 = "select count(*) from orderInfo where orderStatus=?";
		pm = this.selectByPageInt(new GetOrderInfo(), pm, sql1, sql2, orderStatus);
		return pm;
	}

	/**
	 * 关键字分页查询
	 */
	@Override
	public PageModel<OrderInfo> selectByPageKey(PageModel<OrderInfo> pm, String keywords) {
		String sql1 = "select o.*,c.*,d.* from orderinfo o inner join customerinfo c on o.customerId=c.id inner join customerdetailinfo d on c.id=d.customerId where o.orderId like ? or c.email like ? or d.name like ? or d.telPhone like ? or d.movePhone like ? order by o.orderId limit ?,?";
		String sql2 = "select count(*) from orderInfo o inner join customerinfo c on c.id=o.customerId inner join customerdetailinfo d on d.customerId = c.id where o.orderId like ? or c.email like ? or d.name like ? or d.telPhone like ? or d.movePhone like ?";
		Object[] params = { keywords, keywords, keywords, keywords, keywords };
		pm = this.selectByPageP(new GetOrderInfo(), pm, sql1, sql2, params);
		return pm;
	}

	/**
	 * id查找
	 */
	@Override
	public OrderInfo selecteById(String id) {
		OrderInfo orderInfo = new OrderInfo();
		String sql = "select o.*,c.*,d.* from orderinfo o inner join customerinfo c on o.customerId=c.id inner join customerdetailinfo d on c.id=d.customerId where orderId=?";
		orderInfo = this.selectForEntity(new GetOrderEntity(), sql, id).get(0);
		sql = "select o.*,g.*,t.typeName from ordergoodsinfo o inner join goodsinfo g on o.goodsId=g.goodsId inner join goodstype t on g.typeId=t.typeId where custOrderId=?";
		orderInfo.setGoodsList(this.selectForEntity(new GetOrderDetail(), sql, id));
		return orderInfo;
	}

	/**
	 * 获取订单编号，状态，下单时间，客户资料
	 * 
	 * @author lyp
	 *
	 */
	private class GetOrderEntity implements GetEntity<OrderInfo> {
		@Override
		public OrderInfo getEntity(ResultSet rs) throws SQLException {
			OrderInfo oi = new OrderInfo();
			oi.setOrderId(rs.getString("orderId"));
			oi.setOrderStatus(rs.getInt("orderStatus"));
			oi.setOrderTime(rs.getTimestamp("orderTime"));
			oi.getCustomer().setEmail(rs.getString("email"));
			oi.getCustomer().setRegTime(rs.getTimestamp("regTime"));
			oi.getCustomer().getCustDetail().setName(rs.getString("name"));
			oi.getCustomer().getCustDetail().setTelPhone(rs.getString("telPhone"));
			oi.getCustomer().getCustDetail().setMovePhone(rs.getString("movePhone"));
			oi.getCustomer().getCustDetail().setAddress(rs.getString("address"));
			return oi;
		}
	}

	/**
	 * 获取订单商品详情
	 * 
	 * @author lyp
	 *
	 */
	private class GetOrderDetail implements GetEntity<OrderGoodsInfo> {
		@Override
		public OrderGoodsInfo getEntity(ResultSet rs) {

			OrderGoodsInfo ogi = new OrderGoodsInfo();
			try {
				ogi.setQuantity(rs.getInt("quantity"));
				ogi.setBuyPrice(rs.getDouble("buyPrice"));
				ogi.getGoods().setGoodsId(rs.getInt("goodsId"));
				ogi.getGoods().getGoodsType().setTypeName(rs.getString("typeName"));
				ogi.getGoods().setGoodsName(rs.getString("goodsName"));
				ogi.getGoods().setPhoto(rs.getString("photo"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ogi;
		}

	}

	/**
	 * 通过客户id查找订单编号
	 */
	@Override
	public List<String> selectForOrderId(int custId) {
		List<String> orderList = new ArrayList<String>();
		String sql = "select o.orderId from orderinfo o inner join customerinfo c on o.customerId=c.id where o.customerId=?";
		Connection conn  =null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = this.getConn();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, custId);
			rs=pst.executeQuery();
			while(rs.next()){
				orderList.add(rs.getString("orderId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(rs, pst, conn);
		}
		return orderList;
	}

	/**
	 * 遍历订单编号查找订单详情
	 * return 订单集合 ordersList
	 */
	@Override
	public List<OrderInfo> selecteByCustId(List<String> orderList) {
		List<OrderInfo> ordersList = new ArrayList<OrderInfo>();
		for (String orderId : orderList) {
			ordersList.add(this.selecteById(orderId));
		}
		return ordersList;
	}
}
