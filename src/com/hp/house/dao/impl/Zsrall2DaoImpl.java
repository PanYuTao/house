package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.Zsrall2Dao;
import com.hp.house.entity.House;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Zsrall1;
import com.hp.house.entity.Zsrall2;
import com.hp.house.utils.JDBCUtil;

public class Zsrall2DaoImpl implements Zsrall2Dao {

	@Override
	public PageInfo<Zsrall2> findByPage(int current, int pageSize) {
		String sql = "select a.*,b.haddress,b.hfh,c.cname,c.ctel,d.erealname from mydj a inner join myhouse b on a.hid=b.hid"
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		String sql2 = "select count(1) from mydj a inner join myhouse b on a.hid=b.hid" 
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		List<Zsrall2> list = new JDBCUtil().findByPage(sql, current, pageSize, Zsrall2.class);
		int allcount = new JDBCUtil().total(sql2);
		PageInfo<Zsrall2> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(current);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

	@Override
	public PageInfo<Zsrall2> findByCondition(int pageNum, int pageSize, Zsrall2 zsrall2) {
		String pub = " from mydj a inner join myhouse b on a.hid=b.hid "
				+ "inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid "
				+ "where 1=1";
		if(zsrall2 != null){
			if(zsrall2.getSid() != 0)
				pub+=" and b.sid="+zsrall2.getSid();
			if(zsrall2.getAid() != 0)
				pub+=" and b.aid="+zsrall2.getAid( );
			if(zsrall2.getHid() > 0)
				pub+=" and a.hid ="+zsrall2.getHid();
		}
		String sql = "select a.*,b.SID,b.aid,b.haddress,b.hfh,c.cname,c.ctel,d.erealname "+pub;
		String sql2 = "select count(1) "+pub;
		List<Zsrall2> list = new JDBCUtil().findByPage(sql,pageNum,pageSize,Zsrall2.class);
		int total = new JDBCUtil().total(sql2);
		System.out.println(sql + "\n" +sql2);
		PageInfo<Zsrall2> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum) ;
		pageInfo.setPagesize(pageSize);
		pageInfo.setList(list);
		pageInfo.setTotal(total);
		
		return pageInfo;
	}
	public static void main(String[] args) {
		Zsrall2 zsrall2 = new Zsrall2();
		zsrall2.setAid(2);
		zsrall2.setSid(2);
		zsrall2.setHid(2);
		PageInfo<Zsrall2> findByPage = new Zsrall2DaoImpl().findByCondition(1, 5, zsrall2);
		System.out.println(findByPage);
	}
	
	@Override
	public List<Zsrall2> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zsrall2 selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Zsrall2 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Zsrall2 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
