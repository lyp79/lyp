package lyp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import lyp.entity.PageModel;

public interface BaseDao {

	Connection getConn();

	void closeAll(ResultSet rs, Statement st, Connection conn);
	
	void aduSQL(String sql, Object... params);

	//List<G> selectForEntity(String sql1,Object...params);
	
	//PageModel<G> selectByPage(PageModel<G> pm, String sql1, String sql2); //合并到关键字查询方法中
	
	//普通分页和关键在分页通用查找
	//<G> PageModel<G> selectByPage(PageModel<G> pm, String sql1, String sql2, String keywords);

	int executeQuerySQLForInt(String sql);
	
	<G> List<G> select(GetEntity<G> getEntity,String sql);

	<G> List<G> selectForEntity(GetEntity<G> getEntity, String sql, Object... params);

	<G> PageModel<G> selectByPage(GetEntity<G> getEntity, PageModel<G> pm, String sql1, String sql2, String keywords);
	
	<G>PageModel<G> selectByPageInt(GetEntity<G> getEntity, PageModel<G> pm, String sql1,String sql2, int num);
	
	void executeUpdateSQL(Connection conn, String sql, Object... params);
	
	<G> List<G> selectForList(Connection conn, GetEntity<G> getEntity, String sql,Object... params);

	<G> PageModel<G> selectByPageP(GetEntity<G> getEntity, PageModel<G> pm, String sql1, String sql2, Object... params);
	
}
