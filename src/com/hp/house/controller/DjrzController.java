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
import com.hp.house.dao.DjrzDao;
import com.hp.house.dao.impl.DjrzDaoImpl;
import com.hp.house.entity.Djrz;
import com.hp.house.entity.PageInfo;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/DjrzController")
public class DjrzController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DjrzDao djrzDao = new DjrzDaoImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		if (type != null) {
			switch (type) {
			case "list":
				list(request, response);
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
			response.setCharacterEncoding("gbk");
			response.getWriter().write("type无值");
		}
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current") ;	
		int c= 1;
		if(current!=null) 
		c=Integer.parseInt(request.getParameter("current"));
		PageInfo<Djrz> pageInfo = djrzDao.findByPage(c,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cidStr = request.getParameter("cid");
		String hidStr = request.getParameter("hid");
		
		Integer cid = Integer.parseInt(cidStr);		
		Integer hid = Integer.parseInt(hidStr);			

		double myj = Double.parseDouble(request.getParameter("myj"));
		
		String myzjf = request.getParameter("myzj");
		double myzj = Double.parseDouble(myzjf);
		String mbegintime = request.getParameter("time");
		Djrz mydj = new Djrz(cid, hid, myj, myzj, mbegintime);
		int i = djrzDao.save(mydj);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//读取参数的值
		String midStr = request.getParameter("mid");
		Integer mid = Integer.parseInt(midStr);
		Djrz mydj = djrzDao.selectById(mid);
		response.setCharacterEncoding("utf-8");
		String jsonStr = new ObjectMapper().writeValueAsString(mydj);
		response.getWriter().write(jsonStr);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		
		int mid = Integer.parseInt(paramsMap.get("dj.mid"));		
		int cid = Integer.parseInt(paramsMap.get("dj.cid"));		
		int hid = Integer.parseInt(paramsMap.get("dj.hid"));			
		double myj = Double.parseDouble(paramsMap.get("dj.myj"));
		double myzj = Double.parseDouble(paramsMap.get("dj.myzj"));
		String mbegintime = paramsMap.get("dj.mbegintime");
		Djrz djrz = new Djrz(mid, cid, hid, myj, myzj, 0, mbegintime);
		int i = djrzDao.update(djrz);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String midstr = request.getParameter("mid");		
		Integer mid = Integer.parseInt(midstr);
		int count = djrzDao.del(mid);
		String jsonStr = new ObjectMapper().writeValueAsString(count);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

}
