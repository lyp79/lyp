package lyp.daoImpl;

import java.sql.ResultSet;
import java.util.List;

import lyp.dao.GetEntity;
import lyp.dao.GoodsDao;
import lyp.entity.GoodsInfo;
import lyp.entity.OrderGoodsInfo;
import lyp.entity.PageModel;

public class GoodsDaoImpl extends BaseDaoImpl implements GoodsDao {

	/**
	 * 使用内部类实现获取实例GetEntity接口
	 * @author lyp
	 *
	 */
	private class GetGoodsEntity implements GetEntity<GoodsInfo> {
		
		@Override
		public GoodsInfo getEntity(ResultSet rs) {
			GoodsInfo goods=new GoodsInfo();
			try {
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.getGoodsType().setTypeId(rs.getInt("typeId"));
				goods.getGoodsType().setTypeName(rs.getString("typeName"));
				goods.setPrice(rs.getFloat("price"));
				goods.setDiscount(rs.getFloat("discount"));
				goods.setStatus(rs.getInt("status"));
				goods.setIsRecommend(rs.getInt("isRecommend"));
				goods.setIsNew(rs.getInt("isNew"));
				goods.setPhoto(rs.getString("photo"));
				goods.setRemark(rs.getString("remark"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return goods;
		}
	}
	
	/**
	 * 删除一个商品
	 */
	@Override
	public void delGoods(int goodsId) {
		String sql="delete from goodsinfo where goodsId=?";
		this.aduSQL(sql, goodsId);
	}

	/**
	 * 删除多个商品
	 */
	@Override
	public void delCheckGoods(String cleckid) {
		String sql = "delete from goodsinfo where goodsId in(" + cleckid + ")";
		this.aduSQL(sql);
	}

	/**
	 * id查询商品
	 */
	@Override
	public GoodsInfo selectById(int goodsId) {
		String sql="select g.*,t.typeName from goodsinfo g inner join goodstype t on g.typeId=t.typeId where goodsId=? ";
		List<GoodsInfo> goodsList = this.selectForEntity(new GetGoodsEntity(),sql, goodsId);
		return goodsList.isEmpty()?null:goodsList.get(0);
	}

	/**
	 * 名称查询商品
	 */
	@Override
	public GoodsInfo selectByName(String goodsName) {
		String sql="select g.*,t.typeName from goodsinfo g inner join goodstype t on g.typeId=t.typeId where goodsName=? ";
		List<GoodsInfo> goodsList = this.selectForEntity(new GetGoodsEntity(),sql, goodsName);
		return goodsList.isEmpty()?null:goodsList.get(0);
	}

	/**
	 * 增加商品
	 */
	@Override
	public void addGoods(GoodsInfo goods) {
		String sql="insert into goodsinfo(typeId,goodsName,price,discount,isNew,isRecommend,status,photo,remark) values(?,?,?,?,?,?,?,?,?)";
		Object[] params={goods.getGoodsType().getTypeId(),goods.getGoodsName(),goods.getPrice(),goods.getDiscount(),goods.getIsNew(),goods.getIsRecommend(),goods.getStatus(),goods.getPhoto(),goods.getRemark()};
		this.aduSQL(sql, params);
	}

	/**
	 * 修改商品
	 */
	@Override
	public void updateGoods(GoodsInfo goods) {
		String sql="update goodsinfo set typeId=?,goodsName=?,price=?,discount=?,isNew=?,isRecommend=?,status=?,photo=?,remark=? where goodsId=?";
		Object[] params={goods.getGoodsType().getTypeId(),goods.getGoodsName(),goods.getPrice(),goods.getDiscount(),goods.getIsNew(),goods.getIsRecommend(),goods.getStatus(),goods.getPhoto(),goods.getRemark(),goods.getGoodsId()};
		this.aduSQL(sql, params);
	}
	
	/**
	 * 普通分页查询
	 */
	@Override
	public PageModel<GoodsInfo> selectByPage(PageModel<GoodsInfo> pm) {
		String sql1="select g.*,t.typeName from goodsinfo g inner join goodstype t on g.typeId=t.typeId order by goodsId desc limit ?,? ";
		String sql2="select count(*) from goodsinfo";
		return this.selectByPage(new GetGoodsEntity(),pm, sql1, sql2,null);
	}

	/**
	 * 关键字分页查询
	 */
	@Override
	public PageModel<GoodsInfo> selectByPage(PageModel<GoodsInfo> pm, String keywords) {
		String sql1="select g.*,t.typeName from goodsinfo g inner join goodstype t on g.typeId=t.typeId where goodsName like (?) order by goodsId desc limit ?,? ";
		String sql2="select count(*) from goodsinfo where goodsName like (?)";
		return this.selectByPage(new GetGoodsEntity(),pm, sql1, sql2,keywords);
	}

	@Override
	public List<OrderGoodsInfo> selectByTypeId(int typeId) {
		String sql ="select t.*,g.goodsName from (select goodsId,sum(quantity) as quantity from ordergoodsinfo group by goodsId) t,goodsInfo g where t.goodsId=g.goodsId and typeId=? order by quantity desc limit 0,10";
		return this.<OrderGoodsInfo>selectForEntity(new GetEntity<OrderGoodsInfo>() {
			@Override
			public OrderGoodsInfo getEntity(ResultSet rs) {
				OrderGoodsInfo ogi = new OrderGoodsInfo();
				GoodsInfo goods = new GoodsInfo();
				try {
					goods.setGoodsId(rs.getInt("goodsId"));
					goods.setGoodsName(rs.getString("goodsName"));
					ogi.setGoods(goods);
					ogi.setQuantity(rs.getInt("quantity"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ogi;
			}
			
		}, sql, typeId);
	}

	@Override
	public void select(PageModel<GoodsInfo> pm, int typeId) {
		String sql = "select g.*,t.typeName from goodsinfo g,goodstype t where g.typeId=t.typeId and g.typeId=? order by goodsId desc limit ?,?";
		Object[] params = {typeId,(pm.getPageNo()-1)*pm.getPageSize(),pm.getPageSize()};
		List<GoodsInfo> list = this.selectForEntity(new GetGoodsEntity(), sql, params);
		pm.setData(list);
		sql = "select count(*) from goodsinfo where typeId="+typeId;
		int record = this.executeQuerySQLForInt(sql);
		pm.setTotalRecord(record);
	}

}
