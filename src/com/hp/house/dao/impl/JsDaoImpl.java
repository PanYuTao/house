package com.hp.house.dao.impl;

import java.util.List;

import com.hp.house.dao.JsDao;
import com.hp.house.entity.Js;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.JDBCUtil;

public class JsDaoImpl implements JsDao {

	@Override
	public List<Js> ListAll() {
		String sql = "select * from myjs";
		return new JDBCUtil().executeQuery(sql, Js.class);
		/*List<Js> list = new ArrayList<Js>();
		Connection conn = null;
		try {
			conn = JDBCUtil.getconnection();
			String sql = "select * from myjs";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Js js = new Js();
				js.setjId(rs.getInt("jId"));
				js.setjName(rs.getString("jName"));
				list.add(js);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(conn);
		}
		
		return list;*/
	}

	@Override
	public int save(String name){
		String sql = "INSERT INTO myjs VALUES(NULL,?)";		
		return new JDBCUtil().executeUpdate(sql, name);
		/*int count = 0;
		Connection conn = null;
		try {
			conn = JDBCUtil.getconnection();
			String sql = "INSERT INTO myjs VALUES(NULL,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(conn);
		}
		return count;*/
	}
	
	public Js selectJsById(Integer jid) {
		String sql = "SELECT * FROM myjs WHERE jid = ?";
		List<Js> list = new JDBCUtil().executeQuery(sql, Js.class,jid);
		Js js = new Js();
		for (Js j : list) {
			js.setJid(j.getJid());
			js.setJname(j.getJname());
		}
		return js;
		/*Js js = new Js();
		try {
			Connection conn = JDBCUtil.getconnection();
			String sql = "SELECT * FROM myjs WHERE jid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, jid);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				js.setjId(rs.getInt("jid"));
				js.setjName(rs.getString("jname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;*/
	}

	@Override
	public int update(Js js) {
		String sql = "UPDATE myjs SET jname = ? WHERE jid = ?";		
		return new JDBCUtil().executeUpdate(sql, js.getJname(),js.getJid());
		/*int count = 0;
		try {
			Connection conn = JDBCUtil.getconnection();
			String sql = "UPDATE myjs SET jname = ? WHERE jid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, js.getjName());
			pstmt.setInt(2, js.getjId());
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;*/
	}
	
	public int del(Integer jid) {
		String sql = "DELETE FROM myjs WHERE jid=?";		
		return new JDBCUtil().executeUpdate(sql, jid);
		/*int count = 0;
		try {
			Connection conn = JDBCUtil.getconnection();
			String sql = "DELETE FROM myjs WHERE jid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, jid);
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;*/
	}
	
	public static void main(String[] args) {
		Js js = new JsDaoImpl().selectJsById(18);
		System.out.println(js);
	}

	@Override
	public PageInfo<Js> findByPage(int pageNum, int pageSize) {
		String sql = "SELECT * FROM myjs";
		String sql2 = "SELECT count(1) FROM myjs";
		List<Js> jsList = new JDBCUtil().findByPage(sql, pageNum, pageSize, Js.class);
		int total = new JDBCUtil().total(sql2);
		
		PageInfo<Js> pageInfo = new PageInfo<Js>();
		pageInfo.setList(jsList);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(total);
				
		return pageInfo;
	}
}
