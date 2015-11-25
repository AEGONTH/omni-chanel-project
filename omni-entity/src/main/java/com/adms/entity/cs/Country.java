package com.adms.entity.cs;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name="COUNTRY")
public class Country extends BaseDomain {

	private static final long serialVersionUID = 7958040418190370572L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	
	@Column(name="COUNTRY_NAME_TH")
	private String countryNameTh;
	
	@Column(name="COUNTRY_NAME_EN")
	private String countryNameEn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryNameTh() {
		return countryNameTh;
	}

	public void setCountryNameTh(String countryNameTh) {
		this.countryNameTh = countryNameTh;
	}

	public String getCountryNameEn() {
		return countryNameEn;
	}

	public void setCountryNameEn(String countryNameEn) {
		this.countryNameEn = countryNameEn;
	}
	
}
