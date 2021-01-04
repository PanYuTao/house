package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.DjsfDao;
import com.hp.house.entity.Djsf;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class DjsfDaoImpl implements DjsfDao {

	@Override
	public List<Djsf> ListAll() {
		String sql = "select * from mysf";
		List<Djsf> list = new JDBCUtil().executeQuery(sql, Djsf.class);
		return list;
	}

	

	@Override
	public Djsf selectById(Integer yid) {
		String sql = "select * from mysf where yid="+yid;
		List<Djsf> list = new JDBCUtil().executeQuery(sql, Djsf.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public PageInfo<Djsf> findByPage(int current, int pageSize) {
		String sql = "SELECT a.*,c.haddress,c.hfh,d.cname,d.ctel,e.erealname FROM mysf a"
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid"
				+" INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT count(1) FROM mysf a INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid" 
				+ " INNER JOIN myemp e ON a.eid=e.eid";
		List<Djsf> list = new JDBCUtil().findByPage(sql, current, pageSize, Djsf.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Djsf> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(current);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

	@Override
	public int update(Djsf t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Djsf t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
