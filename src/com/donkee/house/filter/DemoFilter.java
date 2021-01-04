package com.donkee.house.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class DemoServlet
 */
//@WebFilter(urlPatterns ="/*")
public class DemoFilter implements Filter {

    public DemoFilter() {
    }
    
    //在服务器启动时，过滤器被实例化及初始化
    public void init(FilterConfig fConfig) throws ServletException {
    	System.out.println("init....");
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("1.请求资源之前");
		chain.doFilter(request, response);//调用下一个过滤器或用户请求资源
		System.out.println("2.请求资源之后");
	}

	public void destroy() {
		System.out.println("destroy....");
	}

}
