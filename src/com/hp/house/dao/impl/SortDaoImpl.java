package com.hp.house.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hp.house.dao.SortDao;
import com.hp.house.entity.Dept;
import com.hp.house.entity.Js;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Sort;
import com.hp.house.utils.JDBCUtil;

public class SortDaoImpl implements SortDao {

	@Override
	public List<Sort> ListAll() {
		String sql = "select * from mysort";
		return new JDBCUtil().executeQuery(sql, Sort.class);
		/*List<Sort> list = new ArrayList<Sort>();
		Connection conn = null;
		
		try {
			conn = JDBCUtil.getconnection();
			String sql = "select * from mysort";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sort sort = new Sort();
				sort.setsId(rs.getInt("sId"));
				sort.setsName(rs.getString("sName"));
				list.add(sort);
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
		String sql = "INSERT INTO mysort VALUES(NULL,?)";
		return new JDBCUtil().executeUpdate(sql, name);
		/*int count = 0;
		Connection conn = null;
		try {
			conn = JDBCUtil.getconnection();
			String sql = "INSERT INTO mysort VALUES(NULL,?)";
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
	public Sort selectSortById(Integer sid) {
		String sql = "SELECT * FROM mysort WHERE sid = ?";
		List<Sort> list = new JDBCUtil().executeQuery(sql, Sort.class,sid);
		Sort sort = new Sort();
		for (Sort s : list) {
			sort.setSid(s.getSid());
			sort.setSname(s.getSname());
		}
		return sort;
		/*Sort sort = new Sort();
		try {
			Connection conn = JDBCUtil.getconnection();
			String sql = "SELECT * FROM mysort WHERE sid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				sort.setsId(rs.getInt("sid"));
				sort.setsName(rs.getString("sname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sort;*/
	}

	@Override
	public int update(Sort sort) {
		String sql = "UPDATE mysort SET sname = ? WHERE sid = ?";
		return new JDBCUtil().executeUpdate(sql, sort.getSname(),sort.getSid());
		/*int count = 0;
		try {
			Connection conn = JDBCUtil.getconnection();
			String sql = "UPDATE mysort SET sname = ? WHERE sid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sort.getsName());
			pstmt.setInt(2, sort.getsId());
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;*/
	}
	
	public int del(Integer sid) {
		String sql = "DELETE FROM mysort WHERE sid=?";
		return new JDBCUtil().executeUpdate(sql, sid);
		/*int count = 0;
		try {
			Connection conn = JDBCUtil.getconnection();
			String sql = "DELETE FROM mysort WHERE sid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;*/
	}
	
	@Override
	public PageInfo<Sort> findByPage(int pageNum, int pageSize) {
		String sql = "select * from mysort";
		String sql2 = "select count(1) from mysort";
		List<Sort> sortList = new JDBCUtil().findByPage(sql, pageNum, pageSize, Sort.class);
		int total = new JDBCUtil().total(sql2);
		
		PageInfo<Sort> pageInfo = new PageInfo<Sort>();
		pageInfo.setList(sortList);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(total);         
		
		return pageInfo;
	}
	
	public static void main(String[] args) {
		Sort sort = new Sort();
		sort.setSid(28);
		sort.setSname("计算机");
		List<Sort> listAll = new SortDaoImpl().ListAll();
		for (Sort sort2 : listAll) {
			System.out.println(sort2);
		}
	}

	
}
