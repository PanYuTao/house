package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.Zsrall1Dao;
import com.donkee.house.entity.Zsrall1;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;


public class Zsrall1DaoImpl implements Zsrall1Dao {

	@Override
	public List<Zsrall1> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Zsrall1 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Zsrall1 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Zsrall1 findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<Zsrall1> findByPage(int pageNum, int pageSize) {
		String sql = "select a.*,b.sname,c.aname from myhouse a inner join mysort b on a.sid=b.sid"
				+" inner join myarea c on a.aid=c.aid";
		String sql2 = "select count(1) from myhouse a inner join mysort b on a.sid=b.sid" + 
				" inner join myarea c on a.aid=c.aid";
		List<Zsrall1> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Zsrall1.class);
		PageInfo<Zsrall1> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));	
		return pageInfo;
	}


}
