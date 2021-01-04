package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.ZcDao;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Zc;
import com.hp.house.utils.JDBCUtil;

public class ZcDaoImpl implements ZcDao {

	@Override
	public PageInfo<Zc> findByPage(int current, int pageSize) {
		String sql = "select * from myzc";
		String sql2 = "select count(1) from myzc";
		List<Zc> list = new JDBCUtil().findByPage(sql, current, pageSize, Zc.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Zc> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(current);
		pageInfo.setList(list);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

	@Override
	public List<Zc> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zc selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Zc t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Zc t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
