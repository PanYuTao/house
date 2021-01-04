package com.hp.house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.house.dao.SortDao;
import com.hp.house.dao.impl.SortDaoImpl;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Sort;

/**
 * Servlet implementation class JsController
 */
@WebServlet("/SortController")
public class SortController extends HttpServlet {

	private SortDao sortDao = new SortDaoImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if (type != null) {
			switch (type) {
			case "list":
				list(request, response);
				break;
			case "listAll":
				listAll(request, response);
				break;
			case "add":
				add(request, response);
				break;
			case "del":
				del(request, response);
				break;
			case "toUpdate":
				toUpdate(request, response);
				break;
			case "update":
				update(request, response);
				break;
			}
		} else {
			System.out.println(response.getCharacterEncoding());
			response.setCharacterEncoding("gbk");
			response.getWriter().write("type无值");
		}
	}


	protected void listAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Sort> list = sortDao.ListAll();
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sidStr = request.getParameter("sort.sid");
		int sid = Integer.parseInt(sidStr);
		String sname = request.getParameter("sort.sname");

		Sort sort = new Sort();
		sort.setSid(sid);
		sort.setSname(sname);
		int i = sortDao.update(sort);

		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sidStr = request.getParameter("sid");
		int sid = Integer.parseInt(sidStr);

		Sort sort = sortDao.selectSortById(sid);

		String jsonStr = new ObjectMapper().writeValueAsString(sort);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sidStr = request.getParameter("sid");
		int sid = Integer.parseInt(sidStr);

		int del = sortDao.del(sid);

		String jsonStr = new ObjectMapper().writeValueAsString(del);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("sort.sname");

		int save = sortDao.save(name);

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
		PageInfo<Sort> pageInfo = sortDao.findByPage(pageNum, 5);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);

		/*
		 * //调用M得到相关的数据 List<Sort> sortlist = sortDao.ListAll();
		 * 
		 * request.setAttribute("sortlist", sortlist);
		 * 
		 * String jsonStr = new ObjectMapper().writeValueAsString(sortlist);
		 * response.setCharacterEncoding("utf-8"); response.getWriter().write(jsonStr);
		 */
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
