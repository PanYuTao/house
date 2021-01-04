package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.ZcDao;
import com.donkee.house.entity.Zc;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;


public class ZcDaoImpl implements ZcDao {

	@Override
	public List<Zc> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Zc zc) {
		String sql = "insert into myzc values(null,?,?,?)";
		return JdbcUtil.executeUpdate(sql, zc.getCtitle(),zc.getCtime(),zc.getCremark());
	}

	@Override
	public int update(Zc zc) {
		String sql = "update myzc set ctitle=?,cremark=? where cid =?";
		return JdbcUtil.executeUpdate(sql, zc.getCtitle(),zc.getCremark(),zc.getCid());
	}

	@Override
	public Zc findById(int cid) {
		String sql = "select * from myzc where cid ="+cid;
		List<Zc> list = JdbcUtil.executeQuery(sql, Zc.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
		
	}

	@Override
	public int delete(int cid) {
		String sql = "delete from myzc where cid =?";
		return JdbcUtil.executeUpdate(sql, cid);
	}

	@Override
	public PageInfo<Zc> findByPage(int pageNum, int pageSize) {
		String sql = "select * from myzc";
		String sql2 = "select count(1) from myzc";
		List<Zc> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Zc.class);
		PageInfo<Zc> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

}
