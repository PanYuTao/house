package com.donkee.house.dao;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.donkee.house.util.JdbcUtil;

public class JdbcUtilTest2 {

	@Test
	public void testStatic() {
		Connection conn = JdbcUtil.getConnection();
		System.out.println(conn);
	}
}
