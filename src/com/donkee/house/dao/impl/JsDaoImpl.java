package com.donkee.house.dao.impl;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;*/
import java.util.List;

import com.donkee.house.dao.JsDao;
import com.donkee.house.entity.Js;
import com.donkee.house.util.JdbcUtil;
import com.donkee.house.util.PageInfo;

public class JsDaoImpl implements JsDao {

	@Override
	public List<Js> listAll() {
		String sql = "select * from myjs";
		List<Js> list = JdbcUtil.executeQuery(sql, Js.class);
		/*List<Js> list = new ArrayList<Js>();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from myjs";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Js js = new Js();
				js.setJid(rs.getInt("jid"));
				js.setJname(rs.getString("jname"));
				list.add(js);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeConnection(conn);
		}*/
		return list;
	}

	@Override
	public int save(Js js) {
		String sql = "insert into myjs(jname) values(?)";
		return JdbcUtil.executeUpdate(sql, js.getJname());
		/*
		 * int count = 0; Connection conn = null; try { conn = JdbcUtil.getConnection();
		 * String sql = "insert into myjs(jname) values(?)"; PreparedStatement ps =
		 * conn.prepareStatement(sql); ps.setString(1, js.getJname()); count =
		 * ps.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }finally
		 * { JdbcUtil.closeConnection(conn); } return count;
		 */
	}

	@Override
	public int delete(int jid) {
		String sql = "delete from myjs where jid=?";
		return JdbcUtil.executeUpdate(sql, jid);
		/*
		 * int count = 0; Connection conn = null; try { conn = JdbcUtil.getConnection();
		 * String sql = "delete from myjs where jid=?"; PreparedStatement ps =
		 * conn.prepareStatement(sql); ps.setInt(1, jid); count = ps.executeUpdate(); }
		 * catch (SQLException e) { e.printStackTrace(); }finally { //7.关闭连接
		 * JdbcUtil.closeConnection(conn); } return count;
		 */
	}

	@Override
	public Js findById(int jid) {
		String sql = "select * from myjs where jid="+jid;
		List<Js> list = JdbcUtil.executeQuery(sql, Js.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
		/*Connection conn = null;
		Js js = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from myjs where jid=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, jid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				js = new Js();
				js.setJid(rs.getInt("jid"));
				js.setJname(rs.getString("jname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.closeConnection(conn);
		}
		return js;*/
	}

	@Override
	public int update(Js js) {
		String sql = "update myjs set jname=? where jid=?";
		return JdbcUtil.executeUpdate(sql, js.getJname(),js.getJid());
		/*
		 * int count = 0; Connection conn = null; try { conn = JdbcUtil.getConnection();
		 * String sql = "update myjs set jname=? where jid=?"; PreparedStatement ps =
		 * conn.prepareStatement(sql); ps.setString(1, js.getJname()); ps.setInt(2,
		 * js.getJid()); count = ps.executeUpdate(); } catch (SQLException e) {
		 * e.printStackTrace(); }finally { //7.关闭连接 JdbcUtil.closeConnection(conn); }
		 * return count;
		 */
	}

	@Override
	public PageInfo<Js> findByPage(int pageNum, int pageSize) {
		String sql = "select * from myjs";
		String sql2 = "select count(1) from myjs";
		List<Js> list = JdbcUtil.findByPage(sql, pageNum, pageSize, Js.class);
		int total = JdbcUtil.total(sql2);
		PageInfo<Js> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);//一定要放在封装总记录数之前
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
