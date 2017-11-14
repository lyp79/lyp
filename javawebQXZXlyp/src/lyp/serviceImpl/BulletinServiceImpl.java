package lyp.serviceImpl;

import lyp.dao.BulletinDao;
import lyp.daoImpl.BulletinDaoImpl;
import lyp.entity.Bulletin;
import lyp.entity.PageModel;
import lyp.service.BulletinService;

public class BulletinServiceImpl implements BulletinService {
	BulletinDao bd=new BulletinDaoImpl();

	//通过id查询
	@Override
	public Bulletin selectById(int id) {
		Bulletin bulletin = bd.selectById(id);
		return bulletin;
	}
	

	//获取分页
	@Override
	public PageModel<Bulletin> getBulletinPage(PageModel<Bulletin> pm) {
		pm = bd.selectByPage(pm);
		return pm;
	}


	//关键字查找获取分页
	@Override
	public PageModel<Bulletin> getBulletinPage(PageModel<Bulletin> pm, String keywords) {
		pm = bd.selectByPage(pm,keywords);
		return pm;
	}


	//增加公告
	@Override
	public void addBulletin(Bulletin bulletin) {
		if (bd.selectByTitle(bulletin.getTitle()) == null) {
			bd.addBulletin(bulletin);
		} else {
			throw new RuntimeException("添加的公告标题已存在!");
		}
		
	}

	//删除公告
	@Override
	public void delBulletin(int id) {
		bd.delBulletin(id);
		
	}

	//删除多个公告
	@Override
	public void delBulletins(String cleckid) {
		bd.delBulletins(cleckid);
		
	}
	
	//修改公告
	@Override
	public void updateBulletin(Bulletin bulletin) {
		Bulletin bull = bd.selectByTitle(bulletin.getTitle());
		if (bull != null && bull.getId() != bulletin.getId()) {
			throw new RuntimeException("添加的公告标题已存在!");
		}else{
		bd.updateBulletin(bulletin);
		}
	}

}
