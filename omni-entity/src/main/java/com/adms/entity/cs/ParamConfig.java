package com.adms.entity.cs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name="PARAM_CONFIG")
public class ParamConfig extends BaseDomain {

	private static final long serialVersionUID = 4640998882226451919L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="PARAM_GROUP")
	private String paramGroup;
	
	@Column(name="PARAM_KEY")
	private String paramKey;
	
	@Column(name="PARAM_VALUE")
	private String paramValue;

	public Long getId() {
		return id;
	}

	public ParamConfig setId(Long id) {
		this.id = id;
		return this;
	}

	public String getParamGroup() {
		return paramGroup;
	}

	public ParamConfig setParamGroup(String paramGroup) {
		this.paramGroup = paramGroup;
		return this;
	}

	public String getParamKey() {
		return paramKey;
	}

	public ParamConfig setParamKey(String paramKey) {
		this.paramKey = paramKey;
		return this;
	}

	public String getParamValue() {
		return paramValue;
	}

	public ParamConfig setParamValue(String paramValue) {
		this.paramValue = paramValue;
		return this;
	}
	
}
