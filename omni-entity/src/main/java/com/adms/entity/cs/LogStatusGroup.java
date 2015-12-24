package com.adms.entity.cs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name="LOG_STATUS_GROUP")
public class LogStatusGroup extends BaseDomain {

	private static final long serialVersionUID = 5478097060300309760L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="LOG_GROUP")
	private String logGroup;
	
	@Column(name="PARAM")
	private String param;
	
	@Column(name="VALUE")
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogGroup() {
		return logGroup;
	}

	public void setLogGroup(String logGroup) {
		this.logGroup = logGroup;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
