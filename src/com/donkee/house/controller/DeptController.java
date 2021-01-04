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
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/DeptController")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptDao deptDao = new DeptDaoImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//���뷽��:�ύ��ʽΪpost,д�����ݽ���֮ǰ
		//URL���θ�ʽ  /DeptServlet?type=list&pid=1
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
				case "listAll":
					listAll(request, response);
					break;
			}
		}else {
			response.setCharacterEncoding("GBK");
			response.getWriter().write("��û�д�type����");
			//response.sendRedirect("error/errorPara.html");
		}
	}
	//��Ӧ�ͻ��˵�ajax���󣬽��������response��Ӧ����
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if(current!=null)
			pageNum = Integer.parseInt(current);
		PageInfo<Dept> pageInfo = deptDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		/*List<Dept> list = deptDao.listAll(); 
		//ʹ��jackson���߽�java����ת���ɷ���json��ʽ(����[],����{})���ַ���
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);*/
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Dept> list = deptDao.listAll(); 
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("dept.pname");
		String premark = request.getParameter("dept.premark");
		Dept dept = new Dept(pname, 0, premark);
		int count = deptDao.save(dept);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//���뷽��:�ύ��ʽΪget
		int count = deptDao.delete(pid);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("pid"));
		int pid = Integer.parseInt(request.getParameter("pid"));	
		Dept dept = deptDao.findById(pid);
		String jsonStr = new ObjectMapper().writeValueAsString(dept);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("dept.pid"));
		String pname = request.getParameter("dept.pname");
		int pflag = Integer.parseInt(request.getParameter("dept.pflag"));
		String premark = request.getParameter("dept.premark");
		Dept dept = new Dept();
		dept.setPid(pid);
		dept.setPname(pname);
		dept.setPflag(pflag);
		dept.setPremark(premark);
		int count = deptDao.update(dept);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
