package com.hp.house.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.house.dao.EmpDao;
import com.hp.house.dao.impl.EmpDaoImpl;
import com.hp.house.entity.Emp;
import com.hp.house.entity.PageInfo;
import com.hp.house.utils.MD5;

/**
 * Servlet implementation class EmpController
 */
public class EmpController extends HttpServlet {
	
	private EmpDao empDao = new EmpDaoImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if(type != null) {
			switch(type) {
				case "list" :
					list(request,response);
					break;
				case "add" :
					add(request,response);
					break;
				case "del" :
					del(request,response);
					break;
				case "empById" :
					empById(request,response);
					break;
				case "update" :
					update(request,response);
					break;
				case "login" :
					login(request,response);
					break;
				
			}
		}else {
			System.out.println(response.getCharacterEncoding());
			response.setCharacterEncoding("gbk");
			response.getWriter().write("type无值");
		}
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jidStr = request.getParameter("emp.myjs.jid");
		String pidStr = request.getParameter("emp.mydept.pid");
		String ename = request.getParameter("emp.ename");
		String epsw = request.getParameter("emp.epsw");
		String erealname = request.getParameter("emp.erealname");
		String etel = request.getParameter("emp.etel");
		String eaddress = request.getParameter("emp.eaddress");
		String eremark = request.getParameter("emp.eremark");
		String eidStr = request.getParameter("emp.eid");
		
		int eid = Integer.parseInt(eidStr);
		int jid = Integer.parseInt(jidStr);
		int pid = Integer.parseInt(pidStr);
		Emp emp = new Emp(eid, pid, jid, ename, epsw, erealname, etel, eaddress, eremark);
		 
		int i = empDao.update(emp);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	protected void empById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eidStr = request.getParameter("eid");
		int eid = Integer.parseInt(eidStr);
		
		Emp emp = empDao.selectById(eid);
		String jsonStr = new ObjectMapper().writeValueAsString(emp);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eidStr = request.getParameter("eid");
		int eid = Integer.parseInt(eidStr);
		
		int i = empDao.del(eid);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jidStr = request.getParameter("emp.myjs.jid");
		String pidStr = request.getParameter("emp.mydept.pid");
		String ename = request.getParameter("emp.ename");
		String epsw = request.getParameter("emp.epsw");
		String erealname = request.getParameter("emp.erealname");
		String etel = request.getParameter("emp.etel");
		String eaddress = request.getParameter("emp.eaddress");
		String eremark = request.getParameter("emp.eremark");
		
		int jid = Integer.parseInt(jidStr);
		int pid = Integer.parseInt(pidStr);

		Emp emp = new Emp(pid, jid, ename, epsw, erealname, etel, eaddress, eremark);
		int i = empDao.save(emp);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if (current != null) {
			pageNum = Integer.parseInt(current);
		}
		PageInfo<Emp> pageInfo = empDao.findByPage(pageNum, 5);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ename = request.getParameter("emp.ename");
		String epsw = request.getParameter("emp.epsw");
		String md5Psw = MD5.MD5Encode(epsw);
		Emp emp = empDao.findByName(ename);
		int mydata = 0;
		if (emp == null || !emp.getEpsw().equals(md5Psw)) {
			mydata = 0;
		}else {
			mydata = 1;
			request.getSession().setAttribute("user", emp);
		}
		String jsonStr = new ObjectMapper().writeValueAsString(mydata);
		response.getWriter().write(jsonStr);
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
