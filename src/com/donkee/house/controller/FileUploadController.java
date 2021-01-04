package com.donkee.house.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class DomeServlet
 */

/**
 * Servlet定义:运行在服务端,响应客户端的请求的java类
 * 			单例模式：所有用户使用多线程方式共享一个实例
 * 			Servlet有容器实例化，由容器并调用其方法
 */
@WebServlet("/FileUploadController")
public class FileUploadController extends HttpServlet {//自定义Servlet一般继承HttpServlet
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String type = request.getParameter("type");	
		String title = request.getParameter("title");//不能读取
		String photo = request.getParameter("photo");
		System.out.println(type+":"+title+":"+photo);*/
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		//factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		ServletContext servletContext = this.getServletConfig().getServletContext();
		String path = servletContext.getRealPath("upload")+"/";//得到web应用路径在服务器实际位置
		
		System.out.println(path);
		Map<String, String> paramsMap = new HashMap<>();
		try {
			List<FileItem> items = upload.parseRequest(request);//3.解析请求，将所有数据封装到FileItem对象
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {//isFormField:是否是普通表单元素
					String filedName = fileItem.getFieldName();//getFieldName：读取当前文件项的名称
					paramsMap.put(filedName, fileItem.getString());//getString：读取当前文件项的值
				} else {//文件表单域
					String filePath = path+fileItem.getName();//得到文件名
					FileOutputStream fos = new FileOutputStream(filePath);
					InputStream is = fileItem.getInputStream();
					byte[] buffer = new byte[1024];
					int len;
					//is.read(buffer):读取数据到buffer字节数组，返回实际读取的长度
					while ((len=is.read(buffer))!=-1) {
						fos.write(buffer,0,len);//向输出流中写入buffer[0,len]数据
					}
					fos.close();				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		String title = paramsMap.get("title");
		String auth = paramsMap.get("auth");
		System.out.println(title+":"+auth);
		
	}

}
