package com.adms.entity.sale;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "S_RECONFIRM_STATUS")
@Cacheable
public class ReconfirmStatus extends BaseDomain {

	private static final long serialVersionUID = 7372032872823929233L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "RECONFIRM_STATUS")
	private String reconfirmStatus;

	@Column(name = "DESCRIPTION")
	private String description;

	@Override
	public String toString()
	{
		return "ReconfirmStatus [id=" + id + ", reconfirmStatus=" + reconfirmStatus + ", description=" + description + "]";
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getReconfirmStatus()
	{
		return reconfirmStatus;
	}

	public void setReconfirmStatus(String reconfirmStatus)
	{
		this.reconfirmStatus = reconfirmStatus;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
