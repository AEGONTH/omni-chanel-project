package com.adms.web.bean.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;

import com.adms.cs.service.CategoryDataService;
import com.adms.cs.service.InceProductService;
import com.adms.cs.service.InsurerService;
import com.adms.cs.service.OmniLogMotorHistService;
import com.adms.cs.service.ParamConfigService;
import com.adms.cs.service.ProductPlanService;
import com.adms.cs.service.ProvinceService;
import com.adms.entity.cs.CategoryData;
import com.adms.entity.cs.Customer;
import com.adms.entity.cs.OmniLogMotor;
import com.adms.entity.cs.OmniLogMotorHist;
import com.adms.entity.cs.ParamConfig;
import com.adms.entity.cs.Province;
import com.adms.web.base.bean.BaseBean;

@ManagedBean
@ViewScoped
public class OmniMainView extends BaseBean {

	private static final long serialVersionUID = -1006785263944871332L;

	@ManagedProperty("#{omniLogMotorHistService}")
	private OmniLogMotorHistService omniLogMotorHistService;
	
	private final String[] masterCategories = new String[]{"OMNI_MOTOR_CAT", "MOTOR_BRAND_HONDA", "MOTOR_BRAND_TOYOTA", "OMNI_CHANNEL", "OMNI_CONTACT_REASON", "OMNI_TRACKING_STATUS"};
	
	private List<OmniLogMotorHist> omniLogMotorHist;
	
	private Long logId;
	
	private String cFirstName;
	private String cLastName;
	private String tel;
	private String email;
	
	private String address1;
	private String address2;
	private String address3;
	private String postCode;
	
	private List<SelectItem> provinceSelection;
	private Province province;
	
	private List<SelectItem> sexSelection;
	private ParamConfig sex;
	
	private Map<String, CategoryData> categoryMap;
	
	private List<SelectItem> motorBrandSelection;
	private String motorBrand;
	
	private List<SelectItem> brandModelSelection;
	private String brandModel;
	
	private List<SelectItem> carYearSelection;
	private Integer carYear;
	
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
	
	private List<SelectItem> trackingStatusSelection;
	private String trackingStatus;
	
	private String contactDetails;
	
	private Date dueDate;
	
