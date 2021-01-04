package com.donkee.house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donkee.house.dao.SortDao;
import com.donkee.house.dao.impl.SortDaoImpl;
import com.donkee.house.entity.Sort;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class SortServlet
 */
@WebServlet("/SortController")
public class SortController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SortDao sortDao = new SortDaoImpl();
    
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
				case "listAll":
					listAll(request, response);
					break;
			}
		}else {
			response.setCharacterEncoding("GBK");
			response.getWriter().write("你没有传type参数");
			//response.sendRedirect("error/errorPara.html");
		}
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if(current!=null)
			pageNum = Integer.parseInt(current);
		PageInfo<Sort> pageInfo = sortDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		
		/*List<Sort> list = sortDao.listAll();
		request.setAttribute("sortList", list);
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);*/
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Sort> list = sortDao.listAll();
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/sort/sort_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sname = request.getParameter("sort.sname");
		Sort sort = new Sort();
		sort.setSname(sname);
		int count = sortDao.save(sort);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("sid"));
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//乱码方法:提交方式为get
		int count = sortDao.delete(sid);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("sid"));
		Sort sort = sortDao.findById(sid);
		String jsonStr = new ObjectMapper().writeValueAsString(sort);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("sort.sid"));
		String sname = request.getParameter("sort.sname");
		Sort sort = new Sort();
		sort.setSid(sid);
		sort.setSname(sname);
		int count = sortDao.update(sort);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
