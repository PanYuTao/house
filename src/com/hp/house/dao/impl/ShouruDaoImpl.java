package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.ShouruDao;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Shouru;
import com.hp.house.utils.JDBCUtil;

public class ShouruDaoImpl implements ShouruDao {
	

	@Override
	public PageInfo<Shouru> findByPage(int current, int pageSize) {
		String sql = "SELECT a.*,e.erealname FROM myshouru a"
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM myshouru a " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Shouru> list = new JDBCUtil().findByPage(sql, current, pageSize, Shouru.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Shouru> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(current);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

	@Override
	public List<Shouru> ListAll() {
		String sql = "SELECT a.*,e.erealname FROM myshouru a "
				+ "INNER JOIN myemp e ON a.eid=e.eid";
		return new JDBCUtil().executeQuery(sql, Shouru.class);
	}

	@Override
	public Shouru selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Shouru t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Shouru t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
