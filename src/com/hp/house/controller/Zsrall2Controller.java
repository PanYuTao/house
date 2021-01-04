package com.hp.house.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.house.dao.Zsrall2Dao;
import com.hp.house.dao.impl.Zsrall2DaoImpl;
import com.hp.house.entity.House;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Zsrall2;



/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/Zsrall2Controller")
public class Zsrall2Controller extends HttpServlet {
	
	private Zsrall2Dao zsrall2Dao = new Zsrall2DaoImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		if (type != null) {
			switch (type) {
			case "list":
				list(request, response);
				break;
			case "add":
				add(request, response);
				break;
			case "toUpdate":
				toUpdate(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			default:
				break;
			}
		} else {
			response.sendRedirect("wq.jsp");
		}
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");	
		int pageNum= 1;
		if(current!=null) 
			pageNum=Integer.parseInt(request.getParameter("current"));
		int sid = Integer.parseInt(request.getParameter("sid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		int hid = Integer.parseInt(request.getParameter("hid"));
		
		Zsrall2 zsrall2 = new Zsrall2();
		zsrall2.setSid(sid);zsrall2.setAid(aid);zsrall2.setHid(hid);
		
		PageInfo<Zsrall2> pageInfo = zsrall2Dao.findByCondition(pageNum , 5, zsrall2);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
