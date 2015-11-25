package com.adms.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.adms.web.bean.login.LoginSession;

public class AuthenFilter extends AbstractFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		String reqURI = req.getRequestURI();
		
		if(reqURI.contains("/login")
				|| (session != null && (((LoginSession) session.getAttribute("loginSession")) != null && ((LoginSession) session.getAttribute("loginSession")).getUsername() != null))
				|| reqURI.contains("errors")
				|| reqURI.contains("/public/")
				|| reqURI.contains("javax.faces.resource")
				|| reqURI.contains("resources")) {
			chain.doFilter(request, response);
		} else {
			doLogin(request, response, req);
		}
	}
	
	@Override
	public void destroy() {

	}

}
