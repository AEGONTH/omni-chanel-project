package com.adms.web.bean.login;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.config.WebXml;
import org.omnifaces.util.Faces;

import com.adms.web.base.bean.BaseBean;

@ManagedBean
@SessionScoped
public class LoginSession extends BaseBean {

	private static final long serialVersionUID = 3528950428438273568L;

	private String username;
	private Collection<String> roles;
	private Map<String, List<String>> rolePrivileges;
	private List<String> distinctPrivileges;
	private List<String> urlAllows;
	
	private int sessionTimeOut;
	
	public LoginSession() {
		
	}
	
	@PostConstruct
	private void init() {
		sessionTimeOut = (WebXml.INSTANCE.getSessionTimeout() * 1000 * 60) - 500;
	}

	public void signOut() throws Exception {
		invalidateSession();
		Faces.redirect(Faces.getRequestContextPath() + "/login");
	}
	
	public void toChangePwd() throws IOException {
		Faces.redirect(Faces.getRequestContextPath() + "/secured/changepwd");
	}
	
	public void invalidateSession() throws Exception {
		Faces.invalidateSession();
	}
	
	public boolean isPrivSales() throws Throwable {
		return distinctPrivileges != null
				&& !distinctPrivileges.isEmpty()
				&& distinctPrivileges.contains("OMNI_CHANNEL_SALES");
	}
	
	public boolean isPrivEnquiry() throws Throwable {
		return distinctPrivileges != null
				&& !distinctPrivileges.isEmpty()
				&& distinctPrivileges.contains("OMNI_CH_CS_ENQUIRY");
	}
	
	public boolean isPrivCsSuper() throws Throwable {
		return distinctPrivileges != null
				&& !distinctPrivileges.isEmpty()
				&& distinctPrivileges.contains("OMNI_CHANNEL_REFRESH");
	}
	
	public boolean isPrivSysAdmin() throws Throwable {
		return distinctPrivileges != null
				&& !distinctPrivileges.isEmpty()
				&& distinctPrivileges.contains("SYSTEM_ADMIN");
	}
	
//	public boolean privSysAdmin() throws Throwable {
//		return distinctPrivileges != null && distinctPrivileges.contains("SYSTEM_ADMIN");
//	}
//	
//	public boolean privAdmin() throws Throwable {
//		return distinctPrivileges != null 
//				&& (distinctPrivileges.contains("CS_ADMIN")
//						|| distinctPrivileges.contains("SYSTEM_ADMIN"));
//	}
//	
//	public boolean privCusEnq() throws Throwable {
//		return distinctPrivileges != null 
//				&& (distinctPrivileges.contains("CUSTOMER_ENQUIRY")
//						|| distinctPrivileges.contains("SYSTEM_ADMIN"));
//	}
//	
//	public boolean privConfCall() throws Throwable {
//		return distinctPrivileges != null 
//				&& (distinctPrivileges.contains("CONFIRMATION_CALL")
//						|| distinctPrivileges.contains("SYSTEM_ADMIN"));
//	}
//	
//	public boolean privOmniCh() throws Throwable {
//		return distinctPrivileges != null 
//				&& (distinctPrivileges.contains("OMNI_CHANNEL_SALES")
//						|| distinctPrivileges.contains("SYSTEM_ADMIN"));
//	}
	
	public void checkPermissions() throws Throwable {
		if(distinctPrivileges == null) {
			signOut();
		} else {
			
		}
	}
	
	public LoginSession username(String username) {
		this.username = username;
		return this;
	}
	
	public LoginSession roles(Collection<String> roles) {
		this.roles = roles;
		return this;
	}
	
	public LoginSession rolePrivileges(Map<String, List<String>> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
		return this;
	}

	public String getUsername() {
		return username;
	}
	
	public Map<String, List<String>> getRolePrivileges() {
		return rolePrivileges;
	}

	public Collection<String> getRoles() {
		return roles;
	}

	public List<String> getDistinctPrivileges() {
		return distinctPrivileges;
	}
	
	public LoginSession distinctPrivileges(List<String> distinctPrivileges) {
		this.distinctPrivileges = distinctPrivileges;
		return this;
	}

	public int getSessionTimeOut() {
		return sessionTimeOut;
	}

	public List<String> getUrlAllows() {
		return urlAllows;
	}

	public LoginSession setUrlAllows(List<String> urlAllows) {
		this.urlAllows = urlAllows;
		return this;
	}
	
}
