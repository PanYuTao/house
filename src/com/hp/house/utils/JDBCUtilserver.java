package com.hp.house.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtilserver {

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url= "jdbc:sqlserver://localhost:1433;DatabaseName=house_system";
	private static String user= "root";
	private static String pwd= "root";
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
	public static void main(String[] args) {
		System.out.println(getconnection());
	}
}
