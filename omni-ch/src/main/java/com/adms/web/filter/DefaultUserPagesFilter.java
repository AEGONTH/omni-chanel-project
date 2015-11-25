package com.adms.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.adms.web.bean.login.LoginSession;

public class DefaultUserPagesFilter extends AbstractFilter {

	private static Map<String, String> mapURIPrivs;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if(mapURIPrivs == null) {
			mapURIPrivs = new HashMap<>();
			mapURIPrivs.put("secured/", "OMNI_CHANNEL_SALES");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(true);
		if(session == null || session.getAttribute("loginSession") == null) {
			doLogin(request, response, req);
			return;
		}
		LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
		String reqURI = req.getRequestURI();
		
		List<String> keys = new ArrayList<>();
		keys = mapURIPrivs.keySet().stream().filter(p -> reqURI.contains(p)).collect(Collectors.toList());
		if(keys != null 
				&& keys.size() == 1
				&& loginSession.getDistinctPrivileges().contains(mapURIPrivs.get(keys.get(0)))) {
			chain.doFilter(request, response);
		} else {
			accessDenied(request, response, req);
		}
	}

	@Override
	public void destroy() {

	}

}
