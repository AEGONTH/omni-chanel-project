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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.adms.cs.service.CategoryDataService;
import com.adms.cs.service.CustomerService;
import com.adms.cs.service.InceProductService;
import com.adms.cs.service.InsurerService;
import com.adms.cs.service.ListSourceService;
import com.adms.cs.service.OmniLogMotorHistService;
import com.adms.cs.service.OmniLogMotorService;
import com.adms.cs.service.ParamConfigService;
import com.adms.cs.service.ProductPlanService;
import com.adms.cs.service.ProvinceService;
import com.adms.entity.cs.CategoryData;
import com.adms.entity.cs.Customer;
import com.adms.entity.cs.ListSource;
import com.adms.entity.cs.OmniLogMotor;
import com.adms.entity.cs.OmniLogMotorHist;
import com.adms.entity.cs.ParamConfig;
import com.adms.entity.cs.ProductPlan;
import com.adms.entity.cs.Province;
import com.adms.web.base.bean.BaseBean;
import com.adms.web.bean.login.LoginSession;

@ManagedBean
@ViewScoped
public class OmniMainView extends BaseBean {

	private static final long serialVersionUID = -1006785263944871332L;

	@ManagedProperty("#{omniLogMotorHistService}")
	private OmniLogMotorHistService omniLogMotorHistService;
	
	@ManagedProperty("#{customerService}")
	private CustomerService customerService;
	
	@ManagedProperty("#{loginSession}")
	private LoginSession loginSession;

	private ParamConfigService paramConfigService = Faces.evaluateExpressionGet("#{paramConfigService}");
	private ProvinceService provinceService = Faces.evaluateExpressionGet("#{provinceService}");

	private Map<String, CategoryData> categoryMap;
	private Map<String, Province> provinceMap;
	
	private List<OmniLogMotorHist> omniLogMotorHist;
	
	private Long logId;
	private Long logHistId;
	
	private String citizenId;
	
	private String cFirstName;
	private String cLastName;
	private String oldTel;
	private String tel;
	private String email;
	
	private String address1;
	private String address2;
	private String address3;
	private String postCode;
	
	private Customer customer;
	
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

