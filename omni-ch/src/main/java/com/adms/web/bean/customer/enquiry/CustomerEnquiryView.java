package com.adms.web.bean.customer.enquiry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.adms.cs.service.CallLogService;
import com.adms.cs.service.CustomerService;
import com.adms.cs.service.CustomerYesRecordService;
import com.adms.cs.service.LogDetailCategoryService;
import com.adms.cs.service.LogStatusGroupService;
import com.adms.entity.cs.CallLog;
import com.adms.entity.cs.Customer;
import com.adms.entity.cs.CustomerYesRecord;
import com.adms.entity.cs.LogDetailCategory;
import com.adms.entity.cs.LogStatusGroup;
import com.adms.entity.cs.LogValue;
import com.adms.sales.service.SalesService;
import com.adms.utils.DateUtil;
import com.adms.web.base.bean.BaseBean;
import com.adms.web.base.bean.LanguageBean;
import com.adms.web.bean.login.LoginSession;

@ManagedBean
@ViewScoped
public class CustomerEnquiryView extends BaseBean {

	private static final long serialVersionUID = -7189621707509848797L;
	
	@ManagedProperty(value="#{language}")
	private LanguageBean language;
	
	@ManagedProperty(value="#{loginSession}")
	private LoginSession loginSession;
	
	@ManagedProperty(value="#{customerService}")
	private CustomerService customerService;
	
	@ManagedProperty(value="#{customerYesRecordService}")
	private CustomerYesRecordService customerYesRecordService;
	
	@ManagedProperty(value="#{callLogService}")
	private CallLogService callLogService;
	
	@ManagedProperty(value="#{logDetailCategoryService}")
	private LogDetailCategoryService logDetailCategoryService;
	
	@ManagedProperty(value="#{logStatusGroupService}")
	private LogStatusGroupService logStatusGroupService;
	
	@ManagedProperty(value="#{salesService}")
	private SalesService salesService;
	
	private ResourceBundle csMsg;
	private CustomerEnquiryModel model;
	
	private String shCitizenId;
	private String shFirstName;
	private String shLastName;
	private Date shDOB;
	private String shTel;
	
	private boolean addNew;
	
//	Add Case Parameters
	private static final String PLEASE_SELECT = "Please Select";
	private static final Long DEFAULT_SELECT_ONE_MENU_VALUE = -1L;
	
	private boolean updateLog;
	private CallLog selectedCallLog;
	
//	GROUP
	private final String CHANNEL = "CHANNEL";
	private final String CALL_NATURE = "CALL_NATURE";
	private final String CALL_CATEGORY = "CALL_CATEGORY";
	private final String CALL_RESULT = "CALL_RESULT";
	private final String CANCEL_REASON = "CANCEL_REASON";
	private final String CALL_RESOURCE = "CALL_RESOURCE";
	private final String COMPLAINT_STATUS = "COMPLAINT_STATUS";
	private final String COMPLAINT_RESULT = "COMPLAINT_RESULT";
	
	private Map<String, List<LogStatusGroup>> callLogGrupStatMap;
	private Map<Long, Map<Long, List<LogValue>>> detailCatMap;
	
	public CustomerEnquiryView() {
	
	}
	
