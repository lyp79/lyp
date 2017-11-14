package lyp.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lyp.dao.GetEntity;
import lyp.dao.TypeDao;
import lyp.entity.GoodsType;
import lyp.entity.PageModel;

public class TypeDaoImpl extends BaseDaoImpl implements TypeDao {
	
	/**
	 * 实现 获得实例GetEntity接口
	 * @author lyp
	 *
	 */
	private class GetTypeEntity implements GetEntity<GoodsType>{

		@Override
		public GoodsType getEntity(ResultSet rs) {
			GoodsType type = new GoodsType();
			try {
				type.setTypeId(rs.getInt("typeId"));
				type.setTypeName(rs.getString("typeName"));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return type;
		}
		
	}

	/**
	 *  普通分页查找
	 */
	@Override
	public PageModel<GoodsType> selectByPage(PageModel<GoodsType> pm) {
		String sql1 = "select * from goodstype order by typeId desc limit ?,?";
		String sql2 = "select count(*) from goodsType";
		return this.selectByPage(new GetTypeEntity(),pm, sql1, sql2,null);
	}

	/**
	 *  关键字分页查找
	 */
	@Override
	public PageModel<GoodsType> selectByPage(PageModel<GoodsType> pm, String keywords) {
		String sql1 = "select * from goodstype where typeName like (?) order by typeId desc limit ?,?";
		String sql2 = "select count(*) from goodstype where typeName like (?)";
		return this.selectByPage(new GetTypeEntity(),pm, sql1, sql2, keywords);
	}

	/**
	 *  通过类型名查找
	 */
	@Override
	public GoodsType selectByName(String typeName) {
		String sql = "select * from goodstype where typeName=?";
		List<GoodsType> typeList = this.selectForEntity(new GetTypeEntity(),sql, typeName);
		return typeList.isEmpty() ? null : typeList.get(0);
	}

	/**
	 *  通过id查找
	 */
	@Override
	public GoodsType selectById(int typeId) {
		String sql = "select * from goodstype where typeId=?";
		List<GoodsType> typeList = this.selectForEntity(new GetTypeEntity(),sql, typeId);
		return typeList.isEmpty() ? null : typeList.get(0);
	}

	/**
	 *  查询所有
	 */
	@Override
	public List<GoodsType> selectAll() {
		String sql = "select * from goodstype";
		return this.select(new GetTypeEntity(),sql);
	}

	/**
	 *  增加类型
	 */
	@Override
	public void addType(GoodsType type) {
		String sql = "insert into goodstype(typeName) values(?)";
		String typeName = type.getTypeName();
		this.aduSQL(sql, typeName);

	}

	/**
	 *  删除类型
	 */
	@Override
	public void delType(int typeId) {
		String sql = "delete from goodstype where typeId=?";
		this.aduSQL(sql, typeId);
	}

	/**
	 *  修改类型
	 */
	@Override
	public void updateType(GoodsType type) {
		String sql = "update goodstype set typeName=? where typeId=?";
		Object[] params = { type.getTypeName(), type.getTypeId() };
		this.aduSQL(sql, params);
	}

}
