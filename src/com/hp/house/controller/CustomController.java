package com.hp.house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.house.dao.CustomDao;
import com.hp.house.dao.impl.CustomDaoImpl;
import com.hp.house.entity.Area;
import com.hp.house.entity.Custom;
import com.hp.house.entity.PageInfo;

/**
 * Servlet implementation class CustomController
 */
public class CustomController extends HttpServlet {
	
	private CustomDao customDao = new CustomDaoImpl();
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
				case "toUpdate":
					toUpdate(request,response);	
					break;
				case "update":
					update(request,response);	
					break;
			}
		}else {
			System.out.println(response.getCharacterEncoding());
			response.setCharacterEncoding("gbk");
			response.getWriter().write("type无值");
		}
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//读取参数的值
		String didStr = request.getParameter("cid");
		Integer cid = Integer.parseInt(didStr);
		Custom mycus = customDao.selectById(cid);
		response.setCharacterEncoding("utf-8");
		String jsonStr = new ObjectMapper().writeValueAsString(mycus);
		response.getWriter().write(jsonStr);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cidStr = request.getParameter("cus.cid");
		Integer cid = Integer.parseInt(cidStr);
		String cname = request.getParameter("cus.cname");
		String csex = request.getParameter("cus.csex");	
		String ctel = request.getParameter("cus.ctel");
		String ctel1 = request.getParameter("cus.ctel1");
		String ccard = request.getParameter("cus.ccard");
		
		Custom mycus = new Custom();
		mycus.setCname(cname);
		mycus.setCsex(csex);
		mycus.setCtel(ctel);
		mycus.setCtel1(ctel1);
		mycus.setCcard(ccard);
		mycus.setCid(cid);
		int i = customDao.update(mycus);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.getWriter().write(jsonStr);
	}
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cidStr = request.getParameter("cid");
		int cid = Integer.parseInt(cidStr);
		
		
		int i = customDao.del(cid);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("cus.cname");
		String csex = request.getParameter("cus.csex");
		String ctel = request.getParameter("cus.ctel");
		String ctel1 = request.getParameter("cus.ctel1");
		String ccard = request.getParameter("cus.ccard");
		
		Custom custom = new Custom(cname, csex, ctel, ctel1, ccard);
		
		int i = customDao.save(custom);
		
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		
		/*String name = request.getParameter("area.aname");
		
		Area area = new Area();
		area.setAname(name);
		int save = areaDao.save(area);
		
		String jsonStr = new ObjectMapper().writeValueAsString(save);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);*/
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Custom> mycusList = customDao.ListAll();
		String jsonStr = new ObjectMapper().writeValueAsString(mycusList);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if (current != null) {
			pageNum = Integer.parseInt(current);
		}
		PageInfo<Custom> pageInfo = customDao.findByPage(pageNum, 5);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
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
