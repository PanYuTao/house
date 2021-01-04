package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.BsDao;
import com.donkee.house.entity.Bs;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;



public class BsDaoImpl  implements BsDao{

	@Override
	public List<Bs> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Bs bs) {
		String sql = "insert into mybs(hid,mtime,bremark,eid) values(?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, bs.getHid(),bs.getMtime(),bs.getBremark(),bs.getEid());
	}

	@Override
	public int update(Bs bs) {
		String sql = "update mybs set hid=?,bremark=?,eid=? where bid=?";
		return JdbcUtil.executeUpdate(sql, bs.getHid(),bs.getBremark(),bs.getEid(),bs.getBid());
	}

	@Override
	public Bs findById(int bid) {
		String sql = "select * from mybs where bid="+bid;
		List<Bs> list = JdbcUtil.executeQuery(sql, Bs.class);
		if (list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public int delete(int bid) {
		String sql = "delete from mybs where bid=?";
		return JdbcUtil.executeUpdate(sql, bid);
	}

	@Override
	public PageInfo<Bs> findByPage(int pageNum, int pageSize) {
		String sql = "SELECT a.*,c.haddress,c.hfh,e.erealname FROM mybs a"
				+" INNER JOIN myhouse c ON a.hid=c.hid "
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM mybs a INNER JOIN myhouse b ON a.hid=b.hid " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Bs> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Bs.class);
		PageInfo<Bs> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

}
