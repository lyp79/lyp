package lyp.service;

import lyp.entity.Bulletin;
import lyp.entity.PageModel;

public interface BulletinService {

	void addBulletin(Bulletin bulletin);
	
	void delBulletin(int parseInt);

	void updateBulletin(Bulletin bulletin);
	
	Bulletin selectById(int parseInt);

	PageModel<Bulletin> getBulletinPage(PageModel<Bulletin> pm);

	void delBulletins(String cleckid);

	PageModel<Bulletin> getBulletinPage(PageModel<Bulletin> pm, String keywords);
	
}
