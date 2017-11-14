package lyp.serviceImpl;


import java.util.List;

import lyp.dao.TypeDao;
import lyp.daoImpl.TypeDaoImpl;
import lyp.entity.GoodsType;
import lyp.entity.PageModel;
import lyp.service.TypeService;

public class TypeServiceImpl implements TypeService {
	TypeDao td = new TypeDaoImpl();

	// id查找
	@Override
	public GoodsType selectById(int typeId) {
		GoodsType type = td.selectById(typeId);
		if (type != null) {
			return type;
		} else {
			return null;
		}
	}
	
	//查询所有类型
	@Override
	public List<GoodsType> selectAll() {
		List<GoodsType> list= td.selectAll();
		return list;
	}
	
	//获取分页
		@Override
		public PageModel<GoodsType> getTypePage(PageModel<GoodsType> pm) {
			pm=td.selectByPage(pm);
			return pm;
		}

		@Override
		public PageModel<GoodsType> getTypePage(PageModel<GoodsType> pm, String keywords) {
			pm=td.selectByPage(pm,keywords);
			return pm;
		}
	
	// 增加类型
	@Override
	public void addType(GoodsType type) {
		GoodsType t = td.selectByName(type.getTypeName());
		if (t == null) {
			td.addType(type);
		} else {
			throw new RuntimeException("商品类型已存在!");
		}
	}

	// 删除类型
	@Override
	public void delType(int typeId) {
		td.delType(typeId);
	}

	// 修改类型
	@Override
	public void updateType(GoodsType type) {
		GoodsType tp = td.selectByName(type.getTypeName());
		if (tp != null && type.getTypeId() != tp.getTypeId()) {
			throw new RuntimeException("商品类型已存在!");
		} else if (tp != null && tp.getTypeName().equals(type.getTypeName())) {
			throw new RuntimeException("商品类型未修改!");
		} else {
			td.updateType(type);
		}
	}
}
