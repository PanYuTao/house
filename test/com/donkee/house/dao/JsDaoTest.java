package com.donkee.house.dao;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.donkee.house.dao.impl.JsDaoImpl;
import com.donkee.house.entity.Js;

public class JsDaoTest {
	@Test
	public void testJsDao() {
		JsDao jsDao = new JsDaoImpl();
		List<Js> list = jsDao.listAll();
		System.out.println(list);
	}

}
