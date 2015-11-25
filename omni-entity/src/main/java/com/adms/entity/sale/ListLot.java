package com.adms.entity.sale;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "S_LIST_LOT")
@Cacheable
public class ListLot extends BaseAuditDomain {

	private static final long serialVersionUID = -1825478009604393158L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "CAMPAIGN_CODE", referencedColumnName = "CAMPAIGN_CODE")
	private Campaign campaign;

	@Column(name = "LIST_LOT_CODE")
	private String listLotCode;

	@Column(name = "LIST_LOT_NAME")
	private String listLotName;

	@Column(name = "LOT_EFFECTIVE_DATE")
	private Date lotEffectiveDate;

	@Column(name = "SCRIPT_ID")
	private String scriptId;
	
	public ListLot() {
		
	}
	
	public ListLot(String listLotCode) {
		this.listLotCode = listLotCode;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Campaign getCampaign()
	{
		return campaign;
	}

	public void setCampaign(Campaign campaign)
	{
		this.campaign = campaign;
	}

	public String getListLotCode()
	{
		return listLotCode;
	}

	public void setListLotCode(String listLotCode)
	{
		this.listLotCode = listLotCode;
	}

	public String getListLotName()
	{
		return listLotName;
	}

	public void setListLotName(String listLotName)
	{
		this.listLotName = listLotName;
	}

	public Date getLotEffectiveDate()
	{
		return lotEffectiveDate;
	}

	public void setLotEffectiveDate(Date lotEffectiveDate)
	{
		this.lotEffectiveDate = lotEffectiveDate;
	}

	public String getScriptId()
	{
		return scriptId;
	}

	public void setScriptId(String scriptId)
	{
		this.scriptId = scriptId;
	}

	@Override
	public String toString()
	{
		return "ListLot [id=" + id + ", campaign=" + campaign + ", listLotCode=" + listLotCode + ", listLotName=" + listLotName + ", lotEffectiveDate=" + lotEffectiveDate + ", scriptId=" + scriptId + "]";
	}

}
