package com.donkee.house.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class JdbcUtil {
	//静态成员变量生存其是整个应用
	private static String user = null;
	private static String pwd = null;
	private static String url = null;
	private static String driver = null;
	
	//静态块,其中的代码只会被执行一次
	static {
		//JdbcUtil.class:返回Class类的一个对象
		//getClassLoader:返回ClassLoader对象
		//getResourceAsStream:到类加载器位置(WEB-INF\classes文件)找db.properties;
		try {
			InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties pro = new Properties();
			//如果文件没有找到,会抛出Null指针异常
			pro.load(is);//将db.properties的文件内容封装到Properties对象
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			pwd = pro.getProperty("password");
			//加载驱动类到内存
			Class.forName(driver);//反射
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//创建链接
	public static Connection getConnection() {
		Connection conn = null;
		try {		
			//创建连接，根据url到内存中找与之匹配的驱动
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭连接
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeAll(Connection conn,Statement stmt,ResultSet rs) {
		try {
			if (rs!=null)
				rs.close();
			if (stmt!=null)
				stmt.close();
			if (conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeAll(Connection conn,Statement stmt) {
		closeAll(conn, stmt, null);
	}
	
	//执行增删改SQL
	//params值要与sql中的?一一对应
	public static int executeUpdate(String sql,Object...params) {
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			setParamsValues(ps, params);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, ps);
		}
		return count;
	}
	
	//执行查询SQL
	public static <T> List<T> executeQuery(String sql,Class<T> clazz,Object...params) {
		List<T> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			setParamsValues(ps, params);
			ResultSet rs = ps.executeQuery();
			//ResultSetMetaData保存的是结果集 (ResultSet)中的列的信息
			ResultSetMetaData rsmd = rs.getMetaData();
			//getColumnCount:返回结果集中的总列数
			int clnCount = rsmd.getColumnCount();
			//while循环遍历结果集中所有的记录 (行)
			while (rs.next()) {
				T entity = null;
				try {
					entity = clazz.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
				for (int i = 0; i < clnCount; i++) {
					String clnName = rsmd.getColumnName(i+1);//动态获取了结果集中的所有"列名"
					Object clnValue = rs.getObject(clnName);//得到对应的值
					try {
						//要求:实体类中的属性名全为小写字母
						Field field = clazz.getDeclaredField(clnName.toLowerCase());
						field.setAccessible(true);
						field.set(entity, clnValue);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				list.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return list;
	}
	public static <T> List<T> findByPage(String sql,int pageNum,int pageSize,Class<T> clazz,Object...params) {
		int from = (pageNum-1)*pageSize;
		sql += " limit "+from+","+pageSize;
		return executeQuery(sql, clazz, params);
	}
	
    //返回总记录数
	public static int total(String sql,Object...params) {
		int total = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			setParamsValues(ps, params);
			ResultSet rs = ps.executeQuery();	
			rs.next();
			total = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return total;
	}
	
	//给sql中的?传值
	private static void setParamsValues(PreparedStatement ps,Object...params) {
		try {
			if (params!=null && params.length>0) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
