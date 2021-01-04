package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.BsDao;
import com.hp.house.entity.Bs;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class BsDaoImpl implements BsDao{

	@Override
	public PageInfo<Bs> findByPage(int current, int pageSize) {
		String sql = "SELECT a.*,c.haddress,c.hfh,e.erealname FROM mybs a"
				+" INNER JOIN myhouse c ON a.hid=c.hid "
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM mybs a INNER JOIN myhouse b ON a.hid=b.hid " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Bs> list = new JDBCUtil().findByPage(sql, current, pageSize, Bs.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Bs> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(current);
		pageInfo.setPagesize(pageSize);//һ��Ҫ���ڷ�װ�ܼ�¼��֮ǰ
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

	@Override
	public List<Bs> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bs selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Bs t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Bs t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
