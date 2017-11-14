package lyp.service;

import lyp.entity.CustomerInfo;
import lyp.entity.PageModel;

public interface CustomerService {

	PageModel<CustomerInfo> getCustomerByPage(PageModel<CustomerInfo> pm);

	PageModel<CustomerInfo> getCustomerByPage(PageModel<CustomerInfo> pm, String keywords);

	CustomerInfo getCustomerById(int id);

	void delCustomer(String parameter);

	void updateCust(CustomerInfo customer);

	CustomerInfo login(String userName, String userPwd);

	void addCustomer(CustomerInfo cust);

	CustomerInfo getCustomer(String email);

	boolean getCust(String email);

}
