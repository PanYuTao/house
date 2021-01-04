package com.hp.house.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.house.entity.Emp;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getRequestURI();
		String ctxPath = request.getContextPath();
		uri = uri.substring(ctxPath.length());
		
		Emp user = (Emp) request.getSession().getAttribute("user");
		
		if (user != null) {
			chain.doFilter(request, response);
		}else if(uri.endsWith("EmpController") && request.getParameter("type").equals("login")){
			System.out.println(1);
			
			chain.doFilter(request, response);
		}else if (uri.startsWith("/images") || uri.startsWith("/css") ||uri.startsWith("/js")||uri.startsWith("/layer") || 
				uri.endsWith("index.html")) {
			System.out.println(2);
			chain.doFilter(request, response);
		}else {
			response.sendRedirect(ctxPath + "/index.html"); 
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
