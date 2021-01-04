package com.hp.house.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hp.house.entity.Dept;
import com.hp.house.entity.Js;


public class JDBCUtil {
	private static String driver=null;
	private static String url=null;
	private static String user=null;
	private static String pwd=null;
	
	static {
		try {
			InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties properties = new Properties();
			properties.load(is);
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			pwd = properties.getProperty("pwd");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getconnection() {
		Connection conn = null;
		try {
			//加转驱动类
			Class.forName(driver);//反射Class.forName(drivec2);
			//创建连接
			conn = DriverManager.getConnection(url,user,pwd);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//关闭连接
	public static void closeConnection(Connection conn){
		try {
			if(conn != null) {
			conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行增删改SQL
	 * @param args
	 */
	public int executeUpdate(String sql, Object...params) {
		int count = 0;
		Connection conn = null;
		try {
			conn = JDBCUtil.getconnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if (params !=null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(conn);
		}
		return count;
	}
	
	/**
	 * 执行查询SQL
	 * @param args
	 */
	public <T> List<T> executeQuery(String sql,Class<T> c,Object...params) {
		List<T> list = new ArrayList<T>();
		Connection conn = null;
		
		try {
			conn = JDBCUtil.getconnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			ResultSet rs = ps.executeQuery();
			//ResultSetMetaData保存的是结果集（ResultSet）中的列的信息
			ResultSetMetaData rsmd = rs.getMetaData();
			//getColumnCount:返回结果集中的总列数
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				Object entity = c.newInstance();
				for (int i = 0; i < columnCount; i++) {
					String columnName = rsmd.getColumnName(i+1);
					Object obj = rs.getObject(columnName);
					Field field = c.getDeclaredField(columnName.toLowerCase());
					field.setAccessible(true);
					field.set(entity, obj);
				}	
				list.add((T) entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(conn);
		}
		
		return list;
	}
	
	/**
	 * 分页查询
	 * @param args
	 */
	public <T> List<T> findByPage(String sql,int pageNum,int pageSize,Class<T> c,Object...params) {
		int page = (pageNum - 1)*pageSize;
		sql += " limit " + page + "," + pageSize;
		return new JDBCUtil().executeQuery(sql, c,params);
	}
	
	/**
	 * 总记录数	
	 * @param args
	 */
	public int total(String sql,Object...params) {
		int total = 0;
		Connection conn = null;
		
		try {
			conn = JDBCUtil.getconnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(conn);
		}
		
		return total;
	}
	
	public static void main(String[] args) {
		String sql = "select * from myjs";
		List<Js> list = new JDBCUtil().executeQuery(sql, Js.class);
		for (Js dept : list) {
			System.out.println(dept);
		}
	} 
}
