package lyp.daoImpl;

import java.sql.ResultSet;
import java.util.List;

import lyp.dao.GetEntity;
import lyp.dao.UserDao;
import lyp.entity.UserInfo;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	
	/**
	 * ʵ�ֻ��ʵ���Ľӿ�
	 * @author lyp
	 */
	private class GetUserEntity implements GetEntity<UserInfo>{

		@Override
		public UserInfo getEntity(ResultSet rs) {
			UserInfo user=new UserInfo();
			try {
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserPass(rs.getString("userPass"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return user;
		}
		
	}

	/**
	 * �û�������
	 */
	@Override
	public UserInfo selectByName(String userName) {
		String sql = "select * from userinfo where userName=?";
		List<UserInfo> userList = this.selectForEntity(new GetUserEntity(),sql, userName);
		return userList.isEmpty()?null:userList.get(0);
	}
	
	/**
	 * ע���û�
	 */
	@Override
	public void addUser(UserInfo user) {
		String sql="insert into userinfo(userName,userPass) valuses(?,?) ";
		Object[] params={user.getUserName(),user.getUserPass()};
		this.aduSQL(sql, params);
		
	}

	/**
	 * �޸��û���
	 */
	@Override
	public void changeName(String userName, int userId) {
		String sql="update userinfo set userName=? where userId=?";
		this.aduSQL(sql, userName,userId);
	}

	/**
	 * �޸�����
	 */
	@Override
	public void changePass(String userPass, int userId) {
		String sql="update userinfo set userPass=? where userId=?";
		this.aduSQL(sql, userPass,userId);
	}

}
