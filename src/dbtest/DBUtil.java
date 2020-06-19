package dbtest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Administrator 数据库辅助工具类
 */
public class DBUtil {


	/**
	 * JDBC
	 */
	private final String DATA = "datafactory_db";
	private final String HOST = "localhost";
	private static DBUtil instance = null;
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://" + HOST + "/" + DATA
			+ "?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
	private final String USER = "root";
	private final String PWD = "shit1027";
	private CallableStatement callableStatement = null;
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	public DBUtil() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("连接数据库失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 建立数据库连接
	 * 
	 * @return 数据库连接
	 */
	public Connection getConnection() {
		try {
			// 加载数据库驱动程序
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println("加载驱动错误");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			// 获取连接
			con = DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}

	/**
	 * 关闭增删改
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet getSet(String sql, int type, Object... params) {
		int j = 0;
		if (params.length == 1 && params.length % 2 == 0) {
			System.out.println("type=1");
			j = 1;
		} else if (params.length > 1 && params.length % 2 == 0) {
			System.out.println("type=2");
			j = params.length / 2;
		}

		try {
			con = getConnection();
			pst = con.prepareStatement(sql);
			if (params != null && type != 1) {
				for (int i = 0; i < params.length / 2; i++) {
					pst.setObject(i + 1, params[i + j]);
				}
			}
			System.out.println("Executing SQL:　" + pst.toString());
			rs = pst.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("参数错误");
		}
		return rs;

	}

	/**
	 * 执行sql增删改
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params) {
		int returnValue = 0;
		try {
			con = getConnection();
			pst = con.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			returnValue = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return returnValue;

	}

	/**
	 * 关闭所有资源
	 */
	public void close() {
		// 关闭结果集对象
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		// 关闭PreparedStatement对象
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		// 关闭CallableStatement 对象
		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		// 关闭Connection 对象
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}