package com.library.dao.implement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/library";
	private static final String USR = "root";
	private static final String PWD = "admin";

	/**
	 * 建立连接
	 * 
	 * @return 返回connection的对象
	 */
	public Connection getConn() {
		Connection conn = null;
		try {
			// 加载驱动
			System.out.println("加载驱动");
			Class.forName(DRIVER);
			System.out.println("驱动加载成功");
			// 建立连接
			conn = DriverManager.getConnection(URL, USR, PWD);
			System.out.println("数据库连接成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn; // 返回Connection的值

	}

	// 关闭所有资源
	public void closeAll(ResultSet rs, PreparedStatement psmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// 关闭资源出错
			e.printStackTrace();
		}

	}

	/**
	 * 此方法可以完成增删改所有的操作,
	 * 
	 * @param sql
	 * @param params
	 * @return 返回值为boolean类型的数据
	 */
	public boolean operateUpdate(String sql, List<Object> params) {
		int res = 0;// 影响的行数
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConn();// 建立数据库链接
			pstmt = conn.prepareStatement(sql); // 装载sql语句
			if (params != null) {
				// 如果有？占位符，在执行之前将？占位符替换
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			res = pstmt.executeUpdate();// 返回受影响行数

		} catch (SQLException e) {
			// 数据库建立 pstmt语句错误
			e.printStackTrace();
		} finally {
			// 关闭所有资源
			closeAll(rs, pstmt, conn);
		}

		return res > 0 ? true : false;

	}

	/**
	 * 使用泛型方法和反射机制进行封装
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 *            查询出来的结果封装为对象
	 * @return
	 * @throws Exception
	 * @throws InstantiationException
	 */
	public <T> List<T> operateQuery(String sql, List<Object> params,
			Class<T> cls) throws InstantiationException, Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> data = new ArrayList<T>();// 将返回的结果保存
		try {
			conn = getConn();// 建立数据库链接
			pstmt = conn.prepareStatement(sql); // 装载sql语句
			if (params != null) {
				// 如果有？占位符，在执行之前将？占位符替换
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			rs = pstmt.executeQuery();// 返回受影响行数
			// 将查询出来的额结果封装成对象
			ResultSetMetaData rsmd = rs.getMetaData();// 得到记录集源数据对象
			// 通过此对象可以得到表的结构，可以得到列名、列的个数、数据类型
			while (rs.next()) {
				T m = cls.newInstance();// 产生泛型对象
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i + 1);// 获得列名
					Object value = rs.getObject(columnName);// 获得列所对应的值
					Field field = cls.getDeclaredField(columnName);// 获得属性
					field.setAccessible(true);// 给私有属性设置可访问权，然后才能赋值
					field.set(m, value);// 给对象的私有属性赋值

				}
				data.add(m);
			}

		} catch (SQLException e) {
			// 数据库建立 pstmt语句错误
			e.printStackTrace();
		} finally {
			// 关闭所有资源
			closeAll(rs, pstmt, conn);
		}
		return data;
	}
}
