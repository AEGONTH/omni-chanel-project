package com.adms.entity.sale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "S_TSR_POSITION")
public class TsrPosition extends BaseDomain {

	private static final long serialVersionUID = 1675452007568566908L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "POSITION_CODE")
	private String positionCode;

	@Column(name = "POSITION_NAME")
	private String positionName;

	@ManyToOne
	@JoinColumn(name = "TSR_LEVEL", referencedColumnName = "LEVEL_CODE")
	private TsrLevel tsrLevel;

	@Column(name = "IS_ACTIVE")
	private String isActive;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getPositionCode()
	{
		return positionCode;
	}

	public void setPositionCode(String positionCode)
	{
		this.positionCode = positionCode;
	}

	public String getPositionName()
	{
		return positionName;
	}

	public void setPositionName(String positionName)
	{
		this.positionName = positionName;
	}

	public TsrLevel getTsrLevel()
	{
		return tsrLevel;
	}

	public void setTsrLevel(TsrLevel tsrLevel)
	{
		this.tsrLevel = tsrLevel;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	@Override
	public String toString()
	{
		return "TsrPosition [id=" + id + ", positionCode=" + positionCode + ", positionName=" + positionName + ", tsrLevel=" + tsrLevel + ", isActive=" + isActive + "]";
	}

}
