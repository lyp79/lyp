package lyp.dao;

import lyp.entity.Bulletin;
import lyp.entity.PageModel;

public interface BulletinDao {

	void addBulletin(Bulletin bulletin);
	
	void delBulletin(int id);
	
	void updateBulletin(Bulletin bulletin);
	
	void delBulletins(String cleckid);
	
	Bulletin selectByTitle(String title);
	
	Bulletin selectById(int id);
	
	PageModel<Bulletin> selectByPage(PageModel<Bulletin> pm);

	PageModel<Bulletin> selectByPage(PageModel<Bulletin> pm, String keywords);
	
}
