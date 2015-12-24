package com.adms.web.bean.motor;

import java.util.Date;
import java.util.List;

import com.adms.entity.cs.Customer;
import com.adms.entity.cs.OmniMotorHist;

public class OmniMotorPolicyModel {

	private Customer customer;
	
	private List<OmniMotorHist> logMotors;
	private OmniMotorHist selectedOmniMotorHist;
	
	private String policyNo;
	private Double premium;
	
	private Date effectiveDate;
	private Date expireDate;

	public Customer getCustomer() {
		return customer;
	}

	public OmniMotorPolicyModel setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	public List<OmniMotorHist> getLogMotors() {
		return logMotors;
	}

	public OmniMotorPolicyModel setLogMotors(List<OmniMotorHist> logMotors) {
		this.logMotors = logMotors;
		return this;
	}

	public OmniMotorHist getSelectedOmniMotorHist() {
		return selectedOmniMotorHist;
	}

	public void setSelectedOmniMotorHist(OmniMotorHist selectedOmniMotorHist) {
		this.selectedOmniMotorHist = selectedOmniMotorHist;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

}
