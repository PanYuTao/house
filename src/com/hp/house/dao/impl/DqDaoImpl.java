package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.DqDao;
import com.hp.house.entity.Dq;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class DqDaoImpl implements DqDao {

	@Override
	public PageInfo<Dq> findByPage(int current, int pageSize) {
		String sql = "SELECT a.*,c.haddress,c.hfh,d.cname,d.ctel,e.erealname FROM mysf a"
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid"
				+" INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT count(1) FROM mysf a INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid" 
				+ " INNER JOIN myemp e ON a.eid=e.eid";
		List<Dq> list = new JDBCUtil().findByPage(sql, current, pageSize, Dq.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Dq> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(current);
		pageInfo.setPagesize(pageSize);//һ��Ҫ���ڷ�װ�ܼ�¼��֮ǰ
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

	@Override
	public List<Dq> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dq selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Dq t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Dq t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
