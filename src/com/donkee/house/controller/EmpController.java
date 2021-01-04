package com.donkee.house.controller;

import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.List;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.donkee.house.dao.EmpDao;
import com.donkee.house.dao.impl.EmpDaoImpl;
import com.donkee.house.entity.Emp;
import com.donkee.house.util.MD5;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/EmpController")
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpDao empDao = new EmpDaoImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//乱码方法:提交方式为post,写在数据接收之前
		//URL传参格式  /DeptServlet?type=list&pid=1
		String type = request.getParameter("type");
		if (type!=null) {
			switch (type) {
				case "list":
					list(request, response);
					break;
				case "listAll":
					listAll(request, response);
					break;
				case "toAdd":
					toAdd(request, response);
					break;
				case "add":
					add(request, response);
					break;
				case "delete":
					delete(request, response);
					break;
				case "toUpdate":
					toUpdate(request, response);
					break;
				case "update":
					update(request, response);
					break;
				case "login":
					login(request, response);
					break;
				
			}
		}else {
			response.setCharacterEncoding("GBK");
			response.getWriter().write("你没有传type参数");
			//response.sendRedirect("error/errorPara.html");
		}
	}
	//响应客户端的ajax请求，将数据添加response响应流中
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if(current!=null)
			pageNum = Integer.parseInt(current);
		PageInfo<Emp> pageInfo = empDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Emp> list = empDao.listAll();
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jid = Integer.parseInt(request.getParameter("emp.myjs.jid"));
		int pid = Integer.parseInt(request.getParameter("emp.mydept.pid"));
		String ename = request.getParameter("emp.ename");
		String epsw = request.getParameter("emp.epsw");
		String erealname = request.getParameter("emp.erealname");
		String etel = request.getParameter("emp.etel");
		String eaddress = request.getParameter("emp.eaddress");
		String eremark = request.getParameter("emp.eremark");
		Emp emp = new Emp();
		emp.setJid(jid);
		emp.setPid(pid);
		emp.setEname(ename);
		emp.setEpsw(epsw);
		emp.setErealname(erealname);
		emp.setEtel(etel);
		emp.setEaddress(eaddress);
		emp.setEremark(eremark);
		emp.setEflag(0);
		int count = empDao.save(emp);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eid = Integer.parseInt(request.getParameter("eid"));
		int count = empDao.delete(eid);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eid = Integer.parseInt(request.getParameter("eid"));	
		Emp emp = empDao.findById(eid);
		String jsonStr = new ObjectMapper().writeValueAsString(emp);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eid = Integer.parseInt(request.getParameter("emp.eid"));
		int jid = Integer.parseInt(request.getParameter("emp.myjs.jid"));
		int pid = Integer.parseInt(request.getParameter("emp.mydept.pid"));
		String ename = request.getParameter("emp.ename");
		String epsw = request.getParameter("emp.epsw");
		String erealname = request.getParameter("emp.erealname");
		String etel = request.getParameter("emp.etel");
		String eaddress = request.getParameter("emp.eaddress");
		String eremark = request.getParameter("emp.eremark");
		int eflag = Integer.parseInt(request.getParameter("emp.eflag"));
		Emp emp = new Emp();
		emp.setJid(jid);
		emp.setPid(pid);
		emp.setEname(ename);
		emp.setEpsw(epsw);
		emp.setErealname(erealname);
		emp.setEtel(etel);
		emp.setEaddress(eaddress);
		emp.setEremark(eremark);
		emp.setEid(eid);
		emp.setEflag(eflag);
		int count = empDao.update(emp);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ename = request.getParameter("emp.ename");	
		String epsw = request.getParameter("emp.epsw");//用户输入的密码
		//String md5Epsw = MD5.MD5Encode(epsw);
		Emp loginEmp = empDao.findByName(ename);
		int mydata=0;
		if (loginEmp==null || !loginEmp.getEpsw().equals(epsw)) {
			mydata=0;
		}else {
			mydata=1;
			request.getSession().setAttribute("eid", loginEmp.getEid());
			request.getSession().setAttribute("loginUser", loginEmp);
		}
		String jsonStr = new ObjectMapper().writeValueAsString(mydata);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
