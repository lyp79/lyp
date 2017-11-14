package lyp.serviceImpl;

import lyp.dao.BulletinDao;
import lyp.daoImpl.BulletinDaoImpl;
import lyp.entity.Bulletin;
import lyp.entity.PageModel;
import lyp.service.BulletinService;

public class BulletinServiceImpl implements BulletinService {
	BulletinDao bd=new BulletinDaoImpl();

	//ͨ��id��ѯ
	@Override
	public Bulletin selectById(int id) {
		Bulletin bulletin = bd.selectById(id);
		return bulletin;
	}
	

	//��ȡ��ҳ
	@Override
	public PageModel<Bulletin> getBulletinPage(PageModel<Bulletin> pm) {
		pm = bd.selectByPage(pm);
		return pm;
	}


	//�ؼ��ֲ��һ�ȡ��ҳ
	@Override
	public PageModel<Bulletin> getBulletinPage(PageModel<Bulletin> pm, String keywords) {
		pm = bd.selectByPage(pm,keywords);
		return pm;
	}


	//���ӹ���
	@Override
	public void addBulletin(Bulletin bulletin) {
		if (bd.selectByTitle(bulletin.getTitle()) == null) {
			bd.addBulletin(bulletin);
		} else {
			throw new RuntimeException("��ӵĹ�������Ѵ���!");
		}
		
	}

	//ɾ������
	@Override
	public void delBulletin(int id) {
		bd.delBulletin(id);
		
	}

	//ɾ���������
	@Override
	public void delBulletins(String cleckid) {
		bd.delBulletins(cleckid);
		
	}
	
	//�޸Ĺ���
	@Override
	public void updateBulletin(Bulletin bulletin) {
		Bulletin bull = bd.selectByTitle(bulletin.getTitle());
		if (bull != null && bull.getId() != bulletin.getId()) {
			throw new RuntimeException("��ӵĹ�������Ѵ���!");
		}else{
		bd.updateBulletin(bulletin);
		}
	}

}
