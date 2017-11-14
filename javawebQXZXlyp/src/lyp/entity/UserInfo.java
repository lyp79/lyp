package lyp.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String userPass;
	 
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
}
