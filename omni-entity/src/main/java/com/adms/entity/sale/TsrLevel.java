package com.adms.entity.sale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "S_TSR_LEVEL")
public class TsrLevel extends BaseDomain {

	private static final long serialVersionUID = 8470617238802878433L;

	@Id
	@Column(name = "ID")
	private Long id;

	@OneToOne
	@JoinColumn(name = "PARENT_LEVEL", referencedColumnName = "LEVEL_CODE")
	private TsrLevel parentLevel;

	@Column(name = "LEVEL_CODE")
	private String levelCode;

	@Column(name = "LEVEL_NAME")
	private String levelName;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public TsrLevel getParentLevel()
	{
		return parentLevel;
	}

	public void setParentLevel(TsrLevel parentLevel)
	{
		this.parentLevel = parentLevel;
	}

	public String getLevelCode()
	{
		return levelCode;
	}

	public void setLevelCode(String levelCode)
	{
		this.levelCode = levelCode;
	}

	public String getLevelName()
	{
		return levelName;
	}

	public void setLevelName(String levelName)
	{
		this.levelName = levelName;
	}

	@Override
	public String toString()
	{
		return "TsrLevel [id=" + id + ", parentLevel=" + parentLevel + ", levelCode=" + levelCode + ", levelName=" + levelName + "]";
	}

}
