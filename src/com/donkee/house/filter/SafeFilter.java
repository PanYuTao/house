package com.donkee.house.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donkee.house.entity.Emp;

/**
 * Servlet Filter implementation class DemoServlet
 */
//@WebFilter(urlPatterns = "/*")
public class SafeFilter implements Filter {

	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;
		String uri = request.getRequestURI();
		String ctxPath = request.getContextPath();//得到上下文的路径，web应用项目名
		uri = uri.substring(uri.indexOf(ctxPath)+ctxPath.length());
		System.out.println("uri="+uri);
		System.out.println("ctxPath="+ctxPath);
		Emp loginUser = (Emp) request.getSession().getAttribute("loginUser");
		if (loginUser!=null) {//已经登录
			chain.doFilter(request, response);//调用下一个过滤器或用户请求资源
		} else if (uri.endsWith("EmpController") && request.getParameter("type").equals("login")) {//准备登录
			chain.doFilter(request, response);
		} else if (uri.startsWith("/images") || uri.startsWith("/css") || uri.startsWith("/js") || uri.startsWith("/layer") || uri.endsWith("index.html")) {//静态资源
			chain.doFilter(request, response);
		}else {
			response.sendRedirect(ctxPath+"/index.html");
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
