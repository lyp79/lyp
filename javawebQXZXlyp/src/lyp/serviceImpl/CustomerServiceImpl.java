package lyp.serviceImpl;

import lyp.dao.CustomerDao;
import lyp.daoImpl.CustomerDaoImpl;
import lyp.entity.CustomerInfo;
import lyp.entity.PageModel;
import lyp.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	CustomerDao cd=new CustomerDaoImpl();

	@Override
	public PageModel<CustomerInfo> getCustomerByPage(PageModel<CustomerInfo> pm) {
		return cd.selectByPage(pm);
	}

	@Override
	public PageModel<CustomerInfo> getCustomerByPage(PageModel<CustomerInfo> pm, String keywords) {
		return cd.selectByPage(pm,keywords);
	}

	@Override
	public CustomerInfo getCustomerById(int id) {
		return cd.selectById(id);
	}

	@Override
	public void delCustomer(String id) {
		cd.deleteCust(id);
	}

	@Override
	public void updateCust(CustomerInfo customer) {
		cd.updateCustomer(customer);
	}

	public void updateCustDetail(CustomerInfo cust) {
		if(cd.isExistsDetail(cust.getId())){
			cd.updateDetail(cust);
		}else{
			cd.saveDetail(cust);
		}
			
	}

	@Override
	public CustomerInfo login(String email, String userPwd) {
		CustomerInfo cust = cd.selectByEmail(email);
		if(cust!=null&&cust.getPass().equals(userPwd)){
			return cust;
		}
		return null;
	}

	@Override
	public void addCustomer(CustomerInfo cust) {
		CustomerInfo cust1 = cd.selectByEmail(cust.getEmail());
		if(cust1==null){
		cd.addCust(cust);
		}else{
			throw new RuntimeException("注册失败，用户名已存在！");
		}
	}
	
	@Override
	public CustomerInfo getCustomer(String email) {
		return cd.selectByEmail(email);
	}
	
	@Override
	public boolean  getCust(String email) {
		CustomerInfo cust = cd.selectByEmail(email);
		if(cust==null){
			return false;
		}
		return true;
	}
	
}
