package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.Semp;
import com.hp.house.entity.Emp;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class SempDaoImpl implements Semp {

	

	@Override
	public Emp selectById(Integer id) {
		String sql = "select * from emp where eid = ?";
		List<Emp> list = new JDBCUtil().executeQuery(sql, Emp.class, id);
		
		return list.get(0);
	}

	

	@Override
	public int save(Emp t) {
		String sql = "insert into emp VALUE(?,?,?,?,?,?,?,?,'0',?)";
		return new JDBCUtil().executeUpdate(sql, t.getEid(),
				t.getPid(),t.getJid(),t.getEname(),t.getEpsw(),t.getErealname()
				,t.getEtel(),t.getEaddress(),t.getEremark());
	}


	@Override
	public PageInfo<Emp> findByPage(int pageNum, int pageSize) {
		String sql = "select a.*,b.pname,c.jname from emp a inner join mydept b on a.pid=b.pid"
				+ " inner join myjs c on a.jid=c.jid";
		String sql2 = "select count(1) from emp a inner join mydept b on a.pid=b.pid"
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
	


	public static void main(String[] args) {
		Emp findByPage = new SempDaoImpl().selectById(1);
		
			System.out.println(findByPage);
	}



	@Override
	public List<Emp> ListAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int update(Emp t) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
