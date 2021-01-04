package com.hp.house.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class FileUploadController
 */
public class FileUploadController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext servletContext = this.getServletConfig().getServletContext();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String path = servletContext.getRealPath("upload")+"/";//得到web应用路径在服务器实际位置
		System.out.println(path);
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		try {
			List<FileItem> items = upload.parseRequest(request);//解析请求，将所有数据封装到FileItem对象
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {//isFormField:是否是普通表单元素
					String fieldName = fileItem.getFieldName();//getFieldName读取当前项的名称
					paramsMap.put(fieldName, fileItem.getString());
					System.out.println(fieldName+":"+fileItem.getString());
				}else {//文件表单域
					String fileName = path+fileItem.getName();
					System.out.println(fileName);
					FileOutputStream fos = new FileOutputStream(fileName);
					InputStream is = fileItem.getInputStream();
					byte[] buffer = new byte[1024];
					int len;
					while ((len=is.read(buffer))!= -1) {
						fos.write(buffer, 0, len);
					}
					fos.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		String title = paramsMap.get("title");
		String auth = paramsMap.get("auth");
		System.out.println(title + "--" + auth);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
