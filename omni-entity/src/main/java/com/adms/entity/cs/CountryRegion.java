package com.adms.entity.cs;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name="COUNTRY_REGION")
public class CountryRegion extends BaseDomain {

	private static final long serialVersionUID = 5614031297084887410L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="COUNTRY_CODE", referencedColumnName="COUNTRY_CODE")
	private Country country;
	
	@Column(name="REGION_CODE")
	private String regionCode;
	
	@Column(name="REGION_NAME_TH")
	private String regionNameTh;
	
	@Column(name="REGION_NAME_EN")
	private String regionNameEn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionNameTh() {
		return regionNameTh;
	}

	public void setRegionNameTh(String regionNameTh) {
		this.regionNameTh = regionNameTh;
	}

	public String getRegionNameEn() {
		return regionNameEn;
	}

	public void setRegionNameEn(String regionNameEn) {
		this.regionNameEn = regionNameEn;
	}
	
}
