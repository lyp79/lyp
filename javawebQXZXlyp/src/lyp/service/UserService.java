package lyp.service;

public interface UserService {

	void changePass(String userName, String oldUserPass, String newUserPass);

	Object userLogin(String userName, String newUserPass);

	void changeName(String userName, String userPass, String newUserName);

}
