package com.donkee.house.util;

import java.sql.*;

public class JdbcUtil2 {
	private static String user = "sa";
	private static String pwd = "123";
	private static String url = "jdbc:sqlserver://172.16.9.199:1433;DatabaseName=Student";
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//创建链接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			//加载驱动类到内存
			Class.forName(driver);//反射
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
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	
}
