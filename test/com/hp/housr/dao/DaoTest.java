package com.hp.housr.dao;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.hp.house.dao.DeptDao;
import com.hp.house.dao.JsDao;
import com.hp.house.dao.SortDao;
import com.hp.house.dao.impl.DeptDaoImpl;
import com.hp.house.dao.impl.JsDaoImpl;
import com.hp.house.dao.impl.SortDaoImpl;
import com.hp.house.entity.Dept;
import com.hp.house.entity.Js;
import com.hp.house.entity.Sort;

public class DaoTest {

	@Test
	public void deptDome1() {
		DeptDao deptDao = new DeptDaoImpl();
		List<Dept> list = deptDao.ListAll();
		System.out.println(list);
	}
	@Test
	public void jsDome1() {
		JsDao jsDao = new JsDaoImpl();
		List<Js> listAll = jsDao.ListAll();
		System.out.println(listAll);
	}
	@Test
	public void sortDome1() {
		SortDao sortDao = new SortDaoImpl();
		List<Sort> listAll = sortDao.ListAll();
		System.out.println(listAll);
	}
}
