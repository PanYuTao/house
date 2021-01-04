package com.hp.house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.house.dao.DeptDao;
import com.hp.house.dao.impl.DeptDaoImpl;
import com.hp.house.entity.Dept;
import com.hp.house.entity.PageInfo;

/**
 * Servlet implementation class DeptController
 */
public class DeptController extends HttpServlet {

	private DeptDao deptDao = new DeptDaoImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if(type != null) {
			switch(type) {
				case "list" :
					list(request,response);
					break;
				case "listAll" :
					listAll(request,response);
					break;
				case "add" :
					add(request,response);
					break;
				case "toUpdate" :
					toUpdate(request,response);
					break;
				case "update" :
					update(request,response);
					break;
				case "del" :
					del(request,response);
					break;
			}
		}else {
			System.out.println(response.getCharacterEncoding());
			response.setCharacterEncoding("gbk");
			response.getWriter().write("type无值");
		}
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Dept> deptList = deptDao.ListAll();
		String jsonStr = new ObjectMapper().writeValueAsString(deptList);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		
	}
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pidStr = request.getParameter("pid");
		
		int pid = Integer.parseInt(pidStr);
		int i = deptDao.del(pid);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pidStr = request.getParameter("dept.pid");
		String pnameStr = request.getParameter("dept.pname");
		String premarkStr = request.getParameter("dept.premark");

		int pid = Integer.parseInt(pidStr);
		Dept dept = new Dept(pid, pnameStr, premarkStr);
		
		int i = deptDao.update(dept);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pidStr = request.getParameter("pid");
		int pid = Integer.parseInt(pidStr);
		Dept dept = deptDao.selectById(pid);
		String jsonStr = new ObjectMapper().writeValueAsString(dept);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("dept.pname");

		String remark = request.getParameter("dept.premark");
		
		Dept dept = new Dept(name,remark); 
		
		int save = deptDao.save(dept);
		
		String jsonStr = new ObjectMapper().writeValueAsString(save);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if (current != null) {
			pageNum = Integer.parseInt(current);
		}
		PageInfo<Dept> pageInfo = deptDao.findByPage(pageNum, 5);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		
		/*//调用M得到相关的数据
		List<Dept> deptlist = deptDao.ListAll();
		
		request.setAttribute("deptlist", deptlist);
		
		String jsonStr = new ObjectMapper().writeValueAsString(deptlist);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);*/
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
