package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.BiaoDao;
import com.hp.house.entity.Biao;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class BiaoDaoImpl implements BiaoDao {

	@Override
	public PageInfo<Biao> findByPage(int current, int pageSize) {
		String sql = "SELECT a.*,c.haddress,c.hfh,e.erealname FROM mybiao a"
				+" INNER JOIN myhouse c ON a.hid=c.hid "
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM mybiao a INNER JOIN myhouse b ON a.hid=b.hid " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Biao> list = new JDBCUtil().findByPage(sql, current, pageSize, Biao.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Biao> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(current);
		pageInfo.setPagesize(pageSize);//һ��Ҫ���ڷ�װ�ܼ�¼��֮ǰ
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

	@Override
	public List<Biao> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Biao selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Biao t) {
		String sql = "insert into mybiao values(null,?,?,?,?,?,?)";		
		return new JDBCUtil().executeUpdate(sql, t.getHid(),t.getDkd(),t.getSkd(),t.getMkd()
				,t.getMtime(),t.getEid());
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Biao t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
