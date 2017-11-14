package lyp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import lyp.dao.BaseDao;
import lyp.dao.GetEntity;
import lyp.entity.PageModel;

public abstract class BaseDaoImpl implements BaseDao {
	static ComboPooledDataSource cpds = new ComboPooledDataSource();
	
	/**
	 * 获得数据库连接
	 */
	@Override
	public Connection getConn() {
		Connection conn = null;
		try {
			conn = cpds.getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return conn;
	}

	/**
	 * 关闭数据库相关
	 */
	@Override
	public void closeAll(ResultSet rs, Statement st, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * 通用增删改方法
	 */
	@Override
	public void aduSQL(String sql, Object... params) {
		Connection conn = getConn();
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pst.setObject(i + 1, params[i]);
			}
			int rel = pst.executeUpdate();
			if (rel == 0) {
				throw new RuntimeException("操作失败,受影响的项为0!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeAll(null, pst, conn);
		}
	}

	/**
	 * 通用查询所有
	 */
	@Override
	public <G> List<G> select(GetEntity<G> getEntity, String sql) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<G> list = new ArrayList<G>();
		try {
			conn = getConn();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				G obj = getEntity.getEntity(rs);
				list.add(obj);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeAll(rs, pst, conn);
		}
		return list;
	}

	/**
	 * 通用查询获得实例的集合
	 */
	@Override
	public <G> List<G> selectForEntity(GetEntity<G> getEntity, String sql, Object... params) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<G> list = new ArrayList<G>();
		try {
			conn = getConn();
			pst = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				G obj = getEntity.getEntity(rs);
				list.add(obj);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeAll(rs, pst, conn);
		}
		return list;
	}

	/**
	 * 普通分页和关键字分页通用查找方法
	 */
	@Override
	public <G> PageModel<G> selectByPage(GetEntity<G> getEntity, PageModel<G> pm, String sql1, String sql2, String keywords) {
		List<G> list = new ArrayList<G>();
		Connection conn = this.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql1);
			if (keywords != null) {
				pst.setString(1, "%" + keywords + "%");
				pst.setInt(2, (pm.getPageNo() - 1) * pm.getPageSize());
				pst.setInt(3, pm.getPageSize());
			} else {
				pst.setInt(1, (pm.getPageNo() - 1) * pm.getPageSize());
				pst.setInt(2, pm.getPageSize());
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				G obj = getEntity.getEntity(rs);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		pm.setData(list);
		this.closeAll(rs, pst, null);
		try {
			pst = conn.prepareStatement(sql2);
			if (keywords != null) {
				pst.setString(1, "%" + keywords + "%");
			}
			rs = pst.executeQuery();
			if (rs.next()) {
				pm.setTotalRecord(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			this.closeAll(rs, pst, conn);
		}
		return pm;
	}

	@Override
	public <G> PageModel<G> selectByPageP(GetEntity<G> getEntity, PageModel<G> pm, String sql1, String sql2,Object... params) {
		List<G> list = new ArrayList<G>();
		Connection conn = this.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql1);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {

					pst.setObject(i + 1, "%" + params[i] + "%");
				}
				pst.setInt(params.length + 1, (pm.getPageNo() - 1) * pm.getPageSize());
				pst.setInt(params.length + 2, pm.getPageSize());
			} else {
				pst.setInt(1, (pm.getPageNo() - 1) * pm.getPageSize());
				pst.setInt(2, pm.getPageSize());
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				G obj = getEntity.getEntity(rs);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		pm.setData(list);
		this.closeAll(rs, pst, null);
		try {
			pst = conn.prepareStatement(sql2);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, "%" + params[i] + "%");
				}
			}
			rs = pst.executeQuery();
			if (rs.next()) {
				pm.setTotalRecord(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			this.closeAll(rs, pst, conn);
		}
		return pm;
	}

	/**
	 * 前台数据查询
	 */
	@Override
	public <G> List<G> selectForList(Connection conn, GetEntity<G> getEntity, String sql, Object... params) {
		List<G> list = new ArrayList<G>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				G g = getEntity.getEntity(rs);
				list.add(g);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			this.closeAll(rs, pst, null);
		}
		return list;
	}

	@Override
	public int executeQuerySQLForInt(String sql) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int rel = 0;
		try {
			conn = this.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				rel = rel + rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			this.closeAll(rs, st, conn);
		}
		return rel;
	}

	@Override
	public void executeUpdateSQL(Connection conn, String sql, Object... params) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pst.setObject(i + 1, params[i]);
			}
			int rel = pst.executeUpdate();
			if (rel == 0) {
				throw new RuntimeException("受影响的条数为零。");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);// 异常要往上层抛。
		} finally {
			this.closeAll(null, pst, null);
		}
	}

	@Override
	public <G> PageModel<G> selectByPageInt(GetEntity<G> getEntity, PageModel<G> pm, String sql1, String sql2, int num) {
		List<G> list = new ArrayList<G>();
		Connection conn = this.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql1);
			pst.setInt(1, num);
			pst.setInt(2, (pm.getPageNo() - 1) * pm.getPageSize());
			pst.setInt(3, pm.getPageSize());
			rs = pst.executeQuery();
			while (rs.next()) {
				G obj = getEntity.getEntity(rs);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		pm.setData(list);
		this.closeAll(rs, pst, null);
		try {
			pst = conn.prepareStatement(sql2);
			pst.setInt(1, num);
			rs = pst.executeQuery();
			if (rs.next()) {
				pm.setTotalRecord(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			this.closeAll(rs, pst, conn);
		}
		return pm;
	}

	/*
	 * //普通分页查找
	 * 
	 * @Override public PageModel<G> selectByPage(PageModel<G> pm, String sql1,
	 * String sql2) { List<G> list = new ArrayList<G>(); Connection conn =
	 * this.getConn(); PreparedStatement pst = null; ResultSet rs = null; try {
	 * pst = conn.prepareStatement(sql1); pst.setInt(1, (pm.getPageNo() - 1) *
	 * pm.getPageSize()); pst.setInt(2, pm.getPageSize()); rs =
	 * pst.executeQuery(); while (rs.next()) { G obj = getEntity(rs);
	 * list.add(obj); } } catch (SQLException e) { throw new
	 * RuntimeException(e); } pm.setData(list); this.closeAll(rs, pst, null);
	 * try { pst = conn.prepareStatement(sql2); rs = pst.executeQuery(); if
	 * (rs.next()) { pm.setTotalRecord(rs.getInt(1)); } } catch (SQLException e)
	 * { throw new RuntimeException(e); } finally { this.closeAll(rs, pst,
	 * conn); } return pm; }
	 */
}
