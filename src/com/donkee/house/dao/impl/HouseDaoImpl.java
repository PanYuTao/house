package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.HouseDao;
import com.donkee.house.entity.House;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class HouseDaoImpl implements HouseDao {

	@Override
	public List<House> listAll() {
		String sql = "select * from myhouse";
		List<House> list = JdbcUtil.executeQuery(sql, House.class);
		return list;
	}

	@Override
	public int save(House house) {
		String sql = "insert into myhouse(sid,aid,haddress,hfh,hhx,hmj,hcx,hmoney,hwf,hdx,hsf,hmq,dkd,skd,mkd,hjp,hremark,himg,hflag) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, house.getSid(),house.getAid(),house.getHaddress(),
				house.getHfh(),house.getHhx(),house.getHmj(),house.getHcx(),house.getHmoney(),
				house.getHwf(),house.getHdx(),house.getHsf(),house.getHmq(),house.getDkd(),
				house.getSkd(),house.getMkd(),house.getHjp(),house.getHremark(),house.getHimg(),house.getHflag());
	}

	@Override
	public int delete(int hid) {
		String sql = "delete from myhouse where hid=?";
		return JdbcUtil.executeUpdate(sql, hid);
	}

	@Override
	public House findById(int hid) {
		String sql = "select * from myhouse where hid="+hid;
		List<House> list = JdbcUtil.executeQuery(sql, House.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int update(House house) {
		String sql = "update myhouse set sid=?,aid=?,haddress=?,hfh=?,hhx=?,hmj=?,hcx=?,hmoney=?,hwf=?,hdx=?,hsf=?,hmq=?,dkd=?,skd=?,mkd=?,hjp=?,hremark=?,himg=? where hid=?";
		return JdbcUtil.executeUpdate(sql, house.getSid(),house.getAid(),house.getHaddress(),
				house.getHfh(),house.getHhx(),house.getHmj(),house.getHcx(),house.getHmoney(),
				house.getHwf(),house.getHdx(),house.getHsf(),house.getHmq(),house.getDkd(),
				house.getSkd(),house.getMkd(),house.getHjp(),house.getHremark(),house.getHimg(),house.getHid());
	}

	@Override
	public PageInfo<House> findByPage(int pageNum, int pageSize) {
		String sql = "select a.*,b.sname,c.aname from myhouse a inner join mysort b on a.sid=b.sid"
				+" inner join myarea c on a.aid=c.aid";
		String sql2 = "select count(1) from myhouse a inner join mysort b on a.sid=b.sid" + 
				" inner join myarea c on a.aid=c.aid";
		PageInfo<House> pageInfo = new PageInfo<House>();
		pageInfo.setList(JdbcUtil.findByPage(sql, pageNum, pageSize, House.class));
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

	@Override
	public List<House> listArea(int aid) {
		String sql = "select * from myhouse where aid="+aid;
		return JdbcUtil.executeQuery(sql, House.class);
	}

	@Override
	public PageInfo<House> findByCondition(int pageNum, int pageSize, House house) {
		String pub = " from myhouse a inner join myarea b on a.aid=b.aid inner join mysort c on a.sid=c.sid inner join mydj d on a.hid=d.hid inner join mycus e on d.cid=e.cid inner join myemp f on d.eid=f.eid where 1=1";
		//动态查询，根据条件动态生成SQL
		if (house!=null) {
			if (house.getSid()!=0) 
				pub+=" and a.sid="+house.getSid();
			if (house.getAid()!=0)
				pub+=" and a.aid="+house.getAid();
			if (house.getHflag()!=null && !house.getHflag().equals("-1"))
				pub+=" and a.hflag="+house.getHflag();
			if (house.getHid()!=0 && house.getHid()!=-1)
				pub+=" and a.hid="+house.getHid();
		}
		String sql = "select a.*,c.sname,b.aname,d.mid,d.mdate,d.mbegintime,d.myj,d.myzj,e.cname,e.ctel,f.erealname"+pub;
		String sql2 = "select count(1)"+pub;
		List<House> list = JdbcUtil.findByPage(sql, pageNum, pageSize, House.class);
		PageInfo<House> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));	
		return pageInfo;
	}

}
