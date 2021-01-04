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

/**
 * Servlet implementation class SortServlet
 */
@WebServlet("/SortServlet")
public class SortServlet extends HttpServlet {
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
			}
		}else {
			response.setCharacterEncoding("GBK");
			response.getWriter().write("你没有传type参数");
			//response.sendRedirect("error/errorPara.html");
		}
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用M得到相关的数据
		List<Sort> list = sortDao.listAll();
		//保存数据(页面之间数据传递) request session application cookie url重写
		//作用域通信对象request session application,使用方式是一样的
		request.setAttribute("sortList", list);//setAttribute保存数据
		//List<Sort> rdList = (List<Sort>) request.getAttribute("sortList"); //getAttribute读取数据
		//跳转到jsp页面显示数据
		request.getRequestDispatcher("WEB-INF/jsp/sort2/sort_list.jsp").forward(request, response);
		//服务器将jsp页面数据响应给客户端
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/sort/sort_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sname = request.getParameter("name");
		Sort sort = new Sort();
		sort.setSname(sname);
		int count = sortDao.save(sort);
		response.sendRedirect("SortServlet?type=list");//重定向,增删改之后,使用重定向更新数据的显示
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("id"));
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//乱码方法:提交方式为get
		int count = sortDao.delete(sid);
		response.sendRedirect("SortServlet?type=list");//重定向,增删改之后,使用重定向更新数据的显示
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("id"));
		Sort sort = sortDao.findById(sid);
		request.setAttribute("sort", sort);
		request.getRequestDispatcher("WEB-INF/jsp/sort/sort_update.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("id"));
		String sname = request.getParameter("name");
		Sort sort = new Sort();
		sort.setSid(sid);
		sort.setSname(sname);
		int count = sortDao.update(sort);
		response.sendRedirect("SortServlet?type=list");//重定向,增删改之后,使用重定向更新数据的显示
	}

}
