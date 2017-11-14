package lyp.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lyp.dao.BulletinDao;
import lyp.dao.GetEntity;
import lyp.entity.Bulletin;
import lyp.entity.PageModel;

public class BulletinDaoImpl extends BaseDaoImpl implements BulletinDao {

	/**
	 *  ͨ��Id����
	 */
	@Override
	public Bulletin selectById(int id) {
		String sql = "select b.*,u.userName from bulletin b inner join userinfo u on b.userId=u.userId where id=?";
		List<Bulletin> bullList = this.selectForEntity(new GetBulletinEntity(), sql, id);
		return bullList.isEmpty() ? null : bullList.get(0);
	}

	/**
	 *  �������
	 */
	@Override
	public Bulletin selectByTitle(String title) {
		String sql = "select b.*,u.userName from bulletin b inner join userinfo u on b.userId=u.userId where title=?";
		List<Bulletin> bullList = this.selectForEntity(new GetBulletinEntity(), sql, title);
		return bullList.isEmpty() ? null : bullList.get(0);
	}

	/**
	 *  ��ӹ���
	 */
	@Override
	public void addBulletin(Bulletin bulletin) {
		String sql = "insert into bulletin(title,content,userId,createTime) values(?,?,?,?)";
		Object[] params = { bulletin.getTitle(), bulletin.getContent(), bulletin.getUser().getUserId(), new java.sql.Timestamp(bulletin.getCreateTime().getTime()) };
		this.aduSQL(sql, params);

	}

	/**
	 *  ɾ��һ������
	 */
	@Override
	public void delBulletin(int id) {
		String sql = "delete from bulletin where id=?";
		this.aduSQL(sql, id);

	}

	/**
	 *  ɾ���������
	 */
	@Override
	public void delBulletins(String cleckid) {
		String sql = "delete from bulletin where id in(" + cleckid + ")";
		this.aduSQL(sql);
	}

	/**
	 *  �޸Ĺ���
	 */
	@Override
	public void updateBulletin(Bulletin bulletin) {
		String sql = "update bulletin set title=?,content=? where id=?";
		Object[] params = { bulletin.getTitle(), bulletin.getContent(), bulletin.getId() };
		this.aduSQL(sql, params);
	}

	/**
	 * �ڲ���ʵ�ֻ�ȡʵ���Ľӿ�
	 */
	private class GetBulletinEntity implements GetEntity<Bulletin> {

		@Override
		public Bulletin getEntity(ResultSet rs) {
			Bulletin bulletin = new Bulletin();
			try {
				bulletin.setId(rs.getInt("id"));
				bulletin.setTitle(rs.getString("title"));
				bulletin.setContent(rs.getString("content"));
				bulletin.getUser().setUserId(rs.getInt("userId"));
				bulletin.getUser().setUserName(rs.getString("userName"));
				bulletin.setCreateTime(rs.getTimestamp("createTime"));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return bulletin;
		}
		
	}

	/**
	 * ��ͨ��ҳ����
	 */
	@Override
	public PageModel<Bulletin> selectByPage(PageModel<Bulletin> pm) {
		String sql1 = "select b.*,u.userName from bulletin b inner join userinfo u on b.userId=u.userId order by id desc limit ?,?";
		String sql2 = "select count(*) from bulletin";
		this.selectByPage(new GetBulletinEntity(), pm, sql1, sql2,null);
		return pm;
	}
	
	/**
	 * �ؼ��ַ�ҳ����
	 */
	@Override
	public PageModel<Bulletin> selectByPage(PageModel<Bulletin> pm, String keywords) {
		String sql1 = "select b.*,u.userName from bulletin b inner join userinfo u on b.userId=u.userId where title like (?) order by id desc limit ?,?";
		String sql2 = "select count(*) from bulletin where title like (?)";
		this.selectByPage(new GetBulletinEntity(), pm, sql1, sql2, keywords);
		return pm;
	}
}
