package lyp.dao;

import java.util.List;

import lyp.entity.GoodsInfo;
import lyp.entity.OrderGoodsInfo;
import lyp.entity.PageModel;

public interface GoodsDao {

	void delGoods(int goodsId);

	GoodsInfo selectById(int goodsId);

	GoodsInfo selectByName(String goodsName);

	void addGoods(GoodsInfo goods);

	void updateGoods(GoodsInfo goods);
	
	void delCheckGoods(String cleckid);
	
	PageModel<GoodsInfo> selectByPage(PageModel<GoodsInfo> pm);
	
	PageModel<GoodsInfo> selectByPage(PageModel<GoodsInfo> pm, String keywords);

	List<OrderGoodsInfo> selectByTypeId(int typeId);

	void select(PageModel<GoodsInfo> pm, int typeId);



}
