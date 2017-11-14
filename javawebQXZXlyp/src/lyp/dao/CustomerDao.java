package lyp.dao;

import lyp.entity.CustomerInfo;
import lyp.entity.PageModel;

public interface CustomerDao {

	PageModel<CustomerInfo> selectByPage(PageModel<CustomerInfo> pm);

	PageModel<CustomerInfo> selectByPage(PageModel<CustomerInfo> pm, String keywords);

	CustomerInfo selectById(int id);

	void deleteCust(String id);

	void updateCustomer(CustomerInfo customer);

	boolean isExistsDetail(int id);

	void updateDetail(CustomerInfo cust);

	void saveDetail(CustomerInfo cust);

	CustomerInfo selectByEmail(String email);

	void addCust(CustomerInfo cust);

}
