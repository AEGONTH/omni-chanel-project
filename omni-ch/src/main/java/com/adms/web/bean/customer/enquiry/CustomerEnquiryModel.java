package com.adms.web.bean.customer.enquiry;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;

import com.adms.entity.cs.CallLog;
import com.adms.entity.cs.Customer;
import com.adms.entity.cs.CustomerYesRecord;

public class CustomerEnquiryModel implements Serializable {

	private static final long serialVersionUID = -1137407173421271827L;
	public final String OTHER_CAMPAIGN_NAME = "OMNI Channel or Non Customer Data";
	
	private final String searchDlg = "SEARCH_DLG";
	private final String addCaseDlg = "ADD_CASE_DLG";
	
	private String dlgHeaderVal;
	private Customer customer;
	private String customerAllTels;
	private List<Customer> customerFounds;
	private List<CustomerYesRecord> customerYRs;
	private List<CustomerPolicyBean> customerYRs2;
	private CustomerYesRecord policy;
	private List<CallLog> policyCallLogs;
	
	private List<SelectItem> sourceSelection;
	private List<SelectItem> channelSelection;
	private List<SelectItem> natureSelection;
	private List<SelectItem> categorySelection;
	private List<SelectItem> subCategorySelection;
	private List<SelectItem> callResultSelection;
	private List<SelectItem> cancelReasonSelection;
	private List<SelectItem> complaintStatusSelection;
	private List<SelectItem> complaintResultSelection;

	private Long selectedSource;
	private Long selectedChannel;
	private Long selectedCallNature;
	private Long selectedCategory;
	private Long selectedSubCategory;
	private Long selectedCallResult;
	private Long selectedCancelReason;
	private Long selectedComplaintStatus;
	private Long selectedComplaintResult;
	
	private Date logDate;
	private String logDetail;
	private String resultDetail;
	private String correctiveAction;
	private String suggestDetail;
	private String logRemark;
	
	private boolean nonCustomer;
	
	public CustomerEnquiryModel() {
		dlgHeaderVal = "";
	}

	public String getDlgHeaderVal() {
		return dlgHeaderVal;
	}

	public void setDlgHeaderVal(String dlgHeaderVal) {
		this.dlgHeaderVal = dlgHeaderVal;
	}

	public String getSearchDlg() {
		return searchDlg;
	}

