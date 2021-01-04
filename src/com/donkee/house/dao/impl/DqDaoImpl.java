package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.DqDao;
import com.donkee.house.entity.Dq;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;


public class DqDaoImpl implements DqDao {

	@Override
	public List<Dq> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Dq t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Dq t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Dq findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<Dq> findByPage(int pageNum, int pageSize) {
		String sql = "SELECT a.*,c.haddress,c.hfh,d.cname,d.ctel,e.erealname FROM mysf a"
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid"
				+" INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT count(1) FROM mysf a INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid" 
				+ " INNER JOIN myemp e ON a.eid=e.eid";
		List<Dq> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Dq.class);
		PageInfo<Dq> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

	@Override
	public PageInfo<Dq> findByPage1(int pageNum, int pageSize) {
		String sql = "SELECT a.*,d.erealname FROM mysf a INNER JOIN myemp d ON a.eid=d.eid";			
		String sql2 = "SELECT count(1) FROM mysf a INNER JOIN myemp d ON a.eid=d.eid";
		List<Dq> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Dq.class);
		PageInfo<Dq> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

}
