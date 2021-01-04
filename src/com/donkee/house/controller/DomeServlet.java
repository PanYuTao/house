package com.donkee.house.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DomeServlet
 */

/**
 * Servlet����:�����ڷ����,��Ӧ�ͻ��˵������java��
 * 			����ģʽ�������û�ʹ�ö��̷߳�ʽ����һ��ʵ��
 * 			Servlet������ʵ�������������������䷽��
 */
@WebServlet("/DomeServlet")
public class DomeServlet extends HttpServlet {//�Զ���Servletһ��̳�HttpServlet
	private static final long serialVersionUID = 1L;
       
    
    public DomeServlet() {
        super();
        System.out.println("new instance...");//�ڵ�һ������ʱʵ����
    }
    
    //��ʼ��
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init...");
	}

	//����
	public void destroy() {
		System.out.println("destroy...");//�ڷ������ر�ʱ����һ��
	}
	//service�����ͻ����󣬶��ᱻ����
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service....");
		super.service(request, response);//����HttpServlet�е�service���ݿͻ��˵�����ʽ�ַ���doGet/doPost�ȷ�������
		response.getWriter().append("service...");
	}
	
	//����ͻ���get����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get....");
		response.getWriter().append("do get...");
	}

	//����ͻ���post����
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do post....");
	}

}
