package com.hp.house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.house.dao.DeptDao;
import com.hp.house.dao.JsDao;
import com.hp.house.dao.impl.DeptDaoImpl;
import com.hp.house.dao.impl.JsDaoImpl;
import com.hp.house.entity.Dept;
import com.hp.house.entity.Js;
import com.hp.house.entity.PageInfo;

/**
 * Servlet implementation class Js_all
 */
public class JsController extends HttpServlet {

	private JsDao jsDao = new JsDaoImpl();
	
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
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jidStr = request.getParameter("jid");
		int jid = Integer.parseInt(jidStr);
		
		int js = jsDao.del(jid);
		String jsonStr = new ObjectMapper().writeValueAsString(js);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Js> jsList = jsDao.ListAll();
		String jsonStr = new ObjectMapper().writeValueAsString(jsList);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jidStr = request.getParameter("jid");
		int jid = Integer.parseInt(jidStr);
		
		Js js = jsDao.selectJsById(jid);
		String jsonStr = new ObjectMapper().writeValueAsString(js);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jidStr = request.getParameter("js.jid");
		String jname = request.getParameter("js.jname");
		int jid = Integer.parseInt(jidStr);
		
		Js js = new Js();
		js.setJid(jid);
		js.setJname(jname);
		int i = jsDao.update(js);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("js.jname");
		
		int save = jsDao.save(name);
		
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
		PageInfo<Js> pageInfo = jsDao.findByPage(pageNum, 5);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		/*//调用M得到相关的数据
		List<Js> jslist = jsDao.ListAll();
		
		request.setAttribute("jslist", jslist);
		
		String jsonStr = new ObjectMapper().writeValueAsString(jslist);
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
