package lyp.entity;

import java.util.Date;

public class CustomerInfo {
	private int id;
	private String email;
	private String pass;
	private Date regTime;
	private CustomerDetailInfo custDetail=new CustomerDetailInfo();
	
	public CustomerDetailInfo getCustDetail() {
		return custDetail;
	}
	public void setCustDetail(CustomerDetailInfo custDetail) {
		this.custDetail = custDetail;
	}
	
	public CustomerInfo() {
		super();
		custDetail.setCust(this);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
}
