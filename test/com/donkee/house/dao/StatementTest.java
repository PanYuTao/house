package com.donkee.house.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import com.donkee.house.util.JdbcUtil;

public class StatementTest {

	@Test
	public void testStatement() {
		try {
			Connection conn = JdbcUtil.getConnection();
			Statement stmt = conn.createStatement();
			String ename="admin";
			String psw="admin2' or 1=1 or '";//SQL注入
			String sql = "select * from myemp where ename='"+ename+"' and epsw='"+psw+"'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println("登陆成功");//显示登录成功
			} else {
				System.out.println("登陆失败");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPrepareStatement() {
		try {
			Connection conn = JdbcUtil.getConnection();
			String ename="admin";
			String psw="admin2' or 1=1 or '";//SQL注入
			String sql = "select * from myemp where ename=? and epsw=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			//给参数传值
			ps.setString(1, ename);
			ps.setString(2, psw);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("登陆成功");
			} else {
				System.out.println("登陆失败");//显示登录失败
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
