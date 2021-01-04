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
 * Servlet����:�����ڷ����,��Ӧ�ͻ��˵������java��
 * 			����ģʽ�������û�ʹ�ö��̷߳�ʽ����һ��ʵ��
 * 			Servlet������ʵ�������������������䷽��
 */
@WebServlet("/FileUploadController")
public class FileUploadController extends HttpServlet {//�Զ���Servletһ��̳�HttpServlet
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String type = request.getParameter("type");	
		String title = request.getParameter("title");//���ܶ�ȡ
		String photo = request.getParameter("photo");
		System.out.println(type+":"+title+":"+photo);*/
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		//factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		ServletContext servletContext = this.getServletConfig().getServletContext();
		String path = servletContext.getRealPath("upload")+"/";//�õ�webӦ��·���ڷ�����ʵ��λ��
		
		System.out.println(path);
		Map<String, String> paramsMap = new HashMap<>();
		try {
			List<FileItem> items = upload.parseRequest(request);//3.�������󣬽��������ݷ�װ��FileItem����
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {//isFormField:�Ƿ�����ͨ��Ԫ��
					String filedName = fileItem.getFieldName();//getFieldName����ȡ��ǰ�ļ��������
					paramsMap.put(filedName, fileItem.getString());//getString����ȡ��ǰ�ļ����ֵ
				} else {//�ļ�����
					String filePath = path+fileItem.getName();//�õ��ļ���
					FileOutputStream fos = new FileOutputStream(filePath);
					InputStream is = fileItem.getInputStream();
					byte[] buffer = new byte[1024];
					int len;
					//is.read(buffer):��ȡ���ݵ�buffer�ֽ����飬����ʵ�ʶ�ȡ�ĳ���
					while ((len=is.read(buffer))!=-1) {
						fos.write(buffer,0,len);//���������д��buffer[0,len]����
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
