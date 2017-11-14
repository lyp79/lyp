package lyp.serviceImpl;

import lyp.dao.UserDao;
import lyp.daoImpl.UserDaoImpl;
import lyp.entity.UserInfo;
import lyp.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao ud=new UserDaoImpl();

	//登录
	@Override
	public Object userLogin(String userName, String newUserPass) {
		UserInfo user=ud.selectByName(userName);
		if(user!=null&&user.getUserPass().equals(newUserPass)){
			return user;
		}else{
			return null;
		}
	}

	//修改用户名
	@Override
	public void changeName(String userName, String userPass, String newUserName) {
		UserInfo user=ud.selectByName(userName);
		if(userPass.equals(user.getUserPass())){
		ud.changeName(newUserName, user.getUserId());
		}
	}

	//修改密码
	@Override
	public void changePass(String userName, String oldUserPass, String newUserPass) {
		UserInfo user=ud.selectByName(userName);
		if(oldUserPass.equals(user.getUserPass())){
			ud.changePass(newUserPass, user.getUserId());
		}
	}

}
