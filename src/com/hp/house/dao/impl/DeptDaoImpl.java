package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.DeptDao;
import com.hp.house.entity.Dept;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class DeptDaoImpl implements DeptDao {

	@Override
	public List<Dept> ListAll() {
		String sql = "select * from mydept";
		return new JDBCUtil().executeQuery(sql, Dept.class);
		/*List<Dept> list = new ArrayList<Dept>();
		Connection conn = null;
		
		try {
			conn = JDBCUtil.getconnection();
			String sql = "select * from mydept";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Dept dept = new Dept();
				dept.setPid(rs.getInt("pid"));
				dept.setPname(rs.getString("pname"));
				dept.setPflag(rs.getInt("pflag"));
				dept.setPremark(rs.getString("premark"));
				list.add(dept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(conn);
		}
		
		return list;*/
	}
	
	@Override
	public int save(Dept dept){
		String sql = "insert into mydept(pname,premark) values(?,?)"; 
		return new JDBCUtil().executeUpdate(sql, dept.getPname(),dept.getPremark());
		/*int count = 0;
		Connection conn = null;
		try {
			conn = JDBCUtil.getconnection();
			String sql = "insert into mydept(pname,premark) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,dept.getPname());//1:是第1个?号
			pstmt.setObject(2,dept.getPremark());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(conn);
		}
		return count;*/
	}

	public Dept selectById(Integer pid) {
		String sql = "SELECT * FROM mydept WHERE pid = ?";
		List<Dept> list = new JDBCUtil().executeQuery(sql, Dept.class,pid);
		Dept dept = new Dept();
		for (Dept d : list) {
			dept.setPid(d.getPid());
			dept.setPname(d.getPname());
			dept.setPremark(d.getPremark());
			dept.setPflag(d.getPflag());
		}
		return dept;
		/*Dept dept = new Dept();
		try {
			Connection conn = JDBCUtil.getconnection();
			String sql = "SELECT * FROM mydept WHERE pid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dept.setPid(rs.getInt("pid"));
				dept.setPname(rs.getString("pname"));
				dept.setPremark(rs.getString("premark"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dept;*/
	}
	
	public int update(Dept dept) {
		String sql = "UPDATE mydept SET pname = ?,premark = ? WHERE pid = ?";
		return new JDBCUtil().executeUpdate(sql, dept.getPname(),dept.getPremark(),dept.getPid());
		/*int count = 0;
		try {
			Connection conn = JDBCUtil.getconnection();
			String sql = "UPDATE mydept SET pname = ?,premark = ? WHERE pid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept.getPname());
			pstmt.setString(2, dept.getPremark());
			pstmt.setInt(3, dept.getPid());
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;*/
	}
	
	public int del(Integer pid) {
		String sql = "DELETE FROM mydept WHERE pid="+pid;
		return new JDBCUtil().executeUpdate(sql);
		/*int count = 0;
		try {
			Connection conn = JDBCUtil.getconnection();
			String sql = "DELETE FROM mydept WHERE pid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;*/
	}
	@Override
	public PageInfo<Dept> findByPage(int pageNum, int pageSize) {
		String sql = "select * from mydept";
		String sql2 = "select count(1) from mydept";
		List<Dept> deptList = new JDBCUtil().findByPage(sql, pageNum, pageSize, Dept.class);
		int total = new JDBCUtil().total(sql2);
		PageInfo<Dept> pageInfo = new PageInfo<Dept>();
		pageInfo.setList(deptList);
		pageInfo.setPagesize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setTotal(total);
		return pageInfo;
	}
	
	public static void main(String[] args) {
		Dept dept = new Dept();
		dept.setPid(26);
		dept.setPname("台式机");
		dept.setPremark("444");
		int update = new DeptDaoImpl().del(25);
		System.out.println(update);
	}

	
}
