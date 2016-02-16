package com.adms.web.bean.nav;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

import org.omnifaces.util.Faces;

@ManagedBean
@NoneScoped
public class NavigatorBean implements Serializable {

	private static final long serialVersionUID = -8193432200159583456L;
	
	protected final String redirect = "?faces-redirect=true";
	
	protected final String toUnderConstruction = "/errors/under-construction";
	
	private final String toLogin = "/login";
	
	private final String toOmniMain = "/secured/omni-main";
	private final String toOmniMotor = "/secured/sales/omni-motor";
	private final String toOmniTravel = "/secured/sales/omni-travel";
	
	private final String toCsEnquiry = "/secured/cs/customer-enquiry";
	
	public String toLoginPage() {
		return toLogin + redirect;
	}
	
	public String toOmniMainPage() throws IOException {
		Faces.redirect(Faces.getRequestContextPath() + toOmniMain);
		return null;
	}
	
	public String toOmniMotorPage() throws IOException {
		Faces.redirect(Faces.getRequestContextPath() + toOmniMotor);
		return null;
	}
	
	public String toOmniTravelPage() throws IOException {
		Faces.redirect(Faces.getRequestContextPath() + toOmniTravel);
		return null;
	}
	
	public String toCsCustomerEnquiry() throws IOException {
		Faces.redirect(Faces.getRequestContextPath() + toCsEnquiry);
		return null;
	}
}
