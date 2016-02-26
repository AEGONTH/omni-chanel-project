package com.adms.web.bean.omni.travel;

import java.math.BigDecimal;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import com.adms.cs.service.CustomerService;
import com.adms.cs.service.CustomerYesRecordService;
import com.adms.cs.service.OmniChLogService;
import com.adms.cs.service.OmniTravelHistService;
import com.adms.entity.cs.Customer;
import com.adms.entity.cs.CustomerYesRecord;
import com.adms.entity.cs.OmniChLog;
import com.adms.entity.cs.OmniTravelHist;
import com.adms.web.base.bean.BaseBean;
import com.adms.web.bean.login.LoginSession;
import com.adms.web.bean.omni.OmniChannelSync;

@ManagedBean
@ViewScoped
public class OmniTravelView extends BaseBean {

	private static final long serialVersionUID = -9056687058722776725L;

	private final String TEL = "TEL";
	private final String CITIZEN = "CITIZEN";
	private final String globalMsgId = "globalMsg";

	private OmniChannelSync omniChannelSync = Faces.evaluateExpressionGet("#{omniChannelSync}");
	private LoginSession loginSession = Faces.evaluateExpressionGet("#{loginSession}");

	private final CustomerService customerService = Faces.evaluateExpressionGet("#{customerService}");
	private final OmniChLogService omniChLogService = Faces.evaluateExpressionGet("#{omniChLogService}");
	private final OmniTravelHistService omniTravelHistService = Faces.evaluateExpressionGet("#{omniTravelHistService}");
	private final CustomerYesRecordService customerYesRecordService = Faces.evaluateExpressionGet("#{customerYesRecordService}");
	
	private OmniTravelCustomerModel customerInfo;
	private OmniTravelPolicyModel policyModel;
	private boolean telReadyOnly;
	private boolean citizenReadyOnly;
	
	private OmniTravelHist selectedTravelHist;
	
	private List<SelectItem> genderSelection;
	private List<SelectItem> provinceSelection;
	
	private List<SelectItem> destinationCountrySelection;
	private List<SelectItem> travelPurposeSelection;
	
	private List<SelectItem> listSourceSelection;
	private List<SelectItem> insurerSelection;
	private List<SelectItem> inceProductSelection;
	private List<SelectItem> productPlanSelection;
	
	private List<SelectItem> channelSelection;
	private List<SelectItem> contactReasonSelection;
	private List<SelectItem> trackingStatusSelection;
	
	@PostConstruct
	private void init() {
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
		if(customerInfo.getArrivalDateTime() != null && customerInfo.getArrivalDateTime().compareTo(customerInfo.getDepartDateTime()) < 0) {
			customerInfo.setArrivalDateTime(null);
		}
	}
	
	public void insurerSelectionListener() throws Throwable {
		initInceProducts();
	}
	
	public void productSelectionListener() throws Throwable {
		initProductPlans();
	}
	
	public void contactReasonSelectionListener() {
		if(StringUtils.isNoneBlank(customerInfo.getContactReason()) && customerInfo.getContactReason().equals("OMNI_CTRS_OTHER")) {
			
		} else {
			customerInfo.setContactReasonOther(null);
		}
	}
	
