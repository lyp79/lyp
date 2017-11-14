package lyp.daoImpl;

import java.sql.ResultSet;
import java.util.List;

import lyp.dao.CustomerDao;
import lyp.dao.GetEntity;
import lyp.entity.CustomerInfo;
import lyp.entity.PageModel;

/**
 * Customer 数据访问实现类
 * 
 * @author lyp
 *
 */
public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao {

	/**
	 * 实现获取实例的接口
	 * 
	 * @author lyp
	 *
	 */
	private class GetCustomerEntity implements GetEntity<CustomerInfo> {
		@Override
		public CustomerInfo getEntity(ResultSet rs) {
			CustomerInfo cust = new CustomerInfo();
			try {
				cust.setId(rs.getInt("id"));
				cust.setEmail(rs.getString("email"));
				cust.setPass(rs.getString("pass"));
				cust.setRegTime(rs.getTimestamp("regTime"));
				cust.getCustDetail().setName(rs.getString("name"));
				cust.getCustDetail().setTelPhone(rs.getString("telPhone"));
				cust.getCustDetail().setMovePhone(rs.getString("movePhone"));
				cust.getCustDetail().setAddress(rs.getString("address"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cust;
		}

	}

	/**
	 * 普通分页查找
	 */
	@Override
	public PageModel<CustomerInfo> selectByPage(PageModel<CustomerInfo> pm) {
		String sql1 = "select * from customerinfo c left outer join customerdetailinfo d on c.id=d.customerId  order by id desc limit ?,?";
		String sql2 = "select count(*) from customerinfo";
		return this.selectByPage(new GetCustomerEntity(), pm, sql1, sql2, null);
	}

	/**
	 * 关键字分页查找
	 */
	@Override
	public PageModel<CustomerInfo> selectByPage(PageModel<CustomerInfo> pm, String keywords) {
		String sql1 = "select * from customerinfo c left outer join customerdetailinfo d on c.id=d.customerId where email like(?) order by id desc limit ?,?";
		String sql2 = "select count(*) from customerinfo where email like(?)";
		return this.selectByPage(new GetCustomerEntity(), pm, sql1, sql2, keywords);
	}

	/**
	 * id查询
	 */
	@Override
	public CustomerInfo selectById(int id) {
		String sql = "select * from customerinfo c left outer join customerdetailinfo d on c.id=d.customerId where id=?";
		List<CustomerInfo> custList =this.selectForEntity(new GetCustomerEntity(), sql, id);
		return custList.isEmpty() ? null: custList.get(0);
	}

	/**
	 * 删除客户
	 */
	@Override
	public void deleteCust(String id) {
		String sql = "delete from customerinfo where id in(" + id + ")";
		this.aduSQL(sql);

	}

	/**
	 * 修改客户
	 */
	@Override
	public void updateCustomer(CustomerInfo customer) {
		String sql = "update CustomerInfo c left outer join customerdetailinfo d on c.id=d.customerId set email=?,name=?,telPhone=?,movePhone=?,address=? where id=?";
		Object[] params = { customer.getEmail(), customer.getCustDetail().getName(),
				customer.getCustDetail().getTelPhone(), customer.getCustDetail().getMovePhone(),
				customer.getCustDetail().getAddress(), customer.getId() };
		this.aduSQL(sql, params);
	}

	@Override
	public boolean isExistsDetail(int id) {
		String sql = "select count(*) from customerdetailinfo where customerId=" + id;
		int rel = this.executeQuerySQLForInt(sql);
		if (rel == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void updateDetail(CustomerInfo cust) {
		String sql = "update customerdetailinfo set name=?,telPhone=?,movePhone=?,address=? where customerId=?";
		Object[] params = { cust.getCustDetail().getName(), cust.getCustDetail().getTelPhone(),
				cust.getCustDetail().getMovePhone(), cust.getCustDetail().getAddress(), cust.getId() };
		this.aduSQL(sql, params);
	}

	@Override
	public void saveDetail(CustomerInfo cust) {
		String sql = "insert into customerdetailinfo(customerId,name,telPhone,movePhone,address) values(?,?,?,?,?)";
		Object[] params = { cust.getId(), cust.getCustDetail().getName(), cust.getCustDetail().getTelPhone(),
				cust.getCustDetail().getMovePhone(), cust.getCustDetail().getAddress() };
		this.aduSQL(sql, params);
	}

	@Override
	public CustomerInfo selectByEmail(String email) {
		String sql = "select * from customerinfo c left join customerdetailinfo d on c.id=d.customerId where email=?";
		List<CustomerInfo> custList = this.selectForEntity(new GetCustomerEntity(), sql, email);
		if (!custList.isEmpty()) {
			return custList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addCust(CustomerInfo cust) {
		String sql = "insert into customerinfo(email,pass,regTime) values(?,?,?)";
		this.aduSQL(sql, cust.getEmail(), cust.getPass(), new java.sql.Timestamp(cust.getRegTime().getTime()));
	}

}
