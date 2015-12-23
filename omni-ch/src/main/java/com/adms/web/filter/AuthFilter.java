package com.adms.web.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;

import com.adms.common.entity.UserLogin;
import com.adms.web.bean.login.LoginSession;

public class AuthFilter extends AbstractFilter {
	
	private final String AUTH_URL = "http://localhost:8080/authen-ws/rest/authservice";
	private final String AUTH_PATH = "auth";
	private final String SSO_PATH = "simpleSSO";
	private final String CHG_PWD_PATH = "chgpwd";
	private final String SALT_PWD = "$AlT*P@$$w0Rd#";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
//		HttpSession session = req.getSession(false);
		String reqURI = req.getRequestURI();
		
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add("requestedType", "filter");
		headers.add("requestedURL", reqURI);
		
		Response resp = authWS(headers);
		
		if(resp.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("Status OK!");
			chain.doFilter(request, response);
		} if(resp.getStatus() == Status.UNAUTHORIZED.getStatusCode()) {
			System.out.println("Unauthorized!");
			res.sendRedirect(req.getContextPath() + "/errors/access-denied");
		} else {
			System.out.println("Bad Requested!!");
			res.sendRedirect(req.getContextPath() + "/errors/500");
		}
	}
	
	private Response authWS(MultivaluedMap<String, Object> headers) {
		try {
			Response resp =  ClientBuilder.newClient()
					.target(AUTH_URL)
					.path(SSO_PATH)
					.request()
					.headers(headers)
					.get();
			return resp;
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@Override
	public void destroy() {

	}

}
