package lyp.serviceImpl;

import java.util.List;

import lyp.dao.GoodsDao;
import lyp.daoImpl.GoodsDaoImpl;
import lyp.entity.GoodsInfo;
import lyp.entity.OrderGoodsInfo;
import lyp.entity.PageModel;
import lyp.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	GoodsDao gd=new GoodsDaoImpl();

	@Override
	public void delGoods(int goodsId) {
		gd.delGoods(goodsId);
		
	}

	@Override
	public void delCheckGoods(String cleckid) {
		gd.delCheckGoods(cleckid);
		
	}

	@Override
	public PageModel<GoodsInfo> getGoodsPage(PageModel<GoodsInfo> pm) {
		pm= gd.selectByPage(pm);
		return pm;
	}
	
	@Override
	public PageModel<GoodsInfo> getGoodsPage(PageModel<GoodsInfo> pm,String keywords) {
		pm= gd.selectByPage(pm,keywords);
		return pm;
	}

	@Override
	public GoodsInfo selectById(int goodsId) {
		GoodsInfo goods=gd.selectById(goodsId);
		return goods;
	}

	@Override
	public GoodsInfo selectByName(String goodsName) {
		GoodsInfo goods=gd.selectByName(goodsName);
		return goods;
	}

	@Override
	public void addGoods(GoodsInfo goods) {
			gd.addGoods(goods);
	
	}

	@Override
	public void updateGoods(GoodsInfo goods) {
		gd.updateGoods(goods);
	}

	@Override
	public List<OrderGoodsInfo> getGoodsInfoTop10(int typeId) {
		return gd.selectByTypeId(typeId);
	}

	@Override
	public PageModel<GoodsInfo> getGoodsPageById(PageModel<GoodsInfo> pm, int typeId) {
		gd.select(pm,typeId);
		return pm;
	}

}
