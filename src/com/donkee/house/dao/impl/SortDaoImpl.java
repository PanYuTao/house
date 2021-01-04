package com.donkee.house.dao.impl;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;*/
import java.util.List;

import com.donkee.house.dao.SortDao;
import com.donkee.house.entity.Sort;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class SortDaoImpl implements SortDao {

	@Override
	public List<Sort> listAll() {
		String sql = "select * from mysort";
		List<Sort> list = JdbcUtil.executeQuery(sql, Sort.class);
		/*List<Sort> list = new ArrayList<Sort>();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from mysort";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sort sort = new Sort();
				sort.setSid(rs.getInt("sid"));
				sort.setSname(rs.getString("sname"));
				list.add(sort);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeConnection(conn);
		}*/
		return list;
	}

	@Override
	public int save(Sort sort) {
		String sql = "insert into mysort(sname) values(?)";
		return JdbcUtil.executeUpdate(sql, sort.getSname());
		/*
		 * int count = 0; Connection conn = null; try { conn = JdbcUtil.getConnection();
		 * String sql = "insert into mysort(sname) values(?)"; PreparedStatement ps =
		 * conn.prepareStatement(sql); ps.setString(1, sort.getSname()); count =
		 * ps.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }finally
		 * { JdbcUtil.closeConnection(conn); } return count;
		 */
	}

	@Override
	public int delete(int sid) {
		String sql = "delete from mysort where sid=?";
		return JdbcUtil.executeUpdate(sql, sid);
		/*
		 * int count = 0; Connection conn = null; try { conn = JdbcUtil.getConnection();
		 * String sql = "delete from mysort where sid=?"; PreparedStatement ps =
		 * conn.prepareStatement(sql); ps.setInt(1, sid); count = ps.executeUpdate(); }
		 * catch (SQLException e) { e.printStackTrace(); }finally { //7.关闭连接
		 * JdbcUtil.closeConnection(conn); } return count;
		 */
	}

	@Override
	public Sort findById(int sid) {
		String sql = "select * from mysort where sid="+sid;
		List<Sort> list = JdbcUtil.executeQuery(sql, Sort.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
		/*Connection conn = null;
		Sort sort = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from mysort where sid=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sort = new Sort();
				sort.setSid(rs.getInt("sid"));
				sort.setSname(rs.getString("sname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeConnection(conn);
		}
		return sort;*/
	}

	@Override
	public int update(Sort sort) {
		String sql = "update mysort set sname=? where sid=?";
		return JdbcUtil.executeUpdate(sql, sort.getSname(),sort.getSid());
		/*
		 * int count = 0; Connection conn = null; try { conn = JdbcUtil.getConnection();
		 * String sql = "update mysort set sname=? where sid=?"; PreparedStatement ps =
		 * conn.prepareStatement(sql); ps.setString(1, sort.getSname()); ps.setInt(2,
		 * sort.getSid()); count = ps.executeUpdate(); } catch (SQLException e) {
		 * e.printStackTrace(); }finally { //7.关闭连接 JdbcUtil.closeConnection(conn); }
		 * return count;
		 */
	}

	@Override
	public PageInfo<Sort> findByPage(int pageNum, int pageSize) {
		String sql = "select * from mysort";
		String sql2 = "select count(1) from mysort";
		List<Sort> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Sort.class);
		int total = JdbcUtil.total(sql2);
		PageInfo<Sort> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);//一定要放在封装总记录数之前
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
