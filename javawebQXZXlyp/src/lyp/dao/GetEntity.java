package lyp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetEntity<G> {
	G getEntity(ResultSet rs)throws SQLException;
}
