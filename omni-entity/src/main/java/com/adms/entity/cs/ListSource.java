package com.adms.entity.cs;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name="LIST_SOURCE")
public class ListSource extends BaseDomain {

	private static final long serialVersionUID = 476308879533779408L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="LIST_SOURCE_CODE")
	private String listSourceCode;
	
	@Column(name="LIST_SOURCE_VALUE")
	private String listSourceValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getListSourceCode() {
		return listSourceCode;
	}

	public void setListSourceCode(String listSourceCode) {
		this.listSourceCode = listSourceCode;
	}

	public String getListSourceValue() {
		return listSourceValue;
	}

	public void setListSourceValue(String listSourceValue) {
		this.listSourceValue = listSourceValue;
	}
	
}
