package com.adms.web.bean.omni.travel;

import java.util.Date;

import com.adms.entity.cs.Customer;

public class OmniTravelCustomerModel {
	
	private Long omniChannelLogId;
	
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

	public Long getOmniChannelLogId() {
		return omniChannelLogId;
	}

	public OmniTravelCustomerModel setOmniChannelLogId(Long omniChannelLogId) {
		this.omniChannelLogId = omniChannelLogId;
		return this;
	}

	public String getOldTel() {
		return oldTel;
	}

	public OmniTravelCustomerModel setOldTel(String oldTel) {
		this.oldTel = oldTel;
		return this;
	}

	public String getOldCitizenId() {
		return oldCitizenId;
	}

	public OmniTravelCustomerModel setOldCitizenId(String oldCitizenId) {
		this.oldCitizenId = oldCitizenId;
		return this;
	}

	public String getOldPassportId() {
		return oldPassportId;
	}

	public OmniTravelCustomerModel setOldPassportId(String oldPassportId) {
		this.oldPassportId = oldPassportId;
		return this;
	}

	public String getTel() {
		return tel;
	}

	public OmniTravelCustomerModel setTel(String tel) {
		this.tel = tel;
		return this;
	}

	public String getOtherTel() {
		return otherTel;
	}

	public OmniTravelCustomerModel setOtherTel(String otherTel) {
		this.otherTel = otherTel;
		return this;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public OmniTravelCustomerModel setCitizenId(String citizenId) {
		this.citizenId = citizenId;
		return this;
	}

	public String getPassportId() {
		return passportId;
	}

	public OmniTravelCustomerModel setPassportId(String passportId) {
		this.passportId = passportId;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public OmniTravelCustomerModel setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public OmniTravelCustomerModel setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public OmniTravelCustomerModel setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public OmniTravelCustomerModel setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getAddress1() {
		return address1;
	}

	public OmniTravelCustomerModel setAddress1(String address1) {
		this.address1 = address1;
		return this;
	}

	public String getAddress2() {
		return address2;
	}

	public OmniTravelCustomerModel setAddress2(String address2) {
		this.address2 = address2;
		return this;
	}

	public String getAddress3() {
		return address3;
	}

	public OmniTravelCustomerModel setAddress3(String address3) {
		this.address3 = address3;
		return this;
	}

	public String getPostCode() {
		return postCode;
	}

	public OmniTravelCustomerModel setPostCode(String postCode) {
		this.postCode = postCode;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public OmniTravelCustomerModel setProvince(String province) {
		this.province = province;
		return this;
	}

	public String getTravelPurpose() {
		return travelPurpose;
	}

	public OmniTravelCustomerModel setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
		return this;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public OmniTravelCustomerModel setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
		return this;
	}

	public Date getDepartDateTime() {
		return departDateTime;
	}

	public OmniTravelCustomerModel setDepartDateTime(Date departDateTime) {
		this.departDateTime = departDateTime;
		return this;
	}

	public String getDepartFlightNo() {
		return departFlightNo;
	}

	public OmniTravelCustomerModel setDepartFlightNo(String departFlightNo) {
		this.departFlightNo = departFlightNo;
		return this;
	}

	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	public OmniTravelCustomerModel setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
		return this;
	}

	public String getArrivalFlightNo() {
		return arrivalFlightNo;
	}

	public OmniTravelCustomerModel setArrivalFlightNo(String arrivalFlightNo) {
		this.arrivalFlightNo = arrivalFlightNo;
		return this;
	}

	public String getInsurerCode() {
		return insurerCode;
	}

	public OmniTravelCustomerModel setInsurerCode(String insurerCode) {
		this.insurerCode = insurerCode;
		return this;
	}

	public String getInceProductCode() {
		return inceProductCode;
	}

	public OmniTravelCustomerModel setInceProductCode(String inceProductCode) {
		this.inceProductCode = inceProductCode;
		return this;
	}

	public String getProductPlan() {
		return productPlan;
	}

	public OmniTravelCustomerModel setProductPlan(String productPlan) {
		this.productPlan = productPlan;
		return this;
	}

	public String getChannel() {
		return channel;
	}

	public OmniTravelCustomerModel setChannel(String channel) {
		this.channel = channel;
		return this;
	}

	public String getContactReason() {
		return contactReason;
	}

	public OmniTravelCustomerModel setContactReason(String contactReason) {
		this.contactReason = contactReason;
		return this;
	}

	public String getContactReasonOther() {
		return contactReasonOther;
	}

	public OmniTravelCustomerModel setContactReasonOther(String contactReasonOther) {
		this.contactReasonOther = contactReasonOther;
		return this;
	}

	public String getTrackingStatus() {
		return trackingStatus;
	}

	public OmniTravelCustomerModel setTrackingStatus(String trackingStatus) {
		this.trackingStatus = trackingStatus;
		return this;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public OmniTravelCustomerModel setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
		return this;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public OmniTravelCustomerModel setDueDate(Date dueDate) {
		this.dueDate = dueDate;
		return this;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getListSourceCode() {
		return listSourceCode;
	}

	public void setListSourceCode(String listSourceCode) {
		this.listSourceCode = listSourceCode;
	}
	
}
