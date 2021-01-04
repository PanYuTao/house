package com.donkee.house.controller;

import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.donkee.house.dao.BiaoDao;
import com.donkee.house.dao.impl.BiaoDaoImpl;
import com.donkee.house.entity.Biao;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class BiaoController
 */
@WebServlet("/BiaoController")
public class BiaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BiaoDao biaoDao = new BiaoDaoImpl();
       
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
	//响应客户端的ajax请求，将数据添加response响应流中
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if(current!=null)
			pageNum = Integer.parseInt(current);
		PageInfo<Biao> pageInfo = biaoDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid = Integer.parseInt(request.getParameter("biao.hid"));
		int eid = Integer.parseInt(request.getParameter("biao.eid"));
		double mkd = Double.parseDouble(request.getParameter("biao.mkd"));
		double dkd = Double.parseDouble(request.getParameter("biao.dkd"));
		double skd = Double.parseDouble(request.getParameter("biao.skd"));
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Biao biao = new Biao();
		biao.setHid(hid);
		biao.setMkd(mkd);
		biao.setSkd(skd);
		biao.setDkd(dkd);
		biao.setMtime(dateFormat.format(date));
		biao.setEid(eid);
		int count = biaoDao.save(biao);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("bid"));
		int count = biaoDao.delete(bid);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("bid"));	
		Biao biao = biaoDao.findById(bid);
		String jsonStr = new ObjectMapper().writeValueAsString(biao);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("biao.bid"));
		int hid = Integer.parseInt(request.getParameter("biao.hid"));
		int eid = Integer.parseInt(request.getParameter("biao.eid"));
		double mkd = Double.parseDouble(request.getParameter("biao.mkd"));
		double dkd = Double.parseDouble(request.getParameter("biao.dkd"));
		double skd = Double.parseDouble(request.getParameter("biao.skd"));
		Biao biao = new Biao();
		biao.setBid(bid);
		biao.setHid(hid);
		biao.setMkd(mkd);
		biao.setSkd(skd);
		biao.setDkd(dkd);
		biao.setEid(eid);
		int count = biaoDao.update(biao);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
