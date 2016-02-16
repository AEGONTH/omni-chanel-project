package com.adms.web.bean.omni.travel;

import java.util.Date;

import com.adms.entity.cs.Customer;
import com.adms.entity.cs.OmniTravelHist;
import com.adms.entity.cs.ProductPlan;

public class OmniTravelCustomerModel {
	
	private Long omniTravelHistId;
	
	private Customer customer;
	
	private String oldTel;
	private String oldCitizenId;
	private String oldPassportId;
	
	private String tel;
	private String otherTel;
	private String citizenId;
	private String passportId;
	private String firstName;
	private String lastName;
	private String gender;
	private Date dateOfBirth;
	private String email;
	private String address1;
	private String address2;
	private String address3;
	private String postCode;
	private String province;
	
	private String travelPurpose;
	private String destinationCountry;
	private Date departDateTime;
	private String departFlightNo;
	private Date arrivalDateTime;
	private String arrivalFlightNo;
	
	private String listSourceCode;
	private String insurerCode;
	private String inceProductCode;
	private String productPlan;
	
	private String channel;
	private String contactReason;
	private String contactReasonOther;
	private String trackingStatus;
	private String contactDetails;
	
	private Date dueDate;
	
	public OmniTravelCustomerModel setCustomerInfoForDialog(Customer customer) {
		if(customer != null) {
			this.customer = customer;
			this.tel = customer.getMobileNo();
			this.otherTel = customer.getOtherNo();
			this.citizenId = customer.getCitizenId();
			this.passportId = customer.getPassportId();
			this.firstName = customer.getFirstName();
			this.lastName = customer.getLastName();
			this.gender = customer.getGender() == null ? null : customer.getGender().getParamKey();
			this.dateOfBirth = customer.getDob();
			this.email = customer.getEmail();
			this.address1 = customer.getAddress1();
			this.address2 = customer.getAddress2();
			this.address3 = customer.getAddress3();
			this.postCode = customer.getPostCode();
			this.province = customer.getProvince() == null ? null : customer.getProvince().getProvinceCode();
		} else {
			this.customer = null;
			this.tel = null;
			this.otherTel = null;
			this.citizenId = null;
			this.passportId = null;
			this.firstName = null;
			this.lastName = null;
			this.gender = null;
			this.dateOfBirth = null;
			this.email = null;
			this.address1 = null;
			this.address2 = null;
			this.address3 = null;
			this.postCode = null;
			this.province = null;
		}
		return this;
	}
	
	public OmniTravelCustomerModel setCaseData(OmniTravelHist omniTravelHist) {
		this.travelPurpose = omniTravelHist.getTravelPurpose();
		this.destinationCountry = omniTravelHist.getCountry() == null ? null : omniTravelHist.getCountry().getCountryCode();
		
		this.departDateTime = omniTravelHist.getDepartureDateTime();
		this.departFlightNo = omniTravelHist.getDepartureFlightNo();
		
		this.arrivalDateTime = omniTravelHist.getArrivalDateTime();
		this.arrivalFlightNo = omniTravelHist.getArrivalFlightNo();
		
		this.listSourceCode = omniTravelHist.getListSource().getListSourceCode();
		
		ProductPlan productPlan = omniTravelHist.getProductPlan();
		this.insurerCode = productPlan == null ? null : productPlan.getProduct().getInsurer().getInsurerCode();
		this.inceProductCode = productPlan == null ? null : productPlan.getProduct().getProductCode();
		this.productPlan = productPlan == null ? null : productPlan.getPlanCode();
		
		this.channel = omniTravelHist.getChannel() == null ? null : omniTravelHist.getChannel().getCode();
		this.contactReason = omniTravelHist.getContactReason() == null ? null : omniTravelHist.getContactReason().getCode();
		this.contactReasonOther = omniTravelHist.getContactReasonDetail();
		this.trackingStatus = omniTravelHist.getStatus() == null ? null : omniTravelHist.getStatus().getCode();
		this.contactDetails = omniTravelHist.getDetail();
		
		this.dueDate = omniTravelHist.getDueDate();
		
		return this;
	}

	public Long getOmniTravelHistId() {
		return omniTravelHistId;
	}

	public void setOmniTravelHistId(Long omniTravelHistId) {
		this.omniTravelHistId = omniTravelHistId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOldTel() {
		return oldTel;
	}

	public void setOldTel(String oldTel) {
		this.oldTel = oldTel;
	}

	public String getOldCitizenId() {
		return oldCitizenId;
	}

	public void setOldCitizenId(String oldCitizenId) {
		this.oldCitizenId = oldCitizenId;
	}

	public String getOldPassportId() {
		return oldPassportId;
	}

	public void setOldPassportId(String oldPassportId) {
		this.oldPassportId = oldPassportId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOtherTel() {
		return otherTel;
	}

	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getPassportId() {
		return passportId;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getTravelPurpose() {
		return travelPurpose;
	}

	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public Date getDepartDateTime() {
		return departDateTime;
	}

	public void setDepartDateTime(Date departDateTime) {
		this.departDateTime = departDateTime;
	}

	public String getDepartFlightNo() {
		return departFlightNo;
	}

	public void setDepartFlightNo(String departFlightNo) {
		this.departFlightNo = departFlightNo;
	}

	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public String getArrivalFlightNo() {
		return arrivalFlightNo;
	}

	public void setArrivalFlightNo(String arrivalFlightNo) {
		this.arrivalFlightNo = arrivalFlightNo;
	}

	public String getListSourceCode() {
		return listSourceCode;
	}

	public void setListSourceCode(String listSourceCode) {
		this.listSourceCode = listSourceCode;
	}

	public String getInsurerCode() {
		return insurerCode;
	}

	public void setInsurerCode(String insurerCode) {
		this.insurerCode = insurerCode;
	}

	public String getInceProductCode() {
		return inceProductCode;
	}

	public void setInceProductCode(String inceProductCode) {
		this.inceProductCode = inceProductCode;
	}

	public String getProductPlan() {
		return productPlan;
	}

	public void setProductPlan(String productPlan) {
		this.productPlan = productPlan;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getContactReason() {
		return contactReason;
	}

	public void setContactReason(String contactReason) {
		this.contactReason = contactReason;
	}

	public String getContactReasonOther() {
		return contactReasonOther;
	}

	public void setContactReasonOther(String contactReasonOther) {
		this.contactReasonOther = contactReasonOther;
	}

	public String getTrackingStatus() {
		return trackingStatus;
	}

	public void setTrackingStatus(String trackingStatus) {
		this.trackingStatus = trackingStatus;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
