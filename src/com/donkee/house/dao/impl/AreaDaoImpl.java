package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.AreaDao;
import com.donkee.house.entity.Area;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class AreaDaoImpl implements AreaDao {

	@Override
	public List<Area> listAll() {
		String sql = "select * from myarea";
		List<Area> list = JdbcUtil.executeQuery(sql, Area.class);
		return list;
	}

	@Override
	public int save(Area area) {
		String sql = "insert into myarea(aname) values(?)";
		return JdbcUtil.executeUpdate(sql, area.getAname());
	}

	@Override
	public int delete(int id) {
		String sql = "delete from myarea where aid=?";
		return JdbcUtil.executeUpdate(sql, id);
	}

	@Override
	public Area findById(int id) {
		String sql = "select * from myarea where aid="+id;
		List<Area> list = JdbcUtil.executeQuery(sql, Area.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}	
	}

	@Override
	public int update(Area area) {
		String sql = "update myarea set aname=? where aid=?";
		return JdbcUtil.executeUpdate(sql, area.getAname(),area.getAid());
	}

	@Override
	public PageInfo<Area> findByPage(int pageNum, int pageSize) {
		String sql = "select * from myarea";
		String sql2 = "select count(1) from myarea";
		List<Area> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Area.class);
		int total = JdbcUtil.total(sql2);
		PageInfo<Area> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);//一定要放在封装总记录数之前
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
