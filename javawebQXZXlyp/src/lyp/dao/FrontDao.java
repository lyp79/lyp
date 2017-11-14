package lyp.dao;

import java.util.List;
import java.util.Map;

public interface FrontDao {

	@SuppressWarnings("rawtypes")
	Map<String, List> selectAll();

}