			initListSource();
			
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
		setDataToDialog(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public void onBlurTel() throws Throwable {
		RequestContext rc = RequestContext.getCurrentInstance();
		List<Customer> list = null;
		
		if(this.logId == null && (StringUtils.isBlank(oldTel) || oldTel.compareTo(tel) != 0)) {
			if(!StringUtils.isBlank(this.tel)) {
				list = getCustomerByMobile(this.tel);
			}
			
			if(list != null && list.size() > 0) {
				customer = list.get(0);
				setCustomerInfoForDialog(customer);
				rc.update("frmDlg:gridCustomerInfo");
			} else if(!StringUtils.isBlank(oldTel) && oldTel.compareTo(tel) != 0) {
				customer = null;
				setCustomerInfoForDialog(customer);
				rc.update("frmDlg:gridCustomerInfo");
			}
			oldTel = new String(tel);
		}
	}
	
	public void saveCustomerInfo() throws Throwable {
		ListSourceService listSourceService = Faces.evaluateExpressionGet("#{listSourceService}");
		ProductPlanService productPlanService = Faces.evaluateExpressionGet("#{productPlanService}");
		OmniLogMotorService logMotorService = Faces.evaluateExpressionGet("#{omniLogMotorService}");
		OmniLogMotorHistService omniLogMotorHistService = Faces.evaluateExpressionGet("#{omniLogMotorHistService}");
		
		OmniLogMotor omniLogMotor = null;
		OmniLogMotorHist hist = null;
		System.out.println("saving log id: " + logId);
		if(logId == null) {
			omniLogMotor = new OmniLogMotor();
			omniLogMotor.setCar(categoryMap.get(this.brandModel));
			omniLogMotor.setCarYear(this.carYear.toString());
			omniLogMotor.setCustomer(getValidatedCustomer());
			omniLogMotor.setListSource(listSourceService.find(new ListSource(listSource)).get(0));
			omniLogMotor.setProductPlan(productPlanService.find(new ProductPlan(productPlan)).get(0));
			omniLogMotor = logMotorService.add(omniLogMotor, loginSession.getUsername());
			
			hist = new OmniLogMotorHist();
			hist.setChannel(categoryMap.get(channel));
			hist.setContactReason(categoryMap.get(contactReason));
			hist.setDetail(StringUtils.isBlank(contactDetails) ? null : contactDetails);
			hist.setDueDate(dueDate);
			hist.setLogDate(new Date());
			hist.setOmniLogMotor(omniLogMotor);
			hist.setStatus(categoryMap.get(trackingStatus));
			omniLogMotorHistService.add(hist, loginSession.getUsername());
		} else {
			hist = omniLogMotorHistService.find(logHistId);
			
			OmniLogMotorHist newHist = new OmniLogMotorHist();
			newHist.setChannel(categoryMap.get(channel));
			newHist.setContactReason(categoryMap.get(contactReason));
			newHist.setDetail(StringUtils.isBlank(contactDetails) ? null : contactDetails);
			newHist.setDueDate(dueDate);
			newHist.setLogDate(new Date());
			newHist.setOmniLogMotor(hist.getOmniLogMotor());
			newHist.setStatus(categoryMap.get(trackingStatus));
			omniLogMotorHistService.add(newHist, loginSession.getUsername());
		}
		initData();
		RequestContext.getCurrentInstance().update("frmWL:dtWaitingList");
		RequestContext.getCurrentInstance().execute("PF('dlgCustomer').hide()");
	}
	
	private Customer getValidatedCustomer() throws Exception {
		boolean isChange = false;
		boolean isNew = false;
		if(customer != null) {
			if(customer.getFirstName().compareTo(cFirstName) != 0) {
				isChange = true;
			}
			if(customer.getLastName().compareTo(cLastName) != 0) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getCitizenId()) && StringUtils.isBlank(citizenId))
					|| (StringUtils.isBlank(customer.getCitizenId()) && !StringUtils.isBlank(citizenId))
					|| (!StringUtils.isBlank(customer.getCitizenId()) && StringUtils.isBlank(citizenId))
					|| customer.getCitizenId().compareTo(citizenId) != 0) {
				isChange = true;
			}
			if(!(customer.getGender() == null && StringUtils.isBlank(sex))
					|| (customer.getGender() == null && !StringUtils.isBlank(sex)) 
					|| (customer.getGender() != null && StringUtils.isBlank(sex))
					|| customer.getGender().getParamKey().compareTo(sex) != 0) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getEmail()) && StringUtils.isBlank(email))
					|| (StringUtils.isBlank(customer.getEmail()) && !StringUtils.isBlank(email))
					|| (!StringUtils.isBlank(customer.getEmail()) && StringUtils.isBlank(email))
					|| customer.getEmail().compareTo(email) != 0) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getAddress1()) && StringUtils.isBlank(address1))
					|| (StringUtils.isBlank(customer.getAddress1()) && !StringUtils.isBlank(address1))
					|| (!StringUtils.isBlank(customer.getAddress1()) && StringUtils.isBlank(address1))
					|| customer.getAddress1().compareTo(address1) != 0) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getAddress2()) && StringUtils.isBlank(address2))
					|| (StringUtils.isBlank(customer.getAddress2()) && !StringUtils.isBlank(address2))
					|| (!StringUtils.isBlank(customer.getAddress2()) && StringUtils.isBlank(address2))
					|| customer.getAddress2().compareTo(address2) != 0) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getAddress3()) && StringUtils.isBlank(address3))
					|| (StringUtils.isBlank(customer.getAddress3()) && !StringUtils.isBlank(address3))
					|| (!StringUtils.isBlank(customer.getAddress3()) && StringUtils.isBlank(address3))
					|| customer.getAddress3().compareTo(address3) != 0) {
				isChange = true;
			}
			if(!(StringUtils.isBlank(customer.getPostCode()) && StringUtils.isBlank(postCode))
					|| (StringUtils.isBlank(customer.getPostCode()) && !StringUtils.isBlank(postCode))
					|| (!StringUtils.isBlank(customer.getPostCode()) && StringUtils.isBlank(postCode))
					|| customer.getPostCode().compareTo(postCode) != 0){
				isChange = true;
			}
			if(!(customer.getProvince() == null && StringUtils.isBlank(province))
					|| (customer.getProvince() == null && !StringUtils.isBlank(province)) 
					|| (customer.getProvince() != null && StringUtils.isBlank(province))
					|| customer.getProvince().getProvinceCode().compareTo(province) != 0) {
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
						.setFirstName(cFirstName)
						.setLastName(cLastName)
						.setGender(paramConfigService.find(new ParamConfig().setParamKey(sex)).get(0))
						.setEmail(email)
						.setAddress1(address1)
						.setAddress2(address2)
						.setAddress3(address3)
						.setPostCode(postCode)
						.setProvince(provinceMap.get(province))
						.setVisible("Y")
					, loginSession.getUsername());
		} else {
			return customer;
		}
	}
	
	public void onRowSelect(SelectEvent event) throws NumberFormatException, Throwable {
		OmniLogMotorHist logHist =(OmniLogMotorHist) event.getObject();
		logHistId = logHist.getId();
		setDataToDialog(logHist.getOmniLogMotor().getId()
				, logHist.getOmniLogMotor().getCustomer().getCitizenId()
				, logHist.getOmniLogMotor().getCustomer().getFirstName()
				, logHist.getOmniLogMotor().getCustomer().getLastName()
				, logHist.getOmniLogMotor().getCustomer().getMobileNo()
				, logHist.getOmniLogMotor().getCustomer().getEmail()
				, logHist.getOmniLogMotor().getCustomer().getAddress1()
				, logHist.getOmniLogMotor().getCustomer().getAddress2()
				, logHist.getOmniLogMotor().getCustomer().getAddress3()
				, logHist.getOmniLogMotor().getCustomer().getPostCode()
				, logHist.getOmniLogMotor().getCustomer().getProvince() == null ? null : logHist.getOmniLogMotor().getCustomer().getProvince().getProvinceCode()
				, logHist.getOmniLogMotor().getCustomer().getGender() == null ? null : logHist.getOmniLogMotor().getCustomer().getGender().getParamKey()
				, logHist.getOmniLogMotor().getCar().getParent().getCode()
				, logHist.getOmniLogMotor().getCar().getCode()
				, new Integer(logHist.getOmniLogMotor().getCarYear())
				, logHist.getOmniLogMotor().getListSource().getListSourceCode()
				, logHist.getOmniLogMotor().getProductPlan().getProduct().getInsurer().getInsurerCode()
				, logHist.getOmniLogMotor().getProductPlan().getProduct().getProductCode()
				, logHist.getOmniLogMotor().getProductPlan().getPlanCode()
				, logHist.getChannel() == null ? null : logHist.getChannel().getCode()
				, logHist.getContactReason() == null ? null : logHist.getContactReason().getCode()
				, logHist.getStatus() == null ? null : logHist.getStatus().getCode()
				, logHist.getDetail()
				, logHist.getDueDate());
	}
	
	private void setDataToDialog(
			Long logId, String citizenId, String cFirstName, String cLastName, String tel, String email
			, String address1, String address2, String address3, String postCode, String province
			, String sex
			, String motorBrand, String brandModel, Integer carYear
			, String listSourceCode
			, String insurerCode, String inceProductCode, String productPlan
			, String channel, String contactReason, String trackingStatus
			, String contactDetails, Date dueDate) throws Throwable {
		this.customer = null;
		this.oldTel = null;
		
		this.logId = logId;
		
		this.citizenId = citizenId;
		
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
		
		this.listSource = listSourceCode;
		this.insurerCode = insurerCode;
		
		initInceProducts();
		this.inceProductCode = inceProductCode;

		initProductPlans();
		this.productPlan = productPlan;
		
//		initChannels();
		this.channel = channel;
		
//		initContactReason();
		this.contactReason = contactReason;
		
//		initTrackingStatus();
		this.trackingStatus = trackingStatus;
		
		this.contactDetails = contactDetails;
		this.dueDate = dueDate;
	}
	
	private List<Customer> getCustomerByMobile(String mobileNo) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria
			.add(Restrictions.eq("mobileNo", this.tel))
			.add(Restrictions.eq("visible", "Y"));
		return customerService.findByCriteria(criteria);
	}

	private void setCustomerInfoForDialog(Customer customer) {
		if(customer != null) {
			this.citizenId = customer.getCitizenId();
			this.cFirstName = customer.getFirstName();
			this.cLastName = customer.getLastName();
			this.sex = customer.getGender().getParamKey();
			this.email = customer.getEmail();
			this.address1 = customer.getAddress1();
			this.address2 = customer.getAddress2();
			this.address3 = customer.getAddress3();
			this.postCode = customer.getPostCode();
			this.province = customer.getProvince().getProvinceCode();
		} else {
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

	private void initData() throws Throwable {
		try {
			omniLogMotorHist = new ArrayList<>();
			DetachedCriteria criteria = DetachedCriteria.forClass(OmniLogMotorHist.class);
			criteria.add(Restrictions.sqlRestriction("this_.ID in (select MAX(d.ID) from OMNI_LOG_MOTOR_HIST d GROUP BY d.LOG_MOTOR_ID)"));
			criteria.add(Restrictions.not(Restrictions.in("status.code", new String[]{TrackingStatus.CANCELLED.getValue(), TrackingStatus.CLOSED.getValue()})));
			criteria.addOrder(Order.asc("dueDate"));
			omniLogMotorHist = omniLogMotorHistService.findByCriteria(criteria);
		} catch(Exception e) {
			throw e;
		}
	}
	
	private void initCategoryMap() throws Throwable {
		CategoryDataService categoryDataService = Faces.evaluateExpressionGet("#{categoryDataService}");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(CategoryData.class);
		criteria.add(Restrictions.isNotNull("parent.code"));
		
		categoryMap = categoryDataService.findByCriteria(criteria)
				.stream()
				.collect(Collectors.toMap(CategoryData::getCode, (p) -> p));
	}

	private void initSex() throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(ParamConfig.class);
		criteria.add(Restrictions.eq("paramGroup", "SEX"));
		List<ParamConfig> list = paramConfigService.findByCriteria(criteria);
		
		ResourceBundle globalMsg = Faces.evaluateExpressionGet("#{globalMsg}");
		sexSelection = new ArrayList<>();
		for(ParamConfig c : list) {
			sexSelection.add(new SelectItem(c.getParamKey(), globalMsg.getString(c.getParamValue())));
		}
	}
	
	private void initBrand() throws Throwable {
		String parent = "OMNI_MOTOR_CAT";
		motorBrandSelection = getCategoryDataByParent(parent)
				.stream()
				.sorted((c1, c2) -> c1.getCode().compareTo(c2.getCode()))
				.map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
	}
	
	private void initModelByBrand(String brandCode) throws Throwable {
		brandModel = null;
		brandModelSelection = getCategoryDataByParent(brandCode)
				.stream()
				.sorted((c1, c2) -> c1.getCode().compareTo(c2.getCode()))
				.map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
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
		List<Province> l = provinceService.findAll();
		provinceSelection = l.stream().map(m -> new SelectItem(m.getProvinceCode(), m.getProvinceNameTh())).collect(Collectors.toList());
		provinceMap = l.stream().collect(Collectors.toMap(Province::getProvinceCode, (p) -> p));
	}
	
	private void initListSource() throws Throwable {
		listSourceSelection = new ArrayList<>();
		ListSourceService listSourceService = Faces.evaluateExpressionGet("#{listSourceService}");
		List<ListSource> l = listSourceService.findAll();
		listSourceSelection = l.stream().map(m -> new SelectItem(m.getListSourceCode(), m.getListSourceValue())).collect(Collectors.toList());
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
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
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
