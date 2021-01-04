package com.donkee.house.controller;

import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.List;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.donkee.house.dao.CusDao;
import com.donkee.house.dao.impl.CusDaoImpl;
import com.donkee.house.entity.Cus;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/CusController")
public class CusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CusDao cusDao = new CusDaoImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//乱码方法:提交方式为post,写在数据接收之前
		//URL传参格式  /DeptServlet?type=list&pid=1
		String type = request.getParameter("type");
		if (type!=null) {
			switch (type) {
				case "list":
					list(request, response);
					break;
				case "listAll":
					listAll(request, response);
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
		PageInfo<Cus> pageInfo = cusDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cus> list = cusDao.listAll();
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("cus.cname");
		String csex = request.getParameter("cus.csex");
		String ctel = request.getParameter("cus.ctel");
		String ctel1 = request.getParameter("cus.ctel1");
		String ccard = request.getParameter("cus.ccard");
		Cus cus = new Cus();
		cus.setCname(cname);
		cus.setCsex(csex);
		cus.setCtel(ctel);
		cus.setCtel1(ctel1);
		cus.setCcard(ccard);
		int count = cusDao.save(cus);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int count = cusDao.delete(cid);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));	
		Cus cus = cusDao.findById(cid);
		String jsonStr = new ObjectMapper().writeValueAsString(cus);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cus.cid"));
		String cname = request.getParameter("cus.cname");
		String csex = request.getParameter("cus.csex");
		String ctel = request.getParameter("cus.ctel");
		String ctel1 = request.getParameter("cus.ctel1");
		String ccard = request.getParameter("cus.ccard");
		Cus cus = new Cus();
		cus.setCid(cid);
		cus.setCname(cname);
		cus.setCsex(csex);
		cus.setCtel(ctel);
		cus.setCtel1(ctel1);
		cus.setCcard(ccard);
		int count = cusDao.update(cus);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
