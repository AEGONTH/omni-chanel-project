package com.adms.web.bean.omni.motor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
import com.adms.cs.service.OmniMotorHistService;
import com.adms.entity.cs.Customer;
import com.adms.entity.cs.CustomerYesRecord;
import com.adms.entity.cs.OmniChLog;
import com.adms.entity.cs.OmniMotorHist;
import com.adms.web.base.bean.BaseBean;
import com.adms.web.bean.login.LoginSession;
import com.adms.web.bean.omni.OmniChannelSync;

@ManagedBean
@ViewScoped
public class OmniMotorView extends BaseBean {

	private static final long serialVersionUID = -1006785263944871332L;

	private final OmniMotorHistService omniMotorHistService = Faces.evaluateExpressionGet("#{omniMotorHistService}");
	private final OmniChLogService omniChLogService = Faces.evaluateExpressionGet("#{omniChLogService}");
	private final CustomerYesRecordService customerYesRecordService = Faces.evaluateExpressionGet("#{customerYesRecordService}");
	private final CustomerService customerService = Faces.evaluateExpressionGet("#{customerService}");

	private OmniChannelSync omniChannelSync = Faces.evaluateExpressionGet("#{omniChannelSync}");
	
	@ManagedProperty("#{loginSession}")
	private LoginSession loginSession;
	
	public final String TEL = "TEL";
	public final String CITIZEN = "CITIZEN";
	
	private List<OmniMotorHist> omniMotorHistList;
	
	private OmniMotorHist selectedMotor;
	
	private final String globalMsgId = "globalMsg";
	
	private Long logId;
	private Long logHistId;
	
	private String oldCitizenId;
	private String citizenId;
	private boolean citizenReadyOnly;
	
	private String cFirstName;
	private String cLastName;
	private String oldTel;
	private String tel;
	private boolean telReadyOnly;
	private String email;
	
	private String address1;
	private String address2;
	private String address3;
	private String postCode;
	
	private String otherTel;
	
	private Customer customer;
	
	private String policyNo;
	private Double sumAssured;
	private Double premium;
	private Date effectiveDate;
	private Date expireDate;
	
	private List<SelectItem> provinceSelection;
	private String province;
	
	private List<SelectItem> sexSelection;
	private String sex;
	
	private List<SelectItem> motorBrandSelection;
	private String motorBrand;
	
	private List<SelectItem> brandModelSelection;
	private String brandModel;
	
	private List<SelectItem> carYearSelection;
	private Integer carYear;
	
	private List<SelectItem> listSourceSelection;
	private String listSource;
	
	private List<SelectItem> insurerSelection;
	private String insurerCode;
	
	private List<SelectItem> inceProductSelection;
	private String inceProductCode;
	
	private List<SelectItem> productPlanSelection;
	private String productPlan;
	
	private List<SelectItem> channelSelection;
	private String channel;
	
	private List<SelectItem> contactReasonSelection;
	private String contactReason;
	private String contactReasonOther;
	
	private List<SelectItem> trackingStatusSelection;
	private String trackingStatus;
	
	private String contactDetails;
	
	private Date dueDate;
	
	//POLICY MODEL
	private String searchTel;
	private String searchCitizen;
	private OmniMotorPolicyModel policyModel;
	private String currentFlow;
	
	@PostConstruct
	private void init() {
		
	}
	
	public void initialData() throws Throwable {
		initSex();
		
		initBrand();
		initModelByBrand(motorBrand);
		
		initCarYears();
		initListSource();
		initInsurers();
		initInceProducts();
		initProductPlans();
		
		initChannels();
		initContactReason();
		initTrackingStatus();
		
		initProvince();
	}
	
	public void brandSelectionListener() throws Throwable {
		initModelByBrand(this.motorBrand);
	}
	
	public void insurerSelectionListener() throws Throwable {
		initInceProducts();
	}
	
	public void productSelectionListener() throws Throwable {
		initProductPlans();
	}
	
