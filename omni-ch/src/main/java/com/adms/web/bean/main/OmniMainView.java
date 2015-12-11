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
import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import com.adms.cs.service.CategoryDataService;
import com.adms.cs.service.CustomerService;
import com.adms.cs.service.CustomerYesRecordService;
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
	
	public final String TEL = "TEL";
	public final String CITIZEN = "CITIZEN";

	private ParamConfigService paramConfigService = Faces.evaluateExpressionGet("#{paramConfigService}");
	private ProvinceService provinceService = Faces.evaluateExpressionGet("#{provinceService}");
	
	private CustomerYesRecordService customerYesRecordService = Faces.evaluateExpressionGet("#{customerYesRecordService}");

	private Map<String, CategoryData> categoryMap;
	private Map<String, Province> provinceMap;
	
	private List<OmniLogMotorHist> omniLogMotorHist;
	
	private OmniLogMotorHist selectedMotor;
	
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
	
	private List<SelectItem> trackingStatusSelection;
	private String trackingStatus;
	
	private String contactDetails;
	
	private Date dueDate;
	
	@PostConstruct
	private void init() {
		
	}
	
	public void initialData() throws Throwable {
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
	}
	
	public void test() throws Exception {
		MotorModel motorModel = Faces.evaluateExpressionGet("#{motorModel}");
		motorModel.initialData();
		
		Ajax.update("frmWL:testDT");
	}
	
	public void refreshData() throws Throwable{
		initData();
		Ajax.update("frmWL");
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
		customer = null;
		telReadyOnly = false;
		citizenReadyOnly = false;
		RequestContext.getCurrentInstance().reset("frmDlg");
		setDataToDialog(null, null, null, null, null, null, null, null, null, null, null, null, null, null);;
	}
	
	public void onClickAddPolicy() throws Throwable {
		
		
		RequestContext.getCurrentInstance().execute("PF('dlgPolicy').show();");
	}
	
	public void onRowSelect(SelectEvent event) throws NumberFormatException, Throwable {
		OmniLogMotorHist logHist = this.selectedMotor;
		logHistId = logHist.getId();
		setDataToDialog(logHist.getOmniLogMotor().getId()
				, logHist.getOmniLogMotor().getCustomer()
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
	
	public String onFlowProcess(FlowEvent event) {
		boolean flag = false;
		
		String oldStep = event.getOldStep();
		String newStep = event.getNewStep();
		
		if(oldStep.equalsIgnoreCase("search")) {
			System.out.println("Submit Search");
		} else if(oldStep.equalsIgnoreCase("selectResult")) {
			System.out.println("Submit select result");
		}
		
        return newStep;
    }

	public void doSearchCustomerForPolicy() throws Throwable {
		
	}
	
	public void doSearchCustomer(String val) throws Throwable {
		String temp = null;
		if(this.logId == null) {
			
			if(val.equalsIgnoreCase(TEL)) {
				temp = new String(this.tel);
				setDialogDataFromList(getCustomer(this.tel, null), val);
				this.tel = temp;
				this.oldTel = new String(this.tel);
			} else if(val.equalsIgnoreCase(CITIZEN)) {
				temp = new String(this.citizenId);
				setDialogDataFromList(getCustomer(null, this.citizenId), val);
				this.citizenId = temp;
				this.oldCitizenId = new String(this.citizenId);
			}
			Ajax.update("frmDlg:gridCustomerInfo");
		}
	}
	
	public void saveCustomerInfo() throws Throwable {
		ListSourceService listSourceService = Faces.evaluateExpressionGet("#{listSourceService}");
		ProductPlanService productPlanService = Faces.evaluateExpressionGet("#{productPlanService}");
		OmniLogMotorService logMotorService = Faces.evaluateExpressionGet("#{omniLogMotorService}");
		OmniLogMotorHistService omniLogMotorHistService = Faces.evaluateExpressionGet("#{omniLogMotorHistService}");
		
		OmniLogMotor omniLogMotor = null;
		OmniLogMotorHist hist = null;

		if((StringUtils.isNotBlank(oldTel) && oldTel.compareTo(tel) != 0)
				|| (StringUtils.isNotBlank(oldCitizenId) && oldCitizenId.compareTo(citizenId) != 0)) {
			Messages.addError("dlgMsg", "Please check Mobile No. or Citizen ID agian.");
			Ajax.update("frmWL:globalMsg");
			return;
		}
		
		if(logId == null) {
			omniLogMotor = new OmniLogMotor();
//			omniLogMotor.setCar(categoryMap.get(this.brandModel));
//			omniLogMotor.setCarYear(this.carYear.toString());
			omniLogMotor.setCustomer(getValidatedCustomer());
//			omniLogMotor.setListSource(listSourceService.find(new ListSource(listSource)).get(0));
//			omniLogMotor.setProductPlan(productPlanService.find(new ProductPlan(productPlan)).get(0));
			omniLogMotor = logMotorService.add(omniLogMotor, loginSession.getUsername());
			
			hist = new OmniLogMotorHist();
			hist.setChannel(categoryMap.get(channel));
			hist.setContactReason(categoryMap.get(contactReason));
			hist.setDetail(StringUtils.isBlank(contactDetails) ? null : contactDetails);
			hist.setDueDate(dueDate);
			hist.setLogDate(new Date());
			hist.setCar(categoryMap.get(this.brandModel));
			hist.setCarYear(this.carYear.toString());
			hist.setOmniLogMotor(omniLogMotor);
			hist.setListSource(listSourceService.find(new ListSource(listSource)).get(0));
			hist.setProductPlan(productPlanService.find(new ProductPlan(productPlan)).get(0));
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
			omniLogMotor = hist.getOmniLogMotor();
			omniLogMotor.setCustomer(getValidatedCustomer());
			newHist.setOmniLogMotor(omniLogMotor);
			newHist.setCar(categoryMap.get(this.brandModel));
			newHist.setCarYear(this.carYear.toString());
			newHist.setOmniLogMotor(omniLogMotor);
			newHist.setListSource(listSourceService.find(new ListSource(listSource)).get(0));
			newHist.setProductPlan(productPlanService.find(new ProductPlan(productPlan)).get(0));
			newHist.setStatus(categoryMap.get(trackingStatus));
			omniLogMotorHistService.add(newHist, loginSession.getUsername());
		}
		initData();
		Ajax.update("frmWL");
		RequestContext.getCurrentInstance().execute("PF('dlgCustomer').hide()");
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
			Messages.addInfo("globalMsg", "Not found.");
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
		
//		initChannels();
		this.channel = channel;
		
//		initContactReason();
		this.contactReason = contactReason;
		
//		initTrackingStatus();
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

	private List<Customer> getCustomer(String mobileNo, String citizenId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if(StringUtils.isNotBlank(mobileNo)) criteria.add(Restrictions.eq("mobileNo", mobileNo));
		if(StringUtils.isNotBlank(citizenId)) criteria.add(Restrictions.eq("citizenId", citizenId));
		criteria.add(Restrictions.eq("visible", "Y"));
		return customerService.findByCriteria(criteria);
	}

	private void initData() throws Throwable {
		try {
			omniLogMotorHist = queryOmniLogMotorHist(TrackingStatus.OPEN.getValue(), TrackingStatus.IN_PROGRESS.getValue());
//			MotorModel motorModel = Faces.evaluateExpressionGet("#{motorModel}");
//			motorModel.initialData();
		} catch(Exception e) {
			throw e;
		}
	}
	
	private List<OmniLogMotorHist> queryOmniLogMotorHist(String... status) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(OmniLogMotorHist.class);
		criteria.add(Restrictions.sqlRestriction("this_.ID in (select MAX(d.ID) from OMNI_LOG_MOTOR_HIST d GROUP BY d.LOG_MOTOR_ID)"));
		if(status != null) {
			criteria.add(Restrictions.in("status.code", status));
		}
		criteria.addOrder(Order.asc("dueDate"));
		return omniLogMotorHistService.findByCriteria(criteria);
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
		if(StringUtils.isNotBlank(insurerCode)) {
			InceProductService inceProductService = Faces.evaluateExpressionGet("#{inceProductService}");
			inceProductSelection = inceProductService.findAll().stream().filter(p -> p.getInsurer().getInsurerCode().equals(insurerCode)).map(m -> new SelectItem(m.getProductCode(), m.getProductName())).collect(Collectors.toList());
		}
	}
	
	private void initProductPlans() throws Throwable {
		productPlan = null;
		productPlanSelection = new ArrayList<>();
		if(StringUtils.isNotBlank(inceProductCode)) {
			ProductPlanService productPlanService = Faces.evaluateExpressionGet("#{productPlanService}");
			productPlanSelection = productPlanService.findAll().stream().filter(p -> p.getProduct().getProductCode().equals(inceProductCode)).map(m -> new SelectItem(m.getPlanCode(), m.getPlanName())).collect(Collectors.toList());
		}
	}
	
	private void initChannels() throws Throwable {
		channel = null;
		channelSelection = getCategoryDataByParent("OMNI_CHANNEL")
				.stream().sorted((c1, c2) -> c1.getValue().compareTo(c2.getValue())).map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
	}
	
	private void initContactReason() throws Throwable {
		contactReason = null;
		contactReasonSelection = getCategoryDataByParent("OMNI_CONTACT_REASON")
				.stream().sorted((c1, c2) -> c1.getValue().compareTo(c2.getValue())).map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
	}
	
	private void initTrackingStatus() throws Throwable {
		trackingStatus = null;
		trackingStatusSelection = getCategoryDataByParent("OMNI_TRACKING_STATUS")
				.stream().sorted((c1, c2) -> c1.getLevel().compareTo(c2.getLevel())).map(m -> new SelectItem(m.getCode(), m.getValue())).collect(Collectors.toList());
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

	public OmniLogMotorHist getSelectedMotor() {
		return selectedMotor;
	}

	public void setSelectedMotor(OmniLogMotorHist selectedMotor) {
		this.selectedMotor = selectedMotor;
	}
	
}
