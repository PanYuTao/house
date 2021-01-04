package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.HtDao;
import com.hp.house.entity.Ht;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class HtDaoImpl implements HtDao {

	@Override
	public PageInfo<Ht> findByPage(int current, int pageSize) {
		String sql = "select * from myht";
		String sql2 = "select count(1) from myht";
		List<Ht> list = new JDBCUtil().findByPage(sql, current, pageSize, Ht.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Ht> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(current);
		pageInfo.setList(list);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

	@Override
	public List<Ht> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ht selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Ht t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Ht t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
