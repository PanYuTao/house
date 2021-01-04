package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.HtDao;
import com.donkee.house.entity.Ht;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class HtDaoImpl implements HtDao {

	@Override
	public List<Ht> listAll() {
		String sql = "select * from myht";
		List<Ht> list = JdbcUtil.executeQuery(sql, Ht.class);
		return list;
	}

	@Override
	public int save(Ht ht) {
		String sql = "insert into myht(htname,htremark) values(?,?)";
		return JdbcUtil.executeUpdate(sql, ht.getHtname(),ht.getHtremark());
	}

	@Override
	public int delete(int id) {
		String sql = "delete from myht where htid=?";
		return JdbcUtil.executeUpdate(sql, id);
	}

	@Override
	public Ht findById(int id) {
		String sql = "select * from myht where htid="+id;
		List<Ht> list = JdbcUtil.executeQuery(sql, Ht.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}	
	}

	@Override
	public int update(Ht ht) {
		String sql = "update myht set htname=?,htremark=? where htid=?";
		return JdbcUtil.executeUpdate(sql, ht.getHtname(),ht.getHtremark(),ht.getHtid());
	}

	@Override
	public PageInfo<Ht> findByPage(int pageNum, int pageSize) {
		String sql = "select * from myht";
		String sql2 = "select count(1) from myht";
		List<Ht> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Ht.class);
		PageInfo<Ht> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);//一定要放在封装总记录数之前
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

}