	public String getAddCaseDlg() {
		return addCaseDlg;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CustomerYesRecord> getCustomerYRs() {
		return customerYRs;
	}

	public void setCustomerYRs(List<CustomerYesRecord> customerYRs) {
		this.customerYRs = customerYRs;
	}

	public List<Customer> getCustomerFounds() {
		return customerFounds;
	}

	public void setCustomerFounds(List<Customer> customerFounds) {
		this.customerFounds = customerFounds;
	}

	public List<CallLog> getPolicyCallLogs() {
		return policyCallLogs;
	}

	public void setPolicyCallLogs(List<CallLog> policyCallLogs) {
		this.policyCallLogs = policyCallLogs;
	}

	public List<SelectItem> getChannelSelection() {
		return channelSelection;
	}

	public void setChannelSelection(List<SelectItem> channelSelection) {
		this.channelSelection = channelSelection;
	}

	public List<SelectItem> getNatureSelection() {
		return natureSelection;
	}

	public void setNatureSelection(List<SelectItem> natureSelection) {
		this.natureSelection = natureSelection;
	}

	public List<SelectItem> getCategorySelection() {
		return categorySelection;
	}

	public void setCategorySelection(List<SelectItem> categorySelection) {
		this.categorySelection = categorySelection;
	}

	public List<SelectItem> getSubCategorySelection() {
		return subCategorySelection;
	}

	public void setSubCategorySelection(List<SelectItem> subCategorySelection) {
		this.subCategorySelection = subCategorySelection;
	}

	public List<SelectItem> getCallResultSelection() {
		return callResultSelection;
	}

	public void setCallResultSelection(List<SelectItem> callResultSelection) {
		this.callResultSelection = callResultSelection;
	}

	public List<SelectItem> getCancelReasonSelection() {
		return cancelReasonSelection;
	}

	public void setCancelReasonSelection(List<SelectItem> cancelReasonSelection) {
		this.cancelReasonSelection = cancelReasonSelection;
	}

	public Long getSelectedChannel() {
		return selectedChannel;
	}

	public void setSelectedChannel(Long selectedChannel) {
		this.selectedChannel = selectedChannel;
	}

	public Long getSelectedCallNature() {
		return selectedCallNature;
	}

	public void setSelectedCallNature(Long selectedCallNature) {
		this.selectedCallNature = selectedCallNature;
	}

	public Long getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Long selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public Long getSelectedSubCategory() {
		return selectedSubCategory;
	}

	public void setSelectedSubCategory(Long selectedSubCategory) {
		this.selectedSubCategory = selectedSubCategory;
	}

	public Long getSelectedCallResult() {
		return selectedCallResult;
	}

	public void setSelectedCallResult(Long selectedCallResult) {
		this.selectedCallResult = selectedCallResult;
	}

	public Long getSelectedCancelReason() {
		return selectedCancelReason;
	}

	public void setSelectedCancelReason(Long selectedCancelReason) {
		this.selectedCancelReason = selectedCancelReason;
	}

	public CustomerYesRecord getPolicy() {
		return policy;
	}

	public void setPolicy(CustomerYesRecord policy) {
		this.policy = policy;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getLogDetail() {
		return logDetail;
	}

	public void setLogDetail(String logDetail) {
		this.logDetail = logDetail;
	}

	public String getLogRemark() {
		return logRemark;
	}

	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}

	public Long getSelectedSource() {
		return selectedSource;
	}

	public void setSelectedSource(Long selectedResource) {
		this.selectedSource = selectedResource;
	}

	public List<SelectItem> getSourceSelection() {
		return sourceSelection;
	}

	public void setSourceSelection(List<SelectItem> resourceSelection) {
		this.sourceSelection = resourceSelection;
	}

	public List<SelectItem> getComplaintStatusSelection() {
		return complaintStatusSelection;
	}

	public void setComplaintStatusSelection(List<SelectItem> complaintStatusSelection) {
		this.complaintStatusSelection = complaintStatusSelection;
	}

	public List<SelectItem> getComplaintResultSelection() {
		return complaintResultSelection;
	}

	public void setComplaintResultSelection(List<SelectItem> complaintResultSelection) {
		this.complaintResultSelection = complaintResultSelection;
	}

	public Long getSelectedComplaintStatus() {
		return selectedComplaintStatus;
	}

	public void setSelectedComplaintStatus(Long selectedComplaintStatus) {
		this.selectedComplaintStatus = selectedComplaintStatus;
	}

	public Long getSelectedComplaintResult() {
		return selectedComplaintResult;
	}

	public void setSelectedComplaintResult(Long selectedComplaintResult) {
		this.selectedComplaintResult = selectedComplaintResult;
	}

	public String getResultDetail() {
		return resultDetail;
	}

	public void setResultDetail(String resultDetail) {
		this.resultDetail = resultDetail;
	}

	public String getCorrectiveAction() {
		return correctiveAction;
	}

	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}

	public String getSuggestDetail() {
		return suggestDetail;
	}

	public void setSuggestDetail(String suggestDetail) {
		this.suggestDetail = suggestDetail;
	}

	public List<CustomerPolicyBean> getCustomerYRs2() {
		return customerYRs2;
	}

	public void setCustomerYRs2(List<CustomerPolicyBean> customerYRs2) {
		this.customerYRs2 = customerYRs2;
	}

	public String getCustomerAllTels() {
		if(customer != null) {
			customerAllTels = concating(customer.getHomeNo(), customer.getMobileNo(), customer.getOfficeNo(), customer.getOtherNo());
		}
		return customerAllTels;
	}
	
	private String concating(String...vals) {
		String concat = "";
		if(vals != null) {
			for(String val : vals) {
				if(StringUtils.isNotBlank(val)) {
					concat += (val + ", ");
				}
			}
			concat = concat.substring(0, concat.lastIndexOf(","));
		}
		return concat;
	}

	public boolean isNonCustomer() {
		return nonCustomer;
	}

	public void setNonCustomer(boolean nonCustomer) {
		this.nonCustomer = nonCustomer;
	}

}
