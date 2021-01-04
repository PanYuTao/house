package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.Zsrall2Dao;
import com.donkee.house.entity.Zsrall2;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;


public class Zsrall2DaoImpl implements Zsrall2Dao {

	@Override
	public List<Zsrall2> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Zsrall2 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Zsrall2 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Zsrall2 findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<Zsrall2> findByPage(int pageNum, int pageSize) {
		String sql = "select a.*,b.haddress,b.hfh,c.cname,c.ctel,d.erealname from mydj a inner join myhouse b on a.hid=b.hid"
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		String sql2 = "select count(1) from mydj a inner join myhouse b on a.hid=b.hid" 
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		List<Zsrall2> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Zsrall2.class);
		PageInfo<Zsrall2> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

	@Override
	public PageInfo<Zsrall2> findByPage1(int pageNum, int pageSize) {
		String sql = "SELECT a.*,d.erealname FROM mysf a INNER JOIN myemp d ON a.eid=d.eid";			
		String sql2 = "SELECT count(1) FROM mysf a INNER JOIN myemp d ON a.eid=d.eid";
		List<Zsrall2> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Zsrall2.class);
		PageInfo<Zsrall2> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

	

}
