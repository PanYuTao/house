package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.DjtfDao;
import com.hp.house.entity.Djtf;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class DjtfDaoImpl implements DjtfDao {

	@Override
	public PageInfo<Djtf> findByPage(int current, int pageSize) {
		String sql = "SELECT a.*,c.haddress,c.hfh,d.cname,d.ctel,b.mdate,b.mbegintime FROM mytf a"
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid";
		String sql2 = "SELECT count(1) FROM mytf a" 
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid";
		List<Djtf> list = new JDBCUtil().findByPage(sql, current, pageSize, Djtf.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Djtf> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(current);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(allcount);
		return pageInfo;
	}
	public static void main(String[] args) {
		PageInfo<Djtf> findByPage = new DjtfDaoImpl().findByPage(1, 3);
		System.out.println(findByPage);
	}

	@Override
	public List<Djtf> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Djtf selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Djtf t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Djtf t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
