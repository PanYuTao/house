package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.DjrzDao;
import com.hp.house.entity.Djrz;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class DjrzDaoImpl implements DjrzDao {

	@Override
	public List<Djrz> ListAll() {
		String sql = "select * from mydj";
		List<Djrz> list = new JDBCUtil().executeQuery(sql, Djrz.class);
		return list;
	}

	@Override
	public PageInfo<Djrz> findByPage(int current, int pageSize) {
		String sql = "select a.*,b.haddress,b.hfh,c.cname,c.ctel,d.erealname from mydj a inner join myhouse b on a.hid=b.hid"
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		String sql2 = "select count(1) from mydj a inner join myhouse b on a.hid=b.hid" 
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		List<Djrz> list = new JDBCUtil().findByPage(sql, current, pageSize, Djrz.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Djrz> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(current);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(allcount);
		return pageInfo;
	}
	public static void main(String[] args) {
		PageInfo<Djrz> pageInfo = new DjrzDaoImpl().findByPage(1, 3);
		System.out.println(pageInfo);
	}

	@Override
	public Djrz selectById(Integer id) {
		String sql = "select * from mydj where mid=?";
		List<Djrz> list = new JDBCUtil().executeQuery(sql, Djrz.class,id);		
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public int update(Djrz t) {
		String sql = "update mydj set cid=?,hid=?,myj=?,myzj=?,mbegintime=? where mid=?";
		return new JDBCUtil().executeUpdate(sql,t.getCid(),t.getHid(),t.getMyj(),t.getMyzj(),t.getMbegintime(),t.getMid());
	}

	@Override
	public int save(Djrz t) {
		String sql = "INSERT INTO mydj VALUES(NULL,null,null,?,?,NULL,?,?,null,?)";
		return new JDBCUtil().executeUpdate(sql,t.getCid(),t.getHid(),
				t.getMyj(),t.getMyzj(),t.getMbegintime());
	}

	@Override
	public int del(Integer id) {
		String sql = "delete from mydj where mid=?";
		return new JDBCUtil().executeUpdate(sql,id);
	}

}
