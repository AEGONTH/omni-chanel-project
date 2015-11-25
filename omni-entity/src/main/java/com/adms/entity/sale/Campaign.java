package com.adms.entity.sale;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "S_CAMPAIGN")
@Cacheable
public class Campaign extends BaseAuditDomain {

	private static final long serialVersionUID = -2317187847529467988L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CAMPAIGN_YEAR")
	private String campaignYear;

	@Column(name = "CAMPAIGN_CODE")
	private String campaignCode;

	@Column(name = "CAMPAIGN_NAME")
	private String campaignName;

	@Column(name = "CAMPAIGN_NAME_COMM")
	private String campaignNameComm;

	@Column(name = "CAMPAIGN_NAME_MGL")
	private String campaignNameMgl;

	@Column(name = "REPORT_NAME")
	private String reportName;

	@Column(name = "CALL_CENTER")
	private String callCenter;

	@Column(name = "LIST_SOURCE")
	private String listSource;

	@Column(name = "INSURER")
	private String insurer;

	@Column(name = "PRODUCT_CODE")
	private String productCode;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "IS_ACTIVE")
	private String isActive;
	
	public Campaign() {
		
	}
	
	public Campaign(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCampaignYear()
	{
		return campaignYear;
	}

	public void setCampaignYear(String campaignYear)
	{
		this.campaignYear = campaignYear;
	}

	public String getCampaignCode()
	{
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode)
	{
		this.campaignCode = campaignCode;
	}

	public String getCampaignName()
	{
		return campaignName;
	}

	public void setCampaignName(String campaignName)
	{
		this.campaignName = campaignName;
	}

	public String getCampaignNameComm()
	{
		return campaignNameComm;
	}

	public void setCampaignNameComm(String campaignNameComm)
	{
		this.campaignNameComm = campaignNameComm;
	}

	public String getCampaignNameMgl()
	{
		return campaignNameMgl;
	}

	public void setCampaignNameMgl(String campaignNameMgl)
	{
		this.campaignNameMgl = campaignNameMgl;
	}

	public String getReportName()
	{
		return reportName;
	}

	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}

	public String getCallCenter()
	{
		return callCenter;
	}

	public void setCallCenter(String callCenter)
	{
		this.callCenter = callCenter;
	}

	public String getListSource()
	{
		return listSource;
	}

	public void setListSource(String listSource)
	{
		this.listSource = listSource;
	}

	public String getInsurer()
	{
		return insurer;
	}

	public void setInsurer(String insurer)
	{
		this.insurer = insurer;
	}

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}


}
