package com.donkee.house.controller;

import java.io.IOException;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
/*
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;*/

import com.donkee.house.dao.SfDao;
import com.donkee.house.dao.impl.SfDaoImpl;
import com.donkee.house.entity.Sf;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/SfController")
public class SfController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SfDao sfDao = new SfDaoImpl();
       
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
		PageInfo<Sf> pageInfo = sfDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid = Integer.parseInt(request.getParameter("house.hid"));
		//int eid = Integer.parseInt(request.getParameter("emp.eid"));
		int cid = Integer.parseInt(request.getParameter("cus.cid"));
		int mid = Integer.parseInt(request.getParameter("dj.mid"));
		double myzj = Double.parseDouble(request.getParameter("myzj"));
		String mbegintime = request.getParameter("mbegintime");
		int eid = Integer.parseInt(request.getSession().getAttribute("eid").toString());
		Sf sf = new Sf();
		sf.setHid(hid);
		sf.setMyzj(myzj);
		sf.setMid(mid);
		sf.setCid(cid);
		sf.setEid(eid);
		sf.setMbegintime(mbegintime);
		int count = sfDao.save(sf);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int yid = Integer.parseInt(request.getParameter("yid"));
		int count = sfDao.delete(yid);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int yid = Integer.parseInt(request.getParameter("yid"));
		Sf sf = sfDao.findById(yid);
		response.setCharacterEncoding("utf-8");
		String jsonStr = new ObjectMapper().writeValueAsString(sf);
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid = Integer.parseInt(request.getParameter("house.hid"));
		int eid = Integer.parseInt(request.getParameter("emp.eid"));
		int cid = Integer.parseInt(request.getParameter("cus.cid"));
		int mid = Integer.parseInt(request.getParameter("sf.mid"));
		double myzj = Double.parseDouble(request.getParameter("sf.myzj"));
		String mbegintime = request.getParameter("sf.mbegintime");
		int yid = Integer.parseInt(request.getParameter("sf.yid"));
		Sf sf = new Sf();
		sf.setMyzj(myzj);
		sf.setHid(hid);
		sf.setMid(mid);
		sf.setCid(cid);
		sf.setEid(eid);
		sf.setYid(yid);
		sf.setMbegintime(mbegintime);
		int count = sfDao.update(sf);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.getWriter().write(jsonStr);
	}

}