	@PostConstruct
	private void init() {
		try {	
			initData();
			initSex();
			
			initCategoryMap();
			initBrand();
			initModelByBrand(motorBrand);
			
			initCarYears();
			
			initInsurers();
			initInceProducts();
			initProductPlans();
			
			initChannels();
			initContactReason();
			initTrackingStatus();
			
			initProvince();
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void refreshData() throws Throwable {
		initData();
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
	
	public void onClickAddNew() throws Throwable {
		setDataToDialog(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public void saveCustomerInfo(Long logId) throws Throwable {
		if(logId == null) {
			//TODO Insert new Record
			CategoryDataService categoryDataService = Faces.evaluateExpressionGet("#{categoryDataService}");
			
			OmniLogMotor omniLogMotor = new OmniLogMotor();
			omniLogMotor.setCar(categoryDataService.find(new CategoryData().setCode(this.brandModel)).get(0));
			omniLogMotor.setCarYear(this.carYear.toString());
		} else {
			//TODO Insert and Update flag (already inserted)
			
		}
	}
	
	private boolean isNewCustomer(String mobileNo, String firstName, String lastName) {
		boolean flag = false;
		
		return flag;
	}
	
	public void onRowSelect(SelectEvent event) throws NumberFormatException, Throwable {
		OmniLogMotorHist logHist =(OmniLogMotorHist) event.getObject();
		
		setDataToDialog(logHist.getOmniLogMotor().getId()
				, logHist.getOmniLogMotor().getCustomer().getFirstName()
				, logHist.getOmniLogMotor().getCustomer().getLastName()
				, logHist.getOmniLogMotor().getCustomer().getMobileNo()
				, logHist.getOmniLogMotor().getCustomer().getEmail()
				, logHist.getOmniLogMotor().getCustomer().getAddress1()
				, logHist.getOmniLogMotor().getCustomer().getAddress2()
				, logHist.getOmniLogMotor().getCustomer().getAddress3()
				, logHist.getOmniLogMotor().getCustomer().getPostCode()
				, logHist.getOmniLogMotor().getCustomer().getProvince()
				, logHist.getOmniLogMotor().getCustomer().getGender()
				, logHist.getOmniLogMotor().getCar().getParent().getCode()
				, logHist.getOmniLogMotor().getCar().getCode()
				, new Integer(logHist.getOmniLogMotor().getCarYear())
				, logHist.getOmniLogMotor().getProductPlan().getProduct().getInsurer().getInsurerCode()
				, logHist.getOmniLogMotor().getProductPlan().getProduct().getProductCode()
				, logHist.getOmniLogMotor().getProductPlan().getPlanCode()
				, logHist.getChannel().getCode()
				, logHist.getContactReason().getCode()
				, logHist.getStatus().getCode()
				, logHist.getDetail()
				, logHist.getDueDate());
		
	}
	
	private void setDataToDialog(Long logId, String cFirstName, String cLastName, String tel, String email
			, String address1, String address2, String address3, String postCode, Province province
			, ParamConfig sex
			, String motorBrand, String brandModel, Integer carYear
			, String insurerCode, String inceProductCode, String productPlan
			, String channel, String contactReason, String trackingStatus
			, String contactDetails, Date dueDate) throws Throwable {
		this.logId = logId;
		this.cFirstName = cFirstName;
		this.cLastName = cLastName;
		this.tel = tel;
		this.email = email;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.postCode = postCode;
		
//		initProvince();
		this.province = province;
		this.sex = sex;
		
		initModelByBrand(motorBrand);
		this.motorBrand = motorBrand;
		
		this.brandModel = brandModel;
		this.carYear = carYear;
		this.insurerCode = insurerCode;
		
		initInceProducts();
		this.inceProductCode = inceProductCode;

		initProductPlans();
		this.productPlan = productPlan;
		
		initChannels();
		this.channel = channel;
		
		initContactReason();
		this.contactReason = contactReason;
		
		initTrackingStatus();
		this.trackingStatus = trackingStatus;
		
		this.contactDetails = contactDetails;
		this.dueDate = dueDate;
	}
	
	private void initData() throws Throwable {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(OmniLogMotorHist.class);
			criteria.add(Restrictions.sqlRestriction("this_.ID in (select MAX(d.ID) from OMNI_LOG_MOTOR_HIST d GROUP BY d.LOG_MOTOR_ID)"));
			criteria.add(Restrictions.not(Restrictions.in("status.code", new String[]{TrackingStatus.CANCELLED.getValue(), TrackingStatus.CLOSED.getValue()})));
			criteria.addOrder(Order.asc("dueDate"));
			omniLogMotorHist = omniLogMotorHistService.findByCriteria(criteria);
		} catch(Exception e) {
			throw e;
		}
	}
	
	private void initSex() throws Throwable {
		ParamConfigService paramConfigService = Faces.evaluateExpressionGet("#{paramConfigService}");
		DetachedCriteria criteria = DetachedCriteria.forClass(ParamConfig.class);
		criteria.add(Restrictions.eq("paramGroup", "SEX"));
		List<ParamConfig> list = paramConfigService.findByCriteria(criteria);
		
		ResourceBundle globalMsg = Faces.evaluateExpressionGet("#{globalMsg}");
		sexSelection = new ArrayList<>();
		for(ParamConfig c : list) {
			sexSelection.add(new SelectItem(c, globalMsg.getString(c.getParamValue())));
		}
	}
	
	private void initCategoryMap() throws Throwable {
		CategoryDataService categoryDataService = Faces.evaluateExpressionGet("#{categoryDataService}");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(CategoryData.class);
		criteria.add(Restrictions.in("parent.code", masterCategories));
		
		categoryMap = categoryDataService.findByCriteria(criteria).stream().collect(Collectors.toMap(CategoryData::getCode, (p) -> p));
	}
	
	private void initBrand() throws Throwable {
		String parent = masterCategories[0];
		motorBrandSelection = getCategoryDataByParent(parent)
				.stream().map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
	}
	
	private void initModelByBrand(String brandCode) throws Throwable {
		brandModel = null;
		brandModelSelection = getCategoryDataByParent(brandCode)
				.stream().map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
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
		
		InsurerService insurerService = Faces.evaluateExpressionGet("#{insurerService}");
		insurerSelection = insurerService.findAll().stream().map(m -> new SelectItem(m.getInsurerCode(), m.getInsurerName())).collect(Collectors.toList());
	}
	
	private void initInceProducts() throws Throwable {
		inceProductCode = null;
		inceProductSelection = new ArrayList<>();
		InceProductService inceProductService = Faces.evaluateExpressionGet("#{inceProductService}");
		inceProductSelection = inceProductService.findAll().stream().filter(p -> p.getInsurer().getInsurerCode().equals(insurerCode)).map(m -> new SelectItem(m.getProductCode(), m.getProductName())).collect(Collectors.toList());
	}
	
	private void initProductPlans() throws Throwable {
		productPlan = null;
		productPlanSelection = new ArrayList<>();
		ProductPlanService productPlanService = Faces.evaluateExpressionGet("#{productPlanService}");
		productPlanSelection = productPlanService.findAll().stream().filter(p -> p.getProduct().getProductCode().equals(inceProductCode)).map(m -> new SelectItem(m.getPlanCode(), m.getPlanName())).collect(Collectors.toList());
	}
	
	private void initChannels() throws Throwable {
		channel = null;
		channelSelection = getCategoryDataByParent("OMNI_CHANNEL")
				.stream().map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
	}
	
	private void initContactReason() throws Throwable {
		contactReason = null;
		contactReasonSelection = getCategoryDataByParent("OMNI_CONTACT_REASON")
				.stream().map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
	}
	
	private void initTrackingStatus() throws Throwable {
		trackingStatus = null;
		trackingStatusSelection = getCategoryDataByParent("OMNI_TRACKING_STATUS")
				.stream().map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
	}
	
	private void initProvince() throws Throwable {
		province = null;
		provinceSelection = new ArrayList<>();
		ProvinceService provinceService = Faces.evaluateExpressionGet("#{provinceService}");
		provinceSelection = provinceService.findAll().stream().map(m -> new SelectItem(m, m.getProvinceNameTh())).collect(Collectors.toList());
	}
	
	private List<CategoryData> getCategoryDataByParent(String parentCode) {
		if(!StringUtils.isBlank(parentCode)) {
			return categoryMap
				.entrySet()
				.stream()
				.filter(p -> p.getValue().getParent().getCode().equals(parentCode))
				.map(m -> m.getValue()).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
	
	public void setOmniLogMotorHistService(OmniLogMotorHistService omniLogMotorHistService) {
		this.omniLogMotorHistService = omniLogMotorHistService;
	}

	public List<OmniLogMotorHist> getOmniLogMotorHist() {
		return omniLogMotorHist;
	}

	public void setOmniLogMotorHist(List<OmniLogMotorHist> omniLogMotorHist) {
		this.omniLogMotorHist = omniLogMotorHist;
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

	public ParamConfig getSex() {
		return sex;
	}

	public void setSex(ParamConfig sex) {
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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<SelectItem> getProvinceSelection() {
		return provinceSelection;
	}

	public void setProvinceSelection(List<SelectItem> provinceSelection) {
		this.provinceSelection = provinceSelection;
	}

	private enum TrackingStatus {
		OPEN("OMNI_TKS_OPEN")
		, IN_PROGRESS("OMNI_TKS_IN_PROGRESS")
		, CLOSED("OMNI_TKS_CLOSED")
		, CANCELLED("OMNI_TKS_CANCELLED");
		
		private String value;
		
		private TrackingStatus(String val) {
			value = val;
		}
		
		public String getValue() {
			return value;
		}
	}
}
