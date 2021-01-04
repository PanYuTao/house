package com.donkee.house.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donkee.house.dao.HtDao;
import com.donkee.house.dao.impl.HtDaoImpl;
import com.donkee.house.entity.Ht;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class AreaController
 */
@WebServlet("/HtController")
public class HtController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HtDao htDao = new HtDaoImpl();
    
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
		String current = request.getParameter("current");
		int pageNum = 1;
		if(current!=null)
			pageNum = Integer.parseInt(current);
		PageInfo<Ht> pageInfo = htDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		//List<Ht> list = htDao.listAll();
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/sort/sort_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htname = request.getParameter("ht.htname");
		String htremark = request.getParameter("ht.htremark");
		Ht ht = new Ht();
		ht.setHtname(htname);
		ht.setHtremark(htremark);
		int count = htDao.save(ht);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int htid = Integer.parseInt(request.getParameter("htid"));
		int count = htDao.delete(htid);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int htid = Integer.parseInt(request.getParameter("id"));
		Ht ht = htDao.findById(htid);
		request.setAttribute("ht", ht);
		request.getRequestDispatcher("WEB-INF/jsp/sort/sort_update.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int htid = Integer.parseInt(request.getParameter("id"));
		String htname = request.getParameter("name");
		String htremark = request.getParameter("remark");
		Ht ht = new Ht();
		ht.setHtid(htid);
		ht.setHtname(htname);
		ht.setHtremark(htremark);
		int count = htDao.update(ht);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
