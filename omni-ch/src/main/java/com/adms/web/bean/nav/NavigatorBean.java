package com.adms.web.bean.nav;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

@ManagedBean
@NoneScoped
public class NavigatorBean implements Serializable {

	private static final long serialVersionUID = -8193432200159583456L;
	
	private final String redirect = "?faces-redirect=true";
	
	private final String toUnderConstruction = "/errors/under-construction";
	
	private final String toLogin = "/login";
	
	private final String toOmniMain = "/secured/omni-main";
	private final String toOmniMotor = "/secured/omni-motor";
	private final String toOmniTravel = "/secured/omni-travel";
	
	private final String toCsEnquiry = "/secured/cs/customer-enquiry";
	
	public String toLoginPage() {
		return toLogin + redirect;
	}
	
	public String toOmniMainPage() {
		return toOmniMain + redirect;
	}
	
	public String toOmniMotorPage() {
		return toOmniMotor + redirect;
	}
	
	public String toOmniTravelPage() {
		return toUnderConstruction + redirect;
	}
	
	public String toCsCustomerEnquiry() {
		return toCsEnquiry + redirect;
	}
}
