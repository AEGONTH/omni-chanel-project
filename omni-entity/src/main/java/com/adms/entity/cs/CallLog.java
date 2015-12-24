package com.adms.entity.cs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name="CALL_LOG")
public class CallLog extends BaseAuditDomain {

	private static final long serialVersionUID = -3120190981448911783L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="CALL_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date callDate;
	
	@ManyToOne
	@JoinColumn(name="SOURCE")
	private LogStatusGroup source;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_YES_REC_ID")
	private CustomerYesRecord customerYesRecord;
	
	@Column(name="POLICY_NO")
	private String policyNo;

	@ManyToOne
	@JoinColumn(name="CHANNEL")
	private LogStatusGroup channel;
	
	@ManyToOne
	@JoinColumn(name="CALL_LOG_NATURE")
	private LogStatusGroup callLogNature;
	
	@ManyToOne
	@JoinColumn(name="CALL_LOG_CATEGORY")
	private LogStatusGroup callLogCategory;
	
	@ManyToOne
	@JoinColumn(name="CALL_LOG_DETAIL", referencedColumnName="CALL_DETAIL_VALUE")
	private LogDetailCategory callLogDetail;
	
	@ManyToOne
	@JoinColumn(name="CALL_RESULT")
	private LogStatusGroup callResult;
	
	@ManyToOne
	@JoinColumn(name="CANCEL_REASON")
	private LogStatusGroup cancelReason;
	
	@Column(name="DETAIL")
	private String detail;
	
	@ManyToOne
	@JoinColumn(name="LOG_CURRENT_STATUS")
	private LogStatusGroup logCurrentStatus;
	
	@ManyToOne
	@JoinColumn(name="LOG_RESULT")
	private LogStatusGroup logResult;
	
	@Column(name="LOG_RESULT_DETAIL")
	private String logResultDetail;
	
	@Column(name="CORRECTIVE_ACTION")
	private String correctiveAction;
	
	@Column(name="SUGGEST_DETAIL")
	private String suggestDetail;
	
	@Column(name="CLOSED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closedDate;
	
	@Column(name="REMARK")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public LogStatusGroup getSource() {
		return source;
	}

	public void setSource(LogStatusGroup source) {
		this.source = source;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerYesRecord getCustomerYesRecord() {
		return customerYesRecord;
	}

	public void setCustomerYesRecord(CustomerYesRecord customerYesRecord) {
		this.customerYesRecord = customerYesRecord;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public LogStatusGroup getChannel() {
		return channel;
	}

	public void setChannel(LogStatusGroup channel) {
		this.channel = channel;
	}

	public LogStatusGroup getCallLogNature() {
		return callLogNature;
	}

	public void setCallLogNature(LogStatusGroup callLogNature) {
		this.callLogNature = callLogNature;
	}

	public LogStatusGroup getCallLogCategory() {
		return callLogCategory;
	}

	public void setCallLogCategory(LogStatusGroup callLogCategory) {
		this.callLogCategory = callLogCategory;
	}

	public LogDetailCategory getCallLogDetail() {
		return callLogDetail;
	}

	public void setCallLogDetail(LogDetailCategory callLogDetail) {
		this.callLogDetail = callLogDetail;
	}

	public LogStatusGroup getCallResult() {
		return callResult;
	}

	public void setCallResult(LogStatusGroup callResult) {
		this.callResult = callResult;
	}

	public LogStatusGroup getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(LogStatusGroup cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LogStatusGroup getLogCurrentStatus() {
		return logCurrentStatus;
	}

	public void setLogCurrentStatus(LogStatusGroup logCurrentStatus) {
		this.logCurrentStatus = logCurrentStatus;
	}

	public LogStatusGroup getLogResult() {
		return logResult;
	}

	public void setLogResult(LogStatusGroup logResult) {
		this.logResult = logResult;
	}

	public String getLogResultDetail() {
		return logResultDetail;
	}

	public void setLogResultDetail(String logResultDetail) {
		this.logResultDetail = logResultDetail;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

}
