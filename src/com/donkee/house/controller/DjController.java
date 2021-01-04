package com.donkee.house.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.donkee.house.dao.DjDao;
import com.donkee.house.dao.impl.DjDaoImpl;
import com.donkee.house.entity.Dj;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/DjController")
public class DjController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DjDao djDao = new DjDaoImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//���뷽��:�ύ��ʽΪpost,д�����ݽ���֮ǰ
		//URL���θ�ʽ  /DeptServlet?type=list&pid=1
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
			response.getWriter().write("��û�д�type����");
			//response.sendRedirect("error/errorPara.html");
		}
	}
	//��Ӧ�ͻ��˵�ajax���󣬽��������response��Ӧ����
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if(current!=null)
			pageNum = Integer.parseInt(current);
		PageInfo<Dj> pageInfo = djDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Dj> list = djDao.listAll();
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int hid = Integer.parseInt(request.getParameter("hid"));
		double myj = Double.parseDouble(request.getParameter("myj"));
		double myzj = Double.parseDouble(request.getParameter("myzj"));
		String mbegintime = request.getParameter("time");
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		int eid = Integer.parseInt(request.getSession().getAttribute("eid").toString());
		Dj dj = new Dj();
		dj.setEid(eid);
		dj.setCid(cid);
		dj.setHid(hid);
		dj.setMyj(myj);
		dj.setMyzj(myzj);
		dj.setMdate(dateFormat.format(date));
		dj.setMflag(0);
		dj.setMimg(null);
		dj.setMbegintime(mbegintime);
		int count = djDao.save(dj);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mid = Integer.parseInt(request.getParameter("mid"));
		int count = djDao.delete(mid);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mid = Integer.parseInt(request.getParameter("mid"));	
		Dj dj = djDao.findById(mid);
		String jsonStr = new ObjectMapper().writeValueAsString(dj);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> fileList = new ArrayList<FileItem>();
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		String targetPath = this.getServletContext().getRealPath("upload")+"/";
		String fileName;
		String mimg = "NULL";
		Map<String, String> paramsMap = new HashMap<String, String>();
		for (FileItem item : fileList) {
			if (!item.isFormField()) {
				fileName = item.getName();
				mimg="upload/"+fileName;
				File saveFile = new File(targetPath + fileName); 
				try {
					item.write(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}		
			} else {
				paramsMap.put(item.getFieldName(), item.getString());
			}
		}
		String mbegintime = paramsMap.get("dj.mbegintime");	
		int cid = Integer.parseInt(paramsMap.get("dj.cid"));
		int hid = Integer.parseInt(paramsMap.get("dj.hid"));
		double myj = Double.parseDouble(paramsMap.get("dj.myj"));
		//double myj = Double.parseDouble(paramsMap.get("dj.myj"));
		double myzj = Double.parseDouble(paramsMap.get("dj.myzj"));	
		int mid = Integer.parseInt(paramsMap.get("dj.mid"));
		Dj dj = new Dj();
		dj.setMid(mid);
		dj.setCid(cid);
		dj.setHid(hid);
		dj.setMimg(mimg);
		dj.setMyj(myj);
		dj.setMyzj(myzj);
		dj.setMbegintime(mbegintime);
		int count = djDao.update(dj);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
