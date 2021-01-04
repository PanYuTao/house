package com.donkee.house.dao.impl;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;*/
import java.util.List;

import com.donkee.house.dao.CusDao;
import com.donkee.house.entity.Cus;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class CusDaoImpl implements CusDao {

	@Override
	public List<Cus> listAll() {
		String sql = "select * from mycus";
		List<Cus> list = JdbcUtil.executeQuery(sql, Cus.class);
		return list;
	}

	@Override
	public int save(Cus cus) {
		String sql = "insert into mycus(cname,csex,ctel,ctel1,ccard) values(?,?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, cus.getCname(),cus.getCsex(),cus.getCtel(),cus.getCtel1(),cus.getCcard());
	}

	@Override
	public int delete(int cid) {
		String sql = "delete from mycus where cid=?";
		return JdbcUtil.executeUpdate(sql, cid);
	}

	@Override
	public Cus findById(int cid) {
		String sql = "select * from mycus where cid="+cid;
		List<Cus> list = JdbcUtil.executeQuery(sql, Cus.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int update(Cus cus) {
		String sql = "update mycus set cname=?,csex=?,ctel=?,ctel1=?,ccard=? where cid=?";
		return JdbcUtil.executeUpdate(sql, cus.getCname(),cus.getCsex(),cus.getCtel(),cus.getCtel1(),cus.getCcard(),cus.getCid());
	}

	@Override
	public PageInfo<Cus> findByPage(int pageNum, int pageSize) {
		String sql = "select * from mycus";
		String sql2 = "select count(1) from mycus";
		List<Cus> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Cus.class);
		int total = JdbcUtil.total(sql2);
		PageInfo<Cus> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);//保存当前页
		pageInfo.setPageSize(pageSize);//一定要放在封装总记录数之前
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
