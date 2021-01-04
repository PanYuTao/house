package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.ZhichuDao;
import com.donkee.house.entity.Zhichu;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;


public class ZhichuDaoImpl implements ZhichuDao {

	@Override
	public List<Zhichu> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Zhichu zc) {
		String sql = "insert into myzhichu values(null,?,?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, zc.getEid(),zc.getZmoney(),zc.getZtm(),zc.getZtime(),zc.getZremark());
	}

	@Override
	public int update(Zhichu zc) {
		String sql = "update myzhichu set eid=?,zmoney=?,ztm=?,zremark=? where zid=?";
		return JdbcUtil.executeUpdate(sql, zc.getEid(),zc.getZmoney(),zc.getZtm(),zc.getZremark(),zc.getZid());
	}

	@Override
	public Zhichu findById(int zid) {
		String sql = "select * from myzhichu where zid="+zid;
		List<Zhichu> list = JdbcUtil.executeQuery(sql, Zhichu.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
		
	}

	@Override
	public int delete(int zid) {
		String sql = "delete from myzhichu where zid=?";
		return JdbcUtil.executeUpdate(sql, zid);
	}

	@Override
	public PageInfo<Zhichu> findByPage(int pageNum, int pageSize) {
		String sql = "SELECT a.*,e.erealname FROM myzhichu a"
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM myzhichu a " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Zhichu> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Zhichu.class);
		PageInfo<Zhichu> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

}
