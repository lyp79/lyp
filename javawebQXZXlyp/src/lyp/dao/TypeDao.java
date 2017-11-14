package lyp.dao;

import java.util.List;

import lyp.entity.GoodsType;
import lyp.entity.PageModel;

public interface TypeDao {
	
	GoodsType selectByName(String typeName);
	
	GoodsType selectById(int typeId);

	List<GoodsType> selectAll();
	
	void addType(GoodsType type);
	
	void delType(int typeId);
	
	void updateType(GoodsType type);

	PageModel<GoodsType> selectByPage(PageModel<GoodsType> pm);
	
	PageModel<GoodsType> selectByPage(PageModel<GoodsType> pm, String keywords);

}
