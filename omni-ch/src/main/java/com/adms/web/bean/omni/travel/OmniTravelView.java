package com.adms.web.bean.omni.travel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.adms.cs.service.CustomerService;
import com.adms.entity.cs.Customer;
import com.adms.entity.cs.OmniTravelHist;
import com.adms.web.base.bean.BaseBean;
import com.adms.web.bean.omni.OmniChannelSync;

@ManagedBean
@ViewScoped
public class OmniTravelView extends BaseBean {

	private static final long serialVersionUID = -9056687058722776725L;

	private final String TEL = "TEL";
	private final String CITIZEN = "CITIZEN";
	private final String globalMsgId = "globalMsg";
	
	private final CustomerService customerService = Faces.evaluateExpressionGet("#{customerService}");
	private OmniChannelSync omniChannelSync = Faces.evaluateExpressionGet("#{omniChannelSync}");
	
	private OmniTravelCustomerModel customerInfo;
	private boolean telReadyOnly;
	private boolean citizenReadyOnly;
	
	private List<SelectItem> genderSelection;
	private List<SelectItem> provinceSelection;
	
	private List<SelectItem> destinationCountrySelection;
	private List<SelectItem> travelPurposeSelection;

	private String tel;
	private String citizenId;
	
	private Date currDepartDate;
	private Date currArrivalDate;
	
	private String purposeCode;
	
	private String insurerCode;
	private String inceProductCode;
	private String contactReasonCode;
	private String contactReasonTxt;
	
	private List<SelectItem> listSourceSelection;
	private List<SelectItem> insurerSelection;
	private List<SelectItem> inceProductSelection;
	private List<SelectItem> productPlanSelection;
	
	private List<SelectItem> channelSelection;
	private List<SelectItem> contactReasonSelection;
	private List<SelectItem> trackingStatusSelection;
	
	@PostConstruct
	private void init() {
		System.out.println("URI: " + Faces.getRequestURI());
		System.out.println("URL: " + Faces.getRequestURL());
		System.out.println("SessionId: " + Faces.getSessionId());
		
		try {
			initGender();
			initProvince();
			initDestinationCountry();
			initTravelPurpose();
			initListSource();
			initInsurers();

			initChannels();
			initContactReason();
			initTrackingStatus();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void departureDateListener() throws Throwable {
		if(currArrivalDate != null && currArrivalDate.compareTo(currDepartDate) < 0) {
			currArrivalDate = null;
		}
	}
	
	public void insurerSelectionListener() throws Throwable {
		initInceProducts();
	}
	
	public void productSelectionListener() throws Throwable {
		initProductPlans();
	}
	
	public void contactReasonSelectionListener() {
		if(StringUtils.isNoneBlank(contactReasonCode) && contactReasonCode.equals("OMNI_CTRS_OTHER")) {
			
		} else {
			contactReasonTxt = null;
		}
	}
	
	public void saveCustomerInfo() throws Throwable {
		
	}
	
	public void doSearchCustomer(String val) throws Throwable {
		String temp = null;
		if(customerInfo.getOmniChannelLogId() == null) {
			if(val.equalsIgnoreCase(TEL)) {
				temp = new String(tel);
				setDialogDataFromList(findVisibleCustomer(tel, null), val);
				tel = temp;
				customerInfo.setTel(tel);
				customerInfo.setOldTel(tel);
				citizenId = customerInfo.getCitizenId();
			} else if(val.equalsIgnoreCase(CITIZEN)) {
				temp = new String(citizenId);
				setDialogDataFromList(findVisibleCustomer(null, citizenId), val);
				citizenId = temp;
				customerInfo.setCitizenId(citizenId);
				customerInfo.setOldCitizenId(citizenId);
				tel = customerInfo.getTel();
			}
			Ajax.update("frmDlg:gridCustomerInfo");
		}
	}
	
	public void onClickAddNew() throws Throwable {
		customerInfo = new OmniTravelCustomerModel();
		telReadyOnly = false;
		citizenReadyOnly = false;

		tel = null;
		citizenId = null;
		
		currDepartDate = null;
		currArrivalDate = null;
		
		insurerCode = null;
		inceProductCode = null;
		contactReasonCode = null;
		contactReasonTxt = null;
		setDataToDialog(null, null, null);
	}

	private List<Customer> findVisibleCustomer(String mobileNo, String citizenId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if(StringUtils.isNotBlank(mobileNo)) criteria.add(Restrictions.eq("mobileNo", mobileNo));
		if(StringUtils.isNotBlank(citizenId)) criteria.add(Restrictions.eq("citizenId", citizenId));
		criteria.add(Restrictions.eq("visible", "Y"));
		return customerService.findByCriteria(criteria);
	}

	private void setDialogDataFromList(List<Customer> list, String searchField) {
		if(list != null && list.size() == 1) {
			customerInfo.setCustomer(list.get(0));
			if(searchField.equalsIgnoreCase(TEL)) {
				telReadyOnly = true;
			} else if(searchField.equalsIgnoreCase(CITIZEN)) {
				citizenReadyOnly = true;
			}
		} else {
			customerInfo.setCustomer(null);
			Messages.addInfo(globalMsgId, "Not found.");
			Ajax.update("frmDlg:globalMsg");
		}
		customerInfo.setCustomerInfoForDialog(customerInfo.getCustomer());
	}
	
	private void setDataToDialog(Long omniChannelLogId, Customer customer, OmniTravelHist omniTravelHist) throws Throwable {
		customerInfo
			.setOmniChannelLogId(omniChannelLogId)
			.setCustomerInfoForDialog(customer);
		
		if(omniTravelHist != null) {
			customerInfo
				.setTravelPurpose(omniTravelHist.getTravelPurpose())
				.setDestinationCountry(omniTravelHist.getCountry().getCountryCode())
				.setDepartDateTime(omniTravelHist.getDepartureDateTime())
				.setDepartFlightNo(omniTravelHist.getDepartureFlightNo())
				.setArrivalDateTime(omniTravelHist.getArrivalDateTime())
				.setArrivalFlightNo(omniTravelHist.getArrivalFlightNo())
				.setInsurerCode(omniTravelHist.getProductPlan().getProduct().getInsurer().getInsurerCode());
		
			initInceProducts();
			customerInfo.setInceProductCode(omniTravelHist.getProductPlan().getProduct().getProductCode());
	
			initProductPlans();
			customerInfo.setProductPlan(omniTravelHist.getProductPlan().getPlanCode());
	
			customerInfo
				.setChannel(omniTravelHist.getChannel().getCode())
				.setContactReason(omniTravelHist.getContactReason().getCode())
				.setTrackingStatus(omniTravelHist.getStatus().getCode())
				.setContactDetails(omniTravelHist.getContactReasonDetail())
				.setDueDate(omniTravelHist.getDueDate());
		}
	}
	
	private void initGender() throws Throwable {
		ResourceBundle globalMsg = Faces.evaluateExpressionGet("#{globalMsg}");
		genderSelection = omniChannelSync.getParamConfigMap()
			.entrySet()
			.stream()
			.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
			.filter(p -> p.getValue().getParamGroup().equals("SEX"))
			.map(m -> new SelectItem(m.getValue().getParamKey(), globalMsg.getString(m.getValue().getParamValue())))
			.collect(Collectors.toList());
	}
	
	private void initProvince() throws Throwable {
		provinceSelection = omniChannelSync.getProvinceMap()
				.entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getProvinceNameTh().compareTo(c2.getValue().getProvinceNameTh()))
				.map(m -> new SelectItem(m.getValue().getProvinceCode(), m.getValue().getProvinceNameTh()))
				.collect(Collectors.toList());
	}
	
	private void initDestinationCountry() {
		destinationCountrySelection = omniChannelSync.getCountryMap()
				.entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getCountryNameEn().compareTo(c2.getValue().getCountryNameEn()))
				.filter(p -> !p.getValue().getCountryCode().equals("THA"))
				.map(m -> new SelectItem(m.getValue().getCountryCode(), m.getValue().getCountryNameEn()))
				.collect(Collectors.toList());
	}
	
