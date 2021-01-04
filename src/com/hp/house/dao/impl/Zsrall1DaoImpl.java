package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.Zsrall1Dao;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Zsrall1;
import com.hp.house.utils.JDBCUtil;

public class Zsrall1DaoImpl implements Zsrall1Dao {

	@Override
	public PageInfo<Zsrall1> findByPage(int current, int pageSize) {
		String sql = "select a.*,b.sname,c.aname from myhouse a inner join mysort b on a.sid=b.sid"
				+" inner join myarea c on a.aid=c.aid";
		String sql2 = "select count(1) from myhouse a inner join mysort b on a.sid=b.sid" + 
				" inner join myarea c on a.aid=c.aid";
		List<Zsrall1> list = new JDBCUtil().findByPage(sql, current, pageSize, Zsrall1.class);
		int total = new JDBCUtil().total(sql2);
		PageInfo<Zsrall1> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(current);
		pageInfo.setList(list);
		pageInfo.setPagesize(pageSize);//һ��Ҫ�����ܼ�¼��֮ǰ
		pageInfo.setTotal(total);
		
		return pageInfo;
	}

	@Override
	public List<Zsrall1> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zsrall1 selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Zsrall1 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Zsrall1 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
