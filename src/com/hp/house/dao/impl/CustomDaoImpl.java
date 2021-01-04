package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.CustomDao;
import com.hp.house.entity.Custom;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class CustomDaoImpl implements CustomDao {

	@Override
	public List<Custom> ListAll() {
		String sql1 = "select * from mycus";
		List<Custom> list = new JDBCUtil().executeQuery(sql1, Custom.class);			
		return list;
	}
	public static void main(String[] args) {
		List<Custom> i = new CustomDaoImpl().ListAll();
		System.out.println(i);
	}

	@Override
	public Custom selectById(Integer id) {
		String sql = "select * from mycus where cid=?";
		List<Custom> list = new JDBCUtil().executeQuery(sql, Custom.class,id);
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public int update(Custom t) {
		String sql = "UPDATE mycus SET CNAME=?,CSEX=?,CTEL=?,CTEL1=?,CCARD=? WHERE cid=?";
		return new JDBCUtil().executeUpdate(sql, t.getCname(),t.getCsex(),t.getCtel(),t.getCtel1(),t.getCcard(),t.getCid());
	}

	@Override
	public int save(Custom t) {
		String sql = "insert into mycus VALUE(null,?,?,?,?,?)";
		return new JDBCUtil().executeUpdate(sql, t.getCname(),t.getCsex(),t.getCtel(),t.getCtel1(),t.getCcard());
	}

	@Override
	public int del(Integer id) {
		String sql = "delete from mycus where cid = ?";
		return new JDBCUtil().executeUpdate(sql, id);
	}
	
	
	@Override
	public PageInfo<Custom> findByPage(int pageNum, int pageSize) {
		String sql = "select * from mycus";
		String sql2 = "select count(1) from mycus";
		
		List<Custom> customList = new JDBCUtil().findByPage(sql, pageNum, pageSize, Custom.class);
		int total = new JDBCUtil().total(sql2);
		
		PageInfo<Custom> pageInfo = new PageInfo<Custom>();
		pageInfo.setList(customList);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
