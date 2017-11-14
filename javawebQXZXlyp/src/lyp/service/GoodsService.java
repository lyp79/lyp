package lyp.service;

import java.util.List;

import lyp.entity.GoodsInfo;
import lyp.entity.OrderGoodsInfo;
import lyp.entity.PageModel;

public interface GoodsService {

	GoodsInfo selectById(int goodsId);
	
	GoodsInfo selectByName(String goodsName);

	void delGoods(int goodsId);

	void addGoods(GoodsInfo goods);

	void updateGoods(GoodsInfo goods);
	
	PageModel<GoodsInfo> getGoodsPage(PageModel<GoodsInfo> pm);

	PageModel<GoodsInfo> getGoodsPage(PageModel<GoodsInfo> pm, String keywords);

	void delCheckGoods(String cleckid);

	List<OrderGoodsInfo> getGoodsInfoTop10(int typeId);

	PageModel<GoodsInfo> getGoodsPageById(PageModel<GoodsInfo> pm, int id);

}
