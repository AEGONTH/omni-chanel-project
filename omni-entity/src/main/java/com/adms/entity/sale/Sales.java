package com.adms.entity.sale;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "S_SALES")
public class Sales extends BaseAuditDomain {

	private static final long serialVersionUID = 456533834897529961L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@Column(name = "IMPORT_ID")
	private String fileImport;

	@Column(name = "X_REFERENCE")
	private String xReference;

	@Column(name = "X_REFERENCE_NEW")
	private String xReferenceNew;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "LIST_LOT_CODE", referencedColumnName = "LIST_LOT_CODE")
	private ListLot listLot;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "TSR_CODE", referencedColumnName = "TSR_CODE")
	private Tsr tsr;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "SUP_CODE", referencedColumnName = "TSR_CODE")
	private Tsr supervisor;

	@Column(name = "SALE_DATE")
	@Temporal(TemporalType.DATE)
	private Date saleDate;

	@Column(name = "APPROVE_DATE")
	@Temporal(TemporalType.DATE)
	private Date approveDate;

	@Column(name = "APPROVE_DATE_PENDING")
	@Temporal(TemporalType.DATE)
	private Date approveDatePending;

	@Column(name = "ITEM_NO")
	private Integer itemNo;

	@Column(name = "CUSTOMER_FULL_NAME")
	private String customerFullName;

	@Column(name = "CUSTOMER_TITLE")
	private String customerTitle;

	@Column(name = "CUSTOMER_FIRST_NAME")
	private String customerFirstName;

	@Column(name = "CUSTOMER_MID_NAME")
	private String customerMidName;

	@Column(name = "CUSTOMER_LAST_NAME")
	private String customerLastName;

	@Column(name = "PRODUCT")
	private String product;

	@Column(name = "PREMIUM")
	private BigDecimal premium;

	@Column(name = "ANNUAL_FYP")
	private BigDecimal annualFyp;

	@Column(name = "PROTECT_AMOUNT")
	private String protectAmount;

	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;

	@Column(name = "PAYMENT_FREQUENCY")
	private String paymentFrequency;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "QA_STATUS", referencedColumnName = "RECONFIRM_STATUS")
	private ReconfirmStatus qaStatus;

	@Column(name = "QA_REASON")
	private String qaReason;

	@Column(name = "QA_REASON_DETAIL")
	private String qaReasonDetail;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFileImport()
	{
		return fileImport;
	}

	public void setFileImport(String fileImport)
	{
		this.fileImport = fileImport;
	}

	public String getxReference()
	{
		return xReference;
	}

	public void setxReference(String xReference)
	{
		this.xReference = xReference;
	}

	public String getxReferenceNew()
	{
		return xReferenceNew;
	}

	public void setxReferenceNew(String xReferenceNew)
	{
		this.xReferenceNew = xReferenceNew;
	}

	public ListLot getListLot()
	{
		return listLot;
	}

	public void setListLot(ListLot listLot)
	{
		this.listLot = listLot;
	}

	public Tsr getTsr()
	{
		return tsr;
	}

	public void setTsr(Tsr tsr)
	{
		this.tsr = tsr;
	}

	public Tsr getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(Tsr supervisor)
	{
		this.supervisor = supervisor;
	}

	public Date getSaleDate()
	{
		return saleDate;
	}

	public void setSaleDate(Date saleDate)
	{
		this.saleDate = saleDate;
	}

	public Date getApproveDate()
	{
		return approveDate;
	}

	public void setApproveDate(Date approveDate)
	{
		this.approveDate = approveDate;
	}

	public Date getApproveDatePending()
	{
		return approveDatePending;
	}

	public void setApproveDatePending(Date approveDatePending)
	{
		this.approveDatePending = approveDatePending;
	}

	public Integer getItemNo()
	{
		return itemNo;
	}

	public void setItemNo(Integer itemNo)
	{
		this.itemNo = itemNo;
	}

	public String getCustomerFullName()
	{
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName)
	{
		this.customerFullName = customerFullName;
	}

	public String getCustomerTitle()
	{
		return customerTitle;
	}

	public void setCustomerTitle(String customerTitle)
	{
		this.customerTitle = customerTitle;
	}

	public String getCustomerFirstName()
	{
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName)
	{
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerMidName()
	{
		return customerMidName;
	}

	public void setCustomerMidName(String customerMidName)
	{
		this.customerMidName = customerMidName;
	}

	public String getCustomerLastName()
	{
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName)
	{
		this.customerLastName = customerLastName;
	}

	public String getProduct()
	{
		return product;
	}

	public void setProduct(String product)
	{
		this.product = product;
	}

	public BigDecimal getPremium()
	{
		return premium;
	}

	public void setPremium(BigDecimal premium)
	{
		this.premium = premium;
	}

	public BigDecimal getAnnualFyp()
	{
		return annualFyp;
	}

	public void setAnnualFyp(BigDecimal annualFyp)
	{
		this.annualFyp = annualFyp;
	}

	public String getProtectAmount()
	{
		return protectAmount;
	}

	public void setProtectAmount(String protectAmount)
	{
		this.protectAmount = protectAmount;
	}

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentFrequency()
	{
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency)
	{
		this.paymentFrequency = paymentFrequency;
	}

	public ReconfirmStatus getQaStatus()
	{
		return qaStatus;
	}

	public void setQaStatus(ReconfirmStatus qaStatus)
	{
		this.qaStatus = qaStatus;
	}

	public String getQaReason()
	{
		return qaReason;
	}

	public void setQaReason(String qaReason)
	{
		this.qaReason = qaReason;
	}

	public String getQaReasonDetail()
	{
		return qaReasonDetail;
	}

	public void setQaReasonDetail(String qaReasonDetail)
	{
		this.qaReasonDetail = qaReasonDetail;
	}

}
