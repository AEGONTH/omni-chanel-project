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
@Table(name="OMNI_TRAVEL_HIST")
public class OmniTravelHist extends BaseAuditDomain {

	private static final long serialVersionUID = -6049567810159875355L;

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
	
	@Column(name="TRAVEL_PURPOSE")
	private String travelPurpose;
	
	@ManyToOne
	@JoinColumn(name="DESTINATION_COUNTRY", referencedColumnName="COUNTRY_CODE")
	private Country country;
	
	@Column(name="DEPARTURE_DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDateTime;
	
	@Column(name="DEPARTURE_FLIGHT_NO")
	private String departureFlightNo;
	
	@Column(name="ARRIVAL_DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDateTime;
	
	@Column(name="ARRIVAL_FLIGHT_NO")
	private String arrivalFlightNo;
	
	@ManyToOne
	@JoinColumn(name="PLAN_CODE", referencedColumnName="PLAN_CODE")
	private ProductPlan productPlan;
	
	@Column(name="LATEST")
	private String latest;

	public Long getId() {
		return id;
	}

	public OmniTravelHist setId(Long id) {
		this.id = id;
		return this;
	}

	public OmniChLog getOmniChLog() {
		return omniChLog;
	}

	public OmniTravelHist setOmniChLog(OmniChLog omniChLog) {
		this.omniChLog = omniChLog;
		return this;
	}

	public Date getLogDate() {
		return logDate;
	}

	public OmniTravelHist setLogDate(Date logDate) {
		this.logDate = logDate;
		return this;
	}

	public CategoryData getChannel() {
		return channel;
	}

	public OmniTravelHist setChannel(CategoryData channel) {
		this.channel = channel;
		return this;
	}

	public CategoryData getContactReason() {
		return contactReason;
	}

	public OmniTravelHist setContactReason(CategoryData contactReason) {
		this.contactReason = contactReason;
		return this;
	}

	public CategoryData getStatus() {
		return status;
	}

	public OmniTravelHist setStatus(CategoryData status) {
		this.status = status;
		return this;
	}

	public String getDetail() {
		return detail;
	}

	public OmniTravelHist setDetail(String detail) {
		this.detail = detail;
		return this;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public OmniTravelHist setDueDate(Date dueDate) {
		this.dueDate = dueDate;
		return this;
	}

	public String getContactReasonDetail() {
		return contactReasonDetail;
	}

	public OmniTravelHist setContactReasonDetail(String contactReasonDetail) {
		this.contactReasonDetail = contactReasonDetail;
		return this;
	}

	public String getLatest() {
		return latest;
	}

	public OmniTravelHist setLatest(String latest) {
		this.latest = latest;
		return this;
	}

	public String getTravelPurpose() {
		return travelPurpose;
	}

	public OmniTravelHist setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
		return this;
	}

	public Country getCountry() {
		return country;
	}

	public OmniTravelHist setCountry(Country country) {
		this.country = country;
		return this;
	}

	public Date getDepartureDateTime() {
		return departureDateTime;
	}

	public OmniTravelHist setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
		return this;
	}

	public String getDepartureFlightNo() {
		return departureFlightNo;
	}

	public OmniTravelHist setDepartureFlightNo(String departureFlightNo) {
		this.departureFlightNo = departureFlightNo;
		return this;
	}

	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	public OmniTravelHist setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
		return this;
	}

	public String getArrivalFlightNo() {
		return arrivalFlightNo;
	}

	public OmniTravelHist setArrivalFlightNo(String arrivalFlightNo) {
		this.arrivalFlightNo = arrivalFlightNo;
		return this;
	}

	public ProductPlan getProductPlan() {
		return productPlan;
	}

	public OmniTravelHist setProductPlan(ProductPlan productPlan) {
		this.productPlan = productPlan;
		return this;
	}
	
}
