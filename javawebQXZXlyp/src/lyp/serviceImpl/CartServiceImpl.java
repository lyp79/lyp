package lyp.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import lyp.entity.GoodsInfo;
import lyp.entity.OrderGoodsInfo;

public class CartServiceImpl {
	private Map<Integer,OrderGoodsInfo> cart = new HashMap<Integer,OrderGoodsInfo>();
	public Map<Integer, OrderGoodsInfo> getCart() {
		return cart;
	}

	public void addGoods(GoodsInfo goods){
		addGoods(goods,1);
	}
	public void addGoods(GoodsInfo goods, int quantity) {
		if(cart.containsKey(goods.getGoodsId())){
			OrderGoodsInfo ogi = cart.get(goods.getGoodsId());
			ogi.setQuantity(ogi.getQuantity()+quantity);
		}else{
		    OrderGoodsInfo ogi = new OrderGoodsInfo();
		    ogi.setGoods(goods);
		    ogi.setQuantity(quantity);
		    cart.put(goods.getGoodsId(), ogi);
		}
	}

	public void updateGoodsQuantity(int goodsId, int quantity) {
		if(cart.containsKey(goodsId)){
			OrderGoodsInfo ogi = cart.get(goodsId);
			ogi.setQuantity(quantity);
		}else{
		    throw new RuntimeException("购物车中没有该商品。");
		}
	}

	public void removeGoods(int goodsId) {
		if(cart.containsKey(goodsId)){
		    cart.remove(goodsId);
		}else{
			throw new RuntimeException("购物车中没有该商品。");
		}
	}

	public void clearGoods() {
		cart.clear();
	}
	public double getTotalMoney(){
		double total = 0;
		for(OrderGoodsInfo ogi : cart.values()){
			total = total + ogi.getCaculMoney();
		}
		return total;
	}
	public int getCount(){
		return cart.size();
	}
}