	public void contactReasonSelectionListener() {
		if(StringUtils.isNoneBlank(contactReason) && contactReason.equals("OMNI_CTRS_OTHER")) {
			
		} else {
			contactReasonOther = null;
		}
	}
	
	public void onClickAddNew() throws Throwable {
		customer = null;
		telReadyOnly = false;
		citizenReadyOnly = false;
		trackingStatus = null;
		contactReasonOther = null;
		RequestContext.getCurrentInstance().reset("frmDlg");
		setDataToDialog(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public void onClickAddPolicy() throws Throwable {
		RequestContext rc = RequestContext.getCurrentInstance();
		this.searchCitizen = null;
		this.searchTel = null;
		policyModel = new OmniMotorPolicyModel();
		setCurrentFlow("search");
		rc.reset("frmPolicy");
	}
	
	public void onRowSelect(SelectEvent event) throws NumberFormatException, Throwable {
		OmniMotorHist logHist = this.selectedMotor;
		logHistId = logHist.getId();
		setDataToDialog(logHist.getOmniChLog().getId()
				, logHist.getOmniChLog().getCustomer()
				, logHist.getCar().getParent().getCode()
				, logHist.getCar().getCode()
				, new Integer(logHist.getCarYear())
				, logHist.getListSource().getListSourceCode()
				, logHist.getProductPlan().getProduct().getInsurer().getInsurerCode()
				, logHist.getProductPlan().getProduct().getProductCode()
				, logHist.getProductPlan().getPlanCode()
				, logHist.getChannel() == null ? null : logHist.getChannel().getCode()
				, logHist.getContactReason() == null ? null : logHist.getContactReason().getCode()
				, logHist.getStatus() == null ? null : logHist.getStatus().getCode()
				, logHist.getDetail()
				, logHist.getDueDate());
	}
	
	public void doFlowProcess(String var) throws Throwable {
		//Button: Next, Back, Save
		//Process Flow: search -> result -> policyEntry -> save
		boolean flag = false;
		final String msgId = "searchCustomerMsg";
		
		if(var.equalsIgnoreCase("next")) {
			if(currentFlow.equalsIgnoreCase("search")) {
				// Validate
				if(StringUtils.isBlank(this.searchTel) && StringUtils.isBlank(this.searchCitizen)) {
					Messages.addError(msgId, "Please fills some data.");
				} else {
					List<Customer> customers = findVisibleCustomer(searchTel, searchCitizen);
					if(customers != null && customers.size() == 1) {
						policyModel.setCustomer(customers.get(0));
						List<OmniMotorHist> histList = findOmniMotorHistByCustomerId(searchCitizen);
						if(histList != null && histList.size() > 0) {
							policyModel.setLogMotors(histList);
							policyModel.setSelectedOmniMotorHist(null);
							flag = true;
						} else {
							policyModel.setLogMotors(null);
							Messages.addWarn(msgId, "Not found any record for this customer");
						}
					} else {
						Messages.addWarn(msgId, "Not found or found more than 1 customer.");
						policyModel.setCustomer(null);
						policyModel.setLogMotors(null);
					}
				}
				currentFlow = flag ? "result" : currentFlow;
			} else if(currentFlow.equalsIgnoreCase("result")) {
				//Validate
				if(policyModel.getSelectedOmniMotorHist() == null) {
					Messages.addError(msgId, "Please select data.");
				} else {
					policyModel.setPolicyNo(null);
					policyModel.setPremium(null);
					policyModel.setEffectiveDate(null);
					policyModel.setExpireDate(null);
					
					policyModel.setCustomer(policyModel.getSelectedOmniMotorHist().getOmniChLog().getCustomer());
					flag = true;
				}
				
				currentFlow = flag ? "policyEntry" : currentFlow;
			} else {
				//ERR
			}
			
			Ajax.update("frmPolicy:panelFlow");
			Ajax.update("frmPolicy:policyMsg");
		} else if(var.equalsIgnoreCase("back")) {
			currentFlow = currentFlow.equalsIgnoreCase("result") ? "search" 
					: currentFlow.equalsIgnoreCase("policyEntry") ? "result" : "";
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
	
	public void doSearchCustomer(String val) throws Throwable {
		String temp = null;
		if(this.logId == null) {
			if(val.equalsIgnoreCase(TEL)) {
				temp = new String(this.tel);
				setDialogDataFromList(findVisibleCustomer(this.tel, null), val);
				this.tel = temp;
				this.oldTel = new String(this.tel);
			} else if(val.equalsIgnoreCase(CITIZEN)) {
				temp = new String(this.citizenId);
				setDialogDataFromList(findVisibleCustomer(null, this.citizenId), val);
				this.citizenId = temp;
				this.oldCitizenId = new String(this.citizenId);
			}
			Ajax.update("frmDlg:gridCustomerInfo");
		}
	}
	
	public void saveCustomerInfo() throws Throwable {
		OmniChLog omniLogMotor = null;
		OmniMotorHist hist = null;

		if((StringUtils.isNotBlank(oldTel) && oldTel.compareTo(tel) != 0)
				|| (StringUtils.isNotBlank(oldCitizenId) && oldCitizenId.compareTo(citizenId) != 0)) {
			Messages.addError("dlgMsg", "Please check Mobile No. or Citizen ID agian.");
			Ajax.update("frmWL:globalMsg");
			return;
		}
		
		if(logId == null) {
			omniLogMotor = new OmniChLog();
			omniLogMotor.setCustomer(getValidatedCustomer());
			omniLogMotor = omniChLogService.add(omniLogMotor, loginSession.getUsername());
			
			hist = new OmniMotorHist();
			hist.setChannel(omniChannelSync.getCategoryMap().get(channel))
				.setContactReason(omniChannelSync.getCategoryMap().get(contactReason))
				.setContactReasonDetail(StringUtils.isBlank(contactReasonOther) ? null : contactReasonOther)
				.setDetail(StringUtils.isBlank(contactDetails) ? null : contactDetails)
				.setDueDate(dueDate)
				.setLogDate(new Date())
				.setCar(omniChannelSync.getCategoryMap().get(this.brandModel))
				.setCarYear(this.carYear.toString())
				.setOmniChLog(omniLogMotor)
				.setListSource(omniChannelSync.getListSourceMap().get(listSource))
				.setProductPlan(omniChannelSync.getProductPlanMap().get(productPlan))
				.setStatus(omniChannelSync.getCategoryMap().get(trackingStatus))
				.setLatest("Y");
			omniMotorHistService.add(hist, loginSession.getUsername());
		} else {
			hist = omniMotorHistService.find(logHistId);
			OmniMotorHist newHist = new OmniMotorHist();
			newHist.setChannel(omniChannelSync.getCategoryMap().get(channel))
				.setContactReason(omniChannelSync.getCategoryMap().get(contactReason))
				.setContactReasonDetail(StringUtils.isBlank(contactReasonOther) ? null : contactReasonOther)
				.setDetail(StringUtils.isBlank(contactDetails) ? null : contactDetails)
				.setDueDate(dueDate)
				.setLogDate(new Date());
			omniLogMotor = hist.getOmniChLog();
			omniLogMotor.setCustomer(getValidatedCustomer());
			newHist.setOmniChLog(omniLogMotor)
				.setCar(omniChannelSync.getCategoryMap().get(this.brandModel))
				.setCarYear(this.carYear.toString())
				.setListSource(omniChannelSync.getListSourceMap().get(listSource))
				.setProductPlan(omniChannelSync.getProductPlanMap().get(productPlan))
				.setStatus(omniChannelSync.getCategoryMap().get(trackingStatus))
				.setLatest("Y");
			
			hist.setLatest("N");
			omniMotorHistService.update(hist, loginSession.getUsername());
			omniMotorHistService.add(newHist, loginSession.getUsername());
		}
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/refreshDTCustomerWL", "Successful");
		RequestContext.getCurrentInstance().execute("PF('dlgCustomer').hide()");
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
			logMotor = policyModel.getSelectedOmniMotorHist().getOmniChLog();
			logMotor.setPolicy(policy);
			logMotor = omniChLogService.update(logMotor, loginSession.getUsername());
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void setDialogDataFromList(List<Customer> list, String searchField) {
		if(list != null && list.size() == 1) {
			customer = list.get(0);
			if(searchField.equalsIgnoreCase(TEL)) {
				telReadyOnly = true;
			} else if(searchField.equalsIgnoreCase(CITIZEN)) {
				citizenReadyOnly = true;
			}
		} else {
			customer = null;
			Messages.addInfo(globalMsgId, "Not found.");
			Ajax.update("frmDlg:globalMsg");
		}
		setCustomerInfoForDialog(customer);
	}

	private void setDataToDialog(
			Long logId
			, Customer customer
			, String motorBrand, String brandModel, Integer carYear
			, String listSourceCode
			, String insurerCode, String inceProductCode, String productPlan
			, String channel, String contactReason, String trackingStatus
			, String contactDetails, Date dueDate) throws Throwable {
		this.oldTel = null;
		this.oldCitizenId = null;
		this.logId = logId;
		
		this.customer = customer;
		setCustomerInfoForDialog(customer);
		
		initModelByBrand(motorBrand);
		this.motorBrand = motorBrand;
		
		this.brandModel = brandModel;
		this.carYear = carYear;
		
		this.listSource = listSourceCode;
		this.insurerCode = insurerCode;
		
		initInceProducts();
		this.inceProductCode = inceProductCode;

		initProductPlans();
		this.productPlan = productPlan;
		
		this.channel = channel;
		this.contactReason = contactReason;
		this.trackingStatus = trackingStatus;
		this.contactDetails = contactDetails;
		this.dueDate = dueDate;
	}
	
	private void setCustomerInfoForDialog(Customer customer) {
		if(customer != null) {
			this.tel = customer.getMobileNo();
			this.otherTel = customer.getOtherNo();
			this.citizenId = customer.getCitizenId();
			this.cFirstName = customer.getFirstName();
			this.cLastName = customer.getLastName();
			this.sex = customer.getGender() == null ? null : customer.getGender().getParamKey();
			this.email = customer.getEmail();
			this.address1 = customer.getAddress1();
			this.address2 = customer.getAddress2();
			this.address3 = customer.getAddress3();
			this.postCode = customer.getPostCode();
			this.province = customer.getProvince() == null ? null : customer.getProvince().getProvinceCode();
		} else {
			this.tel = null;
			this.otherTel = null;
			this.citizenId = null;
			this.cFirstName = null;
			this.cLastName = null;
			this.sex = null;
			this.email = null;
			this.address1 = null;
			this.address2 = null;
			this.address3 = null;
			this.postCode = null;
			this.province = null;
		}
	}

	private Customer getValidatedCustomer() throws Exception {
		boolean isChange = false;
		boolean isNew = false;
		if(customer != null) {
			if(customer.getFirstName().toUpperCase().compareTo(cFirstName.toUpperCase()) != 0) {
				isChange = true;
			}
			if(customer.getLastName().toUpperCase().compareTo(cLastName.toUpperCase()) != 0) {
				isChange = true;
			}
			if(customer.getMobileNo().compareTo(tel) != 0) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getOtherNo()) && StringUtils.isBlank(otherTel))
					&& ((StringUtils.isBlank(customer.getOtherNo()) && !StringUtils.isBlank(otherTel))
							|| (!StringUtils.isBlank(customer.getOtherNo()) && StringUtils.isBlank(otherTel))
							|| customer.getOtherNo().compareTo(otherTel) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getCitizenId()) && StringUtils.isBlank(citizenId))
					&& ((StringUtils.isBlank(customer.getCitizenId()) && !StringUtils.isBlank(citizenId))
						|| (!StringUtils.isBlank(customer.getCitizenId()) && StringUtils.isBlank(citizenId))
						|| customer.getCitizenId().compareTo(citizenId) != 0)) {
				isChange = true;
			}
			if(!(customer.getGender() == null && StringUtils.isBlank(sex))
					&& ((customer.getGender() == null && !StringUtils.isBlank(sex)) 
						|| (customer.getGender() != null && StringUtils.isBlank(sex))
						|| customer.getGender().getParamKey().compareTo(sex) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getEmail()) && StringUtils.isBlank(email))
					&& ((StringUtils.isBlank(customer.getEmail()) && !StringUtils.isBlank(email))
						|| (!StringUtils.isBlank(customer.getEmail()) && StringUtils.isBlank(email))
						|| customer.getEmail().compareTo(email) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getAddress1()) && StringUtils.isBlank(address1))
					&& ((StringUtils.isBlank(customer.getAddress1()) && !StringUtils.isBlank(address1))
						|| (!StringUtils.isBlank(customer.getAddress1()) && StringUtils.isBlank(address1))
						|| customer.getAddress1().compareTo(address1) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getAddress2()) && StringUtils.isBlank(address2))
					&& ((StringUtils.isBlank(customer.getAddress2()) && !StringUtils.isBlank(address2))
						|| (!StringUtils.isBlank(customer.getAddress2()) && StringUtils.isBlank(address2))
						|| customer.getAddress2().compareTo(address2) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getAddress3()) && StringUtils.isBlank(address3))
					&& ((StringUtils.isBlank(customer.getAddress3()) && !StringUtils.isBlank(address3))
						|| (!StringUtils.isBlank(customer.getAddress3()) && StringUtils.isBlank(address3))
						|| customer.getAddress3().compareTo(address3) != 0)) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getPostCode()) && StringUtils.isBlank(postCode))
					&& ((StringUtils.isBlank(customer.getPostCode()) && !StringUtils.isBlank(postCode))
						|| (!StringUtils.isBlank(customer.getPostCode()) && StringUtils.isBlank(postCode))
						|| customer.getPostCode().compareTo(postCode) != 0)){
				isChange = true;
			}
			if(!(customer.getProvince() == null && StringUtils.isBlank(province))
					&& ((customer.getProvince() == null && !StringUtils.isBlank(province)) 
						|| (customer.getProvince() != null && StringUtils.isBlank(province))
						|| customer.getProvince().getProvinceCode().compareTo(province) != 0)) {
				isChange = true;
			}
		} else {
			isNew = true;
		}
		
		if(isChange) {
			customerService.update(customer.setVisible("N"), loginSession.getUsername());
		}
		
		if(isChange || isNew) {
			return customerService.add(
					new Customer()
						.setMobileNo(tel)
						.setCitizenId(citizenId)
						.setFirstName(cFirstName.toUpperCase().trim())
						.setLastName(cLastName.toUpperCase().trim())
						.setGender(omniChannelSync.getParamConfigMap().get(sex))
//						.setGender(paramConfigService.find(new ParamConfig().setParamKey(sex)).get(0))
						.setEmail(StringUtils.isBlank(email) ? null : email)
						.setAddress1(StringUtils.isBlank(address1) ? null : address1)
						.setAddress2(StringUtils.isBlank(address2) ? null : address2)
						.setAddress3(StringUtils.isBlank(address3) ? null : address3)
						.setPostCode(StringUtils.isBlank(postCode) ? null : postCode)
						.setProvince(omniChannelSync.getProvinceMap().get(province))
						.setVisible("Y")
					, loginSession.getUsername());
		} else {
			return customer;
		}
	}

	private List<Customer> findVisibleCustomer(String mobileNo, String citizenId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if(StringUtils.isNotBlank(mobileNo)) criteria.add(Restrictions.eq("mobileNo", mobileNo));
		if(StringUtils.isNotBlank(citizenId)) criteria.add(Restrictions.eq("citizenId", citizenId));
		criteria.add(Restrictions.eq("visible", "Y"));
		return customerService.findByCriteria(criteria);
	}
	
	private List<OmniMotorHist> findOmniMotorHistByCustomerId(String citizenId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(OmniMotorHist.class);
		criteria.createCriteria("omniChLog", JoinType.INNER_JOIN)
			.createCriteria("customer", JoinType.INNER_JOIN)
			.add(Restrictions.eq("citizenId", citizenId));
		criteria.add(Restrictions.eq("latest", "Y"));
		criteria.addOrder(Order.asc("id"));
		return omniMotorHistService.findByCriteria(criteria);
	}

	private void initSex() throws Throwable {
		ResourceBundle globalMsg = Faces.evaluateExpressionGet("#{globalMsg}");
		sexSelection = omniChannelSync.getParamConfigMap()
				.entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.filter(p -> p.getValue().getParamGroup().equals("SEX"))
				.map(m -> new SelectItem(m.getValue().getParamKey(), globalMsg.getString(m.getValue().getParamValue())))
				.collect(Collectors.toList());
	}
	
	private void initBrand() throws Throwable {
		String parent = "OMNI_MOTOR_CAT";
		motorBrandSelection = omniChannelSync.getCategoryMap()
				.entrySet()
				.stream()
				.filter(p -> (p.getValue().getParent() != null && p.getValue().getParent().getCode().equals(parent)))
				.sorted((c1, c2) -> c1.getValue().getCode().compareTo(c2.getValue().getCode()))
				.map(m -> new SelectItem(m.getValue().getCode(), m.getValue().getValue()))
				.collect(Collectors.toList());
	}
	
	private void initModelByBrand(String brandCode) throws Throwable {
		brandModel = null;
		
		brandModelSelection = omniChannelSync.getCategoryMap()
				.entrySet()
				.stream()
				.filter(p -> (p.getValue().getParent() != null && p.getValue().getParent().getCode().equals(brandCode)))
				.sorted((c1 ,c2) -> c1.getValue().getCode().compareTo(c2.getValue().getCode()))
				.map(m -> new SelectItem(m.getValue().getCode(), m.getValue().getValue()))
				.collect(Collectors.toList());
	}
	
	private void initCarYears() throws Throwable {
		carYear = null;
		carYearSelection = new ArrayList<>();
		
		LocalDate now = LocalDate.now();
		LocalDate begin =LocalDate.of(1990, 1, 1);
		
		while(now.getYear() >= begin.getYear()) {
			String label = now.getYear() + " (" + (now.getYear() + 543) + ")";
			carYearSelection.add(new SelectItem(now.getYear(), label));
			now = now.minusYears(1);
		}
	}
	
	private void initInsurers() throws Throwable {
		insurerCode = null;
		insurerSelection = new ArrayList<>();
		
		insurerSelection = omniChannelSync.getInsurerMap()
				.entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getInsurerName().compareTo(c2.getValue().getInsurerName()))
				.map(m -> new SelectItem(m.getValue().getInsurerCode(), m.getValue().getInsurerName()))
				.collect(Collectors.toList());
	}
	
	private void initInceProducts() throws Throwable {
		inceProductCode = null;
		inceProductSelection = new ArrayList<>();
		if(StringUtils.isNotBlank(insurerCode)) {
			inceProductSelection = omniChannelSync.getInceProductMap().entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.filter(p -> (p.getValue().getInsurer().getInsurerCode().equals(insurerCode) && p.getValue().getProductType().equals("MOTOR")))
				.map(m -> new SelectItem(m.getKey(), m.getValue().getProductName()))
				.collect(Collectors.toList());
		}
	}
	
	private void initProductPlans() throws Throwable {
		productPlan = null;
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
		channel = null;
		
		channelSelection = omniChannelSync.getCategoryMap()
				.entrySet()
				.stream()
				.filter(p -> (p.getValue().getParent() != null && p.getValue().getParent().getCode().equals("OMNI_CHANNEL")))
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.map(m -> new SelectItem(m.getValue().getCode(), m.getValue().getValue()))
				.collect(Collectors.toList());
	}
	
	private void initContactReason() throws Throwable {
		contactReason = null;
		
		contactReasonSelection = omniChannelSync.getCategoryMap()
				.entrySet()
				.stream()
				.filter(p -> (p.getValue().getParent() != null && p.getValue().getParent().getCode().equals("OMNI_CONTACT_REASON")))
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.map(m -> new SelectItem(m.getValue().getCode(), m.getValue().getValue()))
				.collect(Collectors.toList());
	}
	
	private void initTrackingStatus() throws Throwable {
		initTrackingStatus(new Integer(-1));
	}
	
	private void initTrackingStatus(Integer currentLevel) throws Throwable {
		trackingStatusSelection = omniChannelSync.getCategoryMap()
				.entrySet()
				.stream()
				.filter(p -> (p.getValue().getParent() != null && p.getValue().getParent().getCode().equals("OMNI_TRACKING_STATUS")))
				.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
				.map(m -> new SelectItem(m.getValue().getCode(), m.getValue().getValue()))
				.collect(Collectors.toList());
	}
	
	private void initProvince() throws Throwable {
		province = null;
		provinceSelection = new ArrayList<>();
		provinceSelection = omniChannelSync.getProvinceMap()
				.entrySet()
				.stream()
				.sorted((c1, c2) -> c1.getValue().getProvinceNameTh().compareTo(c2.getValue().getProvinceNameTh()))
				.map(m -> new SelectItem(m.getValue().getProvinceCode(), m.getValue().getProvinceNameTh()))
				.collect(Collectors.toList());
	}
	
	private void initListSource() throws Throwable {
		listSourceSelection = omniChannelSync.getListSourceMap().entrySet()
			.stream()
			.sorted((c1, c2) -> c1.getValue().getId().compareTo(c2.getValue().getId()))
			.map(m -> new SelectItem(m.getKey(), m.getValue().getListSourceValue()))
			.collect(Collectors.toList());
	}
	
	public List<OmniMotorHist> getOmniMotorHistList() {
		return omniMotorHistList;
	}

	public void setOmniMotorHistList(List<OmniMotorHist> omniMotorHistList) {
		this.omniMotorHistList = omniMotorHistList;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getcFirstName() {
		return cFirstName;
	}

	public void setcFirstName(String cFirstName) {
		this.cFirstName = cFirstName;
	}

	public String getcLastName() {
		return cLastName;
	}

	public void setcLastName(String cLastName) {
		this.cLastName = cLastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<SelectItem> getSexSelection() {
		return sexSelection;
	}

	public void setSexSelection(List<SelectItem> sexSelection) {
		this.sexSelection = sexSelection;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<SelectItem> getMotorBrandSelection() {
		return motorBrandSelection;
	}

	public void setMotorBrandSelection(List<SelectItem> motorBrandSelection) {
		this.motorBrandSelection = motorBrandSelection;
	}

	public String getMotorBrand() {
		return motorBrand;
	}

	public void setMotorBrand(String motorBrand) {
		this.motorBrand = motorBrand;
	}

	public List<SelectItem> getBrandModelSelection() {
		return brandModelSelection;
	}

	public void setBrandModelSelection(List<SelectItem> brandModelSelection) {
		this.brandModelSelection = brandModelSelection;
	}

	public String getBrandModel() {
		return brandModel;
	}

	public void setBrandModel(String brandModel) {
		this.brandModel = brandModel;
	}

	public List<SelectItem> getCarYearSelection() {
		return carYearSelection;
	}

	public void setCarYearSelection(List<SelectItem> carYearSelection) {
		this.carYearSelection = carYearSelection;
	}

	public Integer getCarYear() {
		return carYear;
	}

	public void setCarYear(Integer carYear) {
		this.carYear = carYear;
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

	public List<SelectItem> getInceProductSelection() {
		return inceProductSelection;
	}

	public void setInceProductSelection(List<SelectItem> inceProductSelection) {
		this.inceProductSelection = inceProductSelection;
	}

	public String getInceProductCode() {
		return inceProductCode;
	}

	public void setInceProductCode(String inceProductCode) {
		this.inceProductCode = inceProductCode;
	}

	public List<SelectItem> getProductPlanSelection() {
		return productPlanSelection;
	}

	public void setProductPlanSelection(List<SelectItem> productPlanSelection) {
		this.productPlanSelection = productPlanSelection;
	}

	public String getProductPlan() {
		return productPlan;
	}

	public void setProductPlan(String productPlan) {
		this.productPlan = productPlan;
	}

	public List<SelectItem> getChannelSelection() {
		return channelSelection;
	}

	public void setChannelSelection(List<SelectItem> channelSelection) {
		this.channelSelection = channelSelection;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public List<SelectItem> getContactReasonSelection() {
		return contactReasonSelection;
	}

	public void setContactReasonSelection(List<SelectItem> contactReasonSelection) {
		this.contactReasonSelection = contactReasonSelection;
	}

	public String getContactReason() {
		return contactReason;
	}

	public void setContactReason(String contactReason) {
		this.contactReason = contactReason;
	}

	public List<SelectItem> getTrackingStatusSelection() {
		return trackingStatusSelection;
	}

	public void setTrackingStatusSelection(List<SelectItem> trackingStatusSelection) {
		this.trackingStatusSelection = trackingStatusSelection;
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

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
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

	public List<SelectItem> getProvinceSelection() {
		return provinceSelection;
	}

	public void setProvinceSelection(List<SelectItem> provinceSelection) {
		this.provinceSelection = provinceSelection;
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

	public List<SelectItem> getListSourceSelection() {
		return listSourceSelection;
	}

	public void setListSourceSelection(List<SelectItem> listSourceSelection) {
		this.listSourceSelection = listSourceSelection;
	}

	public String getListSource() {
		return listSource;
	}

	public void setListSource(String listSource) {
		this.listSource = listSource;
	}

	public void setLoginSession(LoginSession loginSession) {
		this.loginSession = loginSession;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getOtherTel() {
		return otherTel;
	}

	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Double getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
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

	public String getOldCitizenId() {
		return oldCitizenId;
	}

	public void setOldCitizenId(String oldCitizenId) {
		this.oldCitizenId = oldCitizenId;
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

	public OmniMotorHist getSelectedMotor() {
		return selectedMotor;
	}

	public void setSelectedMotor(OmniMotorHist selectedMotor) {
		this.selectedMotor = selectedMotor;
	}

	public OmniMotorPolicyModel getPolicyModel() {
		return policyModel;
	}

	public void setPolicyModel(OmniMotorPolicyModel policyModel) {
		this.policyModel = policyModel;
	}

	public String getSearchTel() {
		return searchTel;
	}

	public void setSearchTel(String searchTel) {
		this.searchTel = searchTel;
	}

	public String getSearchCitizen() {
		return searchCitizen;
	}

	public void setSearchCitizen(String searchCitizen) {
		this.searchCitizen = searchCitizen;
	}

	public String getCurrentFlow() {
		return currentFlow;
	}

	public void setCurrentFlow(String currentFlow) {
		this.currentFlow = currentFlow;
	}

	public String getContactReasonOther() {
		return contactReasonOther;
	}

	public void setContactReasonOther(String contactReasonOther) {
		this.contactReasonOther = contactReasonOther;
	}
	
}
