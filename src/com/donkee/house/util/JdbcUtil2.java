package com.donkee.house.util;

import java.sql.*;

public class JdbcUtil2 {
	private static String user = "sa";
	private static String pwd = "123";
	private static String url = "jdbc:sqlserver://172.16.9.199:1433;DatabaseName=Student";
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//��������
	public static Connection getConnection() {
		Connection conn = null;
		try {
			//���������ൽ�ڴ�
			Class.forName(driver);//����
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
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	
}
