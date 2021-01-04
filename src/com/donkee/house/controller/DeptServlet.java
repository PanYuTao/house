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

import com.donkee.house.dao.DeptDao;
import com.donkee.house.dao.impl.DeptDaoImpl;
import com.donkee.house.entity.Dept;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/DeptServlet")
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptDao deptDao = new DeptDaoImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//乱码方法:提交方式为post,写在数据接收之前
		//URL传参格式  /DeptServlet?type=list&pid=1
		String type = request.getParameter("type");
		if (type!=null) {
			switch (type) {
				case "list":
					list(request, response);
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
			}
		}else {
			response.setCharacterEncoding("GBK");
			response.getWriter().write("你没有传type参数");
			//response.sendRedirect("error/errorPara.html");
		}
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用M得到相关的数据
		List<Dept> list = deptDao.listAll();
		//保存数据(页面之间数据传递) request session application cookie url重写
		//作用域通信对象request session application,使用方式是一样的
		//session:是一次会话    request:是一次请求    application:从服务器启动到给关闭 
		request.setAttribute("deptList", list);//setAttribute保存数据
	//	HttpSession session = request.getSession();//getSession:创建/引用当前会话
	//	ServletContext application = request.getServletContext();
		//跳转到jsp页面显示数据
		request.getRequestDispatcher("WEB-INF/jsp/dept2/dept_list.jsp").forward(request, response);
		//服务器将jsp页面数据响应给客户端
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("name");
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//乱码方法:提交方式为get
		String premark = request.getParameter("remark");
		Dept dept = new Dept(pname, 0, premark);
		int count = deptDao.save(dept);
		response.sendRedirect("DeptServlet?type=list");//重定向,增删改之后,使用重定向更新数据的显示
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("id"));
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//乱码方法:提交方式为get
		int count = deptDao.delete(pid);//1
		response.sendRedirect("DeptServlet?type=list");//重定向,增删改之后,使用重定向更新数据的显示
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//读取参数的值//数据类型转换
		//Double.parseDouble(str),字符串转double
		//new SimpleDateFormat("yyyy-MM-dd").parse(dateStr) 字符串转java.util.Date
		int pid = Integer.parseInt(request.getParameter("id"));	
		Dept dept = deptDao.findById(pid);
		request.setAttribute("dept", dept);
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_update.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("id"));
		String pname = request.getParameter("name");
		int pflag = Integer.parseInt(request.getParameter("flag"));
		String premark = request.getParameter("remark");
		Dept dept = new Dept();
		dept.setPid(pid);
		dept.setPname(pname);
		dept.setPflag(pflag);
		dept.setPremark(premark);
		int count = deptDao.update(dept);//1
		response.sendRedirect("DeptServlet?type=list");//重定向,增删改之后,使用重定向更新数据的显示
	}

}
