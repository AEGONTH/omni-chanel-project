package com.adms.web.bean.login;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Components;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import com.adms.common.entity.UserLogin;
import com.adms.utils.EncryptionUtil;
import com.adms.web.base.bean.BaseBean;
import com.adms.web.bean.nav.NavigatorBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ManagedBean
@ViewScoped
public class LoginView extends BaseBean {

	private static final long serialVersionUID = -7276944451892430995L;

	private final String AUTH_URL = "http://10.66.0.16:8080/authen-ws/rest/authservice";
	private final String AUTH_PATH = "auth";
	private final String CHG_PWD_PATH = "chgpwd";
	private final String SALT_PWD = "$AlT*P@$$w0Rd#";
	
	@ManagedProperty(value="#{loginSession}")
	private LoginSession loginSession;
	
	private String username;
	private String password;
	
	private String newPassword;
	private String confirmPassword;
	
	@PostConstruct
	private void init() {
		
	}
	
	public String doLogin() throws Throwable {
		if(!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
			if(authService()) {
				NavigatorBean navBean = Faces.evaluateExpressionGet("#{navigatorBean}");
				return navBean.toOmniMainPage();
			} else {
				Messages.addError("msgLogin", "Invalid Username or Password.");
				RequestContext.getCurrentInstance().execute("PF('progressDialog').hide();");
			}
		} else {
			if(StringUtils.isBlank(username)) ((UIInput) Components.findComponent("loginForm:username")).setValid(false);
			if(StringUtils.isBlank(password)) ((UIInput) Components.findComponent("loginForm:pwd")).setValid(false);
			Messages.addError("msgLogin", "Please fill all the required");
		}
		return null;
	}
	
	public void doChangePwd() throws Exception {
		String username = loginSession.getUsername();
		if(!StringUtils.isBlank(username)) {
			if(password.equals(newPassword)) {
				//same password!
				((UIInput) Components.findComponent("chgPwdForm:pwd")).setValid(false);
				((UIInput) Components.findComponent("chgPwdForm:newPwd")).setValid(false);
				Messages.addError("chgPwdMsg", "Both old and new password are same value.");
				return;
			}
			
			if(!newPassword.equals(confirmPassword)) {
				//new password not matched!
				((UIInput) Components.findComponent("chgPwdForm:newPwd")).setValid(false);
				((UIInput) Components.findComponent("chgPwdForm:confPwd")).setValid(false);
				Messages.addError("chgPwdMsg", "The new passowrd you typed, not matched.");
				return;
			}
			
			String oldPwd = EncryptionUtil.getInstance().md5(password, SALT_PWD);
			String newPwd = EncryptionUtil.getInstance().md5(newPassword, SALT_PWD);
			
			UserLogin userLogin = new UserLogin(username, oldPwd).setNewPwd(newPwd);
			Gson gson = new GsonBuilder().create();
			try {
				String respStr = ws(AUTH_URL, CHG_PWD_PATH, "val", gson.toJson(userLogin));
				gson = new GsonBuilder().create();
				userLogin = gson.fromJson(respStr, UserLogin.class);
				
				if(userLogin.getLoginSuccess()) {
					Messages.addInfo("chgPwdMsg", "Your password has been changed.");
				} else {
					Messages.addError("chgPwdMsg", "Wrong Password.");
				}
			} catch(Exception e) {
				e.printStackTrace();
				Messages.addError("chgPwdMsg", "Something went wrong! please try agian.");
			}
		} else {
			loginSession.signOut();
		}
	}
	
	private String ws(String targetUrl, String path, MultivaluedMap<String, Object> headers) {
		Response resp =  ClientBuilder.newClient()
			.target(targetUrl)
			.path(path)
			.request()
			.headers(headers)
			.get();
		return resp.readEntity(String.class);
	}
	
	private String ws(String targetUrl, String path, String header, Object val) {
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add(header, val);
		return ws(targetUrl, path, headers);
	}
	
	private boolean authService() {
		boolean flag = false;
		
		try {
			String encryptedPwd = EncryptionUtil.getInstance().md5(password, SALT_PWD);
			UserLogin userLogin = new UserLogin(username, encryptedPwd);
			Gson gson = new GsonBuilder().create();
			String respStr = ws(AUTH_URL, AUTH_PATH, "val", gson.toJson(userLogin));
			if(respStr.contains("Not Found")) {
				Messages.addGlobalError("Auth Service not found!! or maybe service is not running. -> " + AUTH_URL);
			} else {
				gson = new GsonBuilder().create();
				userLogin = gson.fromJson(respStr, UserLogin.class);

				flag = userLogin.getLoginSuccess();
				
				if(flag) {
					List<String> privs = new ArrayList<>();
					
					if(userLogin.getRolePrivileges() == null) {
						Messages.addGlobalError("Cannot get privileges");
						return false;
					}
					
					for(String key : userLogin.getRolePrivileges().keySet()) {
						privs.addAll(userLogin.getRolePrivileges().get(key));
					}
					List<String> distinctPrivs = privs.stream()
												.sorted((p1, p2) -> p1.compareTo(p2))
												.distinct()
												.collect(Collectors.toList());
					loginSession.username(userLogin.getUsername()).roles(userLogin.getRolePrivileges().keySet())
								.rolePrivileges(userLogin.getRolePrivileges())
								.distinctPrivileges(distinctPrivs);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLoginSession(LoginSession loginSession) {
		this.loginSession = loginSession;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
