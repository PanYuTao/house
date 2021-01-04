package com.donkee.house.dao.impl;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;*/
import java.util.List;

import com.donkee.house.dao.DjDao;
import com.donkee.house.entity.Dj;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class DjDaoImpl implements DjDao {

	@Override
	public List<Dj> listAll() {
		String sql = "select * from mydj";
		List<Dj> list = JdbcUtil.executeQuery(sql, Dj.class);
		return list;
	}

	@Override
	public int save(Dj dj) {
		String sql = "insert into mydj(mdate,eid,cid,hid,mimg,myj,myzj,mflag,mbegintime) values(?,?,?,?,?,?,?,?,?)";
		return JdbcUtil.executeUpdate(sql ,dj.getMdate(),dj.getEid(),
				dj.getCid(),dj.getHid(),dj.getMimg(),dj.getMyj(),
				dj.getMyzj(),dj.getMflag(),dj.getMbegintime());
	}

	@Override
	public int delete(int mid) {
		String sql = "delete from mydj where mid=?";
		return JdbcUtil.executeUpdate(sql, mid);
	}

	@Override
	public Dj findById(int mid) {
		String sql = "select * from mydj where mid="+mid;
		List<Dj> list = JdbcUtil.executeQuery(sql, Dj.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int update(Dj dj) {
		String sql = "update mydj set cid=?,hid=?,mimg=?,myj=?,myzj=?,mbegintime=? where mid=?";
		return JdbcUtil.executeUpdate(sql ,dj.getCid(),dj.getHid(),dj.getMimg(),dj.getMyj(),dj.getMyzj(),dj.getMbegintime(),dj.getMid());
	}

	@Override
	public PageInfo<Dj> findByPage(int pageNum, int pageSize) {
		String sql = "select a.*,b.haddress,b.hfh,c.cname,c.ctel,d.erealname from mydj a inner join myhouse b on a.hid=b.hid"
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		String sql2 = "select count(1) from mydj a inner join myhouse b on a.hid=b.hid" 
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		List<Dj> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Dj.class);
		int total = JdbcUtil.total(sql2);
		PageInfo<Dj> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);//一定要放在封装总记录数之前
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
