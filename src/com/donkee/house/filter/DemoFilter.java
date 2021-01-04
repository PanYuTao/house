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
    
    //�ڷ���������ʱ����������ʵ��������ʼ��
    public void init(FilterConfig fConfig) throws ServletException {
    	System.out.println("init....");
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("1.������Դ֮ǰ");
		chain.doFilter(request, response);//������һ�����������û�������Դ
		System.out.println("2.������Դ֮��");
	}

	public void destroy() {
		System.out.println("destroy....");
	}

}
