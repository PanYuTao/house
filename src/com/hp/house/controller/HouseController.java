package com.hp.house.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.house.dao.HouseDao;
import com.hp.house.dao.impl.HouseDaoImpl;
import com.hp.house.entity.House;
import com.hp.house.entity.PageInfo;

/**
 * Servlet implementation class DeptServlet
 */
public class HouseController extends HttpServlet {

	private HouseDao houseDao = new HouseDaoImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		if (type != null) {
			switch (type) {
			case "list":
				list(request, response);
				break;
			case "list1" :
				list1(request,response);
				break;
			case "listAll" :
				listAll(request,response);
				break;
			case "houseById" :
				houseById(request,response);
				break;
			case "houseByAid" :
				houseByAid(request,response);
				break;
			case "add":
				add(request, response);
				break;
			case "toUpdate":
				toUpdate(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "del":
				delete(request, response);
				break;
			default:
				break;
			}
		} else {
			response.sendRedirect("wq.jsp");
		}
	}
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<House> deptList = houseDao.ListAll();
		String jsonStr = new ObjectMapper().writeValueAsString(deptList);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	protected void houseByAid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		House house = new House();
		house.setAid(aid);
		List<House> list = houseDao.findById(house);
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		
	}
	protected void houseById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid = Integer.parseInt(request.getParameter("sid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		House house = new House();
		house.setSid(sid);
		house.setAid(aid);
		List<House> list = houseDao.findById(house);
		String jsonStr = new ObjectMapper().writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
		
	}
	protected void list1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current");
		int pageNum = 1;
		if (current != null) {
			pageNum = Integer.parseInt(current);
		}
		int sid = Integer.parseInt(request.getParameter("sid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		String zt = request.getParameter("zt");
		
		House house = new House();
		house.setSid(sid);
		house.setAid(aid);
		house.setHflag(zt);
		PageInfo<House> pageInfo = houseDao.findByCondition(pageNum,5,house);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current") ;	
		int c= 1;
		if(current!=null) 
		c=Integer.parseInt(request.getParameter("current"));
		PageInfo<House> pageInfo = houseDao.findByPage(c,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();//创建FileItem对象的工厂	创建一个解析器工厂
		ServletFileUpload upload = new ServletFileUpload(factory);//负责处理上传的文件数据，并将表单中每个输入项封装成一个FileItem 对象中。文件上传解析器
		/*
		 * 设置编码格式。在文件上传请求的消息体中，
		 * 除了普通表单域的值是文本内容以外，文件上传字段中的文件路径名也是文本，
		 * 在内存中保存的是它们的某种字符集编码的字节数组，Apache文件上传组件在读取这些内容时，
		 * 必须知道它们所采用的字符集编码，才能将它们转换成正确的字符文本返回。
		 */
		upload.setHeaderEncoding("utf-8");
		List<FileItem> fileList = new ArrayList<FileItem>();
		try {
			fileList = upload.parseRequest(request);//解析请求，将表单中每个输入项封装成一个FileItem对象
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String targetPath = this.getServletContext().getRealPath("upload")+"/";//得到web应用路径在服务器实际位置
		String fileName;
		String himg = "";
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		for (FileItem item : fileList) {
			if (!item.isFormField()) {//isFormField:是否是普通表单元素
				fileName = item.getName();//获得文件上传字段中的文件名
				himg += "upload/"+fileName+"、";
				File file = new File(targetPath + fileName);//保存文件
				try {
					item.write(file);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else {
				/*
				 * public Java.lang.String getString()；
				 * public java.lang.String getString(java.lang.String encoding) throws java.io.UnsupportedEncodingException
				 * 前者使用缺省的字符集编码将主体内容转换成字符串，后者使用参数指定的字符集编码将主体内容转换成字符串。
				 * 如果在读取普通表单字段元素的内容时出现了中文乱码现象，请调用第二个getString方法，并为之传递正确的字符集编码名称。
				 */
				paramsMap.put(item.getFieldName(), item.getString());//getFieldName读取当前项的名称，getString：读取当前项的值
			}
		}
		himg = himg.substring(0,himg.lastIndexOf("、"));
		
		int sid = Integer.parseInt(paramsMap.get("house.sid"));	
		int aid = Integer.parseInt(paramsMap.get("house.aid"));	
		String haddress = new String(paramsMap.get("house.haddress").getBytes("ISO-8859-1"),"UTF-8");	
		String hfh = new String(paramsMap.get("house.hfh").getBytes("ISO-8859-1"),"UTF-8");
		String hhx = new String(paramsMap.get("house.hhx").getBytes("ISO-8859-1"),"UTF-8");
		String hmj = new String(paramsMap.get("house.hmj").getBytes("ISO-8859-1"),"UTF-8");
		String hcx = new String(paramsMap.get("house.hcx").getBytes("ISO-8859-1"),"UTF-8");
		Double hmoney = Double.parseDouble(paramsMap.get("house.hmoney"));
		Double hwf = Double.parseDouble(paramsMap.get("house.hwf"));
		Double hdx = Double.parseDouble(paramsMap.get("house.hdx"));
		Double hsf = Double.parseDouble(paramsMap.get("house.hsf"));
		Double hmq = Double.parseDouble(paramsMap.get("house.hmq"));
		Double dkd = Double.parseDouble(paramsMap.get("house.dkd"));
		Double skd = Double.parseDouble(paramsMap.get("house.skd"));
		Double mkd = Double.parseDouble(paramsMap.get("house.mkd"));
		String hjp = new String(paramsMap.get("house.hjp").getBytes("ISO-8859-1"),"UTF-8");
		String hremark = new String(paramsMap.get("house.hremark").getBytes("ISO-8859-1"),"UTF-8");

		House house = new House(sid, aid, haddress,
				hfh, hhx, hmj, hcx, hmoney, hwf,
				hdx, hsf, hmq, dkd, skd, mkd, hjp, hremark, himg, "0");
		
		int save = houseDao.save(house);
		String jsonStr = new ObjectMapper().writeValueAsString(save);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
	
	protected void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int hid = Integer.parseInt(request.getParameter("hid"));
		House house = houseDao.selectById(hid);
		String[] strs = null;
		strs = house.getHimg().split("、");
		if (strs != null || strs.length != 3) {
			house.setPic1(strs[0]);
			house.setPic2(strs[1]);
			house.setPic3(strs[2]);
		}
		String jsonStr = new ObjectMapper().writeValueAsString(house);
		response.setCharacterEncoding("utf-8");
		request.getSession().setAttribute("house", house);
		response.getWriter().write(jsonStr);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		List<FileItem> fileList = new ArrayList<FileItem>();
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String targetPath = this.getServletContext().getRealPath("upload")+"/";
		String fileName;
		String himg = "";
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		for (FileItem item : fileList) {
			if (!item.isFormField()) {
				fileName = item.getName();
				himg += "upload/"+fileName+"、";
				File file = new File(targetPath + fileName);
				try {
					item.write(file);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else {
				paramsMap.put(item.getFieldName(), item.getString());
			}
		}
		himg = himg.substring(0,himg.lastIndexOf("、"));
		
		int hid = Integer.parseInt(paramsMap.get("house.hid"));	
		int sid = Integer.parseInt(paramsMap.get("house.sid"));	
		int aid = Integer.parseInt(paramsMap.get("house.aid"));	
		String haddress = new String(paramsMap.get("house.haddress").getBytes("ISO-8859-1"),"UTF-8");	
		String hfh = new String(paramsMap.get("house.hfh").getBytes("ISO-8859-1"),"UTF-8");
		String hhx = new String(paramsMap.get("house.hhx").getBytes("ISO-8859-1"),"UTF-8");
		String hmj = new String(paramsMap.get("house.hmj").getBytes("ISO-8859-1"),"UTF-8");
		String hcx = new String(paramsMap.get("house.hcx").getBytes("ISO-8859-1"),"UTF-8");
		Double hmoney = Double.parseDouble(paramsMap.get("house.hmoney"));
		Double hwf = Double.parseDouble(paramsMap.get("house.hwf"));
		Double hdx = Double.parseDouble(paramsMap.get("house.hdx"));
		Double hsf = Double.parseDouble(paramsMap.get("house.hsf"));
		Double hmq = Double.parseDouble(paramsMap.get("house.hmq"));
		Double dkd = Double.parseDouble(paramsMap.get("house.dkd"));
		Double skd = Double.parseDouble(paramsMap.get("house.skd"));
		Double mkd = Double.parseDouble(paramsMap.get("house.mkd"));
		String hjp = new String(paramsMap.get("house.hjp").getBytes("ISO-8859-1"),"UTF-8");
		String hremark = new String(paramsMap.get("house.hremark").getBytes("ISO-8859-1"),"UTF-8");

		House house = new House(hid, sid, aid, haddress, hfh, hhx, hmj, hcx, hmoney, 
				hwf, hdx, hsf, hmq, dkd, skd, mkd, hjp, hremark);
		int i = houseDao.update(house);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int hid = Integer.parseInt(request.getParameter("hid"));
		int i = houseDao.del(hid);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
