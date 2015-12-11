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
@Table(name="OMNI_LOG_MOTOR_HIST")
public class OmniLogMotorHist extends BaseAuditDomain {

	private static final long serialVersionUID = -3733399494331331694L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="LOG_MOTOR_ID")
	private OmniLogMotor omniLogMotor;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OmniLogMotor getOmniLogMotor() {
		return omniLogMotor;
	}

	public OmniLogMotorHist setOmniLogMotor(OmniLogMotor omniLogMotor) {
		this.omniLogMotor = omniLogMotor;
		return this;
	}

	public Date getLogDate() {
		return logDate;
	}

	public OmniLogMotorHist setLogDate(Date logDate) {
		this.logDate = logDate;
		return this;
	}

	public CategoryData getChannel() {
		return channel;
	}

	public OmniLogMotorHist setChannel(CategoryData channel) {
		this.channel = channel;
		return this;
	}

	public CategoryData getContactReason() {
		return contactReason;
	}

	public OmniLogMotorHist setContactReason(CategoryData contactReason) {
		this.contactReason = contactReason;
		return this;
	}

	public CategoryData getStatus() {
		return status;
	}

	public OmniLogMotorHist setStatus(CategoryData status) {
		this.status = status;
		return this;
	}

	public String getDetail() {
		return detail;
	}

	public OmniLogMotorHist setDetail(String detail) {
		this.detail = detail;
		return this;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public OmniLogMotorHist setDueDate(Date dueDate) {
		this.dueDate = dueDate;
		return this;
	}

	public CategoryData getCar() {
		return car;
	}

	public OmniLogMotorHist setCar(CategoryData car) {
		this.car = car;
		return this;
	}

	public String getCarYear() {
		return carYear;
	}

	public OmniLogMotorHist setCarYear(String carYear) {
		this.carYear = carYear;
		return this;
	}

	public ProductPlan getProductPlan() {
		return productPlan;
	}

	public OmniLogMotorHist setProductPlan(ProductPlan productPlan) {
		this.productPlan = productPlan;
		return this;
	}

	public ListSource getListSource() {
		return listSource;
	}

	public OmniLogMotorHist setListSource(ListSource listSource) {
		this.listSource = listSource;
		return this;
	}
	
}
