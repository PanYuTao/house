package com.donkee.house.dao.impl;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;*/
import java.util.List;

import com.donkee.house.dao.TfDao;
import com.donkee.house.entity.Tf;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class TfDaoImpl implements TfDao {

	@Override
	public List<Tf> listAll() {
		String sql = "select * from mysf";
		List<Tf> list = JdbcUtil.executeQuery(sql, Tf.class);
		return list;
	}

	@Override
	public int save(Tf sf) {
		String sql = "insert into mysf(jname) values(?)";
		return JdbcUtil.executeUpdate(sql);
	}

	@Override
	public int delete(int tid) {
		String sql = "delete from mysf where tid=?";
		return JdbcUtil.executeUpdate(sql, tid);
	}

	@Override
	public Tf findById(int tid) {
		String sql = "select * from mysf where tid="+tid;
		List<Tf> list = JdbcUtil.executeQuery(sql, Tf.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int update(Tf tf) {
		String sql = "update mysf set jname=? where tid=?";
		return JdbcUtil.executeUpdate(sql);
	}

	@Override
	public PageInfo<Tf> findByPage(int pageNum, int pageSize) {
		String sql = "SELECT a.*,c.haddress,c.hfh,d.cname,d.ctel,b.mdate,b.mbegintime FROM mytf a"
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid";
		String sql2 = "SELECT count(1) FROM mytf a" 
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid";
		List<Tf> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Tf.class);
		int total = JdbcUtil.total(sql2);
		PageInfo<Tf> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);//一定要放在封装总记录数之前
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
