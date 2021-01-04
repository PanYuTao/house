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

import com.donkee.house.dao.DeptDao;
import com.donkee.house.dao.impl.DeptDaoImpl;
import com.donkee.house.entity.Dept;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/DeptServlet")
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptDao deptDao = new DeptDaoImpl();
       
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
		List<Dept> list = deptDao.listAll();
		//��������(ҳ��֮�����ݴ���) request session application cookie url��д
		//������ͨ�Ŷ���request session application,ʹ�÷�ʽ��һ����
		//session:��һ�λỰ    request:��һ������    application:�ӷ��������������ر� 
		request.setAttribute("deptList", list);//setAttribute��������
	//	HttpSession session = request.getSession();//getSession:����/���õ�ǰ�Ự
	//	ServletContext application = request.getServletContext();
		//��ת��jspҳ����ʾ����
		request.getRequestDispatcher("WEB-INF/jsp/dept2/dept_list.jsp").forward(request, response);
		//��������jspҳ��������Ӧ���ͻ���
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("name");
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//���뷽��:�ύ��ʽΪget
		String premark = request.getParameter("remark");
		Dept dept = new Dept(pname, 0, premark);
		int count = deptDao.save(dept);
		response.sendRedirect("DeptServlet?type=list");//�ض���,��ɾ��֮��,ʹ���ض���������ݵ���ʾ
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("id"));
		//pname = new String(pname.getBytes("iso8858-1"),"utf-8");//���뷽��:�ύ��ʽΪget
		int count = deptDao.delete(pid);//1
		response.sendRedirect("DeptServlet?type=list");//�ض���,��ɾ��֮��,ʹ���ض���������ݵ���ʾ
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ������ֵ//��������ת��
		//Double.parseDouble(str),�ַ���תdouble
		//new SimpleDateFormat("yyyy-MM-dd").parse(dateStr) �ַ���תjava.util.Date
		int pid = Integer.parseInt(request.getParameter("id"));	
		Dept dept = deptDao.findById(pid);
		request.setAttribute("dept", dept);
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_update.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("id"));
		String pname = request.getParameter("name");
		int pflag = Integer.parseInt(request.getParameter("flag"));
		String premark = request.getParameter("remark");
		Dept dept = new Dept();
		dept.setPid(pid);
		dept.setPname(pname);
		dept.setPflag(pflag);
		dept.setPremark(premark);
		int count = deptDao.update(dept);//1
		response.sendRedirect("DeptServlet?type=list");//�ض���,��ɾ��֮��,ʹ���ض���������ݵ���ʾ
	}

}
