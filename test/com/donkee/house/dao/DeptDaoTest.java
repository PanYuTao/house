package com.donkee.house.dao;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.donkee.house.dao.impl.DeptDaoImpl;
import com.donkee.house.entity.Dept;

public class DeptDaoTest {

	@Test
	public void testListAll() {
		DeptDao deptDao = new DeptDaoImpl();
		List<Dept> list = deptDao.listAll();
		System.out.println(list);
	}
}
