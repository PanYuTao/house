package com.donkee.house.dao;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.donkee.house.dao.impl.SortDaoImpl;
import com.donkee.house.entity.Sort;

public class SortDaoTest {
	@Test
	public void testSortDao() {
		SortDao sortDao = new SortDaoImpl();
		List<Sort> list = sortDao.listAll();
		System.out.println(list);
	}
}
