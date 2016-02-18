package com.adms.web.filter;

import java.io.IOException;
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
		//available Privileges in this application
		if(mapURIPrivs == null) {
			mapURIPrivs = new HashMap<>();
			mapURIPrivs.put("SYSTEM_ADMIN", "/");
			mapURIPrivs.put("OMNI_CHANNEL_MAIN", "/secured/omni-main");
			mapURIPrivs.put("OMNI_CHANNEL_SALES", "/secured/sales/");
			mapURIPrivs.put("OMNI_CH_CS_ENQUIRY", "/secured/cs/");
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
		
		if(reqURI.contains("/secured/changepwd")) {
			chain.doFilter(request, response);
			return;
		}
		
		List<String> requiredPrivs = mapURIPrivs.entrySet().stream().filter(p -> reqURI.contains(p.getValue())).map(m -> m.getKey()).collect(Collectors.toList());
		
		if(requiredPrivs != null
				&& !requiredPrivs.isEmpty()) {
			for(String loginPriv : loginSession.getDistinctPrivileges()) {
				if(requiredPrivs.contains(loginPriv)) {
					chain.doFilter(request, response);
					return;
				}
			}
		}
		accessDenied(request, response, req);
	}

	@Override
	public void destroy() {

	}

}
