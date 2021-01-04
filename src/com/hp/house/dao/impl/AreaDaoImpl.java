package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.AreaDao;
import com.hp.house.entity.Area;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class AreaDaoImpl implements AreaDao {

	private JDBCUtil JDBCUtil = new JDBCUtil();
	
	@Override
	public List<Area> ListAll() {
		String sql = "select * from myarea";
		return JDBCUtil.executeQuery(sql, Area.class);
	}
	public static void main(String[] args) {
		Area area = new Area();
		area.setAid(18);
		area.setAname("雨花区1");
		List<Area> save = new AreaDaoImpl().ListAll();
			
		System.out.println(save);
	}
	@Override
	public Area selectById(Integer id) {
		String sql = "SELECT * FROM myarea WHERE aid = ?";
		List<Area> arealist = JDBCUtil.executeQuery(sql, Area.class, id);
		Area area = new Area();
		for (Area a : arealist) {
			area.setAid(a.getAid());
			area.setAname(a.getAname());
		}
		return area;
	}

	@Override
	public int update(Area t) {
		String sql = "UPDATE myarea SET aname = ? WHERE aid = ?";
		return JDBCUtil.executeUpdate(sql, t.getAname(),t.getAid());
	}

	@Override
	public int save(Area t) {
		String sql = "INSERT INTO myarea VALUE(NULL,?)";
		return JDBCUtil.executeUpdate(sql, t.getAname());
	}

	@Override
	public int del(Integer id) {
		String sql = "DELETE FROM myarea WHERE aid = ?";
		return JDBCUtil.executeUpdate(sql, id);
	}
	
	@Override
	public PageInfo<Area> findByPage(int pageNum, int pageSize) {
		String sql = "select * from myarea";
		String sql2 = "select count(1) from myarea";
		List<Area> areaList = new JDBCUtil().findByPage(sql, pageNum, pageSize, Area.class);
		int total = new JDBCUtil().total(sql2);
		
		PageInfo<Area> pageInfo = new PageInfo<Area>();
		pageInfo.setList(areaList);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(pageSize);
		pageInfo.setTotal(total);
		return pageInfo;
	}

	


}
