package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.ShouruDao;
import com.donkee.house.entity.Shouru;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;


public class ShouruDaoImpl implements ShouruDao {

	@Override
	public List<Shouru> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Shouru shouru) {
		String sql = "insert into myshouru values(null,?,?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, shouru.getEid(),shouru.getSmoney(),shouru.getStm(),shouru.getStime(),shouru.getSremark());
	}

	@Override
	public int update(Shouru shouru) {
		String sql = "update myshouru set eid=?,smoney=?,stm=?,sremark=? where sid=?";
		return JdbcUtil.executeUpdate(sql, shouru.getEid(),shouru.getSmoney(),shouru.getStm(),shouru.getSremark(),shouru.getSid());
	}

	@Override
	public Shouru findById(int sid) {
		String sql = "select * from myshouru where sid="+sid;
		List<Shouru> list = JdbcUtil.executeQuery(sql, Shouru.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int delete(int sid) {
		String sql = "delete from myshouru where sid=?";
		return JdbcUtil.executeUpdate(sql, sid);
	}

	@Override
	public PageInfo<Shouru> findByPage(int pageNum, int pageSize) {
		String sql = "SELECT a.*,e.erealname FROM myshouru a"
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM myshouru a " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Shouru> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Shouru.class);
		PageInfo<Shouru> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

}
