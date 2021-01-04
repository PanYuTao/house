package com.hp.housr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.hp.house.utils.JDBCUtil;

public class StatementTest {

	@Test
	public void testsStatement() {
		
		Connection conn = null;
		try {
			conn = JDBCUtil.getconnection();
			Statement stmt= conn.createStatement();
			String ename="admin";
			String psw="admin2' or 1=1 or'"; //SQL注入
			String sql = "select * from myemp where ename= '"+ename+"' and epsw= '"+psw +"'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				System.out.println("登录成功!");
			}
			else {
				System.out.println("登录失败!");
			}
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test
	public void testPreparstatement() {
		Connection conn = null;
		try {
			conn = JDBCUtil.getconnection();
			String sql = "select * from myemp where ename=? and epsw=?";
			System.out.println(sql);
			 PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("登录成功!");
			}
			else {
				System.out.println("登录失败!");
			}
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}	
