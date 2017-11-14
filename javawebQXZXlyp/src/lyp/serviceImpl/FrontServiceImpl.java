package lyp.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lyp.dao.FrontDao;
import lyp.daoImpl.FrontDaoImpl;
import lyp.service.FrontService;

public class FrontServiceImpl implements FrontService {
	FrontDao fd = new FrontDaoImpl();

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List> getIndexMap() {
		Map<String, List> indexMap = new HashMap<String, List>();
		indexMap=fd.selectAll();
		return indexMap;
	}
	
}
