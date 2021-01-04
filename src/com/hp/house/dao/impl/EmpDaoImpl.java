package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.EmpDao;
import com.hp.house.entity.Emp;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<Emp> ListAll() {
		
		return null;
	}

	@Override
	public Emp selectById(Integer id) {
		String sql = "select * from myemp where eid = ?";
		List<Emp> list = new JDBCUtil().executeQuery(sql, Emp.class, id);
		
		return list.get(0);
	}

	@Override
	public int update(Emp t) {
		String sql = "update myemp set pid = ?,jid=?,ENAME=?,EPSW=?,EREALNAME=?,ETEL=?" + 
				",EADDRESS=?,EREMARK=? where eid = ?";
		return new JDBCUtil().executeUpdate(sql, t.getPid(),t.getJid(),t.getEname(),t.getEpsw()
				,t.getErealname(),t.getEtel(),t.getEaddress(),t.getEremark(),t.getEid());
	}

	@Override
	public int save(Emp t) {
		String sql = "insert into myemp VALUE(null,?,?,?,?,?,?,?,'0',?)";
		return new JDBCUtil().executeUpdate(sql, 
				t.getPid(),t.getJid(),t.getEname(),t.getEpsw(),t.getErealname()
				,t.getEtel(),t.getEaddress(),t.getEremark());
	}

	@Override
	public int del(Integer id) {
		String sql = "delete from myemp where eid = ?";
		return new JDBCUtil().executeUpdate(sql,id);
	}

	@Override
	public PageInfo<Emp> findByPage(int pageNum, int pageSize) {
		String sql = "select a.*,b.pname,c.jname from myemp a inner join mydept b on a.pid=b.pid"
				+ " inner join myjs c on a.jid=c.jid";
		String sql2 = "select count(1) from myemp a inner join mydept b on a.pid=b.pid"
				+ " inner join myjs c on a.jid=c.jid";
		
		List<Emp> empList = new JDBCUtil().findByPage(sql, pageNum, pageSize, Emp.class);
		int total = new JDBCUtil().total(sql2);	
		
		PageInfo<Emp> pageInfo = new  PageInfo<Emp>();
		pageInfo.setList(empList);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(total);
		
		return pageInfo;
	}
	
	@Override
	public Emp findByName(String ename) {
		String sql = "select * from myemp where ename = ?";
		Object[] params = {ename};
		List<Emp> list = new JDBCUtil().executeQuery(sql, Emp.class, ename);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	public static void main(String[] args) {
		Emp findByPage = new EmpDaoImpl().selectById(1);
		
			System.out.println(findByPage);
	}

}
