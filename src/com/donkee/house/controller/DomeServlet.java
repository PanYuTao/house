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
 * Servlet定义:运行在服务端,响应客户端的请求的java类
 * 			单例模式：所有用户使用多线程方式共享一个实例
 * 			Servlet有容器实例化，由容器并调用其方法
 */
@WebServlet("/DomeServlet")
public class DomeServlet extends HttpServlet {//自定义Servlet一般继承HttpServlet
	private static final long serialVersionUID = 1L;
       
    
    public DomeServlet() {
        super();
        System.out.println("new instance...");//在第一次请求时实例化
    }
    
    //初始化
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init...");
	}

	//销毁
	public void destroy() {
		System.out.println("destroy...");//在服务器关闭时调用一次
	}
	//service方法客户请求，都会被调用
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service....");
		super.service(request, response);//父类HttpServlet中的service根据客户端的请求方式分发给doGet/doPost等方法处理
		response.getWriter().append("service...");
	}
	
	//处理客户端get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get....");
		response.getWriter().append("do get...");
	}

	//处理客户端post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do post....");
	}

}