	public void saveCustomerInfo() throws Throwable {
		OmniChLog omniChLog = null;
		OmniTravelHist omniTravelHist = null;
		
		try {
			if((StringUtils.isNotBlank(customerInfo.getOldTel()) && customerInfo.getOldTel().compareTo(customerInfo.getTel()) != 0)
					|| (StringUtils.isNotBlank(customerInfo.getOldCitizenId()) && customerInfo.getOldCitizenId().compareTo(customerInfo.getCitizenId()) != 0)) {
				Messages.addError("dlgMsg", "Please check Mobile No. or Citizen ID agian.");
				Ajax.update("frmWL:globalMsg");
				return;
			}
			
			if(customerInfo.getOmniTravelHistId() == null) {
				omniChLog = new OmniChLog();
				omniChLog.setCustomer(getValidatedCustomer());
				omniChLog = omniChLogService.add(omniChLog, loginSession.getUsername());
				
				omniTravelHist = new OmniTravelHist();
				omniTravelHist.setChannel(omniChannelSync.getCategoryMap().get(customerInfo.getChannel()))
					.setContactReason(omniChannelSync.getCategoryMap().get(customerInfo.getContactReason()))
					.setContactReasonDetail(StringUtils.isBlank(customerInfo.getContactReasonOther()) ? null : customerInfo.getContactReasonOther())
					.setDetail(StringUtils.isBlank(customerInfo.getContactDetails()) ? null : customerInfo.getContactDetails())
					.setDueDate(customerInfo.getDueDate())
					.setLogDate(new Date())
					.setTravelPurpose(customerInfo.getTravelPurpose())			
					.setCountry(omniChannelSync.getCountryMap().get(customerInfo.getDestinationCountry()))
					.setDepartureDateTime(customerInfo.getDepartDateTime())
					.setDepartureFlightNo(customerInfo.getDepartFlightNo())
					.setArrivalDateTime(customerInfo.getArrivalDateTime())
					.setArrivalFlightNo(customerInfo.getArrivalFlightNo())
					.setProductPlan(omniChannelSync.getProductPlanMap().get(customerInfo.getProductPlan()))
					.setListSource(omniChannelSync.getListSourceMap().get(customerInfo.getListSourceCode()))
					.setStatus(omniChannelSync.getCategoryMap().get(customerInfo.getTrackingStatus()))
					.setOmniChLog(omniChLog)
					.setLatest("Y")
					;
				omniTravelHistService.add(omniTravelHist, loginSession.getUsername());
			} else {
				omniTravelHist = omniTravelHistService.find(customerInfo.getOmniTravelHistId());
				omniChLog = omniTravelHist.getOmniChLog();
				omniChLog.setCustomer(getValidatedCustomer());
				OmniTravelHist newHist = new OmniTravelHist();
				newHist.setChannel(omniChannelSync.getCategoryMap().get(customerInfo.getChannel()))
					.setContactReason(omniChannelSync.getCategoryMap().get(customerInfo.getContactReason()))
					.setContactReasonDetail(StringUtils.isBlank(customerInfo.getContactReasonOther()) ? null : customerInfo.getContactReasonOther())
					.setDetail(StringUtils.isBlank(customerInfo.getContactDetails()) ? null : customerInfo.getContactDetails())
					.setDueDate(customerInfo.getDueDate())
					.setLogDate(new Date())
					.setTravelPurpose(customerInfo.getTravelPurpose())
					.setCountry(omniChannelSync.getCountryMap().get(customerInfo.getDestinationCountry()))
					.setDepartureDateTime(customerInfo.getDepartDateTime())
					.setDepartureFlightNo(customerInfo.getDepartFlightNo())
					.setArrivalDateTime(customerInfo.getArrivalDateTime())
					.setArrivalFlightNo(customerInfo.getArrivalFlightNo())
					.setProductPlan(omniChannelSync.getProductPlanMap().get(customerInfo.getProductPlan()))
					.setListSource(omniChannelSync.getListSourceMap().get(customerInfo.getListSourceCode()))
					.setStatus(omniChannelSync.getCategoryMap().get(customerInfo.getTrackingStatus()))
					.setOmniChLog(omniChLog)
					.setLatest("Y");
				
				omniTravelHistService.update(omniTravelHist.setLatest("N"), loginSession.getUsername());
				omniTravelHistService.add(newHist, loginSession.getUsername());
			}
			EventBus eventBus = EventBusFactory.getDefault().eventBus();
	        eventBus.publish("/refreshDTCustomerTravel", "Successful");
			RequestContext.getCurrentInstance().execute("PF('dlgCustomer').hide()");
			
		} catch(Exception e) {
			throw e;
		}
	}
	
