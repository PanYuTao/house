package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.BiaoDao;
import com.donkee.house.entity.Biao;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;



public class BiaoDaoImpl implements BiaoDao {

	@Override
	public List<Biao> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Biao biao) {
		String sql = "insert into mybiao(hid,dkd,skd,mkd,mtime,eid) values(?,?,?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, biao.getHid(),biao.getDkd(),biao.getSkd(),biao.getMkd(),biao.getMtime(),biao.getEid());
	}

	@Override
	public int update(Biao biao) {
		String sql = "update mybiao set hid=?,dkd=?,skd=?,mkd=?,eid=? where bid=?";
		return JdbcUtil.executeUpdate(sql, biao.getHid(),biao.getDkd(),biao.getSkd(),biao.getMkd(),biao.getEid(),biao.getBid());
	}

	@Override
	public Biao findById(int bid) {
		String sql = "select * from mybiao where bid="+bid;
		List<Biao> list = JdbcUtil.executeQuery(sql, Biao.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int delete(int bid) {
		String sql = "delete from mybiao where bid=?";
		return JdbcUtil.executeUpdate(sql, bid);
	}

	@Override
	public PageInfo<Biao> findByPage(int pageNum, int pageSize) {
		String sql = "SELECT a.*,c.haddress,c.hfh,e.erealname FROM mybiao a"
				+" INNER JOIN myhouse c ON a.hid=c.hid "
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM mybiao a INNER JOIN myhouse b ON a.hid=b.hid " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Biao> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Biao.class);
		int allcount = JdbcUtil.total(sql2);
		PageInfo<Biao> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);//一定要放在封装总记录数之前
		pageInfo.setTotal(allcount);
		return pageInfo;
	}

}
