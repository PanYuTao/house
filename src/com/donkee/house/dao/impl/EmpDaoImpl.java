package com.donkee.house.dao.impl;

import java.util.List;

import com.donkee.house.dao.EmpDao;
import com.donkee.house.entity.Emp;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<Emp> listAll() {
		String sql = "select * from myemp";
		List<Emp> list = JdbcUtil.executeQuery(sql, Emp.class);
		return list;
	}

	@Override
	public int save(Emp emp) {
		String sql = "insert into myemp(pid,jid,ename,epsw,erealname,etel,eaddress,eflag,eremark) values(?,?,?,?,?,?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, emp.getPid(),emp.getJid(),emp.getEname(),emp.getEpsw(),emp.getErealname(),emp.getEtel(),emp.getEaddress(),emp.getEflag(),emp.getEremark());
	}

	@Override
	public int delete(int eid) {
		String sql = "delete from myemp where eid=?";
		return JdbcUtil.executeUpdate(sql, eid);
	}

	@Override
	public Emp findById(int eid) {
		String sql = "select * from myemp where eid="+eid;
		List<Emp> list = JdbcUtil.executeQuery(sql, Emp.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int update(Emp emp) {
		String sql = "update myemp set pid=?,jid=?,ename=?,epsw=?,erealname=?,etel=?,eaddress=?,eflag=?,eremark=? where eid=?";
		return JdbcUtil.executeUpdate(sql, emp.getPid(),emp.getJid(),emp.getEname(),emp.getEpsw(),emp.getErealname(),emp.getEtel(),emp.getEaddress(),emp.getEflag(),emp.getEremark(),emp.getEid());
	}

	@Override
	public PageInfo<Emp> findByPage(int pageNum, int pageSize) {
		String sql = "select a.*,b.pname,c.jname from myemp a inner join mydept b on a.pid=b.pid"
				+" inner join myjs c on a.jid=c.jid";
		String sql2 = "select count(1) from myemp a inner join mydept b on a.pid=b.pid"
				+" inner join myjs c on a.jid=c.jid";
		PageInfo<Emp> pageInfo = new PageInfo<Emp>();
		pageInfo.setList(JdbcUtil.findByPage(sql, pageNum, pageSize, Emp.class));
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(JdbcUtil.total(sql2));
		return pageInfo;
	}

	@Override
	public Emp findByName(String ename) {
		String sql = "select * from myemp where ename=?";
		Object[] params = {ename};
		List<Emp> list = JdbcUtil.executeQuery(sql, Emp.class, params);
		if (list.size()>0)
			return list.get(0);
		return null;
	}

}
