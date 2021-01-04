package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.HouseDao;
import com.hp.house.entity.House;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class HouseDaoImpl implements HouseDao {

	@Override
	public List<House> ListAll() {
		String sql = "select * from myhouse";
		List<House> list = new JDBCUtil().executeQuery(sql, House.class);
		return list;
	}

	@Override
	public int save(House house) {
		String sql = "insert into myhouse values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,null)";
		return new JDBCUtil().executeUpdate(sql,house.getSid(),house.getAid(),house.getHaddress(),
				house.getHfh(),house.getHhx(),house.getHmj(),house.getHcx(),house.getHmoney(),
				house.getHwf(),house.getHdx(),house.getHsf(),house.getHmq(),house.getDkd(),
				house.getSkd(),house.getMkd(),house.getHjp(),house.getHremark(),house.getHimg());
	}

	@Override
	public int del(Integer hid) {
		String sql = "delete from myhouse where hid =?";
		return new JDBCUtil().executeUpdate(sql,hid);
	}

	@Override
	public PageInfo<House> findByPage(int current, int pageSize) {
		String sql = "select a.*,b.sname,c.aname from myhouse a inner join mysort b on a.sid=b.sid"
				+" inner join myarea c on a.aid=c.aid";
		String sql2 = "select count(1) from myhouse a inner join mysort b on a.sid=b.sid" + 
				" inner join myarea c on a.aid=c.aid";
		List<House> list = new JDBCUtil().findByPage(sql, current, pageSize, House.class);
		int total = new JDBCUtil().total(sql2);
		PageInfo<House> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(current);
		pageInfo.setList(list);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(total);
		
		return pageInfo;
	}

	@Override
	public House selectById(Integer id) {
		String sql = "select * from myhouse where hid = ?";
		return new JDBCUtil().executeQuery(sql, House.class, id).get(0);
	}
	
	@Override
	public int update(House t) {
		String sql = "update myhouse set "
				+ "sid = ?,aid = ?,HADDRESS= ? ,HFH = ?,HHX = ?,HMJ = ?,HCX = ?,"
				+ "HMONEY = ?,HWF = ?,HDX = ?,HSF = ?,HMQ = ?,DKD = ?,SKD = ?,MKD = ?,"
				+ "HJP =?,HREMARK = ?,himg = ? "  
				+ "where hid = ?";
		return new JDBCUtil().executeUpdate(sql, 
				t.getSid(),t.getAid(),t.getHaddress(),t.getHfh(),
				t.getHhx(),t.getHmj(),t.getHcx(),t.getHmoney(),
				t.getHwf(),t.getHdx(),t.getHsf(),t.getHmq(),
				t.getDkd(),t.getSkd(),t.getMkd(),t.getHjp(),t.getHremark(),t.getHimg(),t.getHid());
	}

	@Override
	public PageInfo<House> findByCondition(int pageNum, int pageSize, House house) {
		String pub = " from myhouse a inner join myarea b on a.aid=b.aid inner join mysort c on a.sid=c.sid where 1=1";
		if(house != null){
			if(house.getSid() != 0)
				pub+=" and a.sid="+house.getSid();
			if(house.getAid() != 0)
				pub+=" and a.aid="+house.getAid( );
			if(house.getHflag()!=null && house.getHflag( ).equals("-1"))
				pub+=" and a.hflag "+house.getHflag();
		}
		String sql = "select a.*,b.aname,c.sname "+pub;
		String sql2 = "select count(1) "+pub;
		List<House> list = new JDBCUtil().findByPage(sql,pageNum,pageSize,House.class);
		int total = new JDBCUtil().total(sql2);
		PageInfo<House> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum) ;
		pageInfo.setPagesize(pageSize);
		pageInfo.setList( list);
		pageInfo.setTotal(total);
		
		return pageInfo;
	}
	@Override
	public List<House> findById(House house) {
		String pub = "from myhouse a inner join myarea b on a.aid=b.aid "
				+ "inner join mysort c on a.sid=c.sid where 1=1";
		if(house != null){
			if(house.getSid() != 0)
				pub+=" and a.sid="+house.getSid();
			if(house.getAid() != 0)
				pub+=" and a.aid="+house.getAid();
		}
		String sql = "select a.*,b.aname,c.sname "+pub;
		List<House> list = new JDBCUtil().executeQuery(sql, House.class);
		return list;
	}
	
	public static void main(String[] args) {
		House house = new House();
		house.setSid(1);
		house.setAid(1);
		List<House> list = new HouseDaoImpl().findById(house);
		System.out.println(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
