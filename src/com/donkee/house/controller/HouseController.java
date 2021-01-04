package com.donkee.house.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.text.SimpleDateFormat;
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

import com.donkee.house.dao.HouseDao;
import com.donkee.house.dao.impl.HouseDaoImpl;
import com.donkee.house.entity.House;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class HouseController
 */
@WebServlet("/HouseController")
public class HouseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HouseDao houseDao = new HouseDaoImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//乱码方法:提交方式为post,写在数据接收之前
		//URL传参格式  /DeptServlet?type=list&pid=1
		String type = request.getParameter("type");
		if (type!=null) {
			switch (type) {
				case "list":
					list(request, response);
					break;
				case "list1":
					list1(request, response);
					break;
				case "list2":
					list2(request, response);
					break;
				case "listAll":
					listAll(request, response);
					break;
				case "listArea":
					listArea(request, response);
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
		PageInfo<House> pageInfo = houseDao.findByPage(pageNum, 3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void list1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int aid = Integer.parseInt(request.getParameter("aid"));
		int sid = Integer.parseInt(request.getParameter("sid"));
		String hflag = request.getParameter("zt");
		int pageNum = 1;
		if(current!=null)
			pageNum = Integer.parseInt(current);
		House house = new House();
		house.setAid(aid);
		house.setSid(sid);
		house.setHflag(hflag);
		PageInfo<House> pageInfo = houseDao.findByCondition(pageNum, 3, house);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void list2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if(current!=null)
			pageNum = Integer.parseInt(current);
		int aid = Integer.parseInt(request.getParameter("aid"));
		int sid = Integer.parseInt(request.getParameter("sid"));
		int hid = Integer.parseInt(request.getParameter("hid"));
		House house = new House();
		house.setAid(aid);
		house.setSid(sid);
		house.setHid(hid);
		PageInfo<House> pageInfo = houseDao.findByCondition(pageNum, 3, house);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<House> list = houseDao.listAll();
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void listArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		List<House> list = houseDao.listArea(aid);
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String himg = "";
		Map<String, String> paramsMap = new HashMap<String, String>();
		for (FileItem item : fileList) {
			if (!item.isFormField()) {
				fileName = item.getName();
				himg+="upload/"+fileName+"、";
				File saveFile = new File(targetPath + fileName); 
				try {
					item.write(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}		
			} else {
				paramsMap.put(item.getFieldName(), item.getString("utf-8"));
			}
		}
		himg = himg.substring(0,himg.lastIndexOf("、"));
		int sid = Integer.parseInt(paramsMap.get("house.sid"));
		int aid = Integer.parseInt(paramsMap.get("house.aid"));
		String haddress = paramsMap.get("house.haddress");
		String hfh = paramsMap.get("house.hfh");
		String hhx = paramsMap.get("house.hhx");
		String hmj = paramsMap.get("house.hmj");
		String hcx = paramsMap.get("house.hcx");
		double hmoney = Double.parseDouble(paramsMap.get("house.hmoney"));
		double hwf = Double.parseDouble(paramsMap.get("house.hwf"));
		double hdx = Double.parseDouble(paramsMap.get("house.hdx"));
		double hsf = Double.parseDouble(paramsMap.get("house.hsf"));
		double hmq = Double.parseDouble(paramsMap.get("house.hmq"));
		double dkd = Double.parseDouble(paramsMap.get("house.dkd"));
		double skd = Double.parseDouble(paramsMap.get("house.skd"));
		double mkd = Double.parseDouble(paramsMap.get("house.mkd"));
		String hjp = paramsMap.get("house.hjp");
		String hremark = paramsMap.get("house.hremark");
		String hflag = "0";
		House house = new House();
		house.setSid(sid);
		house.setAid(aid);
		house.setHaddress(haddress);
		house.setHfh(hfh);
		house.setHhx(hhx);
		house.setHmj(hmj);
		house.setHcx(hcx);
		house.setHmoney(hmoney);
		house.setHwf(hwf);
		house.setHdx(hdx);
		house.setHsf(hsf);
		house.setHmq(hmq);
		house.setDkd(dkd);
		house.setSkd(skd);
		house.setMkd(mkd);
		house.setHjp(hjp);
		house.setHremark(hremark);
		house.setHimg(himg);
		house.setHflag(hflag);
		int count = houseDao.save(house);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid = Integer.parseInt(request.getParameter("hid"));
		int count = houseDao.delete(hid);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid = Integer.parseInt(request.getParameter("hid"));
		House house = houseDao.findById(hid);
		String jsonStr = new ObjectMapper().writeValueAsString(house);
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
		String himg = "";
		Map<String, String> paramsMap = new HashMap<String, String>();
		for (FileItem item : fileList) {
			if (!item.isFormField()) {
				fileName = item.getName();
				himg+="upload/"+fileName+"、";
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
		himg = himg.substring(0,himg.lastIndexOf("、"));
		int hid = Integer.parseInt(paramsMap.get("house.hid"));
		int sid = Integer.parseInt(paramsMap.get("house.sid"));
		int aid = Integer.parseInt(paramsMap.get("house.aid"));
		String haddress = paramsMap.get("house.haddress");
		String hfh = paramsMap.get("house.hfh");
		String hhx = paramsMap.get("house.hhx");
		String hmj = paramsMap.get("house.hmj");
		String hcx = paramsMap.get("house.hcx");
		double hmoney = Double.parseDouble(paramsMap.get("house.hmoney"));
		double hwf = Double.parseDouble(paramsMap.get("house.hwf"));
		double hdx = Double.parseDouble(paramsMap.get("house.hdx"));
		double hsf = Double.parseDouble(paramsMap.get("house.hsf"));
		double hmq = Double.parseDouble(paramsMap.get("house.hmq"));
		double dkd = Double.parseDouble(paramsMap.get("house.dkd"));
		double skd = Double.parseDouble(paramsMap.get("house.skd"));
		double mkd = Double.parseDouble(paramsMap.get("house.mkd"));
		String hjp = paramsMap.get("house.hjp");
		String hremark = paramsMap.get("house.hremark");
		House house = new House();
		house.setHid(hid);
		house.setSid(sid);
		house.setAid(aid);
		house.setHaddress(haddress);
		house.setHfh(hfh);
		house.setHhx(hhx);
		house.setHmj(hmj);
		house.setHcx(hcx);
		house.setHmoney(hmoney);
		house.setHwf(hwf);
		house.setHdx(hdx);
		house.setHsf(hsf);
		house.setHmq(hmq);
		house.setDkd(dkd);
		house.setSkd(skd);
		house.setMkd(mkd);
		house.setHjp(hjp);
		house.setHremark(hremark);
		house.setHimg(himg);
		int count = houseDao.update(house);//1
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
