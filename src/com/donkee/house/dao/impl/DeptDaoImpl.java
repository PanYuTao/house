package com.donkee.house.dao.impl;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;*/
import java.util.List;

import com.donkee.house.dao.DeptDao;
import com.donkee.house.entity.Dept;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class DeptDaoImpl implements DeptDao {

	@Override
	public List<Dept> listAll() {
		String sql = "select * from mydept";
		List<Dept> list = JdbcUtil.executeQuery(sql, Dept.class);
		/*List<Dept> list = new ArrayList<Dept>();
		Connection conn = null;
		try {
			//1.创建链接
			conn = JdbcUtil.getConnection();
			//2.写sql
			String sql = "select * from mydept";
			//3.创建Statement对象
			PreparedStatement ps = conn.prepareStatement(sql);
			//4.给PreparedStatement中的参数传值
			//5.执行sql
			ResultSet rs = ps.executeQuery();
			//6.处理结果
			while (rs.next()) {//rs.next():将记录指针指向下一条,并返回是否成功；记录指针初始位置在第一条记录之上
				//将当前记录封装到实体对象
				Dept dept = new Dept();
				dept.setPid(rs.getInt("pid"));
				dept.setPname(rs.getString("pname"));
				dept.setPflag(rs.getInt("pflag"));
				dept.setPremark(rs.getString("premark"));
				list.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//7.关闭连接
			JdbcUtil.closeConnection(conn);
		}*/
		return list;
	}

	@Override
	public int save(Dept dept) {
		String sql = "insert into mydept(pname,pflag,premark) values(?,?,?)";
		return JdbcUtil.executeUpdate(sql, dept.getPname(),dept.getPflag(),dept.getPremark());
		/*
		 * int count = 0; Connection conn = null; try { conn = JdbcUtil.getConnection();
		 * String sql = "insert into mydept(pname,pflag,premark) values(?,?,?)";
		 * PreparedStatement ps = conn.prepareStatement(sql); ps.setString(1,
		 * dept.getPname()); ps.setInt(2, dept.getPflag()); ps.setObject(3,
		 * dept.getPremark()); count = ps.executeUpdate(); } catch (SQLException e) {
		 * e.printStackTrace(); }finally { //7.关闭连接 JdbcUtil.closeConnection(conn); }
		 * return count;
		 */
	}

	@Override
	public int delete(int pid) {
		String sql = "delete from mydept where pid=?";
		return JdbcUtil.executeUpdate(sql, pid);
		/*
		 * int count = 0; Connection conn = null; try { conn = JdbcUtil.getConnection();
		 * String sql = "delete from mydept where pid=?"; PreparedStatement ps =
		 * conn.prepareStatement(sql); ps.setInt(1, pid); count = ps.executeUpdate(); }
		 * catch (SQLException e) { e.printStackTrace(); }finally { //7.关闭连接
		 * JdbcUtil.closeConnection(conn); } return count;
		 */
	}

	@Override
	public Dept findById(int pid) {
		String sql = "select * from mydept where pid="+pid;
		List<Dept> list = JdbcUtil.executeQuery(sql, Dept.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
		/*Connection conn = null;
		Dept dept = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from mydept where pid=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dept = new Dept();
				dept.setPid(rs.getInt("pid"));
				dept.setPname(rs.getString("pname"));
				dept.setPflag(rs.getInt("pflag"));
				dept.setPremark(rs.getString("premark"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeConnection(conn);
		}
		return dept;*/
	}

	@Override
	public int update(Dept dept) {
		String sql = "update mydept set pname=?,pflag=?,premark=? where pid=?";
		return JdbcUtil.executeUpdate(sql, dept.getPname(),dept.getPflag(),dept.getPremark(),dept.getPid());
		/*
		 * int count = 0; 
		 * Connection conn = null; 
		 * try { 
		 * 		conn = JdbcUtil.getConnection();
		 * 		String sql = "update mydept set pname=?,pflag=?,premark=? where pid=?";
		 *		 PreparedStatement ps = conn.prepareStatement(sql); 
		 *		 ps.setString(1,dept.getPname()); 
		 *		 ps.setInt(2, dept.getPflag()); 
		 *		 ps.setString(3, dept.getPremark()); 
		 *		 ps.setInt(4, dept.getPid()); 
		 * 		count = ps.executeUpdate();
		 * } catch (SQLException e) { 
		 * e.printStackTrace(); 
		 * }finally { 
		 * //7.关闭连接
		 * 		JdbcUtil.closeConnection(conn); 
		 * } 
		 * return count;
		 */
	}

	@Override
	public PageInfo<Dept> findByPage(int pageNum, int pageSize) {
		String sql = "select * from mydept";
		String sql2 = "select count(1) from mydept";
		List<Dept> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Dept.class);
		int total = JdbcUtil.total(sql2);
		PageInfo<Dept> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);//保存当前页
		pageInfo.setPageSize(pageSize);//一定要放在封装总记录数之前
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
