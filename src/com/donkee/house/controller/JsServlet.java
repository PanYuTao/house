package com.donkee.house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donkee.house.dao.JsDao;
import com.donkee.house.dao.impl.JsDaoImpl;
import com.donkee.house.entity.Js;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/JsServlet")
public class JsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private JsDao jsDao = new JsDaoImpl();
       
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
		//调用M得到相关的数据
		List<Js> list = jsDao.listAll();
		//保存数据(页面之间数据传递) request session application cookie url重写
		//作用域通信对象request session application,使用方式是一样的
		request.setAttribute("jsList", list);//setAttribute保存数据
		//List<Js> rdList = (List<Js>) request.getAttribute("jsList"); //getAttribute读取数据
		//跳转到jsp页面显示数据
		request.getRequestDispatcher("WEB-INF/jsp/js2/js_list.jsp").forward(request, response);
		//服务器将jsp页面数据响应给客户端
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/js/js_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jname = request.getParameter("name");
		Js js = new Js();
		js.setJname(jname);
		int count = jsDao.save(js);
		response.sendRedirect("JsServlet?type=list");//重定向,增删改之后,使用重定向更新数据的显示
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jid = Integer.parseInt(request.getParameter("id"));
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//乱码方法:提交方式为get
		int count = jsDao.delete(jid);
		response.sendRedirect("JsServlet?type=list");//重定向,增删改之后,使用重定向更新数据的显示
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jid = Integer.parseInt(request.getParameter("id"));
		Js js = jsDao.findById(jid);
		request.setAttribute("js", js);
		request.getRequestDispatcher("WEB-INF/jsp/js/js_update.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jid = Integer.parseInt(request.getParameter("id"));
		String jname = request.getParameter("name");
		Js js = new Js();
		js.setJid(jid);
		js.setJname(jname);
		int count = jsDao.update(js);
		response.sendRedirect("JsServlet?type=list");//重定向,增删改之后,使用重定向更新数据的显示
	}

}
