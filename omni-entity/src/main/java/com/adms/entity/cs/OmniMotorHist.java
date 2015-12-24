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
@Table(name="OMNI_MOTOR_HIST")
public class OmniMotorHist extends BaseAuditDomain {

	private static final long serialVersionUID = 2601264871583159557L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="OMNI_CH_LOG_ID")
	private OmniChLog omniChLog;
	
	@Column(name="LOG_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logDate;
	
	@ManyToOne
	@JoinColumn(name="CHANNEL_CODE", referencedColumnName="CATEGORY_CODE")
	private CategoryData channel;

	@ManyToOne
	@JoinColumn(name="CONTACT_REASON_CODE", referencedColumnName="CATEGORY_CODE")
	private CategoryData contactReason;

	@ManyToOne
	@JoinColumn(name="STATUS_CODE", referencedColumnName="CATEGORY_CODE")
	private CategoryData status;
	
	@Column(name="CONTACT_REASON_DETAIL")
	private String contactReasonDetail;
	
	@Column(name="DETAIL")
	private String detail;
	
	@Column(name="DUE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;
	
	@ManyToOne
	@JoinColumn(name="CAR_CODE", referencedColumnName="CATEGORY_CODE")
	private CategoryData car;
	
	@Column(name="CAR_YEAR")
	private String carYear;
	
	@ManyToOne
	@JoinColumn(name="PLAN_CODE", referencedColumnName="PLAN_CODE")
	private ProductPlan productPlan;
	
	@ManyToOne
	@JoinColumn(name="LIST_SOURCE_CODE", referencedColumnName="LIST_SOURCE_CODE")
	private ListSource listSource;
	
	@Column(name="LATEST")
	private String latest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OmniChLog getOmniChLog() {
		return omniChLog;
	}

	public OmniMotorHist setOmniChLog(OmniChLog omniChLog) {
		this.omniChLog = omniChLog;
		return this;
	}

	public Date getLogDate() {
		return logDate;
	}

	public OmniMotorHist setLogDate(Date logDate) {
		this.logDate = logDate;
		return this;
	}

	public CategoryData getChannel() {
		return channel;
	}

	public OmniMotorHist setChannel(CategoryData channel) {
		this.channel = channel;
		return this;
	}

	public CategoryData getContactReason() {
		return contactReason;
	}

	public OmniMotorHist setContactReason(CategoryData contactReason) {
		this.contactReason = contactReason;
		return this;
	}

	public CategoryData getStatus() {
		return status;
	}

	public OmniMotorHist setStatus(CategoryData status) {
		this.status = status;
		return this;
	}

	public String getDetail() {
		return detail;
	}

	public OmniMotorHist setDetail(String detail) {
		this.detail = detail;
		return this;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public OmniMotorHist setDueDate(Date dueDate) {
		this.dueDate = dueDate;
		return this;
	}

	public CategoryData getCar() {
		return car;
	}

	public OmniMotorHist setCar(CategoryData car) {
		this.car = car;
		return this;
	}

	public String getCarYear() {
		return carYear;
	}

	public OmniMotorHist setCarYear(String carYear) {
		this.carYear = carYear;
		return this;
	}

	public ProductPlan getProductPlan() {
		return productPlan;
	}

	public OmniMotorHist setProductPlan(ProductPlan productPlan) {
		this.productPlan = productPlan;
		return this;
	}

	public ListSource getListSource() {
		return listSource;
	}

	public OmniMotorHist setListSource(ListSource listSource) {
		this.listSource = listSource;
		return this;
	}

	public String getContactReasonDetail() {
		return contactReasonDetail;
	}

	public OmniMotorHist setContactReasonDetail(String contactReasonDetail) {
		this.contactReasonDetail = contactReasonDetail;
		return this;
	}

	public String getLatest() {
		return latest;
	}

	public void setLatest(String latest) {
		this.latest = latest;
	}
	
}
