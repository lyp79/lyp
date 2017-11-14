package lyp.entity;

public class CustomerDetailInfo {
	private String name;
	private String telPhone;
	private String movePhone;
	private String address;
	private CustomerInfo cust;
	
	public CustomerInfo getCust() {
		return cust;
	}
	public void setCust(CustomerInfo cust) {
		this.cust = cust;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getMovePhone() {
		return movePhone;
	}
	public void setMovePhone(String movePhone) {
		this.movePhone = movePhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
