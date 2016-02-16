package com.adms.web.bean.omni.travel;

import java.util.Date;
import java.util.List;

import com.adms.entity.cs.Customer;
import com.adms.entity.cs.OmniTravelHist;

public class OmniTravelPolicyModel {

	private String currentFlow;
	
	private Customer customer;
	
	private String searchCitizenId;
	private String searchTel;
	private String searchPassportId;
	
	private List<OmniTravelHist> omniTravelHists;
	private OmniTravelHist selectedOmniTravelHist;
	
	private String policyNo;
	private Double premium;
	
	private Date effectiveDate;
	private Date expireDate;
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getSearchCitizenId() {
		return searchCitizenId;
	}
	
	public void setSearchCitizenId(String searchCitizenId) {
		this.searchCitizenId = searchCitizenId;
	}
	
	public String getSearchTel() {
		return searchTel;
	}
	
	public void setSearchTel(String searchTel) {
		this.searchTel = searchTel;
	}
	
	public String getSearchPassportId() {
		return searchPassportId;
	}
	
	public void setSearchPassportId(String searchPassportId) {
		this.searchPassportId = searchPassportId;
	}
	
	public List<OmniTravelHist> getOmniTravelHists() {
		return omniTravelHists;
	}
	
	public void setOmniTravelHists(List<OmniTravelHist> omniTravelHists) {
		this.omniTravelHists = omniTravelHists;
	}
	
	public OmniTravelHist getSelectedOmniTravelHist() {
		return selectedOmniTravelHist;
	}
	
	public void setSelectedOmniTravelHist(OmniTravelHist selectedOmniTravelHist) {
		this.selectedOmniTravelHist = selectedOmniTravelHist;
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

	public String getCurrentFlow() {
		return currentFlow;
	}

	public void setCurrentFlow(String currentFlow) {
		this.currentFlow = currentFlow;
	}

}