	private boolean savePolicy() {
		CustomerYesRecord policy = new CustomerYesRecord();
		OmniChLog logMotor = null;
		
		try {
			policy.setPolicyNo(policyModel.getPolicyNo());
			BigDecimal premium = new BigDecimal(policyModel.getPremium()).setScale(10, BigDecimal.ROUND_HALF_UP);
			policy.setPremium(premium);
			policy.setEffectiveDate(policyModel.getEffectiveDate());
			policy.setExpireDate(policyModel.getExpireDate());
			policy.setImportDate(new Date());
			policy.setCustomer(policyModel.getCustomer());
			policy.setInsuredCitizenId(policyModel.getCustomer().getCitizenId());
			policy = customerYesRecordService.add(policy, loginSession.getUsername());
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			logMotor = policyModel.getSelectedOmniTravelHist().getOmniChLog();
			logMotor.setPolicy(policy);
			logMotor = omniChLogService.update(logMotor, loginSession.getUsername());
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void doSearchCustomer(String val) throws Throwable {
		String temp = null;
		if(customerInfo.getOmniTravelHistId() == null) {
			if(val.equalsIgnoreCase(TEL)) {
				temp = new String(customerInfo.getTel());
				setDialogDataFromList(findVisibleCustomer(customerInfo.getTel(), null), val);
				customerInfo.setTel(temp);
				customerInfo.setOldTel(temp);
//				citizenId = customerInfo.getCitizenId();
			} else if(val.equalsIgnoreCase(CITIZEN)) {
				temp = new String(customerInfo.getCitizenId());
				setDialogDataFromList(findVisibleCustomer(null, customerInfo.getCitizenId()), val);
				customerInfo.setCitizenId(temp);
				customerInfo.setOldCitizenId(temp);
//				tel = customerInfo.getTel();
			}
			Ajax.update("frmDlg:gridCustomerInfo");
		}
	}
	
	public void doFlowProcess(String var) throws Throwable {
		//Button: Next, Back, Save
		//Process Flow: search -> result -> policyEntry -> save
		boolean flag = false;
		final String msgId = "searchCustomerMsg";
		
		if(var.equalsIgnoreCase("next")) {
			if(policyModel.getCurrentFlow().equalsIgnoreCase("search")) {
				// Validate
				if(StringUtils.isBlank(policyModel.getSearchTel()) && StringUtils.isBlank(policyModel.getSearchCitizenId())) {
					Messages.addError(msgId, "Please fills some data.");
				} else {
					List<Customer> customers = findVisibleCustomer(policyModel.getSearchTel(), policyModel.getSearchCitizenId());
					if(customers != null && !customers.isEmpty() && customers.size() == 1) {
						policyModel.setCustomer(customers.get(0));
						List<OmniTravelHist> histList = findOmniMotorHistByCustomerId(policyModel.getSearchCitizenId());
						if(histList != null && histList.size() > 0) {
							policyModel.setOmniTravelHists(histList);
							policyModel.setSelectedOmniTravelHist(null);
							flag = true;
						} else {
							policyModel.setOmniTravelHists(null);
							Messages.addWarn(msgId, "Not found any record for this customer");
						}
					} else {
						Messages.addWarn(msgId, "Not found or found more than 1 customer.");
						policyModel.setCustomer(null);
						policyModel.setOmniTravelHists(null);
					}
				}
				policyModel.setCurrentFlow(flag ? "result" : policyModel.getCurrentFlow());
			} else if(policyModel.getCurrentFlow().equalsIgnoreCase("result")) {
				//Validate
				if(policyModel.getSelectedOmniTravelHist() == null) {
					Messages.addError(msgId, "Please select data.");
				} else {
					policyModel.setPolicyNo(null);
					policyModel.setPremium(null);
					policyModel.setEffectiveDate(policyModel.getSelectedOmniTravelHist().getDepartureDateTime());
					policyModel.setExpireDate(policyModel.getSelectedOmniTravelHist().getArrivalDateTime());
					policyModel.setCustomer(policyModel.getSelectedOmniTravelHist().getOmniChLog().getCustomer());
					flag = true;
				}
				policyModel.setCurrentFlow(flag ? "policyEntry" : policyModel.getCurrentFlow());
			} else {
				//ERR
			}
			
			Ajax.update("frmPolicy:panelFlow");
			Ajax.update("frmPolicy:policyMsg");
		} else if(var.equalsIgnoreCase("back")) {
			policyModel.setCurrentFlow(policyModel.getCurrentFlow().equalsIgnoreCase("result") ? "search" 
					: policyModel.getCurrentFlow().equalsIgnoreCase("policyEntry") ? "result" : "");
		} else if(var.equals("save")) {
			//Validate
			if(StringUtils.isNotBlank(policyModel.getPolicyNo())
					&& policyModel.getPremium() != null
					&& policyModel.getEffectiveDate() != null
					&& policyModel.getExpireDate() != null) {
				flag = true;
			} else {
				Messages.addError(msgId, "All fields are required!");
				Ajax.update("frmPolicy:policyMsg");
			}
			
			if(flag) {
				if(savePolicy()) {
					RequestContext.getCurrentInstance().execute("PF('dlgPolicy').hide()");
					Messages.addInfo(globalMsgId, "Save Suceesfully");
					Ajax.update(globalMsgId);
				} else {
					Messages.addError(msgId, "Something went wrong!! Please contact your system admin.");
					Ajax.update("frmPolicy:policyMsg");
				}
			}
		}
    }
	
	public void onClickAddNew() throws Throwable {
		customerInfo = new OmniTravelCustomerModel();
		telReadyOnly = false;
		citizenReadyOnly = false;
	}
	
	public void onClickAddPolicy() throws Throwable {
		RequestContext rc = RequestContext.getCurrentInstance();
		policyModel = new OmniTravelPolicyModel();
		policyModel.setCurrentFlow("search");
		rc.reset("frmPolicy");
	}
	
	public void onRowDataSelect(SelectEvent event) throws Throwable {
		customerInfo = new OmniTravelCustomerModel();
		customerInfo.setOmniTravelHistId(this.selectedTravelHist.getId());
		customerInfo.setCustomerInfoForDialog(selectedTravelHist.getOmniChLog().getCustomer());
		customerInfo.setCaseData(selectedTravelHist);
		initInceProducts();
		initProductPlans();
	}

	private List<Customer> findVisibleCustomer(String mobileNo, String citizenId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if(StringUtils.isNotBlank(mobileNo)) criteria.add(Restrictions.eq("mobileNo", mobileNo));
		if(StringUtils.isNotBlank(citizenId)) criteria.add(Restrictions.eq("citizenId", citizenId));
		criteria.add(Restrictions.eq("visible", "Y"));
		return customerService.findByCriteria(criteria);
	}
	
	private List<OmniTravelHist> findOmniMotorHistByCustomerId(String citizenId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(OmniTravelHist.class);
		criteria.createCriteria("omniChLog", JoinType.INNER_JOIN)
			.createCriteria("customer", JoinType.INNER_JOIN)
			.add(Restrictions.eq("citizenId", citizenId));
		criteria.add(Restrictions.eq("latest", "Y"));
		criteria.addOrder(Order.asc("id"));
		return omniTravelHistService.findByCriteria(criteria);
	}

	private Customer getValidatedCustomer() throws Exception {
		boolean isChange = false;
		boolean isNew = false;
		
		if(customerInfo.getCustomer() != null) {
			if(customerInfo.getCustomer().getFirstName().toUpperCase().compareTo(customerInfo.getFirstName().toUpperCase()) != 0) {
				isChange = true;
			}
			if(customerInfo.getCustomer().getLastName().toUpperCase().compareTo(customerInfo.getLastName().toUpperCase()) != 0) {
				isChange = true;
			}
			if(customerInfo.getCustomer().getMobileNo().compareTo(customerInfo.getTel()) != 0) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customerInfo.getCustomer().getOtherNo()) && StringUtils.isBlank(customerInfo.getOtherTel()))
					&& ((StringUtils.isBlank(customerInfo.getCustomer().getOtherNo()) && !StringUtils.isBlank(customerInfo.getOtherTel()))
							|| (!StringUtils.isBlank(customerInfo.getCustomer().getOtherNo()) && StringUtils.isBlank(customerInfo.getOtherTel()))
							|| customerInfo.getCustomer().getOtherNo().compareTo(customerInfo.getOtherTel()) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customerInfo.getCustomer().getCitizenId()) && StringUtils.isBlank(customerInfo.getCitizenId()))
					&& ((StringUtils.isBlank(customerInfo.getCustomer().getCitizenId()) && !StringUtils.isBlank(customerInfo.getCitizenId()))
						|| (!StringUtils.isBlank(customerInfo.getCustomer().getCitizenId()) && StringUtils.isBlank(customerInfo.getCitizenId()))
						|| customerInfo.getCustomer().getCitizenId().compareTo(customerInfo.getCitizenId()) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customerInfo.getCustomer().getPassportId()) && StringUtils.isBlank(customerInfo.getPassportId()))
					&& ((StringUtils.isBlank(customerInfo.getCustomer().getPassportId()) && !StringUtils.isBlank(customerInfo.getPassportId()))
						|| (!StringUtils.isBlank(customerInfo.getCustomer().getPassportId()) && StringUtils.isBlank(customerInfo.getPassportId()))
						|| customerInfo.getCustomer().getPassportId().compareTo(customerInfo.getPassportId()) != 0)) {
				isChange = true;
			}
			if(!(customerInfo.getCustomer().getGender() == null && StringUtils.isBlank(customerInfo.getGender()))
					&& ((customerInfo.getCustomer().getGender() == null && !StringUtils.isBlank(customerInfo.getGender())) 
						|| (customerInfo.getCustomer().getGender().getParamKey() != null && StringUtils.isBlank(customerInfo.getGender()))
						|| customerInfo.getCustomer().getGender().getParamKey().compareTo(customerInfo.getGender()) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customerInfo.getCustomer().getEmail()) && StringUtils.isBlank(customerInfo.getEmail()))
					&& ((StringUtils.isBlank(customerInfo.getCustomer().getEmail()) && !StringUtils.isBlank(customerInfo.getEmail()))
						|| (!StringUtils.isBlank(customerInfo.getCustomer().getEmail()) && StringUtils.isBlank(customerInfo.getEmail()))
						|| customerInfo.getCustomer().getEmail().compareTo(customerInfo.getEmail()) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customerInfo.getCustomer().getAddress1()) && StringUtils.isBlank(customerInfo.getAddress1()))
					&& ((StringUtils.isBlank(customerInfo.getCustomer().getAddress1()) && !StringUtils.isBlank(customerInfo.getAddress1()))
						|| (!StringUtils.isBlank(customerInfo.getCustomer().getAddress1()) && StringUtils.isBlank(customerInfo.getAddress1()))
						|| customerInfo.getCustomer().getAddress1().compareTo(customerInfo.getAddress1()) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customerInfo.getCustomer().getAddress2()) && StringUtils.isBlank(customerInfo.getAddress2()))
					&& ((StringUtils.isBlank(customerInfo.getCustomer().getAddress2()) && !StringUtils.isBlank(customerInfo.getAddress2()))
						|| (!StringUtils.isBlank(customerInfo.getCustomer().getAddress2()) && StringUtils.isBlank(customerInfo.getAddress2()))
						|| customerInfo.getCustomer().getAddress2().compareTo(customerInfo.getAddress2()) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customerInfo.getCustomer().getAddress3()) && StringUtils.isBlank(customerInfo.getAddress3()))
					&& ((StringUtils.isBlank(customerInfo.getCustomer().getAddress3()) && !StringUtils.isBlank(customerInfo.getAddress3()))
						|| (!StringUtils.isBlank(customerInfo.getCustomer().getAddress3()) && StringUtils.isBlank(customerInfo.getAddress3()))
						|| customerInfo.getCustomer().getAddress3().compareTo(customerInfo.getAddress3()) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customerInfo.getCustomer().getPostCode()) && StringUtils.isBlank(customerInfo.getPostCode()))
					&& ((StringUtils.isBlank(customerInfo.getCustomer().getPostCode()) && !StringUtils.isBlank(customerInfo.getPostCode()))
						|| (!StringUtils.isBlank(customerInfo.getCustomer().getPostCode()) && StringUtils.isBlank(customerInfo.getPostCode()))
						|| customerInfo.getCustomer().getPostCode().compareTo(customerInfo.getPostCode()) != 0)){
				isChange = true;
			}
			if(!(customerInfo.getCustomer().getProvince() == null && StringUtils.isBlank(customerInfo.getProvince()))
					&& ((customerInfo.getCustomer().getProvince() == null && !StringUtils.isBlank(customerInfo.getProvince())) 
						|| (customerInfo.getCustomer().getProvince() != null && StringUtils.isBlank(customerInfo.getProvince()))
						|| customerInfo.getCustomer().getProvince().getProvinceCode().compareTo(customerInfo.getProvince()) != 0)) {
				isChange = true;
			}
		} else {
			isNew = true;
		}
		
		if(isChange) {
			customerService.update(customerInfo.getCustomer().setVisible("N"), loginSession.getUsername());
		}
		
		if(isChange || isNew) {
			return customerService.add(
					new Customer()
						.setMobileNo(customerInfo.getTel())
						.setCitizenId(customerInfo.getCitizenId())
						.setFirstName(customerInfo.getFirstName().toUpperCase().trim())
						.setLastName(customerInfo.getLastName().toUpperCase().trim())
						.setGender(omniChannelSync.getParamConfigMap().get(customerInfo.getGender()))
						.setEmail(StringUtils.isBlank(customerInfo.getEmail()) ? null : customerInfo.getEmail())
						.setAddress1(StringUtils.isBlank(customerInfo.getAddress1()) ? null : customerInfo.getAddress1())
						.setAddress2(StringUtils.isBlank(customerInfo.getAddress2()) ? null : customerInfo.getAddress2())
						.setAddress3(StringUtils.isBlank(customerInfo.getAddress3()) ? null : customerInfo.getAddress3())
						.setPostCode(StringUtils.isBlank(customerInfo.getPostCode()) ? null : customerInfo.getPostCode())
						.setProvince(omniChannelSync.getProvinceMap().get(customerInfo.getProvince()))
						.setVisible("Y")
					, loginSession.getUsername());
		} else {
			return customerInfo.getCustomer();
		}
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
		if(StringUtils.isNotBlank(customerInfo.getInsurerCode())) {
			inceProductSelection = omniChannelSync.getInceProductMap().entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.filter(p -> (p.getValue().getInsurer().getInsurerCode().equals(customerInfo.getInsurerCode()) && p.getValue().getProductType().equals("TRAVEL")))
				.map(m -> new SelectItem(m.getKey(), m.getValue().getProductName()))
				.collect(Collectors.toList());
		} else {
			customerInfo.setInceProductCode(null);
		}
	}
	
	private void initProductPlans() throws Throwable {
		productPlanSelection = new ArrayList<>();
		if(StringUtils.isNotBlank(customerInfo.getInceProductCode())) {
			productPlanSelection = omniChannelSync.getProductPlanMap().entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.filter(p -> p.getValue().getProduct().getProductCode().equals(customerInfo.getInceProductCode()))
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

	public OmniTravelHist getSelectedTravelHist() {
		return selectedTravelHist;
	}

	public void setSelectedTravelHist(OmniTravelHist selectedTravelHist) {
		this.selectedTravelHist = selectedTravelHist;
	}

	public OmniTravelPolicyModel getPolicyModel() {
		return policyModel;
	}

	public void setPolicyModel(OmniTravelPolicyModel policyModel) {
		this.policyModel = policyModel;
	}
	
}
