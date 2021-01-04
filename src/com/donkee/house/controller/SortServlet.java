package com.donkee.house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donkee.house.dao.SortDao;
import com.donkee.house.dao.impl.SortDaoImpl;
import com.donkee.house.entity.Sort;

/**
 * Servlet implementation class SortServlet
 */
@WebServlet("/SortServlet")
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SortDao sortDao = new SortDaoImpl();
    
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
		List<Sort> list = sortDao.listAll();
		//��������(ҳ��֮�����ݴ���) request session application cookie url��д
		//������ͨ�Ŷ���request session application,ʹ�÷�ʽ��һ����
		request.setAttribute("sortList", list);//setAttribute��������
		//List<Sort> rdList = (List<Sort>) request.getAttribute("sortList"); //getAttribute��ȡ����
		//��ת��jspҳ����ʾ����
		request.getRequestDispatcher("WEB-INF/jsp/sort2/sort_list.jsp").forward(request, response);
		//��������jspҳ��������Ӧ���ͻ���
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/sort/sort_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sname = request.getParameter("name");
		Sort sort = new Sort();
		sort.setSname(sname);
		int count = sortDao.save(sort);
		response.sendRedirect("SortServlet?type=list");//�ض���,��ɾ��֮��,ʹ���ض���������ݵ���ʾ
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("id"));
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//���뷽��:�ύ��ʽΪget
		int count = sortDao.delete(sid);
		response.sendRedirect("SortServlet?type=list");//�ض���,��ɾ��֮��,ʹ���ض���������ݵ���ʾ
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("id"));
		Sort sort = sortDao.findById(sid);
		request.setAttribute("sort", sort);
		request.getRequestDispatcher("WEB-INF/jsp/sort/sort_update.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("id"));
		String sname = request.getParameter("name");
		Sort sort = new Sort();
		sort.setSid(sid);
		sort.setSname(sname);
		int count = sortDao.update(sort);
		response.sendRedirect("SortServlet?type=list");//�ض���,��ɾ��֮��,ʹ���ض���������ݵ���ʾ
	}

}
