package com.donkee.house.entity;

import org.junit.jupiter.api.Test;

public class DeptTest {

	@Test
	public void testDept() {
		Dept dept = new Dept();
		//����int��Integer������
		System.out.println(dept.getPid());//0
		System.out.println(dept.getPflag());//null
	}
}