	@PostConstruct
	private void init() {
		csMsg = ResourceBundle.getBundle("com.adms.msg.cs.csMsg", new Locale(language.getLocaleCode()));
		clearModel();
		clearSh();
		try {
			initGroupStatus();
			initDetailCategory();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initGroupStatus() throws Exception {
		List<LogStatusGroup> list = logStatusGroupService.findAll();
		callLogGrupStatMap = new HashMap<>();
		for(LogStatusGroup logGroup : list) {
			List<LogStatusGroup> stats = callLogGrupStatMap.get(logGroup.getLogGroup());
			if(stats == null) {
				stats = new ArrayList<>();
				callLogGrupStatMap.put(logGroup.getLogGroup(), stats);
			}
			stats.add(logGroup);
		}
	}

	private void initDetailCategory() throws Exception {
		List<LogDetailCategory> list = logDetailCategoryService.findAll();
		detailCatMap = new HashMap<>();
		
		for(LogDetailCategory d : list) {
			Map<Long, List<LogValue>> catMap = detailCatMap.get(d.getLevelCallNature().getId());
			if(catMap == null) {
				catMap = new HashMap<>();
				detailCatMap.put(d.getLevelCallNature().getId(), catMap);
			}
			List<LogValue> details = catMap.get(d.getLevelCallCategory().getId());
			if(details == null) {
				details = new ArrayList<>();
				catMap.put(d.getLevelCallCategory().getId(), details);
			}
			details.add(d.getLogValue());
		}
	}
	
	private void initCallSubCategory() {
		if(model.getSelectedCallNature() != null && model.getSelectedCallNature() != DEFAULT_SELECT_ONE_MENU_VALUE
				&& model.getSelectedCategory() != null && model.getSelectedCategory() != DEFAULT_SELECT_ONE_MENU_VALUE) {
			
			model.setSubCategorySelection(new ArrayList<>());
			model.getSubCategorySelection().add(new SelectItem(DEFAULT_SELECT_ONE_MENU_VALUE, PLEASE_SELECT));
			
			List<LogValue> details = detailCatMap.get(model.getSelectedCallNature()).get(model.getSelectedCategory());
			
			for(LogValue detail : details) {
				model.getSubCategorySelection().add(new SelectItem(detail.getId(), detail.getValue()));
			}
		} else {
			model.setSubCategorySelection(null);
		}
	}
	
	public void initAllDropdownMenu() {
		model.setSourceSelection(createSelectItems(CALL_RESOURCE));
		model.setSelectedSource(DEFAULT_SELECT_ONE_MENU_VALUE);
		
		model.setChannelSelection(createSelectItems(CHANNEL));
		model.setSelectedChannel(DEFAULT_SELECT_ONE_MENU_VALUE);
		
		model.setNatureSelection(createSelectItems(CALL_NATURE));
		model.setSelectedCallNature(DEFAULT_SELECT_ONE_MENU_VALUE);
		
		model.setCategorySelection(createSelectItems(CALL_CATEGORY));
		model.setSelectedCategory(DEFAULT_SELECT_ONE_MENU_VALUE);
		
		model.setComplaintStatusSelection(createSelectItems(COMPLAINT_STATUS));
		model.setSelectedComplaintStatus(DEFAULT_SELECT_ONE_MENU_VALUE);
		
		model.setComplaintResultSelection(createSelectItems(COMPLAINT_RESULT));
		model.setSelectedComplaintResult(DEFAULT_SELECT_ONE_MENU_VALUE);
		
		//TODO: other initial selection add here
		
		initCallSubCategory();
	}

	public void initDialogAddLog() {
		model.setLogDate(DateUtil.getCurrentDate());
	}

	public void subCategoryListener() {
		initCallSubCategory();
	}

	public void clearModel() {
		model = new CustomerEnquiryModel();
	}
	
	public void clearSh() {
		shCitizenId = null;
		shDOB = null;
		shFirstName = null;
		shLastName = null;
		shTel = null;
		addNew = false;
		RequestContext.getCurrentInstance().update("frmMain:searchDlg");
	}
	
	public void clearAddCaseLog() {
		initAllDropdownMenu();
		model.setLogDetail(null);
		model.setResultDetail(null);
		model.setCorrectiveAction(null);
		model.setSuggestDetail(null);
		model.setLogRemark(null);
		selectedCallLog = null;
	}
	
	private boolean validateIsShEmpty() {
		return StringUtils.isBlank(shCitizenId) 
				&& StringUtils.isBlank(shTel)
				&& StringUtils.isBlank(shFirstName) 
				&& StringUtils.isBlank(shLastName)
				&& shDOB == null;
	}

	public void doSearch() throws Exception {
		RequestContext rc = RequestContext.getCurrentInstance();
		if(validateIsShEmpty()) {
			rc.execute("PF('searchDlg').jq.effect('shake', {times:5}, 100);");
			
			Messages.addError("msgGrowl", this.csMsg.getString("please.fill.1.field"));
			rc.update("frmMain:msgGrowl");
		} else {
			if(shDOB != null && shDOB.compareTo(DateUtil.getCurrentDate()) > 0) {
				Messages.addError("msgGrowl", this.csMsg.getString("dob.must.exeed.today"));
				rc.update("frmMain:msgGrowl");
				return;
			}
			
			List<Customer> list = findCustomer();
			
			if(list.isEmpty()) {
				Messages.addError("msgGrowl", this.csMsg.getString("search.not.found"));
				addNew = true;
				rc.update(Arrays.asList("frmMain:panelBtnAdd", "frmMain:msgGrowl"));
			} else if(list.size() == 1) {
				model.setCustomer(list.get(0));
				clearSh();
				logicPolicyByCus();
				rc.execute("PF('searchDlg').hide();");
				rc.update("frmMain");
			} else {
				model.setCustomerFounds(list);
				rc.update("frmMain:selectCustomerDlg");
				rc.execute("PF('selectCustomerDlg').show();");
			}
			
		}
	}
	
	public void doAddNewCustomer() {
		RequestContext rc = RequestContext.getCurrentInstance();
		final String msgGrowlId = "frmMain:msgGrowl";
		if(validateIsShEmpty()) {
			rc.execute("PF('searchDlg').jq.effect('shake', {times:5}, 100);");
			Messages.addError("msgGrowl", this.csMsg.getString("please.fill.1.field"));
			rc.update(msgGrowlId);
		} else {
			if(shDOB != null && shDOB.compareTo(DateUtil.getCurrentDate()) > 0) {
				Messages.addError("msgGrowl", this.csMsg.getString("dob.must.exeed.today"));
				rc.update(msgGrowlId);
				return;
			}
			
			if(StringUtils.isBlank(shFirstName) || StringUtils.isBlank(shLastName)) {
				Messages.addError("msgGrowl", this.csMsg.getString("error.required.firstname.lastname"));
				((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmMain:shInsuredFname")).setValid(false);
				((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmMain:shInsuredLname")).setValid(false);
				rc.update(msgGrowlId);
				return;
			}
			
			if(StringUtils.isBlank(shCitizenId) && StringUtils.isBlank(shTel)) {
				Messages.addError("msgGrowl", this.csMsg.getString("error.required.citizen.or.tel"));
				rc.update(msgGrowlId);
				return;
			}
			
			List<Customer> list = findCustomer();
			
			if(!list.isEmpty()) {
				Messages.addError("msgGrowl", this.csMsg.getString("error.customer.already.exist"));
				rc.update(Arrays.asList("frmMain:panelBtnAdd", msgGrowlId));
				addNew = false;
				return;
			}
			
			Customer customer = new Customer();
			customer.setFirstName(shFirstName.toUpperCase().trim())
					.setLastName(shLastName.toUpperCase().trim())
					.setCitizenId(shCitizenId)
					.setMobileNo(shTel)
					.setVisible("Y")
					;
			try {
				customer = customerService.add(customer, loginSession.getUsername());
			} catch (Exception e) {
				Messages.addError("msgGrowl", "System Error! Please contact system admin.: " + e.getMessage());
				rc.update(msgGrowlId);
				return;
			}
			
			model.setCustomer(customer);
			clearSh();
			logicPolicyByCus();
			rc.execute("PF('searchDlg').hide();");
			rc.update("frmMain");
		}
	}
	
	public void doAddDummyDataForNonCustomer() throws Exception {
		CustomerYesRecord dummyPolicy = new CustomerYesRecord();
		dummyPolicy.setImportDate(new Date());
		dummyPolicy.setPolicyNo("NonCust-" + model.getCustomer().getId());
		dummyPolicy.setCustomer(model.getCustomer());
		dummyPolicy.setEffectiveDate(new Date());
		dummyPolicy.setPremium(new BigDecimal(0).setScale(10));
		dummyPolicy = customerYesRecordService.add(dummyPolicy, loginSession.getUsername());
		
		List<CustomerPolicyBean> policyBeans = new ArrayList<>();
		policyBeans.add(new CustomerPolicyBean().setCustomerYesRecord(dummyPolicy)
												.setCampaignName(model.OTHER_CAMPAIGN_NAME)
												.setRefNo(dummyPolicy.getPolicyNo())
												.setPremium(dummyPolicy.getPremium().doubleValue())
												.setObjectDate(dummyPolicy.getEffectiveDate()));
		model.setCustomerYRs2(policyBeans);
		model.setNonCustomer(false);
		
		doVisibleLogHistory(dummyPolicy);
	}

	public void doVisibleLogHistory(CustomerYesRecord obj) throws Exception {
		model.setPolicy(obj);
		List<CallLog> list = null;
		
		DetachedCriteria criteria = DetachedCriteria.forClass(CallLog.class);
		DetachedCriteria customerYes = criteria.createCriteria("customerYesRecord", JoinType.INNER_JOIN);

		if(obj.getSales() != null) {
			customerYes.add(Restrictions.eq("sales.xReference", obj.getSales().getxReference()));
		} else {
			customerYes.add(Restrictions.eq("policyNo", obj.getPolicyNo()));
		}
		
		criteria.addOrder(Order.desc("id"));
		
		list = callLogService.findByCriteria(criteria);
		if(list != null && !list.isEmpty()) {
			this.model.setPolicyCallLogs(list);
		} else {
			this.model.setPolicyCallLogs(new ArrayList<>());
		}
		
		initAllDropdownMenu();
	}
	
	public void doInvisibleLogHistory() {
		this.model.setPolicyCallLogs(null);
	}
	
	public void doInitLog(CallLog callLog) {
		clearAddCaseLog();
		model.setLogDate(DateUtil.getCurrentDate());
		if(callLog == null) {
			updateLog = false;
			selectedCallLog = null;
		} else {
			updateLog = true;
			selectedCallLog = callLog;
			
			model.setSelectedSource(callLog.getSource().getId());
			model.setSelectedChannel(callLog.getChannel().getId());
			model.setSelectedCallNature(callLog.getCallLogDetail().getLevelCallNature().getId());
			model.setSelectedCategory(callLog.getCallLogDetail().getLevelCallCategory().getId());
			initCallSubCategory();
			model.setSelectedSubCategory(callLog.getCallLogDetail().getLogValue().getId());
			model.setLogDetail(callLog.getDetail());
			model.setSelectedComplaintStatus(callLog.getLogCurrentStatus().getId());
			model.setSelectedComplaintResult(callLog.getLogResult() != null ? callLog.getLogResult().getId() : null);
			model.setResultDetail(callLog.getLogResultDetail());
			model.setCorrectiveAction(callLog.getCorrectiveAction());
			model.setSuggestDetail(callLog.getSuggestDetail());
			model.setLogRemark(callLog.getRemark());
		}
	}
	
	public void doAddCallLog() throws Exception {
		if(model == null) throw new Exception("ERROR, please refresh page");
		RequestContext rc = RequestContext.getCurrentInstance();
		
		boolean flag = validateAddCaseLog();
		if(!flag) {
			Messages.addError("msgAddCase", this.csMsg.getString("error.required.some"));
			rc.update("frmMain:panelLogDetail");
		} else {
			saveCallLog(
					updateLog
					, model.getPolicy()
					, model.getLogDate()
					, model.getSelectedSource()
					, model.getSelectedChannel()
					, model.getSelectedSubCategory()
					, model.getLogDetail()
					
					, model.getSelectedComplaintStatus()
					, model.getSelectedComplaintResult()
					, model.getResultDetail()
					, model.getCorrectiveAction()
					, model.getSuggestDetail()
					
					, model.getLogRemark());
			doVisibleLogHistory(model.getPolicy());
			
			clearAddCaseLog();
			selectedCallLog = null;
			rc.update("frmMain:panelLogHistTbl");
			rc.execute("PF('addCaseDlg').hide();");
		}
	}

	public void onRowSelectLog(SelectEvent event) {
		doInitLog((CallLog) event.getObject());
    }
	
	public void onSelectCustomer(SelectEvent event) {
		clearSh();
		model.setCustomerFounds(null);
		model.setCustomerYRs(null);
		model.setPolicyCallLogs(null);
		logicPolicyByCus();
	}

	private boolean validateAddCaseLog() {
		//Message ID: frmMain:msgAddCase
		//frmMain:logSourceName
		//channel, callNature, callCategory, callSubcategory
		boolean flag = true;
		
		if(model.getSelectedSource() == DEFAULT_SELECT_ONE_MENU_VALUE) {
			((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmMain:logSourceName")).setValid(false);
			flag = false;
		}
		
		if(model.getSelectedChannel() == DEFAULT_SELECT_ONE_MENU_VALUE) {
			((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmMain:channel")).setValid(false);
//			Messages.addError("msgAddCase", "Channel is Required.");
			flag = false;
		}
		
		if(model.getSelectedCallNature() == DEFAULT_SELECT_ONE_MENU_VALUE) {
			((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmMain:callNature")).setValid(false);
//			Messages.addError("msgAddCase", "Call Nature is Required.");
			flag = false;
		}
		
		if(model.getSelectedCategory() == DEFAULT_SELECT_ONE_MENU_VALUE) {
			((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmMain:callCategory")).setValid(false);
//			Messages.addError("msgAddCase", "Call Category is Required.");
			flag = false;
		}
		
		if(model.getSelectedSubCategory() == DEFAULT_SELECT_ONE_MENU_VALUE) {
			((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmMain:callSubcategory")).setValid(false);
//			Messages.addError("msgAddCase", "Call Sub-category is Required.");
			flag = false;
		}
		
		return flag;
	}

	private CallLog saveCallLog(boolean updateLog, CustomerYesRecord policy, Date logDate, Long source, Long channel, Long callLogDetail, String detail
			, Long complaintStatus, Long complaintResult, String resultDetail, String correctiveAction, String suggestDetail, String remark) throws Exception {
		CallLog example = new CallLog();
		example.setCustomerYesRecord(policy);
		example.setCallDate(logDate);
		example.setSource(logStatusGroupService.find(source));
		example.setChannel(logStatusGroupService.find(channel));
		
		DetachedCriteria logDetailCriteria = DetachedCriteria.forClass(LogDetailCategory.class);
		logDetailCriteria.add(Restrictions.eq("logValue.id", callLogDetail));
		example.setCallLogDetail(logDetailCategoryService.findByCriteria(logDetailCriteria).get(0));
		if(!StringUtils.isBlank(detail)) example.setDetail(detail);
		
		if(complaintStatus > 0) {
			LogStatusGroup lsgComplaint = logStatusGroupService.find(complaintStatus);
			example.setLogCurrentStatus(lsgComplaint);
			if(lsgComplaint.getParam().equals("COMPLAINT_STATUS_CLOSE_CANNOT_CONTACT") 
					|| lsgComplaint.getParam().equals("COMPLAINT_STATUS_CLOSE_CAN_CONTACT_CUSTOMER")) {
				example.setClosedDate(DateUtil.getCurrentDate());
			} else {
				example.setClosedDate(null);
			}
		}
		if(complaintResult > 0) example.setLogResult(logStatusGroupService.find(complaintResult));
		example.setLogResultDetail(resultDetail);
		example.setCorrectiveAction(correctiveAction);
		example.setSuggestDetail(suggestDetail);
		
		if(!StringUtils.isBlank(detail)) example.setRemark(remark);
		
		if(updateLog) {
			example.setId(selectedCallLog.getId());
			example.setCreateBy(selectedCallLog.getCreateBy());
			example.setCreateDate(selectedCallLog.getCreateDate());
			
			return callLogService.update(example, loginSession.getUsername());
		} else {
			return callLogService.add(example, loginSession.getUsername());
		}
	}
	
	private void logicPolicyByCus() {
		List<CustomerYesRecord> list = findPolicyByCustomer();
		List<CustomerPolicyBean> policyBeans = new ArrayList<>();
		
		if(list != null && !list.isEmpty()) {
			policyBeans = list.stream()
					.map(m -> new CustomerPolicyBean()
						.setCustomerYesRecord(m)
						.setCampaignName(m.getSales() != null ? m.getSales().getListLot().getCampaign().getCampaignName() : model.OTHER_CAMPAIGN_NAME)
						.setObjectDate(m.getSales() != null ? m.getSales().getSaleDate() : m.getEffectiveDate())
						.setRefNo(m.getSales() != null ? m.getSales().getxReference() : m.getPolicyNo())
						.setPremium(m.getSales() != null ? m.getSales().getPremium().doubleValue() : m.getPremium().doubleValue()))
					.collect(Collectors.toList());
			model.setNonCustomer(false);
		} else {
			model.setNonCustomer(true);
		}
		
		model.setCustomerYRs2(policyBeans);
		
	}
	
	private List<Customer> findCustomer() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
			if(!StringUtils.isBlank(shCitizenId)) criteria.add(Restrictions.eq("citizenId", shCitizenId));
			if(!StringUtils.isBlank(shTel)) {
				criteria.add(Restrictions
						.disjunction()
							.add(Restrictions.eq("homeNo", shTel))
							.add(Restrictions.eq("mobileNo", shTel))
							.add(Restrictions.eq("otherNo", shTel))
							.add(Restrictions.eq("officeNo", shTel)));
			}
			if(!StringUtils.isBlank(shFirstName)) criteria.add(Restrictions.like("firstName", shFirstName.trim().toUpperCase(), MatchMode.ANYWHERE));
			if(!StringUtils.isBlank(shLastName)) criteria.add(Restrictions.like("lastName", shLastName.trim().toUpperCase(), MatchMode.ANYWHERE));
			if(shDOB != null) criteria.add(Restrictions.eq("dob", shDOB));
			criteria.add(Restrictions.eq("visible", "Y"));
			
			return customerService.findByCriteria(criteria);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<CustomerYesRecord> findPolicyByCustomer() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CustomerYesRecord.class);
			criteria.createCriteria("customer").add(Restrictions.eq("id", this.model.getCustomer().getId()));
			return customerYesRecordService.findByCriteria(criteria);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<SelectItem> createSelectItems(String groupParam) {
		List<LogStatusGroup> list = callLogGrupStatMap.get(groupParam);
		List<SelectItem> selectItems = new ArrayList<>();
		
		selectItems.add(new SelectItem(DEFAULT_SELECT_ONE_MENU_VALUE, PLEASE_SELECT));
		for(LogStatusGroup s : list) {
			selectItems.add(new SelectItem(s.getId(), s.getValue()));
		}
		return selectItems;
	}

	public CustomerEnquiryModel getModel() {
		return model;
	}

	public void setModel(CustomerEnquiryModel model) {
		this.model = model;
	}

	public void setLanguage(LanguageBean language) {
		this.language = language;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public String getShCitizenId() {
		return shCitizenId;
	}

	public void setShCitizenId(String shCitizenId) {
		this.shCitizenId = shCitizenId;
	}

	public String getShFirstName() {
		return shFirstName;
	}

	public void setShFirstName(String shFirstName) {
		this.shFirstName = shFirstName;
	}

	public String getShLastName() {
		return shLastName;
	}

	public void setShLastName(String shLastName) {
		this.shLastName = shLastName;
	}

	public Date getShDOB() {
		return shDOB;
	}

	public void setShDOB(Date shDOB) {
		this.shDOB = shDOB;
	}

	public void setCallLogService(CallLogService callLogService) {
		this.callLogService = callLogService;
	}

	public void setCustomerYesRecordService(CustomerYesRecordService customerYesRecordService) {
		this.customerYesRecordService = customerYesRecordService;
	}

	public void setLogDetailCategoryService(LogDetailCategoryService logDetailCategoryService) {
		this.logDetailCategoryService = logDetailCategoryService;
	}

	public void setLogStatusGroupService(LogStatusGroupService logStatusGroupService) {
		this.logStatusGroupService = logStatusGroupService;
	}

	public void setSalesService(SalesService salesService) {
		this.salesService = salesService;
	}

	public boolean isUpdateLog() {
		return updateLog;
	}

	public void setUpdateLog(boolean updateLog) {
		this.updateLog = updateLog;
	}

	public CallLog getSelectedCallLog() {
		return selectedCallLog;
	}

	public void setSelectedCallLog(CallLog selectedCallLog) {
		this.selectedCallLog = selectedCallLog;
	}
	
	public void setLoginSession(LoginSession loginSession) {
		this.loginSession = loginSession;
	}

	public String getShTel() {
		return shTel;
	}

	public void setShTel(String shTel) {
		this.shTel = shTel;
	}

	public boolean isAddNew() {
		return addNew;
	}

	public void setAddNew(boolean addNew) {
		this.addNew = addNew;
	}

}
