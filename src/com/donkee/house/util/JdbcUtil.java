package com.donkee.house.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class JdbcUtil {
	//��̬��Ա����������������Ӧ��
	private static String user = null;
	private static String pwd = null;
	private static String url = null;
	private static String driver = null;
	
	//��̬��,���еĴ���ֻ�ᱻִ��һ��
	static {
		//JdbcUtil.class:����Class���һ������
		//getClassLoader:����ClassLoader����
		//getResourceAsStream:���������λ��(WEB-INF\classes�ļ�)��db.properties;
		try {
			InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties pro = new Properties();
			//����ļ�û���ҵ�,���׳�Nullָ���쳣
			pro.load(is);//��db.properties���ļ����ݷ�װ��Properties����
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			pwd = pro.getProperty("password");
			//���������ൽ�ڴ�
			Class.forName(driver);//����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��������
	public static Connection getConnection() {
		Connection conn = null;
		try {		
			//�������ӣ�����url���ڴ�������֮ƥ�������
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//�ر�����
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
	
	//ִ����ɾ��SQL
	//paramsֵҪ��sql�е�?һһ��Ӧ
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
	
	//ִ�в�ѯSQL
	public static <T> List<T> executeQuery(String sql,Class<T> clazz,Object...params) {
		List<T> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			setParamsValues(ps, params);
			ResultSet rs = ps.executeQuery();
			//ResultSetMetaData������ǽ���� (ResultSet)�е��е���Ϣ
			ResultSetMetaData rsmd = rs.getMetaData();
			//getColumnCount:���ؽ�����е�������
			int clnCount = rsmd.getColumnCount();
			//whileѭ����������������еļ�¼ (��)
			while (rs.next()) {
				T entity = null;
				try {
					entity = clazz.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
				for (int i = 0; i < clnCount; i++) {
					String clnName = rsmd.getColumnName(i+1);//��̬��ȡ�˽�����е�����"����"
					Object clnValue = rs.getObject(clnName);//�õ���Ӧ��ֵ
					try {
						//Ҫ��:ʵ�����е�������ȫΪСд��ĸ
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
	
    //�����ܼ�¼��
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
	
	//��sql�е�?��ֵ
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
