package com.hp.house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.house.dao.AreaDao;
import com.hp.house.dao.impl.AreaDaoImpl;
import com.hp.house.entity.Area;
import com.hp.house.entity.PageInfo;

/**
 * Servlet implementation class AreaController
 */
public class AreaController extends HttpServlet {
	
	private AreaDao areaDao = new AreaDaoImpl();
	
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
				case "del" :
					del(request,response);
					break;
				case "toUpdate" :
					toUpdate(request,response);
					break;
				case "update" :
					update(request,response);
					break;
			}
		}else {
			response.setCharacterEncoding("gbk");
			response.getWriter().write("type无值");
		}
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Area> list = areaDao.ListAll();
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aidStr = request.getParameter("area.aid");
		String aname = request.getParameter("area.aname");
		int aid = Integer.parseInt(aidStr);
		
		Area area = new Area();
		area.setAid(aid);
		area.setAname(aname);
		int i = areaDao.update(area);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aidStr = request.getParameter("aid");
		int aid = Integer.parseInt(aidStr);
		
		Area area = areaDao.selectById(aid);
		String jsonStr = new ObjectMapper().writeValueAsString(area);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aidStr = request.getParameter("aid");
		int aid = Integer.parseInt(aidStr);
		
		int i = areaDao.del(aid);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("area.aname");
		
		Area area = new Area();
		area.setAname(name);
		int save = areaDao.save(area);
		
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
		PageInfo<Area> pageInfo = areaDao.findByPage(pageNum, 5);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		
		/*//调用M得到相关的数据
		List<Area> arealist = areaDao.ListAll();
		
		request.setAttribute("arealist", arealist);
		
		String jsonStr = new ObjectMapper().writeValueAsString(arealist);
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
