package lyp.service;

import java.util.List;

import lyp.entity.GoodsType;
import lyp.entity.PageModel;

public interface TypeService {

	void delType(int parseInt);

	GoodsType selectById(int parseInt);

	void addType(GoodsType type);

	void updateType(GoodsType type);

	List<GoodsType> selectAll();

	PageModel<GoodsType> getTypePage(PageModel<GoodsType> pm);

	PageModel<GoodsType> getTypePage(PageModel<GoodsType> pm, String keywords);

}
