package com.donkee.house.dao;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.donkee.house.entity.Dept;
import com.donkee.house.entity.Js;
import com.donkee.house.entity.Sort;
import com.donkee.house.util.JdbcUtil;

public class JdbcUtilTest3 {
	@Test
	public void test1() {
		String sql = "select * from mydept";
		List<Dept> list = JdbcUtil.executeQuery(sql, Dept.class);	
		for (Dept dept : list) {
			System.out.println(dept);
		}
		
		String sql2 = "select * from myjs";
		List<Js> list2 = JdbcUtil.executeQuery(sql2, Js.class);
		for (Js js : list2) {
			System.out.println(js);
		}
		
		String sql3 = "select * from mysort";
		List<Sort> list3 = JdbcUtil.executeQuery(sql3, Sort.class);
		for (Sort sort : list3) {
			System.out.println(sort);
		}
	}
	
	
}
