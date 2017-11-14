package lyp.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class Ddao {
	/*
	 * 数据源的管理:
	 *  1. 由程序自己管理连接
	 * 
	 */
		
		private static String driver;// 数据库驱动
		private static String url;// 数据库url
		private static String user;// 数据库用户
		private static String pass;// 密码
		private static ComboPooledDataSource cpds = null;
		
		static {
			Properties ps = new Properties();
			cpds=new ComboPooledDataSource();
			try {
				ps.load(Ddao.class.getResourceAsStream("db.properties"));
				driver = ps.getProperty("driver");
				url = ps.getProperty("url");
				user = ps.getProperty("user");
				pass = ps.getProperty("pass");
				cpds.setDriverClass(driver);
				cpds.setJdbcUrl(url);
				cpds.setUser(user);
				cpds.setPassword(pass);
				cpds.setInitialPoolSize(10);
				cpds.setMaxIdleTime(20000);
				cpds.setAcquireIncrement(3);
				cpds.setMaxPoolSize(100);
				cpds.setMinPoolSize(5);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public static Connection getConn() throws SQLException {

			Connection conn = cpds.getConnection();
			return conn;
		}

		public static void close(ResultSet rs, Statement st, Connection conn) {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	

}
