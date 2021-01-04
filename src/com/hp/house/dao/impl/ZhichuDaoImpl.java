package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.ZhichuDao;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Zhichu;
import com.hp.house.utils.JDBCUtil;

public class ZhichuDaoImpl implements ZhichuDao {

	@Override
	public PageInfo<Zhichu> findByPage(int current, int pageSize) {
		String sql = "SELECT a.*,e.erealname FROM myzhichu a"
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM myzhichu a " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Zhichu> list = new JDBCUtil().findByPage(sql, current, pageSize, Zhichu.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Zhichu> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(current);
		pageInfo.setPagesize(pageSize);//һ��Ҫ���ڷ�װ�ܼ�¼��֮ǰ
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

	@Override
	public List<Zhichu> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zhichu selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Zhichu t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Zhichu t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
