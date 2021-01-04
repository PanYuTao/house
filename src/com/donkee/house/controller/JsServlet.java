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
		request.setCharacterEncoding("utf-8");//���뷽��:�ύ��ʽΪpost,д�����ݽ���֮ǰ
		//URL���θ�ʽ  /DeptServlet?type=list&pid=1
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
			response.getWriter().write("��û�д�type����");
			//response.sendRedirect("error/errorPara.html");
		}
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����M�õ���ص�����
		List<Js> list = jsDao.listAll();
		//��������(ҳ��֮�����ݴ���) request session application cookie url��д
		//������ͨ�Ŷ���request session application,ʹ�÷�ʽ��һ����
		request.setAttribute("jsList", list);//setAttribute��������
		//List<Js> rdList = (List<Js>) request.getAttribute("jsList"); //getAttribute��ȡ����
		//��ת��jspҳ����ʾ����
		request.getRequestDispatcher("WEB-INF/jsp/js2/js_list.jsp").forward(request, response);
		//��������jspҳ��������Ӧ���ͻ���
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/js/js_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jname = request.getParameter("name");
		Js js = new Js();
		js.setJname(jname);
		int count = jsDao.save(js);
		response.sendRedirect("JsServlet?type=list");//�ض���,��ɾ��֮��,ʹ���ض���������ݵ���ʾ
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jid = Integer.parseInt(request.getParameter("id"));
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//���뷽��:�ύ��ʽΪget
		int count = jsDao.delete(jid);
		response.sendRedirect("JsServlet?type=list");//�ض���,��ɾ��֮��,ʹ���ض���������ݵ���ʾ
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
		response.sendRedirect("JsServlet?type=list");//�ض���,��ɾ��֮��,ʹ���ض���������ݵ���ʾ
	}

}