	private void initTravelPurpose() {
		travelPurposeSelection = omniChannelSync.getCategoryMap()
				.entrySet()
				.stream()
				.filter(p -> p.getValue().getParent() != null && p.getValue().getParent().getCode().equals("TRAVEL_PURPOSE"))
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.map(m -> new SelectItem(m.getValue().getCode(), m.getValue().getValue()))
				.collect(Collectors.toList());
		
	}
	
	private void initListSource() throws Throwable {
		listSourceSelection = omniChannelSync.getListSourceMap().entrySet()
			.stream()
			.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
			.map(m -> new SelectItem(m.getKey(), m.getValue().getListSourceValue()))
			.collect(Collectors.toList());
	}
	
	private void initInsurers() throws Throwable {
		insurerSelection = omniChannelSync.getInsurerMap()
				.entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getInsurerName().compareTo(c2.getValue().getInsurerName()))
				.map(m -> new SelectItem(m.getValue().getInsurerCode(), m.getValue().getInsurerName()))
				.collect(Collectors.toList());
	}
	
	private void initInceProducts() throws Throwable {
		inceProductSelection = new ArrayList<>();
		if(StringUtils.isNotBlank(insurerCode)) {
			inceProductSelection = omniChannelSync.getInceProductMap().entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.filter(p -> (p.getValue().getInsurer().getInsurerCode().equals(insurerCode) && p.getValue().getProductType().equals("TRAVEL")))
				.map(m -> new SelectItem(m.getKey(), m.getValue().getProductName()))
				.collect(Collectors.toList());
		} else {
			customerInfo.setInceProductCode(null);
		}
	}
	
	private void initProductPlans() throws Throwable {
		productPlanSelection = new ArrayList<>();
		if(StringUtils.isNotBlank(inceProductCode)) {
			productPlanSelection = omniChannelSync.getProductPlanMap().entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.filter(p -> p.getValue().getProduct().getProductCode().equals(inceProductCode))
				.map(m -> new SelectItem(m.getKey(), m.getValue().getPlanName()))
				.collect(Collectors.toList());
		}
	}
	
	private void initChannels() throws Throwable {
		channelSelection = omniChannelSync.getCategoryMap()
				.entrySet()
				.stream()
				.filter(p -> (p.getValue().getParent() != null && p.getValue().getParent().getCode().equals("OMNI_CHANNEL")))
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.map(m -> new SelectItem(m.getValue().getCode(), m.getValue().getValue()))
				.collect(Collectors.toList());
	}
	
	private void initContactReason() throws Throwable {
		contactReasonSelection = omniChannelSync.getCategoryMap()
				.entrySet()
				.stream()
				.filter(p -> (p.getValue().getParent() != null && p.getValue().getParent().getCode().equals("OMNI_CONTACT_REASON")))
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.map(m -> new SelectItem(m.getValue().getCode(), m.getValue().getValue()))
				.collect(Collectors.toList());
	}
	
	private void initTrackingStatus() throws Throwable {
		trackingStatusSelection = omniChannelSync.getCategoryMap()
				.entrySet()
				.stream()
				.filter(p -> (p.getValue().getParent() != null && p.getValue().getParent().getCode().equals("OMNI_TRACKING_STATUS")))
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.map(m -> new SelectItem(m.getValue().getCode(), m.getValue().getValue()))
				.collect(Collectors.toList());
	}
	
	public OmniTravelCustomerModel getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(OmniTravelCustomerModel customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<SelectItem> getInceProductSelection() {
		return inceProductSelection;
	}

	public void setInceProductSelection(List<SelectItem> inceProductSelection) {
		this.inceProductSelection = inceProductSelection;
	}

	public List<SelectItem> getProductPlanSelection() {
		return productPlanSelection;
	}

	public void setProductPlanSelection(List<SelectItem> productPlanSelection) {
		this.productPlanSelection = productPlanSelection;
	}

	public List<SelectItem> getChannelSelection() {
		return channelSelection;
	}

	public void setChannelSelection(List<SelectItem> channelSelection) {
		this.channelSelection = channelSelection;
	}

	public boolean isTelReadyOnly() {
		return telReadyOnly;
	}

	public void setTelReadyOnly(boolean telReadyOnly) {
		this.telReadyOnly = telReadyOnly;
	}

	public boolean isCitizenReadyOnly() {
		return citizenReadyOnly;
	}

	public void setCitizenReadyOnly(boolean citizenReadyOnly) {
		this.citizenReadyOnly = citizenReadyOnly;
	}

	public List<SelectItem> getGenderSelection() {
		return genderSelection;
	}

	public void setGenderSelection(List<SelectItem> genderSelection) {
		this.genderSelection = genderSelection;
	}

	public List<SelectItem> getProvinceSelection() {
		return provinceSelection;
	}

	public void setProvinceSelection(List<SelectItem> provinceSelection) {
		this.provinceSelection = provinceSelection;
	}

	public List<SelectItem> getTravelPurposeSelection() {
		return travelPurposeSelection;
	}

	public void setTravelPurposeSelection(List<SelectItem> travelPurposeSelection) {
		this.travelPurposeSelection = travelPurposeSelection;
	}

	public List<SelectItem> getDestinationCountrySelection() {
		return destinationCountrySelection;
	}

	public void setDestinationCountrySelection(List<SelectItem> destinationCountrySelection) {
		this.destinationCountrySelection = destinationCountrySelection;
	}

	public Date getCurrDepartDate() {
		return currDepartDate;
	}

	public void setCurrDepartDate(Date currDepartDate) {
		this.currDepartDate = currDepartDate;
	}

	public List<SelectItem> getListSourceSelection() {
		return listSourceSelection;
	}

	public void setListSourceSelection(List<SelectItem> listSourceSelection) {
		this.listSourceSelection = listSourceSelection;
	}

	public List<SelectItem> getInsurerSelection() {
		return insurerSelection;
	}

	public void setInsurerSelection(List<SelectItem> insurerSelection) {
		this.insurerSelection = insurerSelection;
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

	public List<SelectItem> getContactReasonSelection() {
		return contactReasonSelection;
	}

	public void setContactReasonSelection(List<SelectItem> contactReasonSelection) {
		this.contactReasonSelection = contactReasonSelection;
	}

	public List<SelectItem> getTrackingStatusSelection() {
		return trackingStatusSelection;
	}

	public void setTrackingStatusSelection(List<SelectItem> trackingStatusSelection) {
		this.trackingStatusSelection = trackingStatusSelection;
	}

	public String getContactReasonCode() {
		return contactReasonCode;
	}

	public void setContactReasonCode(String contactReasonCode) {
		this.contactReasonCode = contactReasonCode;
	}

	public String getContactReasonTxt() {
		return contactReasonTxt;
	}

	public void setContactReasonTxt(String contactReasonTxt) {
		this.contactReasonTxt = contactReasonTxt;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getCurrArrivalDate() {
		return currArrivalDate;
	}

	public void setCurrArrivalDate(Date currArrivalDate) {
		this.currArrivalDate = currArrivalDate;
	}

	public String getPurposeCode() {
		return purposeCode;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}
	
}
