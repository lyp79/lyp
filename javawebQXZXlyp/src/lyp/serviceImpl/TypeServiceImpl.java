package lyp.serviceImpl;


import java.util.List;

import lyp.dao.TypeDao;
import lyp.daoImpl.TypeDaoImpl;
import lyp.entity.GoodsType;
import lyp.entity.PageModel;
import lyp.service.TypeService;

public class TypeServiceImpl implements TypeService {
	TypeDao td = new TypeDaoImpl();

	// id����
	@Override
	public GoodsType selectById(int typeId) {
		GoodsType type = td.selectById(typeId);
		if (type != null) {
			return type;
		} else {
			return null;
		}
	}
	
	//��ѯ��������
	@Override
	public List<GoodsType> selectAll() {
		List<GoodsType> list= td.selectAll();
		return list;
	}
	
	//��ȡ��ҳ
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
	
	// ��������
	@Override
	public void addType(GoodsType type) {
		GoodsType t = td.selectByName(type.getTypeName());
		if (t == null) {
			td.addType(type);
		} else {
			throw new RuntimeException("��Ʒ�����Ѵ���!");
		}
	}

	// ɾ������
	@Override
	public void delType(int typeId) {
		td.delType(typeId);
	}

	// �޸�����
	@Override
	public void updateType(GoodsType type) {
		GoodsType tp = td.selectByName(type.getTypeName());
		if (tp != null && type.getTypeId() != tp.getTypeId()) {
			throw new RuntimeException("��Ʒ�����Ѵ���!");
		} else if (tp != null && tp.getTypeName().equals(type.getTypeName())) {
			throw new RuntimeException("��Ʒ����δ�޸�!");
		} else {
			td.updateType(type);
		}
	}
}
