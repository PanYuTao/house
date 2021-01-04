package com.donkee.house.dao.impl;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;*/
import java.util.List;

import com.donkee.house.dao.SfDao;
import com.donkee.house.entity.Sf;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class SfDaoImpl implements SfDao {

	@Override
	public List<Sf> listAll() {
		String sql = "select * from mysf";
		List<Sf> list = JdbcUtil.executeQuery(sql, Sf.class);
		return list;
	}

	@Override
	public int save(Sf sf) {
		String sql = "insert into mysf(hid,mid,cid,eid,myzj,mbegintime) values(?,?,?,?,?,?)";
		int count = JdbcUtil.executeUpdate(sql, sf.getHid(),sf.getMid(),sf.getCid(),sf.getEid(),sf.getMyzj(),sf.getMbegintime());
		return count;
	}

	@Override
	public int delete(int yid) {
		String sql = "delete from mysf where yid=?";
		return JdbcUtil.executeUpdate(sql, yid);
	}

	@Override
	public Sf findById(int yid) {
		String sql = "select * from mysf where yid="+yid;
		List<Sf> list = JdbcUtil.executeQuery(sql, Sf.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int update(Sf sf) {
		String sql = "update mysf set hid=?,mid=?,cid=?,eid=?,myzj=?,mbegintime=? where yid=?";
		return JdbcUtil.executeUpdate(sql, sf.getHid(),sf.getMid(),sf.getCid(),sf.getEid(),sf.getMyzj(),sf.getMbegintime(),sf.getYid());
	}

	@Override
	public PageInfo<Sf> findByPage(int pageNum, int pageSize) {
		String sql = "SELECT a.*,b.erealname,e.cname,e.ctel,d.haddress,d.hfh,c.mdate FROM mysf a  INNER JOIN mydj c ON a.mid=c.mid INNER JOIN myemp b on a.eid=b.eid INNER JOIN myhouse d ON a.hid=d.hid INNER JOIN mycus e ON a.cid= e.cid";
		
		String sql1 = "select count(1) from mysf a  INNER JOIN mydj c ON a.mid=c.mid INNER JOIN myemp b on a.eid=b.eid INNER JOIN myhouse d ON a.hid=d.hid INNER JOIN mycus e ON a.cid= e.cid";
				
		PageInfo<Sf> pageInfo = new PageInfo<Sf>();
		try {
			pageInfo.setList(JdbcUtil.findByPage(sql, pageNum, pageSize, Sf.class));
			pageInfo.setPageNum(pageNum);
			pageInfo.setPageSize(pageSize);
			pageInfo.setTotal(JdbcUtil.total(sql1));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return pageInfo;
	
	}
	

}
