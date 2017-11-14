package lyp.dao;

import lyp.entity.UserInfo;

public interface UserDao {

	UserInfo selectByName(String userName);
	
	void addUser(UserInfo user);
	
	void changeName(String userName,int userId);
	
	void changePass(String userPass,int userId);

}
