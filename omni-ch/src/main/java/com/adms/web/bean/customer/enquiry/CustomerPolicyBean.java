package com.adms.web.bean.customer.enquiry;

import java.util.Date;

import com.adms.entity.cs.CustomerYesRecord;

public class CustomerPolicyBean {

	private Long customerYesRecId;
	private String campaignName;
	private String refNo;
	private Date objectDate;
	private double premium;
	private CustomerYesRecord customerYesRecord;
	
	public CustomerPolicyBean() {
		
	}
	
	public CustomerPolicyBean(Long customerYesRecId, String campaignName, String refNo, Date objectDate, double premium) {
		this.customerYesRecId = customerYesRecId;
		this.campaignName = campaignName;
		this.refNo = refNo;
		this.objectDate = objectDate;
		this.premium = premium;
	}

	public Long getCustomerYesRecId() {
		return customerYesRecId;
	}

	public CustomerPolicyBean setCustomerYesRecId(Long customerYesRecId) {
		this.customerYesRecId = customerYesRecId;
		return this;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public CustomerPolicyBean setCampaignName(String campaignName) {
		this.campaignName = campaignName;
		return this;
	}

	public String getRefNo() {
		return refNo;
	}

	public CustomerPolicyBean setRefNo(String refNo) {
		this.refNo = refNo;
		return this;
	}

	public Date getObjectDate() {
		return objectDate;
	}

	public CustomerPolicyBean setObjectDate(Date objectDate) {
		this.objectDate = objectDate;
		return this;
	}

	public double getPremium() {
		return premium;
	}

	public CustomerPolicyBean setPremium(double premium) {
		this.premium = premium;
		return this;
	}

	public CustomerYesRecord getCustomerYesRecord() {
		return customerYesRecord;
	}

	public CustomerPolicyBean setCustomerYesRecord(CustomerYesRecord customerYesRecord) {
		this.customerYesRecord = customerYesRecord;
		return this;
	}
	
}
