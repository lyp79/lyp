package lyp.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lyp.dao.FrontDao;
import lyp.dao.GetEntity;
import lyp.entity.Bulletin;
import lyp.entity.GoodsInfo;
import lyp.entity.GoodsType;

public class FrontDaoImpl extends BaseDaoImpl implements FrontDao {

	
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List> selectAll() {
		Map<String, List> indexMap = new HashMap<String, List>();
		Connection conn=null;
		try {
			conn= this.getConn();
			
			/**
			 * 查询最新的5条公告奥
			 */
			String sql = "select b.*,u.userName from bulletin b inner join userinfo u on b.userId=u.userId order by id desc limit 0,5";
			List<Bulletin> bullList = this.selectForList(conn, new GetEntity<Bulletin>(){

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
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					return bulletin;
				}}, sql);
			indexMap.put("bullList", bullList);
			
			/**
			 * 查询商品类型
			 */
			sql="select * from goodstype";
			List<GoodsType> typeList = this.selectForList(conn, new GetEntity<GoodsType>(){
				@Override
				public GoodsType getEntity(ResultSet rs) {
					GoodsType type = new GoodsType();
					try {
						type.setTypeId(rs.getInt("typeId"));
						type.setTypeName(rs.getString("typeName"));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					return type;
				}}, sql);
			/**
			 * 通过商品类型各查5个最新商品
			 */
			sql = "select goodsId,goodsName,price,discount,photo from goodsinfo where typeId=? order by goodsId desc limit 0,5";
			for(GoodsType type : typeList){
				List<GoodsInfo> list = this.selectForList(conn, new GetGoods(), sql, type.getTypeId());
				type.setList(list);
			}
			indexMap.put("typeList", typeList);
			
			/**
			 * 查询9个上架的推荐商品
			 */
			sql="select goodsId,goodsName,price,discount,photo from goodsinfo where status=0 and isRecommend=0 order by goodsId desc limit 0,9";
			List<GoodsInfo> rGoodsList = this.selectForList(conn, new GetGoods(), sql);
			indexMap.put("rGoodsList", rGoodsList);
			
			/**
			 * 查询9个上架的新品
			 */
			sql="select goodsId,goodsName,price,discount,photo from goodsinfo where status=0 and isNew=0 order by goodsId desc limit 0,9";
			List<GoodsInfo> nGoodsList = this.selectForList(conn, new GetGoods(), sql);
			indexMap.put("nGoodsList", nGoodsList);
			
			/**
			 * 查询9个上架的折扣商品
			 */
			sql="select goodsId,goodsName,price,discount,photo from goodsinfo where status=0 order by discount asc limit 0,9";
			List<GoodsInfo> dGoodsList = this.selectForList(conn, new GetGoods(), sql);
			indexMap.put("dGoodsList", dGoodsList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeAll(null, null, conn);
		}
		return indexMap;
	}

	private class GetGoods implements GetEntity<GoodsInfo>{
		@Override
		public GoodsInfo getEntity(ResultSet rs) {
			GoodsInfo goods = new GoodsInfo();
			try {
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getFloat("price"));
				goods.setDiscount(rs.getFloat("discount"));
				goods.setPhoto(rs.getString("photo"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return goods;
		}
	}
}
