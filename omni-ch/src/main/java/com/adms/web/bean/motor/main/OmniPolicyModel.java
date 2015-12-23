package com.adms.web.bean.motor.main;

import java.util.Date;
import java.util.List;

import com.adms.entity.cs.Customer;
import com.adms.entity.cs.OmniLogMotorHist;

public class OmniPolicyModel {

	private Customer customer;
	
	private List<OmniLogMotorHist> logMotors;
	private OmniLogMotorHist selectedLogMotor;
	
	private String policyNo;
	private Double premium;
	
	private Date effectiveDate;
	private Date expireDate;
	

	public Customer getCustomer() {
		return customer;
	}

	public OmniPolicyModel setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	public List<OmniLogMotorHist> getLogMotors() {
		return logMotors;
	}

	public OmniPolicyModel setLogMotors(List<OmniLogMotorHist> logMotors) {
		this.logMotors = logMotors;
		return this;
	}

	public OmniLogMotorHist getSelectedLogMotor() {
		return selectedLogMotor;
	}

	public void setSelectedLogMotor(OmniLogMotorHist selectedLogMotor) {
		this.selectedLogMotor = selectedLogMotor;
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
