package com.donkee.house.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.donkee.house.dao.ZhichuDao;
import com.donkee.house.dao.impl.ZhichuDaoImpl;
import com.donkee.house.entity.Zhichu;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class BiaoController
 */
@WebServlet("/ZhichuController")
public class ZhichuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ZhichuDao zhichuDao = new ZhichuDaoImpl();
       
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
		PageInfo<Zhichu> pageInfo = zhichuDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eid = Integer.parseInt(request.getParameter("zc.eid"));
		double zmoney = Double.parseDouble(request.getParameter("zc.zmoney"));
		String ztm = request.getParameter("zc.ztm");
		String zremark = request.getParameter("zc.zremark");
		Date date =new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Zhichu zc = new Zhichu();
		zc.setEid(eid);
		zc.setZmoney(zmoney);
		zc.setZtm(ztm);
		zc.setZremark(zremark);
		zc.setZtime(dateFormat.format(date));
		int count = zhichuDao.save(zc);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int zid = Integer.parseInt(request.getParameter("zid"));
		int count = zhichuDao.delete(zid);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int zid = Integer.parseInt(request.getParameter("zid"));	
		Zhichu zc = zhichuDao.findById(zid);
		String jsonStr = new ObjectMapper().writeValueAsString(zc);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int zid = Integer.parseInt(request.getParameter("zc.zid"));
		int eid = Integer.parseInt(request.getParameter("zc.eid"));
		double zmoney = Double.parseDouble(request.getParameter("zc.zmoney"));
		String ztm = request.getParameter("zc.ztm");
		String zremark = request.getParameter("zc.zremark");
		Zhichu zc = new Zhichu();
		zc.setZid(zid);
		zc.setEid(eid);
		zc.setZmoney(zmoney);
		zc.setZtm(ztm);
		zc.setZremark(zremark);
		int count = zhichuDao.update(zc);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
